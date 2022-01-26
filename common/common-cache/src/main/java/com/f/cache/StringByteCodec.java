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

package com.f.cache;

import com.f.constant.Constant;
import com.f.utils.CommonUtils;
import io.lettuce.core.codec.RedisCodec;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * 自定义编码器
 *
 * @author liuf
 * @date 2021年12月7日
 */
public class StringByteCodec implements RedisCodec<String, byte[]> {

    public static final StringByteCodec UTF8 = new StringByteCodec();

    public StringByteCodec() {
    }

    @Override
    public String decodeKey(final ByteBuffer bytes) {
        return Unpooled.wrappedBuffer(bytes).toString(StandardCharsets.UTF_8);
    }

    @Override
    public byte[] decodeValue(final ByteBuffer bytes) {
        return CommonUtils.getBytes(bytes);
    }

    @Override
    public ByteBuffer encodeKey(final String key) {
        return encodeAndAllocateBuffer(key);
    }

    @Override
    public ByteBuffer encodeValue(final byte[] value) {
        return value == null ? ByteBuffer.wrap(Constant.EMPTY_BYTES) : ByteBuffer.wrap(value);
    }

    private ByteBuffer encodeAndAllocateBuffer(final String key) {
        if (key == null) {
            return ByteBuffer.wrap(Constant.EMPTY_BYTES);
        } else {
            ByteBuffer buffer = ByteBuffer.allocate(ByteBufUtil.utf8MaxBytes(key));
            ByteBuf byteBuf = Unpooled.wrappedBuffer(buffer);
            byteBuf.clear();
            this.encode(key, byteBuf);
            buffer.limit(byteBuf.writerIndex());
            return buffer;
        }
    }

    public void encode(final String str, final ByteBuf target) {
        if (str != null) {
            ByteBufUtil.writeUtf8(target, str);
        }
    }
}
