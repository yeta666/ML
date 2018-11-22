package com.yeta.ml.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 西瓜数据集4.0
 * Created by YETA666 on 2018/4/12 0012.
 */
@Entity
@Table(name = "watermelonDataSet4")
public class WatermelonDataSet4 {

    /**
     * 编号
     */
    @Id
    @Column(name = "id")
    private int id;

    /**
     * 密度
     */
    @Column(name = "density")
    private double density;

    /**
     * 含糖率
     */
    @Column(name = "sugarContent")
    private double sugarContent;

    /**
     * 离散属性集
     */
    @Column(name = "discreteAttr")
    private String discreteAttr;

    /**
     * 连续属性集
     */
    @Column(name = "continuousAttr")
    private String continuousAttr;

    public WatermelonDataSet4() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public double getSugarContent() {
        return sugarContent;
    }

    public void setSugarContent(double sugarContent) {
        this.sugarContent = sugarContent;
    }

    public String getDiscreteAttr() {
        return discreteAttr;
    }

    public void setDiscreteAttr(String discreteAttr) {
        this.discreteAttr = discreteAttr;
    }

    public String getContinuousAttr() {
        return continuousAttr;
    }

    public void setContinuousAttr(String continuousAttr) {
        this.continuousAttr = continuousAttr;
    }

    @Override
    public String toString() {
        return "WatermelonDataSet4{" +
                "id=" + id +
                ", density=" + density +
                ", sugarContent=" + sugarContent +
                ", discreteAttr='" + discreteAttr + '\'' +
                ", continuousAttr='" + continuousAttr + '\'' +
                '}';
    }
}
