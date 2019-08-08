package com.cyh.sorting.merge.sort;

import java.util.Random;

import org.junit.Test;

import com.cyh.sorting.SortUtils;

/**
 * @author: CYH
 * @date: 2019/8/7 0007 8:53
 */
public class MergeSortTest {

    private MergeSort mergeSort = new MergeSort();

    @Test
    public void mergeSort() {
        int[] arr = new int[] {2, 3, 7, 5, 4, 6, 1, 8};
        mergeSort.sort(arr);
        SortUtils.checkResult(arr);
    }

    @Test
    public void testBatch() {
        int testCases = 5000000;
        while (testCases-- > 0) {
            int n = new Random().nextInt(20) + 1;
            int[] arr = new int[n];
            for (int x = 0; x < n; x++) {
                arr[x] = new Random().nextInt(20);
            }
            mergeSort.sort(arr);
            SortUtils.checkResult(arr);
        }
    }
}
