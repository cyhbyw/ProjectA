package com.cyh.calculate;

import java.util.regex.Matcher;

import com.cyh.utils.TypeConvertUtils;

/**
 * 给出精确停顿次数，计算方案数量
 * @author: CYH
 * @date: 2019/4/21
 */
public class ExactlyStopsTripNumberCalculator extends AbstractTripNumberCalculator {

    @Override
    public String calculate(Matcher matcher) {
        int start = TypeConvertUtils.stringToInt(matcher.group("start"));
        int end = TypeConvertUtils.stringToInt(matcher.group("end"));
        int exactlyStops = Integer.valueOf(matcher.group("exactlyStops"));
        return BFS(start, end, exactlyStops);
    }

    @Override
    boolean satisfyCondition(int stops, int maxOrExactlyStops) {
        return stops == maxOrExactlyStops;
    }
}
