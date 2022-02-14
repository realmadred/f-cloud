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

package com.f.utils;

import com.f.base.BaseEntity;
import com.f.base.Result;
import com.f.constant.Constant;
import com.f.enums.ResultEnum;
import com.f.vo.sys.SysUserVo;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * service工具类
 *
 * @author liuf
 * @date 2021/12/15 10:07
 */
public final class ServiceUtils {

    /**
     * 加盟盐
     */
    private static final String SALT = "yu_aW7!9L#p$czo_1G";
    private static final byte[] UNAUTHORIZED = Json.jsonBytes(Result.fail(ResultEnum.UNAUTHORIZED));
    private static final byte[] FORBIDDEN = Json.jsonBytes(Result.fail(ResultEnum.FORBIDDEN));
    private static final byte[] INNER_FORBIDDEN = Json.jsonBytes(Result.fail(ResultEnum.INNER_FORBIDDEN));

    /**
     * 加盟工具
     */
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private static final SysUserVo ANONYMOUS = new SysUserVo();

    static {
        ANONYMOUS.setId(0L);
        ANONYMOUS.setName("");
    }

    /**
     * 获取当前登录用户
     *
     * @return 系统用户
     */
    public static SysUserVo getSysUser() {
        final HttpServletRequest request = getCurrentRequest();
        final String userName = request.getHeader(Constant.USER_NAME);
        final String userId = getCurrentRequest().getHeader(Constant.USER_ID);
        final SysUserVo sysUserVo = new SysUserVo();
        sysUserVo.setId(LambdaUtils.getOrElse(userId, Long::parseLong, ANONYMOUS.getId()));
        sysUserVo.setJid(getCurrentRequest().getHeader(Constant.JWT_ID));
        sysUserVo.setName(Optional.ofNullable(userName).orElse(ANONYMOUS.getName()));
        return sysUserVo;
    }

    /**
     * 获取当前登录用户id
     *
     * @return 用户id
     * @date 2022年1月12日
     */
    public static long getUserId() {
        return Optional.ofNullable(getCurrentRequest().getHeader(Constant.USER_ID)).map(Long::parseLong).orElse(ANONYMOUS.getId());
    }

    /**
     * 获取当前登录用户id
     *
     * @return 用户id
     * @date 2022年1月12日
     */
    public static String getJid() {
        return getCurrentRequest().getHeader(Constant.JWT_ID);
    }

    /**
     * 获取aes redis key
     *
     * @return 用户id
     * @date 2022年1月12日
     */
    public static String getAesRedisKey(String jid) {
        return Constant.JWT_ID + jid;
    }

    /**
     * 设置用户信息
     *
     * @param <T>    实体泛型
     * @param entity 实体
     */
    public static <T extends BaseEntity> void setCreateUser(@NonNull T entity) {
        final SysUserVo sysUser = getSysUser();
        if (entity.getId() == null) {
            entity.setId(IdUtils.id());
        }
        entity.setCreateId(sysUser.getId());
        entity.setCreateName(sysUser.getName());
        final LocalDateTime now = LocalDateTime.now();
        entity.setCreateTime(now);
        entity.setUpdateId(sysUser.getId());
        entity.setUpdateName(sysUser.getName());
        entity.setUpdateTime(now);
        entity.setIsDelete(Constant.DELETE_NO);
    }

    /**
     * 设置用户信息
     *
     * @param <T>        实体泛型
     * @param entityList 实体信息
     */
    public static <T extends BaseEntity> void setCreateUser(@NonNull List<T> entityList) {
        final SysUserVo sysUser = getSysUser();
        final LocalDateTime now = LocalDateTime.now();
        entityList.forEach(entity -> {
            if (entity.getId() == null) {
                entity.setId(IdUtils.id());
            }
            entity.setCreateId(sysUser.getId());
            entity.setCreateName(sysUser.getName());
            entity.setCreateTime(now);
            entity.setUpdateId(sysUser.getId());
            entity.setUpdateName(sysUser.getName());
            entity.setUpdateTime(now);
            entity.setIsDelete(Constant.DELETE_NO);
        });
    }

