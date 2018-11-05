package top.hejiaxuan.util.jdbc.util;

import top.hejiaxuan.util.jdbc.annotation.Column;
import top.hejiaxuan.util.jdbc.annotation.ID;
import top.hejiaxuan.util.jdbc.annotation.Table;
import org.springframework.util.Assert;

import javax.persistence.Id;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 实体类的工具类
 *
 * @author hjx
 */
public class EntityUtils {
    static final String IS_NOT_TABLE = "Class 不是一个Table";
    static final String IS_NOT_COLUMN = "field 不是一个Column";
    static final String CLASS_NOT_NULL = "class 不能为 null";
    static final String FIELD_NOT_NULL = "field 不能为 null";
    static final String ANNOTATIONCLASS_NOT_NULL = "annotationClass 不能为 null";

    /**
     * 找到clz中包含ID注解的属性
     *
     * @param clz
     * @return
     */
    public static Field idField(Class<?> clz) {
        Assert.isTrue(isTable(clz), IS_NOT_TABLE);
        for (Field field : clz.getDeclaredFields()) {
            //同时是Id 和 Column
            if (isColumn(field) && isId(field)) {
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
        Assert.isTrue(isTable(clz), IS_NOT_TABLE);
        Field idField = idField(clz);
        //entity中不存在@ID注解时，忽略。
        if (idField == null) {
            return null;
        }
        return columnName(idField);
    }

    public static String idFieldName(Class<?> clz) {
        Assert.isTrue(isTable(clz), IS_NOT_TABLE);
        Field idField = idField(clz);
        //entity中不存在@ID注解时，忽略。
        if (idField == null) {
            return null;
        }
        return idField.getName();
    }

    public static List<String> fieldNames(Class<?> clz) {
        Assert.isTrue(isTable(clz), IS_NOT_TABLE);
        List<String> fieldNames = new ArrayList<>();
        for (Field field : clz.getDeclaredFields()) {
            if (isColumn(field)) {
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
        Assert.isTrue(isTable(clz), IS_NOT_TABLE);
        Map<String, Field> stringFieldMap = columnFieldMap(clz);
        Map<String, String> map = new HashMap<>(stringFieldMap.size());
        for (Map.Entry<String, Field> entry : stringFieldMap.entrySet()) {
            map.put(entry.getKey(), entry.getValue().getName());
        }
        return map;
    }

    /**
     * 获取Table的列名与Entity属性的映射Map
     *
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> Map<String, Field> columnFieldMap(Class<T> clz) {
        Field[] declaredFields = clz.getDeclaredFields();
        Map<String, Field> map = new HashMap<>(declaredFields.length);
        for (Field field : declaredFields) {
            if (isColumn(field)) {
                map.put(columnName(field), field);
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

    /**
     * 获取类上的注解
     *
     * @param clz
     * @param annotationClass
     * @param <T>
     * @return
     */
    public static <T extends Annotation> T getAnnotation(Class<?> clz, Class<T> annotationClass) {
        Assert.notNull(clz, CLASS_NOT_NULL);
        Assert.notNull(annotationClass, ANNOTATIONCLASS_NOT_NULL);
        return clz.getAnnotation(annotationClass);
    }

    /**
     * 获取属性上的注解
     *
     * @param field
     * @param annotationClass
     * @param <T>
     * @return
     */
    public static <T extends Annotation> T getAnnotation(Field field, Class<T> annotationClass) {
        Assert.notNull(field, FIELD_NOT_NULL);
        Assert.notNull(annotationClass, ANNOTATIONCLASS_NOT_NULL);
        return field.getAnnotation(annotationClass);
    }

    /**
     * 找到clz上Table注解中的值
     * JPA支持
     *
     * @param clz
     * @return
     */
    public static String tableName(Class<?> clz) {
        Assert.isTrue(isTable(clz), IS_NOT_TABLE);
        Table annotation = getAnnotation(clz, Table.class);
        if (annotation == null) {
            return getAnnotation(clz, javax.persistence.Table.class).name();
        }
        return annotation.value();
    }


    /**
     * 获取列的名称
     * JPA支持
     *
     * @param field
     * @return
     */
    static String columnName(Field field) {
        Assert.isTrue(isColumn(field), IS_NOT_COLUMN);
        Column annotation = getAnnotation(field, Column.class);
        if (annotation == null) {
            return getAnnotation(field, javax.persistence.Column.class).name();
        }
        return annotation.value();
    }

    /**
     * 是否是一个Table
     *
     * @param aClass
     * @return
     */
    static boolean isTable(Class aClass) {
        if (hasAnnotation(aClass, Table.class)) {
            return true;
        } else if (hasAnnotation(aClass, javax.persistence.Table.class)) {
            return true;
        }
        return false;
    }

    /**
     * 是否是一个列
     *
     * @param field
     * @return
     */
    static boolean isColumn(Field field) {
        if (hasAnnotation(field, Column.class)) {
            return true;
        } else if (hasAnnotation(field, javax.persistence.Column.class)) {
            return true;
        }
        return false;
    }


    /**
     * 是否是一个id
     *
     * @param field
     * @return
     */
    static boolean isId(Field field) {
        if (hasAnnotation(field, ID.class)) {
            return true;
        } else if (hasAnnotation(field, javax.persistence.Id.class)) {
            return true;
        }
        return false;
    }

    public static boolean hasAnnotation(Class<?> clz, Class<? extends Annotation> annotationClass) {
        Assert.notNull(clz, CLASS_NOT_NULL);
        Assert.notNull(annotationClass, ANNOTATIONCLASS_NOT_NULL);
        return clz.isAnnotationPresent(annotationClass);
    }

    public static boolean hasAnnotation(Field field, Class<? extends Annotation> annotationClass) {
        Assert.notNull(field, FIELD_NOT_NULL);
        Assert.notNull(annotationClass, ANNOTATIONCLASS_NOT_NULL);
        return field.isAnnotationPresent(annotationClass);
    }
}
