package com.cyh.sorting.heap.sort;

import java.util.Random;

import org.junit.Test;

import com.cyh.sorting.SortUtils;

/**
 * @author: CYH
 * @date: 2019/6/20 0020 7:23
 */
public class HeapSortTest {

    private HeapSort heapSort = new HeapSort();

    @Test
    public void test01() {
        // 第0个元素是个占位符，不参与真正计算
        int[] array = {Integer.MIN_VALUE, 6, 7, 2, 9, 8, 4, 3, 1, 0};
        heapSort.buildHeap(array);
        heapSort.heapSort(array);
        SortUtils.checkResult(array);
    }

    @Test
    public void testBatch() {
        Random random = new Random();
        int testCase = 10000000;
        while (testCase-- > 0) {
            int num = random.nextInt(20) + 1;
            int[] array = new int[num + 1];
            for (int i = 1; i <= num; i++) {
                array[i] = random.nextInt(20);
            }
            heapSort.buildHeap(array);
            heapSort.heapSort(array);
            SortUtils.checkResult(array);
        }
    }

}
