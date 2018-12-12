package com.github.hjx601496320.jdbcplus;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Table(name = "user")
public class User {

    @Column(name = "name")
    private String name;

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "age")
    private int age;

    @Column(name = "mark")
    private String mark;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "status")
    private int status;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return this.mark;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[");
        sb.append("name:").append(name).append(";    ");
        sb.append("id:").append(id).append(";    ");
        sb.append("age:").append(age).append(";    ");
        sb.append("mark:").append(mark).append(";    ");
        sb.append("createDate:").append(createDate).append(";    ");
        sb.append("status:").append(status).append(";    ");
        sb.append("]");
        return sb.toString();
    }
}
