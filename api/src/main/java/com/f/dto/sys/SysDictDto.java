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

package com.f.dto.sys;

import com.f.entity.sys.SysDict;
import com.f.entity.sys.SysDictDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 保存数据字典dto
 *
 * @author liuf
 * @date 2022/1/18 18:03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysDictDto extends SysDict implements Serializable {

    private static final long serialVersionUID = -4048403245349216334L;

    /**
     * 字典详情数据
     */
    private List<SysDictDetail> list;
}
