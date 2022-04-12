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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 支付dto
 *
 * @author liuf
 * @date 2022/04/12
 */
@Data
public class PayDto implements Serializable {

    private static final long serialVersionUID = -5172084388489235125L;

    /**
     * appId
     */
    private String appId;
    /**
     * 商品描述
     */
    private String description;
    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 交易结束时间 遵循rfc3339标准格式(例如：2015-05-20T13:29:35+08:00)
     */
    private String timeExpire;
    /**
     * 附加数据
     */
    private String attach;
    /**
     * 通知地址
     */
    private String notifyUrl;
    /**
     * 订单金额保留两位小数,精确到分
     */
    private Double amount;
    /**
     * 货币类型
     */
    private String currency;
    /**
     * 其他非必填参数
     */
    private Map<String, Object> otherParams;

    public static final class PayDtoBuilder {
        /**
         * appId
         */
        private String appId;
        /**
         * 商品描述
         */
        private String description;
        /**
         * 商户订单号
         */
        private String outTradeNo;
        /**
         * 交易结束时间 遵循rfc3339标准格式(例如：2015-05-20T13:29:35+08:00)
         */
        private String timeExpire;
        /**
         * 附加数据
         */
        private String attach;
        /**
         * 通知地址
         */
        private String notifyUrl;
        /**
         * 订单金额保留两位小数,精确到分
         */
        private Double amount;
        /**
         * 货币类型
         */
        private String currency;
        /**
         * 其他非必填参数
         */
        private Map<String, Object> otherParams;

        private PayDtoBuilder() {
        }

        public static PayDtoBuilder builder() {
            return new PayDtoBuilder();
        }

        public PayDtoBuilder appId(String appId) {
            this.appId = appId;
            return this;
        }

        public PayDtoBuilder description(String description) {
            this.description = description;
            return this;
        }

        public PayDtoBuilder outTradeNo(String outTradeNo) {
            this.outTradeNo = outTradeNo;
            return this;
        }

        public PayDtoBuilder timeExpire(String timeExpire) {
            this.timeExpire = timeExpire;
            return this;
        }

        public PayDtoBuilder attach(String attach) {
            this.attach = attach;
            return this;
        }

        public PayDtoBuilder notifyUrl(String notifyUrl) {
            this.notifyUrl = notifyUrl;
            return this;
        }

        public PayDtoBuilder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        public PayDtoBuilder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public PayDtoBuilder otherParams(Map<String, Object> otherParams) {
            this.otherParams = otherParams;
            return this;
        }

        public PayDtoBuilder addOtherParams(String key, Object value) {
            this.otherParams = Optional.ofNullable(otherParams).orElseGet(HashMap::new);
            this.otherParams.put(key, value);
            return this;
        }

        public PayDto build() {
            PayDto payDTO = new PayDto();
            payDTO.setAppId(appId);
            payDTO.setDescription(description.length() > 36 ? description.substring(0, 35) : description);
            payDTO.setOutTradeNo(outTradeNo);
            payDTO.setTimeExpire(timeExpire);
            payDTO.setAttach(attach);
            payDTO.setNotifyUrl(notifyUrl);
            payDTO.setAmount(amount);
            payDTO.setCurrency(currency);
            payDTO.setOtherParams(otherParams);
            return payDTO;
        }
    }
}


