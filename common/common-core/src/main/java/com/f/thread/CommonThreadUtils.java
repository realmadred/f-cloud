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

package com.f.thread;

import java.util.List;
import java.util.concurrent.*;

/**
 * 通用线程工具类
 *
 * @author liuf
 * @date 2021/12/8 16:57
 */
public final class CommonThreadUtils {

    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(32, 128, 60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(2048), new NamedThreadFactory("f-common"));

    public static void shutdown() {
        EXECUTOR.shutdown();
    }

    public static List<Runnable> shutdownNow() {
        return EXECUTOR.shutdownNow();
    }

    public static boolean isShutdown() {
        return EXECUTOR.isShutdown();
    }

    public static boolean isTerminated() {
        return EXECUTOR.isTerminated();
    }


    public static <T> Future<T> submit(final Callable<T> task) {
        return EXECUTOR.submit(task);
    }

    public static Future<?> submit(final Runnable task) {
        return EXECUTOR.submit(task);
    }

    public static void execute(final Runnable command) {
        EXECUTOR.execute(command);
    }

    private CommonThreadUtils() {
    }
}
