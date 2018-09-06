package top.hejiaxuan.util.jdbc.util;

import top.hejiaxuan.util.jdbc.annotation.Column;
import top.hejiaxuan.util.jdbc.annotation.ID;
import top.hejiaxuan.util.jdbc.annotation.Table;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 实体类的工具类
 *
 * @author hjx
 */
public class EntityUtils {

    /**
     * 找到clz中包含ID注解的属性
     *
     * @param clz
     * @return
     */
    public static Field idField(Class<?> clz) {
        Assert.isTrue(hasAnnotation(clz, Table.class), "Class 不是一个Table");
        for (Field field : clz.getDeclaredFields()) {
            if (hasAnnotation(field, ID.class)) {
                Assert.isTrue(hasAnnotation(field, Column.class), "缺少注解: Column");
                return field;
            }
        }
        return null;
    }

    /**
     * 找到id的数据库字段名称
     *
     * @param clz
     * @return
     */
    public static String idColumnName(Class<?> clz) {
        Assert.isTrue(hasAnnotation(clz, Table.class), "Class 不是一个Table");
        Field idField = idField(clz);
        //entity中不存在@ID注解时，忽略。
        if (idField == null) {
            return null;
        }
        Column annotation = getAnnotation(idField, Column.class);
        return annotation.value();
    }

    public static String idFieldName(Class<?> clz) {
        Assert.isTrue(hasAnnotation(clz, Table.class), "Class 不是一个Table");
        Field idField = idField(clz);
        //entity中不存在@ID注解时，忽略。
        if (idField == null) {
            return null;
        }
        return idField.getName();
    }

    /**
     * 找到clz上Table注解中的值
     *
     * @param clz
     * @return
     */
    public static String tableName(Class<?> clz) {
        Assert.isTrue(hasAnnotation(clz, Table.class), "Class 不是一个Table");
        Table annotation = getAnnotation(clz, Table.class);
        return annotation.value();
    }

    public static List<String> fieldNames(Class<?> clz) {
        Assert.isTrue(hasAnnotation(clz, Table.class), "Class 不是一个Table");
        List<String> fieldNames = new ArrayList<>();
        for (Field field : clz.getDeclaredFields()) {
            if (hasAnnotation(field, Column.class)) {
                fieldNames.add(field.getName());
            }
        }
        return fieldNames;
    }

    /**
     * 获取Table的字段名与Entity属性名的映射Map
     *
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> Map<String, String> columnFieldNameMap(Class<T> clz) {
        Assert.isTrue(hasAnnotation(clz, Table.class));
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
            if (hasAnnotation(field, Column.class)) {
                Column annotation = getAnnotation(field, Column.class);
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

    public static <T extends Annotation> T getAnnotation(Class<?> clz, Class<T> annotationClass) {
        Assert.notNull(clz, "class 不能为 null");
        Assert.notNull(annotationClass, "annotationClass 不能为 null");
        return clz.getAnnotation(annotationClass);
    }

    public static boolean hasAnnotation(Class<?> clz, Class<? extends Annotation> annotationClass) {
        Assert.notNull(clz, "class 不能为 null");
        Assert.notNull(annotationClass, "annotationClass 不能为 null");
        return clz.isAnnotationPresent(annotationClass);
    }

    public static <T extends Annotation> T getAnnotation(Field field, Class<T> annotationClass) {
        Assert.notNull(field, "field 不能为 null");
        Assert.notNull(annotationClass, "annotationClass 不能为 null");
        return field.getAnnotation(annotationClass);
    }

    public static boolean hasAnnotation(Field field, Class<? extends Annotation> annotationClass) {
        Assert.notNull(field, "field 不能为 null");
        Assert.notNull(annotationClass, "annotationClass 不能为 null");
        return field.isAnnotationPresent(annotationClass);
    }
}
