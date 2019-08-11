package com.cyh.graph.storage;

import org.junit.Test;

/**
 * @author: CYH
 * @date: 2019/8/11 0011 10:58
 */
public class AdjacencyListTest {


    //      1
    //    /   \
    //   2     3
    //  / \   / \
    // 4   5 6   7
    @Test
    public void test01() {
        int n = 7;
        AdjacencyList adjacencyList = new AdjacencyList(n);
        final int len = 1;
        adjacencyList.addEdge(1, 2, len);
        adjacencyList.addEdge(1, 3, len);
        adjacencyList.addEdge(2, 4, len);
        adjacencyList.addEdge(2, 5, len);
        adjacencyList.addEdge(3, 6, len);
        adjacencyList.addEdge(3, 7, len);

        adjacencyList.printAllEdge();
    }


}
