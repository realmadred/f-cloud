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
package com.f.utils;

import lombok.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring context
 *
 * @author liuf
 * @date 2021/12/6 18:30
 */
@Component
public final class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(final @NonNull ApplicationContext applicationContext) throws BeansException {
        if (ApplicationContextUtils.applicationContext == null) {
            ApplicationContextUtils.applicationContext = applicationContext;
        }
    }

    public static Object getBean(final String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(final String name, final Class<T> clazz) throws BeansException {
        return applicationContext.getBean(name, clazz);
    }

    public static Object getBean(final String name, final Object... args) throws BeansException {
        return applicationContext.getBean(name, args);
    }

    public static <T> T getBean(final Class<T> clazz) throws BeansException {
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBean(final Class<T> clazz, final Object... args) throws BeansException {
        return applicationContext.getBean(clazz, args);
    }

    private ApplicationContextUtils() {
    }
}
