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

import com.f.base.Result;
import com.f.constant.Constant;
import com.f.enums.ResultEnum;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 工具类
 *
 * @author liuf
 * @date 2021/12/3 22:03
 */
public final class WebUtils {

    private static final byte[] UNAUTHORIZED = Json.jsonBytes(Result.fail(ResultEnum.UNAUTHORIZED));
    private static final byte[] FORBIDDEN = Json.jsonBytes(Result.fail(ResultEnum.FORBIDDEN));

    private WebUtils() {
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
     * @date 2022年1月12日
     */
    public static ServletRequestAttributes currentRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    }

    /**
     * 获取当前请求
     *
     * @return 请求
     * @date 2022年1月12日
     */
    public static HttpServletRequest getCurrentRequest() {
        return currentRequestAttributes().getRequest();
    }

    /**
     * 获取当前响应
     *
     * @return 请求响应
     * @date 2022年1月12日
     */
    public static HttpServletResponse getCurrentResponse() {
        return currentRequestAttributes().getResponse();
    }


    /**
     * 根据身份信息获取用户ID
     *
     * @param request 请求
     * @return 用户ID
     * @date 2022年1月25日
     */
    public static String getUserId(HttpServletRequest request) {
        return getHeader(request, Constant.USER_ID);
    }

    /**
     * 根据身份信息获取用户名
     *
     * @param request 请求
     * @return 用户名
     * @date 2022年1月25日
     */
    public static String getUserName(HttpServletRequest request) {
        return getHeader(request, Constant.USER_NAME);
    }

    /**
     * 获取请求头
     *
     * @param request 请求
     * @param name    名称
     * @return 请求头
     */
    public static String getHeader(HttpServletRequest request, @NonNull String name) {
        String value = request.getHeader(name);
        if (StringUtils.isEmpty(value)) {
            return StringUtils.EMPTY;
        }
        return CommonUtils.urlDecode(value);
    }

    /**
     * 获取Ip地址
     *
     * @param request 请求
     * @return 真实ip
     * @date 2022年1月14日
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
        return StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip);
    }
}
