package com.boundary.analytics.embedded;

import com.boundary.analytics.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/***
 * 从配置文件初始化dimensions
 * @author ray
 */
public class SimpleModeCubeInitializer implements CubeInitializer {

    private final static Logger logger =
            LoggerFactory.getLogger(SimpleModeCubeInitializer.class);

    private Map<String, Dimension> dimensions;

    private List<MandatoryDimension> mandatoryDimensions;

    private List<HierarchyDimension> hierarchyDimensions;

    private List<AggregationDimension> aggregationDimensions;

    private List<Measure> measures;

    private DimensionDefineProperties defineProperties;

    @Override
    public void initialize() {
        logger.debug("initialize dimensions in simple mode.");
        /**
         * validation:
         * - aggregation 的字段不能出现在mandatoryDimensions中
         * -
         */
        //load dimensions

        //load measures
    }

    @Override
    public Map<String, Dimension> getDimensions() {
        return dimensions;
    }

    @Override
    public List<MandatoryDimension> getMandatoryDimensions() {
        return mandatoryDimensions;
    }

    @Override
    public List<HierarchyDimension> getHierarchyDimensions() {
        return hierarchyDimensions;
    }

    @Override
    public List<AggregationDimension> getAggregationDimensions() {
        return aggregationDimensions;
    }

    @Override
    public List<Measure> getMeasures() {
        return measures;
    }

    public DimensionDefineProperties getDefineProperties() {
        return defineProperties;
    }

    public void setDefineProperties(DimensionDefineProperties defineProperties) {
        this.defineProperties = defineProperties;
    }
}
