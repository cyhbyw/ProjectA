package com.cyh.google.guava.collection;

import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.List;

/**
 * Created by yanhuche on 5/14/2017.
 */
public class IntsTest {

    @Test
    public void test1() {
        int[] array = {1, 2, 3, 4, 5};
        int a = 3;
        boolean contains = Ints.contains(array, a);
        int indexOf = Ints.indexOf(array, a);
        int max = Ints.max(array);
        int min = Ints.min(array);

        int[] array2 = {7, 8};
        int[] concat = Ints.concat(array, array2);
    }

    @Test
    public void test2() {
        List<Integer> list = Ints.asList(1, 2, 3, 4, 7);
        int[] array = Ints.toArray(list);
        List<Integer> list2 = Ints.asList(array);

        System.out.println(list);
        System.out.println(list2);
        System.out.println(Ints.join(",", 1, 3, 5));
    }


}
