package com.problems.linkedlist;

import com.ds.linkedlist.Node;
import com.util.PrintUtl;

/*
 *
 * problem links :
 * https://leetcode.com/problems/remove-linked-list-elements/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=JI71sxtHTng
 *
 * https://neetcode.io/solutions/remove-linked-list-elements
 * */
public class RemoveLinkedListElements {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // todo optimized approach
    // we will use a previous pointer
    private static void type2() {
        Node head = new Node(1, 2, 6, 3, 4, 5, 6);
        int val = 6;
        Node ans = removeElements(head, val);
        PrintUtl.print(ans);
    }

    public static Node removeElements(Node head, int val) {
        // we will create a dummy node and assign that to the prev
        Node dummyHead = new Node();
        Node prev = dummyHead;
        // traversing the linked list
        while (null != head) {
            Node next = head.next;
            head.next = null; // breaking the link
            if (head.val != val) {
                prev.next = head;
                prev = head;
            }
            // going to the next
            head = next;
        }
        return dummyHead.next;
    }


    // brute force approach
    // we will use a list, if the node val is not equal to val then we will add it to the list
    private static void type1() {

    }
}
