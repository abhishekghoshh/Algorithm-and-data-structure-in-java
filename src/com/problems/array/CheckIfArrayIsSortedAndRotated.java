package com.problems.array;

/*
 *
 * problem links :
 * https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/description/
 * https://www.codingninjas.com/studio/problems/ninja-and-the-sorted-check_6581957
 *
 * Solution link :
 * https://www.youtube.com/watch?v=37E9ckMDdTk&t=1722s
 *
 * https://takeuforward.org/data-structure/check-if-an-array-is-sorted/
 * */
public class CheckIfArrayIsSortedAndRotated {

	public static void main(String[] args) {
		type1();
	}

	private static void type1() {
		int[] nums = {3, 4, 5, 1, 2};
		System.out.println(check(nums));

	}

	// Given an array nums, return true if the array was originally sorted in non-decreasing order,
	// then rotated some number of positions (including zero).
	// Otherwise, return false.
	public static boolean check(int[] nums) {
		int n = nums.length;
		if (n == 1 || n == 2) return true;
		// if the first < last, then we just have to check if there is any out-of-order element in between or not
		if (nums[0] < nums[n - 1]) {
			for (int i = 0; i < n - 1; i++)
				// i th item is greater than an i+1 th element that means an out-of-order element
				if (nums[i] > nums[i + 1]) return false;
		} else {
			// else part means the array is rotated already, so
			//  we will try to find the first out-of-order element
			int i;
			for (i = 0; i < n - 1; i++)
				// i+1 is the starting of the start of the rotated array
				if (nums[i] > nums[i + 1])
					break;
			// find the out-of-order element order for the 2nd part array
			for (int j = i + 1; j < n - 1; j++)
				// j th item is greater than j+1 th element that means an out-of-order element
				if (nums[j] > nums[j + 1])
					return false;
		}
		return true;
	}
}
