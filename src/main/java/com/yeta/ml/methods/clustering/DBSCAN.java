/*
package com.yeta.ml.methods.clustering;

import com.yeta.ml.dataSet.DataSet;
import java.util.*;

*/
/**
 * DBSCAN算法
 * 密度聚类算法：假设聚类结构能够通过样本分布的紧密程度确定
 * 从样本密度的角度来考察样本之间的可连续性，并基于可连接样本不断扩展聚类簇以获得最终的聚类结果
 * Created by YETA666 on 2018/4/4 0004.
 *//*

public class DBSCAN {

    */
/**
     *
     * @param dataSet
     * @param c
     * @param minPts
     * @return
     *//*

    public static List a(Object[][] dataSet, double c, int minPts) {
        //找出各样本的c领域
        List<List> neighbourhoods = new ArrayList();
        for (int i = 0; i < dataSet.length; i++) {
            List<Object[]> neighbourhood = new ArrayList();
            for (int j = 0; j < dataSet.length; j++) {
                //计算样本i和样本j的欧氏距离，样本i与样本j可以为同一个样本
                double dis = 0;
                for (int k = 0; k < dataSet[i].length - 1; k++) {
                    dis += Math.pow((double)dataSet[i][k] - (double)dataSet[j][k], 2);
                }
                dis = Math.sqrt(dis);
                if (dis <= c) {
                    //样本j在样本i的c领域中
                    neighbourhood.add(dataSet[j]);
                }
            }
            neighbourhoods.add(neighbourhood);
        }
        //确定核心对象集
        List<Object[]> coreObjects = new ArrayList();
        List<Integer> coreObjectIndexs = new ArrayList();
        for (int i = 0; i < neighbourhoods.size(); i++) {
            List neighbourhood = neighbourhoods.get(i);
            if (neighbourhood.size() >= minPts) {
                //样本i是一个核心对象
                coreObjects.add(dataSet[i]);
                coreObjectIndexs.add(i + 1);
            }
        }
        //初始化聚类簇数
        int k = 0;
        //初始化为访问样本集合
        List<Object[]> notVisitSamples = new ArrayList();
        for (int i = 0; i < dataSet.length; i++) {
            notVisitSamples.add(Arrays.copyOf(dataSet[i], dataSet[i].length));
        }
        //聚类簇
        List<List> clusters = new ArrayList<>();
        //循环
        while (coreObjects.size() > 0) {
            //记录当前未访问样本集合
            List<Object[]> oldNotVisitSamples = new ArrayList();
            for (int i = 0; i < notVisitSamples.size(); i++) {
                oldNotVisitSamples.add(Arrays.copyOf(notVisitSamples.get(i), notVisitSamples.get(i).length));
            }
            //随机选取一个核心对象样本
            Object[] seed = coreObjects.get((int)Math.round(Math.random() * (coreObjects.size() - 1)));
            //初始化队列
            Queue<Object[]> queue = new ArrayDeque();
            queue.add(seed);
            //未访问样本集合中去掉选取的核心对象样本
            List tempList = new ArrayList();
            tempList.add(queue.peek());     //取队头元素
            notVisitSamples = e(notVisitSamples, tempList);
            //循环
            while (queue.size() > 0) {
                //取出队列中的首个样本
                Object[] sample = queue.poll();     //队头出队
                //如果这个样本是核心对象样本
                List neighbourhood = neighbourhoods.get(b(dataSet, sample));
                if (neighbourhood.size() >= minPts) {
                    //求这个样本的邻域与未访问样本集合的交集
                    List union = c(neighbourhood, notVisitSamples);
                    //将交集中的样本放入队列
                    for (int i = 0; i < union.size(); i++) {
                        Object[] sample2 = (Object[]) union.get(i);
                        queue.add(Arrays.copyOf(sample2, sample2.length));
                    }
                    //未访问样本集合中去掉交集
                    notVisitSamples = e(notVisitSamples, union);
                }
            }
            k = k + 1;
            //生成聚类簇
            List<Object[]> cluster = e(oldNotVisitSamples, notVisitSamples);
            clusters.add(cluster);
            //核心对象集中去掉已经聚类的簇
            coreObjects = e(coreObjects, cluster);
        }
        return clusters;
    }

    */
/**
     * 根据样本得到样本在数据集中的索引
     * @param dataSet
     * @param sample
     * @return
     *//*

    public static int b(Object[][] dataSet, Object[] sample) {
        int index = -1;
        for (int i = 0; i < dataSet.length; i++) {
            Object[] data = dataSet[i];
            boolean flag = d(data, sample);
            if (flag) {
                index = i;
            }
        }
        return index;
    }

    */
/**
     * 求两个集合的交集
     * @param list1
     * @param list2
     * @return
     *//*

    public static List c(List<Object[]> list1, List<Object[]> list2) {
        List <Object[]> list3 = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            Object[] list1Sample = list1.get(i);
            for (int j = 0; j < list2.size(); j++) {
                Object[] list2Sample = list2.get(j);
                boolean flag = d(list1Sample, list2Sample);
                if (flag) {
                    list3.add(Arrays.copyOf(list1Sample, list1Sample.length));
                }
            }
        }
        return list3;
    }

    */
/**
     * 判断两个样本是否是同一个样本
     * @param sample1
     * @param sample2
     * @return
     *//*

    public static boolean d(Object[] sample1, Object[] sample2) {
        boolean flag = true;
        for (int k = 0; k < sample1.length; k++) {
            if (sample1[k] instanceof Double) {
                if ((double)sample1[k] != (double)sample2[k]) {
                    flag = false;
                    break;
                }
            }else if (sample1[k] instanceof String) {
                if (!sample1[k].toString().equals(sample2[k].toString())) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    */
/**
     * 求两个集合的差：list1-list2
     * @param list1
     * @param list2
     * @return
     *//*

    public static List e(List<Object[]> list1, List<Object[]> list2) {
        List list3 = new ArrayList();
        for (int i = 0; i < list1.size(); i++) {
            Object[] list1Sample = list1.get(i);
            int num = 0;
            for (int j = 0; j < list2.size(); j++) {
                Object[] list2Sample = list2.get(j);
                boolean flag = d(list1Sample, list2Sample);
                if (!flag) {
                    num++;
                }
            }
            if (num == list2.size()) {
                list3.add(Arrays.copyOf(list1Sample, list1Sample.length));
            }
        }
        return list3;
    }

    public static void main(String[] args) {
        */
/*//*
/初始化数据集
        DataSet dataSet = new DataSet();
        //初始化邻域参数
        double c = 0.11;
        int minPts = 5;
        //聚类
        List<List> clusters = a(dataSet.dataSet4, c, minPts);
        //输出聚类结果
        System.out.println("聚类结果：");
        for (int i = 0; i < clusters.size(); i++) {
            List cluster = clusters.get(i);
            for (int j = 0; j < cluster.size(); j++) {
                Object[] sample = (Object[])cluster.get(j);
                //System.out.print(Arrays.toString(sample) + " " + (b(dataSet, sample) + 1) + " ");
                System.out.print((b(dataSet.dataSet4, sample) + 1) + " ");
            }
            System.out.println();
        }*//*

    }
}
*/
