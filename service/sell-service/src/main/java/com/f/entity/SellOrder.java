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

package com.f.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.f.base.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author liuf
 * @date 2022-05-11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("sell_order")
public class SellOrder extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 订单金额
     */
    private Long amount;

    /**
     * 首付金额
     */
    private Long payAmount;

    /**
     * 支付方式
     */
    private Integer payMethod;

    /**
     * 优惠金额
     */
    private Long discountAmount;

    /**
     * 尾款金额
     */
    private Long lastPayAmount;

    /**
     * 尾款支付方式
     */
    private Integer pastPayMethod;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;

    /**
     * 尾款支付时间
     */
    private LocalDateTime lastPayTime;

    /**
     * 状态;0-创建、1-支付定金、2-已量房、3-设计中、4-已复尺、5-设计确认、6-下单、7-安装、8-安装完成、9-完成
     */
    private Integer status;

    /**
     * 小区id
     */
    private Long communityId;

    /**
     * 小区名称
     */
    private String communityName;

    /**
     * 业主姓名
     */
    private String memberName;

    /**
     * 业主电话
     */
    private String memberPhone;

    /**
     * 备注
     */
    private String remark;

}
