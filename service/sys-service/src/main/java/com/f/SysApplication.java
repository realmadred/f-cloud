package com.f;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 系统服务启动
 *
 * @author liuf
 * @date 2022/1/26 15:25
 */
@SpringBootApplication(scanBasePackages = "com.f")
@MapperScan(basePackages = {"com.f.mapper.*"})
@EnableDiscoveryClient
public class SysApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysApplication.class, args);
    }
}
