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

/**
 * 退款dto
 *
 * @author liuf
 * @date 2022/04/12
 */
@Data
public class QueryRefundDto implements Serializable {

    private static final long serialVersionUID = -9194661805961086077L;

    /**
     * 商户订单号 与 支付宝交易号二选一
     */
    private String tradeNo;
    /**
     * 支付宝交易号 与 商户订单号二选一
     */
    private String outRequestNo;
    /**
     * 查询请求编号
     */
    private String outTradeNo;

    public QueryRefundDto() {
    }

    public QueryRefundDto(String outTradeNo, String tradeNo, String outRequestNo) {
        this.outTradeNo = outTradeNo;
        this.tradeNo = tradeNo;
        this.outRequestNo = outRequestNo;
    }

}


