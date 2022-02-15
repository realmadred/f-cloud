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

import com.f.base.Result;
import com.f.enums.ResultEnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

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
        builder.header(name, CommonUtils.urlEncode(value));
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
