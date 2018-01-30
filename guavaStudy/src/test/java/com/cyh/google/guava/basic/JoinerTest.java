package com.cyh.google.guava.basic;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;

/**
 * Created by yanhuche on 5/14/2017.
 */
public class JoinerTest {


    @Test
    public void test_on_join() {
        String[] subDirs = {"usr", "local", "lib"};
        String directory = Joiner.on("/").join("usr", "local", "lib");
        System.out.println(directory);
    }

    @Test
    public void test_on_join_2() {
        int[] numbers = {1, 2, 3, 4, 5};
        String numbersAsString = Joiner.on(";").join(Ints.asList(numbers));
        System.out.println(numbersAsString);

        String numbersAsStringDirectly = Ints.join(";", numbers);
        System.out.println(numbersAsStringDirectly);
    }

    @Test
    public void test_on_join_3() {
        ArrayList<String> strArr1 = Lists.newArrayList("test1", "test2", "test3", null, "test4", null, null);
        String result = Joiner.on(';').skipNulls().join(strArr1);
        System.out.println(result);

        String result2 = Joiner.on(';').useForNull("_").join(strArr1);
        System.out.println(result2);
    }

    @Test
    public void test_join_map() {
        Map<String, String> map = Maps.newHashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", null);
        map.put("key4", "value3");
        String result = Joiner.on(';').useForNull("NULL").withKeyValueSeparator("=").join(map);
        System.out.println(result);
    }



}
