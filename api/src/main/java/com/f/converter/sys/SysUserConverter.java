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

package com.f.converter.sys;

import com.f.entity.sys.SysUser;
import com.f.vo.sys.SysUserVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * 系统用户转换器
 *
 * @author liuf
 * @date 2021年12月15日
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysUserConverter {

    SysUserConverter INSTANCE = Mappers.getMapper(SysUserConverter.class);

    /**
     * 实体转vo
     *
     * @param user 实体
     * @return vo
     */
    SysUserVo e2v(SysUser user);
}