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
package com.f.controller.sys;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.f.base.Result;
import com.f.controller.BaseController;
import com.f.dto.SysUsersRolesDto;
import com.f.entity.SysUser;
import com.f.entity.SysUsersRoles;
import com.f.service.SysUserService;
import com.f.service.SysUsersRolesService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统用户  接口
 *
 * @author liuf
 * @date 2021-12-15
 */
@RestController
@RequestMapping("/sysUser")
@RequiredArgsConstructor
@Getter
public class SysUserController extends BaseController<SysUser, SysUserService> {

    private final SysUserService service;

    private final SysUsersRolesService usersRolesService;

    /**
     * 查询用户关联角色id数组
     *
     * @param id 用户id
     * @return 角色d
     */
    @GetMapping("/roleByUserId")
    public Result<List<Long>> selectMenuByRoleId(@Valid @Min(0) Long id) {
        return Result.success(usersRolesService.list(Wrappers.lambdaQuery(SysUsersRoles.class)
                        .eq(SysUsersRoles::getUserId, id).select(SysUsersRoles::getRoleId)).stream()
                .map(SysUsersRoles::getRoleId).collect(Collectors.toList()));
    }

    /**
     * 用户关联角色
     *
     * @param usersRolesDto 用户角色关系
     * @return 结果
     */
    @PostMapping("/bindRole")
    public Result<Void> bindRole(@Valid @RequestBody SysUsersRolesDto usersRolesDto) {
        return usersRolesService.bindRole(usersRolesDto);
    }

    /**
     * 解除用户关联角色
     *
     * @param usersRolesDto 用户角色关系
     * @return 结果
     */
    @PostMapping("/unBindRole")
    public Result<Void> unBindRole(@Valid @RequestBody SysUsersRolesDto usersRolesDto) {
        return usersRolesService.unBindRole(usersRolesDto);
    }
}

