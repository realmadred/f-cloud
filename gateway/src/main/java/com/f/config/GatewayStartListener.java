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
package com.f.config;

import com.alibaba.nacos.client.naming.event.InstancesChangeEvent;
import com.alibaba.nacos.common.notify.Event;
import com.alibaba.nacos.common.notify.NotifyCenter;
import com.alibaba.nacos.common.notify.listener.Subscriber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 项目启动后执行
 *
 * @author liuf
 * @date 2022/3/4 11:34
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class GatewayStartListener implements ApplicationListener<ApplicationReadyEvent> {

    private final ApplicationEventPublisher publisher;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("started:{}", event.getTimeTaken());
        NotifyCenter.registerSubscriber(new Subscriber<InstancesChangeEvent>() {
            @Override
            public void onEvent(InstancesChangeEvent event) {
                log.info("onEvent:{}", event);
                publisher.publishEvent(new RefreshRoutesEvent(event));
            }

            @Override
            public Class<? extends Event> subscribeType() {
                return InstancesChangeEvent.class;
            }
        });
        log.info("started registerSubscriber InstancesChangeEvent");
    }
}
