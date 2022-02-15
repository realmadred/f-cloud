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
        headers.add("referrer-policy", "same-origin");
        headers.add("permissions-policy", "interest-cohort=()");
        // content-security-policy: 待定
        return headers;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}