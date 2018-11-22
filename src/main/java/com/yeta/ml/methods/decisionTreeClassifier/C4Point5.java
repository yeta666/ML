package com.yeta.ml.methods.decisionTreeClassifier;

import com.yeta.ml.methods.decisionTreeClassifier.common.Methods;
import com.yeta.ml.methods.decisionTreeClassifier.common.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

/**
 * C4.5算法
 * Created by YETA666 on 2018/3/23 0023.
 */
@Component
public class C4Point5 {

    @Autowired
    private Methods methods;

    /**
     * 生成决策树模型
     * @param trainingSet
     * @param attrSet
     * @return
     */
    public Node treeGenerate(List<Object[]> trainingSet, List<String[]> attrSet) {
        //获取增益率最大的属性
        Map<String, Object> maxGainRatioAttr = getMaxGainRatioAttr(trainingSet, attrSet, -1);
        int maxGainRatioAttrIndex = (int) maxGainRatioAttr.get("maxGainRatioAttrIndex");
        String maxGainRatioAttrName = maxGainRatioAttr.get("maxGainRatioAttrName").toString();
        //根据属性索引获取该属性的取值
        String attrValues[] = methods.getAttrValuesByAttrIndex(trainingSet, attrSet, maxGainRatioAttrIndex);
        //设置根节点
        Node root = methods.nodeGenerate(maxGainRatioAttrName,
                null,
                null,
                -1,
                attrValues,
                new Node[attrValues.length]);
        //向决策树模型中插入节点
        insertNode(trainingSet, attrSet, root);
        //返回决策树模型
        return root;
    }

    /**
     * 向决策树模型中插入节点
     * @param trainingSet
     * @param attrSet
     * @param parentNode
     */
    public void insertNode(List<Object[]> trainingSet, List<String[]> attrSet, Node parentNode) {
        //获取父节点的属性（对应属性集中属性的取值）
        Object[] parentAttrs = parentNode.getNodeAttrs();
        //遍历父节点的属性（对应属性集中属性的取值）
        for (int i = 0; i < parentAttrs.length; i++) {
            //当前属性
            String attrValue = parentAttrs[i].toString();
            //根据 当前属性 的取值获取该属性的索引
            int attrIndex = methods.getAttrIndexByAttrValue(trainingSet, attrSet, attrValue, "");
            //修改训练集（即满足父节点的 当前属性 的数据有哪些）
            List<Object[]> newTrainingSet = methods.updateTrainingSet(trainingSet, attrValue, attrIndex, 0);
            //获取增益率最大的属性
            Map<String, Object> maxGainRatioAttr = getMaxGainRatioAttr(newTrainingSet, attrSet, attrIndex);
            int maxGainRatioAttrIndex = (int) maxGainRatioAttr.get("maxGainRatioAttrIndex");
            double maxGainRatioValue = (double) maxGainRatioAttr.get("maxGainRatioValue");
            String maxGainRatioAttrName = maxGainRatioAttr.get("maxGainRatioAttrName").toString();
            //如果最大增益率为0，则是叶子节点
            if (maxGainRatioValue == 0) {
                methods.nodeGenerate(maxGainRatioAttrName,
                        parentNode,
                        attrValue,
                        i,
                        null,
                        null);
            }else {    //如果最大信息增益不为0，则还有子节点
                //根据属性索引获取该属性的取值
                String attrValues[] = methods.getAttrValuesByAttrIndex(newTrainingSet, attrSet, maxGainRatioAttrIndex);
                Node node = methods.nodeGenerate(maxGainRatioAttrName,
                        parentNode,
                        attrValue,
                        i,
                        attrValues,
                        new Node[attrValues.length]);
                insertNode(newTrainingSet, attrSet, node);
            }
        }
    }

