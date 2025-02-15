package com.problems.linkedlist;

import com.ds.linkedlist.Node;

import static com.util.PrintUtl.print;

/*
 * 
 * problem links :
 * https://leetcode.com/problems/add-two-numbers/description/
 * https://www.naukri.com/code360/problems/add-two-numbers_1170520
 *
 * Solution link :
 * https://www.youtube.com/watch?v=XmRrGzR6udg
 * https://www.youtube.com/watch?v=LBVsXSMOIk4&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=33
 * https://www.youtube.com/watch?v=wgFPrzTjm7s
 *
 * https://takeuforward.org/data-structure/add-two-numbers-represented-as-linked-lists/
 * https://neetcode.io/solutions/add-two-numbers
 * */
public class AddTwoNumbers {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// time complexity o(max(m,n))
	// space complexity O(1)
	// in place summation
	// here we will modify the existing list
	// we will use l1 if it's not null else l2
	private static void type2() {
		Node l1 = new Node(9, 9, 9, 9, 9, 9, 9, 9);
		Node l2 = new Node(9, 9, 9, 9);
		Node head = addTwoNumbers2(l1, l2);
		print(head);
	}

	public static Node addTwoNumbers2(Node l1, Node l2) {
		int carry = 0, sum;
		// assigning head to l1 or l2
		Node head = null != l1 ? l1 : l2;
		// made a copy of head for our computation
		Node prev = head;
		while (null != l1 || null != l2) {
			sum = carry;
			sum += (null != l1) ? l1.data : 0;
			sum += (null != l2) ? l2.data : 0;
			if (null != l1) {
				// we are assuming that we will use l1 if not null
				l1.data = sum % 10;
				prev = l1;
			} else {
				// we will use l2 for computation
				l2.data = sum % 10;
				// l1 has exhausted then we need to point last of l1 to current node of l2
				// prev was point to the l1 so prev.next=l2 will work
				// after attaching now assign l2 to prev
				prev.next = l2;
				prev = l2;
			}
			carry = sum / 10;
			l1 = (null != l1) ? l1.next : null;
			l2 = (null != l2) ? l2.next : null;
		}
		// if there is any carry then we will append to the previous
		if (carry == 1) prev.next = new Node(carry);

		return head;
	}

	// time complexity o(max(m,n)+1)
	// space o(max(m,n)+1)
	// we will go left to right and also track the carry
	private static void type1() {
		Node l1 = new Node(2, 4, 3, 5);
		Node l2 = new Node(5, 6, 4);
		Node head = addTwoNumbers1(l1, l2);
		print(head);
	}

	public static Node addTwoNumbers1(Node l1, Node l2) {
		int carry = 0;
		// assigning a dummy pointer
		Node head = new Node(-1);
		// prev will pointing to head
		Node prev = head, curr;
		// loop will go until both are null or carry is 0
		while (null != l1 || null != l2 || carry != 0) {
			// sum and carry are calculated even if there is any null list
			int sum = carry;
			sum += (null != l1) ? l1.data : 0;
			sum += (null != l2) ? l2.data : 0;
			carry = sum / 10;
			// temporary creating node
			curr = new Node(sum % 10);
			// attaching the current pointer to the previous pointer
			// and then assigning the current pointer to previous
			prev.next = curr;
			prev = curr;
			// going to the next node if the list is not null
			l1 = (null != l1) ? l1.next : null;
			l2 = (null != l1) ? l1.next : null;
		}
		return head.next;
	}

}
