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
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 页面支付dto
 *
 * @author liuf
 * @date 2022/04/12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PagePayDto extends PayDto implements Serializable {

    private static final long serialVersionUID = -6575788371733662832L;

    /**
     * 支付宝页面支付返回地址
     */
    private String returnUrl;

    public static final class PagePayDtoBuilder {
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
         * 支付宝页面支付返回地址
         */
        private String returnUrl;
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

        private PagePayDtoBuilder() {
        }

        public static PagePayDtoBuilder builder() {
            return new PagePayDtoBuilder();
        }

        public PagePayDtoBuilder appId(String appId) {
            this.appId = appId;
            return this;
        }

        public PagePayDtoBuilder description(String description) {
            this.description = description;
            return this;
        }

        public PagePayDtoBuilder outTradeNo(String outTradeNo) {
            this.outTradeNo = outTradeNo;
            return this;
        }

        public PagePayDtoBuilder timeExpire(String timeExpire) {
            this.timeExpire = timeExpire;
            return this;
        }

        public PagePayDtoBuilder attach(String attach) {
            this.attach = attach;
            return this;
        }

        public PagePayDtoBuilder notifyUrl(String notifyUrl) {
            this.notifyUrl = notifyUrl;
            return this;
        }

        public PagePayDtoBuilder returnUrl(String returnUrl) {
            this.returnUrl = returnUrl;
            return this;
        }

        public PagePayDtoBuilder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        public PagePayDtoBuilder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public PagePayDtoBuilder otherParams(Map<String, Object> otherParams) {
            this.otherParams = otherParams;
            return this;
        }

        public PagePayDtoBuilder addOtherParams(String key, Object value) {
            this.otherParams = Optional.ofNullable(otherParams).orElseGet(HashMap::new);
            this.otherParams.put(key, value);
            return this;
        }

        public PagePayDto build() {
            PagePayDto pagePayDTO = new PagePayDto();
            pagePayDTO.setAppId(appId);
            pagePayDTO.setDescription(description.length() > 36 ? description.substring(0, 35) : description);
            pagePayDTO.setOutTradeNo(outTradeNo);
            pagePayDTO.setTimeExpire(timeExpire);
            pagePayDTO.setAttach(attach);
            pagePayDTO.setNotifyUrl(notifyUrl);
            pagePayDTO.setReturnUrl(returnUrl);
            pagePayDTO.setAmount(amount);
            pagePayDTO.setCurrency(currency);
            pagePayDTO.setOtherParams(otherParams);
            return pagePayDTO;
        }
    }
}


