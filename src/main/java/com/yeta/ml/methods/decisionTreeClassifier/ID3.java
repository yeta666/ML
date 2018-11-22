package com.yeta.ml.methods.decisionTreeClassifier;

import com.yeta.ml.methods.decisionTreeClassifier.common.Methods;
import com.yeta.ml.methods.decisionTreeClassifier.common.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * ID3算法
 * 连续值处理
 * Created by YETA666 on 2018/3/23 0023.
 */
@Component
public class ID3 {

    @Autowired
    private Methods methods;

    /**
     * 生成决策树模型
     * @param trainingSet
     * @param attrSet
     * @return
     */
    public Node treeGenerate(List<Object[]> trainingSet, List<String[]> attrSet) {
        //获取信息增益最大的属性
        Map<String, Object> maxGainAttr = getMaxGainAttr(trainingSet, attrSet, -1);
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
    private void insertNode(List<Object[]> trainingSet, List<String[]> attrSet, Node parentNode) {
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
            List<Object[]> newTrainingSet = methods.updateTrainingSet(trainingSet, attrValue, attrIndex, parentNodeNameValue);
            //获取信息增益最大的属性
            Map<String, Object> maxGainAttr = getMaxGainAttr(newTrainingSet, attrSet, attrIndex);
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
                insertNode(newTrainingSet, attrSet, node);
            }
        }
    }

    /**
     * 获取信息增益最大的属性
     * @param trainingSet
     * @param attrSet
     * @return
     */
    private Map<String, Object> getMaxGainAttr(List<Object[]> trainingSet, List<String[]> attrSet, int parentAttrIndex) {
        //获取该节点的信息熵
        int yes = 0, no = 0;        //正例和反例的个数
        for (Object[] sample : trainingSet) {
            if ("是".equals(sample[sample.length - 1].toString())) {
                yes++;
            }else {
                no++;
            }
        }
        //该节点信息熵
        double nodeEntropy = methods.getEntropy(yes, no);
        //保存属性信息增益的数组
        List<Map> attrGains = new ArrayList<>();
        for (int i = 0; i < attrSet.get(0).length + attrSet.get(1).length; i++) {
            attrGains.add(methods.getAttrGain(trainingSet, attrSet, nodeEntropy, i, parentAttrIndex));
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
}
