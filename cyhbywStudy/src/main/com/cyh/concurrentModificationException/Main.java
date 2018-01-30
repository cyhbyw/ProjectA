package com.cyh.concurrentModificationException;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by CYH on 2016/3/9.
 */
public class Main {


    public static void main(String[] args) {
        Main cyh = new Main();
        cyh.run();
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(new Date());
            }
        }, 0, 10, TimeUnit.SECONDS);
    }

    private void run() {
        int countForGetThread = 50;
        for (int e = 0; e < countForGetThread; e++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        for (String str : Singleton.getInstance().getDirectories().getDirectoryList()) {
//                            System.out.println(str);
                            try {
                                TimeUnit.SECONDS.sleep((long) (Math.random() * 20));
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            }).start();
        }

        int countForSetThread = 50;
        for (int e = 0; e < countForSetThread; e++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        Singleton.getInstance().updateDirectoryInfo();
                    }
                }
            }).start();
        }
    }


}
