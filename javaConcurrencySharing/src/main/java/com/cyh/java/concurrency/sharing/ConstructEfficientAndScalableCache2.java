package com.cyh.java.concurrency.sharing;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanhuche on 4/26/2016. Replacing HashMap with ConcurrentHashMap
 */
public class ConstructEfficientAndScalableCache2 {

    private Map<Integer, Double> testMap = new ConcurrentHashMap<>();


    public static void main(String[] args) {
        new ConstructEfficientAndScalableCache2().run();
    }

    private void run() {
        int maxThreadCount = 10;
        while (maxThreadCount-- > 0) {
            Runnable task = new OneTask();
            new Thread(task).start();
        }

    }

    private Double retrieveValueFromCache(Integer key) {
        Double result = testMap.get(key);
        if (result == null) {
            result = createValue(key);
            testMap.put(key, result);
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
