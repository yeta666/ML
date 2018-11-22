package com.yeta.ml.model;

import javax.persistence.*;
import java.util.List;

/**
 * 西瓜数据集2.0
 * Created by YETA666 on 2018/4/12 0012.
 */
@Entity
@Table(name = "watermelonDataSet2")
public class WatermelonDataSet2 {

    /**
     * 编号
     */
    @Id
    @Column(name = "id")
    private int id;

    /**
     * 色泽
     */
    @Column(name = "colourAndLustre")
    private String colourAndLustre;

    /**
     * 根蒂
     */
    @Column(name = "rootAndBase")
    private String rootAndBase;

    /**
     * 敲声
     */
    @Column(name = "stroke")
    private String stroke;

    /**
     * 纹理
     */
    @Column(name = "venation")
    private String venation;

    /**
     * 脐部
     */
    @Column(name = "umbilicalRegion")
    private String umbilicalRegion;

    /**
     * 触感
     */
    @Column(name = "touch")
    private String touch;

    /**
     * 是否好瓜
     */
    @Column(name = "isGood")
    private String isGood;

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

    public WatermelonDataSet2() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColourAndLustre() {
        return colourAndLustre;
    }

    public void setColourAndLustre(String colourAndLustre) {
        this.colourAndLustre = colourAndLustre;
    }

    public String getRootAndBase() {
        return rootAndBase;
    }

    public void setRootAndBase(String rootAndBase) {
        this.rootAndBase = rootAndBase;
    }

    public String getStroke() {
        return stroke;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public String getVenation() {
        return venation;
    }

    public void setVenation(String venation) {
        this.venation = venation;
    }

    public String getUmbilicalRegion() {
        return umbilicalRegion;
    }

    public void setUmbilicalRegion(String umbilicalRegion) {
        this.umbilicalRegion = umbilicalRegion;
    }

    public String getTouch() {
        return touch;
    }

    public void setTouch(String touch) {
        this.touch = touch;
    }

    public String getIsGood() {
        return isGood;
    }

    public void setIsGood(String isGood) {
        this.isGood = isGood;
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
        return "WatermelonDataSet2{" +
                "id=" + id +
                ", colourAndLustre='" + colourAndLustre + '\'' +
                ", rootAndBase='" + rootAndBase + '\'' +
                ", stroke='" + stroke + '\'' +
                ", venation='" + venation + '\'' +
                ", umbilicalRegion='" + umbilicalRegion + '\'' +
                ", touch='" + touch + '\'' +
                ", isGood='" + isGood + '\'' +
                ", discreteAttr='" + discreteAttr + '\'' +
                ", continuousAttr='" + continuousAttr + '\'' +
                '}';
    }
}
