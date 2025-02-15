package com.problems.linkedlist;

import com.ds.linkedlist.Node;

import static com.util.PrintUtl.print;

/*
 *
 * problem links :
 * https://www.codingninjas.com/studio/problems/introduction-to-linked-list_8144737
 *
 * Solution link :
 *
 *
 * https://takeuforward.org/linked-list/linked-list-introduction/
 * */
public class IntroductionToLinkedList {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // we will use a dummy node
    private static void type2() {
        int[] arr = {4, 2, 5, 1};
        // we will add random data to the head;
        Node head = new Node(-1);
        Node node = head;

        // we will traverse the array and add the number to the linked list
        for (int num : arr) {
            node.next = new Node(num);
            node = node.next;
        }
        // lastly, we will remove that random data
        head = head.next;
        print(head);
    }

    private static void type1() {
        int[] arr = {4, 2, 5, 1};
        Node head = null, node = null;

        // we will use two pointer
        // one for traversal and one for returning the answer
        for (int num : arr) {
            if (node == null) {
                node = new Node(num);
                head = node;
            } else {
                node.next = new Node(num);
                node = node.next;
            }
        }
        print(head);
    }

}
