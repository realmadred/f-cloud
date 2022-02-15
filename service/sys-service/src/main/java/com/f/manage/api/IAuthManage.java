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
package com.f.manage.api;

import com.f.base.Result;
import com.f.dto.TokenDataDto;
import com.f.dto.sys.SysUserDto;
import com.f.vo.sys.SysUserVo;

/**
 * 授权管理
 *
 * @author liuf
 * @date 2021年12月6日
 */
public interface IAuthManage {

    /**
     * 密码等级接口
     *
     * @param tokenDataDto 用户加密信息
     * @return 登录成功用户信息
     */
    Result<SysUserVo> login(TokenDataDto tokenDataDto);

    /**
     * 用户注册
     *
     * @param user 注册用户信息
     * @return 结果
     */
    Result<Void> register(SysUserDto user);

    /**
     * 退出登录
     *
     * @return res
     * @date 2022年1月12日
     */
    Result<Void> logout();

    /**
     * 获取aes key
     *
     * @param key 公钥
     * @return aec key
     */
    String getKey(String key);
}
