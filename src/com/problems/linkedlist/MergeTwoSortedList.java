package com.problems.linkedlist;

import com.ds.linkedlist.Node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.util.PrintUtl.print;


/*
 * 
 * problem links :
 * https://leetcode.com/problems/merge-two-sorted-lists/description/
 * https://neetcode.io/problems/merge-two-sorted-linked-lists
 * https://www.naukri.com/code360/problems/80033
 *
 * Solution link :
 * https://www.youtube.com/watch?v=Xb4slcp1U38&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=30
 * https://www.youtube.com/watch?v=XIdigk956u0
 *
 * https://takeuforward.org/data-structure/merge-two-sorted-linked-lists/
 * https://neetcode.io/solutions/merge-two-sorted-lists
 * */
public class MergeTwoSortedList {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// optimized merge approach used in merge operation in merge sort
	// time complexity O(n)
	// space complexity O(1)
	// in place merge
	private static void type2() {
		Node list1 = new Node(1, 4, 5, 7);
		Node list2 = new Node(2, 3, 6, 8, 9);
		Node head = mergeTwoLists2(list1, list2);
		print(head);
	}

	// in place merge we will use a prev pointer then we will compare l1.data and l2.data
	public static Node mergeTwoLists2(Node list1, Node list2) {
		// we are assigning a dummy node to head to prevent null pointer exception
		Node dummyHead = new Node();
		Node prev = dummyHead;
		// we will preform merging till both list1 and list2 is non-null
		while (null != list1 && null != list2) {
			if (list1.data < list2.data) {
				prev.next = list1;
				list1 = list1.next;
			} else {
				prev.next = list2;
				list2 = list2.next;
			}
			prev = prev.next;
		}
		// at this point either list1 is null or list2 is null
		// as the lists are in sorted order, so we can just attach
		// the non-null list to the prev
		prev.next = (null != list1) ? list1 : list2;
		return dummyHead.next;
	}

	// TODO delete it later, it is kept only for the demonstration purpose
	public static Node mergeTwoLists2_Old(Node list1, Node list2) {
		Node head = null;
		if (null != list1 && null != list2) {
			Node headCopy = null, current = null;
			// assigning the head and copy of head
			if (list1.data < list2.data) {
				head = headCopy = list1;
				list1 = list1.next;
			} else {
				head = headCopy = list2;
				list2 = list2.next;
			}
			// we will merge here
			while (null != list1 && null != list2) {
				if (list1.data < list2.data) {
					current = list1;
					list1 = list1.next;
				} else {
					current = list2;
					list2 = list2.next;
				}
				headCopy.next = current;// changing the next pointer of the previous node
				headCopy = current;// assigning the current pointer to next loop
			}
			// at this point either l1 or l2 is null
			// so we can directly attach the remaining linkedlist it the head
			headCopy.next = null != list1 ? list1 : list2;
		} else if (null == list1) {
			head = list2;
		} else if (null == list2) {
			head = list1;
		}
		return head;
	}


	// brute force approach
	// putting all items in list O(n1+n2)
	// sorting the list O((n1+n2) * log(n1+n2))
	// creating the the linked list with all the items
	// time complexity O(n1+n2) + O((n1+n2) * log(n1+n2)) + O(n1+n2)
	// space complexity O(2*(n1+n2)) for list + linked list
	private static void type1() {
		Node list1 = new Node(1, 4, 5, 7);
		Node list2 = new Node(2, 3, 6, 8, 9);
		Node head = mergeTwoLists1(list1, list2);
		print(head);
	}

	private static Node mergeTwoLists1(Node list1, Node list2) {
		List<Node> list = new ArrayList<>();
		// adding list1 to the list
		while (null != list1) {
			list.add(list1);
			list1 = list1.next;
		}
		// adding list2 to the list
		while (null != list2) {
			list.add(list2);
			list2 = list2.next;
		}
		// sorting the list
		list.sort(Comparator.comparingInt(node -> node.data));
		Node dummyHead = new Node();
		Node prev = dummyHead;
		// connecting the nodes
		for (Node node : list) {
			prev.next = node;
			prev = node;
		}
		return dummyHead.next;
	}

}
