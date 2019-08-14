package com.cyh.chapter02.prob16;

import org.junit.Test;

/**
 * 求数组的最长上升子序列长度
 * @author: CYH
 * @date: 2019/8/15 0015 6:41
 */
public class LongestIncreasingSubSequenceTest {

    @Test
    public void test01() {
        int[] arr = new int[] {1, -1, 2, -3, 4, -5, 6, -7};
        int calculate1 = LongestIncreasingSubSequence.calculate1(arr);
        System.out.println(calculate1);
    }

    @Test
    public void test02() {
        int[] arr = new int[] {2, 9, 3, 6, 5, 1, 7};
        int calculate2 = LongestIncreasingSubSequence.calculate2(arr);
        System.out.println(calculate2);
    }

}
