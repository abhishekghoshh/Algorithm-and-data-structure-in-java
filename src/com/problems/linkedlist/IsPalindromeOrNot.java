package com.problems.linkedlist;

import com.ds.linkedlist.Node;

import java.util.ArrayList;
import java.util.List;

/*
 * 
 * problem links :
 * https://leetcode.com/problems/palindrome-linked-list/description/
 * https://www.naukri.com/code360/problems/799352
 * https://www.naukri.com/code360/problems/check-if-linked-list-is-palindrome_985248
 *
 * Solution link :
 * https://www.youtube.com/watch?v=lRY_G-u_8jk
 * https://www.youtube.com/watch?v=-DtNInqFUXs
 * https://www.youtube.com/watch?v=yOzXms1J6Nk
 *
 * https://takeuforward.org/data-structure/check-if-given-linked-list-is-plaindrome/
 * https://neetcode.io/solutions/palindrome-linked-list
 * */
public class IsPalindromeOrNot {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// todo most optimized approach
	//  we will first go to the middle of the linked list
	//  now we can divide the linked list into (start,mid) and (mid,last)
	//  but there is a problem we can not go from last -> mid
	//  we can reverse the 2nd part so the lost will be like [last,mid] and [start,mid]
	//  now we will use 2 pointer node1 and node2 and traverse till null
	private static void type3() {
		Node head = new Node(1, 2, 3, 4, 3, 2, 1);
		boolean isPalindrome = isPalindrome3(head);
		System.out.println("list is palindrome " + isPalindrome);
	}

	private static boolean isPalindrome3(Node head) {
		Node slow = head;
		Node fast = head;
		// first, we will find the middle point
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		// slow is the middle pointer or previous than a middle node
		// if it has 2n+1 nodes
		// we will reverse from the slow
		slow = reverse(slow);
		// we will again assign the head to fast
		fast = head;
		// now we will traverse both of the pointers
		while (slow != null) {
			if (slow.data != fast.data) return false;
			slow = slow.next;
			fast = fast.next;
		}
		return true;
	}

	// todo optimized approach
	// fist we will find the middle O(n/2)
	// reverse the remaining list O(n/2)
	// check for equality for list[0,n/2) and list[n/2,n) O(n/2)
	// again reverse the last half list O(n/2)
	// total time complexity O(2n)
	// space complexity O(1)
	private static void type2() {
		Node head = new Node(1, 2, 3, 4, 3, 2, 1);
		boolean isPalindrome = isPalindrome2(head);
		System.out.println("list is palindrome " + isPalindrome);
	}

	private static boolean isPalindrome2(Node head) {
		int n = 0;
		Node fast = head, slow = head, right, left, rightCopy;
		while (null != fast && null != fast.next) {
			n += 2;
			slow = slow.next;
			fast = fast.next.next;
			// fast is standing on last element number of nodes are odd
			if (null != fast && null == fast.next) n++;
		}
		// head == slow will occur when only one node is in a linked list
		// as loop didn't get executed so, slow will be the head pointer
		if (head != slow) {
			// adjust the right list start
			// if size is odd then slow will point it exactly the middle of the node.
			// so we have to move it to next
			// if size is even then it will be at the starting of the 2nd half
			if (n % 2 == 1) slow = slow.next;
			// reverse the remaining right
			rightCopy = right = reverse(slow);
			left = head;
			// check while the right list is not null or any mismatch
			while (null != right) {
				if (right.data != left.data) return false;
				right = right.next;
				left = left.next;
			}
			// again reverse the right part
			reverse(rightCopy);
		}
		return true;
	}

	private static Node reverse(Node head) {
		Node curr = head, prev = null, next;
		while (null != curr) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;
	}

	// brute force approach
	// time complexity O(2n)
	// space complexity O(n)
	private static void type1() {
		Node head = new Node(1, 2, 3, 3, 2, 1);
		boolean isPalindrome = isPalindrome1(head);
		System.out.println("list is palindrome " + isPalindrome);
	}

	private static boolean isPalindrome1(Node head) {
		// save the list data into a arraylist
		List<Node> list = new ArrayList<>();
		while (null != head) {
			list.add(head);
			head = head.next;
		}
		// then check that arraylist is a palindrome or not
		int left = 0, right = list.size() - 1;
		while (left < right) {
			// if the left and right are not same then we will return false
			if (list.get(left).val != list.get(right).val)
				return false;
			left++;
			right--;
		}
		return true;
	}

}
