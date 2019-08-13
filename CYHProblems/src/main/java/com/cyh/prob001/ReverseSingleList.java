package com.cyh.prob001;

import java.util.Objects;

/**
 * 反转单链表
 * @author: CYH
 * @date: 2019/8/13 0013 8:28
 */
public class ReverseSingleList {

    public static Node reverse(Node head) {
        if (Objects.isNull(head)) {
            return null;
        }

        Node p = head;
        Node previous = null;
        while (Objects.nonNull(p)) {
            Node next = p.next;
            p.next = previous;
            previous = p;
            p = next;
        }
        return previous;
    }

}
