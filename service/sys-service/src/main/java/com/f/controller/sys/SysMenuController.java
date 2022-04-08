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

import com.f.base.Result;
import com.f.controller.BaseController;
import com.f.dto.SysMenuDto;
import com.f.entity.SysMenu;
import com.f.service.SysMenuService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * 系统菜单  接口
 *
 * @author liuf
 * @date 2021-12-29
 */
@RestController
@RequestMapping("/sysMenu")
@RequiredArgsConstructor
@Getter
public class SysMenuController extends BaseController<SysMenu, SysMenuService> {

    private final SysMenuService service;

    /**
     * 批量添加菜单
     *
     * @param treeList 菜单树列表
     * @return 结果
     */
    @PostMapping("/batch")
    public Result<Void> batchInsert(@RequestBody List<SysMenuDto> treeList) {
        return getService().batchInsert(treeList);
    }

    /**
     * 查询用户拥有的所有菜单
     *
     * @param type 菜单类型
     * @return 菜单树
     */
    @GetMapping("/tree")
    public Result<List<SysMenuDto>> selectUserTree(Integer type) {
        return getService().selectUserTree(type);
    }

    /**
     * 用户查询自己所有的按钮权限
     *
     * @return 用户所有按钮权限
     */
    @GetMapping("/perms")
    public Result<Set<String>> selectPerms() {
        return Result.success(getService().selectPerms());
    }

}

