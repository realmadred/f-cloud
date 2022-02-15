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
package com.f.cache;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * 缓存方法
 *
 * @author liuf
 * @date 2021/12/7 15:32
 */
@Component
@Accessors(fluent = true)
@Getter
public class CacheTemplate {

    private final RedisClient redisClient;

    private final StatefulRedisConnection<String, String> connect;

    private final StatefulRedisConnection<String, Object> objectConnection;

    private final RedisCommands<String, String> sync;

    private final RedisAsyncCommands<String, String> async;

    private final RedisReactiveCommands<String, String> reactive;

    private final RedisCommands<String, Object> syncObject;

    private final RedisAsyncCommands<String, Object> asyncObject;

    private final RedisReactiveCommands<String, Object> reactiveObject;

    public CacheTemplate(final RedisClient redisClient) {
        this.redisClient = redisClient;
        this.connect = redisClient.connect();
        this.objectConnection = redisClient.connect(KryoCodec.INSTANCE);
        this.sync = connect.sync();
        this.async = connect.async();
        this.reactive = connect.reactive();
        this.syncObject = objectConnection.sync();
        this.asyncObject = objectConnection.async();
        this.reactiveObject = objectConnection.reactive();
    }

    @CreateCache(name = "common", expire = 30, cacheType = CacheType.REMOTE)
    private Cache<String, Object> commonCache;

    @CreateCache(name = "local", expire = 3, localLimit = 8096, cacheType = CacheType.LOCAL)
    private Cache<String, Object> localCache;

    @PreDestroy
    public void destroy() {
        connect.close();
        objectConnection.close();
        redisClient.shutdown();
    }
}
