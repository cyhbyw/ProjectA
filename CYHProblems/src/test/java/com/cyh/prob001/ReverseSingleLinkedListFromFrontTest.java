package com.cyh.prob001;

import org.junit.Test;

/**
 * @author: CYH
 * @date: 2019/8/13 0013 6:47
 */
public class ReverseSingleLinkedListFromFrontTest extends SingleListTestBase {

    @Test
    public void test01() {
        Node head = prepareData(1, 8);
        printNode(head);
        int k = 3;
        Node newHead = ReverseSingleLinkedListFromFront.packageReverse(head, k);
        printNode(newHead);
    }

    @Test
    public void test02() {
        Node head = prepareData(1, 8);
        printNode(head);
        int k = 2;
        Node newHead = ReverseSingleLinkedListFromFront.packageReverse(head, k);
        printNode(newHead);
    }

    @Test
    public void test03() {
        Node head = prepareData(1, 8);
        printNode(head);
        int k = 4;
        Node newHead = ReverseSingleLinkedListFromFront.packageReverse(head, k);
        printNode(newHead);
    }

}
