package com.problems.binarysearch;

import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * https://www.codingninjas.com/studio/problems/first-and-last-position-of-an-element-in-sorted-array_1082549
 *
 * Solution link :
 * https://www.youtube.com/watch?v=hjR1IYVx9lY
 * https://www.youtube.com/watch?v=zr_AoTxzn0Y&list=PL_z_8CaSLPWeYfhtuKHj-9MpYb6XQJ_f2&index=5
 *
 *
 * https://takeuforward.org/data-structure/last-occurrence-in-a-sorted-array/
 * */
public class FirstAndLastOccurrenceOfANumberInASortedArray {
	public static void main(String[] args) {
		type1();
	}

	private static void type1() {
		int[] nums = { 0, 1, 2, 3, 4, 4, 4, 4, 5, 6, 7, 7, 8, 9, 10, 11, 11 };
		int target = 11;
		int n = nums.length;
		int low = 0, high = n - 1;
		int first = firstOccurrence(nums, low, high, target);
		int last = lastOccurrence(nums, low, high, target);
		int[] answer = new int[]{first, last};
		print(answer);
	}

	private static int firstOccurrence(int[] nums, int low, int high, int target) {
		int index = -1;
		while (low <= high) {
			int mid = low + ((high - low) >> 1);
			if (target <= nums[mid]) {
				if (target == nums[mid]) index = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return index;
	}

	private static int lastOccurrence(int[] nums, int low, int high, int target) {
		int index = -1;
		while (low <= high) {
			int mid = low + ((high - low) >> 1);
			if (target >= nums[mid]) {
				if (target == nums[mid]) index = mid;
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return index;
	}
}
