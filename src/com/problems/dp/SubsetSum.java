package com.problems.dp;

import com.util.PrintUtl;

/*
 * Problem link :
 * https://www.naukri.com/code360/problems/subset-sum_630213
 * https://www.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=_gPcYovP7wc&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=7
 * https://www.youtube.com/watch?v=fWX9xDmIzRI&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=15
 *
 * https://takeuforward.org/data-structure/subset-sum-equal-to-target-dp-14/
 * */
public class SubsetSum {
	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
		type5();
	}

	private static void type5() {
		int[] nums = {1, 3, 4, 5, 9, 12};
		int n = nums.length;
		int k = 25;
		// +1 to accommodate the zero elements
		boolean[] prev = new boolean[k + 1];
		// target equal 0 is always possible
		prev[0] = true;

		for (int i = 1; i <= n; i++) {
			boolean[] curr = new boolean[k + 1];
			// target equal 0 is always possible
			curr[0] = true;
			for (int target = 1; target <= k; target++)
				// if we num is less than the target, then we will have 2 possibilities either to take it or not
				if (nums[i - 1] <= target)
					curr[target] = prev[target - nums[i - 1]] || prev[target];
				else
					curr[target] = prev[target];
			// assigning the current row to prev for future iterations
			prev = curr;
		}
		boolean isPossible = prev[k];
		System.out.println(isPossible);
	}


	// similar to knapsack problem
	// here we have to say a subset sum is possible or not
	private static void type4() {
		int[] nums = {1, 3, 4, 5, 9, 12};
		int n = nums.length;
		int k = 25;
		// +1 to accommodate the zero elements
		boolean[][] dp = new boolean[n + 1][k + 1];
		// if we have 0 items then it not possible to create any subset sum
		for (int i = 1; i <= k; i++) dp[0][i] = false;
		// if our target sum is zero, it is possible to create that
		// as we can anytime consider the empty set
		// so even with zero elements, we can create target sum 0
		for (int i = 0; i <= n; i++) dp[i][0] = true;

		for (int i = 1; i <= n; i++)
			for (int target = 1; target <= k; target++)
				// if we num is less than the target, then we will have 2 possibilities either to take it or not
				if (nums[i - 1] <= target) //
					dp[i][target] = dp[i - 1][target - nums[i - 1]] || dp[i - 1][target];
				else
					dp[i][target] = dp[i - 1][target];
		boolean isPossible = dp[n][k];
		System.out.println(isPossible);
	}

	// same as previous but it will help us to derive the tabulation format
	private static void type3() {
		int[] nums = {1, 3, 4, 5, 9, 12};
		int n = nums.length;
		int k = 25;
		// Create a DP table with dimensions [n][k+1]
		int[][] dp = new int[n][k + 1];
		// we will consider zero as unmarked and 1 as true and -1 as false
		boolean isPossible = isSubsetPresent3(n - 1, k, nums, dp);
		System.out.println(isPossible);
	}

	static boolean isSubsetPresent3(int i, int target, int[] nums, int[][] dp) {
		// If the target sum is achieved, return true
		if (target == 0) return true;

		// If we have considered all elements but haven't reached the target, return false
		if (i == 0) return nums[0] == target;

		// If the result for this sub-problem has already been calculated, return it
		if (dp[i][target] != 0) return (dp[i][target] == 1);

		boolean isPresent;
		// Try taking the current element if it doesn't exceed the target
		if (nums[i] <= target)
			isPresent = isSubsetPresent3(i - 1, target - nums[i], nums, dp)
					|| isSubsetPresent3(i - 1, target, nums, dp);
		else // Try not taking the current element
			isPresent = isSubsetPresent3(i - 1, target, nums, dp);

		// Store the result in the DP table and return whether either option was successful
		dp[i][target] = isPresent ? 1 : -1;
		return isPresent;
	}



	// this is the same as the previous type, but here we will use memoization
	private static void type2() {
		int[] nums = {1, 3, 4, 5, 9, 12};
		int n = nums.length;
		int k = 25;
		// we will create a memo array of int
		int[][] memo = new int[n][k + 1];
		// we will consider zero as unmarked and 1 as true and -1 as false
		boolean isPossible = isSubsetPresent2(0, nums, k, memo);
		System.out.println(isPossible);

	}

	public static boolean isSubsetPresent2(int i, int[] nums, int rem, int[][] dp) {
		// if the remaining is 0, that means we are capable of creating the sum
		if (rem == 0) return true;
		// if the rem is negative or the index is out of bound, then we will return false.
		// we are checking rem is lesser than 0 or not, we are doing it here because otherwise we have to
		// check the same before spawning a new recursion call
		if (rem < 0 || i == nums.length) return false;
		// check if the recursion call is already made
		if (dp[i][rem] != 0) return (dp[i][rem] == 1);
		// else we have 2 options, either to use the current number or not
		boolean isPresent = isSubsetPresent2(i + 1, nums, rem - nums[i], dp)
				|| isSubsetPresent2(i + 1, nums, rem, dp);
		dp[i][rem] = (isPresent) ? 1 : -1;
		return isPresent;
	}
	// this is a simple recursion
	private static void type1() {
		int[] nums = {1, 3, 4, 5, 9, 12};
		int k = 25;
		boolean isPossible = isSubsetPresent1(0, nums, k);
		System.out.println(isPossible);
	}

	public static boolean isSubsetPresent1(int i, int[] nums, int rem) {
		// if the remaining is 0, that means we are capable of creating the sum
		if (rem == 0) return true;
		// if the rem is negative or the index is out of bound, then we will return false.
		// we could check here if rem is less than 0, or we can check while calling the recursion
		// if we can accommodate the number or not or nums[n-1] < rem
		if (rem < 0 || i == nums.length) return false;
		// else we have 2 options, either to use the current number or not
		return isSubsetPresent1(i + 1, nums, rem - nums[i])
				|| isSubsetPresent1(i + 1, nums, rem);
	}
}
