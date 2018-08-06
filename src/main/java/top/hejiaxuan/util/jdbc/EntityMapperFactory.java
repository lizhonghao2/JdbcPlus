package top.hejiaxuan.util.jdbc;

import top.hejiaxuan.util.jdbc.util.EntityUtils;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mapper 的 工厂类
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
        Assert.isTrue(EntityUtils.isTable(clz), "class 缺少 Table 注解");
        EntityTableRowMapper<?> entityTableRowMapper = null;
        if (!entityTableRowMapperMap.containsKey(clz)) {
            synchronized (EntityMapperFactory.class) {
                if (entityTableRowMapperMap.containsKey(clz)) {
                    return entityTableRowMapperMap.get(clz);
                }
                entityTableRowMapper = new EntityTableRowMapper();
                Assert.notNull(clz);
                entityTableRowMapper.setTableClass(clz);
                entityTableRowMapper.setTableName(EntityUtils.tableName(clz));
                entityTableRowMapper.setIdName(EntityUtils.idColumnName(clz));
                Map<String, Field> columnFieldMap = EntityUtils.columnFieldMap(clz);
                entityTableRowMapper.setColumnFieldMapper(columnFieldMap);
                entityTableRowMapper.setColumnNames(EntityUtils.columnNames(clz));
                Map<String, String> fieldNameColumnMapper = new HashMap<>(columnFieldMap.size());
                List<String> fieldNames = new ArrayList<>();
                for (Map.Entry<String, Field> entry : columnFieldMap.entrySet()) {
                    String column = entry.getKey();
                    Field field = entry.getValue();
                    String fieldName = field.getName();
                    fieldNameColumnMapper.put(fieldName, column);
                    fieldNames.add(fieldName);
                }
                entityTableRowMapper.setFieldNameColumnMapper(fieldNameColumnMapper);
                entityTableRowMapper.setFieldNames(fieldNames);
                entityTableRowMapperMap.put(clz, entityTableRowMapper);
            }
        }
        return entityTableRowMapperMap.get(clz);
    }

}
