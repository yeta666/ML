/*
package com.yeta.ml.methods.clustering;

import com.yeta.ml.dataSet.DataSet;

import java.util.*;

*/
/**
 * 层次聚类：试图在不同层次对数据集进行划分，从而形成树形的聚类结构
 * AGNES算法：先将数据集中每个样本看作是一个初始聚类簇，然后每步找出距离最近的两个聚类簇进行合并，直到达到预设的聚类簇个数
 * 最小距离：两个簇的最近样本之间的距离
 * 最大距离：两个簇的最远样本之间的距离
 * 平均距离：两个簇所有样本之间的平均距离
 * Created by YETA666 on 2018/4/7 0007.
 *//*

public class AGNES {

    */
/**
     * 求两个聚类簇的最短距离
     * @param list1
     * @param list2
     * @return
     *//*

    public static double a(List<Object[]> list1, List<Object[]> list2) {
        List<Double> diss = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            Object[] list1Sample = list1.get(i);
            for (int j = 0; j < list2.size(); j++) {
                Object[] list2Sample = list2.get(j);
                double dis = 0;
                for (int k = 0; k < list1Sample.length - 1; k++) {
                    dis += Math.pow((double)list1Sample[k] - (double)list2Sample[k], 2);
                }
                dis = Math.sqrt(dis);
                diss.add(dis);
            }
        }
        //求最短距离
        double minDis = diss.get(0);
        for (int i = 0; i < diss.size(); i++) {
            if (minDis > diss.get(i)) {
                minDis = diss.get(i);
            }
        }
        return minDis;
    }

    */
/**
     * 求两个聚类簇的最长距离
     * @param list1
     * @param list2
     * @return
     *//*

    public static double b(List<Object[]> list1, List<Object[]> list2) {
        List<Double> diss = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            Object[] list1Sample = list1.get(i);
            for (int j = 0; j < list2.size(); j++) {
                Object[] list2Sample = list2.get(j);
                double dis = 0;
                for (int k = 0; k < list1Sample.length - 1; k++) {
                    dis += Math.pow((double)list1Sample[k] - (double)list2Sample[k], 2);
                }
                dis = Math.sqrt(dis);
                diss.add(dis);
            }
        }
        //求最长距离
        double maxDis = diss.get(0);
        for (int i = 0; i < diss.size(); i++) {
            if (maxDis < diss.get(i)) {
                maxDis = diss.get(i);
            }
        }
        return maxDis;
    }

    public static void c() {

    }

    */
/**
     * AGNES算法
     * @param dataSet
     * @param k
     * @return
     *//*

    public static Map d(Object[][] dataSet, int k) {
        //每个样本初始化为一个簇
        List<List> clusters = new ArrayList();
        List<List> clusterIndexs = new ArrayList<>();
        for (int i = 0; i < dataSet.length; i++) {
            List<Object[]> cluster = new ArrayList<>();
            cluster.add(Arrays.copyOf(dataSet[i], dataSet[i].length));
            clusters.add(cluster);
            List<Integer> clusterIndex = new ArrayList<>();
            clusterIndex.add(i + 1);
            clusterIndexs.add(clusterIndex);
        }
        //当前聚类簇数
        while (clusters.size() > k) {
            //打印
            e(clusters, clusterIndexs);
            //获取每个簇之间的距离
            List<Map> diss = new ArrayList<>();
            for (int i = 0; i < clusters.size(); i++) {
                for (int j = i + 1; j < clusters.size(); j++) {
                    Map<String, Object> dis = new HashMap<>();
                    dis.put("dis", b(clusters.get(i), clusters.get(j)));
                    dis.put("index1", i);
                    dis.put("index2", j);
                    diss.add(dis);
                }
            }
            //找出距离最近的两个簇
            double min = (double)diss.get(0).get("dis");
            int minIndex = 0;
            for (int i = 0; i < diss.size(); i++) {
                if (min > (double)diss.get(i).get("dis")) {
                    min = (double)diss.get(i).get("dis");
                    minIndex = i;
                }
            }
            Map<String, Object> dis = diss.get(minIndex);
            //合并这两个簇
            List<List> newClusters = new ArrayList<>();
            List<List> newClusterIndexs = new ArrayList<>();
            for (int i = 0; i < clusters.size(); i++) {
                int index1 = (int)dis.get("index1");
                int index2 = (int)dis.get("index2");
                if (i == index1 || i == index2) {
                    if (i == index1) {
                        List<Object[]> cluster1 = clusters.get(index1);
                        List<Object[]> cluster2 = clusters.get(index2);
                        List<Integer> clusterIndex1 = clusterIndexs.get(index1);
                        List<Integer> clusterIndex2 = clusterIndexs.get(index2);
                        for (int j = 0; j < cluster2.size(); j++) {
                            cluster1.add(cluster2.get(j));
                            clusterIndex1.add(clusterIndex2.get(j));
                        }
                        newClusters.add(cluster1);
                        newClusterIndexs.add(clusterIndex1);
                    }
                    continue;
                }
                newClusters.add(clusters.get(i));
                newClusterIndexs.add(clusterIndexs.get(i));
            }
            clusters = newClusters;
            clusterIndexs = newClusterIndexs;
        }
        //初始化返回结果
        Map<String, List> result = new HashMap<>();
        result.put("clusters", clusters);
        result.put("clusterIndexs", clusterIndexs);
        return result;
    }

    */
/**
     * 打印
     * @param clusters
     * @param clusterIndexs
     *//*

    public static void e(List<List> clusters, List<List> clusterIndexs) {
        */
/*for (int i = 0; i < clusters.size(); i++) {
            List<Object[]> cluster = clusters.get(i);
            System.out.print("聚类" + i + ": ");
            for (int j = 0; j < cluster.size(); j++) {
                Object[] sample = cluster.get(j);
                System.out.print(Arrays.toString(sample) + " ");
            }
            System.out.println();
        }*//*

        for (int i = 0; i < clusterIndexs.size(); i++) {
            List<Integer> clusterIndex = clusterIndexs.get(i);
            System.out.print("聚类" + i + ": ");
            for (int j = 0; j < clusterIndex.size(); j++) {
                System.out.print(clusterIndex.get(j) + " ");
            }
            System.out.println();
        }
        System.out.println("*************");
    }

    public static void main(String[] args) {
        */
/*//*
/初始化数据集
        DataSet dataSet = new DataSet();
        //聚类簇数
        int k = 1;
        //AGNES算法
        Map result = d(dataSet.dataSet4, k);*//*

    }
}
*/
