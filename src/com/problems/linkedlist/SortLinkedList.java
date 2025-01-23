package com.problems.linkedlist;

import com.ds.linkedlist.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.util.PrintUtl.print;

/*
 * problem links :
 * https://leetcode.com/problems/sort-list/description/
 * https://www.codingninjas.com/studio/problems/sort-linked-list_625193
 *
 * Solution link :
 * https://www.youtube.com/watch?v=8ocB7a_c-Cc
 * https://www.youtube.com/watch?v=TGveA1oFhrc
 *
 * https://neetcode.io/solutions/sort-list
 * */
public class SortLinkedList {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }

    // todo do not discuss it in the interview
    // TODO study this quick sort one more time
    // using quick sort
    private static void type4() {
        Node head = new Node(1, 3, 4, 7, 1, 2, 6);
        head = quickSort(head, null);
        print(head);
    }

    public static Node quickSort(Node head, Node tail) {
        if (head == null || head.next == null || head == tail)
            return head;
        boolean sorted = true;
        Node pivot = head;
        Node right = pivot;
        Node curr = pivot.next;
        while (curr != null && curr != tail) {
            Node front = curr.next;
            if (curr.data < pivot.data) {
                sorted = false;
                right.next = front;
                curr.next = head;
                head = curr;
            } else {
                //if it is greater that pivot but less than right(greater or equal to pivot)
                if (curr.data < right.data) sorted = false;
                right = curr;
            }
            curr = front;
        }
        if (sorted) return head;
        pivot.next = quickSort(pivot.next, tail);
        return quickSort(head, pivot);
    }


    // todo optimized approach using merge sort
    //  discuss it in the interview
    //  using a merge sort idea
    private static void type3() {
        Node head = new Node(1, 3, 4, 7, 1, 2, 6);
        head = mergeSort(head);
        print(head);
    }

    public static Node mergeSort(Node head) {
        if (head == null || head.next == null)
            return head;
        // as this is not an array, so we have to find out the middle pointer by traversing the entire array.
        // but we could do one thing we could use the tortoise approach of finding the middle node
        Node mid = mid(head);
        Node head2 = mid.next;
        mid.next = null;  // detaching the middle pointer
        // at this point, we have two lists one is head to mid another is mid.next ... last
        head = mergeSort(head);
        head2 = mergeSort(head2);
        return merge(head, head2);
    }

    public static Node mid(Node head) {
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node merge(Node list1, Node list2) {
        Node dummyHead = new Node(-1);
        Node prev = dummyHead;
        while (null != list1 && null != list2) {
            if (list1.val < list2.val) {
                prev.next = list1;
                list1 = list1.next;
            } else {
                prev.next = list2;
                list2 = list2.next;
            }
            prev = prev.next;
        }
        prev.next = (null != list1) ? list1 : list2;
        return dummyHead.next;
    }

    // optimized approach using insertion sort
    private static void type2() {
        Node head = new Node(1, 3, 4, 7, 1, 2, 6);
        head = insertionSortList(head);
        print(head);
    }

    private static Node insertionSortList(Node head) {
        Node newHead = new Node(Integer.MIN_VALUE);
        // here we will also store the last value of the sorted list.
        // if the current node value is greater than the last node of the sorted list,
        // then we can directly append to the last node and update the last node
        Node last = newHead;
        Node node = head, next;
        while (null != node) {
            next = node.next;
            // if the current node data value is greater than previous,
            // then we don't need to do anything
            // we will just attach it to the previous
            if (last.data <= node.data) {
                last.next = node;
                last = node;
            } else {
                // else we have to insert the node
                insert(node, newHead);
            }
            node = next;
        }
        last.next = null;
        return newHead.next;
    }

    private static void insert(Node node, Node head) {
        // head is the dummy node with Integer.MIN_VALUE
        // so any node whatever is the value will be added after this;
        // we will try to find the node after which the current node
        // should be added
        Node prev = head;
        // we all always check with the nxt value data
        // if next value data is also lesser than current node data
        // then we go to the next again, continue
        while (null != prev.next && prev.next.data < node.data)
            prev = prev.next;
        // so we have find out the position
        // node will be placed inside prev and prev.next
        node.next = prev.next;
        prev.next = node;
    }

    // brute force approach
    private static void type1() {
        Node head = new Node(1, 3, 4, 7, 1, 2, 6);
        Node ans = sortList1(head);
        print(ans);
    }

    private static Node sortList1(Node head) {
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
        return head;
    }
}
