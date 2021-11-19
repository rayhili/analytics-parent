package com.boundary.analytics;

/**
 * 纬度度量
 * @author ray
 */
public class Measure {
    /**
     * 度量名称
     */
    private String name;

    /**
     * 度量的值
     * todo  这里应该提供通用的
     */
    private String value;

    /**
     * 描述
     */
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
