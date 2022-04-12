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
package com.f.service.impl;

import com.f.dto.pay.PayDto;
import com.f.dto.pay.RefundDto;
import com.f.dto.pay.WxPaySignDto;
import com.f.exception.PayException;
import com.f.properties.WxPayProperties;
import com.f.service.WxPayService;
import com.f.utils.Json;
import com.fasterxml.jackson.databind.JsonNode;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.cert.CertificatesManager;
import com.wechat.pay.contrib.apache.httpclient.util.AesUtil;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import lombok.SneakyThrows;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * 微信支付服务实现
 *
 * @author liuf
 * @date 2022/04/12
 */
public class WxPayServiceImpl implements WxPayService {

    private static final Logger LOGGER = LogManager.getLogger(WxPayServiceImpl.class);

    public static final String HOST = "https://api.mch.weixin.qq.com";

    private final WxPayProperties wxPayProperties;

    private CloseableHttpClient httpClient;

    private Verifier verifier;

    private PrivateKey privateKey;

    public WxPayServiceImpl(WxPayProperties wxPayProperties) {
        this.wxPayProperties = wxPayProperties;
    }

    @PostConstruct
    public void init() {
        LOGGER.info("wx pay init ...");
        // 加载商户私钥（privateKey：私钥字符串）
        try (InputStream inputStream = new FileInputStream(wxPayProperties.getPrivateKeyPath())) {
            privateKey = PemUtil.loadPrivateKey(inputStream);
            // 获取证书管理器实例
            CertificatesManager certificatesManager = CertificatesManager.getInstance();
            // 向证书管理器增加需要自动更新平台证书的商户信息
            certificatesManager.putMerchant(wxPayProperties.getMchId(), new WechatPay2Credentials(wxPayProperties.getMchId(),
                    new PrivateKeySigner(wxPayProperties.getMchSerialNo(), privateKey)), wxPayProperties.getApiV3Key().getBytes(StandardCharsets.UTF_8));
            // 从证书管理器中获取verifier
            verifier = certificatesManager.getVerifier(wxPayProperties.getMchId());
            // 初始化httpClient
            httpClient = WechatPayHttpClientBuilder.create()
                    .withMerchant(wxPayProperties.getMchId(), wxPayProperties.getMchSerialNo(), privateKey)
                    .withValidator(new WechatPay2Validator(verifier)).build();
            LOGGER.info("wx pay init success");
        } catch (Exception e) {
            LOGGER.error("微信支付初始化失败", e);
            throw new PayException("wxpay init error");
        }
    }

    @PreDestroy
    public void destroy() throws IOException {
        httpClient.close();
    }

    @Override
    public String nativePay(PayDto payDTO) {
        LOGGER.info("nativePay:{}", payDTO);
        // 封装请求参数
        final String result = post(HOST + "/v3/pay/transactions/native", buildPrepayJson(payDTO));
        LOGGER.info("二维码支付请求结果:{}", result);
        return getJsonStr(result, "code_url");
    }

    @Nullable
    private String getJsonStr(String json, String key) {
        return Objects.toString(Json.parse(json).get(key), null);
    }

    /**
     * 生成预支付订单json
     */
    private String buildPrepayJson(PayDto payDTO) {
        final Map<String, Object> objectMap = new HashMap<>(16);
        objectMap.put("mchid", wxPayProperties.getMchId());
        objectMap.put("appid", payDTO.getAppId());
        objectMap.put("description", payDTO.getDescription());
        objectMap.put("notify_url", payDTO.getNotifyUrl());
        objectMap.put("out_trade_no", payDTO.getOutTradeNo());
        objectMap.put("attach", payDTO.getAttach());
        objectMap.put("time_expire", payDTO.getTimeExpire());

        // 支付金额
        final Map<String, Object> amount = new HashMap<>(8);
        amount.put("total", getWxAmount(payDTO.getAmount()));
        amount.put("currency", payDTO.getCurrency());
        objectMap.put("amount", amount);
        Optional.ofNullable(payDTO.getOtherParams()).ifPresent(objectMap::putAll);
        return Json.json(objectMap);
    }

    @Override
    public WxPaySignDto appPay(PayDto payDTO) {
        LOGGER.info("appPay:{}", payDTO);
        // 封装请求参数
        final String result = post(HOST + "/v3/pay/transactions/app", buildPrepayJson(payDTO));
        LOGGER.info("app支付请求结果:{}", result);
        final String prepayId = getJsonStr(result, "prepay_id");
        // 生成签名方便app拉起支付
        final WxPaySignDto wxPaySignDTO = new WxPaySignDto()
                .setAppId(payDTO.getAppId())
                .setNonce(UUID.randomUUID().toString())
                .setPartnerId(wxPayProperties.getMchId())
                .setPrepayId(prepayId)
                .setTimestamp(System.currentTimeMillis() / 1000);
        // 签名
        sign(wxPaySignDTO);
        return wxPaySignDTO;
    }

