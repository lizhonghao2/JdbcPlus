package top.hejiaxuan.util.jdbc;

import org.junit.Test;
import top.hejiaxuan.entity.Two;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SimpleDaoTest extends JdbcTest {

    @Test
    public void select() {
        List<Two> twos = simpleDao.select(Two.class);
        for (Two two : twos) {
            System.out.println(two);
        }
    }

    @Test
    public void selectById() {
        simpleDao.selectById(Two.class, "123");
    }

    @Test
    public void selectBy() {
    }

    @Test
    public void selectBy1() {
    }

    @Test
    public void selectOneBy() {
    }

    @Test
    public void selectOneBy1() {
    }

    @Test
    public void selectOneBy2() {
    }

    @Test
    public void selectByWhere() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void deleteBy() {
    }

    @Test
    public void deleteBy1() {
    }

    @Test
    public void insert() {
        Two two = new Two();
        two.setColumn1(100.100f);
        two.setColumn2(100.123d);
        two.setColumn3(1);
        two.setColumn4(null);
        two.setColumn5("1231");
        two.setColumn6(new Date());
        two.setColumn7(new BigDecimal("1000"));
        simpleDao.insert(two);
    }

    @Test
    public void updateById() {
    }

    @Test
    public void updateById1() {
    }
}