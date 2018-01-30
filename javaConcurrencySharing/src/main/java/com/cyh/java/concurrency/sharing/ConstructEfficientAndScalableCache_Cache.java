package com.cyh.java.concurrency.sharing;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * Created by yanhuche on 6/21/2016.
 */
public class ConstructEfficientAndScalableCache_Cache {

    private Cache<Integer, Double> cache = CacheBuilder.newBuilder().build();


    public static void main(String[] args) {
        new ConstructEfficientAndScalableCache_Cache().run();
    }

    private void run() {
        int maxThreadCount = 10;
        while (maxThreadCount-- > 0) {
            Runnable task = new OneTask();
            new Thread(task).start();
        }
    }


    class OneTask implements Runnable {
        @Override
        public void run() {
            int keyCount = 10;
            while (keyCount-- > 0) {
                Double value = retrieveValue(keyCount);
                System.out.println(Thread.currentThread().getName() + ": " + keyCount + " --> " + value);
            }
        }
    }

    private Double retrieveValue(final int keyCount) {
        try {
            return cache.get(keyCount, new Callable<Double>() {
                @Override
                public Double call() throws InterruptedException {
                    System.out.println(Thread.currentThread().getName() + " is trying to create new value for "
                            + keyCount + " at " + new Date());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    anExternalMethod();
                    return Math.random();
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void anExternalMethod() {

    }


}
