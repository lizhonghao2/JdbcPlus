package top.hejiaxuan.util.jdbc;

import top.hejiaxuan.util.jdbc.maker.Function;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

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
