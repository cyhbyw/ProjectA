package com.cyh.heapsort;

import java.util.Random;

/**
 * Created by yanhuche on 5/2/2016.
 */
public class HeapSortTest {

    private final int R = 6;
    private final int[] A = new int[R + 1];
    private final int MAX_INT_VALUE = 100;

    public static void main(String[] args) {
        HeapSortTest cyh = new HeapSortTest();
        cyh.run();
    }

    private void run() {
        generateInputData();
        heapSort();
    }

    private void heapSort() {
        for (int r = R / 2; r >= 1; r--) {
            pushDown(r, R);
        }
        for (int r = R / 2; r >= 2; r--) {
            swap(1, r);
            pushDown(1, r - 1);
        }
        for (int i = 1; i <= R; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();
        System.out.println("--------------------");
    }

    private void swap(int i, int r) {
        int temp = A[i];
        A[i] = A[r];
        A[r] = temp;
    }

    private void pushDown(int a, int b) {
        int i = a;
        while (i <= b && i >= 2) {
            if (b == 2 * i) {
                if (A[i] < A[2 * i]) {
                    swap(i, 2 * i);
                    i = b;
                }
            } else {
                if (A[i] < A[2 * i] && A[2 * i] >= A[2 * i + 1]) {
                    swap(i, 2 * i);
                    i = 2 * i;
                }
                if (A[i] < A[2 * i + 1] && A[2 * i + 1] > A[2 * i]) {
                    swap(i, 2 * i + 1);
                    i = 2 * i + 1;
                } else {
                    i = b;
                }
            }
        }
    }

    private void generateInputData() {
        for (int i = 1; i <= R; i++) {
            A[i] = new Random().nextInt(MAX_INT_VALUE);
        }
        for (int i = 1; i <= R; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();
    }


}
