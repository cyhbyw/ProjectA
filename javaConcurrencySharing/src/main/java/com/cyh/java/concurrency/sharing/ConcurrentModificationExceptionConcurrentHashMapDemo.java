package com.cyh.java.concurrency.sharing;

import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanhuche on 5/27/2016.
 */
public class ConcurrentModificationExceptionConcurrentHashMapDemo {

    private static int intKey = 0;
    private Map<Integer, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        new ConcurrentModificationExceptionConcurrentHashMapDemo().run();
    }

    private void run() {
        outputLog();

        for (int i = 0; i < 5; i++) {
            map.put(++intKey, new Random().nextInt());
        }

        int maxThreadCount = 20;
        for (int i = 0; i < maxThreadCount; i++) {
            new Thread(new TaskForTest()).start();
        }
    }

    private void outputLog() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(new Date());
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    class TaskForTest implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    int x = (int) (Math.random() * 3);
                    if (x == 0) {
                        funb();
                    } else if (x == 1) {
                        map.clear();
                    } else if (x == 2) {
                        map.put(++intKey, new Random().nextInt());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * Work OK.
         * */
        private void funb() {
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                entry.getKey();
                entry.getValue();
            }
        }

    }

}
