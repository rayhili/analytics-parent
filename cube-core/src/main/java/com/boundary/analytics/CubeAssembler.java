package com.boundary.analytics;

import com.boundary.analytics.exception.CubeInitializeFailException;

import java.util.List;

/**
 * dimensions 组合 装配器
 *
 * @author ray
 */
public interface CubeAssembler {

    /***
     * 装载 or 重新装载
     * @return dimensions的组合
     * @throws CubeInitializeFailException 装载失败
     */
    void assemble() throws CubeInitializeFailException;

    /**
     * 设置初始化工具，可以动态设置，重新组装
     *
     * @param initializer
     */
    void setInitializer(CubeInitializer initializer);

    /**
     * 获取所有的纬度组合
     *
     * @return
     */
    List<DimensionCombination> getDimensionCombinations();

    /***
     * 获取所有的cubes
     * @return
     */
    List<Cube> getCubes();

    /**
     * 获取所有的度量
     *
     * @return
     */
    List<Measure> getMeasures();
}
