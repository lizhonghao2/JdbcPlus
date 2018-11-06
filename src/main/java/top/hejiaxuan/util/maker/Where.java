package top.hejiaxuan.util.maker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * where条件 默认使用 and 连接多个条件
 */
public class Where {

    static final String AND = "AND ";

    static final String OR = "OR ";

    private String sql;

    private String column;

    private String connect = AND;

    private List<Object> values;

    //是否有值（null 也代表有值）
    private boolean hasValue;

    /**
     * @param column 被操作的列
     * @param sql    操作的sql
     */
    public Where(String column, String sql) {
        this.column = column;
        this.sql = sql;
        this.hasValue = false;
        this.values = new ArrayList<>();
    }

    /**
     * @param column 被操作的列
     * @param sql    操作的sql
     * @param value sql的参数
     */
    public Where(String column, String sql, Object value) {
        this.sql = sql;
        this.column = column;
        this.values = new ArrayList<>();
        this.values.add(value);
        this.hasValue = true;
    }

    /**
     * @param column 被操作的列
     * @param sql    操作的sql
     * @param values sql的参数
     */
    public Where(String column, String sql, Object[] values) {
        this.sql = sql;
        this.column = column;
        this.values = Arrays.asList(values);
        this.hasValue = true;
    }

    public Where or() {
        this.connect = OR;
        return this;
    }

    public Where and() {
        this.connect = AND;
        return this;
    }

    /**
     * 获取本次条件的连接符
     *
     * @return
     */
    public String getConnect() {
        return connect;
    }

    protected String getSql() {
        return sql;
    }

    protected boolean isHasValue() {
        return hasValue;
    }

    protected List<Object> getValues() {
        return values;
    }

    public String getColumn() {
        return column;
    }
}
