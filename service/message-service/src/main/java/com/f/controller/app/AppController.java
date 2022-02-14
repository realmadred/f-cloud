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

package com.f.controller.app;

import com.f.base.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * app 接口
 *
 * @author liuf
 * @date 2022/02/14 14:51
 */
@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class AppController {

    /**
     * 获取app最新版本
     *
     * @return 字符串
     */
    @GetMapping("/version")
    public Result<String> version() {
        return Result.success("1.0.0");
    }

}
