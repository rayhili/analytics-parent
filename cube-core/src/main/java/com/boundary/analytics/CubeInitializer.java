package com.boundary.analytics;

import java.util.List;
import java.util.Map;

/***
 * dimension 初始化类
 * @author ray
 */
public interface CubeInitializer {

    /**
     * 初始化
     */
    void initialize();

    /**
     * 获取所有的纬度
     *
     * @return
     */
    Map<String, Dimension> getDimensions();

    /***
     * 获取所有的必要纬度
     * @return
     */
    List<MandatoryDimension> getMandatoryDimensions();

    /**
     * 获取所有的层次纬度
     *
     * @return
     */
    List<HierarchyDimension> getHierarchyDimensions();

    /***
     * 获取所有的聚合纬度
     * @return
     */
    List<AggregationDimension> getAggregationDimensions();

    /**
     * 获取所有的度量
     *
     * @return
     */
    List<Measure> getMeasures();
}
