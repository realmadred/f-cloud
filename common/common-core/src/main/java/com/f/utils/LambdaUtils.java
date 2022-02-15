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

import java.util.Optional;
import java.util.function.Function;

/**
 * Lambda工具类
 *
 * @author liuf
 * @date 2021/12/16 10:36
 */
public final class LambdaUtils {

    /**
     * 包装 Optional orElse
     *
     * @param obj    对象
     * @param mapper 转换方法
     * @param other  默认值
     * @param <T>    返回值
     * @param <R>    对象类型
     * @return T 类型值
     */
    public static <T, R> T getOrElse(R obj, Function<R, T> mapper, T other) {
        return Optional.ofNullable(obj).map(mapper).orElse(other);
    }


    private LambdaUtils() {

    }
}
