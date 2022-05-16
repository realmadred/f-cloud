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

import com.alicp.jetcache.support.KryoValueDecoder;
import com.alicp.jetcache.support.KryoValueEncoder;
import io.lettuce.core.codec.RedisCodec;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * Kryo 编解码器
 *
 * @author liuf
 * @date 2021年12月7日
 */
public enum KryoCodec implements RedisCodec<String, Object> {

    /**
     * 单例
     */
    INSTANCE;

    /**
     * 空白byte数组
     */
    private static final byte[] EMPTY_BYTES = new byte[0];

    @Override
    public ByteBuffer encodeKey(final String key) {
        return StandardCharsets.UTF_8.encode(key);
    }

    @Override
    public ByteBuffer encodeValue(final Object value) {
        return ByteBuffer.wrap(KryoValueEncoder.INSTANCE.apply(value));
    }

    @Override
    public String decodeKey(final ByteBuffer bytes) {
        return StandardCharsets.UTF_8.decode(bytes).toString();
    }

    @Override
    public Object decodeValue(final ByteBuffer bytes) {
        return KryoValueDecoder.INSTANCE.doApply(getBytes(bytes));
    }

    /**
     * ByteBuffer -> byte[]
     *
     * @param buffer ByteBuffer
     * @return byte[]
     */
    private byte[] getBytes(final ByteBuffer buffer) {
        int remaining = buffer.remaining();
        if (remaining == 0) {
            return EMPTY_BYTES;
        } else {
            byte[] bytes = new byte[remaining];
            buffer.get(bytes);
            return bytes;
        }
    }

}