package com.cyh.prob001;

import java.util.Objects;

/**
 * @author: CYH
 * @date: 2019/8/13 0013 8:34
 */
public class SingleListTestBase {

    protected Node prepareData(int st, int en) {
        Node next = null;
        Node node = null;
        for (int x = en; x >= st; x--) {
            node = new Node(x, next);
            next = node;
        }
        return node;
    }

    protected void printNode(Node head) {
        Node p = head;
        while (Objects.nonNull(p)) {
            System.out.print(p.value + " -> ");
            p = p.next;
        }
        System.out.println();
    }


}
