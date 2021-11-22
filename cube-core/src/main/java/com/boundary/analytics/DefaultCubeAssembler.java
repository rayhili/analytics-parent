package com.boundary.analytics;

import com.boundary.analytics.embedded.SimpleModeCubeInitializer;
import com.boundary.analytics.exception.CubeInitializeFailException;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
    private List<DimensionCombination> dimensionCombinations = new ArrayList<>(8);

    /***
     * 所有的纬度
     */
    private final List<Measure> measures;

    /***
     * 立方体
     */
    private List<Cube> cubes;

    public DefaultCubeAssembler(CubeInitializer initializer) {
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

        cubes = new ArrayList<>(8);
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
    private Map<String, List<Dimension>> assembleDimensions() {

        /**
         * key 存放的是dimension的key，逗号隔开，顺序不一样会重复，所以需要通过list排序生成
         */
        Map<String, List<Dimension>> mandatoryDimensionMap = new HashMap<>(8);

        //必要
        List<MandatoryDimension> mandatoryDimensions = initializer.getMandatoryDimensions();
        if (!CollectionUtils.isEmpty(mandatoryDimensions)) {
            mandatoryDimensionMap.put(generateDimensionsKey(mandatoryDimensions), new ArrayList<>(mandatoryDimensions));
        }

        //常规
        List<Dimension> normalDimensions = initializer.getNormalDimensions();


        //分组
        Map<String, List<Dimension>> aggregationDimensionMap = buildAggregationDimensions();

        //分层
        Map<String, List<Dimension>> hierarchyDimensionMap = buildHierarchyDimensions();

        //组合
        Map<String, List<Dimension>> combineMap = combine(
                combine(
                        combine(aggregationDimensionMap, hierarchyDimensionMap),
                        mandatoryDimensionMap
                ),
                combineDimensions(normalDimensions)
        );

        if (CollectionUtils.isEmpty(combineMap)) {
            logger.info("dimensions combine map is empty");
            return combineMap;
        }

        combineMap.keySet().forEach(combine -> {
            DimensionCombination dimensionCombination = new DimensionCombination(combineMap.get(combine));
            dimensionCombinations.add(dimensionCombination);
        });

        return combineMap;
    }

    private Map<String, List<Dimension>> combine(
            Map<String, List<Dimension>> leftMap, Map<String, List<Dimension>> rightMap) {
        Map<String, List<Dimension>> map = new HashMap<>(8);

        if (CollectionUtils.isEmpty(leftMap)
                && CollectionUtils.isEmpty(rightMap)) {
            logger.warn("combine map are both empty.");
            return map;
        }

        if (!CollectionUtils.isEmpty(leftMap)) {
            leftMap.keySet().forEach(left -> {
                if (!CollectionUtils.isEmpty(rightMap)) {
                    rightMap.keySet().forEach(right -> {
                        List<Dimension> leftList = leftMap.get(left);
                        List<Dimension> rightList = rightMap.get(right);

                        List<Dimension> list = new ArrayList<>();
                        list.addAll(leftList);
                        list.addAll(rightList);
                        map.put(generateDimensionsKey(list), list);
                    });
                } else {
                    map.put(left, leftMap.get(left));
                }
            });
        } else {
            combine(rightMap, leftMap);
        }
        return map;
    }


    //所有层次的组合
    private Map<String, List<Dimension>> buildHierarchyDimensions() {
        Map<String, List<Dimension>> map = new HashMap<>(8);

        List<HierarchyDimension> hierarchyDimensions = initializer.getHierarchyDimensions();

        hierarchyDimensions.sort(
                Comparator.comparing(HierarchyDimension::getOrder)
                        .thenComparing(HierarchyDimension::getName));
        if (!CollectionUtils.isEmpty(hierarchyDimensions)) {
            for (int j = 1; j < hierarchyDimensions.size() + 1; j++) {
                List<HierarchyDimension> subDimensions = hierarchyDimensions.subList(0, j);
                map.put(generateDimensionsKey(subDimensions), new ArrayList<>(subDimensions));
            }
        }

        return map;
    }

    //所有分组的组合
    private Map<String, List<Dimension>> buildAggregationDimensions() {
        Map<String, List<Dimension>> map = new HashMap<>(8);
        List<AggregationDimension> aggregationDimensions = initializer.getAggregationDimensions();

        if (CollectionUtils.isEmpty(aggregationDimensions)) {
            return map;
        }

        Map<String, List<Dimension>> groupMap = new HashMap<>(8);

        for (AggregationDimension aggregationDimension : aggregationDimensions) {
            if (groupMap.containsKey(aggregationDimension.getAggregationGroupId())) {
                groupMap.get(aggregationDimension.getAggregationGroupId()).add(aggregationDimension);
            } else {
                List<Dimension> list = new ArrayList<>();
                list.add(aggregationDimension);
                groupMap.put(aggregationDimension.getAggregationGroupId(), list);
            }
        }

        groupMap.keySet().forEach(key -> {
            map.put(generateDimensionsKey(groupMap.get(key)), groupMap.get(key));
        });

        return map;
    }

    //组合
    private Map<String, List<Dimension>> combineDimensions(List<Dimension> dimensions) {
        Map<String, List<Dimension>> map = new HashMap<>(8);

        if (CollectionUtils.isEmpty(dimensions)) {
            return map;
        }

        dimensions.sort(Comparator.comparing(Dimension::getName));

        for (int i = 0; i < dimensions.size(); i++) {
            for (int j = i + 1; j < dimensions.size() + 1; j++) {
                List<Dimension> subDimensions = dimensions.subList(i, j);
                map.put(generateDimensionsKey(subDimensions), subDimensions);
            }
        }
        return map;
    }


    private <T extends Dimension> String generateDimensionsKey(List<T> dimensions) {
        if (CollectionUtils.isEmpty(dimensions)) {
            return null;
        }

        List<String> dimensionNames = new ArrayList<>();
        dimensions.sort((Comparator.comparing(Dimension::getName)));

        dimensions.forEach(dimension -> dimensionNames.add(dimension.getName()));

        return StringUtils.collectionToCommaDelimitedString(dimensionNames);
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

    public static void main(String[] args) {


        long start = System.currentTimeMillis();
        DefaultCubeAssembler defaultCubeAssembler = new DefaultCubeAssembler(new SimpleModeCubeInitializer());
//        //分组
//        Map<String, List<Dimension>> aggregationDimensionMap = defaultCubeAssembler.buildAggregationDimensions();
//
//        //分层
//        Map<String, List<Dimension>> hierarchyDimensionMap = defaultCubeAssembler.buildHierarchyDimensions();
//
//        //分组和分层组合
//
//        Map<String, List<Dimension>> map1 = defaultCubeAssembler.combine(aggregationDimensionMap, hierarchyDimensionMap);
////
        Map<String, List<Dimension>> map = defaultCubeAssembler.assembleDimensions();

//        Map<String, List<Dimension>> map = defaultCubeAssembler.combine(map1, new HashMap<>());

        System.out.println("exec:" + (System.currentTimeMillis() - start));
        map.keySet().forEach(key -> {
            System.out.println("k:" + key + ",list:" + new Gson().toJson(map.get(key)));
        });
    }
}
