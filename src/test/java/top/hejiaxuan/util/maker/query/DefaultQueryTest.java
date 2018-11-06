package top.hejiaxuan.util.maker.query;

import org.junit.Test;
import top.hejiaxuan.entity.User;
import top.hejiaxuan.util.maker.Where;
import top.hejiaxuan.util.maker.Wheres;

import java.util.Arrays;

import static org.junit.Assert.*;

public class DefaultQueryTest {

    @Test
    public void addSelection() {
        DefaultQuery query = new DefaultQuery();
        query.target(User.class);
        query.addSelection("user_name", "age", "mark");
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
        DefaultQuery query = new DefaultQuery();
        query.target(User.class);
        query.orderBy("user_name,age", "DESC");
        System.out.println(query.makeSql());
        System.out.println(Arrays.toString(query.makeSqlValue().toArray()));
    }

    @Test
    public void limit() {
        DefaultQuery query = new DefaultQuery();
        query.target(User.class);
        query.limit(0, 10);
        query.orderBy("user_name,age", "DESC");
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