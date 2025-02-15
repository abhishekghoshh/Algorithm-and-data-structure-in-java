package com.problems.linkedlist;

import com.ds.linkedlist.Node;

import java.util.HashSet;
import java.util.Set;

import static com.util.PrintUtl.print;

/*
 * 
 * problem links :
 * https://leetcode.com/problems/intersection-of-two-linked-lists/description/
 * https://www.naukri.com/code360/problems/630457
 *
 * Solution link :
 * https://www.youtube.com/watch?v=0DYoPz2Tpt4
 * https://www.youtube.com/watch?v=u4FWXfgS8jw&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=34
 * https://www.youtube.com/watch?v=D0X0BONOQhI
 *
 * https://takeuforward.org/data-structure/find-intersection-of-two-linked-lists/
 * https://neetcode.io/solutions/intersection-of-two-linked-lists
 * */
public class IntersectionOfTwoLinkedList {
	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
	}

	// todo better optimized approach
	//  time complexity O(2 * max(n1, n2)))
	//  space complexity O(1)
	//  if l1 is m1->d and l2 is m2->d where d is the common portion
	//  then (l1->l2) will be m1->d->m2->d and (l2->l1) will be (m2->d->m1->d)
	//  now if we use 2 pointer h1 and h2 and assign to thr start of (l1->l2) and (l2->l1)
	//  and traverse it then after some point it will go to the common potion
	//  we need not to create (l1->l2) and (l2->l1), we can do little smart
	//  we will start with h1=l1 and h2=l2, once h1.next is null then we will assign it to l2 and h2.next is null
	//  we will assign it to l1, then ultimate h1 will be l1l2 and h2 will be l2l1
	private static void type4() {
		Node common = new Node(15, 30);
		Node headA = new Node(10, 6, 9).next(common);
		Node headB = new Node(10, 11).next(common);
		Node point = getIntersectionNode4(headA, headB);
		print(point);
	}

	private static Node getIntersectionNode4(Node headA, Node headB) {
		// let say headA = (a + h) and headB = (b + h)
		Node h1 = headA, h2 = headB;
		// h1 will become (h1 + h2) h2 will become (h2 + h1)
		// so let's say if there is no common point then after (n1+n2) both h1 and h2 will go to null and loop will terminate.
		// if there is some common point then h1 will become (a + h + b + h) and h2 will become (b + h + a + h)
		// still, the loop will terminate when it comes to last h
		while (h1 != h2) {
			h1 = null != h1 ? h1.next : headB;
			h2 = null != h2 ? h2.next : headA;
		}
		// if there is no intersection point, then it h1 will be null
		return h1;
	}

	// TODO explain this in the interview
	// very simple approach
	// optimized approach
	// two linked list having common point
	// time complexity O(n)
	// space complexity O(1)
	private static void type3() {
		Node common = new Node(15, 30);
		Node headA = new Node(10, 6, 9).next(common);
		Node headB = new Node(10, 11).next(common);
		Node point = getIntersectionNode3(headA, headB);
		print(point);
	}

	private static Node getIntersectionNode3(Node headA, Node headB) {
		int n1 = 0, n2 = 0;
		Node h1 = headA, h2 = headB;
		// first we count n1
		while (null != h1) {
			n1++;
			h1 = h1.next;
		}
		// then we will count n2
		while (null != h2) {
			n2++;
			h2 = h2.next;
		}
		// todo we will try to make the longer list equal to the shorter list
		// then we will move (n1 - n2) for the longer linked list, if (n1 > n2) then this while loop will execute
		while (n1 > n2) {
			n1--;
			headA = headA.next;
		}
		// if (n2 > n2) then this one will execute, the previous for loop will not execute
		while (n2 > n1) {
			n2--;
			headB = headB.next;
		}
		// now we are at the starting point for the both of the linked lists
		// at this point both linked lists have the same number of nodes, we will go till nodes are same
		while (headA != headB) {
			headA = headA.next;
			headB = headB.next;
		}
		return headA;
	}


	// todo hashing approach
	//  time complexity  O(n1*log(n1) + n2)
	//  space complexity O(n1)
	private static void type2() {
		Node common = new Node(15, 30);
		Node headA = new Node(10, 6, 9).next(common);
		Node headB = new Node(10, 11).next(common);
		Node ans = getIntersectionNode2(headA, headB);
		print(ans);
	}

	private static Node getIntersectionNode2(Node headA, Node headB) {
		Set<Node> set = new HashSet<>();
		// adding all the nodes in the set
		for (Node node1 = headA; null != node1; node1 = node1.next)
			set.add(node1);

		for (Node node2 = headB; null != node2; node2 = node2.next)
			if (set.contains(node2)) return node2;

		return null;
	}

	// todo brute force approach
	//  time complexity(O(n1*n2)
	//  space complexity (1)
	private static void type1() {
		Node common = new Node(15, 30);
		Node headA = new Node(10, 6, 9).next(common);
		Node headB = new Node(10, 11).next(common);
		Node ans = getIntersectionNode1(headA, headB);
		print(ans);
	}

	private static Node getIntersectionNode1(Node headA, Node headB) {
		// using n^2 loop
		for (Node node1 = headA; null != node1; node1 = node1.next) {
			for (Node node2 = headB; null != node2; node2 = node2.next)
				if (node1 == node2) return node1;
		}
		return null;
	}


}
