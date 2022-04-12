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
package com.f.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * 微信支付配置
 *
 * @author liuf
 * @date 2022/04/12 13:59
 */
@Data
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "wxpay")
public class WxPayProperties {

    /**
     * 商户号
     */
    private String mchSerialNo;
    /**
     * 商户证书序列号
     */
    private String apiV3Key;
    /**
     * apiV3密钥
     */
    private String privateKeyPath;
    /**
     * 商户私钥路径 apiclient_key.pem
     */
    private String mchId;
}
