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

package com.f.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.f.base.BaseEntity;
import com.f.base.PageRequest;
import com.f.constant.Constant;
import com.f.injector.MyBaseMapper;
import com.f.service.base.BaseService;
import com.f.utils.CommonUtils;
import com.f.utils.ServiceUtils;
import lombok.SneakyThrows;
import org.springframework.transaction.annotation.Transactional;

/**
 * 基础接口实现
 *
 * @param <T> 实体
 * @param <M> Mapper
 * @author liuf
 * @date 2021/12/13 21:00
 */
public class BaseServiceImpl<M extends MyBaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements BaseService<T> {

    @Override
    public Long selectId(LambdaQueryChainWrapper<T> queryWrapper) {
        return CommonUtils.getId(getBaseMapper().selectOne(queryWrapper.select(T::getId).last(Constant.LIMIT)));
    }

    @Override
    public Long selectId(QueryChainWrapper<T> queryWrapper) {
        return CommonUtils.getId(getBaseMapper().selectOne(queryWrapper.select(Constant.ID).last(Constant.LIMIT)));
    }

    @Override
    public boolean exists(LambdaQueryChainWrapper<T> queryWrapper) {
        return selectId(queryWrapper) != null;
    }

    @Override
    public boolean exists(QueryChainWrapper<T> queryWrapper) {
        return selectId(queryWrapper) != null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public T insert(T entity) {
        ServiceUtils.setCreateUser(entity);
        getBaseMapper().insert(entity);
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(T entity) {
        ServiceUtils.setUpdateUser(entity);
        return getBaseMapper().updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows
    public int logicDelete(Long id) {
        final T entity = getEntityClass().newInstance();
        ServiceUtils.setUpdateUser(entity);
        entity.setId(id);
        return getBaseMapper().logicDeleteById(entity);
    }

    @Override
    public Page<T> selectPage(PageRequest<T> page) {
        return page(page.toPlusPage(), Wrappers.query(page.getEntity()));
    }
}