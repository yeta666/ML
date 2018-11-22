package com.yeta.ml.methods.decisionTreeClassifier.common;

import com.yeta.ml.methods.decisionTreeClassifier.ContinuousValueHandling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

/**
 * 决策树中一些通用方法
 * Created by YETA666 on 2018/4/12 0012.
 */
@Component
public class Methods {

    @Autowired
    private ContinuousValueHandling continuousValueHandling;

    /**
     * 根据属性索引获取该属性的取值
     * @param trainingSet
     * @param attrIndex
     * @return
     */
    public String[] getAttrValuesByAttrIndex(List<Object[]> trainingSet, List<String[]> attrSet, int attrIndex) {
        int disLen = attrSet.get(0).length;
        if (disLen == 0 || (disLen != 0 && attrIndex >= disLen)) {      //连续属性
            return new String[]{"是", "否"};
        }else {     //离散属性
            Set<String> set = new HashSet<>();
            for (Object[] sample : trainingSet) {
                if (!"".equals(sample[attrIndex].toString())) {
                    set.add(sample[attrIndex].toString());
                }
            }
            String[] attrValues = new String[set.toArray().length];
            int i = 0;
            for (Object str : set.toArray()) {
                attrValues[i++] = str.toString();
            }
            return attrValues;
        }
    }

