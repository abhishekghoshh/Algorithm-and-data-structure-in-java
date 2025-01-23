package com.problems.array;

/*
 * Problem link :
 * https://leetcode.com/problems/max-consecutive-ones/
 * https://www.naukri.com/code360/problems/traffic_6682625
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=bYWLJb3vCWY
 * https://www.youtube.com/watch?v=Mo33MjjMlyA&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=45
 * 
 * */
public class MaxConsecutiveOnes {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// optimized approach
	// on every 0 we are checking the previous 1's series is the largest or not
	// if the array is ended with 1 then we are also checking the largest once again
	// time complexity O(n)
	// space complexity O(1)
	private static void type3() {
		int[] nums = { 1, 1, 0, 1, 1, 1 };
		int max = 0, length = 0;
		for (int num : nums) {
			if (num == 1) {
				length++;
			} else {
				max = Math.max(length, max);
				length = 0;
			}
		}
		max = Math.max(length, max);
		System.out.println("max length is " + length);
	}

	// optimized approach
	// on every 1 we are calculating length from its start
	// time complexity O(n)
	// space complexity O(1)
	private static void type2() {
		int[] nums = { 1, 1, 0, 1, 1, 1 };
		int n = nums.length, length = 0, start = 0;
		for (int i = 0; i < n; i++) {
			if (nums[i] == 1)
				length = Math.max(length, i - start + 1);
			else
				start = i + 1;
		}
		System.out.println("max length is " + length);
	}

	// brute force approach
	// time complexity O(n^2)
	// space complexity O(1)
	private static void type1() {
		int[] nums = { 1, 1, 0, 1, 1, 1 };
		int n = nums.length, length = 0;
		for (int i = 0; i < n; i++)
			for (int j = i; j < n; j++) {
				if (nums[j] == 0) break;
				length = Math.max(length, j - i + 1);
			}
		System.out.println("max length is " + length);
	}

}
