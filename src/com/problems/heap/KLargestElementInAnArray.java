package com.problems.heap;

import java.util.ArrayList;
import java.util.PriorityQueue;

/*
 * Problem link :
 * https://practice.geeksforgeeks.org/problems/k-largest-elements3736/1
 * https://www.interviewbit.com/problems/k-largest-elements/
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=3DdP6Ef8YZM&list=PL_z_8CaSLPWdtY9W22VjnPxG30CXNZpI9&index=3
 * 
 * */
public class KLargestElementInAnArray {
	public static void main(String[] args) {
		type1();
		type2();
	}

	// using priority queue
	private static void type2() {
		int[] nums = { 1, 4, 6, 2, 8, 5, 3, 9, 0, 7 };
		int k = 3;
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		for (int item : nums) {
			if (minHeap.size() < k) {
				minHeap.offer(item);
			} else {
				if (minHeap.peek() < item) {
					minHeap.poll();
					minHeap.offer(item);
				}
			}
		}
		ArrayList<Integer> list = new ArrayList<>();
		while (!minHeap.isEmpty()) list.add(minHeap.poll());
		System.out.println(list);
	}

	// brute force approach
	private static void type1() {

	}
}
