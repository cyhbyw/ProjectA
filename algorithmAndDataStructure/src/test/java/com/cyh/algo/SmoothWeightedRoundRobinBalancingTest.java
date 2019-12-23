package com.cyh.algo;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SmoothWeightedRoundRobinBalancingTest {

    @Test
    public void selectNext() {
        final List<Integer> data = Arrays.asList(1, 6, 9);
        List<Character> ans = SmoothWeightedRoundRobinBalancing.selectNext(data);
        System.out.println(ans);
    }

    @Test
    public void selectNext2() {
        final List<Integer> data = Arrays.asList(5, 1, 1);
        List<Character> ans = SmoothWeightedRoundRobinBalancing.selectNext(data);
        System.out.println(ans);
    }
}
