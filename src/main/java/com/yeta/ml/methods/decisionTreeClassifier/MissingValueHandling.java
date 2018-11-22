package com.yeta.ml.methods.decisionTreeClassifier;

import com.yeta.ml.methods.decisionTreeClassifier.common.Methods;
import com.yeta.ml.methods.decisionTreeClassifier.common.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * ID3算法
 * 连续值处理
 * 缺失值处理
 * Created by YETA666 on 2018/3/23 0023.
 */
@Component
public class MissingValueHandling {

    @Autowired
    private Methods methods;

    @Autowired
    private ContinuousValueHandling continuousValueHandling;

    /**
     * 生成决策树模型
     * @param trainingSet
     * @param attrSet
     * @param weightSet
     * @return
     */
    public Node treeGenerate(List<Object[]> trainingSet, List<String[]> attrSet, double[] weightSet) {
        //获取信息增益最大的属性
        Map<String, Object> maxGainAttr = getMaxGainAttr(trainingSet, attrSet, weightSet, -1);
        int maxGainAttrIndex = (int) maxGainAttr.get("maxGainAttrIndex");
        String maxGainAttrName = maxGainAttr.get("maxGainAttrName").toString();
        //根据属性索引获取该属性的取值
        String attrValues[] = methods.getAttrValuesByAttrIndex(trainingSet, attrSet, maxGainAttrIndex);
        //设置根节点
        Node root = methods.nodeGenerate(maxGainAttrName,
                null,
                null,
                -1,
                attrValues,
                new Node[attrValues.length]);
        //向决策树模型中插入节点
        insertNode(trainingSet, attrSet, weightSet, root);
        //返回决策树模型
        return root;
    }

    /**
     * 向决策树模型中插入节点
     * @param trainingSet
     * @param attrSet
     * @param weightSet
     * @param parentNode
     */
    public void insertNode(List<Object[]> trainingSet, List<String[]> attrSet, double[] weightSet, Node parentNode) {
        //获取父节点的属性（对应属性集中属性的取值）
        Object[] parentAttrs = parentNode.getNodeAttrs();
        //遍历父节点的属性（对应属性集中属性的取值）
        for (int i = 0; i < parentAttrs.length; i++) {
            //当前属性
            String attrValue = parentAttrs[i].toString();
            //连续属性的命名是：密度≤0.381，取≤左右两部分，左边用于获取属性的索引，右边用于修改训练集
            String[] parentNodeName = parentNode.getNodeName().split("≤");
            double parentNodeNameValue = 0;
            if (parentNodeName.length == 2) {
                parentNodeNameValue = Double.parseDouble(parentNodeName[1]);
            }
            //根据 当前属性 的取值获取该属性的索引
            int attrIndex = methods.getAttrIndexByAttrValue(trainingSet, attrSet, attrValue, parentNodeName[0]);
            //修改训练集（即满足父节点的 当前属性 的数据有哪些）
            Map<String, Object> newTrainingSetAndWeightSet = updateTrainingSetAndWeightSet(trainingSet, weightSet, attrValue, attrIndex, parentNodeNameValue);
            List<Object[]> newTrainingSet = (List<Object[]>) newTrainingSetAndWeightSet.get("newTrainingSet");
            double[] newWeightSet = (double[]) newTrainingSetAndWeightSet.get("newWeightSet");
            //获取信息增益最大的属性
            Map<String, Object> maxGainAttr = getMaxGainAttr(newTrainingSet, attrSet, weightSet, attrIndex);
            int maxGainAttrIndex = (int) maxGainAttr.get("maxGainAttrIndex");
            double maxGainValue = (double) maxGainAttr.get("maxGainValue");
            String maxGainAttrName = maxGainAttr.get("maxGainAttrName").toString();
            //如果最大信息增益为0，则是叶子节点
            if (maxGainValue == 0) {
                methods.nodeGenerate(maxGainAttrName,
                        parentNode,
                        attrValue,
                        i,
                        null,
                        null);
            }else {    //如果最大信息增益不为0，则还有子节点
                //根据属性索引获取该属性的取值
                String attrValues[] = methods.getAttrValuesByAttrIndex(newTrainingSet, attrSet, maxGainAttrIndex);
                Node node = methods.nodeGenerate(maxGainAttrName,
                        parentNode,
                        attrValue,
                        i,
                        attrValues,
                        new Node[attrValues.length]);
                insertNode(newTrainingSet, attrSet, newWeightSet, node);
            }
        }
    }

