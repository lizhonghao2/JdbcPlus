package top.hejiaxuan.util.maker.update;

import org.junit.Test;
import top.hejiaxuan.entity.User;
import top.hejiaxuan.util.maker.Wheres;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

public class DefaultUpdateTest {

    @Test
    public void makeSql() {
        User user = new User();
        user.setId(10);
        user.setCreateDate(new Date());
        user.setAge(20);
        user.setName("heiheihei");
        DefaultUpdate defaultUpdate = new DefaultUpdate();
        defaultUpdate.target(User.class);
        defaultUpdate.set(user, true);

        System.out.println(defaultUpdate.makeSql());
        System.out.println(Arrays.toString(defaultUpdate.makeSqlValue().toArray()));
    }

    @Test
    public void where() {
        User user = new User();
        user.setId(11);
        user.setCreateDate(new Date());
        user.setAge(21);
        user.setName("heiheihei2");
        DefaultUpdate defaultUpdate = new DefaultUpdate();
        defaultUpdate.target(User.class);
        defaultUpdate.set(user, true);
        defaultUpdate.where(Wheres.equal("age", 11));

        System.out.println(defaultUpdate.makeSql());
        System.out.println(Arrays.toString(defaultUpdate.makeSqlValue().toArray()));
    }
}