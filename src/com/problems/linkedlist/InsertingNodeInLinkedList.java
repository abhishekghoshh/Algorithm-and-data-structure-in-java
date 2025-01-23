package com.problems.linkedlist;

import com.ds.linkedlist.Node;

import static com.util.PrintUtl.print;

/*
 *
 * problem links :
 * https://www.codingninjas.com/studio/problems/insert-node-at-the-beginning_8144739
 *
 * Solution link :
 *
 *
 * https://takeuforward.org/data-structure/insert-node-at-beginning-of-linked-list/
 * */
public class InsertingNodeInLinkedList {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // recursive way
    // to insert a node in the tail
    private static void type2() {
        Node node = new Node(4, 10, 3, 5);
        int k = 9;
        Node head = insertAtTail2(node, k);
        print(head);
    }

    private static Node insertAtTail2(Node head, int k) {
        if (head == null) return new Node(k);
        head.next = insertAtTail2(head.next, k);
        return head;
    }

    // here we will create a node
    // then append the head node to the newly created node.
    // we can do another thing, we can replace the data of the head node
    // with the new value and then append a new node to the head and add the previous
    // head data to that newly created node.
    // hence we don't need to return the new pointer address
    // the old head address will still refer to the head
    private static void type1() {
        Node list = new Node(4, 2, 5, 1);
        int newValue = -1;
        Node node = new Node(newValue);
        node.next = list;
        list = node;
        print(list);
    }

}
