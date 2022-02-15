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

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.StringJoiner;

/**
 * mysql 批量插入
 *
 * @author liuf
 * @date 2022年2月14日
 */
public class MysqlInsertBatch extends AbstractMethod {

    private static final String END = "},";
    private static final String FOREACH_END = "</foreach>";
    private static final String FOREACH_STR = "<foreach collection=\"list\" item=\"item\" index=\"index\" open=\"(\" separator=\"),(\" close=\")\">";
    private static final String ITEM = "#{item.";

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        StringJoiner joiner = new StringJoiner(SPACE);
        joiner.add("<script>insert into");
        joiner.add(tableInfo.getTableName());
        joiner.add(prepareFieldSql(tableInfo));
        joiner.add("values");
        joiner.add(prepareValuesSqlForMysqlBatch(tableInfo));
        joiner.add("</script>");
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, joiner.toString(), modelClass);
        return this.addInsertMappedStatement(mapperClass, modelClass, MySqlMethod.MYSQL_INSERT_BATCH.getMethod(), sqlSource, new NoKeyGenerator(), null, null);
    }

    private String prepareFieldSql(TableInfo tableInfo) {
        StringJoiner joiner = new StringJoiner(",");
        joiner.add(tableInfo.getKeyProperty());
        tableInfo.getFieldList().forEach(x -> joiner.add(x.getColumn()));
        return "(" + joiner + ")";
    }

    private String prepareValuesSqlForMysqlBatch(TableInfo tableInfo) {
        final StringBuilder valueSql = new StringBuilder();
        valueSql.append(FOREACH_STR);
        valueSql.append(ITEM).append(tableInfo.getKeyProperty()).append(END);
        tableInfo.getFieldList().forEach(x -> valueSql.append(ITEM).append(x.getProperty()).append(END));
        valueSql.delete(valueSql.length() - 1, valueSql.length());
        valueSql.append(FOREACH_END);
        return valueSql.toString();
    }
}