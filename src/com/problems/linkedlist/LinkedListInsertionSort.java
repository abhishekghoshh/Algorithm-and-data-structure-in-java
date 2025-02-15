package com.problems.linkedlist;

import com.ds.linkedlist.Node;

import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://leetcode.com/problems/insertion-sort-list/description/
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=Kk6mXAzqX3Y
 * https://www.youtube.com/watch?v=gAkRfdtDOaA
 * https://www.youtube.com/watch?v=gwW8U4exaYs
 *
 * https://neetcode.io/solutions/insertion-sort-list
 */
public class LinkedListInsertionSort {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// TODO explain the first type then optimize to the current type in the interview
	//  similar to previous type but with the little optimization
	//  here we will also store the last value of the sorted list.
	//  if the current node value is greater than the last node of the sorted list,
	//  then we can directly append to the last node and update the last node
	private static void type2() {
		Node head = new Node(-1, 5, 3, 4, 0, 5, 7, 2);
		Node newHead = insertionSortList2(head);
		print(newHead);
	}

	private static Node insertionSortList2(Node head) {
		Node newHead = new Node(Integer.MIN_VALUE); // a sorted list
		Node last = newHead;
		Node node = head;
		while (null != node) {
			Node next = node.next;
			// if the current node data value is greater than previous, then we don't need to do anything
			// we will just attach it to the last
			if (last.data <= node.data) {
				last.next = node;
				last = node;
				last.next = null; // breaking the link after node
			} else {
				// else we have to insert the node
				insert(newHead, node);
			}
			node = next;
		}
		return newHead.next;
	}

	private static void insert(Node start, Node node) {
		// head is the dummy node with Integer.MIN_VALUE so any node whatever is the value will be added after this;
		// we will try to find the node after which the current node should be added
		Node prev = start;
		while (start != null) {
			if (start.val > node.val) break;
			prev = start;
			start = start.next;
		}
		// attaching the links
		node.next = prev.next;
		prev.next = node;
	}

	// todo simple insertion approach
	//  first, we will create a dummy node with Integer.MIN_VALUE.
	//  we know all our nodes will be bigger than this,
	//  so this new head will become the sorted list.
	//  we will traverse the list, and then we will add the node into
	//  the sorted list to its proper position
	private static void type1() {
		Node head = new Node(-1, 5, 3, 4, 0, 5, 7, 2);
		Node newHead = insertionSortList1(head);
		print(newHead);
	}

	private static Node insertionSortList1(Node head) {
		Node newHead = new Node(Integer.MIN_VALUE); // a sorted list
		Node node = head;
		while (null != node) {
			Node next = node.next; // getting the next
			// from the start of the list we will check insert the node to its proper position
			Node start = newHead, prev = newHead;
			while (start != null) {
				if (start.val > node.val) break;
				prev = start;
				start = start.next;
			}
			node.next = prev.next; // attaching the links
			prev.next = node;
			// going to the next node
			node = next;
		}
		// return the list from the next of newHead
		return newHead.next;
	}


}
