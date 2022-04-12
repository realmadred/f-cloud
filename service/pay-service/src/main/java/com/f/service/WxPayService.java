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
package com.f.service;

import com.f.dto.pay.PayDto;
import com.f.dto.pay.RefundDto;
import com.f.dto.pay.WxPaySignDto;

/**
 * @author liuf
 * @date 2022/04/12
 */
public interface WxPayService {

    /**
     * 扫码支付获取二维码
     * https://api.mch.weixin.qq.com/v3/pay/transactions/native
     *
     * @param payDTO 支付dto
     * @return 二维码
     */
    String nativePay(PayDto payDTO);

    /**
     * app支付
     * https://api.mch.weixin.qq.com/v3/pay/transactions/app
     *
     * @param payDTO 支付dto
     * @return 支付签名信息
     */
    WxPaySignDto appPay(PayDto payDTO);

    /**
     * 根据微信订单id查询订单
     * https://api.mch.weixin.qq.com/v3/pay/transactions/id/{transaction_id}
     *
     * @param transactionId 微信订单id
     * @return 订单信息json
     */
    String queryOrderByTransactionId(String transactionId);

    /**
     * 根据自己订单号查询订单
     * https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/{out_trade_no}
     *
     * @param outTradeNo 订单号
     * @return 订单信息json
     */
    String queryOrderByOutTradeNo(String outTradeNo);

    /**
     * 根据自己订单号关闭订单
     * https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/{out_trade_no}/close
     *
     * @param outTradeNo 订单号
     */
    void closeOrder(String outTradeNo);

    /**
     * 当交易发生之后一年内，由于买家或者卖家的原因需要退款时，
     * 卖家可以通过退款接口将支付金额退还给买家，
     * 微信支付将在收到退款请求并且验证成功之后，
     * 将支付款按原路退还至买家账号上
     * https://api.mch.weixin.qq.com/v3/refund/domestic/refunds
     * 接口频率：150qps
     *
     * @param refundDTO 退款dto
     * @return 退款信息json
     */
    String refund(RefundDto refundDTO);

    /**
     * 根据退款单号查询退款信息
     * 提交退款申请后，通过调用该接口查询退款状态。
     * 退款有一定延时，建议在提交退款申请后1分钟发起查询退款状态，
     * 一般来说零钱支付的退款5分钟内到账，银行卡支付的退款1-3个工作日到账。
     * https://api.mch.weixin.qq.com/v3/refund/domestic/refunds/{out_refund_no}
     *
     * @param outRefundNo 退款单号
     * @return 订单退款信息json
     */
    String queryRefundByOutRefundNo(String outRefundNo);

    /**
     * 微信验签
     *
     * @param serialNo  证书序列号
     * @param body      密文
     * @param signature 签名
     * @param nonce     随机数
     * @param timestamp 时间戳
     * @return 明温
     */
    String verifyNotify(String serialNo, String body, String signature, String nonce, String timestamp);
}
