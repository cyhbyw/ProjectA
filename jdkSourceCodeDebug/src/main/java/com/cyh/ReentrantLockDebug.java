package com.cyh;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: CYH
 * @date: 2019/3/30 0030 11:40
 */
public class ReentrantLockDebug {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReentrantLockDebug.class);

    public static void main(String[] args) throws InterruptedException {
        new ReentrantLockDebug().debug();
    }

    private ReentrantLock lock = new ReentrantLock();

    private void debug() throws InterruptedException {
        new Thread(() -> businessLogic(60), "第一个能获取到锁的线程").start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> businessLogic(30), "第一个不能获取到锁的线程").start();
    }

    private void businessLogic(int sleepSecond) {
        LOGGER.info("准备获取锁");
        long startTime = System.currentTimeMillis();
        lock.lock();
        try {
            LOGGER.info("成功获取到锁（耗时{}ms），将休息{}s", (System.currentTimeMillis() - startTime), sleepSecond);
            TimeUnit.SECONDS.sleep(sleepSecond);
            LOGGER.info("休息完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("准备释放锁");
        lock.unlock();
        LOGGER.info("释放锁完成");
    }

}
