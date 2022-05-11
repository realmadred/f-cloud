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
 * 小区client
 * @author liuf
 * @date 2022/5/11 19:56
 */
@FeignClient(value = "sell", path = "/sellCommunity", fallback = SellCommunityClient.Fallback.class)
public interface SellCommunityClient {

    /**
     * 测试分布式事务
     */
    @PostMapping("/test")
    void test();

    @Component
    class Fallback implements SellCommunityClient {

        @Override
        public void test() {
            System.out.println("test Fallback");
        }

    }
}
