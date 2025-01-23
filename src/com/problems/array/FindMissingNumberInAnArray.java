package com.problems.array;

/*
 * Problem link :
 * https://leetcode.com/problems/missing-number
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=bYWLJb3vCWY&t=64s
 *
 * https://takeuforward.org/arrays/find-the-missing-number-in-an-array/
 * */

// Tags: Arrays, Hashing, Bit manipulation
public class FindMissingNumberInAnArray {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// time complexity O(n)
	// Space complexity O(1)
	// find the summation of [0,n] number
	// then subtract the array items one by one,
	// and you will get the exact number
	private static void type3() {
		int[] nums = {9, 6, 4, 2, 3, 5, 7, 0, 1};
		int n = nums.length;
		int sum = (n * (n + 1)) / 2;
		for (int item : nums)
			sum -= item;
		System.out.println(sum);
	}

	// as 1^1 = 0,
	// so if we xor all numbers in array and
	// also with the 0-n 
	// then we will do xor for 2n+1 number
	// out of which only one number is occurring 1 times
	// time complexity O(2n)
	// Space complexity O(1)
	private static void type2() {
		int[] nums = { 9, 6, 4, 2, 3, 5, 7, 0, 1 };
		int n = nums.length;
		int res = 0;
		for (int num : nums) {
			res = res ^ num;
		}
		for (int i = 0; i <= n; i++) {
			res = res ^ i;
		}
		System.out.println(res);
	}

	// Using a Set to check if the value exists or not
	// Time complexity O(2n)
	// Space complexity O(n)
	private static void type1() {
		int[] nums = { 9, 6, 4, 2, 3, 5, 7, 0, 1 };
		int n = nums.length;
		int res = -1;
		boolean[] set = new boolean[n + 1];
		for (int num : nums)
			set[num] = true;
		for (int i = 0; i <= n; i++)
			if (!set[i])
				res = i;
		System.out.println(res);
	}

}
