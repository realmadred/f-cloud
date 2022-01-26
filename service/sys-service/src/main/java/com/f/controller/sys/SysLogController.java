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

package com.f.controller.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.f.base.PageRequest;
import com.f.base.Result;
import com.f.entity.sys.SysLog;
import com.f.service.sys.SysLogService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 系统日志  接口
 *
 * @author liuf
 * @date 2022-01-14
 */
@RestController
@RequestMapping("/sysLog")
@RequiredArgsConstructor
@Getter
public class SysLogController {

    private final SysLogService service;

    /**
     * 分页查询
     * @param page 分页参数
     * @return 分页数据
     */
    @PostMapping("/page")
    public Result<Page<SysLog>> selectPage(@RequestBody @Valid PageRequest<SysLog> page) {
        return Result.success(service.selectPage(page));
    }
}

