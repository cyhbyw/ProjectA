package com.cyh.factory;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cyh.calculate.Calculator;
import com.cyh.calculate.ExactlyStopsTripNumberCalculator;
import com.cyh.calculate.MaximumStopsTripNumberCalculator;
import com.cyh.calculate.RouteNumberCalculator;
import com.cyh.calculate.ShortestRouteCalculator;
import com.cyh.calculate.TotalDistanceCalculator;
import com.cyh.consts.Constants;
import com.cyh.utils.AssertUtils;

/**
 * @author: CYH
 * @date: 2019/4/21
 */
public final class CalculatorFactory {

    public static String calculate(String input) {
        AssertUtils.isTrue(Objects.nonNull(input) && !input.isEmpty(), "非法输入: " + input);
        for (CalculatorTypeEnum type : CalculatorTypeEnum.values()) {
            Matcher matcher = type.pattern.matcher(input);
            if (matcher.find()) {
                return type.calculator.calculate(matcher);
            }
        }
        throw new IllegalArgumentException("Illegal input: " + input);
    }

    enum CalculatorTypeEnum {
        /**
         * 计算指定路径的总距离
         */
        TOTAL_DISTANCE(Constants.TOTAL_DISTANCE, new TotalDistanceCalculator()),
        /**
         * 给出最大停顿次数，计算方案数量
         */
        MAXIMUM_STOPS_TRIP_NUMBERS(Constants.MAXIMUM_STOPS_TRIP_NUMBERS, new MaximumStopsTripNumberCalculator()),
        /**
         * 给出精确停顿次数，计算方案数量
         */
        EXACTLY_STOPS_TRIP_NUMBERS(Constants.EXACTLY_STOPS_TRIP_NUMBERS, new ExactlyStopsTripNumberCalculator()),
        /**
         * 计算最短路径
         */
        SHORTEST_ROUTE(Constants.SHORTEST_ROUTE, new ShortestRouteCalculator()),
        /**
         * 在总距离满足的前提下，计算方案数量
         */
        ROUT_NUMBER(Constants.ROUT_NUMBER, new RouteNumberCalculator());

        Pattern pattern;
        Calculator calculator;

        CalculatorTypeEnum(Pattern pattern, Calculator calculator) {
            this.pattern = pattern;
            this.calculator = calculator;
        }
    }

}
