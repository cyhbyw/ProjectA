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
        System.out.println(skipList.find(1));

        skipList.insert(4);
        System.out.println(skipList.find(1));
        System.out.println(skipList.find(4));

        skipList.insert(3);
        System.out.println(skipList.find(1));
        System.out.println(skipList.find(3));
        System.out.println(skipList.find(4));

        skipList.insert(7);
        System.out.println(skipList.find(7));

        skipList.insert(5);
        System.out.println(skipList.find(3));
        System.out.println(skipList.find(5));
        System.out.println(skipList.find(7));

        skipList.insert(8);
        skipList.insert(9);
        skipList.insert(10);
        skipList.insert(13);
        skipList.insert(16);
        skipList.insert(17);
        skipList.insert(18);
    }
}
