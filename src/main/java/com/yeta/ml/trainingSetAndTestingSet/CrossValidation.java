/*
package com.yeta.ml.trainingSetAndTestingSet;

import com.yeta.ml.dataSet.DataSet;

import java.util.*;

*/
/**
 * 交叉验证法
 * Created by YETA666 on 2018/3/21 0021.
 *//*

public class CrossValidation {

    public Map getTrainingSetAndTestingSet() {
        //数据集
        DataSet dataSet = new DataSet();
        Object[][] dSet = null;
        // Object[][] dSet = dataSet.dataSet;
        //10个数据集子集
        Object[][][] dSets = new Object[10][(dSet.length / 10) + 1][dSet[0].length];
        //将数据集分发到10个子集
        for (int i = 0, j = 0; i < dSet.length; i=i+10) {
            for (int z = 0, y = i; z < dSets.length; z++, y++) {
                if (y < dSet.length) {
                    dSets[z][j] = dSet[y];
                }
            }
            j++;
        }
        //初始化训练集和测试集
        Object[] trainingSet = new Object[dSets.length];
        Object[] testingSet = new Object[dSets.length];
        //将数据集分发到训练集和测试机
        for (int i = 0, j = 0, k = 0; i < dSets.length; i++) {
            //取第i个子集为测试集
            List testingSetList = new ArrayList();
            for (int l = 0; l < dSets[i].length; l++) {
                if (dSets[i][l][0] != null) {
                    testingSetList.add(dSets[i][l]);
                }
            }
            testingSet[j] = testingSetList.toArray();
            j++;
            //除第i个子集之外的其他子集共同组成训练集
            List trainingSetList = new ArrayList();
            for (int l = 0; l < dSets.length; l++) {
                if (l != i) {
                    for (int m = 0; m < dSets[l].length; m++) {
                        if (dSets[l][m][0] != null) {
                            trainingSetList.add(dSets[l][m]);
                        }
                    }
                }
            }
            trainingSet[k] = trainingSetList.toArray();
            k++;
        }
        //封装返回对象
        Map result = new HashMap();
        result.put("trainingSet", trainingSet);
        result.put("testingSet", testingSet);
        return result;
    }
}
*/
