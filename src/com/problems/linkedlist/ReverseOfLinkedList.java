package com.problems.linkedlist;

import com.ds.linkedlist.Node;

import java.util.Stack;

import static com.util.PrintUtl.print;

/*
 * 
 * problem links :
 * https://leetcode.com/problems/reverse-linked-list/description/
 * https://neetcode.io/problems/reverse-a-linked-list
 * https://www.naukri.com/code360/problems/799897
 * https://www.naukri.com/code360/problems/reverse-linked-list_920513
 *
 * Solution link :
 * https://www.youtube.com/watch?v=D2vI2DNJGd8
 * https://www.youtube.com/watch?v=iRtLEoL-r-g
 * https://www.youtube.com/watch?v=G0_I-ZF0S38
 *
 * https://takeuforward.org/data-structure/reverse-a-linked-list/
 * https://neetcode.io/solutions/reverse-linked-list
 * */
public class ReverseOfLinkedList {
	public static void main(String[] args) {
		// using array or stack would be very lame
		type1();
		type2();

		type3(); // in place reversal iterative approach
		type4(); // in place reversal recursive approach
	}


	// todo we can also discuss this in the interview
	// this is a recursive approach
	// where we store the recursion stack to store the pointer
	// after each recursion we reverse the pointer direction
	private static void type4() {
		Node head = new Node(10, 6, 9, 15, 30);
		print(head);
		head = reverseLinkedList(head);
		print(head);
	}

	public static Node reverseLinkedList(Node head) {
		// If the linked list is empty or has only one node, return the head as it is already reversed.
		if (head == null || head.next == null) return head;
		// Reverse the linked list starting from the next node (head.next).
		// so we will break the link between current and next and save the next node
		Node next = head.next;  // Save a reference to the node following the current 'head' node.
		head.next = null; // Break the link from the current 'head' node to the 'front' node to avoid cycles.
		Node newHead = reverseLinkedList(next);
		// now the [next..last] is reversed, so now we can reverse the pointer of [head->next]
		next.next = head;
		return newHead; // Return the 'newHead,' which is the new head of the reversed linked list.
	}

	// TODO explain this in the interview In place reverse without extra space
	private static void type3() {
		Node head = new Node(10, 6, 9, 15, 30);
		print(head);
		Node ans = reverseList3(head);
		print(ans);

	}

	private static Node reverseList3(Node head) {
		// new head is the new head, at first it is assigned to null
		// next pointer is to store the next pointer until we do the intermediate
		// operation
		// while(null!=head){next = head.next;head = next;}
		// the upper code snippet will traverse the linkedlist
		// newHead actually keeps track of the previous element
		// at first we are assigning next element to next pointer otherwise we will
		// lose the next pointer, then we are attaching the next pointer to previous
		// here we are breaking the forward link and establishes a reverse link
		// then we are assigning current node to previous, which will help in the next iteration
		Node prev = null, node = head;
		while (null != node) {
			Node next = node.next;
			node.next = prev; // attaching the pointer to the previous element
			prev = node; // assigning the previous pointer to current
			node = next;
		}
		return prev;
	}

	// todo We will use a stack to store the pointer's data in reverse
	// Create a temporary pointer to traverse the linked list
	// Create a stack to temporarily store the data values
	// Step 1: Push the values of the linked list onto the stack
	// Push the current node's data onto the stack
	// Move to the next node in the linked list
	// Reset the temporary pointer to the head of the linked list
	// Step 2: Pop values from the stack
	// and update the linked list
	// Set the current node's data to the value at the top of the stack
	// Move to the next node in the linked list
	// Return the new head of the reversed linked list
	private static void type2() {
		Node head = new Node(10, 6, 9, 15, 30);
		print(head);
		Node ans = reverseList1(head);
		print(ans);
	}

	private static Node reverseList1(Node head) {
		Node node = head;
		// storing all the nodes into the stack
		Stack<Integer> stack = new Stack<>();
		while (node != null) {
			stack.push(node.data);
			node = node.next;
		}
		// reading from the stack
		node = head;
		while (node != null) {
			node.data = stack.pop();
			node = node.next;
		}
		return head;
	}

	// Brute force approach
	// we will store the pointers in an array
	// then we will attach the pointer links in reverse
	private static void type1() {

	}
}
