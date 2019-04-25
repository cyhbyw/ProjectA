package com.cyh.calculate;

import com.cyh.consts.Constants;
import com.cyh.data.structure.Graph;

/**
 * @author: CYH
 * @date: 2019/4/25
 */
public class BaseCalculatorTest {

    /**
     * 初始化图的距离信息
     * @return
     */
    protected static int[][] initDistance() {
        int vertexCount = Constants.MAX_POINT_COUNT;
        int[][] distance = new int[vertexCount][vertexCount];
        for (int row = 0; row < vertexCount; row++) {
            for (int col = 0; col < vertexCount; col++) {
                distance[row][col] = Graph.NON_EXISTENCE;
            }
        }
        return distance;
    }

}
