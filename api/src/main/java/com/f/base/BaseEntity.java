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

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体基类
 *
 * @author liuf
 * @date 2021/12/3 11:35
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -4669747923668704106L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人id
     */
    private Long createId;

    /**
     * 创建人
     */
    private String createName;

    /**
     * 修改人id
     */
    private Long updateId;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 修改人姓名
     */
    private String updateName;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

}
