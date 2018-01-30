package com.cyh.predicate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Objects;
import java.util.function.Predicate;

import org.junit.Test;

/**
 * Created by yanhuche on 3/30/2017.
 */
public class PredicateTest01 {


    @Test
    public void test() {
        Predicate<String> predicate = (s) -> s.length() > 0;
        assertTrue(predicate.test("foo"));
        assertFalse(predicate.negate().test("foo"));

        Predicate<Boolean> nonNull = Objects::nonNull; // static
        assertTrue(nonNull.test(true));
        Predicate<Boolean> isNull = Objects::isNull; // static
        assertFalse(isNull.test(true));

        Predicate<String> isEmpty = String::isEmpty; // non static
        assertFalse(isEmpty.test("a"));
        Predicate<String> isNotEmpty = isEmpty.negate();
        assertTrue(isNotEmpty.test("a"));
    }


}
