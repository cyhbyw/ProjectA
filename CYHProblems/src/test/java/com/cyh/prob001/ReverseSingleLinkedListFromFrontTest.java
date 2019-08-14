package com.cyh.prob001;

import org.junit.Test;

/**
 * @author: CYH
 * @date: 2019/8/13 0013 6:47
 */
public class ReverseSingleLinkedListFromFrontTest extends SingleListTestBase {

    @Test
    public void test01() {
        doTest(1, 8, 3);
    }

    private void doTest(int st, int en, int k) {
        Node head = prepareData(st, en);
        printNode(head);
        Node newHead = ReverseSingleLinkedListFromFront.packageReverse(head, k);
        printNode(newHead);
        System.out.println("------------------------------------------");
    }

    @Test
    public void test02() {
        doTest(1, 8, 2);
    }

    @Test
    public void test03() {
        doTest(1, 8, 4);
    }

    @Test
    public void test04() {
        doTest(1, 7, 2);
    }

}
