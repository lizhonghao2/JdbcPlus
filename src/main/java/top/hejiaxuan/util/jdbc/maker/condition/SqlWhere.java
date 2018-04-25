package top.hejiaxuan.util.jdbc.maker.condition;

import top.hejiaxuan.util.jdbc.maker.AbstractMaker;
import top.hejiaxuan.util.jdbc.maker.Maker;
import top.hejiaxuan.util.jdbc.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqlWhere extends AbstractMaker implements Where {

    protected List<And> ands = new ArrayList<>();

    protected List<Object> whereValues = new ArrayList<>();

    /**
     * 重写此方法,防止代理类出现影响其子类方法
     *
     * @param entity
     * @return
     */
    @Override
    public Maker target(Class entity) {
        return super.target(entity);
    }

    @Override
    public And equal(final String columnName, final Object value) {
        checkColumn(columnName);
        return new And(StringUtils.append(getColumnName(columnName), " = ? "), value);
    }

    @Override
    public And notEqual(final String columnName, final Object value) {
        checkColumn(columnName);
        return new And(StringUtils.append(getColumnName(columnName), " != ? "), value);
    }

    @Override
    public And greater(final String columnName, final Object value, final boolean andEquals) {
        checkColumn(columnName);
        if (andEquals) {
            return new And(StringUtils.append(getColumnName(columnName), " >= ? "), value);
        } else {
            return new And(StringUtils.append(getColumnName(columnName), " > ? "), value);
        }
    }

    @Override
    public And less(final String columnName, final Object value, final boolean andEquals) {
        checkColumn(columnName);
        if (andEquals) {
            return new And(StringUtils.append(getColumnName(columnName), " <= ? "), value);
        } else {
            return new And(StringUtils.append(getColumnName(columnName), " < ? "), value);
        }
    }

    @Override
    public And like(final String columnName, final Object value) {
        checkColumn(columnName);
        return new And(StringUtils.append(getColumnName(columnName), " like ? "), value);
    }

    @Override
    public And in(final String columnName, final Object[] values) {
        Object[] sqlVal = values;
        if (sqlVal.length == 0) {
            sqlVal = new Object[]{null};
        }
        int length = sqlVal.length;
        checkColumn(columnName);
        StringBuffer inSql = new StringBuffer();
        String name = getColumnName(columnName);
        inSql.append(name);
        inSql.append(" IN ( ");
        String[] strings = StringUtils.repeat("?", length);
        inSql.append(StringUtils.join(Arrays.asList(strings), ", "));
        inSql.append(" ) ");
        return new And(String.format(inSql.toString(), name), sqlVal);
    }

    @Override
    public Where where(And... and) {
        List<String> whereList = new ArrayList<>();
        for (And i : and) {
            this.ands.add(i);
            whereList.add(i.getSql());
            List<Object> sqlValues = i.getSqlValues();
            for (Object o : sqlValues) {
                this.whereValues.add(o);
            }
        }
        //可能使用了多次where方法
        this.sqlWhere += StringUtils.join(whereList, "AND ");
        return this;
    }

    @Override
    public List<And> getWhere() {
        return ands;
    }

    @Override
    public boolean newWhere(Where where) {
        this.ands = new ArrayList<>();
        this.sqlWhere = new String();
        this.sqlMode = where.isSqlMode();
        this.whereValues = new ArrayList<>();
        And[] ands = where.getWhere().toArray(new And[]{});
        where(ands);
        return true;
    }

    @Override
    public String toSql() {
        throw new UnsupportedOperationException("不支持的操作");
    }
}
