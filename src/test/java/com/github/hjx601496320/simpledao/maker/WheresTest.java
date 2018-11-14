package com.github.hjx601496320.simpledao.maker;

import org.junit.Test;

import java.util.Arrays;

public class WheresTest {

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
}