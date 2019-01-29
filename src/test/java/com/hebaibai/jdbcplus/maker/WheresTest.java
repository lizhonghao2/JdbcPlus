package com.hebaibai.jdbcplus.maker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WheresTest {

    @Test
    public void name() {
        List<Where> wheres = Arrays.asList(
                Wheres.equal("name", "李叔叔"),
                Wheres.notEqual("status", 1),
                Wheres.in("age", new Integer[]{1, 2, 3, 4, 5}),
                Wheres.greater("age", 20, true)
        );
        List<Object> sqlValue = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        if (wheres.size() != 0) {
            sql.append("WHERE ");
            for (int i = 0; i < wheres.size(); i++) {
                Where where = wheres.get(i);
                if (i != 0) {
                    sql.append(where.getConnect());
                }
                String column = where.getColumn();
                String whereSql = where.getSql();
                sql.append(whereSql.replace(Where.PLACEHOLDER, column));
                //因为有些条件中的参数可能是有多个
                List<Object> values = where.getValues();
                for (int j = 0; j < values.size(); j++) {
                    sqlValue.add(values.get(j));
                }
            }
        }
        System.out.println(sql.toString());
        System.out.println(sqlValue.toString());
    }

    @Test
    public void equal() {
        Where where = Wheres.equal("name", "test");
        System.out.println(where.getSql());
        System.out.println(where.getColumn());
        System.out.println(Arrays.toString(where.getValues().toArray()));
    }

    @Test
    public void notEqual() {
        Where where = Wheres.notEqual("name", "test");
        System.out.println(where.getSql());
        System.out.println(where.getColumn());
        System.out.println(Arrays.toString(where.getValues().toArray()));
    }

    @Test
    public void isNotNull() {
        Where where = Wheres.isNotNull("name");
        System.out.println(where.getSql());
        System.out.println(where.getColumn());
        System.out.println(Arrays.toString(where.getValues().toArray()));
    }

    @Test
    public void isNull() {
        Where where = Wheres.isNull("name");
        System.out.println(where.getSql());
        System.out.println(where.getColumn());
        System.out.println(Arrays.toString(where.getValues().toArray()));
    }

    @Test
    public void greater() {
        Where where = Wheres.greater("age", "10", true);
        System.out.println(where.getSql());
        System.out.println(where.getColumn());
        System.out.println(Arrays.toString(where.getValues().toArray()));
    }

    @Test
    public void less() {
        Where where = Wheres.less("age", "10", false);
        System.out.println(where.getSql());
        System.out.println(where.getColumn());
        System.out.println(Arrays.toString(where.getValues().toArray()));
    }

    @Test
    public void like() {
        Where where = Wheres.like("age", "%10%");
        System.out.println(where.getSql());
        System.out.println(where.getColumn());
        System.out.println(Arrays.toString(where.getValues().toArray()));
    }

    @Test
    public void in() {
        Where where = Wheres.in("age", new Object[]{1, 2, 3, 4, 5, 5, 6, 7});
        System.out.println(where.getSql());
        System.out.println(where.getColumn());
        System.out.println(Arrays.toString(where.getValues().toArray()));
    }


    @Test
    public void replace() {
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            String sql = "123 123 123 #{NAME}";
            sql.replace("#{NAME}", "qweqwe");
        }
        System.out.println(System.currentTimeMillis() - currentTimeMillis);
    }

    @Test
    public void betweenAnd() {
        Where where = Wheres.betweenAnd("age", "123", "678");
        System.out.println(where.getSql());
        System.out.println(where.getColumn());
        System.out.println(Arrays.toString(where.getValues().toArray()));
    }
}