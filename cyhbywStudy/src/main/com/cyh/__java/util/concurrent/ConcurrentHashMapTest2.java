package com.cyh.__java.util.concurrent;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanhuche on 4/26/2016.
 *
 * 测试当至少有两个线程向这个ConcurrentHashMap写入数据时，如何确保相同数据（重复数据）只写入一次
 */
public class ConcurrentHashMapTest2 {

    ConcurrentMap<Integer, FutureTask<Double>> testMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        ConcurrentHashMapTest2 cyh = new ConcurrentHashMapTest2();
        cyh.run();
    }

    private void run() {
        int maxThreadCount = 100;
        for (int i = 0; i < maxThreadCount; i++) {
            new Thread(new OneTask(), "cyhTestThread_" + i).start();
        }

    }


    class OneTask implements Runnable {
        @Override
        public void run() {
            Integer key = new Random().nextInt(5);
            System.out.println(Thread.currentThread().getName() + " " + getValueFromMap(key));
        }

        public Double getValueFromMap(final Integer key) {
            FutureTask<Double> futureTask = testMap.get(key);
            if (futureTask == null) {
                System.out.println(Thread.currentThread().getName() + " thinks 'futureTask == null' for " + key);
                Callable<Double> callable = new Callable<Double>() {
                    @Override
                    public Double call() throws Exception {
                        return createValue(key);
                    }
                };
                FutureTask<Double> futureTask2 = new FutureTask<>(callable);
                futureTask = testMap.putIfAbsent(key, futureTask2);
                if (futureTask == null) {
                    futureTask = futureTask2;
                    futureTask2.run();
                }
            }
            try {
                return futureTask.get();
            } catch (InterruptedException e) {
                System.err.println("InterruptedException");
            } catch (ExecutionException e) {
                System.err.println(Thread.currentThread().getName() + "  ExecutionException");
            }
            return null;
        }

        private double createValue(Integer key) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " is_trying_to_create_new_value_for " + key);
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(10000));
            if (key == 0) {
                throw new RuntimeException("test exception");
            }
            return Math.random();
        }
    }
}
