package com.cyh.__java.util.concurrent;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class MyTest01 {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(1); //此处可使用CountDownLatch(1)
        for (int i = 0; i < 3; i++) {
            new MyThread01((char) (97 + i), phaser).start();
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        phaser.arrive(); //此处可使用latch.countDown()
    }
}


class MyThread01 extends Thread {
    private char c;
    private Phaser phaser;

    public MyThread01(char c, Phaser phaser) {
        this.c = c;
        this.phaser = phaser;
    }

    @Override
    public void run() {
        phaser.awaitAdvance(phaser.getPhase()); //此处可使用latch.await()
        for (int i = 0; i < 100; i++) {
            System.out.print(c + " ");
            if (i % 10 == 9) {
                System.out.println();
            }
        }
    }
}
