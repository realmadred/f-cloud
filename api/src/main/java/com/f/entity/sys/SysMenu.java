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
package com.f.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.f.base.BaseTreeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统菜单
 * </p>
 *
 * @author liuf
 * @date 2021-12-29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("sys_menu")
public class SysMenu extends BaseTreeEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 上级ids（多个用,隔开）
     */
    private String pids;

    /**
     * 重定向路径
     */
    private String redirect;

    /**
     * 标题，需要国际化
     */
    private String title;

    /**
     * 路由地址
     */
    private String url;

    /**
     * vue组件
     */
    private String component;

    /**
     * 是否外链（0 不是 , 1 是）
     */
    private Integer isFrame;

    /**
     * 开启外链
     */
    private String link;

    /**
     * 菜单是否缓存
     */
    private Integer isKeepAlive;

    /**
     * 菜单是否固定
     */
    private Integer isAffix;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单类型（1 菜单 2 按钮 ）
     */
    private Integer type;

    /**
     * 子菜单数目（直接下级）
     */
    private Integer subCount;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态（0 停用 , 1 启用）
     */
    private Integer status;

    /**
     * 备注
     */
    private String remarks;

}
