package top.hejiaxuan.entity;

import top.hejiaxuan.util.jdbc.annotation.Column;
import top.hejiaxuan.util.jdbc.annotation.ID;
import top.hejiaxuan.util.jdbc.annotation.Table;

@Table(value = "user")
public class User {

    @Column(value = "name")
    private String name;

    @ID
    @Column(value = "id")
    private String id;

    @Column(value = "age")
    private int age;

    @Column(value = "mark")
    private int mark;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getMark() {
        return this.mark;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[");
        sb.append("name:").append(name).append(";    ");
        sb.append("id:").append(id).append(";    ");
        sb.append("age:").append(age).append(";    ");
        sb.append("mark:").append(mark).append(";    ");
        sb.append("]");
        return sb.toString();
    }
}
