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

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.payment.app.models.AlipayTradeAppPayResponse;
import com.alipay.easysdk.payment.common.Client;
import com.alipay.easysdk.payment.common.models.AlipayTradeCloseResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeQueryResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeRefundResponse;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.f.dto.pay.PagePayDto;
import com.f.dto.pay.PayDto;
import com.f.dto.pay.QueryRefundDto;
import com.f.dto.pay.RefundDto;
import com.f.exception.PayException;
import com.f.properties.AliPayProperties;
import com.f.service.AliPayService;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

/**
 * 支付宝支付服务实现
 *
 * @author liuf
 * @date 2022/04/12
 */
public class AliPayServiceImpl implements AliPayService {

    private static final Logger LOGGER = LogManager.getLogger(AliPayServiceImpl.class);
    private static final String APP_ID = "app_id";

    public final AliPayProperties aliPayProperties;

    public AliPayServiceImpl(AliPayProperties aliPayProperties) {
        this.aliPayProperties = aliPayProperties;
    }

    @PostConstruct
    public void init() {
        LOGGER.info("alipay init ...");
        try {
            // 设置参数（全局只需设置一次）
            Factory.setOptions(getOptions(aliPayProperties));
            LOGGER.info("alipay init success");
        } catch (Exception e) {
            LOGGER.error("支付宝支付初始化失败", e);
            throw new PayException("alipay init error");
        }
    }

    private Config getOptions(AliPayProperties aliPayProperties) throws IOException {
        Config config = new Config();
        config.protocol = "https";
        config.gatewayHost = aliPayProperties.getGatewayHost();
        config.signType = aliPayProperties.getSignType();
        config.appId = aliPayProperties.getAppId();

        // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
        config.merchantPrivateKey = FileUtils.readFileToString(new File(aliPayProperties.getMerchantPrivateKeyPath()), StandardCharsets.UTF_8);

        //注：证书文件路径支持设置为文件系统中的路径或CLASS_PATH中的路径，优先从文件系统中加载，加载失败后会继续尝试从CLASS_PATH中加载
        config.merchantCertPath = aliPayProperties.getMerchantCertPath();
        config.alipayCertPath = aliPayProperties.getAlipayCertPath();
        config.alipayRootCertPath = aliPayProperties.getAlipayRootCertPath();
        return config;
    }

    @Override
    public String prePay(PayDto payDTO) {
        LOGGER.info("prePay:{}", payDTO);
        try {
            final AlipayTradePrecreateResponse tradePrecreateResponse = Factory.Payment.FaceToFace()
                    .batchOptional(payDTO.getOtherParams())
                    .asyncNotify(payDTO.getNotifyUrl())
                    .preCreate(
                            payDTO.getDescription(),
                            payDTO.getOutTradeNo(),
                            getAliAmount(payDTO.getAmount()));
            LOGGER.info("tradePrecreateResponse:{}", tradePrecreateResponse);
            return tradePrecreateResponse.getQrCode();
        } catch (Exception e) {
            LOGGER.error("支付宝预支付失败", e);
            throw new PayException("alipay prePay error");
        }
    }

    @Override
    public String pagePay(PagePayDto pagePayDTO) {
        LOGGER.info("pagePayDTO:{}", pagePayDTO);
        try {
            final AlipayTradePagePayResponse pagePayResponse = Factory.Payment.Page()
                    .asyncNotify(pagePayDTO.getNotifyUrl())
                    .batchOptional(pagePayDTO.getOtherParams())
                    .pay(pagePayDTO.getDescription(),
                            pagePayDTO.getOutTradeNo(),
                            getAliAmount(pagePayDTO.getAmount()),
                            pagePayDTO.getReturnUrl());
            LOGGER.info("pagePayResponse:{}", pagePayResponse.body);
            return pagePayResponse.body;
        } catch (Exception e) {
            LOGGER.error("支付宝预支付失败", e);
            throw new PayException("alipay prePay error");
        }
    }

