package com.cyh.google.guava.data.structure;

import com.google.common.collect.ArrayListMultimap;
import org.junit.Test;

public class MultiMapTest {

    @Test
    public void test() {
        ArrayListMultimap<String, Object> multimap = ArrayListMultimap.create();
        multimap.put("a", 1);
        multimap.put("a", "two");
        multimap.put("B", 3.14);
        System.out.println(multimap.get("a"));
        System.out.println(multimap.get("B"));
    }

}
