package com.cyh.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by yanhuche on 3/31/2017.
 */
public class StreamTest01 {

    private List<String> stringCollection;

    @Before
    public void doBefore() {
        stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");
    }


    @Test
    public void test_filter() {
        stringCollection.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println); // "aaa2", "aaa1"
    }

    @Test
    public void test_sort() {
        stringCollection.stream().sorted().filter((s) -> s.startsWith("a")).forEach(System.out::println);
        // "aaa1", "aaa2"

        //排序只创建了一个排列好后的Stream，而不会影响原有的数据源
        System.out.println(stringCollection);
    }

    @Test
    public void test_map() {
        //map返回的Stream类型是根据你map传递进去的函数的返回值决定的
        stringCollection.stream().map(String::toUpperCase).sorted((a, b) -> b.compareTo(a))
                .forEach(System.out::println);
        // "DDD2", "DDD1", "CCC", "BBB3", "BBB2", "AAA2", "AAA1"
    }

    @Test
    public void test_match() {
        boolean anyStartsWithA = stringCollection.stream().anyMatch((s) -> s.startsWith("a"));
        System.out.println(anyStartsWithA); // true
        boolean allStartsWithA = stringCollection.stream().allMatch((s) -> s.startsWith("a"));
        System.out.println(allStartsWithA); // false
        boolean noneStartsWithZ = stringCollection.stream().noneMatch((s) -> s.startsWith("z"));
        System.out.println(noneStartsWithZ); // true
    }

    @Test
    public void test_count() {
        long startsWithB = stringCollection.stream().filter((s) -> s.startsWith("b")).count();
        System.out.println(startsWithB); // 3
    }

    @Test
    public void test_reduce() {
        Optional<String> reduced = stringCollection.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);
        // "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"
    }


}
