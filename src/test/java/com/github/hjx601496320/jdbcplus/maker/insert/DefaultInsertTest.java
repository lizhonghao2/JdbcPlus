package com.github.hjx601496320.jdbcplus.maker.insert;

import org.junit.Test;
import com.github.hjx601496320.jdbcplus.User;

import java.util.Arrays;
import java.util.Date;

public class DefaultInsertTest {

    @Test
    public void makeSql() {
        User user = new User();
        user.setId(10);
        user.setCreateDate(new Date());
        user.setAge(20);
        user.setMark("ceshi");
        user.setName("heiheihei");
        DefaultInsert insert = new DefaultInsert();
        insert.target(User.class);
        insert.insert(user);
        insert.insert(user);
        System.out.println(insert.makeSql());
        System.out.println(Arrays.toString(insert.makeSqlValue().toArray()));
    }

}