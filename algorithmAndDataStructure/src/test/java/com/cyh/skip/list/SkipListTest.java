package com.cyh.skip.list;

import org.junit.Test;

/**
 * @author: CYH
 * @date: 2019/4/22 0022 8:27
 */
public class SkipListTest {

    @Test
    public void test_insert() {
        SkipList skipList = new SkipList();
        skipList.insert(1);
        skipList.insert(3);
        skipList.insert(4);
        skipList.insert(5);
        skipList.insert(7);
        skipList.insert(8);
        skipList.insert(9);
        skipList.insert(10);
        skipList.insert(13);
        skipList.insert(16);
        skipList.insert(17);
        skipList.insert(18);
    }
}
