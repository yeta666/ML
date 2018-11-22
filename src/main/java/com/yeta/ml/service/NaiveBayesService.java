package com.yeta.ml.service;

import com.yeta.ml.methods.naiveBayesClassifier.NaiveBayes;
import com.yeta.ml.model.WatermelonDataSet3;
import com.yeta.ml.repository.WatermelonDataSet3Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 朴素贝叶斯逻辑层
 * Created by YETA666 on 2018/4/18 0018.
 */
@Service
public class NaiveBayesService {

    @Autowired
    private NaiveBayes naiveBayes;

    @Autowired
    private WatermelonDataSet3Repository watermelonDataSet3Repository;

    public void naiveBayes() {
        //初始化数据集
        List<WatermelonDataSet3> watermelonDataSet3List = watermelonDataSet3Repository.findAll();
        //初始化训练集
        List<Object[]> trainingSet = new ArrayList<>();
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
        List<String[]> attrSet = new ArrayList<>();
        if (watermelonDataSet3List.get(0).getDiscreteAttr() != null) {
            attrSet.add(watermelonDataSet3List.get(0).getDiscreteAttr().split(","));
        }else {
            attrSet.add(new String[0]);
        }
        if (watermelonDataSet3List.get(0).getContinuousAttr() != null) {
            attrSet.add(watermelonDataSet3List.get(0).getContinuousAttr().split(","));
            attrSet.add(new String[0]);
        }
        //获取先验概率
        double[] priorProbabilities = naiveBayes.getPriorProbabilities(trainingSet);
        //训练模型，即获取每个属性的条件概率
        List<List> model = naiveBayes.getConditionalProbabilities(trainingSet, attrSet);
        //测试模型
        for (Object[] sample : trainingSet) {
            double[] testResult = naiveBayes.testModel(priorProbabilities, model, trainingSet, attrSet, sample);
            System.out.println("好瓜概率：" + testResult[0] + " > " + "坏瓜概率：" + testResult[1] + " ? " + (testResult[0] > testResult[1] ? "是" : "否") + " " + sample[sample.length - 1].toString());
        }
    }
}
