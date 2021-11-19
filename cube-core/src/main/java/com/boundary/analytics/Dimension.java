package com.boundary.analytics;

import java.io.Serializable;

/***
 * 纬度
 * @author ray
 */
public class Dimension implements Serializable {

    /**
     * 纬度名称，todo 跟数据库要映射
     */
    protected String name;

    /**
     * 纬度类型
     */
    protected DimensionTypeEnum type = DimensionTypeEnum.Normal;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DimensionTypeEnum getType() {
        return type;
    }

    public void setType(DimensionTypeEnum type) {
        this.type = type;
    }
}
