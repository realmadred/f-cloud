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
package com.f.mapper;

import com.f.entity.sys.SysMenu;
import com.f.injector.MyBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 系统菜单 Mapper 接口
 *
 * @author liuf
 * @date 2021-12-29
 */
public interface SysMenuMapper extends MyBaseMapper<SysMenu> {

    /**
     * 根据用户id查询菜单
     *
     * @date 2022年1月12日
     * @param userId 用户id
     * @param type 菜单类型
     * @return 菜单
     */
    List<SysMenu> selectMenuByUserId(@Param("userId") long userId, @Param("type") Integer type);

    /**
     * 根据用户id查询按钮权限
     * @param userId 用户id
     * @return 用户权限
     */
    Set<String> selectPermsByUserId(@Param("userId") Long userId);
}