    /**
     * 设置用户信息
     *
     * @param <T>    实体泛型
     * @param entity 实体
     */
    public static <T extends BaseEntity> void setUpdateUser(@NonNull T entity) {
        setUpdateUser(entity, getSysUser());
    }

    /**
     * 设置用户信息
     *
     * @param <T>        实体泛型
     * @param entityList 实体列表
     */
    public static <T extends BaseEntity> void setUpdateUser(@NonNull List<T> entityList) {
        final SysUserVo sysUser = getSysUser();
        final LocalDateTime now = LocalDateTime.now();
        entityList.forEach(entity -> {
            entity.setUpdateId(sysUser.getId());
            entity.setUpdateName(sysUser.getName());
            entity.setUpdateTime(now);
        });
    }

    /**
     * 设置用户信息
     *
     * @param <T>     实体泛型
     * @param entity  实体
     * @param sysUser 用户信息
     */
    public static <T extends BaseEntity> void setUpdateUser(@NonNull T entity, SysUserVo sysUser) {
        entity.setUpdateId(sysUser.getId());
        entity.setUpdateName(sysUser.getName());
        entity.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 加盟密码
     *
     * @param password 原始密码
     * @return 加密后密码
     * @date 2022年1月12日
     */
    public static String encodePassword(String password) {
        return PASSWORD_ENCODER.encode(SALT + password);
    }

    /**
     * 密码匹配
     *
     * @param password        原始密码
     * @param encodedPassword 编码后的密码
     * @return 是否匹配
     * @date 2022年1月12日
     */
    public static boolean matches(String password, String encodedPassword) {
        return PASSWORD_ENCODER.matches(SALT + password, encodedPassword);
    }

    /**
     * 返回去登录json
     *
     * @param response res
     */
    public static void responseToLogin(ServletResponse response) {
        responseJson(response, UNAUTHORIZED);
    }

    /**
     * 返回没有权限
     *
     * @param response res
     * @date 2022年1月13日
     */
    public static void responseForbidden(ServletResponse response) {
        responseJson(response, FORBIDDEN);
    }

    /**
     * 返回非法访问
     *
     * @param response res
     * @date 2022年2月14日
     */
    public static void responseInnerForbidden(ServletResponse response) {
        responseJson(response, INNER_FORBIDDEN);
    }

    /**
     * 返回json
     *
     * @param response res
     * @param bytes    数据
     */
    @SneakyThrows
    public static void responseJson(ServletResponse response, byte[] bytes) {
        response.setContentType(Constant.APPLICATION_JSON);
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write(bytes);
        }
    }

    /**
     * 返回json
     *
     * @param response res
     * @param object   数据
     */
    public static void responseJson(ServletResponse response, Object object) {
        responseJson(response, Json.jsonBytes(object));
    }

    /**
     * 获取当前请求上下文
     *
     * @return 请求上下文
     * @date 2022年2月12日
     */
    public static ServletRequestAttributes currentRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    }

    /**
     * 获取当前请求
     *
     * @return 请求
     * @date 2022年2月12日
     */
    public static HttpServletRequest getCurrentRequest() {
        return currentRequestAttributes().getRequest();
    }

    /**
     * 获取当前响应
     *
     * @return 请求响应
     * @date 2022年2月12日
     */
    public static HttpServletResponse getCurrentResponse() {
        return currentRequestAttributes().getResponse();
    }

    /**
     * 获取Ip地址
     *
     * @param request 请求
     * @return 真实ip
     * @date 2022年2月14日
     */
    public static String getIp(HttpServletRequest request) {
        if (request == null) {
            return "";
        }

        String ip = request.getHeader("X-Real-IP");
        if (isBlankOrUnknownIp(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }

        if (isBlankOrUnknownIp(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (isBlankOrUnknownIp(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (isBlankOrUnknownIp(ip)) {
            ip = request.getRemoteAddr();
        }

        //多次反向代理后会有多个ip值，第一个ip才是真实ip
        int index = ip.indexOf(",");
        if (index > -1) {
            return ip.substring(0, index);
        } else {
            return ip;
        }
    }

    private static boolean isBlankOrUnknownIp(final String ip) {
        return !StringUtils.hasText(ip) || "unknown".equalsIgnoreCase(ip);
    }

    private ServiceUtils() {
    }
}
