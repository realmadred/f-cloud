package com.f.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * 网关配置
 *
 * @author liuf
 * @date 2022/1/25 16:02
 */
@Data
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "f.gateway")
public class MyGatewayProperties {

    private Set<String> notAuthUris;
    private String name;
    /**
     * 内部访问id
     */
    private String innerId;
}
