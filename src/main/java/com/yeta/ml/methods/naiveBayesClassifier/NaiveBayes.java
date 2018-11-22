package com.yeta.ml.methods.naiveBayesClassifier;

import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 朴素贝叶斯分类算法
 * Created by YETA666 on 2018/3/29 0029.
 */
@Component
public class NaiveBayes {

    /**
     * 获取类的先验概率
     * @param trainingSet
     * @return
     */
    public double[] getPriorProbabilities(List<Object[]> trainingSet) {
        //好瓜（是、否）的先验概率
        double[] priorProbabilities = new double[2];
        for (Object[] sample : trainingSet) {
            if ("是".equals(sample[sample.length - 1].toString())) {
                priorProbabilities[0]++;
            }else if ("否".equals(sample[sample.length - 1].toString())) {
                priorProbabilities[1]++;
            }
        }
        double total = priorProbabilities[0] + priorProbabilities[1];
        priorProbabilities[0] = priorProbabilities[0] / total;
        priorProbabilities[1] = priorProbabilities[1] / total;
        //priorProbabilities[0] = (priorProbabilities[0] + 1) / (total + priorProbabilities.length);      //平滑
        //priorProbabilities[1] = (priorProbabilities[1] + 1) / (total + priorProbabilities.length);      //平滑
        //返回
        return priorProbabilities;
    }

    /**
     * 获取每个属性的条件概率
     * 即训练模型
     * @param trainingSet
     * @param attrSet
     * @return
     */
    public List<List> getConditionalProbabilities(List<Object[]> trainingSet, List<String[]> attrSet) {
        //获取各属性的各取值
        List<Set> attrValues = getAttrValues(trainingSet, attrSet);
        //各属性的各取值的数量
        List<List> conditionalProbabilityList = new ArrayList();
        for (int i = 0; i < attrValues.size(); i++) {
            Object[] values = (attrValues.get(i)).toArray();
            List<double[]> valuesNum = new ArrayList<>();
            //初始化
            for (int j = 0; j < attrValues.get(i).toArray().length; j++) {
                valuesNum.add(new double[2]);
            }
            for (int j = 0; j < values.length; j++) {
                for (Object[] data : trainingSet) {
                    if (i < attrSet.get(0).length) {
                        //离散属性
                        if (values[j].equals(data[i])) {
                            if ("是".equals(data[data.length - 1].toString())) {
                                valuesNum.get(j)[0]++;
                            }else if ("否".equals(data[data.length - 1].toString())) {
                                valuesNum.get(j)[1]++;
                            }
                        }
                    }else if (i >= attrSet.get(0).length && i < attrSet.get(0).length + attrSet.get(1).length) {
                        //连续属性
                        if (values[j] == data[i]) {
                            valuesNum.get(j)[0] = (double)data[i];
                            valuesNum.get(j)[1] = (double)data[i];
                        }
                    }

                }
            }
            conditionalProbabilityList.add(valuesNum);
        }
        //正例、反例的数目
        double yesNum = 0, noNum = 0;
        //正例、反例下密度的均值、方差
        double yesDensityAverage = 0, noDensityAverage = 0, yesDensityVariance = 0, noDensityVariance = 0;
        //正例、反例下含糖率的均值、方差
        double yesSugarAverage = 0, noSugarAverage = 0, yesSugarVariance = 0, noSugarVariance = 0;
        for (Object[] sample : trainingSet) {
            if ("是".equals(sample[sample.length - 1].toString())) {
                yesNum++;
                yesDensityAverage += (double) sample[attrSet.get(0).length];
                yesSugarAverage += (double) sample[attrSet.get(0).length + 1];
            }else if ("否".equals(sample[sample.length - 1].toString())) {
                noNum++;
                noDensityAverage += (double) sample[attrSet.get(0).length];
                noSugarAverage += (double) sample[attrSet.get(0).length + 1];
            }
        }
        yesDensityAverage /= yesNum;
        noDensityAverage /= noNum;
        yesSugarAverage /= yesNum;
        noSugarAverage /= noNum;
        for (Object[] sample : trainingSet) {
            if ("是".equals(sample[sample.length - 1].toString())) {
                yesDensityVariance += ((double) sample[attrSet.get(0).length] - yesDensityAverage) * ((double) sample[attrSet.get(0).length] - yesDensityAverage);
                yesSugarVariance += ((double) sample[attrSet.get(0).length + 1] - yesSugarAverage) * ((double) sample[attrSet.get(0).length + 1] - yesSugarAverage);
            }else if ("否".equals(sample[sample.length - 1].toString())) {
                noDensityVariance += ((double) sample[attrSet.get(0).length] - noDensityAverage) * ((double) sample[attrSet.get(0).length] - noDensityAverage);
                noSugarVariance += ((double) sample[attrSet.get(0).length + 1] - noSugarAverage) * ((double) sample[attrSet.get(0).length + 1] - noSugarAverage);
            }
        }
        yesDensityVariance /= yesNum;
        noDensityVariance /= noNum;
        yesSugarVariance /= yesNum;
        noSugarVariance /= noNum;
        //属性的条件概率
        for (int i = 0; i < conditionalProbabilityList.size(); i++) {
            List<double[]> valuesNum = conditionalProbabilityList.get(i);
            for (int j = 0; j < valuesNum.size(); j++) {
                if (i < attrSet.get(0).length) {
                    //离散属性
                    valuesNum.get(j)[0] /= yesNum;
                    valuesNum.get(j)[1] /= noNum;
                    //valuesNum.get(j)[0] = (valuesNum.get(j)[0] + 1) / (yesNum +  valuesNum.size());      //平滑
                    //valuesNum.get(j)[1] = (valuesNum.get(j)[1] + 1) / (noNum + valuesNum.size());      //平滑
                }else if (i >= attrSet.get(0).length && i < attrSet.get(0).length + attrSet.get(1).length) {
                    //连续属性
                    if (i == attrSet.get(0).length) {
                        //密度
                        valuesNum.get(j)[0] = getNormalDistributionValue(valuesNum.get(j)[0], yesDensityAverage, yesDensityVariance);
                        valuesNum.get(j)[1] = getNormalDistributionValue(valuesNum.get(j)[1], noDensityAverage, noDensityVariance);
                    }else if (i == attrSet.get(0).length + 1) {
                        //含糖率
                        valuesNum.get(j)[0] = getNormalDistributionValue(valuesNum.get(j)[0], yesSugarAverage, yesSugarVariance);
                        valuesNum.get(j)[1] = getNormalDistributionValue(valuesNum.get(j)[1], noSugarAverage, noSugarVariance);
                    }
                }
            }
        }
        /*for (int i = 6; i < conditionalProbabilityList.size(); i++) {
            List<double[]> valuesNum = conditionalProbabilityList.get(i);
            System.out.print(valuesNum.size() + " ");
            for (int j = 0; j < valuesNum.size(); j++) {
                System.out.print(Arrays.toString(valuesNum.get(j)));
            }
            System.out.println();
        }*/
        return conditionalProbabilityList;
    }

