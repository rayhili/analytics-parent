package com.boundary.analytics;

/***
 * 必要纬度
 *
 * @author ray
 */
public class MandatoryDimension extends Dimension {

    public MandatoryDimension() {
        super();
        type = DimensionTypeEnum.Mandatory;
    }
}
