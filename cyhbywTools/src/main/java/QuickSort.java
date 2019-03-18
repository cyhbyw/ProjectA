import java.util.Arrays;
import java.util.Random;

/**
 * @author: CYH
 * @date: 2019/3/17 0017 11:23
 */
public class QuickSort {

    public static void main(String[] args) {
        new QuickSort().run();
    }

    private void run() {
        int testCase = 10000000;
        while (testCase-- > 0) {
            int num = new Random().nextInt(20) + 1;
            int[] array = new int[num];
            for (int x = 0; x < num; x++) {
                array[x] = new Random().nextInt(20);
            }
            if ((testCase & 1) == 1) {
                quickSort1(array, 0, num - 1);
            } else {
                quickSort2(array, 0, num - 1);
            }
            checkResult(array, num);
        }
    }

    /**
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

    private void checkResult(int[] array, int num) {
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
