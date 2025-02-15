package com.problems.array;

import java.util.Arrays;
/*
 *
 * problem links :
 * https://leetcode.com/problems/house-robber/description/
 * https://neetcode.io/problems/house-robber
 * https://www.naukri.com/code360/problems/maximum-sum-of-non-adjacent-elements_843261
 *
 * Solution link :
 * https://www.youtube.com/watch?v=GrMBfJNk_NY&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=6
 * https://www.youtube.com/watch?v=73r3KWiEvyk
 *
 * https://takeuforward.org/data-structure/maximum-sum-of-non-adjacent-elements-dp-5/
 * https://neetcode.io/solutions/house-robber
 * */


public class MaxSumForNonConsecutiveElements {
	// The problem is very similar to a maximum sum of a non-adjacent element in an array
	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
	}

	// using tabulation with memory optimization
	// Time complexity O(n)
	// space complexity O(1)
	// we will compute from 0th to n-1 th height
	// it is the same as previous
	// we just need last and 2nd last value to compute the current
	// we can just hold those two values in 2 variable prev and prev2
	private static void type4() {
		int[] nums = {2, 7, 9, 3, 1};
		int prev = houseRobber4(nums);
		System.out.println(prev);
	}

	private static int houseRobber4(int[] nums) {
		int n = nums.length;
		int prev2 = nums[0];
		int prev = Math.max(nums[0], nums[1]);
		for (int i = 2; i < n; i++) {
			int takeCurrentHome = nums[i] + prev2;
			int notTakeCurrentHome = prev;
			int curr = Math.max(takeCurrentHome, notTakeCurrentHome);
			prev2 = prev;
			prev = curr;
		}
		return prev;
	}

	// using tabulation
	// Time complexity O(k*n)
	// space complexity O(n) for array
	private static void type3() {
		int[] nums = {2, 7, 9, 3, 1};
		int n = nums.length;
		int ans = houseRobber3(n, nums);
		System.out.println(ans);
	}

	private static int houseRobber3(int n, int[] nums) {
		int[] dp = new int[n];
		dp[0] = nums[0];
		dp[1] = Math.max(nums[0], nums[1]);
		for (int i = 2; i < n; i++) {
			int takeCurrentHome = nums[i] + dp[i - 2];
			int notTakeCurrentHome = dp[i - 1];
			dp[i] = Math.max(takeCurrentHome, notTakeCurrentHome);
		}
		return dp[n - 1];
	}

	// using memoization
	// Time complexity O(n)
	// space complexity O(2n) for stack space and array
	private static void type2() {
		int[] nums = {2, 7, 9, 3, 1};
		int n = nums.length;
		int[] dp = new int[n];
		Arrays.fill(dp, -1);
		int answer = houseRobber(n - 1, nums, dp);
		System.out.println(answer);
	}

	private static int houseRobber(int i, int[] nums, int[] dp) {
		if (i == 0) return nums[i];
		if (i < 0) return 0;
		if (dp[i] != -1) return dp[i];
		// taking the current home
		int takeCurrentHome = nums[i] + houseRobber(i - 2, nums, dp);
		// not taking the current home
		int notTakeCurrentHome = houseRobber(i - 1, nums, dp);
		return dp[i] = Math.max(takeCurrentHome, notTakeCurrentHome);
	}

	// using Recursion
	// Time complexity O(2^n)
	// space complexity O(n) for stack space
	private static void type1() {
		int[] nums = {2, 7, 9, 3, 1};
		int n = nums.length;
		int answer = houseRobber(n - 1, nums);
		System.out.println(answer);
	}

	// for every home we have 2 choices, we can either take the home
	// or not to take it, but if we take it then we cannot take its adjacent element.
	// choice 1 => current home + rob (current-2th home)
	// choice 2 => start rob (current-1th home)
	// when i==0 that means it is the last house; we have no other choice other than taking it.
	// when i<0 that means we were in the 1st index, and now it cannot go to the 0th index,
	// so it went to 1-2 => -1 th index
	private static int houseRobber(int i, int[] nums) {
		if (i == 0) return nums[i];
		if (i < 0) return 0;
		// taking the current home
		int takeCurrentHome = nums[i] + houseRobber(i - 2, nums);
		// not taking the current home
		int notTakeCurrentHome = houseRobber(i - 1, nums);
		// returning the max
		return Math.max(takeCurrentHome, notTakeCurrentHome);
	}

}
