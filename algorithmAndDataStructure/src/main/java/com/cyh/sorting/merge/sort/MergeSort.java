package com.cyh.sorting.merge.sort;

import java.util.Objects;

/**
 * @author: CYH
 * @date: 2019/8/7 0007 8:27
 */
public class MergeSort {

    public void sort(int[] arr) {
        if (Objects.isNull(arr) || arr.length <= 1) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int le, int ri) {
        if (le >= ri) {
            return;
        }

        int mid = (le + ri) >> 1;
        mergeSort(arr, le, mid);
        mergeSort(arr, mid + 1, ri);
        int[] temp = mergeLeftRight(arr, le, mid, ri);
        copyToOriginalArr(temp, arr, le);
    }

    private int[] mergeLeftRight(int[] arr, int le, int mid, int ri) {
        int[] temp = new int[ri - le + 1];
        int index = 0;
        int riIndex = mid + 1;
        while (le <= mid && riIndex <= ri) {
            if (arr[le] <= arr[riIndex]) {
                temp[index++] = arr[le++];
            } else {
                temp[index++] = arr[riIndex++];
            }
        }
        while (le <= mid) {
            temp[index++] = arr[le++];
        }
        while (riIndex <= ri) {
            temp[index++] = arr[riIndex++];
        }
        return temp;
    }

    private void copyToOriginalArr(int[] temp, int[] arr, int le) {
        for (int x = 0; x < temp.length; x++) {
            arr[le++] = temp[x];
        }
    }


}
