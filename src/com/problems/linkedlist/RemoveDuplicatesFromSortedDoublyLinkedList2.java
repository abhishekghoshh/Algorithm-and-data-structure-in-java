package com.problems.linkedlist;

import com.ds.linkedlist.Node;

import static com.util.PrintUtl.print;

/*
 * problem links :
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=R6-PnHODewY
 * https://www.youtube.com/watch?v=9iJ3UsB9vMo
 *
 * */
public class RemoveDuplicatesFromSortedDoublyLinkedList2 {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // optimized approach
    private static void type2() {
        Node head = new Node(1, 2, 3, 3, 4, 4, 5);
        Node curr = head;
        Node newHead = new Node(Integer.MIN_VALUE);
        Node prev = newHead;
        while (curr != null) {
            Node next = curr.next;
            // we are checking if the current value is same as the next value or not
            while (next != null && curr.data == next.data)
                next = next.next;
            // we will only add current if current.next is the same as next or not
            // in simple words the previous loop executed or not
            if (curr.next == next) {
                prev.next = curr;
                prev = curr;
            }
            // assigning the curr to the next
            curr = next;
        }
        prev.next = null;
        head = newHead.next;
        print(head);
    }

    // brute force approach
    // stores the frequencies in a map
    // add those nodes which have only one occurrence
    private static void type1() {

    }
}
