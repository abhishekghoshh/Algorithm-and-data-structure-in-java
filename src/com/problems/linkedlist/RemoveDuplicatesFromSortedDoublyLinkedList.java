package com.problems.linkedlist;

import com.ds.linkedlist.DNode;

import java.util.TreeSet;

import static com.util.PrintUtl.print;

/*
 * problem links :
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=YJKVTnOJXSY
 *
 *
 * */
public class RemoveDuplicatesFromSortedDoublyLinkedList {
    public static void main(String[] args) {
        type1();
        type2();
    }


    // todo optimized approach
    // we will do place
    // we will compare with previous if it is not the same then only we will add the link
    private static void type2() {
        DNode head = new DNode(1, 2, 2, 2, 3);
        DNode ans = uniqueSortedList2(head);
        print(ans);
    }

    private static DNode uniqueSortedList2(DNode head) {
        DNode dummyHead = new DNode(-1);
        DNode prev = dummyHead, node = head;
        while (null != node) {
            DNode next = node.next;
            // unlinking the next and prev
            node.next = null;
            next.prev = null;
            // if the prev data is not equal to the current data then we will add the current node
            if (prev.data != node.data) {
                prev.next = node;
                node.prev = prev;
                prev = node; // assigning the prev to the current
            }
            // going to the next node
            node = next;
        }
        // unlinking the dummy head
        if (dummyHead.next != null) {
            dummyHead.next.prev = null;
        }
        return dummyHead.next;
    }

    // todo brute force approach
    //  Using a tree set to store all the unique element in a sorted manner
    private static void type1() {
        DNode head = new DNode(1, 2, 2, 2, 3);
        DNode ans = uniqueSortedList1(head);
        print(ans);
    }

    private static DNode uniqueSortedList1(DNode head) {
        TreeSet<Integer> set = new TreeSet<>();
        DNode node = head;
        while (null != node) {
            set.add(node.data);
            node = node.next;
        }
        DNode dummyHead = new DNode(-1);
        DNode prev = dummyHead;
        while (!set.isEmpty()) {
            DNode curr = new DNode(set.pollFirst()); // poll first will give us the lowest element
            prev.next = curr; // setting the next link
            curr.prev = prev; // setting the prev link
            prev = curr;
        }
        // unlinking the dummy head
        if (dummyHead.next != null) {
            dummyHead.next.prev = null;
        }
        return dummyHead.next;
    }
}
