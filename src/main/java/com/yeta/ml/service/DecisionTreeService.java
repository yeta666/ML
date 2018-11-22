package com.yeta.ml.service;

import com.yeta.ml.methods.decisionTreeClassifier.C4Point5;
import com.yeta.ml.methods.decisionTreeClassifier.CART;
import com.yeta.ml.methods.decisionTreeClassifier.ID3;
import com.yeta.ml.methods.decisionTreeClassifier.MissingValueHandling;
import com.yeta.ml.methods.decisionTreeClassifier.common.Node;
import com.yeta.ml.model.*;
import com.yeta.ml.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 决策树逻辑层
 * Created by YETA666 on 2018/4/12 0012.
 */
@Service
public class DecisionTreeService {

    @Autowired
    private WatermelonDataSet2Repository watermelonDataSet2Repository;

    @Autowired
    private WatermelonDataSet2aRepository watermelonDataSet2aRepository;

    @Autowired
    private WatermelonDataSet3Repository watermelonDataSet3Repository;

    @Autowired
    private WatermelonDataSet3aRepository watermelonDataSet3aRepository;

    @Autowired
    private WatermelonDataSet4Repository watermelonDataSet4Repository;

    @Autowired
    private ID3 id3;

    @Autowired
    private C4Point5 c4Point5;

    @Autowired
    private CART cart;

    @Autowired
    private MissingValueHandling missingValueHandling;

    /**
     * 训练集
     */
    private List<Object[]> trainingSet;

    /**
     * 属性集
     */
    private List<String[]> attrSet;

    /**
     * 节点存放在内存中
     */
    private List<Node> nodeList;

    /**
     * 把模型按构建顺序拆分为各节点，存放在内存
     * @param lastNode
     */
    public void splitModel(Node lastNode) {
        Node node = new Node();
        node.setNodeName(lastNode.getNodeName());
        if (lastNode.getParentNode() != null) {
            node.setParentNodeName(lastNode.getParentNode().getNodeName());
        }else {
            node.setParentNodeName(null);
        }
        node.setParentNodeAttr(lastNode.getParentNodeAttr());
        node.setNodeAttrs(lastNode.getNodeAttrs());
        nodeList.add(node);
        Node[] childNodes = lastNode.getChildNodes();
        if (childNodes != null) {
            for (int i = 0; i < childNodes.length; i++) {
                if (childNodes[i] != null) {
                    splitModel(childNodes[i]);
                }
            }
        }
    }

