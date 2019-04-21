package com.cyh.calculate;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import java.util.regex.Matcher;

import com.cyh.consts.Constants;
import com.cyh.data.structure.Graph;
import com.cyh.utils.TypeConvertUtils;

/**
 * 在总距离满足的前提下，计算方案数量
 * @author: CYH
 * @date: @date: 2019/4/21
 */
public class RouteNumberCalculator implements Calculator {

    @Override
    public String calculate(String input, Matcher matcher) {
        int start = TypeConvertUtils.stringToInt(matcher.group("start"));
        int end = TypeConvertUtils.stringToInt(matcher.group("end"));
        int maxDistance = Integer.valueOf(matcher.group("maxDistance"));
        return BFS(start, end, maxDistance);
    }

    private String BFS(int start, int end, int maxDistance) {
        final int[][] distance = Graph.getInstance().getDistance();
        Queue<BFSNode> queue = new ArrayDeque<>();
        queue.add(new BFSNode(start, 0));
        int resultCount = 0;
        while (true) {
            BFSNode node = queue.poll();
            if (Objects.isNull(node)) {
                break;
            }
            if (node.distance >= maxDistance) {
                continue;
            } else if (node.distance != 0 && node.vertex == end) {
                resultCount++;
            }
            for (int x = 0; x < Constants.MAX_POINT_COUNT; x++) {
                if (distance[node.vertex][x] != Graph.NON_EXISTENCE) {
                    queue.add(new BFSNode(x, node.distance + distance[node.vertex][x]));
                }
            }
        }
        return String.valueOf(resultCount);
    }

    private class BFSNode {
        int vertex;
        int distance;

        public BFSNode(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
}
