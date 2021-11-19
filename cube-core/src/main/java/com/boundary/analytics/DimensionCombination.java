package com.boundary.analytics;

import java.util.List;

/***
 * 纬度组合
 *
 * @author ray
 */
public class DimensionCombination {
    /**
     * 多维度组合
     */
    private List<Dimension> dimensions;

    public List<Dimension> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<Dimension> dimensions) {
        this.dimensions = dimensions;
    }
}