    /**
     * 获取每个属性的取值
     * @param trainingSet
     * @param attrSet
     * @return
     */
    private List<Set> getAttrValues(List<Object[]> trainingSet, List<String[]> attrSet) {
        //各属性的取值
        List<Set> attrValues = new ArrayList();
        for (int i = 0; i < attrSet.get(0).length + attrSet.get(1).length; i++) {
            Set<Object> set = new HashSet();
            for (Object[] sample : trainingSet) {
                set.add(sample[i]);
            }
            attrValues.add(set);
        }
        return attrValues;
    }

    /**
     * 求正态分布概率密度函数
     * @param value
     * @param average
     * @param variance
     * @return
     */
    private double getNormalDistributionValue(double value, double average, double variance) {
        double left = 1 / (Math.sqrt(2 * Math.PI * variance));
        double right = Math.exp(-(Math.pow(value - average, 2) / (2 * variance)));
        return left * right;
    }

    /**
     * 测试模型
     * @param model
     * @param priorProbabilities
     * @param trainingSet
     * @param attrSet
     * @param testSet
     * @return
     */
    public double[] testModel(double[] priorProbabilities, List<List> model, List<Object[]> trainingSet, List<String[]> attrSet, Object[] testSet) {
        //获取各属性的各取值
        List attributeValueList = getAttrValues(trainingSet, attrSet);
        for (int i = 0; i < attributeValueList.size(); i++) {
            Object[] values = ((Set) attributeValueList.get(i)).toArray();
            List<double[]> valuesNum = model.get(i);
            for (int j = 0; j < values.length; j++) {
                if (i < attrSet.get(0).length) {
                    //离散属性
                    if (values[j].equals(testSet[i])) {
                        priorProbabilities[0] *= valuesNum.get(j)[0];
                        priorProbabilities[1] *= valuesNum.get(j)[1];
                        break;
                    }
                }else if (i >= attrSet.get(0).length && i < attrSet.get(0).length + attrSet.get(1).length) {
                    //连续属性
                    if ((double)values[j]==(double)testSet[i]) {
                        priorProbabilities[0] *= valuesNum.get(j)[0];
                        priorProbabilities[1] *= valuesNum.get(j)[1];
                        break;
                    }
                }
            }
        }
        return priorProbabilities;
    }
}
