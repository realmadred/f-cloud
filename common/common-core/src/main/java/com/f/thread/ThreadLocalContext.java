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

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal
 *
 * @author liufeng
 * @date 2022年1月19日
 */
@Slf4j
public class ThreadLocalContext {

    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = ThreadLocal.withInitial(HashMap::new);
    private static final ThreadLocal<Map<String, Object>> INHERITABLE_THREAD_LOCAL = InheritableThreadLocal.withInitial(HashMap::new);

    /**
     * 添加
     *
     * @param key   key
     * @param value value
     * @return 原来的value
     */
    public static Object put(String key, Object value) {
        return get().put(key, value);
    }

    /**
     * 获取
     *
     * @param key key
     * @return value
     */
    public static Object get(String key) {
        return get().get(key);
    }

    /**
     * 删除key
     *
     * @param key key
     * @return 对应的值
     */
    public static Object remove(String key) {
        final Map<String, Object> map = get();
        final Object remove = map.remove(key);
        if (map.isEmpty()) {
            remove();
        }
        return remove;
    }

    /**
     * 清理
     */
    public static void remove() {
        THREAD_LOCAL.remove();
        log.info("threadLocal remove");
    }

    /**
     * 获取map
     *
     * @return map
     */
    public static Map<String, Object> get() {
        return THREAD_LOCAL.get();
    }

    /**
     * 添加
     *
     * @param key   key
     * @param value value
     * @return 原来的value
     */
    public static Object putInheritable(String key, Object value) {
        return getInheritable().put(key, value);
    }

    /**
     * 获取
     *
     * @param key key
     * @return value
     */
    public static Object getInheritable(String key) {
        return getInheritable().get(key);
    }

    /**
     * 删除key
     *
     * @param key key
     * @return 对应的值
     */
    public static Object removeInheritable(String key) {
        final Map<String, Object> map = getInheritable();
        final Object remove = map.remove(key);
        if (map.isEmpty()) {
            removeInheritable();
        }
        return remove;
    }

    /**
     * 清理
     */
    public static void removeInheritable() {
        INHERITABLE_THREAD_LOCAL.remove();
        log.info("removeInheritable");
    }

    /**
     * 获取map
     *
     * @return map
     */
    public static Map<String, Object> getInheritable() {
        return INHERITABLE_THREAD_LOCAL.get();
    }
}