package top.hejiaxuan.entity;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "user")
public class User {

    @Column(name = "user_name")
    private String name;

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "age")
    private Integer age;

    @Column(name = "mark")
    private String mark;

    @Column(name = "create_date")
    private Date createDate;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return this.mark;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                ", mark='" + mark + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
