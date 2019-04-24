package com.cyh.calculate;

import java.util.regex.Matcher;

/**
 * @author: CYH
 * @date: 2019/4/21
 */
public interface Calculator {

    /**
     * 获取图信息
     * @return
     */
    int[][] getGraphDistance();

    /**
     * 计算结果
     * @param matcher
     * @return
     */
    String calculate(Matcher matcher);

}
