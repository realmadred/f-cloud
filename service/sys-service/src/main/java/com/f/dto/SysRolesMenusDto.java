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
package com.f.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 角色菜单关系
 * </p>
 *
 * @author liuf
 * @date 2022-01-11
 */
@Data
public class SysRolesMenusDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @Min(1)
    @NotNull
    private Long roleId;

    /**
     * 菜单id 数组
     */
    @NotNull
    private List<Long> menuIds;

}
