package com.boundary.analytics;

import com.boundary.analytics.embedded.SimpleModeCubeInitializer;
import com.boundary.analytics.exception.CubeInitializeFailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;

/***
 * cube装配器
 * - 默认模式，通过normal / aggregation / hierarchy / mandatory dimensions 装配所有的组合
 * @author ray
 */
public class DefaultCubeAssembler implements CubeAssembler {
    private final static Logger logger =
            LoggerFactory.getLogger(DefaultCubeAssembler.class);

    /***
     * 初始化
     */
    private CubeInitializer initializer;

    /***
     * 所有dimension 的组合
     * */
    private List<DimensionCombination> dimensionCombinations;

    /***
     * 所有的纬度
     */
    private final List<Measure> measures;

    /***
     * 立方体
     */
    private List<Cube> cubes;

    public DefaultCubeAssembler(SimpleModeCubeInitializer initializer) {
        Assert.notNull(initializer, "dimension initializer is null");
        this.initializer = initializer;
        this.measures = initializer.getMeasures();
    }


    public void assemble() throws CubeInitializeFailException {
        logger.info("dimension combination is assembled in simple mode.");

        if (CollectionUtils.isEmpty(initializer.getDimensions())) {
            throw new CubeInitializeFailException("dimensions is empty.");
        }

        if (CollectionUtils.isEmpty(measures)) {
            throw new CubeInitializeFailException("measures is empty");
        }

        assembleDimensions();

        if (CollectionUtils.isEmpty(dimensionCombinations)) {
            throw new CubeInitializeFailException("assemble dimension combinations was failed.");
        }

        cubes = new ArrayList<>();
        long index = 1;
        Iterator<Measure> measureIterator = measures.iterator();
        Iterator<DimensionCombination> dimensionCombinationIterator = dimensionCombinations.iterator();
        while (measureIterator.hasNext()) {
            Measure measure = measureIterator.next();

            while (dimensionCombinationIterator.hasNext()) {
                DimensionCombination dimensionCombination = dimensionCombinationIterator.next();

                Cube cube = new Cube();
                cube.setCubeId(index++);
                cube.setCombination(dimensionCombination);
                cube.setMeasure(measure);
                cubes.add(cube);
            }
        }
    }

    /**
     * 将dimensions 进行组合
     */
    private void assembleDimensions() {

        /**
         * key 存放的是dimension的key，逗号隔开，顺序不一样会重复，所以需要通过list排序生成
         */
        Map<String, List<DimensionCombination>> combinationMap = new HashMap<>();

        List<MandatoryDimension> mandatoryDimensions = initializer.getMandatoryDimensions();

        buildAggregationDimensions();

        //通过combinationMap生成 DimensionCombination列表
        if (!CollectionUtils.isEmpty(combinationMap)) {
            //todo
        }


    }

    //所有层次的组合
    private Map<String, List<Dimension>> buildHierarchyDimensions() {
        return null;
    }

    //所有分组的组合
    private Map<String, List<Dimension>> buildAggregationDimensions() {
        List<AggregationDimension> aggregationDimensions = initializer.getAggregationDimensions();
        if (!CollectionUtils.isEmpty(aggregationDimensions)) {
            for (AggregationDimension aggregationDimension : aggregationDimensions) {


            }
        }
        return null;
    }

    private String generateDimensionsKey(List<Dimension> dimensions) {

        return "";
    }

    @Override
    public void setInitializer(CubeInitializer initializer) {
        this.initializer = initializer;
    }

    @Override
    public List<Cube> getCubes() {
        return cubes;
    }

    @Override
    public List<DimensionCombination> getDimensionCombinations() {
        return dimensionCombinations;
    }

    @Override
    public List<Measure> getMeasures() {
        return measures;
    }
}
