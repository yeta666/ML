/*
package com.yeta.ml.trainingSetAndTestingSet;

import com.yeta.ml.dataSet.DataSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * 自助法
 * Created by YETA666 on 2018/3/21 0021.
 *//*

public class Bootstrapping {

    public Map getTrainingSetAndTestingSet() {
        //数据集
        DataSet dataSet = new DataSet();
        Object[][] dSet = null;
        //初始化训练集和测试集
        Object[][] trainingSet = new Object[dSet.length][dSet[0].length];
        Object[][] testingSet = new Object[dSet.length][dSet[0].length];
        //从数据集中有放回的抽样到训练集
        for (int i = 0; i < dSet.length; i++) {
            trainingSet[i] = dSet[(int)Math.round(Math.random() * (dSet.length - 1))];
        }
        //训练集=数据集-测试集
        int o = 0;
        for (int i = 0; i < dSet.length; i++) {
            int  p = 0;
            for (int j = 0; j < trainingSet.length; j++) {
                boolean flag = false;
                for (int k = 0; k < dSet[i].length - 1; k++) {
                    if (dSet[i][k] != trainingSet[j][k]) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    p++;
                }
            }
            if (p == trainingSet.length) {
                testingSet[o++] = dSet[i];
            }
        }
        //封装返回结果
        Map result = new HashMap<>();
        result.put("trainingSet", trainingSet);
        result.put("testingSet", Arrays.copyOfRange(testingSet, 0, o));     //将训练集中多余的数据去掉
        return result;
    }
}
*/
