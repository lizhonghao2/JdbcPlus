package com.github.hjx601496320.jdbcplus.jdbc;

import com.github.hjx601496320.jdbcplus.JdbcTempltePlus;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                "jdbc:mysql://127.0.0.1:3306/demo0?useUnicode=true&characterEncoding=utf-8");
        comboPooledDataSource.setUser("debian-sys-maint");
        comboPooledDataSource.setPassword("cDjhZo5Tdul66r8E");
        DataSource dataSource = comboPooledDataSource;

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);

        jdbcTempltePlus = new JdbcTempltePlus();
        jdbcTempltePlus.setJdbcTemplate(jdbcTemplate);
    }

}
