package com.problems.binarysearch;
/*
 * Problem link :
 * 
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=YbkELwnGRdo&list=PL_z_8CaSLPWeYfhtuKHj-9MpYb6XQJ_f2&index=3
 * 
 * */
public class BinarySearchInReverseSortedArray {
	public static void main(String[] args) {
		type1();
	}

	private static void type1() {
		int[] nums = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
		int target = 2;
		int low = 0;
		int high = nums.length - 1;
		int index = search(nums, low, high, target);
		System.out.println(index);
	}

	private static int search(int[] nums, int low, int high, int target) {
		int mid;
		while (low <= high) {
			mid = low + ((high - low) >> 1);
			if (nums[mid] == target) return mid;
			else if (target < nums[mid]) low = mid + 1;
			else high = mid - 1;
		}
		return -1;
	}
}
