package com.cyh.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by yanhuche on 3/31/2017.
 */
public class ParallelStreamTest01 {

    List<String> values;

    @Before
    public void doBefore() {
        int max = 1000000;
        values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
    }

    @Test
    public void test_sort_serially() {
        long t0 = System.nanoTime();
        long count = values.stream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));
    }

    @Test
    public void test_sort_parallel() {
        long t0 = System.nanoTime();
        long count = values.parallelStream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis));
    }


}