    /**
     * 初始化训练集和属性集
     */
    public void initSet(String dataSetIndex) {
        //重置节点
        nodeList = new ArrayList<>();
        if ("2".equals(dataSetIndex)) {
            //初始化数据集
            List<WatermelonDataSet2> watermelonDataSet2List = watermelonDataSet2Repository.findAll();
            //初始化训练集
            trainingSet = new ArrayList<>();
            for (WatermelonDataSet2 watermelonDataSet2 : watermelonDataSet2List) {
                Object[] sample = new Object[7];
                sample[0] = watermelonDataSet2.getColourAndLustre();
                sample[1] = watermelonDataSet2.getRootAndBase();
                sample[2] = watermelonDataSet2.getStroke();
                sample[3] = watermelonDataSet2.getVenation();
                sample[4] = watermelonDataSet2.getUmbilicalRegion();
                sample[5] = watermelonDataSet2.getTouch();
                sample[6] = watermelonDataSet2.getIsGood();
                trainingSet.add(sample);
            }
            //初始化属性集
            attrSet = new ArrayList<>();
            if (watermelonDataSet2List.get(0).getDiscreteAttr() != null) {
                attrSet.add(watermelonDataSet2List.get(0).getDiscreteAttr().split(","));
            }else {
                attrSet.add(new String[0]);
            }
            if (watermelonDataSet2List.get(0).getContinuousAttr() != null) {
                attrSet.add(watermelonDataSet2List.get(0).getContinuousAttr().split(","));
            }else {
                attrSet.add(new String[0]);
            }
        }else if ("2a".equals(dataSetIndex)) {
            //初始化数据集
            List<WatermelonDataSet2a> watermelonDataSet2aList = watermelonDataSet2aRepository.findAll();
            //初始化训练集
            trainingSet = new ArrayList<>();
            for (WatermelonDataSet2a watermelonDataSet2a : watermelonDataSet2aList) {
                Object[] sample = new Object[7];
                sample[0] = watermelonDataSet2a.getColourAndLustre();
                sample[1] = watermelonDataSet2a.getRootAndBase();
                sample[2] = watermelonDataSet2a.getStroke();
                sample[3] = watermelonDataSet2a.getVenation();
                sample[4] = watermelonDataSet2a.getUmbilicalRegion();
                sample[5] = watermelonDataSet2a.getTouch();
                sample[6] = watermelonDataSet2a.getIsGood();
                trainingSet.add(sample);
            }
            //初始化属性集
            attrSet = new ArrayList<>();
            if (watermelonDataSet2aList.get(0).getDiscreteAttr() != null) {
                attrSet.add(watermelonDataSet2aList.get(0).getDiscreteAttr().split(","));
            }else {
                attrSet.add(new String[0]);
            }
            if (watermelonDataSet2aList.get(0).getContinuousAttr() != null) {
                attrSet.add(watermelonDataSet2aList.get(0).getContinuousAttr().split(","));
            }else {
                attrSet.add(new String[0]);
            }
        }else if ("3".equals(dataSetIndex)) {
            //初始化数据集
            List<WatermelonDataSet3> watermelonDataSet3List = watermelonDataSet3Repository.findAll();
            //初始化训练集
            trainingSet = new ArrayList<>();
            for (WatermelonDataSet3 watermelonDataSet3 : watermelonDataSet3List) {
                Object[] sample = new Object[9];
                sample[0] = watermelonDataSet3.getColourAndLustre();
                sample[1] = watermelonDataSet3.getRootAndBase();
                sample[2] = watermelonDataSet3.getStroke();
                sample[3] = watermelonDataSet3.getVenation();
                sample[4] = watermelonDataSet3.getUmbilicalRegion();
                sample[5] = watermelonDataSet3.getTouch();
                sample[6] = watermelonDataSet3.getDensity();
                sample[7] = watermelonDataSet3.getSugarContent();
                sample[8] = watermelonDataSet3.getIsGood();
                trainingSet.add(sample);
            }
            //初始化属性集
            attrSet = new ArrayList<>();
            if (watermelonDataSet3List.get(0).getDiscreteAttr() != null) {
                attrSet.add(watermelonDataSet3List.get(0).getDiscreteAttr().split(","));
            }else {
                attrSet.add(new String[0]);
            }
            if (watermelonDataSet3List.get(0).getContinuousAttr() != null) {
                attrSet.add(watermelonDataSet3List.get(0).getContinuousAttr().split(","));
            }else {
                attrSet.add(new String[0]);
            }
        }else if ("3a".equals(dataSetIndex)) {
            //初始化数据集
            List<WatermelonDataSet3a> watermelonDataSet3aList = watermelonDataSet3aRepository.findAll();
            //初始化训练集
            trainingSet = new ArrayList<>();
            for (WatermelonDataSet3a watermelonDataSet3a : watermelonDataSet3aList) {
                Object[] sample = new Object[3];
                sample[0] = watermelonDataSet3a.getDensity();
                sample[1] = watermelonDataSet3a.getSugarContent();
                sample[2] = watermelonDataSet3a.getIsGood();
                trainingSet.add(sample);
            }
            //初始化属性集
            attrSet = new ArrayList<>();
            if (watermelonDataSet3aList.get(0).getDiscreteAttr() != null) {
                attrSet.add(watermelonDataSet3aList.get(0).getDiscreteAttr().split(","));
            }else {
                attrSet.add(new String[0]);
            }
            if (watermelonDataSet3aList.get(0).getContinuousAttr() != null) {
                attrSet.add(watermelonDataSet3aList.get(0).getContinuousAttr().split(","));
            }else {
                attrSet.add(new String[0]);
            }
        }else if ("5".equals(dataSetIndex)) {
            //初始化数据集
            List<WatermelonDataSet4> watermelonDataSet4List = watermelonDataSet4Repository.findAll();
            //初始化训练集
            trainingSet = new ArrayList<>();
            for (WatermelonDataSet4 watermelonDataSet4 : watermelonDataSet4List) {
                Object[] sample = new Object[2];
                sample[0] = watermelonDataSet4.getDensity();
                sample[1] = watermelonDataSet4.getSugarContent();
                trainingSet.add(sample);
            }
            //初始化属性集
            attrSet = new ArrayList<>();
            if (watermelonDataSet4List.get(0).getDiscreteAttr() != null) {
                attrSet.add(watermelonDataSet4List.get(0).getDiscreteAttr().split(","));
            }else {
                attrSet.add(new String[0]);
            }
            if (watermelonDataSet4List.get(0).getContinuousAttr() != null) {
                attrSet.add(watermelonDataSet4List.get(0).getContinuousAttr().split(","));
            }else {
                attrSet.add(new String[0]);
            }
        }
    }

    /**
     * ID3训练决策树
     *
     * @return
     */
    public List<Node> id3() {
        //初始化训练集和数据集
        initSet("2");
        //调用id3算法
        Node model = id3.treeGenerate(trainingSet, attrSet);
        //把模型按构建顺序拆分为各节点，存放在内存
        splitModel(model);
        return nodeList;
    }

    /**
     * C4.5训练决策树
     * @return
     */
    public List<Node> c4Point5() {
        //初始化训练集和数据集
        initSet("2");
        //调用c4.5算法
        Node model = c4Point5.treeGenerate(trainingSet, attrSet);
        //把模型按构建顺序拆分为各节点，存放在内存
        splitModel(model);
        return nodeList;
    }

    /**
     * CART决策树
     * @return
     */
    public List<Node> cart() {
        //初始化训练集和数据集
        initSet("2");
        //调用CART算法
        Node model = cart.treeGenerate(trainingSet, attrSet);
        //把模型按构建顺序拆分为各节点，存放在内存
        splitModel(model);
        return nodeList;
    }

    /**
     * 连续值处理
     * @return
     */
    public List<Node> continuousValueHandling() {
        //初始化训练集和数据集
        initSet("3a");
        //调用id3算法
        Node model = id3.treeGenerate(trainingSet, attrSet);
        //把模型按构建顺序拆分为各节点，存放在内存
        splitModel(model);
        return nodeList;
    }

    /**
     * 缺失值处理
     * @return
     */
    public List<Node> missingValueHandling() {
        //初始化训练集和数据集
        initSet("2a");
        //初始化权重集
        double[] weightSet = new double[trainingSet.size()];
        for (int i = 0; i < weightSet.length; i++) {
            weightSet[i] = 1;
        }
        //调用缺失值处理算法
        Node model = missingValueHandling.treeGenerate(trainingSet, attrSet, weightSet);
        //把模型按构建顺序拆分为各节点，存放在内存
        splitModel(model);
        return nodeList;
    }
}
