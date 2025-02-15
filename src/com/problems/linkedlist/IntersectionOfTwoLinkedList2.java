package com.problems.linkedlist;

import com.ds.linkedlist.Node;

import static com.util.PrintUtl.print;

/*
 *
 * problem links :
 *
 * Solution link :
 *
 * */
public class IntersectionOfTwoLinkedList2 {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // note here two linked lists have the common data not the common pointer
    // time complexity O((n1+n2))+O(max((n1+n2)))
    // space complexity O(1)
    private static void type3() {
        Node headA = new Node(10, 6, 9, 15, 30);
        Node headB = new Node(10, 15, 30);
        Node point = getIntersectionNode1(headA, headB);
        print(point);
    }

    private static Node getIntersectionNode1(Node headA, Node headB) {
        Node head = null;
        int n1 = count(headA);
        int n2 = count(headB);
        while (n1 > n2) {
            headA = headA.next;
            n1--;
        }
        while (n2 > n1) {
            headB = headB.next;
            n2--;
        }
        while (null != headA && null != headB) {
            if (headA.data == headB.data) {
                if (head == null) head = headA;
            } else {
                head = null;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return head;
    }

    private static int count(Node head) {
        int n = 0;
        while (null != head) {
            head = head.next;
            n++;
        }
        return n;
    }

    // using recursion
    // first count the nodes then
    // traverse extra nodes for the longer list
    private static void type2() {

    }

    // brute force approach
    // store it in an array list then traverse it from the back
    private static void type1() {

    }
}
