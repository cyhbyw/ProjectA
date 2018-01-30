package com.cyh.java.concurrency.sharing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanhuche on 5/27/2016.
 */
public class ConcurrentModificationExceptionDemo {

    private List<Integer> integerList = Collections.synchronizedList(new ArrayList<Integer>());

    public static void main(String[] args) {
        new ConcurrentModificationExceptionDemo().run();
    }

    private void run() {
        outputLog();

        for (int i = 0; i < 5; i++) {
            integerList.add(i);
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
                        // choose one of the following three methods to test.
                        //                        funa();
                        //                        funb();
                        func();
                    } else if (x == 1) {
                        integerList.clear();
                    } else if (x == 2) {
                        integerList.add((int) (Math.random() * 10));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * Will throw IndexOutOfBoundsException.
         * */
        private void funa() {
            int size = integerList.size();
            for (int i = 0; i < size; i++) {
                // System.out.println(integerList.get(i));
                integerList.get(i);
            }
        }

        /**
         * Will throw ConcurrentModificationException.
         * */
        private void funb() {
            for (Integer value : integerList) {
                // System.out.println(value);
            }
        }

        /**
         * Work OK.
         * */
        private void func() {
            synchronized (integerList) {
                for (Integer value : integerList) {
                    // System.out.println(value);
                }
            }
        }
    }



}
