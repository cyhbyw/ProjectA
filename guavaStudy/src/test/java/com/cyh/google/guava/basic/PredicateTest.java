package com.cyh.google.guava.basic;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

/**
 * Created by yanhuche on 5/14/2017.
 */
public class PredicateTest {

    @Test
    public void test_1() {
        ArrayList<String> strArr =
                Lists.newArrayList(" test1", "test2 ", " test3 ", null, "test4", null, null, "", "  ");
        Predicate<String> EMPTY_OR_NULL_FILTER = str -> {
            str = Strings.nullToEmpty(str).trim();
            return !Strings.isNullOrEmpty(str);
        };
        Collection<String> filter = Collections2.filter(strArr, EMPTY_OR_NULL_FILTER);

        Function<String, String> TRIM_RESULT = str -> Strings.nullToEmpty(str).trim();
        Collection<String> strings = Collections2.transform(filter, TRIM_RESULT);
        String joinStr = Joiner.on(';').skipNulls().join(strings);
        System.out.println(joinStr);
    }

}
