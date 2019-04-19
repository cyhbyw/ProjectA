package com.cyh.data.structure;

import com.cyh.consts.Constants;

/**
 * 数据结构：图
 * 具体存储的时候，用的是邻接表的方式
 * @author: CYH
 * @date: 2019/4/19 0019 8:47
 */
public class Graph {

    private int[] head = new int[Constants.MAX_POINT_COUNT];
    private Edge[] edges = new Edge[Constants.MAX_EDGE_COUNT];
    private int edgeIndex = 0;

    public Graph() {
        for (int i = 0; i < head.length; i++) {
            head[i] = -1;
        }
    }

    public void addEdge(int start, int end, int distance) {
        edges[edgeIndex] = new Edge();
        edges[edgeIndex].end = end;
        edges[edgeIndex].distance = distance;
        edges[edgeIndex].next = head[start];
        head[start] = edgeIndex++;
    }

    class Edge {
        int end;
        int distance;
        int next;
    }

}
