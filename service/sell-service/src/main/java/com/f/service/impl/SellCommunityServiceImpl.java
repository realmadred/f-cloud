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

import com.f.entity.SellCommunity;
import com.f.mapper.SellCommunityMapper;
import com.f.service.BaseServiceImpl;
import com.f.service.SellCommunityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 小区 服务实现类
 * </p>
 *
 * @author liuf
 * @date 2022-05-11
 */
@Service
public class SellCommunityServiceImpl extends BaseServiceImpl<SellCommunityMapper, SellCommunity> implements SellCommunityService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testSeata() {
        final SellCommunity community = new SellCommunity();
        community.setName("test");
        community.setAddress("beijing");
        community.setHouseNumber(1000);
        insert(community);
//        System.out.println(1/0);
    }
}
