package com.cyh.calculate;

import java.util.regex.Matcher;

import com.cyh.utils.TypeConvertUtils;

/**
 * 给出精确停顿次数，计算方案数量
 * @author: yanhua.chen
 * @date: 2019/4/20 16:12
 */
public class ExactlyStopsTripNumberCalculator implements Calculator {

    @Override
    public String calculate(String input, Matcher matcher) {
        int start = TypeConvertUtils.stringToInt(matcher.group("start"));
        int end = TypeConvertUtils.stringToInt(matcher.group("end"));
        int exactlyStops = Integer.valueOf(matcher.group("exactlyStops"));
        System.out.println(String.format("%d %d %d", start, end, exactlyStops));
        return "给出精确停顿次数，计算方案数量";
    }
}
