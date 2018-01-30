package com.cyh.__java.util.concurrent;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanhuche on 7/2/2016.
 */
public class ScheduleWithFixedDelayTest {


    public static void main(String[] args) {
        new ScheduleWithFixedDelayTest().run();
    }

    private void run() {
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new Task(), 1, 1, TimeUnit.SECONDS);
    }

    class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("being " + new Date());
            long count = new Random().nextInt(15) + 1;
            count *= 1000000000L;
            while (count-- > 0) {
            }
            System.out.println("end " + new Date());
        }
    }


}
