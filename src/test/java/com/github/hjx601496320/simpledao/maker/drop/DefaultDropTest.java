package com.github.hjx601496320.simpledao.maker.drop;

import org.junit.Test;
import com.github.hjx601496320.simpledao.User;

import java.util.Arrays;
import java.util.Date;

public class DefaultDropTest {

    @Test
    public void makeSql() {
        User user = new User();
        user.setId(10);
        user.setCreateDate(new Date());
        user.setAge(20);
        user.setName("heiheihei");
        DefaultDrop drop = new DefaultDrop();
        drop.target(User.class);

        System.out.println(drop.makeSql());
        System.out.println(Arrays.toString(drop.makeSqlValue().toArray()));
    }

    @Test
    public void where() {
    }
}