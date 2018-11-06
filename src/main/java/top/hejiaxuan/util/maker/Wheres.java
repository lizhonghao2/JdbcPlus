package top.hejiaxuan.util.maker;

import top.hejiaxuan.util.jdbc.util.StringUtils;

import java.util.Arrays;

/**
 * 查询条件
 */
public class Wheres {

    public static Where equal(final String columnName, final Object value) {
        return new Where(columnName, StringUtils.append(columnName, " = ? "), value);
    }

    public static Where notEqual(final String columnName, final Object value) {
        return new Where(columnName, StringUtils.append(columnName, " != ? "), value);
    }

    public static Where isNotNull(final String columnName) {
        return new Where(columnName, StringUtils.append(columnName, " IS NOT NULL "));
    }

    public static Where isNull(final String columnName) {
        return new Where(columnName, StringUtils.append(columnName, " IS NULL "));
    }

    public static Where greater(final String columnName, final Object value, final boolean andEquals) {
        if (andEquals) {
            return new Where(columnName, StringUtils.append(columnName, " >= ? "), value);
        }
        return new Where(columnName, StringUtils.append(columnName, " > ? "), value);
    }

    public static Where less(final String columnName, final Object value, final boolean andEquals) {
        if (andEquals) {
            return new Where(columnName, StringUtils.append(columnName, " <= ? "), value);
        }
        return new Where(columnName, StringUtils.append(columnName, " < ? "), value);
    }

    public static Where like(final String columnName, final Object value) {
        return new Where(columnName, StringUtils.append(columnName, " like ? "), value);
    }

    public static Where in(final String columnName, final Object[] values) {
        Object[] sqlVal = values;
        if (sqlVal.length == 0) {
            sqlVal = new Object[]{null};
        }
        int length = sqlVal.length;
        StringBuffer inSql = new StringBuffer();
        inSql.append(columnName);
        inSql.append(" IN ( ");
        String[] strings = StringUtils.repeat("?", length);
        inSql.append(StringUtils.join(Arrays.asList(strings), ", "));
        inSql.append(" ) ");
        return new Where(columnName, String.format(inSql.toString(), columnName), sqlVal);
    }

}
