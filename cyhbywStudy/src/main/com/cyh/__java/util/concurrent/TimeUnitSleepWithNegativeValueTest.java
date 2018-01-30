package com.cyh.__java.util.concurrent;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanhuche on 1/8/2017.
 */
public class TimeUnitSleepWithNegativeValueTest {

    public static void main(String[] args) {
        System.out.println("before: " + new Date());
        try {
            TimeUnit.MILLISECONDS.sleep(-2000);
            TimeUnit.SECONDS.sleep(-5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after: " + new Date());
    }



}
