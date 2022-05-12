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
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.f.base.PageRequest;
import com.f.client.SellCommunityClient;
import com.f.entity.SysLog;
import com.f.enums.sys.SysLogTypeEnum;
import com.f.exception.ServiceException;
import com.f.mapper.SysLogMapper;
import com.f.service.SysLogService;
import com.f.utils.IdUtils;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author liuf
 * @date 2022-01-14
 */
@Service
@RequiredArgsConstructor
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    private final SellCommunityClient communityClient;

    @Override
    public Page<SysLog> selectPage(PageRequest<SysLog> page) {
        return page(page.toPlusPage(), Wrappers.query(page.getEntity()));
    }

    @GlobalTransactional(timeoutMills = 30000, name = "seataTest", rollbackFor = Exception.class)
    @Override
    public void seataTest() {
        final SysLog sysLog = new SysLog();
        sysLog.setId(IdUtils.id());
        sysLog.setType(SysLogTypeEnum.INFO.getType());
        sysLog.setClassName("SysLogServiceImpl");
        sysLog.setMethodName("seataTest");
        sysLog.setRequestUri("/seata");
        sysLog.setParams("");
        sysLog.setRequestIp("");
        save(sysLog);
        if (communityClient.test().isFail()) {
            throw ServiceException.of("client fail");
        }
    }
}
