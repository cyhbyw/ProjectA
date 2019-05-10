package com.cyh.str.match;

/**
 * KMP算法，极客时间，Google王争的算法实现
 * https://time.geekbang.org/column/article/71845
 *
 * @author: CYH
 * @date: 2019/5/10 0010 7:54
 */
public class KMPV2 {


    public static int[] getNext(String s) {
        final char[] p = s.toCharArray();
        final int len = p.length;
        int[] next = KMP.initNext(len);
        int k = -1;
        for (int j = 1; j < len; ++j) {
            while (k != -1 && p[k + 1] != p[j]) {
                k = next[k];
            }
            if (p[k + 1] == p[j]) {
                ++k;
            }
            next[j] = k;
        }
        return next;
    }

    /**
     * a, b 分别是主串和模式串
     */
    public static int kmp(String a, String b) {
        final int[] next = getNext(b);
        final char[] t = a.toCharArray();
        final char[] p = b.toCharArray();

        int j = 0;
        for (int i = 0; i < t.length; ++i) {
            // 一直找到 a[i] 和 b[j]
            while (j > 0 && t[i] != p[j]) {
                j = next[j - 1] + 1;
            }
            if (t[i] == p[j]) {
                ++j;
            }
            // 找到匹配模式串的了
            if (j == p.length) {
                return i - p.length + 1;
            }
        }
        return -1;
    }


}
