package com.boundary.analytics;

import static com.boundary.analytics.DimensionTypeEnum.Aggregate;

/***
 * 聚合dimensions
 *
 * 不分顺序，在一同聚合组里就表示必须同时出现。
 *
 * @author ray
 */
public class AggregationDimension extends Dimension {
    /**
     * 聚合分组id
     *
     * @see AggregationGroup
     */
    private String aggregationGroupId;

    public AggregationDimension() {
        super();
        type = Aggregate;
    }

    public String getAggregationGroupId() {
        return aggregationGroupId;
    }

    public void setAggregationGroupId(String aggregationGroupId) {
        this.aggregationGroupId = aggregationGroupId;
    }
}
