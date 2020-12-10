package com.cyh.google.guava.data.structure;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.junit.Test;

public class MultiSetTest {


    @Test
    public void test() {
        // 可重复集合，用于计数
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("a");
        multiset.add("B");
        for (Multiset.Entry<String> entry : multiset.entrySet()) {
            System.out.println(entry.getElement() + " " + entry.getCount());
        }
        for (String s : multiset.elementSet()) {
            System.out.println(s + " --> " + multiset.count(s));
        }
    }

}
