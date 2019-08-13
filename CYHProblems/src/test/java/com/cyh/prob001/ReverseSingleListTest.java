package com.cyh.prob001;

import org.junit.Test;

/**
 * @author: CYH
 * @date: 2019/8/13 0013 8:33
 */
public class ReverseSingleListTest extends SingleListTestBase {

    @Test
    public void reverse() {
        Node head = prepareData(1, 3);
        printNode(head);
        Node newHead = ReverseSingleList.reverse(head);
        printNode(newHead);
    }
}
