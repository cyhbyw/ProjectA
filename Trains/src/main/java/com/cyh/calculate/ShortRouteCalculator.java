package com.cyh.calculate;

import java.util.Objects;
import java.util.PriorityQueue;
import java.util.regex.Matcher;

import com.cyh.consts.Constants;
import com.cyh.data.structure.Graph;
import com.cyh.utils.TypeConvertUtils;

/**
 * 计算最短路径
 * @author: yanhua.chen
 * @date: 2019/4/20 16:08
 */
public class ShortRouteCalculator implements Calculator {

    @Override
    public String calculate(String input, Matcher matcher) {
        int start = TypeConvertUtils.stringToInt(matcher.group("start"));
        int end = TypeConvertUtils.stringToInt(matcher.group("end"));
        return String.valueOf(dijastra(start, end));
    }

    /**
     * Dijastra 算法计算最短路径
     * @param start 起点
     * @param end 终点
     * @return 最短路径
     */
    private long dijastra(int start, int end) {
        final int[][] distance = Graph.getInstance().getDistance();
        int[] minDistance = initMinDistance();
        minDistance[start] = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(start);
        //boolean[] visited = initVisited();
        //visited[start] = true;
        for (int cur = 0; cur < Constants.MAX_POINT_COUNT; cur++) {
            Integer top = priorityQueue.poll();
            if (Objects.isNull(top)) {
                break;
            }
            for (int next = 0; next < Constants.MAX_POINT_COUNT; next++) {
                if (distance[top][next] != Graph.NON_EXISTENCE) {
                    int tempDistance = minDistance[top] + distance[top][next];
                    if (minDistance[next] == Graph.NON_EXISTENCE || minDistance[next] > tempDistance) {
                        minDistance[next] = tempDistance;
                        priorityQueue.add(next);
                    }
                }
            }


        }

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
