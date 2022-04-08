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
package com.f.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 对象
 *
 * @author liuf
 * @date 2022/2/22 21:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ObjectDto extends BucketDto {

    private static final long serialVersionUID = -7803339769794403487L;

    /**
     * 文件后缀(.jpg)
     */
    private String suffix;
}
