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

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.f.base.Result;
import com.f.dto.SysUsersRolesDto;
import com.f.entity.SysUsersRoles;
import com.f.mapper.SysUsersRolesMapper;
import com.f.service.SysUsersRolesService;
import com.f.utils.IdUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户角色关系 服务实现类
 * </p>
 *
 * @author liuf
 * @date 2022-01-12
 */
@Service
public class SysUsersRolesServiceImpl extends ServiceImpl<SysUsersRolesMapper, SysUsersRoles> implements SysUsersRolesService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result<Void> bindRole(SysUsersRolesDto usersRolesDto) {
        List<Long> roleIds = usersRolesDto.getRoleIds();
        Long userId = usersRolesDto.getUserId();
        getBaseMapper().mysqlInsertBatch(roleIds.stream().map(roleId -> new SysUsersRoles()
                .setId(IdUtils.id())
                .setRoleId(roleId)
                .setUserId(userId)).collect(Collectors.toList()));
        return Result.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result<Void> unBindRole(SysUsersRolesDto usersRolesDto) {
        remove(Wrappers.lambdaQuery(SysUsersRoles.class).eq(SysUsersRoles::getUserId, usersRolesDto.getUserId())
                .in(SysUsersRoles::getRoleId, usersRolesDto.getRoleIds()));
        return Result.success();
    }
}
