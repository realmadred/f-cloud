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
package com.f.service;

import com.f.base.Result;
import com.f.dto.SysMenuDto;
import com.f.entity.SysMenu;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统菜单 服务类
 * </p>
 *
 * @author liuf
 * @date 2021-12-29
 */
public interface SysMenuService extends BaseService<SysMenu> {

    /**
     * 批量添加菜单
     *
     * @param treeList 列表
     * @return 结果
     */
    Result<Void> batchInsert(List<SysMenuDto> treeList);

    /**
     * 查询用户拥有的所有菜单
     *
     * @param type 菜单类型
     * @return 菜单树
     * @date 2021年12月31日
     */
    Result<List<SysMenuDto>> selectUserTree(Integer type);

    /**
     * 查询用户按钮权限
     *
     * @date 2022年1月13日
     * @return 权限set
     */
    Set<String> selectPerms();

    /**
     * 查询用户按钮权限
     *
     * @param userId 用户id
     * @date 2022年1月13日
     * @return 权限set
     */
    Set<String> selectPermsByUserId(Long userId);
}
