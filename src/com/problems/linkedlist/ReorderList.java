package com.problems.linkedlist;

import com.ds.linkedlist.Node;
import com.util.PrintUtl;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 *
 * problem links :
 * https://leetcode.com/problems/reorder-list/description/
 * https://neetcode.io/problems/reorder-linked-list
 *
 * Solution link :
 * https://www.youtube.com/watch?v=S5bfdUTrKLM
 *
 * https://takeuforward.org/data-structure/reorder-list/
 * https://neetcode.io/solutions/reorder-list
 * */
public class ReorderList {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }

    // todo most optimized approach, discuss it in the interview
    // first we will use a fast and slow pointer to go to the middle of the linked list
    // now we have 2 list [start, mid] [mid+1, end]
    // now if we reverse the 2nd list [end, mid+1]
    // we we will do a merge on both list, taking one from each linked list at a time
    private static void type4() {
        Node head = new Node(1, 2, 3, 4, 5, 6, 7);
        reorderList4(head);
        PrintUtl.print(head);
    }

    private static void reorderList4(Node head) {
        // if there is only one node then we do not need to check anything
        if (null == head.next) return;
        // we will find the middle node with this approach
        Node fast = head, slow = head;
        while (null != fast && null != fast.next) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node head2 = slow.next; // this will be head of the second part
        slow.next = null; // breaking the middle link
        head2 = reverse(head2);
        // now we will manipulate the pointers
        Node prev = new Node(); // our dummy head node
        while (head != null && head2 != null) {
            Node next1 = head.next, next2 = head2.next;
            head.next = head2.next = null;// breaking the links
            // adding links to head1 and head2 and updating prev
            prev.next = head;
            head.next = head2;
            prev = head2;
            // updating head1 and head2
            head = next1;
            head2 = next2;
        }
        // if node count is odd then there could be one extra node at head
        if (head != null) prev.next = head;
    }

    private static Node reverse(Node node) {
        Node prev = null;
        while (null != node) {
            Node next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }

    // todo do not try to discuss it in the interview
    // this is the same approach as previous, but here we have used recursion in place of stack
    private static void type3() {
        Node head = new Node(1, 2, 3, 4, 5, 6, 7);
        reorderList3(head);
        PrintUtl.print(head);
    }

    public static void reorderList3(Node head) {
        // if there is only one node then we do not need to check anything
        if (null == head.next) return;
        // we will find the middle node with this approach
        Node fast = head, slow = head;
        while (null != fast && null != fast.next) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // we will break the list and the middle-node.next to null
        Node next = slow.next;
        slow.next = null;
        // manipulate the pointers as per the question
        dfs(next, head);
    }

    // this recursion call is to mimic the stack behavior
    // we are carrying the head and when the next is null, then only we will return head
    // return value will be the current head
    // next.next = nextHead.next;
    // nextHead.next = next;
    // now next.next will be the new next head which we will return
    // which will be used by the previous recursion call
    private static Node dfs(Node next, Node head) {
        if (null == next) return head;
        Node nextHead = dfs(next.next, head);
        next.next = nextHead.next;
        nextHead.next = next;
        return next.next;
    }

    // todo this is better approach than the previous
    // we will use first and slow pointer to find the mid-node
    // once found, then we will store the last half nodes in a stack  because we need the nth node at first
    private static void type2() {
        Node head = new Node(1, 2, 3, 4, 5, 6, 7);
        reorderList2(head);
        PrintUtl.print(head);
    }

    public static void reorderList2(Node head) {
        // if there is only one node then we do not need to check anything
        if (null == head.next) return;
        // we will find the middle node with this approach
        Node fast = head, slow = head;
        while (null != fast && null != fast.next) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // we will break the list and the middle-node.next to null
        Node next = slow.next;
        slow.next = null;
        // we will save the last half-nodes in stack
        Stack<Node> stack = new Stack<>();
        while (next != null) {
            stack.push(next);
            next = next.next;
        }
        // now we will pop from the stack and manipulate the pointers as per the question
        Node node = head;
        while (!stack.isEmpty()) {
            next = stack.pop();
            next.next = node.next;
            node.next = next;
            node = next.next;
        }
    }

    // brute force approach
    // store all the nodes in a list then operate from that list
    // once the nodes are saved then we will manipulate the nodes by indices
    private static void type1() {
        Node head = new Node(1, 2, 3, 4, 5, 6, 7);
        reorderList1(head);
        PrintUtl.print(head);
    }

    public static void reorderList1(Node head) {
        // if there is only one node then we do not need to check anything
        if (null == head.next) return;
        // we will store the nodes in the array list
        List<Node> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            Node next = head.next;
            head.next = null; // breaking the link
            head = next;
        }
        // find we will set the next node of the mid to null
        int i = 0, j = list.size() - 1;
        // now we will manipulate the pointers as per the question
        Node prev = new Node();
        // now we will use two pointer and traverse the list
        while (i < j) {
            Node node1 = list.get(i++), node2 = list.get(j--);
            prev.next = node1;
            node1.next = node2;
            prev = node2;
        }
        // if i==j then node count is odd, so there will be one node that was not added in the prev loop
        // we will add it here
        if (i == j) prev.next = list.get(i);
    }

}
