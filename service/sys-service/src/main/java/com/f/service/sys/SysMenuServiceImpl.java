/*
 * Copyright [2021] [liufeng]
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.f.service.sys;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.f.base.Result;
import com.f.constant.Constant;
import com.f.converter.sys.SysMenuConverter;
import com.f.dto.sys.SysMenuDto;
import com.f.entity.sys.SysMenu;
import com.f.mapper.sys.SysMenuMapper;
import com.f.service.base.BaseServiceImpl;
import com.f.utils.CommonUtils;
import com.f.utils.IdUtils;
import com.f.utils.ServiceUtils;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统菜单 服务实现类
 * </p>
 *
 * @author liuf
 * @date 2021-12-29
 */
@Service
@Slf4j
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Resource
    @Lazy
    private SysMenuService menuServiceThis;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Void> batchInsert(List<SysMenuDto> treeList) {
        log.info("batchInsert menu");
        if (CollectionUtils.isEmpty(treeList)) {
            return Result.success();
        }
        log.info("循环处理菜单数据");
        Joiner joiner = Joiner.on(Constant.COMMA).skipNulls();
        List<SysMenuDto> dtoList = CommonUtils.flatTree(treeList, (dto, level) -> {
            long id = IdUtils.id();
            dto.setId(id);
            dto.setLevel(level);
            if (!CollectionUtils.isEmpty(dto.getChildren())) {
                // 设置下级的父级id
                String pids = joiner.join(dto.getPids(), id);
                dto.getChildren().forEach(c -> {
                    c.setPid(id);
                    c.setPids(pids);
                });
            }
        });

        int size = dtoList.size();
        List<SysMenu> sysMenuList = new ArrayList<>(size);
        for (SysMenuDto sysMenuDto : dtoList) {
            SysMenu sysMenu = SysMenuConverter.INSTANCE.d2e(sysMenuDto);
            sysMenu.setStatus(Constant.YES);
            if (sysMenuDto.getChildren() != null) {
                sysMenu.setSubCount(sysMenuDto.getChildren().size());
            }
            sysMenuList.add(sysMenu);
        }
        dtoList.clear();
        ServiceUtils.setCreateUser(sysMenuList);
        log.info("保存到数据库");
        saveBatch(sysMenuList);
        return Result.success();
    }

    @Override
    public SysMenu insert(SysMenu entity) {
        Long pid = entity.getPid();
        if (pid == null || pid < 1) {
            entity.setPid(0L);
            entity.setLevel(0);
        } else {
            SysMenu parent = getById(pid);
            entity.setPids(Joiner.on(Constant.COMMA).skipNulls().join(parent.getPids(), pid));
            entity.setLevel(parent.getLevel() + 1);
        }
        return super.insert(entity);
    }

    @Override
    public Result<List<SysMenuDto>> selectUserTree(Integer type) {
        log.info("查询用户所有菜单");
        // 1 管理员有所有菜单 2 其它用户根据角色查询菜单
        long userId = ServiceUtils.getUserId();
        List<SysMenu> menuList;
        if (Constant.ADMIN_ID == userId) {
            menuList = list(Wrappers.lambdaQuery(SysMenu.class).eq(type != null, SysMenu::getType, type));
        } else {
            menuList = getBaseMapper().selectMenuByUserId(userId, type);
        }

        if (CollectionUtils.isEmpty(menuList)) {
            return Result.success();
        }

        List<SysMenuDto> result = new ArrayList<>(menuList.size());
        for (SysMenu sysMenu : menuList) {
            SysMenuDto sysMenuDto = SysMenuConverter.INSTANCE.e2d(sysMenu);
            result.add(sysMenuDto);
        }

        log.info("开始构建树");
        List<SysMenuDto> list = CommonUtils.buildTreeDto(result, 0L);
        list.sort(Comparator.comparing(SysMenuDto::getSort));
        return Result.success(list);
    }

    @Override
    public Set<String> selectPerms() {
        return menuServiceThis.selectPermsByUserId(ServiceUtils.getUserId());
    }

    @Cached(name = "perms", key = "#userId", localLimit = 1024, localExpire = 5, expire = 120, cacheType = CacheType.BOTH)
    @Override
    public Set<String> selectPermsByUserId(Long userId) {
        return getBaseMapper().selectPermsByUserId(userId);
    }
}
