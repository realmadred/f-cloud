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
package com.f.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 消息client
 * path 为公共路径
 * @author liuf
 * @date 2022/2/15 17:56
 */
@FeignClient(value = "message", path = "/message", fallback = MessageClient.Fallback.class)
public interface MessageClient {

    /**
     * 发送邮件
     */
    @PostMapping("/sendEmail")
    void sendEmail();

    /**
     * 发送短信
     */
    @PostMapping("/sendSms")
    void sendSms();

    @Component
    class Fallback implements MessageClient {

        @Override
        public void sendEmail() {
            System.out.println("sendEmail Fallback");
        }

        @Override
        public void sendSms() {
            System.out.println("sendSms Fallback");
        }
    }
}
