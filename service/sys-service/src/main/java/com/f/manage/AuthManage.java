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
package com.f.manage;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.f.base.Result;
import com.f.cache.CacheTemplate;
import com.f.config.SysProperties;
import com.f.constant.Constant;
import com.f.converter.SysUserConverter;
import com.f.dto.SysUserDto;
import com.f.dto.TokenDataDto;
import com.f.entity.SysUser;
import com.f.enums.ResultEnum;
import com.f.manage.api.IAuthManage;
import com.f.service.SysUserService;
import com.f.utils.AesUtils;
import com.f.utils.IdUtils;
import com.f.utils.Json;
import com.f.utils.RsaUtils;
import com.f.utils.ServiceUtils;
import com.f.vo.SysUserVo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 授权管理
 *
 * @author liuf
 * @date 2021/12/6 17:05
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class AuthManage implements IAuthManage {

    private final SysUserService userService;
    private final CacheTemplate cacheTemplate;
    private final SysProperties sysProperties;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result<Void> register(SysUserDto user) {
        log.info("查看用户是否已经注册");
        if (userService.exists(userService.lambdaQuery().eq(SysUser::getName, user.getName()))) {
            return Result.fail(ResultEnum.USER_EXISTS);
        }
        final SysUser sysUser = new SysUser();
        sysUser.setName(user.getName()).setPassword(ServiceUtils.encodePassword(user.getPassword()));
        userService.insert(sysUser);
        return Result.success();
    }

    @Override
    public Result<Void> logout() {
        final String jid = ServiceUtils.getJid();
        if (StringUtils.isNotBlank(jid)) {
            cacheTemplate.reactiveObject().del(ServiceUtils.getAesRedisKey(jid)).subscribe();
            cacheTemplate.reactiveObject().setex(jid, sysProperties.getAuth().getTokenExpire(), StringUtils.EMPTY).subscribe();
        }
        return Result.success();
    }

    @Override
    public Result<SysUserVo> login(TokenDataDto tokenDataDto) {
        final String data = tokenDataDto.getData();
        String key = cacheTemplate.sync().get(tokenDataDto.getToken());
        if (key == null) {
            return Result.fail(ResultEnum.AES_KEY_EXPIRE);
        }
        final SysUserDto sysUserDto = Json.parse(AesUtils.decrypt(data, key), SysUserDto.class);
        final SysUser sysUser = userService.getOne(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getName, sysUserDto.getName())
                .eq(SysUser::getStatus, Constant.USER_STATUS_OK).last(Constant.LIMIT));
        if (sysUser == null) {
            return Result.fail(ResultEnum.LOGIN_FAIL);
        }

        log.info("开始验证密码");
        if (!ServiceUtils.matches(sysUserDto.getPassword(), sysUser.getPassword())) {
            log.info("密码不正确");
            return Result.fail(ResultEnum.LOGIN_FAIL);
        }

        log.info("登录成功");
        final SysUserVo userVo = SysUserConverter.INSTANCE.e2v(sysUser);
        // 生成token
        final long jid = IdUtils.id();
        // Jwt存储信息
        Map<String, Object> claimsMap = new HashMap<>(8);
        claimsMap.put(Constant.JWT_ID, jid);
        claimsMap.put(Constant.USER_ID, userVo.getId());
        claimsMap.put(Constant.USER_NAME, userVo.getName());
        userVo.setToken(createToken(claimsMap));
        cacheTemplate.reactive().del(tokenDataDto.getToken()).subscribe();
        cacheTemplate.reactive().setex(ServiceUtils.getAesRedisKey(String.valueOf(jid)), sysProperties.getAuth().getTokenExpire(), key).subscribe();
        return Result.success(userVo);
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     * @date 2022年1月25日
     */
    private String createToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).setExpiration(DateUtils.addSeconds(new Date(), sysProperties.getAuth().getTokenExpire())).signWith(SignatureAlgorithm.HS256, Constant.JWT_SECRET).compact();
    }

    @Override
    public String getKey(String key) {
        final String token = IdUtils.uuid();
        final String uuid = IdUtils.uuid();
        cacheTemplate.reactive().setex(token, 120, uuid).subscribe();
        return RsaUtils.encryptByPublicKey(StringUtils.replace(key, Constant.SPACE, Constant.ADD), token + Constant.VERTICAL + uuid);
    }
}
