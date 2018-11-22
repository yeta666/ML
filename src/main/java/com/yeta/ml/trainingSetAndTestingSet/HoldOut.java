/*
package com.yeta.ml.trainingSetAndTestingSet;

import com.yeta.ml.dataSet.DataSet;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * 留出法
 * Created by YETA666 on 2018/3/21 0021.
 *//*

public class HoldOut {
    //设置从数据集中获取训练集的比例
    private static final double rate = 2.0 / 3;

    public Map getTrainingSetAndTestingSet() {
        //数据集
        DataSet dataSet = new DataSet();
        Object[][] dSet = null;
        //获取数据集中正例的比例
        double positiveCases = 0, negativeCases = 0;
        for (int i = 0; i < dSet.length; i++) {
            if (dSet[i][dSet[i].length - 1].equals("是")) {
                positiveCases++;
            }else {
                negativeCases++;
            }
        }
        //正例和反例概率
        double positiveCasesRate = positiveCases / dSet.length;
        double negativeCasesRate = negativeCases / dSet.length;
        //初始化训练集和测试集
        Object[][] trainingSet = new Object[(int)(rate*dSet.length)][dSet[0].length];
        Object[][] testingSet = new Object[(int)((1-rate)*dSet.length)][dSet[0].length];
        //将数据集分发到训练集和测试集
        for (int i = 0, pos = 0, neg = 0, p = 0, q = 0; i < dSet.length; i++) {
            if (dSet[i][dSet[i].length - 1].equals("是")) {
                if (rate * positiveCasesRate > (pos*1.0) / dSet.length) {
                    pos++;
                    trainingSet[p] = dSet[i];
                    p++;
                }else {
                    testingSet[q] = dSet[i];
                    q++;
                }
            }else {
                if (rate * negativeCasesRate > (neg*1.0) / dSet.length) {
                    neg++;
                    trainingSet[p] = dSet[i];
                    p++;
                }else {
                    testingSet[q] = dSet[i];
                    q++;
                }
            }
        }
        //封装返回对象
        Map result = new HashMap();
        result.put("trainingSet", trainingSet);
        result.put("testingSet", testingSet);
        return result;
    }
}
*/
