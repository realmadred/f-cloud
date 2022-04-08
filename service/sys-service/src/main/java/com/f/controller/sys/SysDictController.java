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
package com.f.controller.sys;

import com.f.base.Result;
import com.f.controller.BaseController;
import com.f.dto.SysDictDto;
import com.f.entity.SysDict;
import com.f.entity.SysDictDetail;
import com.f.service.SysDictDetailService;
import com.f.service.SysDictService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * 数据字典  接口
 *
 * @author liuf
 * @date 2022-01-18
 */
@RestController
@RequestMapping("/sysDict")
@RequiredArgsConstructor
@Getter
public class SysDictController extends BaseController<SysDict, SysDictService> {

    private final SysDictService service;
    private final SysDictDetailService detailService;

    /**
     * 根据id查询字典详情
     *
     * @param id id
     * @return 字典详情列表
     */
    @GetMapping("/detailByDictId")
    public Result<List<SysDictDetail>> detailByDictId(@Min(1) Long id) {
        return Result.success(detailService.detailByDictId(id));
    }

    /**
     * 根据code查询字典详情
     *
     * @param code 字典code
     * @return 字典详情列表
     */
    @GetMapping("/detailByCode")
    public Result<List<SysDictDetail>> detailByCode(@RequestParam @Length(min = 2, max = 64) String code) {
        return Result.success(detailService.detailByCode(code));
    }

    /**
     * 新增字典详情数据
     *
     * @param dictDto dto
     * @return 结果
     */
    @PostMapping("/save")
    public Result<Void> saveDetail(@RequestBody @Valid SysDictDto dictDto) {
        service.saveDetail(dictDto);
        return Result.success();
    }

}

