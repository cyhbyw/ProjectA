package com.cyh.skip.list;

import org.junit.Test;

/**
 * @author: CYH
 * @date: 2019/4/22 0022 8:27
 */
public class SkipListTest {

    /**
     * 插入时，故意打乱数字的大小顺序，方便分析源码: 1 -> 4 -> 3 -> 7 -> 5
     * 如果全部是从小到大的顺序，要不得
     */
    @Test
    public void test_insertAndFind() {
        SkipList skipList = new SkipList();
        skipList.insert(1);
        skipList.printAll();
        System.out.println(skipList.find(1));

        skipList.insert(4);
        skipList.printAll();
        System.out.println(skipList.find(1));
        System.out.println(skipList.find(4));

        skipList.insert(3);
        skipList.printAll();
        System.out.println(skipList.find(1));
        System.out.println(skipList.find(3));
        System.out.println(skipList.find(4));

        skipList.insert(7);
        skipList.printAll();
        System.out.println(skipList.find(7));

        skipList.insert(5);
        skipList.printAll();
        System.out.println(skipList.find(3));
        System.out.println(skipList.find(5));
        System.out.println(skipList.find(7));

        skipList.insert(8);
        skipList.printAll();
        skipList.insert(9);
        skipList.printAll();
        skipList.insert(10);
        skipList.printAll();
        skipList.insert(13);
        skipList.printAll();
        skipList.insert(16);
        skipList.printAll();
        skipList.insert(17);
        skipList.printAll();
        skipList.insert(18);
        skipList.printAll();
    }
}
