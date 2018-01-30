package com.cyh.google.guava.concurrency;

import com.google.common.util.concurrent.AbstractScheduledService;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanhuche on 6/22/2016.
 */
public class AbstractScheduledServiceGlance extends AbstractScheduledService {


    @Override
    protected void runOneIteration() throws Exception {
        System.out.println(new Date());
    }

    @Override
    protected Scheduler scheduler() {
        return Scheduler.newFixedRateSchedule(0, 1, TimeUnit.SECONDS);
    }


    public static void main(String[] args) throws Exception {
        new AbstractScheduledServiceGlance().startUp();
    }

}
