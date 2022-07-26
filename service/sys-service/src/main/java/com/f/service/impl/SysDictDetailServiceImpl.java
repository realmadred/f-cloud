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

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.f.entity.SysDictDetail;
import com.f.mapper.SysDictDetailMapper;
import com.f.service.BaseServiceImpl;
import com.f.service.SysDictDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 数据字典详情 服务实现类
 * </p>
 *
 * @author liuf
 * @date 2022-01-18
 */
@Service
public class SysDictDetailServiceImpl extends BaseServiceImpl<SysDictDetailMapper, SysDictDetail> implements SysDictDetailService {

    @Override
    public List<SysDictDetail> detailByDictId(Long dictId) {
        return getBaseMapper().selectList(Wrappers.lambdaQuery(SysDictDetail.class).eq(SysDictDetail::getDictId, dictId));
    }

    @Override
    public List<SysDictDetail> detailByCode(String code) {
        return getBaseMapper().selectList(Wrappers.lambdaQuery(SysDictDetail.class).eq(SysDictDetail::getDictCode, code));
    }
}
