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

package com.f.controller;

import com.f.base.Result;
import com.f.dto.TokenDataDto;
import com.f.dto.sys.SysUserDto;
import com.f.manage.api.IAuthManage;
import com.f.vo.sys.SysUserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 登录授权控制器
 *
 * @author liuf
 * @date 2021/12/15 11:43
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final IAuthManage authManage;

    /**
     * 密码登录
     *
     * @param tokenDataDto 登录用户信息
     * @return 登录用户信息
     */
    @PostMapping("/login")
    public Result<SysUserVo> login(@RequestBody @Valid TokenDataDto tokenDataDto) {
        return authManage.login(tokenDataDto);
    }

    /**
     * 退出登录
     *
     * @return 无
     */
    @PostMapping("/logout")
    public Result<Void> logout() {
        return authManage.logout();
    }

    /**
     * 注册
     *
     * @param user 注册用户
     * @return 注册用户信息
     */
    @PostMapping("/register")
    public Result<Void> register(@RequestBody @Valid SysUserDto user) {
        return authManage.register(user);
    }

    /**
     * 通过公钥获取key
     *
     * @param key 公钥
     * @return aes key
     */
    @GetMapping("/getKey")
    public Result<String> getKey(String key) {
        return Result.success(authManage.getKey(key));
    }
}
