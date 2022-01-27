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
