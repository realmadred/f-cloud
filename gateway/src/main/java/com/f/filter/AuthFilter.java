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
package com.f.filter;

import com.f.cache.CacheTemplate;
import com.f.config.MyGatewayProperties;
import com.f.config.ServiceProperties;
import com.f.constant.Constant;
import com.f.utils.GatewayUtils;
import com.f.utils.JwtUtils;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;
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

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

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
    private final ServiceProperties serviceProperties;
    private final CacheTemplate cacheTemplate;
    private AsyncLoadingCache<String, Long> jidCache;

    @PostConstruct
    public void init() {
        jidCache = Caffeine.newBuilder()
                .maximumSize(8096)
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .buildAsync(key -> cacheTemplate.sync().exists(key));
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();
        // 内部访问id
        GatewayUtils.addHeader(mutate, Constant.INNER_HEADER, serviceProperties.getId());
        if (gatewayProperties.getNotAuthUris().contains(request.getURI().getPath())) {
            return chain.filter(exchange.mutate().request(mutate.build()).build());
        }
        String token = request.getHeaders().getFirst(Constant.TOKEN);
        if (StringUtils.isEmpty(token)) {
            return GatewayUtils.responseToLogin(exchange.getResponse());
        }
        Claims claims = JwtUtils.parseToken(token);
        if (claims == null) {
            return GatewayUtils.responseToLogin(exchange.getResponse());
        }
        String jid = JwtUtils.getJid(claims);
        String userid = JwtUtils.getUserId(claims);
        String username = JwtUtils.getUserName(claims);
        if (StringUtils.isAnyEmpty(userid, username, jid)) {
            return GatewayUtils.responseToLogin(exchange.getResponse());
        }

        // 判断是否退出登录了
        return Mono.fromFuture(jidCache.get(jid)).flatMap(v -> {
            if (Constant.ONE == v) {
                return GatewayUtils.responseToLogin(exchange.getResponse());
            } else {
                // 设置用户信息到请求
                GatewayUtils.addHeader(mutate, Constant.JWT_ID, jid);
                GatewayUtils.addHeader(mutate, Constant.USER_ID, userid);
                GatewayUtils.addHeader(mutate, Constant.USER_NAME, username);
                return chain.filter(exchange.mutate().request(mutate.build()).build());
            }
        });
    }

    @Override
    public int getOrder() {
        // 数值越小越先执行 pre
        return -100;
    }
}