package com.cyh.utils;

/**
 * @author: yanhua.chen
 * @date: 2019/4/20 17:00
 */
public final class TypeConvertUtils {

    public static int upperCharToInt(char ch) {
        return ch - 'A';
    }

    /**
     * 长度为1的String，将其转换为Char再转换为int
     * @param value 要求长度等于1
     * @return "A"-->0 "B"-->1
     */
    public static int stringToInt(String value) {
        return upperCharToInt(value.charAt(0));
    }

}
