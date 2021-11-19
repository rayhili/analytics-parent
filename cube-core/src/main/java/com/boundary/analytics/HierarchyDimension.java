package com.boundary.analytics;

/***
 * 层次纬度
 *
 * 需要定义层次的顺序
 * @author ray
 */
public class HierarchyDimension extends Dimension {
    /**
     * 层次id
     *
     * @see Hierarchy
     */
    private String hierarchyId;

    /**
     * 顺序
     */
    private int order;


    public HierarchyDimension() {
        super();
        this.type = DimensionTypeEnum.hierarchy;
    }

    public String getHierarchyId() {
        return hierarchyId;
    }

    public void setHierarchyId(String hierarchyId) {
        this.hierarchyId = hierarchyId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
