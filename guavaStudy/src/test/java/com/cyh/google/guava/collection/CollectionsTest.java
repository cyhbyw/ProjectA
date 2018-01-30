package com.cyh.google.guava.collection;

import static com.google.common.collect.Maps.newConcurrentMap;
import static com.google.common.collect.Sets.newHashSet;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import com.google.common.primitives.Ints;

/**
 * Created by yanhuche on 6/10/2016.
 */
public class CollectionsTest {


    Map<String, Map<Long, List<String>>> map = Maps.newHashMap();
    Map<String, Map<Long, List<String>>> map2 = newConcurrentMap();

    List<Map<Long, List<String>>> list = Lists.newArrayList();

    ImmutableList<String> of = ImmutableList.of("a", "b", "c", "d");

    ImmutableMap<String, String> mapx = ImmutableMap.of("key1", "value1", "key2", "value2");

    @Test
    public void testXX() {
        File file = new File(getClass().getResource("/test.txt").getFile());
        List<String> lines = null;
        try {
            lines = Files.readLines(file, Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int a = 3;
        int b = 4;
        int compare = Ints.compare(a, b);
    }

    @Test
    public void test1() {
        HashSet setA = newHashSet(1, 2, 3, 4, 5);
        HashSet setB = newHashSet(4, 5, 6, 7, 8);

        Sets.SetView union = Sets.union(setA, setB);
        System.out.println("union: ");
        for (Object integer : union)
            System.out.println(integer);

        Sets.SetView difference = Sets.difference(setA, setB);
        System.out.println("difference: ");
        for (Object integer : difference)
            System.out.println(integer);

        Sets.SetView intersection = Sets.intersection(setA, setB);
        System.out.println("intersection: ");
        for (Object integer : intersection) {
            System.out.println(integer);
        }
    }

    @Test
    public void test2() {
        MapDifference differenceMap = Maps.difference(map, map2);

        differenceMap.areEqual();
        Map entriesDiffering = differenceMap.entriesDiffering();
        Map entriesOnlyOnLeft = differenceMap.entriesOnlyOnLeft();
        Map entriesOnlyOnRight = differenceMap.entriesOnlyOnRight();
        Map entriesInCommon = differenceMap.entriesInCommon();
    }





}
