package com.boundary.analytics;

/**
 * 纬度类型
 *
 * @author ray
 */
public enum DimensionTypeEnum {

    /**
     * 普通纬度
     */
    Normal,

    /**
     * 聚合纬度
     */
    Aggregate,

    /**
     * 必要纬度
     */
    Mandatory,

    /**
     * 层级纬度
     */
    hierarchy
}
