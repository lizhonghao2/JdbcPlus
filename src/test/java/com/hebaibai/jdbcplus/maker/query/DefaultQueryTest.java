package com.hebaibai.jdbcplus.maker.query;

import com.hebaibai.jdbcplus.maker.Wheres;
import org.junit.Test;
import com.hebaibai.jdbcplus.entity.User;

import java.util.Arrays;

public class DefaultQueryTest {

    @Test
    public void addSelection() {
        DefaultQuery query = new DefaultQuery();
        query.target(User.class);
        query.where(
                Wheres.equal("id", 12),
                Wheres.notEqual("name", 12),
                Wheres.greater("age", 12, true)
        );
        System.out.println(query.makeSql());
        System.out.println(Arrays.toString(query.makeSqlValue().toArray()));
    }

    @Test
    public void addSelectionNoCheck() {
        DefaultQuery query = new DefaultQuery();
        query.target(User.class);
        query.addSelection(false, "123", "234", "567");
        System.out.println(query.makeSql());
        System.out.println(Arrays.toString(query.makeSqlValue().toArray()));
    }

    @Test
    public void orderBy() {
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            DefaultQuery query = new DefaultQuery();
            query.target(User.class);
            query.orderBy("name ,  age", "DESC");
            String sql = query.makeSql();
            System.out.println(sql);
            System.out.println(Arrays.toString(query.makeSqlValue().toArray()));
        }
        System.out.println(System.currentTimeMillis() - currentTimeMillis);
    }

    @Test
    public void limit() {
        DefaultQuery query = new DefaultQuery();
        query.target(User.class);
        query.limit(0, 10);
        query.orderBy("name,age", "DESC");
        System.out.println(query.makeSql());
        System.out.println(Arrays.toString(query.makeSqlValue().toArray()));

    }

    @Test
    public void where() {
        DefaultQuery query = new DefaultQuery();
        query.target(User.class);
        query.limit(0, 10);
        query.orderBy("user_name,age", "DESC");
        query.where(
                Wheres.equal("user_name", "hebaiabi"),
                Wheres.less("age", 13, true).or()
        );
        System.out.println(query.makeSql());
        System.out.println(Arrays.toString(query.makeSqlValue().toArray()));
    }
}