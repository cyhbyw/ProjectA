package com.cyh.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

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
}
