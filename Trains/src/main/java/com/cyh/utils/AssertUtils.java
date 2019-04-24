package com.cyh.utils;

/**
 * 验证/断言工具类
 * @author: CYH
 * @date: 2019/4/24
 */
public final class AssertUtils {


    /**
     * 验证某个条件一定成立，如果不成立，抛出指定错误信息的异常
     * @param expression Boolean值
     * @param errorMsg 错误提示信息
     * @throws IllegalArgumentException 如果条件不成立，抛出此异常，错误信息由errorMsg指定
     */
    public static void isTrue(boolean expression, String errorMsg) {
        if (!expression) {
            throw new IllegalArgumentException(errorMsg);
        }
    }



}
