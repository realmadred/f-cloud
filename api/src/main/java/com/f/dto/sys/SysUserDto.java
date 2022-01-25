/*
 * Copyright [2021] [liufeng]
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

package com.f.dto.sys;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 系统用户dto
 *
 * @author liuf
 * @date 2021/12/15 10:11
 */
@Data
public class SysUserDto implements Serializable {

    private static final long serialVersionUID = -4471676617706013455L;

    /**
     * 姓名
     */
    @NotNull
    @Length(min = 2, max = 30, message = "用户名: {org.hibernate.validator.constraints.Length.message}")
    private String name;

    /**
     * 密码
     */
    @NotNull
    @Length(min = 6, max = 60, message = "密码: {org.hibernate.validator.constraints.Length.message}")
    private String password;

}
