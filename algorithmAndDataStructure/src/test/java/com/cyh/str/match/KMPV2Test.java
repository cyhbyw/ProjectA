package com.cyh.str.match;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author: CYH
 * @date: 2019/5/10 0010 7:56
 */
public class KMPV2Test {

    @Test
    public void test_getNext() {
        int[] next = KMPV2.getNext("abcabb");
        System.out.println(Arrays.toString(next));
    }

    @Test
    public void test_getNext_2() {
        int[] next = KMPV2.getNext("abacDababC");
        System.out.println(Arrays.toString(next));
    }



    @Test
    public void test_KMP_1() {
        System.out.println(KMPV2.kmp("ABABAC", "ABAC"));
    }
}
