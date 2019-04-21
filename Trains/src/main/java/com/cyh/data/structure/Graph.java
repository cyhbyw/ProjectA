package com.cyh.data.structure;

import com.cyh.consts.Constants;

/**
 * 数据结构：图
 * 具体存储的时候，用的是矩阵的方式
 * @author: CYH
 * @date: @date: 2019/4/21
 */
public class Graph {

    public static final Integer NON_EXISTENCE = -1;

    private int[][] distance = new int[Constants.MAX_POINT_COUNT][Constants.MAX_POINT_COUNT];

    private Graph() {
        for (int row = 0; row < distance.length; row++) {
            for (int col = 0; col < distance[row].length; col++) {
                distance[row][col] = NON_EXISTENCE;
            }
        }
    }

    public static final class SingletonHolder {
        private static final Graph INSTANCE = new Graph();
    }

    public static Graph getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void setDistance(int start, int end, int d) {
        distance[start][end] = d;
    }

    public int[][] getDistance() {
        return distance;
    }
}
