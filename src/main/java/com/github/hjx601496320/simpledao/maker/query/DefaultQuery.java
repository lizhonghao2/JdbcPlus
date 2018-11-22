package com.github.hjx601496320.simpledao.maker.query;

import com.github.hjx601496320.simpledao.jdbc.EntityTableRowMapper;
import com.github.hjx601496320.simpledao.jdbc.util.StringUtils;
import com.github.hjx601496320.simpledao.maker.AbstractMaker;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 默认的查询
 *
 * @author hjx
 */
public class DefaultQuery extends AbstractMaker implements Query {

    private String sqlLimit = StringUtils.BLANK;
    private String sqlOrderBy = StringUtils.BLANK;
    //要查询作为结果的字段
    private List<String> selection = new ArrayList<>();
    //sql
    private StringBuilder sql = new StringBuilder();

    @Override
    public boolean addSelection(String... columnName) {
        return addSelection(true, columnName);
    }

    @Override
    public boolean addSelection(boolean check, String... columnNames) {
        List<String> list = Arrays.asList(columnNames);
        for (String columnName : list) {
            if (check) {
                checkColumn(columnName);
                selection.add(getColumnName(columnName));
            }
            selection.add(columnName);
        }
        return true;
    }

    @Override
    public Query orderBy(String orderBy, String type) {
        String[] split = orderBy.split(",");
        for (int i = 0; i < split.length; i++) {
            split[i] = getColumnName(split[i].trim());
        }
        sqlOrderBy = StringUtils.append("ORDER BY ", StringUtils.join(Arrays.asList(split), StringUtils.COMMA),
                StringUtils.SPACE + type + StringUtils.SPACE);
        return this;
    }

    @Override
    public Query limit(int line, int num) {
        this.sqlLimit = StringUtils.append("LIMIT ", line, StringUtils.COMMA + num + StringUtils.SPACE);
        return this;
    }

    /**
     * @return
     */
    @Override
    protected String makeSql() {
        EntityTableRowMapper entityTableRowMapper = getEntityTableRowMapper();
        if (selection.size() == 0) {
            selection = new ArrayList<>(entityTableRowMapper.getColumnNames());
        }
        sql.append(
                MessageFormat.format("SELECT {0} FROM {1} ",
                        StringUtils.join(selection, StringUtils.COMMA), getTableName())
        );
        sql.append(sqlWhere());
        sql.append(sqlOrderBy);
        sql.append(sqlLimit);
        return sql.toString();
    }

    @Override
    protected List<Object> makeSqlValue() {
        return Arrays.asList(sqlValues);
    }

}
