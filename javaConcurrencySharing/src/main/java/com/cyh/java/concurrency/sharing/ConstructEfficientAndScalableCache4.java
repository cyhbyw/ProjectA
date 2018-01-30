package com.cyh.java.concurrency.sharing;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanhuche on 4/26/2016. Final implementation.
 */
public class ConstructEfficientAndScalableCache4 {

    private ConcurrentMap<Integer, Future<Double>> testMap = new ConcurrentHashMap<>();


    public static void main(String[] args) {
        new ConstructEfficientAndScalableCache4().run();
    }

    private void run() {
        int maxThreadCount = 10;
        while (maxThreadCount-- > 0) {
            Runnable task = new OneTask();
            new Thread(task).start();
        }

    }

    private Double retrieveValueFromCache(final Integer key) {
        while (true) {
            Future<Double> result = testMap.get(key);
            if (result == null) {
                Callable<Double> doubleCallable = new Callable<Double>() {
                    @Override
                    public Double call() throws Exception {
                        return createValue(key);
                    }
                };
                FutureTask<Double> doubleFutureTask = new FutureTask<>(doubleCallable);
                result = testMap.putIfAbsent(key, doubleFutureTask);
                if (result == null) {
                    result = doubleFutureTask;
                    doubleFutureTask.run();
                }
            }

            try {
                return result.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private double createValue(Integer key) {
        System.out.println(Thread.currentThread().getName() + " is trying to create new value for " + key);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Math.random();
    }

    class OneTask implements Runnable {
        @Override
        public void run() {
            int keyCount = 10;
            while (keyCount-- > 0) {
                retrieveValueFromCache(keyCount);
            }
        }
    }
}
