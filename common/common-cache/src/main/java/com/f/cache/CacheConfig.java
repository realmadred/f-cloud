/*
 * Copyright [2021] [liufeng]
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

package com.f.cache;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.alicp.jetcache.autoconfigure.LettuceFactory;
import com.alicp.jetcache.autoconfigure.RedisLettuceAutoConfiguration;
import io.lettuce.core.RedisClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * 缓存配置
 *
 * @author liuf
 * @date 2021/12/7 15:42
 */
@EnableMethodCache(basePackages = "com.f")
@EnableCreateCacheAnnotation
@Configuration
public class CacheConfig {

    @Bean(name = "redisClient")
    @DependsOn(RedisLettuceAutoConfiguration.AUTO_INIT_BEAN_NAME)
    public LettuceFactory redisClient() {
        return new LettuceFactory("remote.default", RedisClient.class);
    }
}
