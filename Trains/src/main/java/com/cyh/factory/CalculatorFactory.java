package com.cyh.factory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cyh.calculate.Calculator;
import com.cyh.calculate.ExactlyStopsTripNumberCalculator;
import com.cyh.calculate.MaximumStopsTripNumberCalculator;
import com.cyh.calculate.RouteNumberCalculator;
import com.cyh.calculate.ShortRouteCalculator;
import com.cyh.calculate.TotalDistanceCalculator;

/**
 * @author: CYH
 * @date: @date: 2019/4/21
 */
public final class CalculatorFactory {

    public static String calculate(String input) {
        for (CalculatorTypeEnum type : CalculatorTypeEnum.values()) {
            Matcher matcher = type.pattern.matcher(input);
            if (matcher.find()) {
                return type.calculator.calculate(input, matcher);
            }
        }
        throw new IllegalArgumentException("Illegal input: " + input);
    }

    enum CalculatorTypeEnum {
        /**
         * 计算指定路径的总距离
         * 使用 vertex 这个组来获取到顶点信息
         */
        ROUTE_DISTANCE(Pattern.compile("The distance of the route (?<vertex>([A-Z](-[A-Z])+))."), new TotalDistanceCalculator()),
        /**
         * 给出最大停顿次数，计算方案数量
         * 使用了三个组： start end maximumStops 分别表示 起点、终点、最大停顿次数
         */
        MAXIMUM_STOPS_TRIP_NUMBERS(Pattern.compile("The number of trips starting at (?<start>[A-Z]) and ending at (?<end>[A-Z]) with a maximum of (?<maximumStops>(\\d)+) stops."), new MaximumStopsTripNumberCalculator()),
        /**
         * 给出精确停顿次数，计算方案数量
         * 使用了三个组： start end exactlyStops 分别表示 起点、终点、精确停顿次数
         */
        EXACTLY_STOPS_TRIP_NUMBERS(Pattern.compile("The number of trips starting at (?<start>[A-Z]) and ending at (?<end>[A-Z]) with exactly (?<exactlyStops>(\\d)+) stops."), new ExactlyStopsTripNumberCalculator()),
        /**
         * 计算最短路径
         * 使用了两个组来分别获取 start end
         */
        SHORTEST_ROUTE(Pattern.compile("The length of the shortest route \\(in terms of distance to travel\\) from (?<start>[A-Z]) to (?<end>[A-Z])."), new ShortRouteCalculator()),
        /**
         * 在总距离满足的前提下，计算方案数量
         * 使用了三个分组： start end maxDistance 分别表示 起点、终点、最大距离
         */
        DIFFERENT_ROUTES(Pattern.compile("The number of different routes from (?<start>[A-Z]) to (?<end>[A-Z]) with a distance of less than (?<maxDistance>(\\d)+)."), new RouteNumberCalculator());

        Pattern pattern;
        Calculator calculator;

        CalculatorTypeEnum(Pattern pattern, Calculator calculator) {
            this.pattern = pattern;
            this.calculator = calculator;
        }
    }

}
