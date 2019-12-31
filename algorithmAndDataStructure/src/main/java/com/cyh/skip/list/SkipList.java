package com.cyh.skip.list;

import java.util.Random;

/**
 * 跳表的一种实现方法。
 * 跳表中存储的是正整数，并且存储的是不重复的。
 *
 * Author: Wang Zheng
 */
public class SkipList {

    private static final int MAX_LEVEL = 8;
    private int levelCount = 1;
    /** 带头链表 */
    private Node head = new Node();
    private Random r = new Random();


    public Node find(int value) {
        Node p = head;
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
        }

        if (p.forwards[0] != null && p.forwards[0].data == value) {
            return p.forwards[0];
        } else {
            return null;
        }
    }

    public void insert(int value) {
        final int level = randomLevel();
        // 记录每一层中小于插入值的最大值，也就是找到单链表中的前驱节点
        Node[] maxSmallerThan = new Node[level];
        Node p = head;
        for (int i = level - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            maxSmallerThan[i] = p;
        }

        Node newNode = new Node();
        newNode.data = value;
        newNode.maxLevel = level;
        for (int i = 0; i < level; ++i) {
            newNode.forwards[i] = maxSmallerThan[i].forwards[i];
            maxSmallerThan[i].forwards[i] = newNode;
        }

        levelCount = Math.max(levelCount, level);
    }

    public void delete(int value) {
        Node[] maxSmallerThan = new Node[levelCount];
        Node p = head;
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            maxSmallerThan[i] = p;
        }

        if (p.forwards[0] != null && p.forwards[0].data == value) {
            for (int i = levelCount - 1; i >= 0; --i) {
                if (maxSmallerThan[i].forwards[i] != null && maxSmallerThan[i].forwards[i].data == value) {
                    maxSmallerThan[i].forwards[i] = maxSmallerThan[i].forwards[i].forwards[i];
                }
            }
        }
    }

    /**
     * 随机 level 次，如果是奇数层数 +1，防止伪随机
     * @return
     */
    private int randomLevel() {
        int level = 1;
        for (int i = 1; i < MAX_LEVEL; ++i) {
            if (r.nextInt() % 2 == 1) {
                level++;
            }
        }
        return level;
    }

    public void printAll() {
        System.out.println("\n==================================================");
        for (int i = levelCount - 1; i >= 0; i--) {
            System.out.printf("Level: %02d: ", i);
            for (Node x = head.forwards[i]; x != null; x = x.forwards[i]) {
                System.out.printf("%3d", x.data);
            }
            System.out.println();
        }
    }

    public class Node {
        private int data = -1;
        private Node[] forwards = new Node[MAX_LEVEL];
        private int maxLevel = 0;

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Node:{data=").append(data).append(", levels=").append(maxLevel).append("}");
            return builder.toString();
        }
    }

}

