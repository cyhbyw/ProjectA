package com.cyh.calculate;

import java.util.regex.Matcher;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cyh.consts.Constants;

/**
 * @author: CYH
 * @date: 2019/4/25
 */
public class RouteNumberCalculatorTest extends BaseCalculatorTest {

    private static RouteNumberCalculator calculator;

    @BeforeClass
    public static void beforeClass() {
        calculator = new RouteNumberCalculator() {
            @Override
            public int[][] getGraphDistance() {
                return buildGraph();
            }
        };
    }

    private static int[][] buildGraph() {
        int[][] distance = initDistance();
        distance[0][1] = 1;
        distance[0][2] = 2;
        distance[1][3] = 3;
        distance[2][3] = 4;
        return distance;
    }

    /**
     * 无路径
     */
    @Test
    public void test_calculate_givenNoRoute_expectedZero() {
        final String expectedResult = "0";
        Matcher matcher = Constants.ROUT_NUMBER
                .matcher("The number of different routes from A to A with a distance of less than 100.");
        matcher.find();
        String result = calculator.calculate(matcher);
        Assert.assertEquals(expectedResult, result);
    }

    /**
     * 有路径，给出一个很大的最大距离，期望返回多个结果
     */
    @Test
    public void test_calculate_givenValidRoute_expectedResult() {
        final String expectedResult = "2";
        Matcher matcher = Constants.ROUT_NUMBER
                .matcher("The number of different routes from A to D with a distance of less than 7.");
        matcher.find();
        String result = calculator.calculate(matcher);
        Assert.assertEquals(expectedResult, result);
    }

    /**
     * 有路径，给出一个适中的距离，期望返回一个结果
     */
    @Test
    public void test_calculate_givenValidRoute_expectedResult_2() {
        final String expectedResult = "1";
        Matcher matcher = Constants.ROUT_NUMBER
                .matcher("The number of different routes from A to D with a distance of less than 6.");
        matcher.find();
        String result = calculator.calculate(matcher);
        Assert.assertEquals(expectedResult, result);
    }

    /**
     * 虽然有路径，但是给出一个很小的距离，期望返回0个结果
     */
    @Test
    public void test_calculate_givenValidRoute_expectedResult_3() {
        final String expectedResult = "0";
        Matcher matcher = Constants.ROUT_NUMBER
                .matcher("The number of different routes from A to D with a distance of less than 4.");
        matcher.find();
        String result = calculator.calculate(matcher);
        Assert.assertEquals(expectedResult, result);
    }
}
