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
package com.f.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.f.base.PageRequest;
import com.f.dto.SysDictDto;
import com.f.entity.SysDict;
import com.f.entity.SysDictDetail;
import com.f.mapper.SysDictDetailMapper;
import com.f.mapper.SysDictMapper;
import com.f.service.BaseServiceImpl;
import com.f.service.SysDictService;
import com.f.utils.IdUtils;
import com.f.utils.ServiceUtils;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author liuf
 * @date 2022-01-18
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysDictServiceImpl extends BaseServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    private final SysDictDetailMapper detailMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveDetail(SysDictDto dictDto) {
        log.info("保存数据字典:{}", dictDto);
        ServiceUtils.setUpdateUser(dictDto);
        getBaseMapper().updateById(dictDto);

        // 删除原来的数据
        detailMapper.realDelete(Wrappers.lambdaQuery(SysDictDetail.class).eq(SysDictDetail::getDictId, dictDto.getId()));

        log.info("处理下级数据");
        final List<SysDictDetail> list = dictDto.getList();
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        log.info("添加下级数据");
        list.forEach(d -> {
            d.setDictId(dictDto.getId());
            d.setDictCode(dictDto.getCode());
            d.setId(IdUtils.id());
            ServiceUtils.setCreateUser(d);
        });
        detailMapper.mysqlInsertBatch(list);
    }

    @Override
    public Page<SysDict> selectPage(PageRequest<SysDict> page) {
        LambdaQueryWrapper<SysDict> lambdaQuery = Wrappers.lambdaQuery(SysDict.class);
        SysDict entity = page.getEntity();
        if (entity != null) {
            if (!Strings.isNullOrEmpty(entity.getName())) {
                lambdaQuery.like(SysDict::getName, entity.getName());
            }
        }
        return page(page.toPlusPage(), lambdaQuery);
    }
}
