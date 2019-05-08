package com.cyh.str.match;

/**
 * @author: CYH
 * @date: 2019/5/8 0008 8:24
 */
public class KMP {

    public static int[] getNext(String ps) {
        char[] p = ps.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < p.length - 1) {
            if (k == -1 || p[j] == p[k]) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }
        return next;
    }

    public static int[] getNexts(String s) {
        final char[] b = s.toCharArray();
        final int len = b.length;
        int[] next = new int[len];
        next[0] = -1;
        int k = -1;
        for (int j = 1; j < len; ++j) {
            while (k != -1 && b[k + 1] != b[j]) {
                k = next[k];
            }
            if (b[k + 1] == b[j]) {
                ++k;
            }
            next[j] = k;
        }
        return next;
    }


}
