package com.problems.linkedlist;

import com.ds.linkedlist.Node;

import static com.util.PrintUtl.print;

/*
 * problem links :
 * https://leetcode.com/problems/sort-list/description/
 *
 * Solution link :
 *
 * */
public class LinkedListMergeSort {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        Node head = new Node(1, 3, 4, 7, 1, 2, 6);
        print(head);
        head = partition(head);
        print(head);
    }

    public static Node partition(Node head) {
        // using a merge sort idea
        if (head == null || head.next == null) return head;
        // as this is not an array, so we have to find out the
        // middle pointer by traversing the entire array.
        // but we could do one thing
        // we could use the tortoise approach of finding the middle node
        Node mid = findMid(head);
        Node head2 = mid.next;
        // detaching the middle pointer
        mid.next = null;
        // at this point, we have two lists
        // one is head to mid
        // another is mid.next ... last
        head = partition(head);
        head2 = partition(head2);
        return merge(head, head2);
    }

    public static Node findMid(Node head) {
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node merge(Node list1, Node list2) {
        Node head = new Node(-1);
        Node prev = head;
        while (null != list1 && null != list2) {
            if (list1.data < list2.data) {
                prev.next = list1;
                list1 = list1.next;
            } else {
                prev.next = list2;
                list2 = list2.next;
            }
            prev = prev.next;
        }
        prev.next = (null != list1) ? list1 : list2;
        return head.next;
    }
}
