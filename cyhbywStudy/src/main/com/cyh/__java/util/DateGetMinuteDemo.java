package com.cyh.__java.util;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanhuche on 7/5/2016.
 */
public class DateGetMinuteDemo {

    public static void main(String[] args) {
        new DateGetMinuteDemo().run();
    }



    private void run() {

        long minute = TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis());
        System.out.println(minute + " " + (minute % 15));

    }

    private void addMinute(Calendar calendar) {
        calendar.add(Calendar.MINUTE, 5);
    }


}
