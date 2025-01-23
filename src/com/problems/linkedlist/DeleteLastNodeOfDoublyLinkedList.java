package com.problems.linkedlist;

import com.ds.linkedlist.DNode;
import com.util.PrintUtl;

/*
 *
 * problem links :
 * https://www.codingninjas.com/studio/problems/delete-last-node-of-a-doubly-linked-list_8160469
 *
 * Solution link :
 *
 *
 * https://www.codingninjas.com/studio/problems/delete-last-node-of-a-doubly-linked-list_8160469
 * */
public class DeleteLastNodeOfDoublyLinkedList {
    public static void main(String[] args) {
        type1();
    }

    // iterative way
    private static void type1() {
        DNode head = new DNode(4, 10, 3, 5, 20);
        head = deleteLastNode(head);
        PrintUtl.print(head);
    }

    private static DNode deleteLastNode(DNode head) {
        // if it is either null or single element, then we will return null
        if (head == null || head.next == null) return null;

        // as there is a previous pointer attached, so we can go to the
        // last pointer unlike a singly linked list
        // but we will follow the same approach
        // like we have done in singly linked list
        DNode current = head;
        while (current.next.next != null) current = current.next;
        DNode next = current.next;
        next.prev = null;
        current.next = null;
        return head;
    }
}
