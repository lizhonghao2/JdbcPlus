# JdbcPlus

```$xslt
简单的数据库操作.
支持mysql.
依赖spring-jdbc.
```
## 配置:

### 配置JdbcPlus

```java
 JdbcPlus jdbcPlus = new JdbcPlus();
 JdbcPlus.setJdbcTemplate(jdbcTemplate);
```

### 在class上添加注解

```java
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

// 表示一张表 value为数据库表名
@Table(name="user")
public class User {

    @Column(name="name")
    private String name;

    // 表示一个id（一个class限制一个id）
    @Id 
    // 表示一个字段 value为数据库字段名
    @Column(name="id") 
    private int id;

    @Column(name="age")
    private int age;

    @Column(name="mark")
    private String mark;

    @Column(name="create_date")
    private Date createDate;

    @Column(name="status")
    private int status;
    ...
    get...
    set...
}

```

## 基本操作

操作对象 **User.class**。

**User.class**需要添加注解**@Table("user")**，注解值为数据库表名称。

属性上添加**@Column("anInt")** 注解，表示为一个数据库字段，注解值为数据库字段名称。

表示**id**的属性上需要添加**@Id**注解，每个表只支持一个**id**。

### 插入数据

```java
//插入单条记录
jdbcPlus.insert(User user);
//插入多条数据(拼接sql形式)
jdbcPlus.insertBatch(User.class, list);
```

### 查询数据

```java
//查出所有的User
jdbcPlus.simpleDao.select(User.class)
//查出id为12的数据
jdbcPlus.selectById(User.class, "12");
//查出所有的user_name 等于 "123" 的数据,user_name 为数据库字段名称
jdbcPlus.selectBy(User.class, "user_name", "123");
//查出所有的name 等于 "123" 并且 age 等于"18" 的数据
//查询条件中的 参数可以是添加了 @Column 的属性名称 此处 user_name 等效与 name
jdbcPlus.selectBy( User.class, "name", "123", "age", "18" );
```

### 其他操作

请看单元测试





