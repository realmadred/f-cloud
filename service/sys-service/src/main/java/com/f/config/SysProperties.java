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

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * 应用配置
 *
 * @author liuf
 * @date 2021/12/15 17:04
 */
@Data
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "f")
public class SysProperties {

    /**
     * workerId
     */
    private Integer workerId;

    /**
     * 认证授权配置
     */
    private AuthConfig auth;

    /**
     * 认证授权配置
     */
    private LogConfig log;

    @Data
    public static class AuthConfig {

        /**
         * token 有效时间s
         */
        private Integer tokenExpire;

        /**
         * 不进行权限过滤的路径
         */
        private Set<String> excludePerms;

        /**
         * 需要解密的路径
         */
        private Set<String> aesUris;
    }

    @Data
    public static class LogConfig {

        /**
         * 不记录参数
         */
        private Set<String> excludeParams;

        /**
         * 不记录路径
         */
        private Set<String> excludeUris;
    }
}
