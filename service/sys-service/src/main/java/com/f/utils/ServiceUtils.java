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

package com.f.utils;

import com.f.base.BaseEntity;
import com.f.constant.Constant;
import com.f.vo.sys.SysUserVo;
import lombok.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * service工具类
 *
 * @author liuf
 * @date 2021/12/15 10:07
 */
public final class ServiceUtils {

    /**
     * 加盟盐
     */
    private static final String SALT = "yu_aW7!9L#p$czo_1G";
    /**
     * 加盟工具
     */
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private static final SysUserVo ANONYMOUS = new SysUserVo();

    static {
        ANONYMOUS.setId(0L);
        ANONYMOUS.setName("");
    }

    /**
     * 获取当前登录用户
     *
     * @return 系统用户
     */
    public static SysUserVo getSysUser() {
        return ANONYMOUS;
    }

    /**
     * 获取当前登录用户id
     *
     * @return 用户id
     * @date 2022年1月12日
     */
    public static long getUserId() {
        return Optional.ofNullable(getSysUser()).map(SysUserVo::getId).orElse(0L);
    }

    /**
     * 设置用户信息
     *
     * @param <T>    实体泛型
     * @param entity 实体
     */
    public static <T extends BaseEntity> void setCreateUser(@NonNull T entity) {
        final SysUserVo sysUser = getSysUser();
        if (entity.getId() == null) {
            entity.setId(IdUtils.id());
        }
        entity.setCreateId(sysUser.getId());
        entity.setCreateName(sysUser.getName());
        final LocalDateTime now = LocalDateTime.now();
        entity.setCreateTime(now);
        entity.setUpdateId(sysUser.getId());
        entity.setUpdateName(sysUser.getName());
        entity.setUpdateTime(now);
        entity.setIsDelete(Constant.DELETE_NO);
    }

    /**
     * 设置用户信息
     *
     * @param <T>        实体泛型
     * @param entityList 实体信息
     */
    public static <T extends BaseEntity> void setCreateUser(@NonNull List<T> entityList) {
        final SysUserVo sysUser = getSysUser();
        final LocalDateTime now = LocalDateTime.now();
        entityList.forEach(entity -> {
            if (entity.getId() == null) {
                entity.setId(IdUtils.id());
            }
            entity.setCreateId(sysUser.getId());
            entity.setCreateName(sysUser.getName());
            entity.setCreateTime(now);
            entity.setUpdateId(sysUser.getId());
            entity.setUpdateName(sysUser.getName());
            entity.setUpdateTime(now);
            entity.setIsDelete(Constant.DELETE_NO);
        });
    }

    /**
     * 设置用户信息
     *
     * @param <T>    实体泛型
     * @param entity 实体
     */
    public static <T extends BaseEntity> void setUpdateUser(@NonNull T entity) {
        setUpdateUser(entity, getSysUser());
    }

    /**
     * 设置用户信息
     *
     * @param <T>        实体泛型
     * @param entityList 实体列表
     */
    public static <T extends BaseEntity> void setUpdateUser(@NonNull List<T> entityList) {
        final SysUserVo sysUser = getSysUser();
        final LocalDateTime now = LocalDateTime.now();
        entityList.forEach(entity -> {
            entity.setUpdateId(sysUser.getId());
            entity.setUpdateName(sysUser.getName());
            entity.setUpdateTime(now);
        });
    }

    /**
     * 设置用户信息
     *
     * @param <T>     实体泛型
     * @param entity  实体
     * @param sysUser 用户信息
     */
    public static <T extends BaseEntity> void setUpdateUser(@NonNull T entity, SysUserVo sysUser) {
        entity.setUpdateId(sysUser.getId());
        entity.setUpdateName(sysUser.getName());
        entity.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 加盟密码
     *
     * @param password 原始密码
     * @return 加密后密码
     * @date 2022年1月12日
     */
    public static String encodePassword(String password) {
        return PASSWORD_ENCODER.encode(SALT + password);
    }

    /**
     * 密码匹配
     *
     * @param password        原始密码
     * @param encodedPassword 编码后的密码
     * @return 是否匹配
     * @date 2022年1月12日
     */
    public static boolean matches(String password, String encodedPassword) {
        return PASSWORD_ENCODER.matches(SALT + password, encodedPassword);
    }

    private ServiceUtils() {
    }
}
