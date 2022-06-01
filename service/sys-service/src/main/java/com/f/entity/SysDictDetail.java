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
 * 数据字典详情
 * </p>
 *
 * @author liuf
 * @date 2022-01-18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("sys_dict_detail")
public class SysDictDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 字典id
     */
    private Long dictId;

    /**
     * 编号
     */
    private String dictCode;

    /**
     * 名称
     */
    private String name;

    /**
     * 字典值
     */
    private String value;

    /**
     * 排序
     */
    private Integer sort;

}