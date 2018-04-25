package top.hejiaxuan.util.jdbc.maker.condition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * where条件
 */
public class And {

    private String sql;

    private List<Object> sqlValues;

    public And(String columnName, Object sqlValue) {
        this.sql = columnName;
        this.sqlValues = new ArrayList<>();
        this.sqlValues.add(sqlValue);
    }

    public And(String columnName, Object[] sqlValues) {
        this.sql = columnName;
        this.sqlValues = Arrays.asList(sqlValues);
    }

    public String getSql() {
        return sql;
    }

    public List<Object> getSqlValues() {
        return sqlValues;
    }
}
