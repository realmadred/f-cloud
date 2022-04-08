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
package com.f.converter;

import com.f.dto.SysMenuDto;
import com.f.entity.SysMenu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * 系统菜单 转换器
 *
 * @author liuf
 * @date 2021年12月30日
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysMenuConverter {

    SysMenuConverter INSTANCE = Mappers.getMapper(SysMenuConverter.class);

    /**
     * dto转实体
     *
     * @param menuDto dto
     * @return entity
     */
    @Mappings({
            @Mapping(target = "url", source = "path"),
            @Mapping(target = "icon", source = "meta.icon"),
            @Mapping(target = "title", source = "meta.title"),
            @Mapping(target = "link", source = "meta.isLink"),
            @Mapping(target = "isKeepAlive", expression = "java( menuDto.getMeta().getIsKeepAlive() ? 1 : 0 )"),
            @Mapping(target = "isAffix", expression = "java( menuDto.getMeta().getIsAffix() ? 1 : 0 )"),
            @Mapping(target = "isFrame", expression = "java( menuDto.getMeta().getIsIframe() ? 1 : 0 )"),
    })
    SysMenu d2e(SysMenuDto menuDto);

    /**
     * 实体转dto
     *
     * @param sysMenu 实体
     * @return dto
     */
    @Mappings({
            @Mapping(target = "path", source = "url"),
            @Mapping(target = "meta.icon", source = "icon"),
            @Mapping(target = "meta.title", source = "title"),
            @Mapping(target = "meta.isLink", source = "link"),
            @Mapping(target = "meta.isKeepAlive", expression = "java( java.util.Optional.ofNullable(sysMenu.getIsKeepAlive()).orElse(0) == 1 )"),
            @Mapping(target = "meta.isAffix", expression = "java( java.util.Optional.ofNullable(sysMenu.getIsAffix()).orElse(0) == 1 )"),
            @Mapping(target = "meta.isIframe", expression = "java( java.util.Optional.ofNullable(sysMenu.getIsFrame()).orElse(0) == 1 )"),
    })
    SysMenuDto e2d(SysMenu sysMenu);
}