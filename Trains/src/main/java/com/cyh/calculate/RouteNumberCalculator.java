package com.cyh.calculate;

import java.util.regex.Matcher;

import com.cyh.utils.TypeConvertUtils;

/**
 * 在总距离满足的前提下，计算方案数量
 * @author: yanhua.chen
 * @date: 2019/4/20 16:04
 */
public class RouteNumberCalculator implements Calculator {

    @Override
    public String calculate(String input, Matcher matcher) {
        int start = TypeConvertUtils.stringToInt(matcher.group("start"));
        int end = TypeConvertUtils.stringToInt(matcher.group("end"));
        int maxDistance = Integer.valueOf(matcher.group("maxDistance"));
        System.out.println(String.format("%d %d %d", start, end, maxDistance));
        return "在总距离满足的前提下，计算方案数量";
    }
}
