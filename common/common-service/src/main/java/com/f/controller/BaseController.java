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
package com.f.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.f.base.BaseEntity;
import com.f.base.PageRequest;
import com.f.base.Result;
import com.f.dto.IdDto;
import com.f.service.base.BaseService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * 基础 Controller
 *
 * @param <T> 实体
 * @param <S> service
 * @author liuf
 * @date 2022年2月14日
 */
public abstract class BaseController<T extends BaseEntity, S extends BaseService<T>> {

    /**
     * 获取service
     *
     * @return service
     */
    protected abstract S getService();

    /**
     * 新增
     *
     * @param entity 实体
     * @return 结果
     */
    @PostMapping()
    public Result<T> insert(@RequestBody @Valid T entity) {
        return Result.success(getService().insert(entity));
    }

    /**
     * 修改
     *
     * @param entity 实体
     * @return 修改数量
     */
    @PutMapping()
    public Result<Integer> update(@RequestBody @Valid T entity) {
        return Result.success(getService().update(entity));
    }

    /**
     * 逻辑删除
     *
     * @param dto id
     * @return 修改数量
     */
    @DeleteMapping()
    public Result<Integer> delete(@RequestBody @Valid IdDto dto) {
        return Result.success(getService().logicDelete(dto.getId()));
    }

    /**
     * 分页查询
     *
     * @param page 分页参数
     * @return 分页数据
     */
    @PostMapping("/page")
    public Result<Page<T>> selectPage(@RequestBody @Valid PageRequest<T> page) {
        return Result.success(getService().selectPage(page));
    }

    /**
     * 根据id查询
     *
     * @param id id
     * @return 实体
     */
    @GetMapping()
    public Result<T> selectById(@Min(1) Long id) {
        return Result.success(getService().getById(id));
    }

}
