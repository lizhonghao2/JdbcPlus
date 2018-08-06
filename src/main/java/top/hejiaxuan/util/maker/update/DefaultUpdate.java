package top.hejiaxuan.util.maker.update;

import top.hejiaxuan.util.maker.Maker;
import top.hejiaxuan.util.maker.condition.SqlWhere;
import top.hejiaxuan.util.jdbc.util.EntityUtils;
import top.hejiaxuan.util.jdbc.util.StringUtils;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 默认的更新
 */
public class DefaultUpdate extends SqlWhere implements Update {

    private List<String> updataColumn;

    @Override
    public Maker target(Class entity) {
        super.target(entity);
        this.updataColumn = entityTableRowMapper.getColumnNames();
        this.sqlValues = new ArrayList<>(updataColumn.size());
        return this;
    }

    @Override
    public boolean set(final Object entity, final boolean selective) {
        Assert.notNull(entity);
        //重置被更新字段列表
        this.updataColumn = new ArrayList<>();
        List<String> columnNames = entityTableRowMapper.getColumnNames();
        Map<String, Field> columnFieldMapper = entityTableRowMapper.getColumnFieldMapper();
        for (int i = 0; i < columnNames.size(); i++) {
            String columnName = columnNames.get(i);
            if (!columnFieldMapper.containsKey(columnName)) {
                continue;
            }
            Field field = columnFieldMapper.get(columnName);
            Object value = EntityUtils.getValue(entity, field);
            if (selective && value == null) {
                continue;
            }
            updataColumn.add(columnName);
            sqlValues.add(value);
        }
        return true;
    }

    @Override
    public String toSql() {
        Assert.isTrue(updataColumn.size() != 0, "没有要更新的字段");
        if (completeSql != null) {
            return completeSql;
        }
        sql.append("UPDATE ").append(tableName).append(StringUtils.SPACE);
        sql.append("SET ");
        for (int i = 0; i < updataColumn.size(); i++) {
            String column = updataColumn.get(i);
            if (i == 0) {
                sql.append(StringUtils.append(column, " = ? "));
            } else {
                sql.append(StringUtils.append(", ", column, " = ? "));
            }
        }
        if (sqlWhere.length() != 0) {
            sql.append("WHERE ");
        }
        sql.append(sqlWhere);
        completeSql = sql.toString();
        return completeSql;
    }

    @Override
    public Object[] getSqlValues() {
        if (completeSqlValues != null) {
            return completeSqlValues;
        }
        sqlValues.addAll(super.whereValues);
        completeSqlValues = sqlValues.toArray();
        return completeSqlValues;
    }

}
