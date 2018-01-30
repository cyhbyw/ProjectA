package com.cyh.__java.util.concurrent;

import java.util.concurrent.Phaser;

public class PhaserTest1 {

    public static void main(String args[]) {
        final int count = 5;
        final Phaser phaser = new Phaser(count);
        for (int i = 0; i < count; i++) {
            System.out.println("starting thread, id: " + i);
            final Thread thread = new Thread(new Task(i, phaser));
            thread.start();
        }
    }

    public static class Task implements Runnable {
        private final int id;
        private final Phaser phaser;

        public Task(int id, Phaser phaser) {
            this.id = id;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println("id " + id);
            phaser.arriveAndAwaitAdvance();
            System.out.println("in Task.run(), phase: " + phaser.getPhase() + ", id: " + this.id);
        }
    }
}
