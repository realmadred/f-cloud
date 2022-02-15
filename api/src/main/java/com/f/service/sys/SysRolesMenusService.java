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
package com.f.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.f.base.Result;
import com.f.dto.sys.SysRolesMenusDto;
import com.f.entity.sys.SysRolesMenus;

/**
 * <p>
 * 角色菜单关系 服务类
 * </p>
 *
 * @author liuf
 * @date 2022-01-11
 */
public interface SysRolesMenusService extends IService<SysRolesMenus> {

    /**
     * 绑定菜单
     * @param rolesMenus 关系
     * @return 结果
     */
    Result<Void> bindMenu(SysRolesMenusDto rolesMenus);

    /**
     * 解除绑定菜单
     * @param rolesMenus 关系
     * @return 结果
     */
    Result<Void> unBindMenu(SysRolesMenusDto rolesMenus);
}