    /**
     * 获取信息增益最大的属性
     * @param trainingSet
     * @param attrSet
     * @param weightSet
     * @return
     */
    public Map<String, Object> getMaxGainAttr(List<Object[]> trainingSet, List<String[]> attrSet, double[] weightSet, int parentAttrIndex) {
        //获取属性集中所有属性的信息增益，存入数组
        List<Map> attrGains = new ArrayList<>();
        for (int i = 0; i < attrSet.get(0).length + attrSet.get(1).length; i++) {
            //获取该属性上无缺失值的样例子集
            List<Object[]> trainingSet1 = new ArrayList<>();
            double[] weightSet1 = new double[weightSet.length];
            for (int j = 0, k = 0; j < trainingSet.size(); j++) {
                Object[] sample = trainingSet.get(j);
                //判断是连续属性还是离散属性
                int disLen = attrSet.get(0).length;
                if (disLen == 0 || (disLen != 0 && i >= disLen)) {      //连续属性
                    if (0.0 != (double) sample[i]) {
                        trainingSet1.add(sample);
                        weightSet1[k++] = weightSet[j];
                    }
                }else {     //离散属性
                    if (!"".equals(sample[i].toString())) {
                        trainingSet1.add(sample);
                        weightSet1[k++] = weightSet[j];
                    }
                }
            }
            double yes = 0, no = 0;        //正例和反例的权重个数
            for (int j = 0; j < trainingSet1.size(); j++) {
                if ("是".equals(trainingSet1.get(j)[trainingSet1.get(j).length - 1])) {
                    yes = yes + weightSet[j];
                }else {
                    no = no + weightSet[j];
                }
            }
            //获取属性attrIndex上无缺失值子集的信息熵
            double nodeEntropy = methods.getEntropy(yes, no);
            //获取该子集第i个属性的信息增益
            double total1 = 0;
            for (int j = 0; j < weightSet.length; j++) {
                total1 = total1 + weightSet[j];
            }
            double total2 = 0;
            for (int j = 0; j < weightSet1.length; j++) {
                total2 = total2 + weightSet1[j];
            }
            attrGains.add(getAttrGain(trainingSet1, attrSet, weightSet, nodeEntropy, i,total2 / total1, parentAttrIndex));
        }
        //获取信息增益最大的属性的索引
        double maxGainValue = (double) attrGains.get(0).get("gain");
        int maxGainAttrIndex = 0;
        for (int i = 0; i < attrGains.size(); i++) {
            double gain = (double) attrGains.get(i).get("gain");
            if (maxGainValue < gain) {
                maxGainValue = gain;
                maxGainAttrIndex = i;
            }
        }
        //封装返回对象
        Map<String, Object> maxGainAttr = new HashMap();
        maxGainAttr.put("maxGainAttrIndex", maxGainAttrIndex);
        maxGainAttr.put("maxGainValue", maxGainValue);
        if (maxGainValue == 0) {
            //叶子节点
            maxGainAttr.put("maxGainAttrName", trainingSet.get(0)[trainingSet.get(0).length - 1].toString());
        }else {
            //非叶子节点
            maxGainAttr.put("maxGainAttrName", attrGains.get(maxGainAttrIndex).get("attr").toString());
        }
        return maxGainAttr;
    }

