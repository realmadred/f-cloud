/**
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

package com.f.log;

import com.baomidou.mybatisplus.core.toolkit.SystemClock;
import com.f.entity.sys.SysLog;
import com.f.mapper.sys.SysLogMapper;
import com.f.thread.NamedThreadFactory;
import com.f.utils.ApplicationContextUtils;
import com.f.utils.IdUtils;
import com.lmax.disruptor.dsl.Disruptor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 日志事件循环
 *
 * @author liuf
 * @date 2022/1/14 14:46
 */
@Slf4j
public class LogDisruptor {

    private static final int BUFFER_SIZE = 1024;
    private static final int BATCH_SIZE = 100;
    private static final long TIME_OUT = TimeUnit.SECONDS.toMillis(5);
    private static final SysLogMapper SYS_LOG_MAPPER = ApplicationContextUtils.getBean(SysLogMapper.class);

    private static final Disruptor<LogEvent> DISRUPTOR =
            new Disruptor<>(LogEvent::new, BUFFER_SIZE, new NamedThreadFactory("sysLog"));

    private static final List<SysLog> LIST = new ArrayList<>(BATCH_SIZE);
    private static long saveTime = SystemClock.now();

    static {
        DISRUPTOR.handleEventsWith((event, sequence, endOfBatch) -> {
            LIST.add(event.getSysLog().setId(IdUtils.id()));
            if (LIST.size() >= BATCH_SIZE || SystemClock.now() - saveTime > TIME_OUT) {
                log.info("开始保存日志:{}", LIST.size());
                saveTime = SystemClock.now();
                SYS_LOG_MAPPER.mysqlInsertBatch(LIST);
                LIST.clear();
            }
        });
        DISRUPTOR.start();
    }

    /**
     * 发布事件
     *
     * @param sysLog 日志
     */
    public static void publishEvent(SysLog sysLog) {
        DISRUPTOR.publishEvent((logEvent, sequence) -> logEvent.setSysLog(sysLog));
    }

    /**
     * 停止事件
     */
    public static synchronized void stop() {
        LIST.clear();
        DISRUPTOR.shutdown();
    }

}
