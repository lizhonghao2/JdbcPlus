package top.hejiaxuan.util;

import org.junit.Test;
import top.hejiaxuan.entity.User;
import top.hejiaxuan.util.jdbc.JdbcTest;
import top.hejiaxuan.util.maker.Where;
import top.hejiaxuan.util.maker.Wheres;
import top.hejiaxuan.util.maker.delete.DefaultDelete;
import top.hejiaxuan.util.maker.delete.Delete;
import top.hejiaxuan.util.maker.query.DefaultQuery;
import top.hejiaxuan.util.maker.query.Query;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.*;

public class SimpleDaoTest extends JdbcTest {

    @Test
    public void insert() {
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setName(UUID.randomUUID().toString());
            user.setMark("mark");
            user.setAge(new Random().nextInt(100));
            user.setCreateDate(new Date());
            Integer integer = simpleDao.insert(user);
            System.out.println(integer);
        }
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
                "user_name", "123",
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