/*
 * Copyright [2022] [liufeng]
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
package com.f.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.f.base.PageRequest;
import com.f.entity.SysRole;
import com.f.mapper.SysRoleMapper;
import com.f.service.BaseServiceImpl;
import com.f.service.SysRoleService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 系统角色 服务实现类
 * </p>
 *
 * @author liuf
 * @date 2022-01-11
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public Page<SysRole> selectPage(PageRequest<SysRole> page) {
        LambdaQueryWrapper<SysRole> lambdaQuery = Wrappers.lambdaQuery(SysRole.class);
        SysRole entity = page.getEntity();
        if (entity != null) {
            if (Strings.isNotBlank(entity.getName())) {
                lambdaQuery.like(SysRole::getName, entity.getName());
            }
        }
        return page(page.toPlusPage(), lambdaQuery);
    }

    @Cached(name = "sysRole", key = "1", expire = 300, cacheType = CacheType.REMOTE)
    @Override
    public List<SysRole> selectAll() {
        return list();
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheInvalidate(name = "sysRole", key = "1")
    @Override
    public SysRole insert(SysRole entity) {
        return super.insert(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheInvalidate(name = "sysRole", key = "1")
    @Override
    public int update(SysRole entity) {
        return super.update(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheInvalidate(name = "sysRole", key = "1")
    @Override
    public int logicDelete(Long id) {
        return super.logicDelete(id);
    }
}
