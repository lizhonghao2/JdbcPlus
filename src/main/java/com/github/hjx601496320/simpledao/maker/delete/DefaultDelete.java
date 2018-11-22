package com.github.hjx601496320.simpledao.maker.delete;

import com.github.hjx601496320.simpledao.jdbc.util.StringUtils;
import com.github.hjx601496320.simpledao.maker.AbstractMaker;

import java.util.Arrays;
import java.util.List;

/**
 * 默认的删除
 *
 * @author hjx
 */
public class DefaultDelete extends AbstractMaker implements Delete {

    @Override
    protected String makeSql() {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ")
                .append(getTableName())
                .append(StringUtils.SPACE)
                .append(sqlWhere());
        return sql.toString();
    }

    @Override
    protected List<Object> makeSqlValue() {
        return Arrays.asList(sqlValues);
    }

}
