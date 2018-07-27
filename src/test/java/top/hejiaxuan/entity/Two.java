package top.hejiaxuan.entity;

import java.lang.Boolean;
import java.util.Date;
import java.math.BigDecimal;
import top.hejiaxuan.util.jdbc.annotation.Column;
import top.hejiaxuan.util.jdbc.annotation.Table;

@Table(value="two")
public class Two {

    @Column(value="column_1")
    private float column1;

    @Column(value="column_11")
    private int column11;

    @Column(value="column_10")
    private Date column10;

    @Column(value="column_5")
    private String column5;

    @Column(value="column_4")
    private Boolean column4;

    @Column(value="column_3")
    private int column3;

    @Column(value="column_2")
    private double column2;

    @Column(value="column_9")
    private Date column9;

    @Column(value="column_8")
    private Date column8;

    @Column(value="column_7")
    private BigDecimal column7;

    @Column(value="column_6")
    private Date column6;

    public void setColumn1(float column1){
        this.column1 = column1;
    }

    public float getColumn1(){
        return this.column1;
    }
    public void setColumn11(int column11){
        this.column11 = column11;
    }

    public int getColumn11(){
        return this.column11;
    }
    public void setColumn10(Date column10){
        this.column10 = column10;
    }

    public Date getColumn10(){
        return this.column10;
    }
    public void setColumn5(String column5){
        this.column5 = column5;
    }

    public String getColumn5(){
        return this.column5;
    }
    public void setColumn4(Boolean column4){
        this.column4 = column4;
    }

    public Boolean getColumn4(){
        return this.column4;
    }
    public void setColumn3(int column3){
        this.column3 = column3;
    }

    public int getColumn3(){
        return this.column3;
    }
    public void setColumn2(double column2){
        this.column2 = column2;
    }

    public double getColumn2(){
        return this.column2;
    }
    public void setColumn9(Date column9){
        this.column9 = column9;
    }

    public Date getColumn9(){
        return this.column9;
    }
    public void setColumn8(Date column8){
        this.column8 = column8;
    }

    public Date getColumn8(){
        return this.column8;
    }
    public void setColumn7(BigDecimal column7){
        this.column7 = column7;
    }

    public BigDecimal getColumn7(){
        return this.column7;
    }
    public void setColumn6(Date column6){
        this.column6 = column6;
    }

    public Date getColumn6(){
        return this.column6;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("column1:").append(column1).append(";");
        sb.append("column11:").append(column11).append(";");
        sb.append("column10:").append(column10).append(";");
        sb.append("column5:").append(column5).append(";");
        sb.append("column4:").append(column4).append(";");
        sb.append("column3:").append(column3).append(";");
        sb.append("column2:").append(column2).append(";");
        sb.append("column9:").append(column9).append(";");
        sb.append("column8:").append(column8).append(";");
        sb.append("column7:").append(column7).append(";");
        sb.append("column6:").append(column6).append(";");
        return sb.toString();
    }
}
