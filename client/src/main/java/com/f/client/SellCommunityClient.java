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

import com.f.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 小区client
 *
 * @author liuf
 * @date 2022/5/11 19:56
 */
@FeignClient(value = "sell", path = "/sellCommunity", fallbackFactory = SellCommunityClient.Fallback.class)
public interface SellCommunityClient {

    /**
     * 测试分布式事务
     */
    @PostMapping("/test")
    Result<Void> test();

    @Component
    @Slf4j
    class Fallback implements FallbackFactory<SellCommunityClient> {

        @Override
        public SellCommunityClient create(Throwable cause) {
            log.error("异常原因:{}", cause.getMessage(), cause);
            return () -> {
                log.info("------test Fallback------");
                return Result.fail("Fallback");
            };
        }

    }
}
