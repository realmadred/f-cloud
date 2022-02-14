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

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.f.base.Result;
import com.f.dto.sys.SysRolesMenusDto;
import com.f.entity.sys.SysRolesMenus;
import com.f.injector.MyBaseMapper;
import com.f.utils.IdUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色菜单关系 服务实现类
 * </p>
 *
 * @author liuf
 * @date 2022-01-11
 */
@Service
public class SysRolesMenusServiceImpl extends ServiceImpl<MyBaseMapper<SysRolesMenus>, SysRolesMenus> implements SysRolesMenusService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result<Void> bindMenu(SysRolesMenusDto rolesMenus) {
        List<Long> menuIds = rolesMenus.getMenuIds();
        Long roleId = rolesMenus.getRoleId();
        getBaseMapper().mysqlInsertBatch(menuIds.stream().map(menuId -> new SysRolesMenus()
                .setId(IdUtils.id())
                .setMenuId(menuId)
                .setRoleId(roleId)).collect(Collectors.toList()));
        return Result.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result<Void> unBindMenu(SysRolesMenusDto rolesMenus) {
        remove(Wrappers.lambdaQuery(SysRolesMenus.class).eq(SysRolesMenus::getRoleId, rolesMenus.getRoleId())
                .in(SysRolesMenus::getMenuId, rolesMenus.getMenuIds()));
        return Result.success();
    }
}
