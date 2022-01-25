package com.f.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.Set;

/**
 * 网关配置
 *
 * @author liuf
 * @date 2022/1/25 16:02
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = "f.gateway")
public class GatewayProperties {

    private Set<String> notAuthUris;
}
