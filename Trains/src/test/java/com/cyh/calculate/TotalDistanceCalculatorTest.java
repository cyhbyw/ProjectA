package com.cyh.calculate;

import java.util.regex.Matcher;

import org.junit.Assert;
import org.junit.Test;

import com.cyh.consts.Constants;

/**
 * @author: CYH
 * @date: 2019/4/24
 */
public class TotalDistanceCalculatorTest extends BaseCalculatorTest {

    private TotalDistanceCalculator totalDistanceCalculator;

    private void init() {
        totalDistanceCalculator = new TotalDistanceCalculator() {
            @Override
            public int[][] getGraphDistance() {
                return buildGraph();
            }
        };
    }

    private int[][] buildGraph() {
        int[][] distance = initDistance();
        distance[0][1] = 100;
        distance[1][2] = 200;
        return distance;
    }

    @Test
    public void test_calculate_givenValidGraph_expectedValidResult() {
        init();
        final String expectedResult = "300";
        Matcher matcher = Constants.TOTAL_DISTANCE.matcher("The distance of the route A-B-C.");
        matcher.find();
        String result = totalDistanceCalculator.calculate(matcher);
        Assert.assertEquals(expectedResult, result);
    }

    /**
     * 图信息合法，但是没有合适的路径
     */
    @Test
    public void test_calculate_givenInvalidGraph_expectedConstResult() {
        init();
        Matcher matcher = Constants.TOTAL_DISTANCE.matcher("The distance of the route A-C.");
        matcher.find();
        String result = totalDistanceCalculator.calculate(matcher);
        Assert.assertEquals(Constants.NO_SUCH_ROUTE, result);
    }

    /**
     * 图信息为空
     */
    @Test
    public void test_calculate_givenEmptyGraph_expectedConstResult() {
        Matcher matcher = Constants.TOTAL_DISTANCE.matcher("The distance of the route A-C.");
        matcher.find();
        String result = new TotalDistanceCalculator().calculate(matcher);
        Assert.assertEquals(Constants.NO_SUCH_ROUTE, result);
    }
}
