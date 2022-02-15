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
package com.f.utils;

import com.f.constant.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Objects;

/**
 * jwt工具类
 *
 * @author liufeng
 * @date 2022年1月25日
 */
public class JwtUtils {

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     * @date 2022年1月25日
     */
    public static Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(Constant.JWT_SECRET).parseClaimsJws(token).getBody();
    }

    /**
     * 根据令牌获取用户标识
     *
     * @param claims 身份信息
     * @return 用户ID
     * @date 2022年1月25日
     */
    public static String getJid(Claims claims) {
        return getValue(claims, Constant.JWT_ID);
    }

    /**
     * 根据身份信息获取用户ID
     *
     * @param claims 身份信息
     * @return 用户ID
     * @date 2022年1月25日
     */
    public static String getUserId(Claims claims) {
        return getValue(claims, Constant.USER_ID);
    }

    /**
     * 根据身份信息获取用户名
     *
     * @param claims 身份信息
     * @return 用户名
     * @date 2022年1月25日
     */
    public static String getUserName(Claims claims) {
        return getValue(claims, Constant.USER_NAME);
    }

    /**
     * 根据身份信息获取键值
     *
     * @param claims 身份信息
     * @param key    键
     * @return 值
     * @date 2022年1月25日
     */
    public static String getValue(Claims claims, String key) {
        return Objects.toString(claims.get(key), "");
    }
}
