package com.cyh.calculate;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

import com.cyh.consts.Constants;
import com.cyh.data.structure.Graph;

/**
 * @author: CYH
 * @date: 2019/4/21
 */
public abstract class AbstractTripNumberCalculator extends CalculatorAdapter {

    /**
     * 使用 BFS 算法搜索符合条件的方案数量
     */
    protected String BFS(int start, int end, int maxOrExactlyStops) {
        final int[][] distance = getGraphDistance();
        Queue<BFSNode> queue = new ArrayDeque<>();
        queue.add(new BFSNode(0, start));
        int resultCount = 0;
        while (true) {
            BFSNode node = queue.poll();
            if (Objects.isNull(node)) {
                break;
            }
            if (node.stops > maxOrExactlyStops) {
                continue;
            } else if (node.stops != 0 && node.vertex == end) {
                if (satisfyCondition(node.stops, maxOrExactlyStops)) {
                    resultCount++;
                }
            }
            for (int x = 0; x < Constants.MAX_POINT_COUNT; x++) {
                if (distance[node.vertex][x] != Graph.NON_EXISTENCE) {
                    queue.add(new BFSNode(node.stops + 1, x));
                }
            }
        }
        return String.valueOf(resultCount);
    }

    /**
     * 是否满足条件
     * @param stops 当前停顿次数
     * @param maxOrExactlyStops  最大或精确停顿次数
     * @return 满足条件返回 True，不满足返回 False
     */
    abstract boolean satisfyCondition(int stops, int maxOrExactlyStops);


    /**
     * BFS 搜索时使用的数据结构
     */
    private class BFSNode {
        int stops;
        int vertex;

        public BFSNode(int stops, int vertex) {
            this.stops = stops;
            this.vertex = vertex;
        }
    }

}
