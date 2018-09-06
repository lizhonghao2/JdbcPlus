package top.hejiaxuan.util.maker;

import top.hejiaxuan.util.jdbc.util.StringUtils;
import top.hejiaxuan.util.maker.And;

import java.util.Arrays;
import java.util.List;

/**
 * 查询条件
 */
public class Where {

    public static And equal(final String columnName, final Object value) {
        return new And(StringUtils.append(columnName, " = ? "), value);
    }

    public static And notEqual(final String columnName, final Object value) {
        return new And(StringUtils.append(columnName, " != ? "), value);
    }

    public static And isNotNull(final String columnName) {
        return new And(StringUtils.append(columnName, " IS NOT NULL "));
    }

    public static And isNull(final String columnName) {
        return new And(StringUtils.append(columnName, " IS NULL "));
    }

    public static And greater(final String columnName, final Object value, final boolean andEquals) {
        if (andEquals) {
            return new And(StringUtils.append(columnName, " >= ? "), value);
        }
        return new And(StringUtils.append(columnName, " > ? "), value);
    }

    public static And less(final String columnName, final Object value, final boolean andEquals) {
        if (andEquals) {
            return new And(StringUtils.append(columnName, " <= ? "), value);
        }
        return new And(StringUtils.append(columnName, " < ? "), value);
    }

    public static And like(final String columnName, final Object value) {
        return new And(StringUtils.append(columnName, " like ? "), value);
    }

    public static And in(final String columnName, final Object[] values) {
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
        return new And(String.format(inSql.toString(), columnName), sqlVal);
    }

}
