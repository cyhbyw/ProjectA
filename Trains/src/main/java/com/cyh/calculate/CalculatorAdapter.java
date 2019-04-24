package com.cyh.calculate;

import java.util.regex.Matcher;

import com.cyh.data.structure.Graph;

/**
 * @author: CYH
 * @date: 2019/4/24
 */
public abstract class CalculatorAdapter implements Calculator {

    /**
     * 获取图信息
     */
    @Override
    public int[][] getGraphDistance() {
        return Graph.getInstance().getDistance();
    }

    /**
     * 计算结果
     */
    @Override
    public abstract String calculate(Matcher matcher);
}
