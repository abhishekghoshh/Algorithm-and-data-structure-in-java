package com.problems.dp;


/*
 * Problem link :
 * https://leetcode.com/problems/partition-equal-subset-sum/description/
 * https://neetcode.io/problems/partition-equal-subset-sum
 * https://www.naukri.com/code360/problems/partition-equal-subset-sum_892980
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=UmMh7xp07kY&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=8
 * https://www.youtube.com/watch?v=7win3dcgo3k&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=16
 * https://www.youtube.com/watch?v=IsvocB5BJhw
 *
 * https://takeuforward.org/data-structure/partition-equal-subset-sum-dp-15/
 * https://neetcode.io/solutions/partition-equal-subset-sum
 */
public class EqualSumPartition {

	// TODO it is an extension of the target sum / subset sum problem
	// Given a non-empty array nums containing only positive integers, find if the
	// array can be partitioned into two subsets such that the sum of elements in
	// both subsets is equal.
	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}



	// type 2 is taking more time than type1 in leetcode
	// same as a subset sum problem
	// just here a target sum is total sum divide by 2
	// time complexity is O(n^2 + 2n)
	// space complexity is O(n^2)
	private static void type3() {
		int[] nums = {1, 5, 11, 5};
		boolean isPossible = canPartition3(nums);
		System.out.println(isPossible);
	}

	public static boolean canPartition3(int[] nums) {
		int n = nums.length;
		int sum = 0;
		for (int num : nums) sum += num;
		// if the total is, even then we can partition else, it is not possible to distribute to sum equally
		if (sum % 2 != 0) return false;
		int target = sum / 2;
		boolean[][] dp = new boolean[n + 1][target + 1];
		// if our target sum is zero, it is possible to create that
		for (int j = 0; j <= target; j++) dp[0][j] = false;
		// with zero elements, we can create target sum 0, as we can anytime consider the empty set
		for (int i = 0; i <= n; i++) dp[i][0] = true;
		// now we fill all the cells one by one
		for (int i = 1; i <= n; i++) {
			int num = nums[i - 1];
			for (int s = 0; s <= target; s++) {
				if (num <= s)
					dp[i][s] = dp[i - 1][s - num] || dp[i - 1][s];
				else
					dp[i][s] = dp[i - 1][s];
			}
		}
		return dp[n][target];
	}

	// exactly same a previous with memoization technique
	// in the recursion we have changed the condition a little bit
	private static void type2() {
		int[] nums = {1, 5, 11, 5};
		boolean isPossible = canPartition2(nums);
		System.out.println(isPossible);
	}

	public static boolean canPartition2(int[] nums) {
		int n = nums.length;
		int sum = 0;
		for (int num : nums) sum += num;
		// if the total is, even then we can partition else; it is not possible to distribute to sum equally
		if (sum % 2 != 0) return false;
		int target = sum / 2;
		int[][] dp = new int[n + 1][target + 1];
		// we will treat 0 as unvisited an 1 as possible and -1 as not possible
		return targetSum2(nums, n, target, dp);
	}

	private static boolean targetSum2(int[] nums, int n, int target, int[][] dp) {
		// if target is 0 then the sum is possible
		if (target == 0) return true;
		// if the target is less than 0 or n == 0 then the sum is not possible
		if (n == 0 || target < 0) return false;
		// if the memo is not 0, then the recursion call is already complete
		if (dp[n][target] != 0) return (dp[n][target] == 1);
		boolean isPossible = targetSum2(nums, n - 1, target - nums[n - 1], dp)
				|| targetSum2(nums, n - 1, target, dp);
		// as the memo is int array, so we have to place 1 or -1 accordingly
		dp[n][target] = isPossible ? 1 : -1;
		return isPossible;
	}

	// memoization technique
	private static void type1() {
		int[] nums = {1, 5, 11, 5};
		boolean isPossible = canPartition1(nums);
		System.out.println(isPossible);
	}

	public static boolean canPartition1(int[] nums) {
		int n = nums.length;
		int sum = 0;
		for (int num : nums) sum += num;
		// if total is, even then we can partition else; it is not possible to distribute to sum equally
		if (sum % 2 != 0) return false;
		int target = sum / 2;
		int[][] dp = new int[n + 1][target + 1];
		// we will treat 0 as unvisited an 1 as possible and -1 as not possible
		return targetSum1(nums, n, target, dp);
	}

	private static boolean targetSum1(int[] nums, int n, int target, int[][] dp) {
		// if at any point, the target is zero then we will return true
		if (target == 0) return true;
		if (n == 0) return false;
		if (dp[n][target] != 0) return (dp[n][target] == 1);
		boolean isPossible;
		// here we have 2 choices depending on the target value
		// if it lesser than the current item on nums
		int num = nums[n - 1];
		if (num <= target)
			isPossible = targetSum1(nums, n - 1, target - num, dp)
					|| targetSum1(nums, n - 1, target, dp);
		else
			isPossible = targetSum1(nums, n - 1, target, dp);

		dp[n][target] = isPossible ? 1 : -1;
		return isPossible;
	}
}
