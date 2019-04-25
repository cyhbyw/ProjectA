package com.cyh.data.structure;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: CYH
 * @date: 2019/4/25
 */
public class GraphTest {

    @Test
    public void test_getInstance() {
        Graph graph = Graph.getInstance();
        Assert.assertNotNull(graph);
    }

    @Test
    public void test_setAndGetDistance() {
        Graph graph = Graph.getInstance();
        int start = 1, end = 2, distance = 100;
        graph.setDistance(start, end, distance);
        checkDistance(start, end, distance, graph.getDistance());
    }

    private void checkDistance(int start, int end, int distance, int[][] graphDistance) {
        for (int row = 0; row < graphDistance.length; row++) {
            for (int col = 0; col < graphDistance[row].length; col++) {
                if (row == start && col == end) {
                    Assert.assertEquals(distance, graphDistance[start][end]);
                } else {
                    Assert.assertEquals(Graph.NON_EXISTENCE.intValue(), graphDistance[row][col]);
                }
            }
        }
    }


}