    /**
     * 根据属性取值获取该属性的索引
     * 连续属性的取值为：是/否
     * 离散属性的取值为：真实取值
     * @param trainingSet
     * @param attrValue
     * @return
     */
    public int getAttrIndexByAttrValue(List<Object[]> trainingSet, List<String[]> attrSet, String attrValue, String parentNodeNameAbout) {
        if ("是".equals(attrValue) || "否".equals(attrValue)) {     //连续属性
            for (int j = 0; j < attrSet.get(1).length; j++) {
                if (attrSet.get(1)[j].equals(parentNodeNameAbout)) {
                    return j;
                }
            }
        }else {     //离散属性
            for (Object[] sample : trainingSet) {
                for (int j = 0; j < attrSet.get(0).length; j++) {
                    if (sample[j].toString().equals(attrValue)) {
                        return j;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * 计算信息熵
     * @return
     */
    public double getEntropy(double yes, double no) {
        double yesEntropy, noEntropy;
        if (yes == 0) {
            yesEntropy = 0;
        }else {
            yesEntropy = (yes*1.0 / (yes+no)) * ((Math.log10(yes*1.0 / (yes+no)) / Math.log10(2)));
        }
        if (no == 0) {
            noEntropy = 0;
        }else {
            noEntropy = (no*1.0 / (yes+no)) * (Math.log10(no*1.0 / (yes+no)) / Math.log10(2));
        }
        return -(yesEntropy + noEntropy);
    }

    /**
     * 修改训练集
     * @param trainingSet
     * @param attrValue
     * @param attrIndex
     * @param parentNodeNameValue
     * @return
     */
    public List<Object[]> updateTrainingSet(List<Object[]> trainingSet, String attrValue, int attrIndex, double parentNodeNameValue) {
        List<Object[]> newTrainingSet = new ArrayList();
        for (Object[] sample : trainingSet) {
            if ("是".equals(attrValue)) {     //连续属性
                if ((double) sample[attrIndex] <= parentNodeNameValue) {
                    newTrainingSet.add(sample);
                }
            }else if ("否".equals(attrValue)) {     //连续属性
                if ((double) sample[attrIndex] > parentNodeNameValue) {
                    newTrainingSet.add(sample);
                }
            }else {     //离散属性
                if (sample[attrIndex].toString().equals(attrValue)) {
                    newTrainingSet.add(sample);
                }
            }
        }
        return newTrainingSet;
    }

    /**
     * 计算属性的信息增益
     * @param trainingSet
     * @param attrIndex
     * @param nodeEntropy
     * @return
     */
    public Map<String, Object> getAttrGain(List<Object[]> trainingSet, List<String[]> attrSet, double nodeEntropy, int attrIndex, int parentAttrIndex) {
        //初始化返回对象
        Map<String, Object> attrGain = new HashMap<>();
        //判断是连续属性还是离散属性
        int disLen = attrSet.get(0).length;
        if (disLen == 0 || (disLen != 0 && attrIndex >= disLen)) {      //连续属性
            //获取数据集中的连续属性的取值
            double[] attrValues = new double[trainingSet.size()];
            for (int i = 0; i < attrValues.length; i++) {
                attrValues[i] = (double) trainingSet.get(i)[attrIndex];
            }
            //排序
            Arrays.sort(attrValues);
            //取排序后的相邻取值的均值作为划分点
            double[] points = new double[attrValues.length - 1];
            for (int i = 0; i < attrValues.length - 1; i++) {
                points[i] = (attrValues[i] + attrValues[i + 1]) / 2;
            }
            //所有划分的信息增益
            double[] gains = new double[trainingSet.size()];
            for (int i = 0; i < points.length; i++) {
                //划分为2个子划分
                List<Object[]> list1 = new ArrayList();
                List<Object[]> list2 = new ArrayList();
                for (int j = 0; j < gains.length; j++) {
                    if ((double) trainingSet.get(j)[attrIndex] < points[i]) {
                        list1.add(trainingSet.get(j));
                    } else {
                        if ((double) trainingSet.get(j)[attrIndex] > points[i]) {
                            list2.add(trainingSet.get(j));
                        }
                    }
                }
                //计算第一个子划分的信息熵
                Map<String, Double> entropyMap1 = continuousValueHandling.getContinuousValueEntropy(list1);
                //计算第二个子划分的信息熵
                Map<String, Double> entropyMap2 = continuousValueHandling.getContinuousValueEntropy(list2);
                //计算该划分的信息增益
                gains[i] = continuousValueHandling.getContinuousValueEntropy(trainingSet).get("entropy") - (((entropyMap1.get("yes") + (double) entropyMap1.get("no")) / trainingSet.size()) * entropyMap1.get("entropy") + ((entropyMap2.get("yes") + (double) entropyMap2.get("no")) / trainingSet.size()) * entropyMap2.get("entropy"));
            }
            //获取最大信息增益的划分点
            double maxGainValue = gains[0];
            int maxGainAttrIndex = 0;
            for (int i = 1; i < gains.length; i++) {
                if (maxGainValue < gains[i]) {
                    maxGainValue = gains[i];
                    maxGainAttrIndex = i;
                }
            }
            //封装返回结果
            attrGain.put("gain", maxGainValue);
            if (points.length != 0) {
                attrGain.put("attr", attrSet.get(1)[attrIndex] + "≤" + points[maxGainAttrIndex]);
            }else {
                attrGain.put("attr", attrSet.get(1)[attrIndex] + "≤0");
            }
        }else {     //离散属性
            if (parentAttrIndex == attrIndex) {     //子节点与父节点取值不同
                attrGain.put("gain", 0.0);
            }else {
                //获取该属性的取值
                String[] attrValues = getAttrValuesByAttrIndex(trainingSet, attrSet, attrIndex);
                //保存该属性的取值的信息熵的数组
                double[] entropy = new double[attrValues.length];
                //保存该属性的取值对应的数据条数
                double[] values = new double[attrValues.length];
                //遍历该属性的取值，统计取值的正例、反例个数，并通过个数计算信息熵
                for (int i = 0; i < attrValues.length; i++) {
                    int yes = 0, no = 0;
                    for (Object[] sample : trainingSet) {
                        if (attrValues[i].equals(sample[attrIndex].toString())) {      //取值相同
                            if ("是".equals(sample[sample.length - 1].toString())) {      //正例
                                yes++;
                            }else {     //反例
                                no++;
                            }
                        }
                    }
                    entropy[i] = getEntropy(yes, no);      //计算信息熵
                    values[i] = yes + no;
                }
                //计算该属性的信息增益
                double attrGainValue = 0;
                for (int i = 0; i < attrValues.length; i++) {
                    attrGainValue += ((values[i]*1.0)/trainingSet.size()) * entropy[i];
                }
                attrGain.put("gain", nodeEntropy - attrGainValue);
            }
            attrGain.put("attr", attrSet.get(0)[attrIndex]);
        }
        return attrGain;
    }

    /**
     * 生成节点
     * @param nodeName
     * @param parentNode
     * @param parentNodeAttr
     * @param parentNodeAttrIndex
     * @param nodeAttrs
     * @param childNodes
     * @return
     */
    public Node nodeGenerate(String nodeName, Node parentNode, String parentNodeAttr, int parentNodeAttrIndex, String[] nodeAttrs, Node[] childNodes) {
        Node node = new Node();
        node.setNodeName(nodeName);
        node.setParentNode(parentNode);
        node.setParentNodeAttr(parentNodeAttr);
        node.setNodeAttrs(nodeAttrs);
        node.setChildNodes(childNodes);
        if (parentNodeAttrIndex != -1) {
            parentNode.getChildNodes()[parentNodeAttrIndex] = node;
        }
        return node;
    }
}
