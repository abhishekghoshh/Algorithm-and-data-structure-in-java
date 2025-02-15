package com.problems.binarysearch;

/*
 * Problem link :
 * https://www.codingninjas.com/studio/problems/occurrence-of-x-in-a-sorted-array_630456
 *
 * Solution link :
 * https://www.youtube.com/watch?v=hjR1IYVx9lY
 * https://www.youtube.com/watch?v=Ru_HhBFV3Xo&list=PL_z_8CaSLPWeYfhtuKHj-9MpYb6XQJ_f2&index=6
 *
 *
 * https://takeuforward.org/data-structure/count-occurrences-in-sorted-array/
 * */
public class CountOccurrencesInSortedArray {
	public static void main(String[] args) {
		type1();
	}

	private static void type1() {
		int[] nums = {0, 1, 2, 3, 4, 4, 4, 4, 5, 6, 7, 7, 8, 9, 10, 11, 11};
		int target = 11;
		int n = nums.length;
		int low = 0, high = n - 1;
		int first = firstOccurrence(nums, low, high, target);
		int last = lastOccurrence(nums, low, high, target);
		int count = first != -1 ? last - first + 1 : 0;
		System.out.println(count);
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
