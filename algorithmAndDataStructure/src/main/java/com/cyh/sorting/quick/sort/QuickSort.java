package com.cyh.sorting.quick.sort;

import java.util.Arrays;
import java.util.Random;

import com.cyh.sorting.SortUtils;

/**
 * @author: CYH
 * @date: 2019/3/17 0017 11:23
 */
public class QuickSort {

    public static void main(String[] args) {
        new QuickSort().run();
    }

    private void run() {
        Random random = new Random();
        int testCase = 30000000 + random.nextInt(100);
        int[] count = new int[] {0, 0, 0};
        int mod = 0;
        while (testCase-- > 0) {
            int num = random.nextInt(20) + 1;
            int[] array = new int[num];
            for (int x = 0; x < num; x++) {
                array[x] = random.nextInt(20);
            }
            if (++mod == 3) {
                mod = 0;
            }
            if (mod == 1) {
                quickSort1(array, 0, num - 1);
                count[1]++;
            } else if (mod == 2) {
                quickSort2(array, 0, num - 1);
                count[2]++;
            } else {
                quickSort3(array, 0, num - 1);
                count[0]++;
            }
            SortUtils.checkResult(array);
        }
        System.err.println(Arrays.toString(count));
    }

    /**
     * 单边扫描
     * 选择最右边的数字作为基准，从左往右遍历，找到第一个比临界值大的数所在的位置
     * @param array
     * @param p 闭区间
     * @param r 闭区间
     */
    private void quickSort1(int[] array, int p, int r) {
        if (p >= r) {
            return;
        }

        int temp = array[r];
        // i == 第一个大于 temp 的数所在的位置
        int i = p;
        for (int j = p; j < r; j++) {
            // 目的是找到第一个大于 temp 的数所在的位置，所以，小于 temp 的数，需要交换到前面去
            if (array[j] < temp) {
                swap(array, j, i);
                i++;
            }
        }
        swap(array, i, r);
        quickSort1(array, p, i - 1);
        quickSort1(array, i + 1, r);
    }

    /**
     * 单边扫描
     * 选择最左边的数字作为基准，从右往左遍历，找到最后一个比临界值小的数所在的位置
     * @param array
     * @param p 闭区间
     * @param r 闭区间
     */
    private void quickSort2(int[] array, int p, int r) {
        if (p >= r) {
            return;
        }

        int temp = array[p];
        // i == 最后一个小于 temp 的数所在的位置
        int i = r;
        for (int j = r; j > p; j--) {
            // 目的是找到最后一个小于 temp 的数所在的位置，所以，大于 temp 的数，需要交换到后面去
            if (array[j] > temp) {
                swap(array, i, j);
                i--;
            }
        }
        swap(array, i, p);
        quickSort2(array, p, i - 1);
        quickSort2(array, i + 1, r);
    }

    private void swap(int[] array, int j, int i) {
        if (j != i) {
            int temp = array[j];
            array[j] = array[i];
            array[i] = temp;
        }
    }

    /**
     * 双边扫描
     */
    private void quickSort3(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int key = array[left];
        int i = left, j = right;
        while (i < j) {
            while (i < j && array[j] >= key) {
                j--;
            }
            array[i] = array[j];
            while (i < j && array[i] <= key) {
                i++;
            }
            array[j] = array[i];
        }
        array[i] = key;
        quickSort3(array, left, i - 1);
        quickSort3(array, i + 1, right);
    }


}
