package com.problems.dp;
/*
 * Problem link :
 * https://www.geeksforgeeks.org/problems/rod-cutting0840/1
 * https://www.naukri.com/code360/problems/rod-cutting-problem_800284
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=SZqAQLjDsag&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=14
 *
 * https://www.youtube.com/watch?v=mO8XpGoJwuo&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=25
 * https://takeuforward.org/data-structure/rod-cutting-problem-dp-24/
 */

public class RodCutting {
	// TODO similar to the unbounded knapsack problem
	/*
	 * Given a rod of length N inches and an array of prices, price[].
	 * price[i] denotes the value of a piece of length i.
	 * Determine the maximum value obtainable by cutting up the rod and selling the pieces.
	 * */
	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
		type5();
	}

	// further space optimization
	private static void type5() {
		int[] prices = {3, 5, 7, 8, 15, 20};
		int[] lengths = {2, 3, 4, 5, 9, 12};
		int total = 14;
		int n = prices.length;

		// if the length is not given, then we will create an array of size n and put there 1..N
		int[] dp = new int[total + 1];

		// initialization
		// we don't need initialization as we are setting everything to 0, the int array default value is already 0


		// similar to the knapsack problem, we can use a single dp array.
		// but here is a catch, in knapsack we were dependent on the previous row that's why
		// we were starting from the n..0 otherwise we would get the modified value of dp[j-x].
		// but here we need the j-x th index of the current row, so we can start iteration from 0
		for (int i = 1; i <= n; i++) {
			int rod = lengths[i - 1];
			for (int len = 1; len <= total; len++)
				// if we can accommodate the current length
				if (rod <= len)
					dp[len] = Math.max(
							prices[i - 1] + dp[len - rod],
							dp[len]
					);
		}
		int profit = dp[total];
		System.out.println(profit);
	}


	// using two 1D arrays for storing current and previous row
	private static void type4() {
		int[] prices = {3, 5, 7, 8, 15, 20};
		int[] length = {2, 3, 4, 5, 9, 12};
		int total = 14;
		int n = prices.length;

		// if the length is not given, then we will create an array of size n and put there 1..N
		int[] prev = new int[total + 1];

		// we do not need any initiation for the problem, because we are setting 0th row and column to be 0
		for (int i = 1; i <= n; i++) {
			int rod = length[i - 1];
			int[] curr = new int[total + 1];
			for (int len = 1; len <= total; len++)
				// if we can accommodate the current rod if it is less than the length
				if (rod <= len)
					curr[len] = Math.max(
							prices[i - 1] + curr[len - rod],
							prev[len]
					);
				else curr[len] = prev[len];
			prev = curr;
		}
		int profit = prev[total];
		System.out.println(profit);
	}

	// top-down approach or iterative memoization
	private static void type3() {
		int[] prices = {3, 5, 7, 8, 15, 20};
		int[] lengths = {2, 3, 4, 5, 9, 12};
		int total = 14;
		int n = prices.length;
		// if the length is not given, then we will create an array of size n and put there 1..N
		int[][] dp = new int[n + 1][total + 1];
		// initialization for every i and j equal 0
		dp[0][0] = 0;
		for (int i = 1; i <= n; i++) dp[i][0] = 0;
		for (int i = 1; i <= total; i++) dp[0][i] = 0;

		for (int i = 1; i <= n; i++) {
			int rod = lengths[i - 1];
			for (int len = 1; len <= total; len++)
				// if we can accommodate the current rod if it is less than the length
				if (rod <= len)
					dp[i][len] = Math.max(
							prices[i - 1] + dp[i][len - rod],
							dp[i - 1][len]
					);
				else dp[i][len] = dp[i - 1][len];
		}
		int profit = dp[n][total];
		System.out.println(profit);
	}

	// recursion with memoization
	private static void type2() {
		int[] prices = {3, 5, 7, 8, 15, 20};
		int[] lengths = {2, 3, 4, 5, 9, 12};
		int total = 14;
		int n = prices.length;
		// if the length is not given, then we will create an array of size n and put there 1..N
		int[][] dp = new int[n + 1][total + 1];
		int profit = rodCut2(n, prices, lengths, total, dp);
		System.out.println(profit);
	}

	private static int rodCut2(int n, int[] prices, int[] lengths, int total, int[][] dp) {
		// if either n or total length is 0, then we cannot accommodate anything
		if (n == 0 || total == 0) return 0;
		// if recursion is already done, then dp will have a non-zero value
		if (dp[n][total] != 0) return dp[n][total];
		// otherwise we would have 2 choices, either to take the current element or not take it
		// unlike regular knapsack here we can take the same element more than once
		if (lengths[n - 1] <= total)
			return dp[n][total] = Math.max(
					prices[n - 1] + rodCut2(n, prices, lengths, total - lengths[n - 1], dp),
					rodCut2(n - 1, prices, lengths, total, dp)
			);
		else
			return dp[n][total] =
					rodCut2(n - 1, prices, lengths, total, dp);
	}

	// with recursion
	private static void type1() {

	}


}
