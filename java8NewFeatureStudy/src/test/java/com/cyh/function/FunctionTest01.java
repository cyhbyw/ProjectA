package com.cyh.function;

import java.util.function.Function;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by yanhuche on 3/30/2017.
 */
public class FunctionTest01 {


    @Test
    public void test() {
        Function<String, Integer> toInteger = Integer::valueOf;
        int integer = toInteger.apply("22");
        assertEquals(integer, 22);

        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        backToString.apply("123"); // "123"
    }


}
