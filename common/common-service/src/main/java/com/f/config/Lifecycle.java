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

package com.f.config;

import com.f.thread.CommonThreadUtils;
import com.f.utils.IdUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * bean 生命周期控制
 *
 * @author liuf
 * @date 2021/12/9 15:57
 */
@Component
@Slf4j
public final class Lifecycle {

    @Value("${f.workerId}")
    private Integer workerId;

    @PostConstruct
    public void init() {
        log.info("f-cloud init");
        IdUtils.init(workerId);
    }

    @PreDestroy
    public void destroy() {
        log.info("f-cloud destroy");
        CommonThreadUtils.shutdown();
    }
}
