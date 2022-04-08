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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 小区
 * </p>
 *
 * @author liuf
 * @date 2022-04-08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("cell_community")
public class CellCommunity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 小区名称
     */
    private String name;

    /**
     * 地址
     */
    private String address;

    /**
     * 户数
     */
    private Integer houseNumber;

}
