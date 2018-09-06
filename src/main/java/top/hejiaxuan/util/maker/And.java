package top.hejiaxuan.util.maker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * where条件
 */
public class And {

    private String sql;

    private List<Object> sqlValues;

    //是否有值（null 也代表有值）
    private boolean hasValue;

    public And(String sql) {
        this.sql = sql;
        this.hasValue = false;
    }

    public And(String sql, Object sqlValue) {
        this.sql = sql;
        this.sqlValues = new ArrayList<>();
        this.sqlValues.add(sqlValue);
        this.hasValue = true;
    }

    public And(String sql, Object[] sqlValues) {
        this.sql = sql;
        this.sqlValues = Arrays.asList(sqlValues);
        this.hasValue = true;
    }

    protected String getSql() {
        return sql;
    }

    protected boolean isHasValue() {
        return hasValue;
    }

    protected List<Object> getSqlValues() {
        return sqlValues;
    }
}
