package top.hejiaxuan.util.jdbc.maker.create;

/**
 * 数据库字段和 java属性字段的映射
 */
public interface ColumnTypeByField {
    
    String getColumnType(Class fieldType);
    
}
