package com.cyh.algo;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SmoothWeightedRoundRobinBalancingTest {

    @Test
    public void selectNext() {
        final List<Integer> data = Arrays.asList(1, 6, 9);
        for (int x = 0; x < 16; x++) {
            System.out.println(SmoothWeightedRoundRobinBalancing.selectNext(data));
        }
    }
}
