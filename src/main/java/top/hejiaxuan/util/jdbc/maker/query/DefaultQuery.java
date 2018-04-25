package top.hejiaxuan.util.jdbc.maker.query;

import top.hejiaxuan.util.jdbc.maker.Maker;
import top.hejiaxuan.util.jdbc.maker.condition.SqlWhere;
import top.hejiaxuan.util.jdbc.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 默认的查询
 */
public class DefaultQuery extends SqlWhere implements Query {

    private List<String> selection;

    private String sqlLimit = StringUtils.BLANK;

    private String sqlOrderBy = StringUtils.BLANK;

    /**
     * 用于标示手动添加查询字段的次数
     */
    private boolean addSelection = false;

    @Override
    public Maker target(Class entity) {
        super.target(entity);
        this.selection = entityTableRowMapper.getColumnNames();
        return this;
    }

    @Override
    public Class<?> getEntity() {
        return entityClass;
    }

    @Override
    public boolean addSelection(String... columnName) {
        return addSelection(true, columnName);
    }

    @Override
    public boolean addSelection(boolean check, String... columnNames) {
        List<String> list = Arrays.asList(columnNames);
        if (!addSelection) {
            selection = new ArrayList<>();
            addSelection = true;
        }
        for (String name : list) {
            if (check) {
                checkColumn(name);
            }
            String columnName = getColumnName(name);
            if (selection.indexOf(columnName) == -1) {
                selection.add(columnName);
                continue;
            }
            throw new RuntimeException(StringUtils.append("字段 >", name, "< 已经存在! "));
        }
        return true;
    }

    @Override
    public Query orderBy(String[] orderColumns, String type) {
        List<String> columns = new ArrayList();
        for (String entityName : orderColumns) {
            String sqlColumnName = getColumnName(entityName);
            //如果遍历出的值不在表的字段中
            if (sqlColumnName == null || StringUtils.BLANK.equals(sqlColumnName)) {
                columns.add(entityName);
            } else {
                columns.add(sqlColumnName);
            }
        }
        this.sqlOrderBy = StringUtils.append("ORDER BY ", StringUtils.join(columns, StringUtils.COMMA), StringUtils.SPACE, type, StringUtils.SPACE);
        return this;
    }

    @Override
    public Query limit(int line, int num) {
        this.sqlLimit = StringUtils.append("LIMIT ", line, StringUtils.COMMA, num, StringUtils.SPACE);
        return this;
    }

    /**
     * @return
     */
    @Override
    public String toSql() {
        sql.append(StringUtils.append("SELECT ", StringUtils.join(selection, StringUtils.COMMA), StringUtils.SPACE));
        sql.append("FROM ").append(tableName).append(StringUtils.SPACE);
        if (sqlWhere.length() != 0) {
            sql.append("WHERE ");
        }
        sql.append(sqlWhere);
        sql.append(sqlOrderBy);
        sql.append(sqlLimit);
        String sqlStr = sql.toString();
        return sqlStr;
    }

    @Override
    public Object[] getSqlValues() {
        sqlValues.addAll(super.whereValues);
        return sqlValues.toArray();
    }

}
