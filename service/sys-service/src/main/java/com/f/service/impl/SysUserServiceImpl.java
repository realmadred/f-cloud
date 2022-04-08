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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.f.base.PageRequest;
import com.f.entity.SysUser;
import com.f.mapper.SysUserMapper;
import com.f.service.BaseServiceImpl;
import com.f.service.SysUserService;
import com.f.utils.ServiceUtils;
import com.google.common.base.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author liuf
 * @date 2021-12-15
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {


    @Override
    public Page<SysUser> selectPage(PageRequest<SysUser> page) {
        LambdaQueryWrapper<SysUser> lambdaQuery = Wrappers.lambdaQuery(SysUser.class);
        SysUser entity = page.getEntity();
        if (entity != null) {
            if (!Strings.isNullOrEmpty(entity.getName())) {
                lambdaQuery.like(SysUser::getName, entity.getName());
            }
        }
        return page(page.toPlusPage(), lambdaQuery);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysUser insert(SysUser entity) {
        String password = entity.getPassword();
        if (!Strings.isNullOrEmpty(password)) {
            entity.setPassword(ServiceUtils.encodePassword(entity.getPassword()));
        }
        return super.insert(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(SysUser entity) {
        String password = entity.getPassword();
        if (!Strings.isNullOrEmpty(password)) {
            entity.setPassword(ServiceUtils.encodePassword(entity.getPassword()));
        }
        return super.update(entity);
    }
}
