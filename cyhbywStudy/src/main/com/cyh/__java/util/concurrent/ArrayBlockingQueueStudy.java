package com.cyh.__java.util.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yanhuche on 7/31/2016.
 */
public class ArrayBlockingQueueStudy {

    public static void main(String[] args) {
        new ArrayBlockingQueueStudy().run();
    }

    private void run() {
        final int MAX_THREAD = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD << 1);

        for (int i = 0; i < MAX_THREAD; i++) {
            executorService.submit(new Producer());
        }

        for (int i = 0; i < MAX_THREAD; i++) {
            executorService.submit(new Consumer());
        }
    }


    class Producer implements Runnable {
        @Override
        public void run() {
            while (true) {
                addElement();
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                transferAllElementToAnotherList();
            }
        }
    }

    private Map<Integer, ArrayBlockingQueue<Double>> testMap = new ConcurrentHashMap<>();

    private void addElement() {
        final int MAX_NUMBER = 10;
        for (int i = 0; i < MAX_NUMBER; i++) {
            ArrayBlockingQueue<Double> blockingQueue = testMap.get(i);
            if (blockingQueue == null) {
                blockingQueue = new ArrayBlockingQueue<>(10000);
                testMap.put(i, blockingQueue);
            }
            try {
                blockingQueue.add(Math.random());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void transferAllElementToAnotherList() {
        for (Map.Entry<Integer, ArrayBlockingQueue<Double>> entry : testMap.entrySet()) {
            ArrayBlockingQueue<Double> blockingQueue = entry.getValue();
            if (blockingQueue.isEmpty()) {
                continue;
            }

            List<Double> anotherList = new ArrayList();
            int doubleListSize = blockingQueue.size();
            blockingQueue.drainTo(anotherList);
            int anotherListSize = anotherList.size();

            if (doubleListSize != anotherListSize) {
                System.out.println("doubleListSize: " + doubleListSize + ", anotherListSize: " + anotherListSize);
            }
        }
    }


}