    /**
     * 获取增益率最大的属性
     * @param trainingSet
     * @param attrSet
     * @return
     */
    public Map<String, Object> getMaxGainRatioAttr(List<Object[]> trainingSet, List<String[]> attrSet, int parentAttrIndex) {
        //获取该节点的信息熵
        int yes = 0, no = 0;        //正例和反例的个数
        for (Object[] sample : trainingSet) {
            if ("是".equals(sample[sample.length - 1].toString())) {
                yes++;
            }else {
                no++;
            }
        }
        double nodeEntropy = methods.getEntropy(yes, no);       //该节点信息熵
        //保存属性增益率的数组
        List<Map> attrGainRatios = new ArrayList<>();
        for (int i = 0; i < attrSet.get(0).length + attrSet.get(1).length; i++) {
            attrGainRatios.add(getAttrGainRatio(trainingSet, attrSet, nodeEntropy, i, parentAttrIndex));
        }
        //获取增益率最大的属性的索引
        double maxGainRatioValue = (double) attrGainRatios.get(0).get("gainRatio");
        int maxGainRatioAttrIndex = 0;
        for (int i = 0; i < attrGainRatios.size(); i++) {
            double gainRatio = (double) attrGainRatios.get(i).get("gainRatio");
            if (maxGainRatioValue < gainRatio) {
                maxGainRatioValue = gainRatio;
                maxGainRatioAttrIndex = i;
            }
        }
        //封装返回对象
        Map<String, Object> maxGainRatioAttr = new HashMap();
        maxGainRatioAttr.put("maxGainRatioValue", maxGainRatioValue);
        maxGainRatioAttr.put("maxGainRatioAttrIndex", maxGainRatioAttrIndex);
        if (maxGainRatioValue == 0) {
            //叶子节点
            maxGainRatioAttr.put("maxGainRatioAttrName", trainingSet.get(0)[trainingSet.get(0).length - 1].toString());
        }else {
            //非叶子节点
            maxGainRatioAttr.put("maxGainRatioAttrName", attrGainRatios.get(maxGainRatioAttrIndex).get("attr").toString());
        }
        return maxGainRatioAttr;
    }

    /**
     * 计算属性的增益率
     * @param trainingSet
     * @param attrIndex
     * @param nodeEntropy
     * @return
     */
    public Map<String, Object> getAttrGainRatio(List<Object[]> trainingSet, List<String[]> attrSet, double nodeEntropy, int attrIndex, int parentAttrIndex) {
        //初始化返回对象
        Map<String, Object> attrGainRatio = new HashMap<>();
        if (parentAttrIndex == attrIndex) {     //子节点与父节点取值不同
            attrGainRatio.put("gainRatio", 0.0);
        }else {

        }
        //获取该属性的信息增益
        Map<String, Object> attrGain = methods.getAttrGain(trainingSet, attrSet, nodeEntropy, attrIndex, parentAttrIndex);
        //获取该属性的取值
        String[] attrValues = methods.getAttrValuesByAttrIndex(trainingSet, attrSet, attrIndex);
        //保存该属性的取值对应的数据条数
        double[] values = new double[attrValues.length];
        for (int i = 0; i < attrValues.length; i++) {
            int yes = 0, no = 0;
            for (Object[] sample : trainingSet) {
                if (attrValues[i].equals(sample[attrIndex])) {      //取值相同
                    if ("是".equals(sample[sample.length - 1].toString())) {      //正例
                        yes++;
                    }else {     //反例
                        no++;
                    }
                }
            }
            values[i] = yes + no;
        }
        //计算IV(a)
        int total = 0;
        for (int i = 0; i < values.length; i++) {
            total += values[i];
        }
        double IV = 0;
        for (int i = 0; i < values.length; i++) {
            IV += (values[i]*1.0/total) * (Math.log10(values[i]/total*1.0)/Math.log10(2));
        }
        if (IV == 0) {
            attrGainRatio.put("gainRatio", 0.0);
        }else {
            attrGainRatio.put("gainRatio", (double) attrGain.get("gain") / (-IV));
        }
        attrGainRatio.put("attr", attrSet.get(0)[attrIndex]);
        return attrGainRatio;
    }
}
