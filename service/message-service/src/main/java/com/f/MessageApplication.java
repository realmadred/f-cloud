package com.f;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 消息服务启动 @EnableDiscoveryClient 默认会启用
 *
 * @author liuf
 * @date 2022/2/14 15:25
 */
@SpringBootApplication(scanBasePackages = "com.f", exclude = {DataSourceAutoConfiguration.class})
public class MessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class, args);
    }
}
