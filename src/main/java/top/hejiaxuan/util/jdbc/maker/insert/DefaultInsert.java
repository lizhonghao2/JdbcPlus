package top.hejiaxuan.util.jdbc.maker.insert;

import top.hejiaxuan.util.jdbc.maker.AbstractMaker;
import top.hejiaxuan.util.jdbc.maker.Maker;
import top.hejiaxuan.util.jdbc.util.EntityUtils;
import top.hejiaxuan.util.jdbc.util.StringUtils;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 默认的插入
 */
public class DefaultInsert extends AbstractMaker implements Insert {

    private List<String> insertColumn = new ArrayList<>();

    /**
     * 是否有值需要保存
     */
    private boolean hasValue = false;

    @Override
    public Maker target(Class entity) {
        super.target(entity);
        this.insertColumn = entityTableRowMapper.getColumnNames();
        return this;
    }

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
        for (int i = 0; i < insertColumn.size(); i++) {
            String columnName = insertColumn.get(i);
            if (columnFieldMapper.containsKey(columnName)) {
                Field field = columnFieldMapper.get(columnName);
                Object value = EntityUtils.getValue(entity, field);
                if (!hasValue && value != null) {
                    hasValue = true;
                }
                sqlValues.add(i, value);
            }
        }
        return true;
    }

    @Override
    public String toSql() {
        int size = insertColumn.size();
        sql.append("INSERT INTO ").append(tableName).append(StringUtils.SPACE);
        sql.append(StringUtils.append("( ", StringUtils.join(insertColumn, ", "), " ) "));
        sql.append("VALUES ( ");
        String[] repeat = StringUtils.repeat("?", size);
        sql.append(StringUtils.join(Arrays.asList(repeat), ", "));
        sql.append(" ) ");
        String sqlStr = sql.toString();
        return sqlStr;
    }

    @Override
    public Object[] getSqlValues() {
        Assert.isTrue(hasValue, "没有要保存的数据");
        return sqlValues.toArray();
    }
}
