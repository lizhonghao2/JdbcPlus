package com.github.hjx601496320.simpledao.jdbc;

import com.github.hjx601496320.simpledao.SimpleDao;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

public class JdbcTest {

    public SimpleDao simpleDao = null;
    JdbcTemplate jdbcTemplate = null;

    {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        try {
            comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        comboPooledDataSource.setJdbcUrl(
                "jdbc:mysql://127.0.0.1:3306/demo?useUnicode=true&characterEncoding=utf-8");
        comboPooledDataSource.setUser("debian-sys-maint");
        comboPooledDataSource.setPassword("cDjhZo5Tdul66r8E");
        DataSource dataSource = comboPooledDataSource;
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);

        simpleDao = new SimpleDao();
        simpleDao.setJdbcTemplate(jdbcTemplate);
    }

}
