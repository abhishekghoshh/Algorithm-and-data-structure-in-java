package com.problems.linkedlist;

import com.ds.linkedlist.Node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import static com.util.PrintUtl.print;

/*
 *
 * problem links:
 * https://www.codingninjas.com/studio/problems/flatten-a-linked-list_1112655
 * https://www.codingninjas.com/codestudio/problems/1112655
 * https://www.geeksforgeeks.org/problems/flattening-a-linked-list/1
 *
 * Solution video:
 * https://www.youtube.com/watch?v=ykelywHJWLg
 * https://www.youtube.com/watch?v=ysytSSXpAI0&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=39
 *
 * https://takeuforward.org/data-structure/flattening-a-linked-list/
 * https://www.geeksforgeeks.org/flattening-a-linked-list/
 * */
public class FlattenOfALinkedList {

	//Given a Linked List of size N, where every node represents a sub-linked-list and contains two pointers:
	//(i) a next pointer to the next node,
	//(ii) a bottom pointer to a linked list where this node is head.
	//Each of the sub-linked-list is in sorted order.
	//Flatten the Link List such that all the nodes appear in a single level while maintaining the sorted order.
	//Note: The flattened list will be printed using the bottom pointer instead of the next pointer.
	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
	}

	// Same as previous just with little optimization
	// using priority queue
	// same approach used in merge K sorted list into one list
	// time complexity O(n*m*log(n))
	// space complexity O(n) for priority queue
	private static void type4() {
		Node head = new Node(1).bottom(7, 8, 30);
		head.chain(new Node(19).bottom(22, 50))
				.chain(new Node(28).bottom(35, 40, 45));
		print(head);
		head = flattenLinkedList4(head);
		print(head);
	}

	private static Node flattenLinkedList4(Node head) {
		PriorityQueue<Node> heap = new PriorityQueue<>(Comparator.comparingInt(node -> node.data));
		Node node = head, next, lowest, currentLowest;
		while (null != node) {
			heap.offer(node);
			next = node.next;
			node.next = null;
			node = next;
		}
		head = new Node(0);
		node = head;
		while (!heap.isEmpty()) {
			lowest = heap.poll();
			if (!heap.isEmpty()) {
				currentLowest = heap.peek();
				while (null != lowest && lowest.data <= currentLowest.data) {
					node.bottom = lowest;
					lowest = lowest.bottom;
					node = node.bottom;
				}
				if (null != lowest) heap.offer(lowest);
			} else {
				while (null != lowest) {
					node.bottom = lowest;
					lowest = lowest.bottom;
					node = node.bottom;
				}
			}
		}
		return head.bottom;
	}

	// using priority queue
	// same approach used in merge K sorted list into one list
	// time complexity O(n*m*log(n))
	// space complexity O(n) for priority queue
	private static void type3() {
		Node head = new Node(1).bottom(7, 8, 30);
		head.chain(new Node(19).bottom(22, 50))
				.chain(new Node(28).bottom(35, 40, 45));

		print(head);
		head = flattenLinkedList3(head);
		print(head);
	}

	private static Node flattenLinkedList3(Node head) {
		PriorityQueue<Node> heap = new PriorityQueue<>(Comparator.comparingInt(node -> node.data));
		Node node = head, next;
		while (null != node) {
			heap.offer(node);
			next = node.next;
			node.next = null;
			node = next;
		}
		head = new Node(-1);
		Node prev = head;
		while (!heap.isEmpty()) {
			node = heap.poll();
			prev.bottom = node;
			prev = prev.bottom;
			if (node.bottom != null) heap.offer(node.bottom);
		}
		return head.bottom;
	}


	// merge approach
	// recursively merge last and second last then merge it to previous and the
	// process goes on
	// let's say there is n right nodes and on each m bottom nodes
	// total time complexity O(n*n*m)
	// space complexity O(1)
	private static void type2() {
		Node head = new Node(1).bottom(7, 8, 30);
		head.chain(new Node(19).bottom(22, 50))
				.chain(new Node(28).bottom(35, 40, 45));
		print(head);
		head = flatten(head);
		print(head);
	}

	private static Node flatten(Node head) {
		if (null == head || null == head.next) return head;
		head.next = flatten(head.next);
		head = merge(head, head.next);
		// at this point node1 and node2 are merged in l1's bottom
		// but there is a link between node1.next and node2
		// so, after merge operation we are breaking the next link
		head.next = null;
		return head;
	}

	// merge operation
	private static Node merge(Node node1, Node node2) {
		// as node1 and node2 sorted order and the linked list it also sorted
		// so node1.data < node2.data
		// first pointer will always be node1's
		Node dummy = new Node(0);
		Node prev = dummy;
		while (null != node1 && null != node2) {
			if (node1.data <= node2.data) {
				prev.bottom = node1;
				prev = node1;
				node1 = node1.bottom;
			} else {
				prev.bottom = node2;
				prev = node2;
				node2 = node2.bottom;
			}
		}
		prev.bottom = (null != node1) ? node1 : node2;
		return dummy.bottom;
	}

	// brute force approach
	// let's say there is n right nodes and on each m bottom nodes
	// O(n*m) to put it in array
	// O((n*m)log(n*m)) to sort the array
	// O(n*m) to create new linked list
	// total time complexity O(2*m*n)+O(m*n*log(m*n))
	// space complexity O(m*n) to store it in array
	private static void type1() {
		Node head = new Node(1).bottom(7, 8, 30);
		head.chain(new Node(19).bottom(22, 50))
				.chain(new Node(28).bottom(35, 40, 45));
		print(head);
		head = flattenLinkedList1(head);
		print(head);
	}

	public static Node flattenLinkedList1(Node head) {
		Node curr = head, next, bottom;
		List<Node> list = new ArrayList<>();
		while (null != curr) {
			bottom = curr;
			while (null != bottom) {
				list.add(bottom);
				bottom = bottom.bottom;
			}
			next = curr.next;
			curr.next = null;
			curr = next;
		}
		list.sort(Comparator.comparingInt(node -> node.data));
		head = new Node(0);
		curr = head;
		for (Node node : list) {
			curr.bottom = node;
			curr = node;
		}
		return head.bottom;
	}

}
