package com.f.utils;

import com.f.constant.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Map;
import java.util.Objects;

/**
 * jwt工具类
 *
 * @author liufeng
 * @date 2022年1月25日
 */
public class JwtUtils {

    private final static String SECRET = "r4XzJ_Q9T@CyL*8GWFO";

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     * @date 2022年1月25日
     */
    public static String createToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     * @date 2022年1月25日
     */
    public static Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    /**
     * 根据令牌获取用户标识
     *
     * @param token 令牌
     * @return 用户ID
     * @date 2022年1月25日
     * @date 2022年1月25日
     */
    public static String getUserKey(String token) {
        Claims claims = parseToken(token);
        return getValue(claims, Constant.USER_KEY);
    }

    /**
     * 根据令牌获取用户标识
     *
     * @param claims 身份信息
     * @return 用户ID
     * @date 2022年1月25日
     */
    public static String getUserKey(Claims claims) {
        return getValue(claims, Constant.USER_KEY);
    }

    /**
     * 根据令牌获取用户ID
     *
     * @param token 令牌
     * @return 用户ID
     * @date 2022年1月25日
     */
    public static String getUserId(String token) {
        Claims claims = parseToken(token);
        return getValue(claims, Constant.USER_ID);
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
     * 根据令牌获取用户名
     *
     * @param token 令牌
     * @return 用户名
     * @date 2022年1月25日
     */
    public static String getUserName(String token) {
        Claims claims = parseToken(token);
        return getValue(claims, Constant.USER_NAME);
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
