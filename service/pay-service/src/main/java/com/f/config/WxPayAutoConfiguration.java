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
package com.f.config;

import com.f.properties.WxPayProperties;
import com.f.service.WxPayService;
import com.f.service.impl.WxPayServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信支付配置启动
 *
 * @author liuf
 * @date 2022/04/12
 */
@Configuration
@ConditionalOnProperty(
        prefix = "wxpay",
        name = {"enabled"},
        havingValue = "true"
)
@EnableConfigurationProperties({WxPayProperties.class})
public class WxPayAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public WxPayService wxPayService(WxPayProperties wxPayProperties) {
        return new WxPayServiceImpl(wxPayProperties);
    }
}
