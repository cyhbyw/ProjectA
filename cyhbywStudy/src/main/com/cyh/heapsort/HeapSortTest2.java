package com.cyh.heapsort;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanhuche on 5/2/2016.
 */
public class HeapSortTest2 {

    private int R;
    private final int[] A = new int[10000];
    private final int MAX_INT_VALUE = 100;

    public static void main(String[] args) {
        HeapSortTest2 cyh = new HeapSortTest2();

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(new Date());
            }
        }, 0, 10, TimeUnit.SECONDS);

        cyh.run();
    }

    private void run() {
        while (true) {
            R = new Random().nextInt(50);
            generateInputData();
            heapSort();
            checkCorrection();
        }
    }

    private void checkCorrection() {
        for (int i = 2; i <= R; i++) {
            if (A[i] > A[i-1]) {
                for (int x = 1; x <= R; x++) {
                    System.out.print(A[x] + " ");
                }
                System.out.println();
                System.out.println("--------------------");
                System.exit(-1);
            }
        }
    }

    private void heapSort() {
        for (int r = R / 2; r >= 1; r--) {
            pushDown(r, R);
        }
        for (int r = R; r >= 2; r--) {
            swap(1, r);
            pushDown(1, r - 1);
        }
    }

    private void swap(int i, int r) {
        int temp = A[i];
        A[i] = A[r];
        A[r] = temp;
    }

    private void pushDown(int a, int b) {
        while (2 * a <= b) {
            int minValue = A[a];
            int minIndex = a;
            if (minValue > A[2 * a]) {
                minValue = A[2 * a];
                minIndex = 2 * a;
            }
            if (2 * a + 1 <= b && minValue > A[2 * a + 1]) {
                minValue = A[2 * a + 1];
                minIndex = 2 * a + 1;
            }
            if (A[a] > minValue) {
                swap(a, minIndex);
                a = minIndex;
            } else {
                break;
            }
        }
    }

    private void generateInputData() {
        for (int i = 1; i <= R; i++) {
            A[i] = new Random().nextInt(MAX_INT_VALUE);
        }
        /*for (int i = 1; i <= R; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();*/
    }


}
