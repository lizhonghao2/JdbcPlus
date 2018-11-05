package top.hejiaxuan.util.jdbc;

import org.junit.Test;
import top.hejiaxuan.entity.User;
import top.hejiaxuan.util.maker.Wheres;
import top.hejiaxuan.util.maker.delete.DefaultDelete;
import top.hejiaxuan.util.maker.delete.Delete;
import top.hejiaxuan.util.maker.update.DefaultUpdate;
import top.hejiaxuan.util.maker.update.Update;

import java.util.*;

public class SimpleDaoTest extends JdbcTest {
    @Test
    public void insert() {
        User user = new User();
        user.setId(1000);
        user.setName(UUID.randomUUID().toString());
        user.setAge(10);
        user.setMark("100");
        user.setCreateDate(new Date());
        System.out.println(simpleDao.insert(user));
    }

    @Test
    public void deleteBy() {
        Delete delete = new DefaultDelete();
        delete.target(User.class);
        delete.where(
                Wheres.equal("id", "1000")
        );
        System.out.println(delete.toSql());
        System.out.println(Arrays.toString(delete.getSqlValues()));
        Integer integer = simpleDao.deleteBy(delete);
        System.out.println(integer);
    }

    @Test
    public void updateBy() {
        List<User> select = simpleDao.select(User.class);
        for (User user : select) {
            user.setId(12);
            Update update = new DefaultUpdate();
            update.target(User.class);
            update.set(user, false);
            System.out.println(update.toSql());
            System.out.println(Arrays.toString(update.getSqlValues()));
            int i = simpleDao.updateBy(update);
            System.out.println(i);
        }
    }

    @Test
    public void selectBy() {
        List<User> select = simpleDao.select(User.class);
        for (User user : select) {
            System.out.println(user);
        }
    }

}