package com.github.hjx601496320.simpledao.jdbc;

import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

/**
 * 数据库与实体类的映射关系
 *
 * @param <T>
 * @author hjx
 */
public class EntityTableRowMapper<T> implements RowMapper<T> {

    /**
     * id的字段名称
     */
    private String idName = null;

    /**
     * table对应的class
     */
    private Class<T> tableClass = null;

    /**
     * 对应的数据库名称
     */
    private String tableName = null;

    /**
     * 表中所有的字段
     */
    private Set<String> columnNames = null;

    /**
     * 表中所有的字段对应的属性名称
     */
    private Set<String> fieldNames = null;

    /**
     * 属性名称和数据库字段名的映射
     */
    private Map<String, String> fieldNameColumnMapper = null;

    /**
     * 数据库字段名和class属性的映射
     */
    private Map<String, Field> columnFieldMapper = null;

    /**
     * sql 结果转换
     */
    private ColumnMapRowMapper columnMapRowMapper = new SimpleDaoColumnMapRowMapper();

    /**
     * 把数据库查询的结果与对象进行转换
     *
     * @param resultSet
     * @param rowNum
     * @return
     * @throws SQLException
     */
    @Override
    public T mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Map<String, Object> resultMap = columnMapRowMapper.mapRow(resultSet, rowNum);
        T instance = getInstance(tableClass);
        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            //数据库字段名
            String key = entry.getKey();
            if (!columnFieldMapper.containsKey(key)) {
                continue;
            }
            Field declaredField = columnFieldMapper.get(key);
            if (declaredField == null) {
                continue;
            }
            //数据库字段值
            Object value = entry.getValue();
            setFieldValue(instance, declaredField, value);
        }
        return instance;
    }

    /**
     * 实例化泛型对象
     *
     * @param aClass
     * @return
     */
    private T getInstance(Class<T> aClass) {
        Assert.notNull(aClass);
        try {
            T t = aClass.newInstance();
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("实例化对象失败");
    }

    /**
     * 给 entityClass 中的 field 设置值
     *
     * @param t
     * @param field
     * @param value
     * @return
     */
    boolean setFieldValue(T t, Field field, Object value) {
        field.setAccessible(true);
        try {
            if (value != null) {
                field.set(t, value);
                return true;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Map<String, Field> getColumnFieldMapper() {
        return columnFieldMapper;
    }

    public void setColumnFieldMapper(Map<String, Field> columnFieldMapper) {
        this.columnFieldMapper = columnFieldMapper;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getIdName() {
        Assert.notNull(idName, "@ID 不存在,无法找到id！");
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public Class<T> getTableClass() {
        return tableClass;
    }

    public void setTableClass(Class tableClass) {
        this.tableClass = tableClass;
    }

    public Set<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(Set<String> columnNames) {
        this.columnNames = columnNames;
    }

    public Set<String> getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(Set<String> fieldNames) {
        this.fieldNames = fieldNames;
    }

    public Map<String, String> getFieldNameColumnMapper() {
        return fieldNameColumnMapper;
    }

    public void setFieldNameColumnMapper(Map<String, String> fieldNameColumnMapper) {
        this.fieldNameColumnMapper = fieldNameColumnMapper;
    }
}
