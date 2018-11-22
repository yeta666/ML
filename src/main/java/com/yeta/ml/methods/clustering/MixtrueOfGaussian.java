/*
package com.yeta.ml.methods.clustering;

import com.yeta.ml.dataSet.DataSet;
import org.ujmp.core.DenseMatrix;
import org.ujmp.core.Matrix;
import java.util.*;

*/
/**
 * Created by YETA666 on 2018/4/2 0002.
 *//*

public class MixtrueOfGaussian {

    private static double[] lastMixtureCoefficients;

    private static List lastAverages;

    private static List lastVariances;

    */
/**
     * 初始化模型参数
     * @param dataSet
     * @param k
     *//*

    public static Map a(Object[][] dataSet, int k, int iterationNum) {
        //初始化混合系数
        double[] mixtureCoefficients = {1.0 / 3, 1.0 / 3, 1.0 / 3};
        //初始化均值向量
        List<Matrix> averages = new ArrayList<>();
        Matrix average1 = DenseMatrix.Factory.zeros(1, dataSet[0].length - 1);
        average1.setAsDouble(0.403, 0, 0);
        average1.setAsDouble(0.237, 0, 1);
        averages.add(average1);
        Matrix average2 = DenseMatrix.Factory.zeros(1, dataSet[0].length - 1);
        average2.setAsDouble(0.714, 0, 0);
        average2.setAsDouble(0.346, 0, 1);
        averages.add(average2);
        Matrix average3 = DenseMatrix.Factory.zeros(1, dataSet[0].length - 1);
        average3.setAsDouble(0.532, 0, 0);
        average3.setAsDouble(0.472, 0, 1);
        averages.add(average3);
        //初始化协方差矩阵
        List<Matrix> variances = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            Matrix variance = DenseMatrix.Factory.zeros(2, 2);
            variance.setAsDouble(0.1, 0, 0);
            variance.setAsDouble(0, 0, 1);
            variance.setAsDouble(0, 1, 0);
            variance.setAsDouble(0.1, 1, 1);
            variances.add(variance);
        }
        //开始迭代
        b(dataSet, mixtureCoefficients, averages, variances, k, iterationNum);
        //将数据集划分簇
        //初始化簇
        List clusters = new ArrayList();
        for (int i = 0; i < k; i++) {
            List cluster = new ArrayList();
            clusters.add(cluster);
        }
        //获取后验概率
        double[][] samplePs = c(dataSet, lastMixtureCoefficients, lastAverages, lastVariances, k);
        //划分
        for (int i = 0; i < samplePs.length; i++) {
            double[] sampleP = samplePs[i];
            double max = sampleP[0];
            int maxIndex = 0;
            for (int j = 0; j < sampleP.length; j++) {
                if (max < sampleP[j])  {
                    max = sampleP[j];
                    maxIndex = j;
                }
            }
            //将当前样本划分到maxIndex簇
            ((List)clusters.get(maxIndex)).add(dataSet[i]);
        }
        //封装返回结果
        Map result = new HashMap();
        result.put("clusters", clusters);
        List newAverages1 = new ArrayList();
        for (int i = 0; i < lastAverages.size(); i++) {
            Matrix average = (Matrix) lastAverages.get(i);
            List list = new ArrayList();
            list.add(average.getAsDouble(0, 0));
            list.add(average.getAsDouble(0, 1));
            newAverages1.add(list);
        }
        result.put("averages", newAverages1);
        return result;
    }

    */
