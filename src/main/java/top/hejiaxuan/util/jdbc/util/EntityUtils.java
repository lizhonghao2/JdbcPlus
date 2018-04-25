package top.hejiaxuan.util.jdbc.util;

import top.hejiaxuan.util.jdbc.annotation.Column;
import top.hejiaxuan.util.jdbc.annotation.ID;
import top.hejiaxuan.util.jdbc.annotation.Table;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实体类的工具类
 */
public class EntityUtils {

    /**
     * 找到clz中包含ID注解的属性
     *
     * @param clz
     * @return
     */
    public static Field idField(Class<?> clz) {
        Assert.isTrue(isTable(clz), "Class 不是一个Table");
        for (Field field : clz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ID.class)) {
                return field;
            }
        }
        throw new RuntimeException("没有找到id");
    }

    /**
     * 找到id的数据库字段名称
     *
     * @param clz
     * @return
     */
    public static String idColumnName(Class<?> clz) {
        Assert.isTrue(isTable(clz), "Class 不是一个Table");
        Field idField = idField(clz);
        Assert.isTrue(idField.isAnnotationPresent(Column.class), "缺少注解: Column");
        Column annotation = idField.getAnnotation(Column.class);
        return annotation.value();
    }

    public static String idFieldName(Class<?> clz) {
        Assert.isTrue(isTable(clz), "Class 不是一个Table");
        Field idField = idField(clz);
        Assert.isTrue(idField.isAnnotationPresent(Column.class), "缺少注解: Column");
        return idField.getName();
    }

    /**
     * 找到clz上Table注解中的值
     *
     * @param clz
     * @return
     */
    public static String tableName(Class<?> clz) {
        Assert.isTrue(isTable(clz), "Class 不是一个Table");
        Table annotation = clz.getAnnotation(Table.class);
        return annotation.value();
    }

    /**
     * 找到一个Entity 的 字段名
     *
     * @param clz
     * @return
     */
    public static List<String> columnNames(Class<?> clz) {
        Assert.isTrue(isTable(clz), "Class 不是一个Table");
        List<String> columnNames = new ArrayList<>();
        for (Field field : clz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                columnNames.add(column.value());
            }
        }
        return columnNames;
    }

    public static List<String> fieldNames(Class<?> clz) {
        Assert.isTrue(isTable(clz), "Class 不是一个Table");
        List<String> fieldNames = new ArrayList<>();
        for (Field field : clz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                fieldNames.add(field.getName());
            }
        }
        return fieldNames;
    }

    /**
     * 是否是一个Table
     *
     * @param clz
     * @return
     */
    public static boolean isTable(Class<?> clz) {
        Assert.notNull(clz, "class 不能为 null");
        return clz.isAnnotationPresent(Table.class);
    }


    /**
     * 获取Table的字段名与Entity属性名的映射Map
     *
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> Map<String, String> columnFieldNameMap(Class<T> clz) {
        Assert.isTrue(isTable(clz));
        Map<String, Field> stringFieldMap = columnFieldMap(clz);
        Map<String, String> map = new HashMap<>(stringFieldMap.size());
        for (Map.Entry<String, Field> entry : stringFieldMap.entrySet()) {
            map.put(entry.getKey(), entry.getValue().getName());
        }
        return map;
    }

    /**
     * 获取Table的字段名与Entity属性的映射Map
     *
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> Map<String, Field> columnFieldMap(Class<T> clz) {
        Field[] declaredFields = clz.getDeclaredFields();
        Map<String, Field> map = new HashMap<>(declaredFields.length);
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column annotation = field.getAnnotation(Column.class);
                map.put(annotation.value(), field);
            }
        }
        return map;
    }

    /**
     * 设置值
     *
     * @param target
     * @param field
     * @param value
     * @return
     */
    public static boolean setValue(Object target, Field field, Object value) {
        Assert.notNull(target);
        Assert.notNull(field);
        field.setAccessible(true);
        try {
            field.set(target, value);
            return true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 取值
     *
     * @param target
     * @param field
     * @return
     */
    public static Object getValue(Object target, Field field) {
        Assert.notNull(target);
        Assert.notNull(field);
        field.setAccessible(true);
        try {
            return field.get(target);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
