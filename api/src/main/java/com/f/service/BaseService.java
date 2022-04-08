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
package com.f.service;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.f.base.BaseEntity;
import com.f.base.PageRequest;

/**
 * 基础service接口
 *
 * @param <T> 实体
 * @author liuf
 * @date 2021年12月13日
 */
public interface BaseService<T extends BaseEntity> extends IService<T> {

    /**
     * 查询id
     *
     * @param queryWrapper 查询条件
     * @return entity
     */
    Long selectId(LambdaQueryChainWrapper<T> queryWrapper);

    /**
     * 查询id
     *
     * @param queryWrapper 查询条件
     * @return entity
     */
    Long selectId(QueryChainWrapper<T> queryWrapper);

    /**
     * 查询是否存在
     *
     * @param queryWrapper 查询条件
     * @return 是否存在
     */
    boolean exists(LambdaQueryChainWrapper<T> queryWrapper);

    /**
     * 查询是否存在
     *
     * @param queryWrapper 查询条件
     * @return 是否存在
     */
    boolean exists(QueryChainWrapper<T> queryWrapper);

    /**
     * 新增
     *
     * @param entity 实体对象
     * @return entity
     */
    T insert(T entity);

    /**
     * 修改
     *
     * @param entity 实体对象
     * @return entity
     */
    int update(T entity);

    /**
     * 逻辑删除
     *
     * @param id id
     * @return 数据改动行数
     */
    int logicDelete(Long id);

    /**
     * 分页查询
     *
     * @param page page
     * @return 分页数据
     */
    Page<T> selectPage(PageRequest<T> page);
}
