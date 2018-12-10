### jdbcPlus
```$xslt
简单的数据库操作.
支持mysql.
依赖spring-jdbc.
```
### 配置:
```$xslt
继承自 JdbcTemplate, 不需要额外配置

import java.util.Date;
import com.github.hjx601496320.jdbcplus.annotation.Id;
import com.github.hjx601496320.jdbcplus.annotation.Column;
import com.github.hjx601496320.jdbcplus.annotation.Table;

// 表示一张表 value为数据库表名
@Table(value="user")
public class User {

    @Column(value="name")
    private String name;

    // 表示一个id（一个class限制一个id）
    @Id 
    // 表示一个字段 value为数据库字段名
    @Column(value="id") 
    private int id;

    @Column(value="age")
    private int age;

    @Column(value="mark")
    private String mark;

    @Column(value="create_date")
    private Date createDate;

    @Column(value="status")
    private int status;
    ...
    get...
    set...
}

/*
            数据库操作
操作对象 User.class, User需要添加注解@Table("user")注解值为数据库表名称
属性上添加@Column("anInt") 表示为一个数据库字段, 注解值为数据库字段名称
表示id 的属性上需要添加@ID 注解
每个表只支持一个id字段
/*

/**********插入数据**********/
//插入单条记录
jdbcTempltePlus.insert(User user);
//插入多条数据(拼接sql形式)
jdbcTempltePlus.insertBatch(User.class, list);

/**********查询数据**********/
//查出所有的User
jdbcTempltePlus.simpleDao.select(User.class)
//查出id为12的数据
jdbcTempltePlus.selectById(User.class, "12");

//查出所有的user_name 等于 "123" 的数据,user_name 为数据库字段名称
jdbcTempltePlus.selectBy(User.class, "user_name", "123");

//查出所有的name 等于 "123" 并且 age 等于"18" 的数据
//查询条件中的 参数可以是添加了 @Column 的属性名称 此处 user_name 等效与 name
jdbcTempltePlus.selectBy(
                User.class,
                "name", "123",
                "age", "18"
        );

...其他的数据库操作请看单元测试...

```

