package com.cyh.java.concurrency.sharing;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class UnsafeSequence_Volatile {
    private volatile int value = 0;


    public static void main(String[] args) {
        new UnsafeSequence_Volatile().run();
    }

    private void run() {
        int threadCount = 20;
        final int operationTimes = 10000;
        Future<?>[] future = new Future[threadCount];
        for (int threadIndex = 0; threadIndex < threadCount; threadIndex++) {
            future[threadIndex] = Executors.newSingleThreadExecutor().submit(new Runnable() {
                @Override
                public void run() {
                    for (int operationIndex = 0; operationIndex < operationTimes; operationIndex++) {
                            getNext();
                    }
                }
            });
        }

        while (true) {
            boolean allTaskCompleted = true;
            for (int threadIndex = 0; threadIndex < threadCount; threadIndex++) {
                if (!future[threadIndex].isDone()) {
                    allTaskCompleted = false;
                    break;
                }
            }
            if (allTaskCompleted) {
                break;
            }
        }
        System.out.println("value " + value);
        System.exit(0);
    }

    public int getNext() {
        return ++value;
    }
}
