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
package com.f.dto;

import com.f.base.BaseTreeDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 系统菜单dto
 *
 * @author liuf
 * @date 2021/12/30 14:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysMenuDto extends BaseTreeDto<SysMenuDto> implements Serializable {

    private static final long serialVersionUID = 8364012718359276338L;

    private String pids;
    private Integer level;
    private String redirect;
    private String path;
    private String name;
    private String component;
    private String perms;
    private Integer type;
    private Integer sort;
    private MetaDto meta;

    @Data
    public static class MetaDto implements Serializable {

        private static final long serialVersionUID = -8601954475089006510L;

        private String title;
        private Boolean isKeepAlive;
        private Boolean isAffix;
        private Boolean isIframe;
        private String isLink;
        private String icon;
    }

}
