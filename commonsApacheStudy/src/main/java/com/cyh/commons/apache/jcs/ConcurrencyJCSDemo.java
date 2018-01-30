package com.cyh.commons.apache.jcs;

import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.access.CacheAccess;
import org.apache.commons.jcs.access.exception.CacheException;

import java.util.concurrent.TimeUnit;

/**
 * Created by yanhuche on 9/12/2016.
 */
public class ConcurrencyJCSDemo {

    public static void main(String[] args) {
        new ConcurrencyJCSDemo().run();
    }

    private CacheAccess<Integer, Double> cache = null;

    public ConcurrencyJCSDemo() {
        try {
            cache = JCS.getInstance("default");
        } catch (CacheException e) {
            System.out.println(String.format("Problem initializing cache: %s", e.getMessage()));
        }
    }

    private void run() {
        int maxThreadCount = 10;
        while (maxThreadCount-- > 0) {
            Runnable task = new OneTask();
            new Thread(task).start();
        }
    }

    private Double retrieveValueFromCache(Integer key) {
        Double result = cache.get(key);
        if (result == null) {
            result = createValue(key);
            cache.put(key, result);
        }
        return result;
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
