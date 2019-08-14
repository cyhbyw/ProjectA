package com.cyh.chapter02.prob16;

import java.util.Arrays;

/**
 * 求数组的最长上升子序列长度
 * @author: CYH
 * @date: 2019/8/15 0015 6:40
 */
public class LongestIncreasingSubSequence {

    /**
     * time: O(N^2)
     */
    public static int calculate1(int[] arr) {
        final int length = arr.length;
        int[] res = new int[length];
        int maxLength = 1;
        for (int x = 0; x < length; x++) {
            res[x] = 1;
            for (int j = 0; j < x; j++) {
                if (arr[x] > arr[j]) {
                    res[x] = Math.max(res[x], res[j] + 1);
                    maxLength = Math.max(maxLength, res[x]);
                }
            }
        }
        return maxLength;
    }

    /**
     * time: O(N^2)
     */
    public static int calculate2(int[] arr) {
        final int length = arr.length;
        int[] maxV = new int[length + 1];
        maxV[1] = arr[0];
        maxV[0] = getMin(arr) - 1;
        int[] res = new int[length];
        for (int i = 0; i < length; i++) {
            res[i] = 1;
        }
        int maxLength = 1;
        for (int x = 1; x < length; x++) {
            int j = maxLength;
            for (; j >= 0; j--) {
                if (arr[x] > maxV[j]) {
                    res[x] = j + 1;
                    break;
                }
            }
            if (res[x] > maxLength) {
                maxLength = res[x];
                maxV[res[x]] = arr[x];
            } else if (maxV[j] < arr[x] && arr[x] < maxV[j + 1]) {
                maxV[j + 1] = arr[x];
            }
        }
        return maxLength;
    }

    private static int getMin(int[] arr) {
        return Arrays.stream(arr).min().getAsInt();
    }

    /**
     * time: O(N*logN)
     */
    public static int calculate3(int[] arr) {
        final int length = arr.length;
        return -1;
    }
}
