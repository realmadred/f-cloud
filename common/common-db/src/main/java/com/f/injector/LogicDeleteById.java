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
package com.f.injector;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.StringJoiner;

/**
 * 逻辑删除并记录修改时间
 *
 * @author liuf
 * @date 2022年2月14日
 */
public class LogicDeleteById extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        StringJoiner joiner = new StringJoiner(SPACE);
        joiner.add("<script> UPDATE");
        joiner.add(tableInfo.getTableName());
        joiner.add("SET");
        joiner.add("update_id=#{updateId},update_time=#{updateTime},update_name=#{updateName},is_delete=1");
        joiner.add("WHERE id = #{id}");
        joiner.add("</script>");
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, joiner.toString(), modelClass);
        return addUpdateMappedStatement(mapperClass, modelClass, MySqlMethod.LOGIC_DELETE_BY_ID.getMethod(), sqlSource);
    }

    @Override
    public String getMethod(SqlMethod sqlMethod) {
        return MySqlMethod.LOGIC_DELETE_BY_ID.getMethod();
    }
}
