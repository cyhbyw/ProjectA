package com.cyh.java.concurrency.sharing;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * Created by yanhuche on 6/21/2016.
 */
public class ConstructEfficientAndScalableCache_LoadingCache {

    public static void main(String[] args) {
        new ConstructEfficientAndScalableCache_LoadingCache().run();
    }

    public ConstructEfficientAndScalableCache_LoadingCache() {
        cache = CacheBuilder.newBuilder().build(new CacheLoader<Integer, Double>() {
            @Override
            public Double load(Integer key) throws Exception {
                System.out.println(Thread.currentThread().getName() + " is trying to create new value for " + key
                        + " at " + new Date());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                anExternalMethod();
                return Math.random();
            }
        });
    }

    private void anExternalMethod() {

    }

    private static LoadingCache<Integer, Double> cache = null;

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
                try {
                    Double value = cache.get(keyCount);
                    System.out.println(Thread.currentThread().getName() + ": " + keyCount + " ==> " + value);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
