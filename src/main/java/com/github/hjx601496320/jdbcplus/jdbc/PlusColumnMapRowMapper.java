package com.github.hjx601496320.jdbcplus.jdbc;

import org.springframework.jdbc.core.ColumnMapRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 自用结果集转换器
 *
 * @author hjx
 */
public class PlusColumnMapRowMapper extends ColumnMapRowMapper {

    /**
     * 数据库类型为时间时, 如果值为null,则会报错,所以重写此方法.
     *
     * @param rs
     * @param index
     * @return
     * @throws SQLException
     */
    @Override
    protected Object getColumnValue(ResultSet rs, int index) throws SQLException {
        Object columnValue = null;
        try {
            columnValue = super.getColumnValue(rs, index);
        } catch (SQLException e) {
        }
        return columnValue;

    }
}
