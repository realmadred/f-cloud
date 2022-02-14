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

package com.f.injector;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 自定义 SqlInjector 的方法
 *
 * @author liuf
 * @date 2022年2月14日
 */
@RequiredArgsConstructor
@Getter
public enum MySqlMethod {

    /**
     * 根据id逻辑删除
     */
    LOGIC_DELETE_BY_ID("logicDeleteById"),

    /**
     * 真删除
     */
    REAL_DELETE("realDelete"),

    /**
     * mysql 方式批量插入数据
     */
    MYSQL_INSERT_BATCH("mysqlInsertBatch");

    private final String method;
}
