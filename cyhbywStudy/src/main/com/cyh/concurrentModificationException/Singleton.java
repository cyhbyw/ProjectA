package com.cyh.concurrentModificationException;

/**
 * Created by CYH on 2016/3/9.
 */
public class Singleton {

    private Directories directories = new Directories();

    private Singleton() {
    }

    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static final Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Directories getDirectories() {
        return directories;
    }

    public void updateDirectoryInfo() {
        directories = update();
    }

    private Directories update() {
        /*
         * 下面这句话，有，或者没有，差距很大。
         *
         * 如果有，则是线程安全的。
         * 原因：
         * 在这个方法内部，Directories directories = new Directories()会创建一个新的对象，并在内存中开辟新的空间。
         * 而后，再将这个对象赋值给其调用方法时，调用方法会得到这个新的对象（对应新的地址空间）。
         * 简单说，域directories所指向的地址改变了。
         *
         * 如果没有，则线程不安全。
         * 原因：
         * 域directories中的directoryList所指向的地址没有改变。
         * 但是地址中的值（也就是directoryList中存储的值）改变了。
         * 这样，很可能会抛出ConcurrentModificationException。
         *
         * 注释下面这句话试试看！
         */
        Directories directories = new Directories();

        directories.getDirectoryList().clear();
        int num = (int) (Math.random() * 10) + 1;
        for (int i = 0; i < num; i++) {
            directories.getDirectoryList().add(String.valueOf(i));
        }
        return directories;
    }

}
