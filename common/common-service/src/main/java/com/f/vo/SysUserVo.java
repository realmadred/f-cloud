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
package com.f.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统用户vo
 *
 * @author liuf
 * @date 2021/12/15 10:11
 */
@Data
public class SysUserVo implements Serializable {

    private static final long serialVersionUID = 2311018619982779751L;

    /**
     * id
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * jid
     */
    private String jid;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机
     */
    private String phone;

    /**
     * 头像
     */
    private String headImage;

    /**
     * 是否管理员（0 否 1 是）
     */
    private Integer isAdmin;

    /**
     * token
     */
    private String token;
}
