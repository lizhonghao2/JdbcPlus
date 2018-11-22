package com.github.hjx601496320.simpledao;

import java.util.Date;
import com.github.hjx601496320.simpledao.jdbc.annotation.ID;
import com.github.hjx601496320.simpledao.jdbc.annotation.Column;
import com.github.hjx601496320.simpledao.jdbc.annotation.Table;

@Table(value="user")
public class User {

    @Column(value="name")
    private String name;

    @ID
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

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }
    public void setAge(int age){
        this.age = age;
    }

    public int getAge(){
        return this.age;
    }
    public void setMark(String mark){
        this.mark = mark;
    }

    public String getMark(){
        return this.mark;
    }
    public void setCreateDate(Date createDate){
        this.createDate = createDate;
    }

    public Date getCreateDate(){
        return this.createDate;
    }
    public void setStatus(int status){
        this.status = status;
    }

    public int getStatus(){
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
