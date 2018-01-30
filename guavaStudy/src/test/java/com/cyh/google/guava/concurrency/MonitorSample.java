package com.cyh.google.guava.concurrency;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.util.concurrent.Monitor;

/**
 * Monitor类语义和 synchronized 或者 ReentrantLocks是一样的， 只允许一个线程进入
 */
public class MonitorSample {

    private static final int MAX_SIZE = 3;

    private Monitor monitor = new Monitor();
    private List<String> list = new ArrayList<String>();


    Monitor.Guard listBelowCapacity = new Monitor.Guard(monitor) {
        @Override
        public boolean isSatisfied() {
            return list.size() < MAX_SIZE;
        }
    };

    public void addToList(String item) throws InterruptedException {
        // 超过MAX_SIZE， 会锁死
        // monitor.enterWhen(listBelowCapacity);

        // 超过返回false 不会锁死
        Boolean a = monitor.tryEnterIf(listBelowCapacity);
        try {
            list.add(item);
        } finally { // 确保线程会推出Monitor锁
            monitor.leave();
        }
    }

    public static void main(String[] args) {
        MonitorSample monitorSample = new MonitorSample();
        for (int count = 0; count < 5; count++) {
            try {
                monitorSample.addToList(count + "");
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        Iterator iteratorStringList = monitorSample.list.iterator();
        while (iteratorStringList.hasNext()) {
            System.out.println(iteratorStringList.next());
        }
    }

}
