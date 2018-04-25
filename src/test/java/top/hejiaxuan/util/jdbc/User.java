package top.hejiaxuan.util.jdbc;

import top.hejiaxuan.util.jdbc.annotation.Column;
import top.hejiaxuan.util.jdbc.annotation.ID;
import top.hejiaxuan.util.jdbc.annotation.Table;

import java.io.Serializable;
import java.util.Date;

@Table(value = "p_user", comment = "用户表23333")
public class User implements Serializable {

    @ID
    @Column(value = "id", comment = "id")
    private int anInt;

    @Column(value = "aDouble", comment = "双精度的")
    private double aDouble;

    @Column(value = "aFloat", comment = "TEST")
    private float aFloat;

    @Column(value = "string", comment = "hahah23423")
    private String string;

    @Column(value = "date", comment = "dfsdfsdf")
    private Date date123;

    @Column("status")
    private Integer status;

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }

    public double getaDouble() {
        return aDouble;
    }

    public void setaDouble(double aDouble) {
        this.aDouble = aDouble;
    }

    public float getaFloat() {
        return aFloat;
    }

    public void setaFloat(float aFloat) {
        this.aFloat = aFloat;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Date getDate123() {
        return date123;
    }

    public void setDate123(Date date123) {
        this.date123 = date123;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("            \"anInt\"=").append(anInt);
        sb.append(",             \"aDouble\"=").append(aDouble);
        sb.append(",             \"aFloat\"=").append(aFloat);
        sb.append(",             \"string\"=\"").append(string).append('\"');
        sb.append(",             \"date\"=\"").append(date123).append('\"');
        sb.append(",             \"status\"=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
