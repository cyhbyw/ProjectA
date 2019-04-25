package com.cyh.calculate;

import java.util.regex.Matcher;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cyh.consts.Constants;

/**
 * @author: CYH
 * @date: 2019/4/25 0025 8:36
 */
public class MaximumStopsTripNumberCalculatorTest extends BaseCalculatorTest {

    private static MaximumStopsTripNumberCalculator calculator;

    @BeforeClass
    public static void beforeClass() {
        calculator = new MaximumStopsTripNumberCalculator() {
            @Override
            public int[][] getGraphDistance() {
                return buildGraph();
            }
        };
    }

    private static int[][] buildGraph() {
        int[][] distance = initDistance();
        distance[0][1] = 1;
        distance[1][2] = 1;
        distance[2][3] = 1;
        distance[0][3] = 1;
        distance[1][3] = 1;
        return distance;
    }

    /**
     * 无路径
     */
    @Test
    public void test_calculate_givenNoneRoute_expectedZero() {
        final String expectedResult = "0";
        Matcher matcher = Constants.MAXIMUM_STOPS_TRIP_NUMBERS
                .matcher("The number of trips starting at D and ending at C with a maximum of 100 stops.");
        matcher.find();
        String result = calculator.calculate(matcher);
        Assert.assertEquals(expectedResult, result);
    }

    /**
     * 有路径，给出一个较大的停顿次数
     */
    @Test
    public void test_calculate_givenValidRoute_expectedNoneZeroResult() {
        final String expectedResult = "3";
        Matcher matcher = Constants.MAXIMUM_STOPS_TRIP_NUMBERS
                .matcher("The number of trips starting at A and ending at D with a maximum of 3 stops.");
        matcher.find();
        String result = calculator.calculate(matcher);
        Assert.assertEquals(expectedResult, result);
    }

    /**
     * 有路径，给出一个适中的停顿次数
     */
    @Test
    public void test_calculate_givenValidRoute_expectedNoneZeroResult_2() {
        final String expectedResult = "2";
        Matcher matcher = Constants.MAXIMUM_STOPS_TRIP_NUMBERS
                .matcher("The number of trips starting at A and ending at D with a maximum of 2 stops.");
        matcher.find();
        String result = calculator.calculate(matcher);
        Assert.assertEquals(expectedResult, result);
    }

    /**
     * 有路径，给出一个较小的停顿次数
     */
    @Test
    public void test_calculate_givenValidRoute_expectedNoneZeroResult_3() {
        final String expectedResult = "1";
        Matcher matcher = Constants.MAXIMUM_STOPS_TRIP_NUMBERS
                .matcher("The number of trips starting at A and ending at D with a maximum of 1 stops.");
        matcher.find();
        String result = calculator.calculate(matcher);
        Assert.assertEquals(expectedResult, result);
    }

    /**
     * 有路径，给出停顿次数为0
     */
    @Test
    public void test_calculate_givenValidRouteAndZeroStops_expectedZero() {
        final String expectedResult = "0";
        Matcher matcher = Constants.MAXIMUM_STOPS_TRIP_NUMBERS
                .matcher("The number of trips starting at A and ending at D with a maximum of 0 stops.");
        matcher.find();
        String result = calculator.calculate(matcher);
        Assert.assertEquals(expectedResult, result);
    }
}
