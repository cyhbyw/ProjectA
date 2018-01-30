package com.cyh.__java.util.concurrent;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanhuche on 4/26/2016.
 *
 * 测试当至少有两个线程向这个ConcurrentHashMap写入数据时，如何确保相同数据（重复数据）只写入一次
 */
public class ConcurrentHashMapTest {

    Map<Integer, Double> testMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        ConcurrentHashMapTest cyh = new ConcurrentHashMapTest();
        cyh.run();
    }

    private void run() {
        int maxThreadCount = 100;
        for (int i = 0; i < maxThreadCount; i++) {
            new Thread(new OneTask()).start();
        }

    }


    class OneTask implements Runnable {
        @Override
        public void run() {
            while (true) {
                Integer key = new Random().nextInt(5);
                if (testMap.get(key) != null) {
                } else {
                    buildValueAndPut(key);
                }
            }
        }

        private void buildValueAndPut(Integer key) {
            Double value = createValue(key);
            testMap.put(key, value);
        }

        private double createValue(Integer key) {
            System.out.println(Thread.currentThread().getName() + " is trying to create new value for " + key);
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Math.random();
        }
    }
}
