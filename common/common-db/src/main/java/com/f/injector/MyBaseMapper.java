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
package com.f.injector;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * MyBaseMapper
 *
 * @param <T> 实体泛型参数
 * @author liuf
 * @date 2022年2月14日
 */
public interface MyBaseMapper<T> extends BaseMapper<T> {

    /**
     * mysql 批量添加
     *
     * @param batchList 数据列表
     * @return 修改行数
     */
    int mysqlInsertBatch(@Param("list") List<T> batchList);

    /**
     * 逻辑删除
     *
     * @param entity 实体
     * @return 修改行数
     */
    int logicDeleteById(T entity);

    /**
     * 物理删除
     *
     * @param queryWrapper 查询条件
     * @return 修改行数
     */
    int realDelete(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
}