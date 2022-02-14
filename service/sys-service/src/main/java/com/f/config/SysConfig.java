package com.f.config;

import com.f.security.AuthInterceptor;
import com.f.utils.ApplicationContextUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 系统配置
 *
 * @author liuf
 * @date 2022/2/14 16:00
 */
@Configuration
public class SysConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ApplicationContextUtils.getBean(AuthInterceptor.class)).order(10);
    }
}
