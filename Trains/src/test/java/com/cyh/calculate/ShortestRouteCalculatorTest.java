package com.cyh.calculate;

import java.util.regex.Matcher;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cyh.consts.Constants;

/**
 * @author: CYH
 * @date: 2019/4/24
 */
public class ShortestRouteCalculatorTest extends BaseCalculatorTest {

    private static ShortestRouteCalculator calculator;

    @BeforeClass
    public static void beforeClass() {
        calculator = new ShortestRouteCalculator() {
            @Override
            public int[][] getGraphDistance() {
                return buildGraph();
            }
        };
    }

    private static int[][] buildGraph() {
        int[][] distance = initDistance();
        distance[0][1] = 5;
        distance[1][2] = 4;
        distance[2][3] = 8;
        distance[3][2] = 8;
        distance[3][4] = 6;
        distance[0][3] = 5;
        distance[0][4] = 7;
        distance[2][4] = 2;
        distance[4][1] = 3;
        return distance;
    }

    @Test
    public void test_calculate_givenTheSameStartEndVertex_expectedNoneZeroResult() {
        final String expectedResult = "16";
        Matcher matcher = Constants.SHORTEST_ROUTE
                .matcher("The length of the shortest route (in terms of distance to travel) from D to D.");
        matcher.find();
        String result = calculator.calculate(matcher);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void test_calculate_givenNoneExistenceRoute_expectedConstantString() {
        Matcher matcher = Constants.SHORTEST_ROUTE
                .matcher("The length of the shortest route (in terms of distance to travel) from E to A.");
        matcher.find();
        String result = calculator.calculate(matcher);
        Assert.assertEquals(Constants.NO_SUCH_ROUTE, result);
    }

    @Test
    public void test_calculate_givenDifferentStartEndVertex_expectedShortestRoute() {
        final String expectedResult = "9";
        Matcher matcher = Constants.SHORTEST_ROUTE
                .matcher("The length of the shortest route (in terms of distance to travel) from D to B.");
        matcher.find();
        String result = calculator.calculate(matcher);
        Assert.assertEquals(expectedResult, result);
    }

}
