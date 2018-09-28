package top.hejiaxuan.util.jdbc;

import top.hejiaxuan.util.jdbc.annotation.Table;
import top.hejiaxuan.util.jdbc.util.EntityUtils;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.*;

/**
 * mapper 的 工厂类
 *
 * @author hjx
 */
public class EntityMapperFactory {

    /**
     * mapper 的 缓存
     */
    private static Map<Class, EntityTableRowMapper> entityTableRowMapperMap = new HashMap<>();

    /**
     * 获取mapper
     *
     * @param clz
     * @return
     */
    public static EntityTableRowMapper getMapper(Class clz) {
        Assert.notNull(clz);
        Assert.isTrue(EntityUtils.hasAnnotation(clz, Table.class), "class 缺少 Table 注解");
        EntityTableRowMapper<?> entityTableRowMapper = null;
        if (!entityTableRowMapperMap.containsKey(clz)) {
            synchronized (EntityMapperFactory.class) {
                if (entityTableRowMapperMap.containsKey(clz)) return entityTableRowMapperMap.get(clz);
                entityTableRowMapper = new EntityTableRowMapper();
                entityTableRowMapper.setTableClass(clz);
                entityTableRowMapper.setTableName(EntityUtils.tableName(clz));
                entityTableRowMapper.setIdName(EntityUtils.idColumnName(clz));
                Map<String, Field> columnFieldMap = EntityUtils.columnFieldMap(clz);
                entityTableRowMapper.setColumnFieldMapper(columnFieldMap);

                Map<String, String> fieldNameColumnMapper = new HashMap<>(columnFieldMap.size());
                Set<String> columnNames = new HashSet<>(columnFieldMap.size());
                Set<String> fieldNames = new HashSet<>();
                for (Map.Entry<String, Field> entry : columnFieldMap.entrySet()) {
                    String columnName = entry.getKey();
                    Field field = entry.getValue();
                    String fieldName = field.getName();
                    fieldNameColumnMapper.put(fieldName, columnName);
                    fieldNames.add(fieldName);
                    columnNames.add(columnName);
                }
                entityTableRowMapper.setColumnNames(columnNames);
                entityTableRowMapper.setFieldNameColumnMapper(fieldNameColumnMapper);
                entityTableRowMapper.setFieldNames(fieldNames);
                entityTableRowMapperMap.put(clz, entityTableRowMapper);
            }
        }
        return entityTableRowMapperMap.get(clz);
    }

}
