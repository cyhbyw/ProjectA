package com.cyh.str.match;

/**
 * KMP算法的理解与实现，参照下文：
 * https://www.cnblogs.com/yjiyjige/p/3263858.html
 *
 * @author: CYH
 * @date: 2019/5/8 0008 8:24
 */
public class KMP {

    public static int[] getNext(String ps) {
        char[] p = ps.toCharArray();
        int[] next = initNext(p.length);
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

    static int[] initNext(int length) {
        int[] next = new int[length];
        next[0] = -1;
        for (int x = 1; x < length; x++) {
            next[x] = -2;
        }
        return next;
    }

    public static int kmp(String ts, String ps) {
        final int[] next = getNext(ps);
        final char[] t = ts.toCharArray();
        final char[] p = ps.toCharArray();
        // 主串的位置
        int i = 0;
        // 模式串的位置
        int j = 0;
        while (i < t.length && j < p.length) {
            // 当j为-1时，要移动的是i，当然j也要归0
            if (j == -1 || t[i] == p[j]) {
                i++;
                j++;
                System.out.println(String.format("(i++, j++): i=%d j=%d", i, j));
            } else {
                // i不需要回溯了
                // j回到指定位置
                // i = i - j + 1;
                j = next[j];
                System.out.println(String.format("(j = next[j]): i=%d j=%d", i, j));
            }
        }
        return j == p.length ? i - j : -1;
    }


}
