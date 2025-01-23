package com.problems.linkedlist;

import com.ds.linkedlist.Node;

import static com.util.PrintUtl.print;

/*
 * 
 * problem links :
 * https://leetcode.com/problems/delete-node-in-a-linked-list/
 * https://www.naukri.com/code360/problems/1105578
 * https://www.naukri.com/code360/problems/delete-node-of-linked-list_8160463
 *
 * Solution link
 * https://www.youtube.com/watch?v=icnp4FJdZ_c&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=32
 *
 * https://takeuforward.org/data-structure/delete-given-node-in-a-linked-list-o1-approach/
 * */

public class DeleteNodeInALinkedList {

	// You are given the node
	// to be deleted node.You will not be given access to the first node of head.
	// All the values of the linked list are unique, and it is guaranteed
	// that the given node is not the last node in the linked list.
	public static void main(String[] args) {
		type1();
	}

	// Time complexity O(1)
	// space complexity O(1)
	private static void type1() {
		// You will not be given access to the head of the list,
		// instead you will be given access to the node to be deleted directly.
		// It is guaranteed that the node to be deleted is not a tail node in the list.
		Node node = new Node(5);
		Node head = new Node(1, 2, 3, 4)
				.next(node)
				.next(new Node(6, 7, 8, 9));
		print(head);
		node.data = node.next.data;
		node.next = node.next.next;
		print(head);
	}
}
