package com.github.hjx601496320.simpledao;

import org.junit.Test;
import com.github.hjx601496320.simpledao.jdbc.JdbcTest;
import com.github.hjx601496320.simpledao.maker.Wheres;
import com.github.hjx601496320.simpledao.maker.delete.DefaultDelete;
import com.github.hjx601496320.simpledao.maker.delete.Delete;
import com.github.hjx601496320.simpledao.maker.query.DefaultQuery;
import com.github.hjx601496320.simpledao.maker.query.Query;

import java.util.*;

public class SimpleDaoTest extends JdbcTest {

    @Test
    public void insertBatch() {
        long l = System.currentTimeMillis();
        List list = new ArrayList();
        for (int i = 0; i < 50000; i++) {
            User user = new User();
            user.setName(UUID.randomUUID().toString());
            user.setMark("mark");
            user.setAge(new Random().nextInt(100));
            user.setCreateDate(new Date());
            list.add(user);
        }
        Integer integer = simpleDao.insertBatch(User.class, list);
        System.out.println(integer);
        System.out.println(System.currentTimeMillis() - l);
    }

    @Test
    public void insert() {
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            User user = new User();
            user.setName(UUID.randomUUID().toString());
            user.setMark("mark");
            user.setAge(new Random().nextInt(100));
            user.setCreateDate(new Date());
            simpleDao.insert(user);
        }
        System.out.println(System.currentTimeMillis() - l);
    }

    @Test
    public void select() {
        List<User> users = simpleDao.select(User.class);
        System.out.println(users);
    }

    @Test
    public void selectById() {
        User user = simpleDao.selectById(User.class, "12");
        System.out.println(user);
    }

    @Test
    public void selectBy() {
        List<User> users = simpleDao.selectBy(User.class, "user_name", "123");
        System.out.println(users);
    }

    @Test
    public void selectBy1() {
        List<User> users = simpleDao.selectBy(
                User.class,
                "name", "123",
                "age", "18"
        );
        System.out.println(users);
    }

    @Test
    public void selectOneBy() {
        User user = simpleDao.selectOneBy(User.class, "age", "18");
        System.out.println(user);
    }

    @Test
    public void selectOneBy1() {
        User user = simpleDao.selectOneBy(
                User.class,
                "user_name", "123",
                "age", "18"
        );
        System.out.println(user);
    }

    @Test
    public void selectOneBy2() {
        Query query = new DefaultQuery();
        query.target(User.class);
        query.where(
                Wheres.equal("age", "18")
        );
        User user = simpleDao.selectOneBy(query);
        System.out.println(user);
    }

    @Test
    public void updateById() {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setMark("markUpdate");
            user.setId(new Random().nextInt(100));
            Integer integer = simpleDao.updateById(user);
            System.out.println(integer);
        }
    }

    @Test
    public void updateById1() {

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setMark("markUpdate");
            user.setId(new Random().nextInt(100));
            Integer integer = simpleDao.updateById(user, true);
            System.out.println(integer);
        }
    }

    @Test
    public void deleteById() {
        Integer deleteById = simpleDao.deleteById(User.class, "123");
        System.out.println(deleteById);
    }

    @Test
    public void deleteBy() {
        Delete delete = new DefaultDelete();
        delete.target(User.class);
        Integer deleteBy = simpleDao.deleteBy(delete);
        System.out.println(deleteBy);
    }

    @Test
    public void deleteBy1() {
        Integer deleteBy = simpleDao.deleteBy(
                User.class,
                "age", "19",
                "user_name", "hebaibai"
        );
        System.out.println(deleteBy);
    }
}