package com.cyh.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * 平滑权重轮询算法
 *
 * @author CYH
 * @date 2019-12-23
 */
public class SmoothWeightedRoundRobinBalancing {

    public static List<Character> selectNext(final List<Integer> data) {
        List<Integer> copy = new ArrayList<>(data);
        Integer sum = data.stream().reduce((a, b) -> a + b).get();
        List<Character> ans = new ArrayList<>(sum);
        for (int x = 0; x < sum; x++) {
            int max = copy.get(0), maxIndex = 0;
            for (int i = 1; i < copy.size(); i++) {
                if (max < copy.get(i)) {
                    max = copy.get(i);
                    maxIndex = i;
                }
            }

            ans.add((char) (maxIndex + 'A'));
            copy.set(maxIndex, max - sum);
            for (int i = 0; i < copy.size(); i++) {
                copy.set(i, copy.get(i) + data.get(i));
            }
        }
        return ans;
    }

}
