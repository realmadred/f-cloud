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
package com.f.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * @author liuf
 * @date 2021年12月3日
 */
@RequiredArgsConstructor
@Getter
public enum ResultEnum {

    /* 0-1000 公共*/
    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 请登录
     */
    UNAUTHORIZED(401, "请登录"),

    /**
     * 未授权
     */
    FORBIDDEN(403, "未授权"),

    /**
     * 未授权
     */
    NOT_FOUND(404, "未找到接口"),

    /**
     * 服务异常
     */
    ERROR(500, "服务异常，请稍后再试"),

    /**
     * 超时
     */
    GATEWAY_TIMEOUT(504, "请求超时，请稍后再试"),

    /**
     * 登录失败
     */
    LOGIN_FAIL(601, "登录失败"),

    /**
     * 用户已存在
     */
    USER_EXISTS(602, "用户已存在"),

    /**
     * 用户已存在
     */
    SIGN_ERROR(603, "签名错误"),

    /**
     * 密钥已过期
     */
    AES_KEY_EXPIRE(604, "密钥已过期,请刷新页面"),

    /**
     * 通用提醒
     */
    COMMON(999, "未知警告"),

    /**
     * 不是内部访问来源
     */
    INNER_FORBIDDEN(1000, "非法访问");

    /**
     * 状态码
     * 200 表示请求成功
     * 500 异常
     * 其它看提示消息
     */
    private final int code;

    /**
     * 消息
     */
    private final String msg;

    public static ResultEnum by(int code){
        return Arrays.stream(values()).filter(r -> r.code == code).findFirst().orElse(null);
    }
}
