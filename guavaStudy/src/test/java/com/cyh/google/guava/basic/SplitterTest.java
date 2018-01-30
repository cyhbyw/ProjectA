package com.cyh.google.guava.basic;

import java.util.Map;

import org.junit.Test;

import com.google.common.base.Splitter;

/**
 * Created by yanhuche on 5/14/2017.
 */
public class SplitterTest {

    @Test
    public void testSplitter() {
        String testString = "foo , what ,    ,  ,more,";
        Iterable<String> split = Splitter.on(",").omitEmptyStrings().trimResults().split(testString);
        for (String iterable : split) {
            System.out.println(iterable);
        }

        String str = "test1,        ,   test2, test3";
        Iterable<String> strArr = Splitter.on(',').trimResults().omitEmptyStrings().split(str);
        for (String iterable : strArr) {
            System.out.println(iterable);
        }

        String str2 = "key1: 1; key2: 2  ; key3: 3";
        Map<String, String> map = Splitter.on(';').trimResults().withKeyValueSeparator(":").split(str2);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.printf("=%s= =%s=;;    ", key, value);
        }
    }
}
