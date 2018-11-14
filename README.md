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

//数据库操作
simpleDao.insert
simpleDao.selectBy
simpleDao.selectById
...其他的数据库操作...

```

