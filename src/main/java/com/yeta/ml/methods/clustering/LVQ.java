/*
package com.yeta.ml.methods.clustering;

import com.yeta.ml.dataSet.DataSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.StreamSupport;

*/
/**
 * 学习向量量化（LVQ）聚类
 * Created by YETA666 on 2018/4/2 0002.
 *//*

public class LVQ {

    */
/**
     * 迭代次数
     *//*

    private static int num = 0;

    */
/**
     *
     * @param dataSet
     * @param prototypeVectors
     * @param n
     *//*

    public static void lvq(Object[][] dataSet, Object[][] prototypeVectors, double n) {
        //打印原型向量
        System.out.println("第" + (num++) + "次迭代原型向量：");
        for (int i = 0; i < prototypeVectors.length; i++) {
            System.out.println(Arrays.toString(prototypeVectors[i]));
        }
        //随机选取样本
        Object[] sample;
        //样本索引
        int sampleIndex = 0;
        //为了和书上的例子一样
        if (num != 1) {
            //生成0-dataSet.length的随机数
            sampleIndex = (int)Math.round(Math.random() * (dataSet.length - 1));
        }
        sample = dataSet[sampleIndex];
        //计算该样本与当前原型向量的距离
        double[] distances = new double[prototypeVectors.length];
        for (int i = 0; i < prototypeVectors.length; i++) {
            for (int j = 0; j < prototypeVectors[i].length - 1; j++) {      //除去类型
                distances[i] += Math.pow(((double)prototypeVectors[i][j] - (double)sample[j]), 2);
            }
            distances[i] = Math.sqrt(distances[i]);
        }
        //获取与样本距离最近的原型向量
        double minDistance = distances[0];
        int minDistanceIndex = 0;
        for (int i = 0; i < distances.length; i++) {
            if (minDistance > distances[i]) {
                minDistance = distances[i];
                minDistanceIndex = i;
            }
        }
        //修改原型向量
        for (int i = 0; i < sample.length - 1; i++) {
            if (sample[sample.length - 1].equals(prototypeVectors[minDistanceIndex][prototypeVectors[minDistanceIndex].length - 1])) {
                prototypeVectors[minDistanceIndex][i] = (double) prototypeVectors[minDistanceIndex][i] + n * ((double) sample[i] - (double) prototypeVectors[minDistanceIndex][i]);
            }else {
                prototypeVectors[minDistanceIndex][i] = (double) prototypeVectors[minDistanceIndex][i] - n * ((double) sample[i] - (double) prototypeVectors[minDistanceIndex][i]);
            }
        }
        //迭代
        if (num < 201) {
            lvq(dataSet, prototypeVectors, n);
        }
    }

    public static void main(String[] args) {
       */
/* //初始化数据集
        DataSet dataSet = new DataSet();
        Object[][] dSet = dataSet.dataSet4;
        //原型向量初始化
        Object[][] prototypeVectors = {{0.556, 0.215, "是"}, {0.343, 0.099, "否"}, {0.359, 0.188, "否"}, {0.483, 0.312, "是"}, {0.725, 0.445, "是"}};
        //学习率
        double n = 0.1;
        //学习向量量化lvq迭代获取原型向量
        lvq(dSet, prototypeVectors, n);*//*

    }
}
*/
