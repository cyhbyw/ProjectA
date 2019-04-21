package com.cyh.calculate;

import java.util.regex.Matcher;

import com.cyh.utils.TypeConvertUtils;

/**
 * 给出最大停顿次数，计算方案数量
 * @author: yanhua.chen
 * @date: 2019/4/20 16:11
 */
public class MaximumStopsTripNumberCalculator extends AbstractTripNumberCalculator {

    @Override
    public String calculate(String input, Matcher matcher) {
        int start = TypeConvertUtils.stringToInt(matcher.group("start"));
        int end = TypeConvertUtils.stringToInt(matcher.group("end"));
        int maximumStops = Integer.valueOf(matcher.group("maximumStops"));
        return BFS(start, end, maximumStops);
    }

    @Override
    boolean satisfyCondition(int stops, int maxOrExactlyStops) {
        return stops <= maxOrExactlyStops;
    }
}
