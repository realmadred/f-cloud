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
import com.f.dto.sys.SysRolesMenusDto;
import com.f.entity.sys.SysRole;
import com.f.entity.sys.SysRolesMenus;
import com.f.service.sys.SysRoleService;
import com.f.service.sys.SysRolesMenusService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统角色  接口
 *
 * @author liuf
 * @date 2022-01-11
 */
@RestController
@RequestMapping("/sysRole")
@RequiredArgsConstructor
@Getter
public class SysRoleController extends BaseController<SysRole, SysRoleService> {

    private final SysRoleService service;

    private final SysRolesMenusService rolesMenusService;

    /**
     * 查询角色关联的菜单id数组
     *
     * @param id 角色id
     * @return 菜单id
     */
    @GetMapping("/menuByRoleId")
    public Result<List<Long>> selectMenuByRoleId(@Valid @Min(0) Long id) {
        return Result.success(rolesMenusService.list(Wrappers.lambdaQuery(SysRolesMenus.class)
                        .eq(SysRolesMenus::getRoleId, id).select(SysRolesMenus::getMenuId)).stream()
                .map(SysRolesMenus::getMenuId).collect(Collectors.toList()));
    }

    /**
     * 角色关联的菜单id
     *
     * @param rolesMenus 角色菜单关系
     * @return 菜单id
     */
    @PostMapping("/bindMenu")
    public Result<Void> bindMenu(@Valid @RequestBody SysRolesMenusDto rolesMenus) {
        return rolesMenusService.bindMenu(rolesMenus);
    }

    /**
     * 解除角色关联的菜单id
     *
     * @param rolesMenus 角色菜单关系
     * @return 菜单id
     */
    @PostMapping("/unBindMenu")
    public Result<Void> unBindMenu(@Valid @RequestBody SysRolesMenusDto rolesMenus) {
        return rolesMenusService.unBindMenu(rolesMenus);
    }

    /**
     * 查询所有角色
     *
     * @return 所有角色
     */
    @GetMapping("/all")
    public Result<List<SysRole>> selectAll() {
        return Result.success(service.selectAll());
    }
}

