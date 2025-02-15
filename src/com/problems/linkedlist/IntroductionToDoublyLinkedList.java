package com.problems.linkedlist;

import com.ds.linkedlist.DNode;

import static com.util.PrintUtl.print;

/*
 *
 * problem links :
 * https://www.codingninjas.com/studio/problems/introduction-to-doubly-linked-list_8160413
 *
 * Solution link :
 *
 *
 * https://takeuforward.org/binary-search/introduction-to-doubly-linked-list/
 * */
public class IntroductionToDoublyLinkedList {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // we will add a random node
    private static void type2() {
        int[] arr = {4, 2, 5, 1};
        DNode head = new DNode(-1);
        DNode node = head;
        for (int num : arr) {
            DNode current = new DNode(num);
            node.next = current;
            current.prev = node;
            node = current;
        }
        // lastly, we remove the first dummy pointer
        head = head.next;
        head.prev = null;
        print(head);
    }

    private static void type1() {
        int[] arr = {4, 2, 5, 1};
        DNode node = null, head = null;
        for (int num : arr) {
            if (node == null) {
                node = new DNode(num);
                head = node;
            } else {
                DNode current = new DNode(num);
                node.next = current;
                current.prev = node;
                node = current;
            }
        }
        print(head);
    }
}
