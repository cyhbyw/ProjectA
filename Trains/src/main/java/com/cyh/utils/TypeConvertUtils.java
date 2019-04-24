package com.cyh.utils;

import java.util.Objects;

/**
 * @author: CYH
 * @date: 2019/4/21
 */
public final class TypeConvertUtils {

    /**
     * @param ch 必须是大写字母
     * @return
     */
    public static int upperCharToInt(char ch) {
        AssertUtils.isTrue(Character.isUpperCase(ch), "不是大写字母: " + ch);
        return ch - 'A';
    }

    /**
     * 长度为1的String，将其转换为Char再转换为int
     * @param value 要求长度等于1
     * @return "A"-->0 "B"-->1
     */
    public static int stringToInt(String value) {
        AssertUtils.isTrue(Objects.nonNull(value), "value对象为null");
        AssertUtils.isTrue(value.length() == 1, "value长度必须等于1");
        return upperCharToInt(value.charAt(0));
    }

}
