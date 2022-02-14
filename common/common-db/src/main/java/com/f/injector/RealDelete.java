package com.f.injector;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.StringJoiner;

/**
 * 物理删除
 *
 * @author liuf
 * @date 2022年2月14日
 */
public class RealDelete extends AbstractMethod {

    private static final String NOT_NULL = " != null";
    private static final String NOT_NULL_AND = " != null and ";
    private static final String NOT_EMPTY_AND = " != '' and ";
    public static final String SQL_SEGMENT = " ${" + WRAPPER_SQLSEGMENT + "}";


    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        StringJoiner joiner = new StringJoiner(SPACE);
        joiner.add("<script> DELETE FROM");
        joiner.add(tableInfo.getTableName());
        joiner.add(sqlWhereEntityWrapper(false, tableInfo));
        joiner.add(sqlComment());
        joiner.add("</script>");
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, joiner.toString(), modelClass);
        return this.addDeleteMappedStatement(mapperClass, MySqlMethod.REAL_DELETE.getMethod(), sqlSource);
    }

    @Override
    public String getMethod(SqlMethod sqlMethod) {
        return MySqlMethod.REAL_DELETE.getMethod();
    }

    @Override
    public String sqlWhereEntityWrapper(boolean newLine, TableInfo table) {
        String sqlScript = table.getAllSqlWhere(false, true, WRAPPER_ENTITY_DOT);
        sqlScript = SqlScriptUtils.convertIf(sqlScript, WRAPPER_ENTITY + NOT_NULL, true);
        sqlScript += NEWLINE;
        sqlScript += SqlScriptUtils.convertIf(SqlScriptUtils.convertIf(" AND", WRAPPER_NONEMPTYOFENTITY + " and " + WRAPPER_NONEMPTYOFNORMAL, false) + SQL_SEGMENT,
                WRAPPER_SQLSEGMENT + NOT_NULL_AND + WRAPPER_SQLSEGMENT + NOT_EMPTY_AND + WRAPPER_NONEMPTYOFWHERE, true);
        sqlScript = SqlScriptUtils.convertWhere(sqlScript) + NEWLINE;
        sqlScript += SqlScriptUtils.convertIf(SQL_SEGMENT,
                WRAPPER_SQLSEGMENT + NOT_NULL_AND + WRAPPER_SQLSEGMENT + NOT_EMPTY_AND + WRAPPER_EMPTYOFWHERE, true);
        sqlScript = SqlScriptUtils.convertIf(sqlScript, WRAPPER + NOT_NULL, true);
        return newLine ? NEWLINE + sqlScript : sqlScript;
    }
}
