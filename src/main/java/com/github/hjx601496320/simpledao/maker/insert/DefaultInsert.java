package com.github.hjx601496320.simpledao.maker.insert;

import com.github.hjx601496320.simpledao.jdbc.util.EntityUtils;
import com.github.hjx601496320.simpledao.jdbc.util.StringUtils;
import com.github.hjx601496320.simpledao.maker.AbstractMaker;
import com.github.hjx601496320.simpledao.maker.SqlMaker;
import org.springframework.util.Assert;
import com.github.hjx601496320.simpledao.maker.Where;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 默认的插入
 *
 * @author hjx
 */
public class DefaultInsert extends AbstractMaker implements Insert {

    private List<String> insertColumns;

    private List<Object> insertColumnValues;

    /**
     * 插入数据
     *
     * @param entity
     * @return
     */
    @Override
    public boolean insert(Object entity) {
        Assert.notNull(entity);
        Map<String, Field> columnFieldMapper = entityTableRowMapper.getColumnFieldMapper();
        insertColumns = new ArrayList(columnFieldMapper.size());
        insertColumnValues = new ArrayList(columnFieldMapper.size());

        for (Map.Entry<String, Field> stringFieldEntry : columnFieldMapper.entrySet()) {
            Field field = stringFieldEntry.getValue();
            Object value = EntityUtils.getValue(entity, field);
            if (value == null) {
                continue;
            }
            insertColumns.add(stringFieldEntry.getKey());
            insertColumnValues.add(value);
        }
        return true;
    }

    @Override
    protected String makeSql() {
        StringBuilder builder = new StringBuilder();
        int size = insertColumns.size();
        builder.append("INSERT INTO ").append(getTableName()).append(StringUtils.SPACE);
        builder.append(StringUtils.append("( ", StringUtils.join(insertColumns, ", "), " ) "));
        builder.append("VALUES ( ");
        String[] repeat = StringUtils.repeat("?", size);
        builder.append(StringUtils.join(Arrays.asList(repeat), ", "));
        builder.append(" ) ");
        return builder.toString();
    }

    @Override
    protected List<Object> makeSqlValue() {
        if (insertColumnValues == null) {
            return new ArrayList<>();
        }
        return insertColumnValues;
    }

    @Override
    public SqlMaker where(Where... wheres) {
        throw new UnsupportedOperationException("不支持的操作");
    }

}
