package com.cyh.graph.storage;

import java.util.ArrayList;
import java.util.List;

/**
 * 图的存储方式：邻接表
 * @author: CYH
 * @date: 2019/8/11 0011 10:37
 */
public class AdjacencyList {

    /** 图的顶点数量 */
    private int n;
    /** 每个节点的第一条可访问边的边索引，Last In First Visited */
    private int[] p;
    /** 图的边信息 */
    private List<Edge> edgeList;

    public AdjacencyList(int n) {
        this.n = n;
        p = new int[n + 1];
        for (int x = 1; x <= n; x++) {
            p[x] = -1;
        }
        edgeList = new ArrayList<>();
    }

    public void addEdge(int st, int to, int len) {
        edgeList.add(new Edge(st, to, len, p[st]));
        p[st] = edgeList.size() - 1;
    }

    public void printAllEdge() {
        for (int x = 1; x <= n; x++) {
            for (int index = p[x]; index != -1; index = edgeList.get(index).next) {
                Edge edge = edgeList.get(index);
                System.out.println(index + ": " + edge);
            }
            System.out.println("----------------" + x);
        }
    }

    static class Edge {
        int st;
        int to;
        int len;
        /** 记录相同 st 的下一条边的index */
        int next;

        public Edge(int st, int to, int len, int next) {
            this.st = st;
            this.to = to;
            this.len = len;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Edge{" + "st=" + st + ", to=" + to + ", len=" + len + ", next=" + next + '}';
        }
    }

}
