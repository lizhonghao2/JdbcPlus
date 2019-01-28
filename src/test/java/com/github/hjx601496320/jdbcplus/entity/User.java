package com.github.hjx601496320.jdbcplus.entity;

import java.util.Date;
import java.math.BigDecimal;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 用户表
 * @author hejiaxuan
 */
@Table(name = "user")
public class User {

    /**big*/
    @Column(name = "big")
    private BigDecimal big;

    /**用户名*/
    @Column(name = "name")
    private String name;

    /**用户id*/
    @Id
    @Column(name = "id")
    private int id;

    /**年龄*/
    @Column(name = "age")
    private int age;

    /**mark*/
    @Column(name = "mark")
    private String mark;

    /**create_date*/
    @Column(name = "create_date")
    private Date createDate;

    /**status*/
    @Column(name = "status")
    private int status;

    public void setBig(BigDecimal big) {
        this.big = big;
    }

    public BigDecimal getBig() {
        return this.big;
    }

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
        sb.append("big:").append(big).append(";    ");
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
