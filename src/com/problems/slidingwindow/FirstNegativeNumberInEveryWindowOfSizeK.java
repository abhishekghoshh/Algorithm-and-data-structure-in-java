package com.problems.slidingwindow;

import java.util.LinkedList;
import java.util.Queue;

import static com.util.PrintUtl.print;

/*
 * Problem link : 
 * https://practice.geeksforgeeks.org/problems/first-negative-integer-in-every-window-of-size-k3345/1
 * https://www.codingninjas.com/codestudio/problems/first-negative-in-every-window_759333?leftPanelTab=1
 * 
 * Solution link :
 * Aditya Verma :
 * https://www.youtube.com/watch?v=uUXXEgK2Jh8&list=PL_z_8CaSLPWeM8BDJmIYDaoQ5zuwyxnfj&index=4
 * */
public class FirstNegativeNumberInEveryWindowOfSizeK {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	private static void type3() {
		int[] nums = {-8, 2, 3, -6, 10};
		int k = 2;
		int n = nums.length;
		// size of the result array
		int size = n - k + 1;
		int[] answer = new int[size];
		Queue<Integer> queue = new LinkedList<>();
		int left = 0, right = 0;
		while (right <= n) {
			if (right < k) {
				int num = nums[right];
				if (num < 0) queue.offer(num);
			} else {
				if (!queue.isEmpty()) {
					answer[left] = queue.peek();
					// is the start-first element of queue being the start of the window?
					if (queue.peek() == nums[left]) {
						queue.poll();
					}
				} else {
					answer[left] = 0;
				}
				// if the current right is less than zero or not
				if (right < n && nums[right] < 0) {
					queue.offer(nums[right]);
				}
				// shifting the window
				left++;
			}
			right++;
		}
		print(answer);
	}


	private static void type2() {
		int[] nums = {-8, 2, 3, -6, 10};
		int k = 2;
		int n = nums.length;
		// size of the result array
		int size = n - k + 1;
		int[] answer = new int[size];
		Queue<Integer> queue = new LinkedList<>();
		int left = 0, right = 0;
		// we are collecting all the negative numbers from 0 to k-1
		while (right < k) {
			int num = nums[right++];
			if (num < 0) queue.offer(num);
		}
		// currently left=0 and right=k
		// we are doing right<=n because we are calculating the answer for previous
		// window
		while (right <= n) {
			// checking the answer for the previous window
			// at the first iteration, it is checking the answer for first window
			if (!queue.isEmpty()) {
				answer[left] = queue.peek();
				// is the start-first element of queue is start of the window
				if (queue.peek() == nums[left]) {
					queue.poll();
				}
			} else {
				answer[left] = 0;
			}
			// if the current right is less than zero or not
			if (right < n && nums[right] < 0) {
				queue.offer(nums[right]);
			}
			// shifting the window
			left++;
			right++;
		}
		print(nums);
		print(answer);
	}


	// brute force approach
	private static void type1() {
	
	}
}
