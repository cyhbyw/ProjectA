package com.cyh.calculate;

import java.util.regex.Matcher;

import com.cyh.consts.Constants;
import com.cyh.data.structure.Graph;
import com.cyh.utils.TypeConvertUtils;

/**
 * 计算最短路径
 * @author: CYH
 * @date: 2019/4/21
 */
public class ShortRouteCalculator extends CalculatorAdapter {

    @Override
    public String calculate(Matcher matcher) {
        int start = TypeConvertUtils.stringToInt(matcher.group("start"));
        int end = TypeConvertUtils.stringToInt(matcher.group("end"));
        return dijastra(start, end);
    }

    /**
     * Dijastra 算法计算最短路径
     * @param start 起点
     * @param end 终点
     * @return 最短路径
     */
    private String dijastra(int start, int end) {
        final int[][] distance = getGraphDistance();
        int[] minDis = initMinDistance();
        minDis[start] = 0;
        boolean[] visited = initVisited();
        boolean vertexEquals = (start == end);
        boolean reset = false;
        for (int loop = 0; loop < Constants.MAX_POINT_COUNT; loop++) {
            int min = Graph.NON_EXISTENCE;
            int u = Graph.NON_EXISTENCE;
            for (int i = 0; i < Constants.MAX_POINT_COUNT; i++) {
                if (!visited[i] && minDis[i] != Graph.NON_EXISTENCE) {
                    if (min == Graph.NON_EXISTENCE || min > minDis[i]) {
                        min = minDis[i];
                        u = i;
                    }
                }
            }
            if (min == Graph.NON_EXISTENCE) {
                break;
            }
            visited[u] = true;
            for (int j = 0; j < Constants.MAX_POINT_COUNT; j++) {
                if (!visited[j] && distance[u][j] != Graph.NON_EXISTENCE) {
                    if (minDis[j] == Graph.NON_EXISTENCE || minDis[j] > minDis[u] + distance[u][j]) {
                        minDis[j] = minDis[u] + distance[u][j];
                    }
                }
            }
            if (vertexEquals && u == start && !reset) {
                visited[start] = false;
                minDis[start] = Graph.NON_EXISTENCE;
                reset = true;
            }

        }
        return minDis[end] == Graph.NON_EXISTENCE ? Constants.NO_SUCH_ROUTE : String.valueOf(minDis[end]);
    }

    private int[] initMinDistance() {
        int[] minDis = new int[Constants.MAX_POINT_COUNT];
        for (int i = 0; i < minDis.length; i++) {
            minDis[i] = Graph.NON_EXISTENCE;
        }
        return minDis;
    }

    private boolean[] initVisited() {
        boolean[] visited = new boolean[Constants.MAX_POINT_COUNT];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
        return visited;
    }
}
