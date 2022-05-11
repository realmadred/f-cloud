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

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.f.base.PageRequest;
import com.f.entity.SysLog;

/**
 * <p>
 * 系统日志 服务类
 * </p>
 *
 * @author liuf
 * @date 2022-01-14
 */
public interface SysLogService extends IService<SysLog> {

    /**
     * 分页查询日志
     * @param page 分页对象
     * @return 分页数据
     */
    Page<SysLog> selectPage(PageRequest<SysLog> page);

    /**
     * 测试分布式事务
     */
    void seataTest();


}
