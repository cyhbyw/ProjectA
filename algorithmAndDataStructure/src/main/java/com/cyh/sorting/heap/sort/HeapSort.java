package com.cyh.sorting.heap.sort;

/**
 * @author: CYH
 * @date: 2019/6/20 0020 6:53
 */
public class HeapSort {

    /**
     * 从最后一个非叶子节点开始对堆进行调整
     * 调整每个节点的时候，都可能一直向下（递归或循环）调整它的子节点
     */
    public void buildHeap(int[] array) {
        for (int parent = array.length >> 1; parent >= 1; parent--) {
            adjustHeap(array, parent);
        }
    }

    /**
     * 从堆的最后一个元素开始，每次取堆项元素与之交换
     * 交换后，又需要从堆项元素一直向下调整
     */
    public void heapSort(int[] array) {
        final int end = 2, parent = 1;
        for (int i = array.length - 1; i >= end; i--) {
            array[i] = array[parent];
            adjustHeap(array, parent);
        }
    }

    /** 大项堆 */
    private void adjustHeap(int[] array, int parent) {
        int len = array.length;
        for (int left, right, max;;) {
            left = parent << 1;
            right = left << 1 | 1;

            max = parent;
            if (left < len && array[max] < array[left]) {
                max = left;
            }
            if (right < len && array[max] < array[right]) {
                max = right;
            }

            if (max == parent) {
                break;
            }

            swap(array, parent, max);
            parent = max;
        }
    }

    private void swap(int[] array, int parent, int max) {
        int t = array[parent];
        array[parent] = array[max];
        array[max] = t;
    }

}
