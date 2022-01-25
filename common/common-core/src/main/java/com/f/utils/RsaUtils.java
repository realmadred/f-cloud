/*
  Copyright [2021] [liufeng]
  <p>
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  <p>
  http://www.apache.org/licenses/LICENSE-2.0
  <p>
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */

package com.f.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * rsa 工具类，公钥私钥生成，加解密
 *
 * @author lifueng
 * @date 2022年1月17日
 **/
public class RsaUtils {

    private static final String RSA_ALGORITHM = "RSA";

    /**
     * 公钥解密
     *
     * @param publicKeyText 公钥
     * @param text          待解密的信息
     * @return 明文
     */
    @SneakyThrows
    public static String decryptByPublicKey(String publicKeyText, String text) {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyText));
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] result = doLongerCipherFinal(Cipher.DECRYPT_MODE, cipher, Base64.getDecoder().decode(text));
        return new String(result);
    }

    /**
     * 私钥加密
     *
     * @param privateKeyText 私钥
     * @param text           待加密的信息
     * @return 密文
     */
    @SneakyThrows
    public static String encryptByPrivateKey(String privateKeyText, String text) {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyText));
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] result = doLongerCipherFinal(Cipher.ENCRYPT_MODE, cipher, text.getBytes());
        return Base64.getEncoder().encodeToString(result);
    }

    /**
     * 私钥解密
     *
     * @param privateKeyText 私钥
     * @param text           待解密的文本
     * @return 明文
     */
    @SneakyThrows
    public static String decryptByPrivateKey(String privateKeyText, String text) {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyText));
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] result = doLongerCipherFinal(Cipher.DECRYPT_MODE, cipher, Base64.getDecoder().decode(text));
        return new String(result);
    }

    /**
     * 公钥加密
     *
     * @param publicKeyText 公钥
     * @param text          待加密的文本
     * @return 密文
     */
    @SneakyThrows
    public static String encryptByPublicKey(String publicKeyText, String text) {
        X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyText));
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(encodedKeySpec);
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] result = doLongerCipherFinal(Cipher.ENCRYPT_MODE, cipher, text.getBytes());
        return Base64.getEncoder().encodeToString(result);
    }

    @SneakyThrows
    private static byte[] doLongerCipherFinal(int opMode, Cipher cipher, byte[] source) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            if (opMode == Cipher.DECRYPT_MODE) {
                out.write(cipher.doFinal(source));
            } else {
                int offset = 0;
                int totalSize = source.length;
                while (totalSize - offset > 0) {
                    int size = Math.min(cipher.getOutputSize(0) - 11, totalSize - offset);
                    out.write(cipher.doFinal(source, offset, size));
                    offset += size;
                }
            }
            return out.toByteArray();
        }
    }

    /**
     * 构建RSA密钥对
     *
     * @return 密钥对
     */
    @SneakyThrows
    public static RsaKeyPair generateKeyPair() {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        String publicKeyString = Base64.getEncoder().encodeToString(rsaPublicKey.getEncoded());
        String privateKeyString = Base64.getEncoder().encodeToString(rsaPrivateKey.getEncoded());
        return new RsaKeyPair(publicKeyString, privateKeyString);
    }


    /**
     * RSA密钥对对象
     */
    @Getter
    @RequiredArgsConstructor
    public static class RsaKeyPair {

        private final String publicKey;
        private final String privateKey;

    }
}
