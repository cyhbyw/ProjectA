package com.cyh.calculate;

import java.util.regex.Matcher;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cyh.consts.Constants;
import com.cyh.data.structure.Graph;

/**
 * @author: CYH
 * @date: 2019/4/24
 */
public class ShortestRouteCalculatorTest {

    private static ShortestRouteCalculator calculator;

    @BeforeClass
    public static void beforeClass() {
        calculator = new ShortestRouteCalculator() {
            @Override
            public int[][] getGraphDistance() {
                return buildGraph().getDistance();
            }
        };
    }

    private static Graph buildGraph() {
        Graph graph = Graph.getInstance();
        graph.setDistance(0, 1, 5);
        graph.setDistance(1, 2, 4);
        graph.setDistance(2, 3, 8);
        graph.setDistance(3, 2, 8);
        graph.setDistance(3, 4, 6);
        graph.setDistance(0, 3, 5);
        graph.setDistance(0, 4, 7);
        graph.setDistance(2, 4, 2);
        graph.setDistance(4, 1, 3);
        return graph;
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
