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
        int[] next = KMP.getNext("abacDababC");
        System.out.println(Arrays.toString(next));
    }

    @Test
    public void test_getNext_3() {
        int[] next = KMP.getNext("abcabdabcabX");
        System.out.println(Arrays.toString(next));
    }



    @Test
    public void test_KMP_1() {
        System.out.println(KMP.kmp("ABABAC", "ABAC"));
    }

    @Test
    public void test_KMP_2() {
        System.out.println(KMP.kmp("ABACXY", "ABAB"));
    }

    @Test
    public void test_KMP_3() {
        System.out.println(KMP.kmp("ABCABCXYZ", "ABCABB"));
    }
}
