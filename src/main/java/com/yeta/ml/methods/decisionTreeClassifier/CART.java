package com.yeta.ml.methods.decisionTreeClassifier;

import com.yeta.ml.methods.decisionTreeClassifier.common.Methods;
import com.yeta.ml.methods.decisionTreeClassifier.common.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

/**
 * CART决策树
 * Created by YETA666 on 2018/3/23 0023.
 */
@Component
public class CART {

    @Autowired
    private Methods methods;

    /**
     * 生成决策树模型
     * @param trainingSet
     * @param attrSet
     * @return
     */
    public Node treeGenerate(List<Object[]> trainingSet, List<String[]> attrSet) {
        //获取基尼指数最小的属性
        Map<String, Object> minGiniIndexAttr = getMinGiniIndexAttr(trainingSet, attrSet, -1);
        int minGiniIndexAttrIndex = (int) minGiniIndexAttr.get("minGiniIndexAttrIndex");
        String minGiniIndexAttrName = minGiniIndexAttr.get("minGiniIndexAttrName").toString();
        //根据属性索引获取该属性的取值
        String attrValues[] = methods.getAttrValuesByAttrIndex(trainingSet, attrSet, minGiniIndexAttrIndex);
        //设置根节点
        Node root = methods.nodeGenerate(minGiniIndexAttrName,
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
            //获取基尼指数最小的属性
            Map<String, Object> minGiniIndexAttr = getMinGiniIndexAttr(newTrainingSet, attrSet, attrIndex);
            int minGiniIndexAttrIndex = (int) minGiniIndexAttr.get("minGiniIndexAttrIndex");
            double minGiniIndexValue = (double) minGiniIndexAttr.get("minGiniIndexValue");
            String minGiniIndexAttrName = minGiniIndexAttr.get("minGiniIndexAttrName").toString();
            //如果最小基尼指数为0，则是叶子节点
            if (minGiniIndexValue == 0) {
                methods.nodeGenerate(minGiniIndexAttrName,
                        parentNode,
                        attrValue,
                        i,
                        null,
                        null);
            } else {    //如果最大信息增益不为0，则还有子节点
                //根据属性索引获取该属性的取值
                String attrValues[] = methods.getAttrValuesByAttrIndex(newTrainingSet, attrSet, minGiniIndexAttrIndex);
                Node node = methods.nodeGenerate(minGiniIndexAttrName,
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
     * 获取基尼指数最小的属性
     * @param trainingSet
     * @param attrSet
     * @return
     */
    public Map<String, Object> getMinGiniIndexAttr(List<Object[]> trainingSet, List<String[]> attrSet, int parentAttrIndex) {
        //存放所有属性基尼指数的数组
        double[] giniIndexs = new double[attrSet.get(0).length];
        //遍历所有属性
        for (int i = 0; i < giniIndexs.length; i++) {
            if (i == parentAttrIndex) {     //离散属性，子节点与父节点不一致
                giniIndexs[i] = 1;
            }else {
                //属性的取值
                String[] attrValues = methods.getAttrValuesByAttrIndex(trainingSet, attrSet, i);
                //属性的取值的个数
                double[] attrValuesNum = new double[attrValues.length];
                //遍历属性的取值，统计属性的取值对应的个数
                for (int j = 0; j < attrValues.length; j++) {
                    for (int k = 0; k < trainingSet.size(); k++) {
                        if (attrValues[j].equals(trainingSet.get(k)[i].toString())) {
                            attrValuesNum[j]++;
                        }
                    }
                }
                //总数
                double total = 0;
                for (int j = 0; j < attrValuesNum.length; j++) {
                    total += attrValuesNum[j];
                }
                //每个属性的基尼指数，存入数组
                double giniIndex = 0;
                for (int j = 0; j < attrValuesNum.length; j++) {
                    giniIndex += (attrValuesNum[j] / total) * getGini(trainingSet, i, attrValues[j]);
                }
                giniIndexs[i] = giniIndex;
            }
        }
        //获取最小的基尼指数
        double minGiniIndexValue = giniIndexs[0];
        int minGiniIndexAttrIndex = 0;
        for (int i = 0; i < giniIndexs.length; i++) {
            if (minGiniIndexValue > giniIndexs[i]) {
                minGiniIndexValue = giniIndexs[i];
                minGiniIndexAttrIndex = i;
            }
        }
        //封装返回对象
        Map<String, Object> minGiniIndexAttr = new HashMap();
        minGiniIndexAttr.put("minGiniIndexValue", minGiniIndexValue);
        minGiniIndexAttr.put("minGiniIndexAttrIndex", minGiniIndexAttrIndex);
        if (minGiniIndexValue == 0) {
            //叶子节点
            minGiniIndexAttr.put("minGiniIndexAttrName", trainingSet.get(0)[trainingSet.get(0).length - 1].toString());
        }else {
            //非叶子节点
            minGiniIndexAttr.put("minGiniIndexAttrName", attrSet.get(0)[minGiniIndexAttrIndex]);
        }
        return minGiniIndexAttr;
    }

    /**
     * 获取基尼值
     * @param dataSet
     * @param attributeIndex
     * @param attributeValue
     * @return
     */
    public double getGini(List<Object[]> dataSet, int attributeIndex, String attributeValue) {
        double yes = 0, no = 0;
        for (int i = 0; i < dataSet.size(); i++) {
            if (dataSet.get(i)[attributeIndex].toString().equals(attributeValue)) {
                //统计正例和反例的个数
                if (dataSet.get(i)[dataSet.get(i).length - 1].toString().equals("是")) {
                    yes++;
                }else {
                    no++;
                }
            }
        }
        //两次抽到不一样的概率 = 1 - (第一次抽到yes的概率 * 第二次抽到yes的概率 + 第一次抽到no的概率 * 第二次抽到no的概率)
        double p = 1 - ((yes / (yes + no)) * (yes / (yes + no)) + (no / (yes + no)) * (no / (yes + no)));
        return p;
    }
}
