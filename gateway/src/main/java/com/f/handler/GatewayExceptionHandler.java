package com.f.handler;

import com.f.base.Result;
import com.f.enums.ResultEnum;
import com.f.utils.GatewayUtils;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
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
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {

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
        } else if (throwable instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) throwable;
            result = Result.fail(responseStatusException.getStatus().value(), responseStatusException.getMessage());
        }

        log.error("uri:{},异常:{}", exchange.getRequest().getPath(), throwable.getMessage());
        return GatewayUtils.responseJson(response, result);
    }
}