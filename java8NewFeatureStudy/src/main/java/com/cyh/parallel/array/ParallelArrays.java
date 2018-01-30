package com.cyh.parallel.array;

/**
 * Created by yanhuche on 3/30/2017.
 */
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class ParallelArrays {

    public static void main(String[] args) {
        long[] arrayOfLong = new long[2000000];

        Arrays.parallelSetAll(arrayOfLong, index -> ThreadLocalRandom.current().nextInt(10000000));
        Arrays.stream(arrayOfLong).limit(10).forEach(i -> System.out.print(i + " "));
        System.out.println();

        Arrays.parallelSort(arrayOfLong);
        Arrays.stream(arrayOfLong).limit(10).forEach(i -> System.out.print(i + " "));
        System.out.println();
    }
}
