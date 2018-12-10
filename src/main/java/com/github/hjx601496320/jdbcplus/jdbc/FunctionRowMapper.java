package com.github.hjx601496320.jdbcplus.jdbc;

import com.github.hjx601496320.jdbcplus.maker.Function;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author hjx
 */
public class FunctionRowMapper implements RowMapper {

    private Function function;

    public FunctionRowMapper(Function function) {
        this.function = function;
    }

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Object object = rs.getObject(function.getResultName(), function.getResultType());
        return object;
    }
}
