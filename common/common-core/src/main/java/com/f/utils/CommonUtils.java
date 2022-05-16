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

import com.f.thread.CommonThreadUtils;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

/**
 * 通用工具类
 *
 * @author liuf
 * @date 2021/12/6 16:53
 */
@Slf4j
public final class CommonUtils {

    /**
     * 多线程并发执行
     *
     * @param concurrency  并发量
     * @param runnable     并发执行业务
     * @param afterExecute 最后执行业务
     */
    @SneakyThrows
    public static void concurrencyExecution(final int concurrency, final @NonNull Runnable runnable, final Runnable afterExecute) {
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch all = new CountDownLatch(concurrency);
        for (int i = 0; i < concurrency; i++) {
            CommonThreadUtils.execute(() -> {
                try {
                    start.await();
                } catch (InterruptedException e) {
                    log.error("latchExecution", e);
                }
                runnable.run();
                all.countDown();
            });
        }
        start.countDown();
        all.await();
        if (afterExecute != null) {
            afterExecute.run();
        }
    }

    /**
     * 内容编码
     *
     * @param str 内容
     * @return 编码后的内容
     */
    public static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    /**
     * 内容解码
     *
     * @param str 内容
     * @return 解码后的内容
     */
    public static String urlDecode(String str) {
        try {
            return URLDecoder.decode(str, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    private CommonUtils() {

    }
}
