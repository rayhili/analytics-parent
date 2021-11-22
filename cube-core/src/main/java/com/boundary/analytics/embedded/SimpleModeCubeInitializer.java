package com.boundary.analytics.embedded;

import com.boundary.analytics.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
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
         * - todo 如果分组里面有hierarchy？第一版本不支持
         * - 去重或者重复报错
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
        mandatoryDimensions = new ArrayList<MandatoryDimension>() {{
            MandatoryDimension mandatoryDimension1 = new MandatoryDimension();
            mandatoryDimension1.setName("j");
            mandatoryDimension1.setType(DimensionTypeEnum.Mandatory);
            add(mandatoryDimension1);

            MandatoryDimension mandatoryDimension2 = new MandatoryDimension();
            mandatoryDimension2.setName("r");
            mandatoryDimension2.setType(DimensionTypeEnum.Mandatory);
            add(mandatoryDimension2);
        }};
        return mandatoryDimensions;
    }

    @Override
    public List<Dimension> getNormalDimensions() {
        return new ArrayList<Dimension>() {
            {
                Dimension dimension1 = new Dimension();
                dimension1.setName("n1");
                add(dimension1);

                Dimension dimension2 = new Dimension();
                dimension2.setName("n2");
                add(dimension2);

                Dimension dimension3 = new Dimension();
                dimension3.setName("n3");
                add(dimension3);

                Dimension dimension4 = new Dimension();
                dimension4.setName("n4");
                add(dimension4);
            }
        };
    }

    @Override
    public List<HierarchyDimension> getHierarchyDimensions() {
        List<HierarchyDimension> dimensions = new ArrayList(6) {
            {
                HierarchyDimension dimension1 = new HierarchyDimension();
                dimension1.setName("g");
                dimension1.setType(DimensionTypeEnum.hierarchy);
                add(dimension1);

                HierarchyDimension dimension2 = new HierarchyDimension();
                dimension2.setName("h");
                dimension2.setType(DimensionTypeEnum.hierarchy);
                add(dimension2);

//                HierarchyDimension dimension3  = new HierarchyDimension();
//                dimension3.setName("x");
//                dimension3.setType(DimensionTypeEnum.hierarchy);
//                add(dimension3);
//
//                HierarchyDimension dimension4  = new HierarchyDimension();
//                dimension4.setName("y");
//                dimension4.setType(DimensionTypeEnum.hierarchy);
//                add(dimension4);
//
//                HierarchyDimension dimension5  = new HierarchyDimension();
//                dimension5.setName("xx");
//                dimension5.setType(DimensionTypeEnum.hierarchy);
//                add(dimension5);
//
//                HierarchyDimension dimension6  = new HierarchyDimension();
//                dimension6.setName("yy");
//                dimension6.setType(DimensionTypeEnum.hierarchy);
//                add(dimension6);

            }
        };
        hierarchyDimensions = dimensions;
        return hierarchyDimensions;
    }

    @Override
    public List<AggregationDimension> getAggregationDimensions() {
        List<AggregationDimension> dimensions = new ArrayList(6) {
            {
                AggregationDimension dimension1 = new AggregationDimension();
                dimension1.setName("c");
                dimension1.setAggregationGroupId("1");
                dimension1.setType(DimensionTypeEnum.Aggregate);
                add(dimension1);

                AggregationDimension dimension2 = new AggregationDimension();
                dimension2.setName("a");
                dimension2.setAggregationGroupId("1");
                dimension2.setType(DimensionTypeEnum.Aggregate);
                add(dimension2);

                AggregationDimension dimension3 = new AggregationDimension();
                dimension3.setName("e");

                dimension3.setAggregationGroupId("2");
                dimension3.setType(DimensionTypeEnum.Aggregate);
                add(dimension3);

                AggregationDimension dimension4 = new AggregationDimension();
                dimension4.setName("b");
                dimension4.setAggregationGroupId("2");
                dimension4.setType(DimensionTypeEnum.Aggregate);
                add(dimension4);

                AggregationDimension dimension5 = new AggregationDimension();
                dimension5.setName("l");
                dimension5.setAggregationGroupId("2");
                dimension5.setType(DimensionTypeEnum.Aggregate);
                add(dimension5);
            }
        };
        aggregationDimensions = dimensions;
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
