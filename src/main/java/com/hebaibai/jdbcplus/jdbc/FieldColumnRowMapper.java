package com.hebaibai.jdbcplus.jdbc;

import com.hebaibai.jdbcplus.util.ClassUtils;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;


/**
 * 属性和查询结果根据名称进行绑定的RowMapper
 */
public class FieldColumnRowMapper implements RowMapper {

    private Class objectClass;

    public FieldColumnRowMapper(Class objectClass) {
        this.objectClass = objectClass;
    }

    /**
     * sql 结果转换
     */
    private ColumnMapRowMapper columnMapRowMapper = new PlusColumnMapRowMapper();

    @Override
    public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Map<String, Object> resultMap = columnMapRowMapper.mapRow(resultSet, rowNum);
        Object instance = ClassUtils.getInstance(objectClass);
        Field[] fields = objectClass.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            Object value = resultMap.get(name);
            if (value == null) {
                continue;
            }
            ClassUtils.setValue(instance, field, value);
        }
        return instance;
    }
}
