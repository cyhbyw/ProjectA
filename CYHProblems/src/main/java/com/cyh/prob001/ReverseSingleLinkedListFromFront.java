package com.cyh.prob001;

import java.util.Objects;

/**
 * @author: CYH
 * @date: 2019/8/13 0013 6:40
 */
public class ReverseSingleLinkedListFromFront {

    public static Node packageReverse(Node head, int k) {
        if (Objects.isNull(head)) {
            return null;
        }
        if (k <= 1) {
            return head;
        }

        Node p = head;
        Node starting = head;
        Node tailing = null;
        Node newHead = null;
        int count = 0;
        while (Objects.nonNull(p)) {
            ++count;
            if (count == 1) {
                starting = p;
            }
            Node next = p.next;
            if (count == k) {
                count = 0;
                ReturnPair returnPair = doReverse(starting, p, tailing);
                tailing = returnPair.tail;
                if (Objects.isNull(newHead)) {
                    newHead = returnPair.head;
                }
            }
            p = next;
        }
        if (count > 0) {
            tailing.next = starting;
        }

        return newHead;
    }

    private static ReturnPair doReverse(Node starting, Node end, Node tailing) {
        Node p = starting;
        Node previous = null;
        Node newTailing = null;
        while (true) {
            Node next = p.next;
            p.next = previous;
            if (Objects.isNull(previous)) {
                newTailing = p;
            }
            if (p == end) {
                if (Objects.nonNull(tailing)) {
                    tailing.next = p;
                }
                break;
            }
            previous = p;
            p = next;
        }
        return new ReturnPair(p, newTailing);
    }

    static class ReturnPair {
        Node head;
        Node tail;

        public ReturnPair(Node head, Node tail) {
            this.head = head;
            this.tail = tail;
        }
    }

}


// https://www.toutiao.com/i6723794105006031371/

// 在做这道题之前，我们不仿先来看看如果从头部开始组起的话，应该怎么做呢
// 例如：链表: 1->2->3->4->5->6->7->8, K = 3
// 调整后：3->2->1 -> 6->5->4 -> 7->8
// 其中 7，8不调整，因为不够一组


// 给定一个单链表的头节点 head,实现一个调整单链表的函数，
// 使得每K个节点之间为一组进行逆序，并且从链表的尾部开始组起，
// 头部剩余节点数量不够一组的不需要逆序。（不能使用队列或者栈作为辅助）
// 例如： 链表:1->2->3->4->5->6->7->8, K = 3
// 那么 6->7->8，3->4->5，1->2各位一组
// 调整后：1->2 -> 5->4->3 -> 8->7->6
// 其中 1，2不调整，因为不够一组
