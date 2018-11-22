### simple-dao
```$xslt
简单的数据库操作.
支持mysql.
依赖spring-jdbc.
```
### 配置:
```$xslt
SimpleDao simpleDao = new SimpleDao();
simpleDao.setJdbcTemplate(jdbcTemplate);

//Entity 添加注解
import Column;
import ID;
import Table;

@Table("p_user")//与数据库表明对应
public class User {

    @ID // 标示为主键
    @Column("anInt") //标示一个字段, 对应数据库字段名称
    private String anInt;

    @Column("created_time")
    private Date createTime;

    @Column("status")
    private Integer status;
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
simpleDao.insert(User user);

/**********查询数据**********/
//查出所有的User
simpleDao.simpleDao.select(User.class)
//查出id为12的数据
simpleDao.selectById(User.class, "12");

//查出所有的user_name 等于 "123" 的数据,user_name 为数据库字段名称
simpleDao.selectBy(User.class, "user_name", "123");

//查出所有的name 等于 "123" 并且 age 等于"18" 的数据
//查询条件中的 参数可以是添加了 @Column 的属性名称 此处 user_name 等效与 name
simpleDao.selectBy(
                User.class,
                "name", "123",
                "age", "18"
        );

...其他的数据库操作请看单元测试...

```

