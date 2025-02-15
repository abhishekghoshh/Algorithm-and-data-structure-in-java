package com.problems.linkedlist;

import com.ds.linkedlist.Node;

import java.util.ArrayList;
import java.util.List;

import static com.util.PrintUtl.print;

/*
 *
 * problem links :
 * https://leetcode.com/problems/rotate-list/description/
 * https://www.naukri.com/code360/problems/920454
 *
 * Solution link :
 * https://www.youtube.com/watch?v=uT7YI7XbTY8
 * https://www.youtube.com/watch?v=9VPm6nEbVPA&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=40
 * https://www.youtube.com/watch?v=UcGtPs2LE_c
 *
 * https://takeuforward.org/data-structure/rotate-a-linked-list/
 * https://neetcode.io/solutions/rotate-list
 * */
public class RotateLinkedListToRight {

    public static void main(String[] args) {
        type1();
        type2();
    }


    // TODO best approach discuss it in the interview
    // time complexity O(n)
    // space complexity O(1)
    // if the series is  1 2 3 4 5 and k = 2
    // then after two rotation it will become  4 5 1 2 3
    // see our our work is just cut the last k pointers and add it to head
    // so from the start we will go n-k nodes which is same as k nodes from the end
    private static void type2() {
        Node head = new Node(0,1,2);
        int k = 4;
        head = rotateRight2(head, k);
        print(head);
    }

    // for example 1,2,3,4,5 will be 4,5,1,2,3 after 2 rotation
    // if list is null, or it has only one element or k ==0 then we will just return
    // as we don't have to do anything
    // our job is to attach 5 to 1 then detach 3.next from 4 to null
    // then assign 4 to head
    // so our list will become like 1,2,3,4,5->>1
    // then it will become 1,2,3->>null 4,5->>1 and 4 is head now
    // our list will become 4,5,1,2,3
    // so first we will go to last pointer and along with it, we will count length
    // then we will attach last.next = head
    // now our list become cycle
    // we are at 5 and k=2, so we need to travel n-k distance
    // after traveling we will reach node 3
    // now we will assign head to 3.next which is 4
    // also breaks 3's next pointer
    public static Node rotateRight2(Node head, int k) {
        // if node count is 0 or 1 or k == 0 then there will be now change
        if (head == null || head.next == null || k == 0) return head;
        int n = count(head);
        // if k is multiple n then we after k shift it will again become original list
        if ((k % n) == 0) return head;
        // removing the extra iteration
        k = k % n;
        // k rotation meaning list will be starting n-k element, so we will be going to the prev of that node
        int k1 = n - k;
        Node node = head;
        for (int i = 1; i < k1; i++) {
            node = node.next;
        }
        Node newHead = node.next;
        node.next = null; // breaking the link
        // now we will go the last node of the 2nd list, because we need to attach last.next = head
        node = newHead;
        while (node.next != null) {
            node = node.next;
        }
        node.next = head;
        return newHead;
    }

    private static int count(Node head) {
        int n = 0;
        while (null != head) {
            n++;
            head = head.next;
        }
        return n;
    }

    // todo brute force approach
    //  time complexity O(n)
    //  store it in the array then rotate the array
    private static void type1() {
        Node head = new Node(1, 2, 3, 4, 5);
        int k = 2;
        Node ans = rotateRight1(head, k);
        print(ans);
    }

    public static Node rotateRight1(Node head, int k) {
        List<Node> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        // do the rest
        return head;
    }
}
