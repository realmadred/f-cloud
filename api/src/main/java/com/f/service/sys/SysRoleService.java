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

import com.f.entity.sys.SysRole;
import com.f.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 系统角色 服务类
 * </p>
 *
 * @author liuf
 * @date 2022-01-11
 */
public interface SysRoleService extends BaseService<SysRole> {

    /**
     * 查询所有角色
     * @return 角色
     */
    List<SysRole> selectAll();
}
