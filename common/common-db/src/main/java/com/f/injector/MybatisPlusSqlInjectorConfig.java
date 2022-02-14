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

package com.f.injector;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义 SqlInjector
 *
 * @author liuf
 * @date 2022年2月14日
 */
@Configuration
public class MybatisPlusSqlInjectorConfig {

    /**
     * 自定义 SqlInjector
     * 里面包含自定义的全局方法
     * @return sql inject
     */
    @Bean
    public MySqlInjector mySqlInjector() {
        return new MySqlInjector();
    }
}