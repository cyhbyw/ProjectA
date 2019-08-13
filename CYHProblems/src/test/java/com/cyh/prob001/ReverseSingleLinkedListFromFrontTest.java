package com.cyh.prob001;

import java.util.Objects;

import org.junit.Test;

/**
 * @author: CYH
 * @date: 2019/8/13 0013 6:47
 */
public class ReverseSingleLinkedListFromFrontTest {

    @Test
    public void test01() {
        Node head = prepareData();
        printNode(head);
        int k = 3;
        Node newHead = ReverseSingleLinkedListFromFront.packageReverse(head, k);
        printNode(newHead);
    }

    private Node prepareData() {
        Node next = null;
        Node node = null;
        for (int x = 8; x >= 1; x--) {
            node = new Node(x, next);
            next = node;
        }
        return node;
    }

    private void printNode(Node head) {
        Node p = head;
        while (Objects.nonNull(p)) {
            System.out.print(p.value + " -> ");
            p = p.next;
        }
        System.out.println();
    }

}
