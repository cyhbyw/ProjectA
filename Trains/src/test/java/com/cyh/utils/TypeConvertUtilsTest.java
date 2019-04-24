package com.cyh.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: CYH
 * @date: 2019/4/24
 */
public class TypeConvertUtilsTest {

    @Test
    public void test_upperCharToInt_givenValidUpperChar_expectedIntValue() {
        final char inputChar = 'A';
        final int expectedResult = 0;
        int actualResult = TypeConvertUtils.upperCharToInt(inputChar);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_upperCharToInt_givenInvalidChar_expectedExceptionThrow() {
        TypeConvertUtils.upperCharToInt('a');
    }

    @Test
    public void test_stringToInt_givenValidString_expectedIntValue() {
        final String input = "B";
        final int expectedResult = 1;
        int actualResult = TypeConvertUtils.stringToInt(input);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_stringToInt_givenNullString_expectedExceptionThrow() {
        TypeConvertUtils.stringToInt(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_stringToInt_givenInvalidString_expectedExceptionThrow() {
        TypeConvertUtils.stringToInt("AB");
    }

}
