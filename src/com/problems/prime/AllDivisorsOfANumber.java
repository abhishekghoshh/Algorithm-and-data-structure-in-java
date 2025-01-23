package com.problems.prime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Problem link :
 * https://practice.geeksforgeeks.org/problems/number-of-factors1435/1
 * https://www.codingninjas.com/studio/problems/print-all-divisors-of-a-number_1164188
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=1xNbjMdbjug&t=1s
 * 
 * https://takeuforward.org/data-structure/print-all-divisors-of-a-given-number/
 */
public class AllDivisorsOfANumber {

	public static void main(String[] args) {
		type1();
		type2();
		type2_();
		type3();
	}

	private static class Node {
		int value;
		Node next;

		public Node(int value) {
			this.value = value;
			this.next = null;
		}

		public Node add(int i) {
			Node node = new Node(i);
			node.next = this.next;
			this.next = node;
			return node;
		}
	}

	// it will take sqrt(n) time complexity
	private static void type3() {
		int n = 120;
		Node head = new Node(-1);
		Node copy = head;
		for (int i = 1; i * i <= n; i++) {
			if (n % i == 0) {
				head = head.add(i);
				if (n / i != i) head.add(n / i);
			}
		}
		head = copy.next;
		while (null != head) {
			System.out.print(head.value + " ");
			head = head.next;
		}
		System.out.println();
	}

	private static void type2_() {
		int n = 120;
		int count = 0;
		for (int i = 1; i * i <= n; i++) {
			if (n % i == 0) {
				count++;
				if (n / i != i) count++;
			}
		}
		System.out.println(count);
	}

	// it will take sqrt(n) time complexity
	private static void type2() {
		int n = 120;
		List<Integer> answer = new ArrayList<>();
		for (int i = 1; i * i <= n; i++) {
			if (n % i == 0) {
				answer.add(i);
				if (n / i != i) answer.add(n / i);
			}
		}
		Collections.sort(answer);
		System.out.println(answer);
	}

	// it will take O(n) time complexity
	private static void type1() {
		int n = 120;
		List<Integer> answer = new ArrayList<>();
		for (int i = 1; i <= n; i++)
			if (n % i == 0) answer.add(i);
		System.out.println(answer);
	}
}
