package com.cyh.calculate;

import java.util.regex.Matcher;

/**
 * @author: CYH
 * @date: 2019/4/21
 */
public interface Calculator {

    /**
     * 计算结果
     * @param input
     * @param matcher
     * @return
     */
    String calculate(String input, Matcher matcher);

}
