package com.problems.linkedlist;

import com.ds.linkedlist.Node;

import static com.util.PrintUtl.print;

/*
 *
 * problem links :
 *
 * Solution link
 *
 * https://takeuforward.org/data-structure/delete-last-node-of-linked-list/
 * */
public class DeleteLastNodeInALinkedList {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        Node node = new Node(2, 5, 8, 7);
        print(node);
        node = deleteTail(node);
        print(node);
    }

    private static Node deleteTail(Node head) {
        // Check if the linked list is empty or has only one node
        if (head == null || head.next == null) return null;
        // Create a temporary pointer for traversal
        Node node = head;
        // Traverse the list until the second-to-last node
        while (node.next.next != null)
            node = node.next;
        // detach the connection from the second-to-last node to delete the last node
        node.next = null;
        // Return the updated head of the linked list
        return head;
    }

}
