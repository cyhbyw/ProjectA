package com.cyh.calculate;

import java.util.regex.Matcher;

import com.cyh.data.structure.Graph;
import com.cyh.utils.TypeConvertUtils;

/**
 * 计算指定路径的总距离
 * @author: yanhua.chen
 * @date: 2019/4/20 16:03
 */
public class TotalDistanceCalculator implements Calculator {

    private static final String SEPARATOR = "-";
    private static final String EMPTY = "";

    @Override
    public String calculate(String input, Matcher matcher) {
        String data = matcher.group("vertex");
        char[] vertex = data.replaceAll(SEPARATOR, EMPTY).toCharArray();
        long totalDistance = 0L;
        for (int x = 1; x < vertex.length; x++) {
            int preVertex = TypeConvertUtils.upperCharToInt(vertex[x - 1]);
            int curVertex = TypeConvertUtils.upperCharToInt(vertex[x]);
            int distance = Graph.getInstance().getDistance()[preVertex][curVertex];
            if (distance == Graph.NON_EXISTENCE) {
                return "No Such Route";
            }
            totalDistance += distance;
        }
        return String.valueOf(totalDistance);
    }
}


