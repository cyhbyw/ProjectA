package com.cyh.improved.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Created by yanhuche on 4/1/2017.
 */
public class TypesTest01 {



    @Test
    public void test_01() {
        List<String> stringList = new ArrayList<>();
        stringList.add("A");
        stringList.addAll(Arrays.asList("b"));
        stringList.addAll(Arrays.<String>asList("b")); //for java 7
    }



}
