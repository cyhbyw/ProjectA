package com.cyh.factory;

import org.junit.Assert;
import org.junit.Test;

import com.cyh.consts.Constants;

/**
 * @author: CYH
 * @date: 2019/4/25
 */
public class CalculatorFactoryTest {

    @Test(expected = IllegalArgumentException.class)
    public void test_calculate_givenInvalidInput_expectedException() {
        CalculatorFactory.calculate("error input");
    }

    @Test
    public void test_calculate_givenValidInput_expected() {
        String result = CalculatorFactory.calculate("The distance of the route A-D.");
        Assert.assertEquals(Constants.NO_SUCH_ROUTE, result);
    }
}
