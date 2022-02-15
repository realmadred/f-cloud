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
import java.util.List;

/**
 * 基础树Dto 结构
 *
 * @param <T> 泛型树对象
 * @author liuf
 * @date 2021/12/3 11:35
 */
@Data
public class BaseTreeDto<T> implements Serializable {

    private static final long serialVersionUID = -8123144585905747338L;

    /**
     * id
     */
    private Long id;

    /**
     * 父级id
     */
    private Long pid;

    /**
     * 下级
     */
    private List<T> children;

}
