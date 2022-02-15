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

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 生成id工具
 *
 * @author liuf
 * @date 2021/12/9 14:57
 */
@Slf4j
public final class IdUtils {

    /**
     * 雪花算法 idWorker
     */
    private static volatile SnowflakeIdWorker idWorker;

    /**
     * 指定位置的byte值数组
     */
    public static final byte[] DIGITS = {
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z'
    };

    /**
     * 雪花算法初始化
     *
     * @param workerId workerId
     */
    public static void init(final Integer workerId) {
        log.info("init workerId:{}", workerId);
        if (idWorker == null) {
            synchronized (IdUtils.class) {
                if (idWorker == null) {
                    idWorker = new SnowflakeIdWorker(workerId);
                    log.info("init workerId success:{}", workerId);
                }
            }
        }
    }

    /**
     * 获取雪花算法生成的id
     *
     * @return id long
     */
    public static long id() {
        if (idWorker == null) {
            init(null);
            return id();
        } else {
            return idWorker.nextId();
        }
    }

    /**
     * 生成jdk9 uuid
     *
     * @return UUID
     */
    public static String uuid() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        long l = random.nextLong();
        long m = random.nextLong();
        byte[] bytes = new byte[32];
        shuffle(l, bytes, 20, 12);
        shuffle(l >>> 48, bytes, 16, 4);
        shuffle(m, bytes, 12, 4);
        shuffle(m >>> 16, bytes, 8, 4);
        shuffle(m >>> 32, bytes, 0, 8);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    private static void shuffle(long val, byte[] buf, int offset, int len) {
        int charPos = offset + len;
        int radix = 1 << 4;
        int mask = radix - 1;
        do {
            buf[--charPos] = DIGITS[((int) val) & mask];
            val >>>= 4;
        } while (charPos > offset);
    }

    private IdUtils() {
    }

}
