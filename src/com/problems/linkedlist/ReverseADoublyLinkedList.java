package com.problems.linkedlist;

import com.ds.linkedlist.DNode;

import java.util.Stack;

import static com.util.PrintUtl.print;

/*
 *
 * problem links :
 * https://www.codingninjas.com/studio/problems/reverse-a-doubly-linked-list_1116098
 *
 * Solution link :
 * https://www.youtube.com/watch?v=u3WUW2qe6ww&list=PLgUwDviBIf0rAuz8tVcM0AymmhTRsfaLU&index=5
 *
 * https://takeuforward.org/data-structure/reverse-a-doubly-linked-list/
 * */
public class ReverseADoublyLinkedList {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    private static void type3() {
        DNode head = new DNode(5, 8, 4, 9, 1);
        head = reverse3(head);
        print(head);
    }

    private static DNode reverse3(DNode head) {
        // if the head is null, or it is a single pointer,
        // then return the node
        if (head == null || head.next == null) return head;
        // Initialize a pointer to the previous node
        DNode prev = null, next;
        // Initialize a pointer to the current node
        DNode current = head;
        // Traverse the linked list and in place change
        while (current != null) {
            next = current.next;
            prev = current.prev;
            // This step reverses the link
            // Swap the previous and next pointers
            current.prev = next;
            current.next = prev;
            // Move to the next node in the original list
            current = next;
        }
        // look closely, prev is pointing too previously to last node
        // so before swapping prev.next was start
        // but as we swapped
        // prev.prev became the start pointer
        return prev.prev;
    }

    // little optimize from the previous
    // This will use a stack to store all the datas in a reverse manner
    // then we will pop from the stack
    private static void type2() {
        DNode head = new DNode(5, 8, 4, 9, 1);
        head = reverse2(head);
        print(head);
    }

    private static DNode reverse2(DNode head) {
        // if the head is null, or it is a single pointer,
        // then return the node
        if (head == null || head.next == null) return head;
        Stack<Integer> stack = new Stack<>();
        DNode node = head;
        while (null != node) {
            stack.push(node.data);
            node = node.next;
        }
        node = head;
        // we will not rearrange the links,
        // we will just pop interchange the datas
        while (node != null && !stack.isEmpty()) {
            node.data = stack.pop();
            node = node.next;
        }
        return head;
    }


    // brute force approach
    // storing all the nodes to an array
    private static void type1() {
        DNode head = new DNode(5, 8, 4, 9, 1);
        head = reverse1(head);
        print(head);
    }

    private static DNode reverse1(DNode head) {
        // if the head is null, or it is a single pointer,
        // then return the node
        if (head == null || head.next == null) return head;
        int n = 0;
        DNode node = head;
        while (node != null) {
            n++;
            node = node.next;
        }
        // we will store all the nodes in the array
        // then from the array we will try to reverse it
        DNode[] arr = new DNode[n];
        node = head;
        for (int i = 0; i < n && node != null; i++) {
            arr[i] = node;
            node = node.next;
        }
        // now we will change the pointers of the array
        for (int i = n - 1; i >= 0; i--) {
            DNode curr = arr[i];
            DNode next = i >= 1 ? arr[i - 1] : null;
            curr.next = next;
            if (null != next) next.prev = curr;
        }

        return arr[n - 1];
    }
}
