package com.yeta.ml.service;

import com.yeta.ml.repository.WatermelonDataSet2Repository;
import com.yeta.ml.repository.WatermelonDataSet2aRepository;
import com.yeta.ml.repository.WatermelonDataSet3Repository;
import com.yeta.ml.repository.WatermelonDataSet4Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * 西瓜数据集逻辑层
 * Created by YETA666 on 2018/4/12 0012.
 */
@Service
public class WatermelonDataSetService {

    @Autowired
    private WatermelonDataSet2Repository watermelonDataSet2Repository;

    @Autowired
    private WatermelonDataSet2aRepository watermelonDataSet2aRepository;

    @Autowired
    private WatermelonDataSet3Repository watermelonDataSet3Repository;

    @Autowired
    private WatermelonDataSet4Repository watermelonDataSet4Repository;

    /**
     * 获取所有西瓜数据集
     * @return
     */
    public Map<String, List> findAll() {
        Map<String, List> watermelonDataSet = new HashMap<>();
        watermelonDataSet.put("watermelonDataSet2", watermelonDataSet2Repository.findAll());
        watermelonDataSet.put("watermelonDataSet2a", watermelonDataSet2aRepository.findAll());
        watermelonDataSet.put("watermelonDataSet3", watermelonDataSet3Repository.findAll());
        watermelonDataSet.put("watermelonDataSet4", watermelonDataSet4Repository.findAll());
        return watermelonDataSet;
    }
}
