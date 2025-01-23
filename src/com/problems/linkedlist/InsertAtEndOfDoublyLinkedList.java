package com.problems.linkedlist;

import com.ds.linkedlist.DNode;

import static com.util.PrintUtl.print;

/*
 *
 * problem links :
 * https://www.codingninjas.com/studio/problems/insert-at-end-of-doubly-linked-list_8160464
 *
 * Solution link :
 *
 *
 * https://takeuforward.org/data-structure/insert-at-end-of-doubly-linked-list/
 * */
public class InsertAtEndOfDoublyLinkedList {
    public static void main(String[] args) {
        type1();
    }

    // iterative way
    // we will find the last nonNull node
    // then attach the new node
    private static void type1() {
        DNode node = new DNode(4, 10, 3, 5);
        int k = 9;
        DNode head = insertAtTail1(node, k);
        print(head);
    }

    private static DNode insertAtTail1(DNode head, int k) {
        DNode node = new DNode(k);
        if (head == null) return node;
        DNode current = head;
        // Traverse to the end of the doubly linked list
        while (null != current.next)
            current = current.next;
        current.next = node;
        node.prev = current;
        return head;
    }
}
