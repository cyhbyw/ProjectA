package com.cyh.calculate;

import java.util.regex.Matcher;

import com.cyh.consts.Constants;
import com.cyh.data.structure.Graph;
import com.cyh.utils.TypeConvertUtils;

/**
 * 计算指定路径的总距离
 * @author: CYH
 * @date: 2019/4/21
 */
public class TotalDistanceCalculator extends CalculatorAdapter {

    private static final String SEPARATOR = "-";
    private static final String EMPTY = "";

    @Override
    public String calculate(Matcher matcher) {
        final int[][] distance = getGraphDistance();
        String data = matcher.group("vertex");
        char[] vertex = data.replaceAll(SEPARATOR, EMPTY).toCharArray();
        long totalDistance = 0L;
        for (int x = 1; x < vertex.length; x++) {
            int preVertex = TypeConvertUtils.upperCharToInt(vertex[x - 1]);
            int curVertex = TypeConvertUtils.upperCharToInt(vertex[x]);
            int d = distance[preVertex][curVertex];
            if (d == Graph.NON_EXISTENCE) {
                return Constants.NO_SUCH_ROUTE;
            }
            totalDistance += d;
        }
        return String.valueOf(totalDistance);
    }
}


