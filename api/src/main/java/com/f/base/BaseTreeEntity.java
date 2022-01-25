/*
 * Copyright [2021] [liufeng]
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
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 基础树结构
 *
 * @author liuf
 * @date 2021/12/3 11:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseTreeEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6806186760886845572L;

    /**
     * 父id
     */
    private Long pid;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 名称
     */
    private String name;

}
