package com.problems.linkedlist;

import com.ds.linkedlist.Node;

import java.util.ArrayList;
import java.util.List;

import static com.util.PrintUtl.print;

/*
 *
 * problem links :
 * https://leetcode.com/problems/reverse-linked-list-ii/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=RF_M9tX4Eag
 *
 * https://neetcode.io/solutions/reverse-linked-list-ii
 * */

public class ReverseOfLinkedList2 {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // in place reversal
    private static void type2() {
        Node head = new Node(1, 2, 3, 4, 5);
        int left = 2, right = 4;
        Node ans = reverseBetween(head, left, right);
        print(ans);
    }

    public static Node reverseBetween(Node head, int left, int right) {
        if (left == right) return head;
        // we have added one more node at the start to make it 1 index
        Node copyHead = new Node();
        copyHead.next = head;
        Node curr = copyHead, parent = null;
        // we will go till left index
        for (int i = 1; i <= left; left++) {
            parent = curr;
            curr = curr.next;
        }
        // now we will do the in place reversal
        Node last = curr; // we will preserve the current node as it will be the last node in the series
        Node next = null, prev = null; // from here we will do the reversal
        // todo study this or memorize this by any way
        for (int i = left; i <= right && curr != null; i++) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        // we will link the nodes now
        parent.next = prev; // prev is the right most element so leftParent will be connected to the prev
        last.next = next; // next is the next element of the right most element, so last will be connected to next
        return copyHead.next;
    }
    // using extra array for reversal
    // first we will put everything in the array then we will find the indices and reverse
    private static void type1() {
        Node head = new Node(1, 2, 3, 4, 5);
        int left = 2, right = 4;
        Node ans = reverseBetween1(head, left, right);
        print(ans);
    }

    private static Node reverseBetween1(Node head, int left, int right) {
        if (left == right) return head;
        List<Node> list = new ArrayList<>();
        // add a dummy node at the start of the array list as left and right is 1 indexed
        list.add(new Node());
        // adding all the items to the buffer list
        while (head != null) {
            list.add(head);
            Node next = head.next;
            head.next = null; // breaking the link
            head = next;
        }
        // reversing the nodes from [left,right]
        while (left < right) {
            Node node1 = list.get(left), node2 = list.get(right);
            list.set(right, node1);
            list.set(left, node2);
            left++;
            right--;
        }
        Node prev = list.get(0);
        int n = list.size();
        for (int i = 1; i < n; i++) {
            prev.next = list.get(i);
            prev = prev.next;
        }
        return list.get(0).next;
    }
}
