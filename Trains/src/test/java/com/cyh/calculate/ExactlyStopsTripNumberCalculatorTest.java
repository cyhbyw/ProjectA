package com.cyh.calculate;

import java.util.regex.Matcher;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cyh.consts.Constants;
import com.cyh.data.structure.Graph;

/**
 * @author: CYH
 * @date: 2019/4/25 0025 8:45
 */
public class ExactlyStopsTripNumberCalculatorTest {

    private static ExactlyStopsTripNumberCalculator calculator;

    @BeforeClass
    public static void beforeClass() {
        calculator = new ExactlyStopsTripNumberCalculator() {
            @Override
            public int[][] getGraphDistance() {
                return buildGraph().getDistance();
            }
        };
    }

    private static Graph buildGraph() {
        Graph graph = Graph.getInstance();
        graph.setDistance(0, 1, 1);
        graph.setDistance(0, 2, 1);
        graph.setDistance(1, 3, 1);
        graph.setDistance(2, 3, 1);
        graph.setDistance(0, 3, 1);
        return graph;
    }

    /**
     * 无路径
     */
    @Test
    public void test_calculate_givenNoneRoute_expectedZero() {
        final String expectedResult = "0";
        Matcher matcher = Constants.EXACTLY_STOPS_TRIP_NUMBERS
                .matcher("The number of trips starting at D and ending at A with exactly 1 stops.");
        matcher.find();
        String result = calculator.calculate(matcher);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void test_calculate_givenValidRoute_expectedNoneZero() {
        final String expectedResult = "2";
        Matcher matcher = Constants.EXACTLY_STOPS_TRIP_NUMBERS
                .matcher("The number of trips starting at A and ending at D with exactly 2 stops.");
        matcher.find();
        String result = calculator.calculate(matcher);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void test_calculate_givenValidRoute_expectedNoneZero_2() {
        final String expectedResult = "1";
        Matcher matcher = Constants.EXACTLY_STOPS_TRIP_NUMBERS
                .matcher("The number of trips starting at A and ending at D with exactly 1 stops.");
        matcher.find();
        String result = calculator.calculate(matcher);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void test_calculate_givenValidRoute_expectedNoneZero_3() {
        final String expectedResult = "0";
        Matcher matcher = Constants.EXACTLY_STOPS_TRIP_NUMBERS
                .matcher("The number of trips starting at A and ending at D with exactly 0 stops.");
        matcher.find();
        String result = calculator.calculate(matcher);
        Assert.assertEquals(expectedResult, result);
    }

}
