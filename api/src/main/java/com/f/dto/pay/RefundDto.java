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
package com.f.dto.pay;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 退款dto
 *
 * @author liuf
 * @date 2022/04/12
 */
@Data
public class RefundDto implements Serializable {

    private static final long serialVersionUID = -5405450991092290714L;

    /**
     * 商户订单号 与 交易号二选一
     */
    private String outTradeNo;
    /**
     * 交易号 与 商户订单号二选一
     */
    private String transactionId;
    /**
     * 退款单号
     */
    private String outRefundNo;
    /**
     * 请求编号,支付宝重新订单需要
     */
    private String outRequestNo;
    /**
     * 通知回调地址
     */
    private String notifyUrl;
    /**
     * 订单总金额保留两位小数,精确到分
     */
    private Double total;
    /**
     * 申请退款金额保留两位小数,精确到分
     */
    private Double refund;
    /**
     * 货币类型
     */
    private String currency;
    /**
     * 其他非必填参数
     */
    private Map<String, Object> otherParams;

    public static final class RefundDtoBuilder {
        /**
         * 商户订单号 与 微信商户订单号二选一
         */
        private String outTradeNo;
        /**
         * 微信商户订单号 与 商户订单号二选一
         */
        private String transactionId;
        /**
         * 退款单号
         */
        private String outRefundNo;
        /**
         * 通知回调地址
         */
        private String notifyUrl;
        /**
         * 请求编号,支付宝重新需要
         */
        private String outRequestNo;
        /**
         * 订单总金额保留两位小数,精确到分
         */
        private Double total;
        /**
         * 申请退款金额保留两位小数,精确到分
         */
        private Double refund;
        /**
         * 货币类型
         */
        private String currency;
        /**
         * 其他非必填参数
         */
        private Map<String, Object> otherParams;

        private RefundDtoBuilder() {
        }

        public static RefundDtoBuilder builder() {
            return new RefundDtoBuilder();
        }

        public RefundDtoBuilder outTradeNo(String outTradeNo) {
            this.outTradeNo = outTradeNo;
            return this;
        }

        public RefundDtoBuilder transactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public RefundDtoBuilder outRefundNo(String outRefundNo) {
            this.outRefundNo = outRefundNo;
            return this;
        }

        public RefundDtoBuilder outRequestNo(String outRequestNo) {
            this.outRequestNo = outRequestNo;
            return this;
        }

        public RefundDtoBuilder notifyUrl(String notifyUrl) {
            this.notifyUrl = notifyUrl;
            return this;
        }

        public RefundDtoBuilder total(Double total) {
            this.total = total;
            return this;
        }

        public RefundDtoBuilder refund(Double refund) {
            this.refund = refund;
            return this;
        }

        public RefundDtoBuilder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public RefundDtoBuilder otherParams(Map<String, Object> otherParams) {
            this.otherParams = otherParams;
            return this;
        }

        public RefundDto build() {
            RefundDto refundDTO = new RefundDto();
            refundDTO.setOutTradeNo(outTradeNo);
            refundDTO.setTransactionId(transactionId);
            refundDTO.setOutRefundNo(outRefundNo);
            refundDTO.setOutRequestNo(outRequestNo);
            refundDTO.setNotifyUrl(notifyUrl);
            refundDTO.setTotal(total);
            refundDTO.setRefund(refund);
            refundDTO.setCurrency(currency);
            refundDTO.setOtherParams(otherParams);
            return refundDTO;
        }
    }
}


