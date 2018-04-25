package top.hejiaxuan.util.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTest {

    SimpleDao simpleDao = null;
    JdbcTemplate jdbcTemplate = null;

    {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        try {
            comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf-8");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("00000");
        DataSource dataSource = comboPooledDataSource;
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        simpleDao = new SimpleDao();
        simpleDao.setDataSource(dataSource);
    }

}
