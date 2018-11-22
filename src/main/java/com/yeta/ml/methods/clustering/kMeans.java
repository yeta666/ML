/*
package com.yeta.ml.methods.clustering;

import com.yeta.ml.dataSet.DataSet;

import java.util.*;

*/
/**
 * k-means聚类
 * Created by YETA666 on 2018/3/31 0031.
 *//*

public class kMeans {

    */
/**
     * 聚类好的簇（样本）
     *//*

    private static List lastClusters = new ArrayList();

    */
/**
     * 聚类好的簇（样本的索引）
     *//*

    private static List lastIndexClusters = new ArrayList();

    */
/**
     * 聚类次数
     *//*

    private static int clusterNum = 0;

    */
/**
     * 初始化均值向量的方法
     * @param dataSet
     * @return
     *//*

    public static void initialMeanVectors(Object[][] dataSet) {
        //簇的数量
        int k = 2;
        //从数据集中随机选择k个不重复样本作为初始均值向量
        //随机生成k个不重复的数
        Set indexSet = new HashSet();
        while (true) {
            if (indexSet.size() == k) {
                break;
            }
            indexSet.add((int) Math.round(Math.random() * (dataSet.length - 1)));
        }
        //初始化初始均值向量
        */
/*double[][] initialMeanVectors = new double[k][dataSet[0].length - 1];
        for (int i = 0; i < k; i++) {
            Object[] indexs = indexSet.toArray();
            initialMeanVectors[i] = dataSet[(int)indexs[i]];
        }*//*

        //double[][] initialMeanVectors = {{0.403, 0.237}, {0.343, 0.099}, {0.478, 0.437}};
        double[][] initialMeanVectors = {{0.403, 0.237}, {0.343, 0.099}};
        //聚类
        kMeansClustering(dataSet, k, initialMeanVectors);
    }

    */
/**
     * k-means聚类方案
     * @param dataSet
     * @param k
     * @param initialMeanVectors
     * @return
     *//*

    public static void kMeansClustering(Object[][] dataSet, int k, double[][] initialMeanVectors) {
        //初始化簇（样本）
        List clusters = new ArrayList();
        //初始化簇（样本的索引）
        List indexClusters = new ArrayList();
        for (int i = 0; i < k; i++) {
            List cluster1 = new ArrayList();
            clusters.add(cluster1);
            List cluster2 = new ArrayList();
            indexClusters.add(cluster2);
        }
        //计算数据集中每个样本和初始均值向量的距离（欧式距离）
        for (int i = 0; i < dataSet.length; i++) {
            double[] distances = new double[initialMeanVectors.length];
            int x = 0;
            for (int j = 0; j < initialMeanVectors.length; j++) {
                double d = 0;
                for (int p = 0; p < dataSet[i].length - 1; p++) {
                    d += Math.pow((double)dataSet[i][p] - initialMeanVectors[j][p], 2);
                }
                distances[x++] = Math.sqrt(d);
            }
            //获取最小距离
            double min = distances[0];
            int minIndex = 0;
            for (int j = 0; j < distances.length; j++) {
                if (min > distances[j]) {
                    min = distances[j];
                    minIndex = j;
                }
            }
            //将当前样本划分到第minIndex簇
            ((List)clusters.get(minIndex)).add(dataSet[i]);
            ((List)indexClusters.get(minIndex)).add(i + 1);
        }
        //初始化新的均值向量
        double[][] newMeanVectors = new double[initialMeanVectors.length][initialMeanVectors[0].length];
        //重新计算均值向量
        for (int i = 0; i < clusters.size(); i++) {
            List cluster = (List)clusters.get(i);
            for (int j = 0; j < cluster.size(); j++) {
                Object[] sample = (Object[])cluster.get(j);
                for (int p = 0; p < sample.length - 1; p++) {
                    newMeanVectors[i][p] += (double)sample[p];
                }
            }
        }
        for (int i = 0; i < newMeanVectors.length; i++) {
            for (int j = 0; j < newMeanVectors[i].length; j++) {
                newMeanVectors[i][j] /= ((List)clusters.get(i)).size();
            }
        }
        //判断新的均值向量是否与上一次的均值向量相等
        boolean flag = false;
        for (int i = 0; i < initialMeanVectors.length; i++) {
            for (int j = 0; j < initialMeanVectors[i].length; j++) {
                if (initialMeanVectors[i][j] != newMeanVectors[i][j]) {
                    flag = true;
                    break;
                }
            }
        }
        //输出聚类结果
        print(clusters, indexClusters);
        if (flag) {
            //如果新的均值向量与上一次的均值向量不等，则递归
            kMeansClustering(dataSet, k, newMeanVectors);
        }else {
            lastClusters = clusters;
            lastIndexClusters = indexClusters;
        }
    }

    */
/**
     * 打印聚类结果的方法
     * @param clusters
     * @param indexClusters
     *//*

    public static void print(List clusters, List indexClusters) {
        //输出聚类好的簇（样本的索引）
        System.out.println("第" + (++clusterNum) + "次聚类结果（样本的索引）：");
        for (int i = 0; i < indexClusters.size(); i++) {
            List indexCluster = (List)indexClusters.get(i);
            for (int j = 0; j < indexCluster.size(); j++) {
                System.out.print(indexCluster.get(j) + " ");
            }
            System.out.println();
        }
        */
/*System.out.println("第" + clusterNum + "次聚类结果（样本）：");
        for (int i = 0; i < clusters.size(); i++) {
            List cluster = (List)clusters.get(i);
            for (int j = 0; j < cluster.size(); j++) {
                double[] sample = (double[])cluster.get(j);
                System.out.print(Arrays.toString(sample));
            }
            System.out.println();
        }*//*

    }

    public static void check() {
        double error = 0;
        double total = 0;
        for (int i = 0; i < lastClusters.size(); i++) {
            double yes = 0, no = 0;
            List lastCluster = (List)lastClusters.get(i);
            for (int j = 0; j < lastCluster.size(); j++) {
                Object[] lastSample = (Object[])lastCluster.get(j);
                if (lastSample[lastSample.length - 1].toString().equals("是")) {
                    yes++;
                }else {
                    no++;
                }
            }
            total = total + yes + no;
            if (yes > no) {
                error += no;
            }else {
                error += yes;
            }
        }
        System.out.println("准确率为：" + (total - error) / total);
    }

    public static void main(String[] args) {
        */
/*//*
/初始化数据集
        DataSet dataSet = new DataSet();
        Object[][] dSet = dataSet.dataSet4;
        //初始化均值向量
        initialMeanVectors(dSet);
        //准确率验证
        check();*//*

    }
}
*/
