package com.f.mapper.injector;

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
 * @date 2022年1月7日
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
