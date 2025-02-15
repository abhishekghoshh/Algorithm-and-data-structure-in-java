package com.problems.linkedlist;

import com.ds.linkedlist.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.util.PrintUtl.print;

/*
 * problem links :
 * https://www.codingninjas.com/studio/problems/sort-linked-list-of-0s-1s-2s_1071937
 *
 * Solution link :
 * https://www.youtube.com/watch?v=gRII7LhdJWc
 *
 * */
public class SortLinkedListOfZeroOneTwo {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // optimized approach
    private static void type2() {
        Node head = new Node(1, 0, 2, 1, 0, 2, 1);
        // we will start with three dummy nodes for zero one and two.
        // we are adding dummy because we have to check null otherwise
        // we will keep track of the head and tail of 0, 1 and 2.
        Node head0 = new Node();
        Node tail0 = head0;
        Node head1 = new Node();
        Node tail1 = head1;
        Node head2 = new Node();
        Node tail2 = head2;
        Node node = head;
        // based on the node value, we will attach the node to the tail
        // of 1 or 2 or 3
        while (null != node) {
            if (node.data == 0) {
                tail0.next = node;
                tail0 = node;
            } else if (node.data == 1) {
                tail1.next = node;
                tail1 = node;
            } else {
                tail2.next = node;
                tail2 = node;
            }
            node = node.next;
        }
        // we will now connect to tail of 0 to head or 1 or 2
        tail0.next = (head1.next != null) ? head1.next : head2.next;
        // we will now connect to tail of 1 to the head of 2
        tail1.next = head2.next;
        // and lastly tail of two will point to null
        tail2.next = null;
        head = head0.next;
        print(head);
    }

    // brute force approach
    private static void type1() {
        Node head = new Node(1, 0, 2, 1, 0, 2, 1);
        Node node = head;
        List<Integer> list = new ArrayList<>();
        while (null != node) {
            list.add(node.data);
            node = node.next;
        }
        node = head;
        Collections.sort(list);
        for (int num : list) {
            node.data = num;
            node = node.next;
        }
        print(head);
    }
}
