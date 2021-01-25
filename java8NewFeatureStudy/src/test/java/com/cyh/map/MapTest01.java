package com.cyh.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.cyh.pojo.Bill;
import com.google.common.collect.Lists;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by yanhuche on 3/31/2017.
 */
public class MapTest01 {

    Map<Integer, String> map;

    @Before
    public void doBefore() {
        map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }
    }

    @Test
    public void test_map_01() {
        map.computeIfPresent(3, (num, val) -> val + num);
        assertEquals(map.get(3), "val33");

        map.computeIfPresent(9, (num, val) -> null);
        assertFalse(map.containsKey(9));

        map.computeIfAbsent(23, num -> "val" + num);
        assertTrue(map.containsKey(23));

        map.computeIfAbsent(3, num -> "bam");
        assertEquals(map.get(3), "val33");

        map.remove(3, "val3");
        assertEquals(map.get(3), "val33");

        map.remove(3, "val33");
        assertEquals(map.get(3), null);

        assertEquals(map.getOrDefault(42, "not found"), "not found");

        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        assertEquals(map.get(9), "val9");
        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        assertEquals(map.get(9), "val9concat");
    }

    @Test
    public void test02() {
        Bill in2 = new Bill("in", new BigDecimal("2"));
        Bill out2 = new Bill("out", new BigDecimal("2"));
        Bill in3 = new Bill("in", new BigDecimal("3"));
        Bill out4 = new Bill("out", new BigDecimal("4"));
        ArrayList<Bill> bills = Lists.newArrayList(in2, out2, in3, out4);
        // 直接这样 groupingBy 返回的是 List<Bill>
        Map<String, List<Bill>> mapByType = bills.stream().collect(Collectors.groupingBy(a -> a.getType()));

        // groupingBy 分组后，再对组内的 List<Bill> 进行计算
        Map<String, BigDecimal> groupMap = bills.stream().collect(
            Collectors.groupingBy(a -> a.getType(),
                Collectors.reducing(BigDecimal.ZERO, Bill::getAmount, (x, y) -> x.add(y))));
        groupMap.forEach((k, v) -> System.out.println(k + "  " + v));

        // groupingBy 分组后，再对组内的 List<Bill> 提取关键字段并聚集之
        Map<String, Set<BigDecimal>> groupSetMap = bills.stream().collect(
            Collectors.groupingBy(a -> a.getType(), Collectors.mapping(b -> b.getAmount(), Collectors.toSet())));
        groupSetMap.forEach((k, v) -> System.out.println(k + "  " + v));
    }
}
