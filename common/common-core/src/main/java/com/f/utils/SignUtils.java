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
package com.f.utils;

import com.f.base.SignDto;
import com.f.constant.Constant;
import com.f.enums.ResultEnum;
import com.f.exception.BaseException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 签名工具类
 *
 * @author liufeng
 * @date 2022年1月17日
 */
public final class SignUtils {

    /**
     * 时间相差2分钟
     */
    private static final int MINUTES_TIME_OUT = 2;

    /**
     * 签名
     *
     * @param appId  appId
     * @param secret 秘钥
     * @param data   json数据
     * @return 签名dto
     * @date 2022年1月17日
     */
    public static SignDto buildSign(String appId, String secret, String data) {
        if (appId == null || secret == null || data == null) {
            throw BaseException.of(ResultEnum.SIGN_ERROR);
        }

        final SignDto signDto = new SignDto();
        signDto.setAppId(appId);
        signDto.setNonce(UUID.randomUUID().toString());
        signDto.setTimestamp(System.currentTimeMillis());
        signDto.setData(data);
        signDto.setSign(sign(signDto, secret));
        return signDto;
    }

    /**
     * 验证签名
     *
     * @param signDto dto
     * @param secret  秘钥
     * @return 是否通过
     * @date 2022年1月17日
     */
    public static boolean validate(SignDto signDto, String secret) {
        if (signDto == null) {
            return false;
        }

        final long timestamp = signDto.getTimestamp();
        if (Math.abs(System.currentTimeMillis() - timestamp) > TimeUnit.MINUTES.toMillis(MINUTES_TIME_OUT)) {
            return false;
        }

        return signDto.getSign().equals(sign(signDto, secret));
    }

    /**
     * 签名
     * appId + data + nonce + secret + timestamp
     * md5后转base64字符串
     *
     * @param secret  秘钥
     * @param signDto 签名dto
     * @return 签名
     * @date 2022年1月17日
     */
    private static String sign(SignDto signDto, String secret) {
        if (signDto == null || secret == null) {
            throw BaseException.of(ResultEnum.SIGN_ERROR);
        }
        // 生成签名
        return md5(String.join(Constant.EMPTY,
                signDto.getAppId(),
                signDto.getData(),
                signDto.getNonce(),
                secret,
                signDto.getTimestamp() + Constant.EMPTY));
    }

    /**
     * MD5 后转 base64
     *
     * @param data 原始数据
     * @return MD5
     */
    public static String md5(final String data) {
        if (data == null) {
            return null;
        }
        try {
            return Base64.getEncoder().encodeToString(MessageDigest.getInstance("MD5")
                    .digest(data.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private SignUtils() {
    }

}