    /**
     * 签名，使用字段appId、timeStamp、nonce、prepayId
     */
    private void sign(WxPaySignDto wxPaySignDTO) {
        try {
            Signature sign = Signature.getInstance("SHA256withRSA");
            sign.initSign(privateKey);
            sign.update(buildSignMessage(wxPaySignDTO.getAppId(),
                    wxPaySignDTO.getTimestamp().toString(),
                    wxPaySignDTO.getNonce(),
                    wxPaySignDTO.getPrepayId()).getBytes(StandardCharsets.UTF_8));
            wxPaySignDTO.setSign(Base64.getEncoder().encodeToString(sign.sign()));
        } catch (NoSuchAlgorithmException var3) {
            throw new RuntimeException("当前Java环境不支持SHA256withRSA", var3);
        } catch (SignatureException var4) {
            throw new RuntimeException("签名计算失败", var4);
        } catch (InvalidKeyException var5) {
            throw new RuntimeException("无效的私钥", var5);
        }
    }

    @Override
    public String queryOrderByTransactionId(String transactionId) {
        return get(String.format("%s/v3/pay/transactions/id/%s?mchid=%s", HOST, transactionId, wxPayProperties.getMchId()));
    }

    @Override
    public String queryOrderByOutTradeNo(String outTradeNo) {
        return get(String.format("%s/v3/pay/transactions/out-trade-no/%s?mchid=%s", HOST, outTradeNo, wxPayProperties.getMchId()));
    }

    @Override
    public void closeOrder(String outTradeNo) {
        LOGGER.info("closeOrder:{}", outTradeNo);
        final Map<String, Object> jsonObject = new HashMap<>(8);
        jsonObject.put("mchid", wxPayProperties.getMchId());
        post(String.format("%s/v3/pay/transactions/out-trade-no/%s/close", HOST, outTradeNo), Json.json(jsonObject));
    }

    @Override
    public String refund(RefundDto refundDTO) {
        LOGGER.info("refund:{}", refundDTO);
        // 封装请求参数
        final Map<String, Object> jsonObject = new HashMap<>(8);
        Optional.ofNullable(refundDTO.getTransactionId()).ifPresent(t -> jsonObject.put("transaction_id", t));
        Optional.ofNullable(refundDTO.getOutTradeNo()).ifPresent(o -> jsonObject.put("out_trade_no", o));
        jsonObject.put("notify_url", refundDTO.getNotifyUrl());
        jsonObject.put("out_refund_no", refundDTO.getOutRefundNo());
        // 金额
        final Map<String, Object> amount = new HashMap<>(8);
        amount.put("total", getWxAmount(refundDTO.getTotal()));
        amount.put("currency", refundDTO.getCurrency());
        amount.put("refund", getWxAmount(refundDTO.getRefund()));
        jsonObject.put("amount", amount);
        Optional.ofNullable(refundDTO.getOtherParams()).ifPresent(jsonObject::putAll);
        final String result = post(HOST + "/v3/refund/domestic/refunds", Json.json(jsonObject));
        LOGGER.info("微信支付退款请求结果:{}", result);
        return result;
    }

    @Override
    public String queryRefundByOutRefundNo(String outRefundNo) {
        return get(String.format("%s/v3/refund/domestic/refunds/%s", HOST, outRefundNo));
    }

    @Override
    @SneakyThrows
    public String verifyNotify(String serialNo, String body, String signature, String nonce, String timestamp) {
        final boolean verify = verifier.verify(serialNo, buildSignMessage(timestamp, nonce, body).getBytes(StandardCharsets.UTF_8), signature);
        if (verify) {
            final JsonNode jsonObject = Json.parseTree(body);
            JsonNode resource = jsonObject.get("resource");
            String cipherText = resource.get("ciphertext").asText();
            String nonceStr = resource.get("nonce").asText();
            String associatedData = resource.get("associated_data").asText();
            AesUtil aesUtil = new AesUtil(wxPayProperties.getApiV3Key().getBytes(StandardCharsets.UTF_8));
            // 密文解密
            return aesUtil.decryptToString(
                    associatedData.getBytes(StandardCharsets.UTF_8),
                    nonceStr.getBytes(StandardCharsets.UTF_8),
                    cipherText);
        }
        return null;
    }

    /**
     * 构造签名串
     */
    private String buildSignMessage(String... str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : str) {
            stringBuilder.append(s).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * post 请求
     *
     * @param url  url
     * @param json 请求json
     * @return 返回body
     */
    private String post(String url, String json) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Content-type", "application/json; charset=utf-8");
        httpPost.setEntity(new StringEntity(json, StandardCharsets.UTF_8));
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            final HttpEntity entity = response.getEntity();
            LOGGER.info("微信支付发起post请求状态:{}", response.getStatusLine());
            return entity == null ? null : EntityUtils.toString(entity);
        } catch (Exception e) {
            LOGGER.error("微信支付发起post请求失败", e);
            throw new PayException("wxpay post error");
        }
    }

    /**
     * get 请求
     *
     * @param url url
     * @return 返回body
     */
    private String get(String url) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Accept", "application/json");
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            LOGGER.error("微信支付发起get请求失败", e);
            throw new PayException("wxpay get error");
        }
    }

    /**
     * 获取微信金额
     * 微信单位是分
     *
     * @param amount 元
     * @return 分
     */
    public int getWxAmount(Double amount) {
        return BigDecimal.valueOf(amount * 100).setScale(0, RoundingMode.HALF_UP).intValue();
    }
}
