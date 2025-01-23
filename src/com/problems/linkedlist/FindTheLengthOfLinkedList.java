package com.problems.linkedlist;

import com.ds.linkedlist.Node;

/*
 *
 * problem links :
 * https://www.codingninjas.com/studio/problems/count-nodes-of-linked-list_5884
 *
 * Solution link
 *
 * https://takeuforward.org/linked-list/find-the-length-of-a-linked-list/
 * */
public class FindTheLengthOfLinkedList {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // recursive way
    private static void type2() {
        Node head = new Node(3, 4, 5, 2, 6, 1, 9, -1);
        int count = length(head);
        System.out.println(count);
    }

    private static int length(Node node) {
        if (null == node) return 0;
        return 1 + length(node.next);
    }

    // iterative way
    private static void type1() {
        Node head = new Node(3, 4, 5, 2, 6, 1, 9, -1);

        int count = 0;
        Node node = head;
        while (null != node) {
            count++;
            node = node.next;
        }
        System.out.println(count);
    }


}
