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
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 微信支付签名dto
 *
 * @author liuf
 * @date 2022/04/12
 */
@Data
@Accessors(chain = true)
public class WxPaySignDto implements Serializable {

    private static final long serialVersionUID = -344239597278126323L;

    /**
     * appId
     */
    private String appId;
    /**
     * 商户id
     */
    private String partnerId;
    /**
     * 预支付id
     */
    private String prepayId;
    /**
     * 随机数
     */
    private String nonce;
    /**
     * 时间戳
     */
    private Long timestamp;
    /**
     * 签名
     */
    private String sign;

}


