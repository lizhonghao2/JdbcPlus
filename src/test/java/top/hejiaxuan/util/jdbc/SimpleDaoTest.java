package top.hejiaxuan.util.jdbc;

import org.junit.Test;
import top.hejiaxuan.entity.User;
import top.hejiaxuan.util.maker.Wheres;
import top.hejiaxuan.util.maker.delete.DefaultDelete;
import top.hejiaxuan.util.maker.delete.Delete;
import top.hejiaxuan.util.maker.update.DefaultUpdate;
import top.hejiaxuan.util.maker.update.Update;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class SimpleDaoTest extends JdbcTest {
    @Test
    public void insert() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(UUID.randomUUID().toString());
        user.setAge(new Random().nextInt(100));
        user.setMark(new Random().nextInt(100));
        System.out.println(simpleDao.insert(user));
    }

    @Test
    public void deleteBy() {
        Delete delete = new DefaultDelete();
        delete.target(User.class);
        delete.where(
                Wheres.isNotNull("id")
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
            Update update = new DefaultUpdate();
            update.target(User.class);
            update.set(user, false);
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