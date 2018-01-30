package com.cyh.google.guava.strings;

import static com.google.common.base.CharMatcher.inRange;

import org.junit.Test;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

/**
 * Created by yanhuche on 6/10/2016.
 */
public class CharMatcherGlance {


    @Test
    public void test_inRange() {
        String result = inRange('a', 'z').or(inRange('A', 'Z')).toString();
        System.out.println(result);
    }

    @Test
    public void test_digit() {
        String string = CharMatcher.digit().retainFrom("some 22 text 89983 and more");
        System.out.println(string);

        String string2 = CharMatcher.digit().removeFrom("some 4334 text 89983 and more");
        System.out.println(string2);
    }

    @Test
    public void testJoiner() {
        String tmpValue = "a_b_c_1_2_3";
        String[] valArr = tmpValue.split("_");
        // 求字符串数组的子串，并最后拼接起来
        String tmpVal = "";
        for (int i = 1; i < valArr.length; i++) {
            tmpVal = tmpVal.equalsIgnoreCase("") ? valArr[i] : tmpVal + "_" + valArr[i];
        }
        System.out.println(tmpVal);
        System.out.println("———————");
        // 上面这么一段与下面这句等价
        System.out.println(Joiner.on("_").join(Lists.newArrayList(valArr).subList(1, valArr.length)));
    }




}
