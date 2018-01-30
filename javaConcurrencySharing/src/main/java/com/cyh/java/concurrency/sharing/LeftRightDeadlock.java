package com.cyh.java.concurrency.sharing;

import java.util.concurrent.Executors;

public class LeftRightDeadlock {

    private final Object left = new Object();
    private final Object right = new Object();

    public static void main(String[] args) {
        new LeftRightDeadlock().run();
    }

    private void run() {
        Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    leftRight();
                }
            }
        });
        Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    rightLeft();
                }
            }
        });
    }


    public void leftRight() {
        synchronized (left) {
            synchronized (right) {
                System.out.println("left to right OK");
                doSomething();
            }
        }
    }

    public void rightLeft() {
        synchronized (right) {
            synchronized (left) {
                System.out.println("right to left OK");
                doSomethingElse();
            }
        }
    }

    void doSomething() {}

    void doSomethingElse() {}
}
