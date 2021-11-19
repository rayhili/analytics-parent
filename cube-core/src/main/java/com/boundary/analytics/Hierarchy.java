package com.boundary.analytics;

import java.io.Serializable;

/***
 * 层级
 * @author ray
 */
public class Hierarchy implements Serializable {
    /**
     * 层级id，唯一
     */
    private String hierarchyId;

    /**
     * 是否启用，默认是启用
     * - 如果没有启用，dimension不参与组装
     */
    private boolean enabled = true;

    /**
     * 描述
     */
    private String description;

    public String getHierarchyId() {
        return hierarchyId;
    }

    public void setHierarchyId(String hierarchyId) {
        this.hierarchyId = hierarchyId;
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
