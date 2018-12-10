package com.github.hjx601496320.jdbcplus.jdbc;

import com.github.hjx601496320.jdbcplus.JdbcTempltePlus;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

public class JdbcTest {

    public JdbcTempltePlus jdbcTempltePlus = null;

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
        jdbcTempltePlus = new JdbcTempltePlus();
        jdbcTempltePlus.setDataSource(dataSource);
    }

}