    /**
     * 计算属性的信息增益
     * @param trainingSet
     * @param attrSet
     * @param weightSet
     * @param attrIndex
     * @param nodeEntropy
     * @param weight
     * @return
     */
    public Map<String, Object> getAttrGain(List<Object[]> trainingSet, List<String[]> attrSet, double[] weightSet, double nodeEntropy, int attrIndex, double weight, int parentAttrIndex) {
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
        }else {
            if (parentAttrIndex == attrIndex) {     //子节点与父节点取值不同
                attrGain.put("gain", 0.0);
            }else {
                //获取属性attrIndex的所有取值
                String[] attrValues = methods.getAttrValuesByAttrIndex(trainingSet, attrSet, attrIndex);
                //获取属性attrIndex的每个取值的信息熵
                double[] entropy = new double[attrValues.length];
                //属性attrIndex的每个取值对应的样例条数
                double[] values = new double[attrValues.length];
                for (int i = 0; i < attrValues.length; i++) {
                    double yes = 0, no = 0;
                    for (int j = 0; j < trainingSet.size(); j++) {
                        Object[] sample = trainingSet.get(j);
                        if (attrValues[i].equals(sample[attrIndex].toString())) {      //取值相同
                            if ("是".equals(sample[sample.length - 1].toString())) {      //正例
                                yes = yes + weightSet[j];
                            }else {     //反例
                                no = no + weightSet[j];
                            }
                        }
                    }
                    entropy[i] = methods.getEntropy(yes, no);      //计算信息熵
                    values[i] = yes + no;
                }
                //计算该属性的信息增益
                double attrGainValue = 0;
                for (int i = 0; i < attrValues.length; i++) {
                    attrGainValue += ((values[i])/trainingSet.size()) * entropy[i];
                }
                //设置返回对象
                attrGain.put("gain", weight * (nodeEntropy - attrGainValue));
            }
            attrGain.put("attr", attrSet.get(0)[attrIndex]);
        }
        return attrGain;
    }

    /**
     * 修改训练集和权重集
     * @param trainingSet
     * @param weightSet
     * @param attrValue
     * @param attrIndex
     * @return
     */
    public Map<String, Object> updateTrainingSetAndWeightSet(List<Object[]> trainingSet, double[] weightSet, String attrValue, int attrIndex, double parentNodeNameValue) {
        List<Object[]> newTrainingSet = new ArrayList<>();
        List<Double> weightSet1 = new ArrayList();
        double nullNum = 0;
        for (int i = 0; i < trainingSet.size(); i++) {
            Object[] sample = trainingSet.get(i);
            if ("是".equals(attrValue)) {     //连续属性
                if ((double) sample[attrIndex] <= parentNodeNameValue || (double) sample[attrIndex] == 0) {
                    newTrainingSet.add(sample);
                    weightSet1.add(weightSet[i]);
                    if ((double) sample[attrIndex] == 0) {
                        nullNum++;
                    }
                }
            }else if ("否".equals(attrValue)) {     //连续属性
                if ((double) sample[attrIndex] > parentNodeNameValue || (double) sample[attrIndex] == 0) {
                    newTrainingSet.add(sample);
                    weightSet1.add(weightSet[i]);
                    if ((double) sample[attrIndex] == 0) {
                        nullNum++;
                    }
                }
            }else {     //离散属性
                if (sample[attrIndex].toString().equals(attrValue) || sample[attrIndex].toString().equals("")) {
                    newTrainingSet.add(sample);
                    weightSet1.add(weightSet[i]);
                    if (sample[attrIndex].toString().equals("")) {
                        nullNum++;
                    }
                }
            }
        }
        for (int i = 0; i < newTrainingSet.size(); i++) {
            Object[] sample = newTrainingSet.get(i);
            if ("是".equals(attrValue) || "否".equals(attrValue)) {     //连续属性
                if ((double) sample[attrIndex] == 0) {
                    double weight = newTrainingSet.size() / (trainingSet.size() - nullNum);
                    weightSet1.set(i, weight);
                }
            }else {     //离散属性
                if (sample[attrIndex].toString().equals("")) {
                    double weight = newTrainingSet.size() / (trainingSet.size() - nullNum);
                    weightSet1.set(i, weight);
                }
            }
        }
        double[] newWeightSet = new double[weightSet1.size()];
        for (int i = 0; i < weightSet1.size(); i++) {
            newWeightSet[i] = weightSet1.get(i);
        }
        Map<String, Object> newTrainingSetAndWeightSet = new HashMap();
        newTrainingSetAndWeightSet.put("newTrainingSet", newTrainingSet);
        newTrainingSetAndWeightSet.put("newWeightSet", newWeightSet);
        return newTrainingSetAndWeightSet;
    }
}
