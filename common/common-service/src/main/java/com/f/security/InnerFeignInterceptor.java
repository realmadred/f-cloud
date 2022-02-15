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
package com.f.security;

import com.f.config.ServiceProperties;
import com.f.constant.Constant;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

/**
 * feign 内部调用拦截器
 *
 * @author liuf
 * @date 2022年2月15日
 */
@ConditionalOnClass(RequestInterceptor.class)
@Configuration
@RequiredArgsConstructor
public class InnerFeignInterceptor implements RequestInterceptor {

    private final ServiceProperties serviceProperties;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(Constant.INNER_HEADER, serviceProperties.getId());
    }
}