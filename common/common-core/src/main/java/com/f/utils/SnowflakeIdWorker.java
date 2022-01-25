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

package com.f.utils;

import com.baomidou.mybatisplus.core.toolkit.SystemClock;

import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Random;

/**
 * https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Number/MAX_SAFE_INTEGER#%E6%8F%8F%E8%BF%B0
 * MAX_SAFE_INTEGER 是一个值为 9007199254740991的常量。
 * 因为Javascript的数字存储使用了IEEE 754中规定的双精度浮点数数据类型，
 * 而这一数据类型能够安全存储 -(2^53 - 1) 到 2^53 - 1 之间的数值（包含边界值）
 * <p>
 * 41 + 6 + 6 = 53
 * id 生成工具
 *
 * @author liuf
 * @date 2021年12月7日
 */
public class SnowflakeIdWorker {

    /**
     * 开始时间(2021-12-01)
     */
    private static final long EPOCH = 1638921600000L;

    /**
     * workerId
     */
    private static final int WORKER_ID_BITS = 6;

    /**
     * sequence
     */
    private static final int SEQUENCE_BITS = 6;

    /**
     * machine id, the result is 63
     */
    private static final int MAX_WORKER_ID = ~(-1 << WORKER_ID_BITS);

    /**
     * machine id, the result is 63
     */
    private static final int MAX_SEQUENCE = ~(-1 << SEQUENCE_BITS);

    /**
     * workerId
     */
    private long workerId;

    /**
     * sequence
     */
    private int sequence = 0;

    /**
     * timestamp
     */
    private long lastTimestamp;

    /**
     * 指定workerId
     *
     * @param workerId 0 - MAX_WORKER_ID
     */
    public SnowflakeIdWorker(final Integer workerId) {
        this.lastTimestamp = getNewestTimestamp();
        initWorkerId(workerId);
    }


    /**
     * 初始化 workerId
     *
     * @param workerId 为null 自动生成一个
     */
    private void initWorkerId(Integer workerId) {
        if (workerId == null) {
            workerId = generateWorkerId();
        }
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException("workerId大于0~" + MAX_WORKER_ID);
        }
        this.workerId = workerId << (SEQUENCE_BITS);
    }

    /**
     * 获取id
     *
     * @return id
     */
    public synchronized long nextId() {
        waitTime();
        final int seq = getSeq();
        return lastTimestamp << (SEQUENCE_BITS + WORKER_ID_BITS) | workerId | seq;
    }

    /**
     * 获取序列
     * 如果大于最大序列，等待下一个时间
     *
     * @return 序列
     */
    private int getSeq() {
        if (++sequence > MAX_SEQUENCE) {
            waitNextTime();
            sequence = 0;
        }
        return sequence;
    }

    /**
     * 等待下一个时间
     * 当前序列已到最大值
     */
    private void waitNextTime() {
        long newest = getNewestTimestamp();
        while (lastTimestamp >= newest) {
            try {
                Thread.sleep(Math.max(1, lastTimestamp - newest));
                newest = getNewestTimestamp();
            } catch (InterruptedException ignore) {
                // don't care
            }
        }
        lastTimestamp = newest;
    }

    /**
     * 处理时间回拨
     * 最后的时间大于当前时间
     */
    private void waitTime() {
        long newest = getNewestTimestamp();
        if (lastTimestamp == newest) {
            return;
        }
        while (lastTimestamp > newest) {
            try {
                Thread.sleep(lastTimestamp - newest);
                newest = getNewestTimestamp();
            } catch (InterruptedException ignore) {
            }
        }
        lastTimestamp = newest;
    }

    /**
     * 获取时间
     *
     * @return 时间差
     */
    private long getNewestTimestamp() {
        return SystemClock.now() - EPOCH;
    }

    /**
     * 生成 workerId
     *
     * @return workerId
     */
    private int generateWorkerId() {
        try {
            return generateWorkerIdBaseOnMac();
        } catch (Exception e) {
            return generateRandomWorkerId();
        }
    }

    /**
     * 使用 MAC 生成 workerId
     *
     * @return workerId
     * @throws Exception when there is no available mac found
     */
    private int generateWorkerIdBaseOnMac() throws Exception {
        Enumeration<NetworkInterface> all = NetworkInterface.getNetworkInterfaces();
        while (all.hasMoreElements()) {
            NetworkInterface networkInterface = all.nextElement();
            boolean isLoop = networkInterface.isLoopback();
            boolean isVirtual = networkInterface.isVirtual();
            if (isLoop || isVirtual) {
                continue;
            }
            byte[] mac = networkInterface.getHardwareAddress();
            if (mac == null || mac.length < 6) {
                continue;
            }
            return ((mac[4] & 0B11) << 4) | (mac[5] & 0xF);
        }
        throw new RuntimeException("no available mac found");
    }

    /**
     * 随机生成 workerId
     *
     * @return workerId
     */
    private int generateRandomWorkerId() {
        return new Random().nextInt(MAX_WORKER_ID + 1);
    }
}
