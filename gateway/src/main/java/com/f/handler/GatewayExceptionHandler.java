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
package com.f.handler;

import com.f.base.Result;
import com.f.enums.ResultEnum;
import com.f.utils.GatewayUtils;
import io.jsonwebtoken.JwtException;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * 网关统一异常处理
 *
 * @author liufeng
 * @date 2022年1月25日
 */
@Order(-5)
@Configuration
@Slf4j
@Data
public class GatewayExceptionHandler implements WebExceptionHandler {

    /**
     * 异常处理
     *
     * @param exchange  exchange
     * @param throwable 异常
     * @return void
     */
    @Override
    @NonNull
    public Mono<Void> handle(ServerWebExchange exchange, @NonNull Throwable throwable) {
        ServerHttpResponse response = exchange.getResponse();
        if (exchange.getResponse().isCommitted()) {
            return Mono.error(throwable);
        }

        Result<Void> result = Result.fail(ResultEnum.ERROR);
        if (throwable instanceof NotFoundException) {
            result = Result.fail(ResultEnum.NOT_FOUND);
        } else if (throwable instanceof JwtException) {
            result = Result.fail(ResultEnum.UNAUTHORIZED);
        } else if (throwable instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) throwable;
            final ResultEnum resultEnum = ResultEnum.by(responseStatusException.getStatus().value());
            result = resultEnum == null ? Result.fail(responseStatusException.getStatus().value(), throwable.getMessage()) :
                    Result.fail(resultEnum);
        }

        log.error("uri:{},异常:{}", exchange.getRequest().getPath(), throwable.getMessage());
        return GatewayUtils.responseJson(response, result);
    }
}