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

    private String column;

    private String connect = AND;

    private List<Object> values;

    //是否有值（null 也代表有值）
    private boolean hasValue;

    public Where(String column) {
        this.column = column;
        this.hasValue = false;
    }

    public Where(String column, Object sqlValue) {
        this.column = column;
        this.values = new ArrayList<>();
        this.values.add(sqlValue);
        this.hasValue = true;
    }

    public Where(String column, Object[] values) {
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

    protected String getColumn() {
        return column;
    }

    protected boolean isHasValue() {
        return hasValue;
    }

    protected List<Object> getValues() {
        return values;
    }
}
