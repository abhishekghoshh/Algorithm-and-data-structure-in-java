package com.problems.linkedlist;

import com.ds.linkedlist.Node;

import java.util.HashSet;
import java.util.Set;

import static com.util.PrintUtl.print;

/*
 *
 * problem links :
 * https://leetcode.com/problems/linked-list-cycle-ii/
 * https://www.naukri.com/code360/problems/1112628
 *
 * Solution link :
 * https://www.youtube.com/watch?v=2Kd0KKmmHFc
 * https://www.youtube.com/watch?v=QfbOhn0WZ88
 *
 * https://takeuforward.org/data-structure/starting-point-of-loop-in-a-linked-list/
 * */
public class StartingNodeOfCycleInLinkedList {

    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // same as previous
    // here we will
    private static void type3() {
        Node head = new Node(1, 2, 3, 4, 5);
        Node node1 = new Node(6);
        Node node2 = new Node(7, 8, 9);
        Node.attach(head, node1, node2);
        print(head);
        node2.last(node1);

        head = detectCycle3(head);
        System.out.println("starting node : " + head.data);
    }

    public static Node detectCycle3(Node head) {
        Node start = head;
        Node slow = head;
        Node fast = head;
        while (null != fast && null != fast.next) {
            // no cycle is present early break to reduce extra computation
            if (fast.next.next == null) break;
            slow = slow.next;
            fast = fast.next.next;
            // we have found the collision
            if (slow == fast) {
                // again we will traverse from start,
                // and the slow pointer will also go as it is,
                // and we know that after x distance start and the slow pointer will collide
                while (start != slow) {
                    start = start.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }

    // enhanced tortoise method for finding linked list cycle approach
    // we will take two pointer slow and fast
    // and move them by one and two to find collision
    // let say slow and fast pointer moves c+ml+x and c+nl+x
    // where c is common distance, m and n is rotation taken before collision
    // l is the cycle length
    // x is collision distance from starting point of cycle
    // and let's say slow pointer goes d distance before collision
    // then fast pointer will be going 2d distance before collision
    // 2d-d = (c+nl+x) - (c+ml+x) => d = l*(n-m)
    // so d is multiple of cycle length
    // l*(n-m) = c+ml+x => c+x = l*(n-2m)
    // so c+x is also multiple of cycle length
    // that means after collision point if we go to x distance
    // then we will obviously get the starting of the cycle
    // which is also the duplicate number
    // time complexity of this more than O(n)
    // as it may rotate some cycles
    private static void type2() {
        Node head = new Node(1, 2, 3, 4, 5);
        Node node1 = new Node(6);
        Node node2 = new Node(7, 8, 9);
        Node.attach(head, node1, node2);
        print(head);
        node2.last(node1);

        head = detectCycle2(head);
        System.out.println("starting node : " + head.data);
    }

    public static Node detectCycle2(Node head) {
        Node slow = head;
        Node fast = head;
        while (null != fast && null != fast.next) {
            // no cycle is present early break to reduce extra computation
            if (fast.next.next == null) break;
            slow = slow.next;
            fast = fast.next.next;
            // we have found the collision
            if (slow == fast) break;
        }
        // again we will traverse from start,
        // and the slow pointer will also go as it is,
        // and we know that after x distance start and the slow pointer will collide
        Node start = head;
        while (start != slow) {
            start = start.next;
            slow = slow.next;
        }
        return start;
    }

    // brute force approach
    // time complexity o(n)
    // space complexity o(n)
    private static void type1() {
        Node head = new Node(1, 2, 3, 4, 5);
        Node node1 = new Node(6);
        Node node2 = new Node(7, 8, 9);
        Node.attach(head, node1, node2);
        print(head);
        node2.last(node1);

        head = detectCycle1(head);
        System.out.println("starting node : " + head.data);
    }

    public static Node detectCycle1(Node head) {
        Set<Node> set = new HashSet<>();
        while (null != head) {
            if (set.contains(head)) break;
            set.add(head);
            head = head.next;
        }
        return head;
    }

}
