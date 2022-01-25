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

package com.f.utils;

import lombok.SneakyThrows;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * aes工具类
 *
 * @author liufeng
 * @date 2022年1月18日
 */
public final class AesUtils {

    /**
     * AES key 128/192/256
     */
    private static final int KEY_LEN = 128;

    /**
     * AES
     */
    private static final String AES = "AES";

    /**
     * 此处使用AES-128-CBC加密模式，key需要为16位。
     */
    private static final String IV_PARAMETER = "24dff9ew978ac1ui";

    /**
     * 算法/模式/补码方式
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";


    /**
     * 加密
     *
     * @param data   原始数据
     * @param secret 秘钥
     * @return 密文
     */
    @SneakyThrows
    public static String encrypt(String data, String secret) {
        Cipher cipher = getCipher(secret, Cipher.ENCRYPT_MODE);
        byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    /**
     * 解密
     *
     * @param data   密文
     * @param secret 秘钥
     * @return 明文
     */
    @SneakyThrows
    public static String decrypt(String data, String secret) {
        Cipher cipher = getCipher(secret, Cipher.DECRYPT_MODE);
        return new String(cipher.doFinal(Base64.getDecoder().decode(data)), StandardCharsets.UTF_8);
    }

    @SneakyThrows
    private static Cipher getCipher(String secret, int mode) {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
        keyGenerator.init(KEY_LEN);
        SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), AES);
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(StandardCharsets.UTF_8));
        cipher.init(mode, keySpec, iv);
        return cipher;
    }

    private AesUtils() {
    }

}