/**
     * 迭代过程
     * @param dataSet
     * @param mixtureCoefficients
     * @param averages
     * @param variances
     * @param k
     * @param iterationNum
     * @return
     *//*

    public static void b(Object[][] dataSet, double[] mixtureCoefficients, List averages, List variances, int k, int iterationNum) {
        //每个样本的后验概率
        double[][] samplePs = c(dataSet, mixtureCoefficients, averages, variances, k);
        //新的混合系数
        double[] newMixtureCoefficients = new double[k];
        //新的均值向量
        List newAverages = new ArrayList();
        //新的协方差矩阵
        List newVariances = new ArrayList();
        //计算
        for (int i = 0; i < k; i++) {
            double total1 = 0;
            Matrix totalMatrix1 = DenseMatrix.Factory.zeros(1, dataSet[0].length - 1);
            for (int j = 0; j < dataSet.length; j++) {
                total1 += samplePs[j][i];
                //样本向量
                Matrix matrix1 = DenseMatrix.Factory.zeros(1, dataSet[0].length - 1);
                for (int p = 0; p < dataSet[j].length - 1; p++) {
                    matrix1.setAsDouble((double)dataSet[j][p], 0, p);
                }
                totalMatrix1 = totalMatrix1.plus(matrix1.times(samplePs[j][i]));
            }
            newMixtureCoefficients[i] = total1 / dataSet.length;
            newAverages.add(totalMatrix1.times(1 / total1));
        }
        //因为新的协方差矩阵需要基于新的均值向量来计算，所以重复过程
        for (int i = 0; i < k; i++) {
            double total1 = 0;
            Matrix totalMatrix2 = DenseMatrix.Factory.zeros(2, dataSet[0].length - 1);
            for (int j = 0; j < dataSet.length; j++) {
                total1 += samplePs[j][i];
                //样本向量
                Matrix matrix1 = DenseMatrix.Factory.zeros(1, dataSet[0].length - 1);
                for (int p = 0; p < dataSet[j].length - 1; p++) {
                    matrix1.setAsDouble((double)dataSet[j][p], 0, p);
                }
                Matrix matrix2 = matrix1.minus((Matrix) newAverages.get(i)).transpose().mtimes(matrix1.minus((Matrix) newAverages.get(i))).times(samplePs[j][i]);
                totalMatrix2 = totalMatrix2.plus(matrix2);
            }
            newVariances.add(totalMatrix2.times(1 / total1));
        }
        System.out.println("第" + (iterationNum--) + "次迭代：");
        System.out.println("新的混合系数：\n" + Arrays.toString(newMixtureCoefficients));
        System.out.println("新的均值向量：\n" + newAverages);
        System.out.println("新的协方差矩阵：\n" + newVariances);
        //迭代
        if (iterationNum > 0) {
            b(dataSet, newMixtureCoefficients, newAverages, newVariances, k, iterationNum);
        }else {
            lastMixtureCoefficients = newMixtureCoefficients;
            lastAverages = newAverages;
            lastVariances = newVariances;
        }
    }

    */
/**
     * 获取后验概率
     * @param dataSet
     * @param mixtureCoefficients
     * @param averages
     * @param variances
     * @param k
     * @return
     *//*

    public static double[][] c(Object[][] dataSet, double[] mixtureCoefficients, List averages, List variances, int k) {
        //每个样本的后验概率
        double[][] samplePs = new double[dataSet.length][k];
        //计算每个样本由各混合成分生成的后验概率
        for (int i = 0; i < dataSet.length; i++) {
            //第i个样本向量
            Matrix sample = DenseMatrix.Factory.zeros(1, dataSet[i].length - 1);
            for (int j = 0; j < dataSet[i].length - 1; j++) {
                sample.setAsDouble((double)dataSet[i][j], 0, j);
            }
            double total = 0;
            for (int j = 0; j < averages.size(); j++) {
                total += mixtureCoefficients[j] * d(sample, (Matrix) averages.get(j), (Matrix) variances.get(j));
            }
            //第i个样本的后验概率
            double[] sampleP = new double[averages.size()];
            for (int j = 0; j < averages.size(); j++) {
                sampleP[j] = mixtureCoefficients[j] * d(sample, (Matrix) averages.get(j), (Matrix) variances.get(j)) / total;
            }
            samplePs[i] = sampleP;
        }
        return samplePs;
    }

    */
/**
     * 计算高斯分布概率
     * @param sample
     * @param average
     * @param variance
     * @return
     *//*

    public static double d(Matrix sample, Matrix average, Matrix variance) {
        double x1 = 1 / (Math.pow(Math.sqrt(2 * Math.PI), sample.getSize()[0]) * Math.sqrt(variance.det()));
        Matrix matrix1 = sample.minus(average);
        Matrix matrix2 = variance.inv();
        Matrix matrix3 = sample.minus(average).transpose();
        Matrix matrix4 = matrix1.mtimes(matrix2).mtimes(matrix3);
        double x2 = Math.exp(-(1.0 / 2) * matrix4.det());
        return x1 * x2;
    }

    public static void main(String[] args) {
        */
/*//*
/初始化数据集
        DataSet dataSet = new DataSet();
        Object[][] dSet = dataSet.dataSet4;
        //初始化混合成分个数
        int k = 3;
        //初始化迭代次数
        int iterationNum = 5;
        //初始化模型参数
        Map result = a(dSet, k, iterationNum);
        List clusters = (List)result.get("clusters");
        //打印簇
        System.out.println("聚类结果：");
        for (int i = 0; i < clusters.size(); i++) {
            List cluster = (List) clusters.get(i);
            for (int j = 0; j < cluster.size(); j++) {
                System.out.print(Arrays.toString((Object[])cluster.get(j)) + " ");
            }
            System.out.println();
        }*//*

    }
}
*/
