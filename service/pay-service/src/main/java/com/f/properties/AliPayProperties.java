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
 * 支付宝支付配置
 *
 * @author liuf
 * @date 2022/04/12 13:59
 */
@Data
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "alipay")
public class AliPayProperties {

    /**
     * 支付宝网关
     */
    public String appId;
    /**
     * appId 例如：2019091767145019
     */
    public String signType;
    /**
     * 签名类型 RSA RSA2
     */
    public String merchantPrivateKeyPath;
    /**
     * 私钥路径
     */
    public String merchantCertPath;
    /**
     * 应用公钥证书文件路径 例如：/foo/appCertPublicKey_2019051064521003.crt
     */
    public String alipayCertPath;
    /**
     * 支付宝公钥证书文件路径 例如：/foo/alipayCertPublicKey_RSA2.crt
     */
    public String alipayRootCertPath;
    /**
     * 支付宝根证书文件路径 例如：/foo/alipayRootCert.crt
     */
    public String encryptKey;
    /**
     * 可设置AES密钥，调用AES加解密相关接口时需要（可选）
     */
    public String gatewayHost;

}
