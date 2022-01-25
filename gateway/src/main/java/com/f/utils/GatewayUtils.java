package com.f.utils;

import com.f.base.Result;
import com.f.enums.ResultEnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 网关工具类
 *
 * @author liufeng
 * @date 2022年1月25日
 */
public final class GatewayUtils {

    private GatewayUtils() {
    }

    private static final byte[] UNAUTHORIZED = Json.jsonBytes(Result.fail(ResultEnum.UNAUTHORIZED));
    private static final byte[] FORBIDDEN = Json.jsonBytes(Result.fail(ResultEnum.FORBIDDEN));

    /**
     * 返回去登录json
     *
     * @param response res
     */
    public static Mono<Void> responseToLogin(ServerHttpResponse response) {
        return responseJson(response, UNAUTHORIZED);
    }

    /**
     * 返回没有权限
     *
     * @param response res
     * @date 2022年1月13日
     */
    public static Mono<Void> responseForbidden(ServerHttpResponse response) {
        return responseJson(response, FORBIDDEN);
    }

    /**
     * 返回json
     *
     * @param response res
     * @param bytes    数据
     */
    public static Mono<Void> responseJson(ServerHttpResponse response, byte[] bytes) {
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return response.writeWith(Mono.just(response.bufferFactory().wrap(bytes)));
    }

    /**
     * 返回json
     *
     * @param response res
     * @param json     数据
     */
    public static Mono<Void> responseJson(ServerHttpResponse response, String json) {
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return response.writeWith(Mono.just(response.bufferFactory().wrap(json.getBytes(StandardCharsets.UTF_8))));
    }

    /**
     * 返回json
     *
     * @param response res
     * @param object   数据
     */
    public static Mono<Void> responseJson(ServerHttpResponse response, Object object) {
        return responseJson(response, Json.jsonBytes(object));
    }

    /**
     * 返回失败
     *
     * @param response ServerHttpResponse
     * @param msg      消息
     * @return Mono<Void>
     */
    public static Mono<Void> responseFail(ServerHttpResponse response, String msg) {
        return responseFail(response, msg, ResultEnum.COMMON.getCode());
    }

    /**
     * 返回失败
     *
     * @param response ServerHttpResponse
     * @param code     响应状态码
     * @param msg      消息
     * @return Mono<Void>
     */
    public static Mono<Void> responseFail(ServerHttpResponse response, String msg, int code) {
        return responseJson(response, Json.jsonBytes(Result.fail(code, msg)));
    }

    /**
     * 返回失败
     *
     * @param response   ServerHttpResponse
     * @param resultEnum resultEnum
     * @return Mono<Void>
     */
    public static Mono<Void> responseFail(ServerHttpResponse response, ResultEnum resultEnum) {
        return responseFail(response, resultEnum.getMsg(), resultEnum.getCode());
    }

    /**
     * 内容编码
     *
     * @param str 内容
     * @return 编码后的内容
     */
    public static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    /**
     * 内容解码
     *
     * @param str 内容
     * @return 解码后的内容
     */
    public static String urlDecode(String str) {
        try {
            return URLDecoder.decode(str, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    /**
     * 添加请求头
     *
     * @param builder ServerHttpRequest
     * @param name    名称
     * @param value   值
     */
    public static void addHeader(ServerHttpRequest.Builder builder, String name, String value) {
        if (value == null) {
            return;
        }
        builder.header(name, GatewayUtils.urlEncode(value));
    }

    /**
     * 删除请求头
     *
     * @param builder ServerHttpRequest
     * @param name    名称
     */
    public static void removeHeader(ServerHttpRequest.Builder builder, String name) {
        builder.headers(httpHeaders -> httpHeaders.remove(name)).build();
    }
}
