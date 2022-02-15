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
package com.f.base;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据签名dto
 *
 * @author liufeng
 * @date 2022年1月17日
 */
@Data
public class SignDto implements Serializable {

    private static final long serialVersionUID = 4462274099567705823L;

    /**
     * appId
     */
    private String appId;

    /**
     * json加密后数据
     */
    private String data;

    /**
     * 随机数
     */
    private String nonce;

    /**
     * 签名
     */
    private String sign;

    /**
     * 时间戳
     */
    private long timestamp;

}
