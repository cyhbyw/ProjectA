package com.cyh.google.guava.collection;

import org.junit.Test;

import com.google.common.primitives.Ints;

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


}
