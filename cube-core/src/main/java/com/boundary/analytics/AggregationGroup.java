package com.boundary.analytics;

/***
 * 纬度聚合组
 * todo 可以定义跟其他组互斥，需要指定互斥id
 * @author ray
 */
public class AggregationGroup {
    /**
     * 分组id，唯一
     */
    private String groupId;

    /**
     * 是否开启，默认true
     * -如果不开启，不参与组合
     */
    private boolean enabled = true;

    /**
     * 描述
     */
    private String description;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
