package com.problems.slidingwindow;

import java.util.ArrayDeque;
import java.util.Deque;

import static com.util.PrintUtl.print;

/*
 * Problem link:
 * https://leetcode.com/problems/sliding-window-maximum/
 * 
 * Solution:
 * https://www.youtube.com/watch?v=xFJXtB5vSmM&list=PL_z_8CaSLPWeM8BDJmIYDaoQ5zuwyxnfj&index=6
 * https://www.youtube.com/watch?v=CZQGRp93K4k&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=83
 * 
 * https://takeuforward.org/data-structure/sliding-window-maximum/
 * https://www.geeksforgeeks.org/sliding-window-maximum-maximum-of-all-subarrays-of-size-k/
 * */

// Tags : Array, Sliding window, queue
public class MaximumOfAllSubarraysOfSizeK {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
	}

	// TODO study later
	// space complexity is O(2n)
	// for storing max from the right and max from the left
	// time complexity is (2n+k)
	private static void type4() {
		int[] nums = { 1, 3, -1, -3, 5, 3, 6, 7 };
		int k = 2;
		int n = nums.length;
		int[] left = new int[n];
		int[] right = new int[n];
		for (int i = 0; i < n; i += k) {
			int max = Integer.MIN_VALUE;
			int rightBound = Math.min(i + k - 1, n - 1);
			for (int j = i; j <= rightBound; j++) {
				if (nums[j] > max) {
					max = nums[j];
				}
				left[j] = max;
			}
			max = Integer.MIN_VALUE;
			for (int j = rightBound; j >= i; j--) {
				if (nums[j] > max) {
					max = nums[j];
				}
				right[j] = max;
			}
		}
		int[] answer = new int[n - k + 1];
		for (int i = 0; i < n - k + 1; i++) {
			int j = i + k - 1;
			answer[i] = Math.max(right[i], left[j]);
		}
		print(nums);
		print(answer);

	}

	// TODO study later
	private static void type3() {
		int[] nums = { 1, 3, -1, -3, 5, 3, 6, 7 };
		int k = 3;
		int n = nums.length;
		int[] answer = new int[n - k + 1];
		int left = 0;
		int right = k - 1;
		int maxIndex = findMaxIndex(nums, left, right + 1);
		int r = 0;
		answer[r] = nums[maxIndex];
		r++;
		left++;
		right++;
		while (right < n) {
			if (nums[right] >= nums[maxIndex])
				maxIndex = right;
			else if (left > maxIndex)
				if (nums[right] >= nums[maxIndex] - 1) maxIndex = right;
				else if (nums[left] >= nums[maxIndex] - 1) maxIndex = left;
				else maxIndex = findMaxIndex(nums, left, right + 1);
			answer[r++] = nums[maxIndex];
			left++;
			right++;
		}
		print(answer);

	}

	public static int findMaxIndex(int[] arr, int left, int right) {
		int ans = left;
		for (int i = left + 1; i < right; i++) {
			if (arr[ans] <= arr[i])
				ans = i;
		}
		return ans;
	}

	// sliding window
	// time complexity O(2n)
	private static void type2() {
		int[] nums = { 1, 3, -1, -3, 5, 3, 6, 7 };
		int k = 3;
		int n = nums.length;
		int[] answer = new int[n - k + 1];
		int right = 0;
		// store index
		// we will store items in a decreasing manner, so every time the queue peek will
		// have the maximum value
		Deque<Integer> deque = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			// remove numbers out-of-range k
			// i-k th element is from the last window
			// as the current range is from i-k+1 to i
			if (!deque.isEmpty() && deque.peek() == i - k) deque.poll();
			// remove smaller numbers in k range as they are useless
			// if there is any greater number encountered then there is no point of store
			// previous smaller number
			while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i])
				deque.pollLast();
			deque.offer(i);
			// as we are storing in a decreasing manner to peek will give the highest element
			if (i >= k - 1) answer[right++] = nums[deque.peek()];
		}
		print(answer);
	}


	// brute force approach
	private static void type1() {
		int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
		int k = 2;
		int n = nums.length;
		// size of the result array
		int size = n - k + 1;
		int[] answer = new int[size];
		int max;
		for (int i = 0; i < n - k + 1; i++) {
			max = nums[i];
			for (int j = 1; j < k; j++)
				max = Math.max(max, nums[i + j]);
			answer[i] = max;
		}
		print(nums);
		print(answer);
	}

}