    @Override
    public String appPay(PayDto payDTO) {
        LOGGER.info("appPay:{}", payDTO);
        try {
            final AlipayTradeAppPayResponse payResponse = Factory.Payment
                    .App().asyncNotify(payDTO.getNotifyUrl())
                    .batchOptional(payDTO.getOtherParams()).pay(
                            payDTO.getDescription(),
                            payDTO.getOutTradeNo(),
                            getAliAmount(payDTO.getAmount()));
            LOGGER.info("payResponse:{}", payResponse.body);
            return payResponse.body;
        } catch (Exception e) {
            LOGGER.error("支付宝app支付失败", e);
            throw new PayException("alipay appPay error");
        }
    }

    @Override
    public String queryOrderByOutTradeNo(String outTradeNo) {
        try {
            final AlipayTradeQueryResponse queryResponse = Factory.Payment.Common().query(outTradeNo);
            LOGGER.info("queryResponse:{}", queryResponse.httpBody);
            return queryResponse.httpBody;
        } catch (Exception e) {
            LOGGER.error("支付宝查询订单失败", e);
            throw new PayException("alipay queryOrderByOutTradeNo error");
        }
    }

    @Override
    public void closeOrderByOutTradeNo(String outTradeNo) {
        LOGGER.info("closeOrderByOutTradeNo:{}", outTradeNo);
        try {
            final AlipayTradeCloseResponse closeResponse = Factory.Payment.Common().close(outTradeNo);
            LOGGER.info("closeResponse:{}", closeResponse.httpBody);
        } catch (Exception e) {
            LOGGER.error("支付宝关闭订单失败", e);
            throw new PayException("alipay closeOrderByOutTradeNo error");
        }
    }

    @Override
    public String refund(RefundDto refundDTO) {
        LOGGER.info("refund:{}", refundDTO);
        try {
            refundDTO.getOtherParams().put("out_request_no", refundDTO.getOutRequestNo());
            final AlipayTradeRefundResponse refundResponse = Factory.Payment.Common()
                    .asyncNotify(refundDTO.getNotifyUrl())
                    .batchOptional(refundDTO.getOtherParams())
                    .refund(refundDTO.getOutTradeNo(), getAliAmount(refundDTO.getRefund()));
            LOGGER.info("refundResponse:{}", refundResponse.httpBody);
            return refundResponse.httpBody;
        } catch (Exception e) {
            LOGGER.error("支付宝退款失败", e);
            throw new PayException("alipay refund error");
        }
    }

    @Override
    public String queryRefundByOutRefundNo(QueryRefundDto queryRefundDTO) {
        try {
            final Client common = Factory.Payment.Common();
            Optional.ofNullable(queryRefundDTO.getTradeNo()).ifPresent(tradeNo -> common.optional("trade_no", tradeNo));
            Optional.ofNullable(queryRefundDTO.getOutTradeNo()).ifPresent(outTradeNo -> common.optional("out_trade_no", outTradeNo));
            final AlipayTradeFastpayRefundQueryResponse refundQueryResponse = common
                    .queryRefund(queryRefundDTO.getOutTradeNo(),
                            queryRefundDTO.getOutRequestNo());
            LOGGER.info("refundQueryResponse:{}", refundQueryResponse.httpBody);
            return refundQueryResponse.httpBody;
        } catch (Exception e) {
            LOGGER.error("支付宝查询退款订单失败", e);
            throw new PayException("alipay queryRefundByOutRefundNo error");
        }
    }

    @Override
    @SneakyThrows
    public boolean verifyNotify(Map<String, String> params) {
        // 4、验证app_id是否为该商户本身。
        if (!params.get(APP_ID).equals(aliPayProperties.getAppId())) {
            throw new PayException("app_id不一致");
        }
        return Factory.Payment.Common().verifyNotify(params);
    }

    /**
     * 获取支付宝金额
     * 字符串 取值范围为 0.01~100000000.00，精确到小数点后两位
     *
     * @param amount 元
     * @return 分
     */
    private String getAliAmount(Double amount) {
        return BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP).toString();
    }
}
