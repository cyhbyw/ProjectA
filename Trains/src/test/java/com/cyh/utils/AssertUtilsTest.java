package com.cyh.utils;

import org.junit.Test;

/**
 * @author: CYH
 * @date: 2019/4/24 17:12
 */
public class AssertUtilsTest {

    @Test
    public void test_isTrue_givenTrue_expectedNothing() {
        AssertUtils.isTrue(true, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_isTrue_givenFalse_expectedExceptionThrow() {
        AssertUtils.isTrue(false, "测试express==false的情况");
    }

}
