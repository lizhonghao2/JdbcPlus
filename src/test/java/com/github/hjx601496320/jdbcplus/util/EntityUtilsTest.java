package com.github.hjx601496320.jdbcplus.util;

import com.github.hjx601496320.jdbcplus.entity.User;
import org.junit.Test;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;

public class EntityUtilsTest {

    @Test
    public void getClassAnnotation() {
        Table annotation = EntityUtils.getAnnotation(User.class, Table.class);
        System.out.println(annotation.name());
    }

    @Test
    public void getFieldAnnotation() throws NoSuchFieldException {
        Class userClass = User.class;
        Field field = userClass.getDeclaredField("createDate");
        Column annotation = EntityUtils.getAnnotation(field, Column.class);
        System.out.println(annotation.name());
    }
}