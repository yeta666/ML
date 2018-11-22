package com.yeta.ml.methods.decisionTreeClassifier;

import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 连续值处理
 * 二分法
 * Created by YETA666 on 2018/3/21 0021.
 */
@Component
public class ContinuousValueHandling {

    /**
     * 获取信息熵及正例、反例的个数
     * @param list
     * @return
     */
    public Map<String, Double> getContinuousValueEntropy(List<Object[]> list) {
        //统计数据集中正例、反例的个数
        double yes = 0, no = 0;
        for (Object[] sample : list) {
            if ("是".equals(sample[sample.length - 1].toString())) {
                yes++;
            }else if ("否".equals(sample[sample.length - 1].toString())) {
                no++;
            }
        }
        //信息熵
        double entropy;
        if (yes == 0 || no == 0) {
            entropy = 0;
        }else {
            entropy = -(yes / (yes + no) * (Math.log10(yes / (yes + no)) / Math.log10(2)) + no / (yes + no) * (Math.log10(no / (yes + no)) / Math.log10(2)));
        }
        //封装返回对象
        Map<String, Double> entropyMap = new HashMap();
        entropyMap.put("yes", yes);
        entropyMap.put("no", no);
        entropyMap.put("entropy", entropy);
        return entropyMap;
    }
}
