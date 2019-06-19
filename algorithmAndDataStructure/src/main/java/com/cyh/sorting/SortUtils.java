package com.cyh.sorting;

import java.util.Arrays;

/**
 * @author: CYH
 * @date: 2019/6/20 0020 6:54
 */
public final class SortUtils {

    public static void checkResult(int[] array) {
        int num = array.length;
        if (num <= 1) {
            return;
        }
        for (int i = 1; i < num; i++) {
            if (array[i - 1] > array[i]) {
                System.err.println("出错: " + Arrays.toString(array));
                System.exit(-1);
            }
        }
    }

}
