package com.cyh.str.match;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author: CYH
 * @date: 2019/5/8 0008 8:25
 */
public class KMPTest {

    @Test
    public void test_getNext() {
        int[] next = KMP.getNext("abcabb");
        System.out.println(Arrays.toString(next));
    }

    @Test
    public void test_getNext_2() {
        int[] next = KMP.getNext("abcabdabcabX");
        System.out.println(Arrays.toString(next));
    }
}
