package com.f.filter;

import com.f.config.MyGatewayProperties;
import com.f.constant.Constant;
import com.f.utils.GatewayUtils;
import com.f.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 网关鉴权
 *
 * @author liuf
 * @date 2022年1月25日
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AuthFilter implements GlobalFilter, Ordered {

    private final MyGatewayProperties gatewayProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();

        if (gatewayProperties.getNotAuthUris().contains(request.getURI().getPath())) {
            return chain.filter(exchange);
        }
        String token = request.getHeaders().getFirst(Constant.TOKEN);
        if (StringUtils.isEmpty(token)) {
            return GatewayUtils.responseToLogin(exchange.getResponse());
        }
        Claims claims = JwtUtils.parseToken(token);
        if (claims == null) {
            return GatewayUtils.responseToLogin(exchange.getResponse());
        }
        String userKey = JwtUtils.getUserKey(claims);
        String userid = JwtUtils.getUserId(claims);
        String username = JwtUtils.getUserName(claims);
        if (StringUtils.isEmpty(userid) || StringUtils.isEmpty(username)) {
            return GatewayUtils.responseToLogin(exchange.getResponse());
        }

        // 设置用户信息到请求
        GatewayUtils.addHeader(mutate, Constant.USER_KEY, userKey);
        GatewayUtils.addHeader(mutate, Constant.USER_ID, userid);
        GatewayUtils.addHeader(mutate, Constant.USER_NAME, username);
        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }

    @Override
    public int getOrder() {
        // 数值越小越先执行 pre
        return -100;
    }
}