package top.hejiaxuan.util.jdbc;

import org.junit.Test;
import top.hejiaxuan.entity.User;
import top.hejiaxuan.util.maker.Wheres;
import top.hejiaxuan.util.maker.insert.DefaultInsert;
import top.hejiaxuan.util.maker.insert.Insert;
import top.hejiaxuan.util.maker.query.DefaultQuery;
import top.hejiaxuan.util.maker.query.Query;
import top.hejiaxuan.util.maker.update.DefaultUpdate;
import top.hejiaxuan.util.maker.update.Update;

import java.util.Arrays;

public class SqlMakerTest extends JdbcTest {

    @Test
    public void query() {
        Query query = new DefaultQuery();
        query.target(User.class);
        query.limit(0, 10);
        query.orderBy("age", "DESC");
        query.where(
                Wheres.equal("id", "456").or(),
                Wheres.equal("id", "123").or(),
                Wheres.equal("id", "234").or(),
                Wheres.equal("age", "123")
        );
        String sql = query.toSql();
        System.out.println(Arrays.toString(query.getSqlValues()));
        System.out.println(sql);
    }

    @Test
    public void insert() {
        User user = new User();
        user.setAge(10);
        user.setId(123);
        user.setName("hjx");
        Insert insert = new DefaultInsert();
        insert.target(User.class);
        insert.insert(user);

        String sql = insert.toSql();
        System.out.println(Arrays.toString(insert.getSqlValues()));
        System.out.println(sql);
    }

    @Test
    public void update() {
        User user = new User();
        user.setAge(10);
        user.setId(234);
        user.setName("hjx");
        Update update = new DefaultUpdate();
        update.target(User.class);
        update.set(user, true);
        update.where(
                Wheres.equal("name", "hjx")
        );
        String sql = update.toSql();
        System.out.println(Arrays.toString(update.getSqlValues()));
        System.out.println(sql);
    }
}