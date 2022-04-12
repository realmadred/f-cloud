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


import com.f.dto.pay.PagePayDto;
import com.f.dto.pay.PayDto;
import com.f.dto.pay.QueryRefundDto;
import com.f.dto.pay.RefundDto;

import java.util.Map;

/**
 * @author liuf
 * @date 2022/04/12
 */
public interface AliPayService {

    /**
     * 商户后台调用支付宝接口，生成二维码后，展示给用户，由用户扫描二维码完成订单支付
     * alipay.trade.precreate
     *
     * @param payDTO 支付dto
     * @return 二维码
     */
    String prePay(PayDto payDTO);

    /**
     * pc页面支付
     * alipay.trade.page.pay
     *
     * @param pagePayDTO 支付dto
     * @return 支付页面
     */
    String pagePay(PagePayDto pagePayDTO);

    /**
     * app支付
     * alipay.trade.app.pay
     *
     * @param payDTO 支付dto
     * @return app签名
     */
    String appPay(PayDto payDTO);

    /**
     * 根据自己订单号查询订单
     * alipay.trade.query
     *
     * @param outTradeNo 订单号
     * @return 订单信息json
     */
    String queryOrderByOutTradeNo(String outTradeNo);

    /**
     * 根据自己订单号关闭订单
     * alipay.trade.close
     *
     * @param outTradeNo 订单号
     */
    void closeOrderByOutTradeNo(String outTradeNo);

    /**
     * 支付宝退款支持单笔交易分多次退款，多次退款需要提交原支付订单的商户订单号和设置不同的退款单号。
     * 一笔退款失败后重新提交，要采用原来的退款单号。总退款金额不能超过用户实际支付金额
     * alipay.trade.refund
     *
     * @param refundDTO 退款dto
     * @return 退款信息json
     */
    String refund(RefundDto refundDTO);

    /**
     * 异步退款场景返回了查询数据，且refund_status为空或为REFUND_SUCCESS，则代表退款成功，
     * 若没有查询到则代表未退款成功，可以调用退款接口进行重试。重试时请务必保证退款请求号一致。
     * 同步退款场景需响应返回 trade_no 等具体退款信息才表示退款成功。
     * alipay.trade.fastpay.refund.query
     *
     * @param queryRefundDTO 查询退款dto
     * @return 订单退款信息json
     */
    String queryRefundByOutRefundNo(QueryRefundDto queryRefundDTO);

    /**
     * 支付宝验签
     *
     * @param params 回调参数
     * @return 是否通过
     */
    boolean verifyNotify(Map<String, String> params);
}
