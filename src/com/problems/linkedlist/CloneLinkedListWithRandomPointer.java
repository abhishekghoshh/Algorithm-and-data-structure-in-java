package com.problems.linkedlist;

import com.ds.linkedlist.Node;

import java.util.HashMap;
import java.util.Map;

import static com.util.PrintUtl.print;

/*
 * 
 * problem links :
 * https://leetcode.com/problems/copy-list-with-random-pointer/description/
 * https://neetcode.io/problems/copy-linked-list-with-random-pointer
 * https://www.naukri.com/code360/problems/873376
 * https://www.naukri.com/code360/problems/clone-a-linked-list-with-random-pointers_983604
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=q570bKdrnlw
 * https://www.youtube.com/watch?v=VNf6VynfpdM
 * https://www.youtube.com/watch?v=5Y2EiZST97Y
 *
 * https://takeuforward.org/data-structure/clone-linked-list-with-random-and-next-pointer/
 * https://neetcode.io/solutions/copy-list-with-random-pointer
 * */
public class CloneLinkedListWithRandomPointer {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// optimized approach
	// time complexity o(3n)
	// space complexity O(1)
	// we will place the new duplicate pointers in between of the exiting linked
	// list nodes in the first loop
	// on the second loop we will assign random pointers
	// on the third loop we will remove the duplicate nodes
	private static void type2() {
		Node head = buildRandom();
		Node copy = copyRandomList2(head);
		print(head);
		print(copy);
	}

	public static Node copyRandomList2(Node head) {
		Node node = head;
		Node copy, next;
		// duplicate nodes will be made
		// Add duplicate node in actual list node
		while (null != node) {
			next = node.next;
			// creating duplicate node
			copy = new Node(node.data);
			// now we will place the copy node to the next of the current node
			node.next = copy;
			copy.next = next;
			// // goes to next element of the actual list
			node = next;

		}
		node = head;
		// So we have created a kind of map where actualNode.next = copyNode
		// we will use this to assign the random pointers
		// at this point list size is 2n, so we don't have to check node.next
		// node.next.next will give us the next pointer of the actual list
		// random pointers will be allocated at this point
		while (null != node) {
			copy = node.next;
			if (null != node.random)
				copy.random = node.random.next;
			node = node.next.next;
		}
		node = head;
		// we will create a dummy node for storing the duplicate nodes
		Node newHead = new Node(0);
		Node prev = newHead;
		// at this point also node size is 2n so, we will separate two list in this loop
		while (null != node) {
			copy = node.next;
			// pointing in between duplicate node to duplicate list's next
			// previous moves to current duplicate node
			prev.next = copy;
			prev = prev.next;
			// at this point duplicate node is pointing to next element of actual list,
			// so we will assign actual list's next pointer to duplicate's next
			// so actual list will restore again
			node.next = copy.next;
			// goes to next element of the actual list
			node = node.next;
		}
		// as there was a dummy node at first, so we will remove it
		return newHead.next;
	}

	// brute force approach
	// first we will copy the entire list and along the way we will store (old-list-node, copy-list-node) into a map
	// after creating the list we will again traverse check if the old list node has any random node or not
	// if yes then we will get the copy list node from the map and assign it to the random node of current copy-list-node
	// time complexity O(2n)
	// space complexity O(n)
	private static void type1() {
		Node head = buildRandom();
		Node copy = copyRandomList1(head);
		print(head);
		print(copy);
	}

	public static Node copyRandomList1(Node head) {
		Node node1 = head;
		// creating a dummy node for head at last we will remove it
		Node newHead = new Node(0);
		Node prev = newHead;
		// we will create a pointer of old list node -> new list node
		Map<Node, Node> map = new HashMap<>();
		while (null != node1) {
			// creating a node
			Node node2 = new Node(node1.data);
			// creating the mapping
			map.put(node1, node2);
			// we are moving the copy list
			prev.next = node2;
			prev = prev.next;
			// we are moving the actual list
			node1 = node1.next;
		}
		// as newHead has an extra dummy pointer
		node1 = head;
		Node node2 = newHead.next;
		// now we will traverse the list and attach the random pointer
		while (null != node2) {
			if (null != node1.random)
				node2.random = map.get(node1.random);
			node1 = node1.next;
			node2 = node2.next;
		}
		// as there was a dummy node at first, so we will remove it
		return newHead.next;
	}

	private static Node buildRandom() {
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		Node node5 = new Node(5);

		node1.random = node3;
		node2.random = node1;
		node3.random = null;
		node4.random = node2;
		node5.random = node4;

		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		return node1;
	}

}
