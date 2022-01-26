package com.f.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.headers.HttpHeadersFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * 添加请求头
 *
 * @author liuf
 * @date 2022年1月26日
 */
@Component
@Slf4j
public class HeaderWriterFilter implements HttpHeadersFilter, Ordered {

    @Override
    public boolean supports(Type type) {
        return type.equals(Type.RESPONSE);
    }

    @Override
    public HttpHeaders filter(HttpHeaders headers, ServerWebExchange exchange) {
        // 增加安全性的请求头
        headers.add("X-XSS-Protection", "1; mode=block");
        headers.add("X-Frame-Options", "deny");
        headers.add("x-content-type-options", "nosniff");
        headers.add("referrer-policy", "no-referrer-when-downgrade");
        headers.add("permissions-policy", "interest-cohort=()");
        // content-security-policy: 待定
        return headers;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}