package com.cyh.__java.util.concurrent;

import java.util.concurrent.Phaser;

public class PhaserTest6 {
    //
    private static final int TASKS_PER_PHASER = 4;

    public static void main(String args[]) throws Exception {
        //
        final int phaseToTerminate = 3;
        final Phaser phaser = new Phaser() {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("======phase " + phase + " ======");
                return phase == phaseToTerminate || registeredParties == 0;
            }
        };

        //
        final Task tasks[] = new Task[10];
        build(tasks, 0, tasks.length, phaser);
        for (int i = 0; i < tasks.length; i++) {
            System.out.println("starting thread, id: " + i);
            final Thread thread = new Thread(tasks[i]);
            thread.start();
        }
    }

    public static void build(Task[] tasks, int lo, int hi, Phaser ph) {
        if (hi - lo > TASKS_PER_PHASER) {
            for (int i = lo; i < hi; i += TASKS_PER_PHASER) {
                int j = Math.min(i + TASKS_PER_PHASER, hi);
                build(tasks, i, j, new Phaser(ph));
            }
        } else {
            for (int i = lo; i < hi; ++i) {
                tasks[i] = new Task(i, ph);
            }
        }
    }

    public static class Task implements Runnable {
        //
        private final int id;
        private final Phaser phaser;

        public Task(int id, Phaser phaser) {
            this.id = id;
            this.phaser = phaser;
            this.phaser.register();
        }

        @Override
        public void run() {
            while (!phaser.isTerminated()) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    // NOP
                }
                System.out.println("in Task.run(), phase: " + phaser.getPhase() + ", id: " + this.id);
                phaser.arriveAndAwaitAdvance();
            }
        }
    }
}
