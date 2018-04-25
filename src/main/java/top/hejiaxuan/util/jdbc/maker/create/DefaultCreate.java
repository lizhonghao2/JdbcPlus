package top.hejiaxuan.util.jdbc.maker.create;

import top.hejiaxuan.util.jdbc.annotation.Column;
import top.hejiaxuan.util.jdbc.annotation.Table;
import top.hejiaxuan.util.jdbc.maker.AbstractMaker;
import top.hejiaxuan.util.jdbc.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 默认的建表sql生成器
 */
public class DefaultCreate extends AbstractMaker implements Create {

    private ColumnTypeByField columnTypeByField;

    public static final String PRIMARY_KEY = "NOT NULL PRIMARY KEY ";
    public static final String COMMENT = "COMMENT '%s' ";
    public static final String CREATE_TABLE = "CREATE TABLE ";

    @Override
    public void setColumnTypeByField(ColumnTypeByField columnTypeByField) {
        this.columnTypeByField = columnTypeByField;
    }

    @Override
    public String toSql() {
        if (columnTypeByField==null) {
            columnTypeByField = new ColumnTypeByFieldImpl();
        }
        Table table = super.entityClass.getAnnotation(Table.class);
        Map<String, Field> columnFieldMapper = entityTableRowMapper.getColumnFieldMapper();
        List<String> sql = new ArrayList<>();
        for (Map.Entry<String, Field> entry : columnFieldMapper.entrySet()) {
            String columnName = entry.getKey();
            Field field = entry.getValue();
            //数据库字段类型
            String columnType = columnTypeByField.getColumnType(field.getType());
            StringBuilder builder = new StringBuilder();
            //添加字段
            builder.append(columnName).append(StringUtils.SPACE).append(columnType).append(StringUtils.SPACE);
            //id
            if (columnName.equals(entityTableRowMapper.getIdName())) {
                builder.append(PRIMARY_KEY);
            }
            //注释
            Column column = field.getAnnotation(Column.class);
            builder.append(String.format(COMMENT, column.comment()));
            sql.add(builder.toString());
        }
        String createTableSql = StringUtils.append(
                CREATE_TABLE,
                entityTableRowMapper.getTableName(),
                " (",
                StringUtils.join(sql, StringUtils.COMMA),
                ") ",
                String.format(COMMENT, table.comment()),
                "; "
        );
        return createTableSql;
    }
}
