package com.boundary.analytics;

import java.io.Serializable;

/**
 * 数据立方体
 * - 通过配置文件装载
 * - 也可以通过数据库读取
 * 一条cube 对应一个dimensions的组合
 *
 * @author ray
 */
public class Cube implements Serializable {

    /**
     * cubeId，根据纬度、度量生成唯一的cube_id
     */
    private Long cubeId;

    /**
     * 纬度组合
     */
    private DimensionCombination combination;

    /**
     * 度量
     */
    private Measure measure;


    public Long getCubeId() {
        return cubeId;
    }

    public void setCubeId(Long cubeId) {
        this.cubeId = cubeId;
    }

    public DimensionCombination getCombination() {
        return combination;
    }

    public void setCombination(DimensionCombination combination) {
        this.combination = combination;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }
}
