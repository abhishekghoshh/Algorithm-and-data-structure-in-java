package com.problems.dp;
/*
 * Problem link :
 * https://practice.geeksforgeeks.org/problems/knapsack-with-duplicate-items4201/1
 * https://www.naukri.com/code360/problems/unbounded-knapsack_1215029
 *
 * Solution link :
 * https://www.youtube.com/watch?v=aycn9KO8_Ls&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=13
 *
 * https://www.youtube.com/watch?v=OgvOZ6OrJoY&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=24
 * https://takeuforward.org/data-structure/unbounded-knapsack-dp-23/
 */
public class UnboundedKnapsack {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
		type5();
	}

	// further space optimization
	// most space optimized
	private static void type5() {
		int[] wt = {2, 3, 4, 5, 9, 12};
		int[] val = {3, 5, 7, 8, 15, 20};
		int n = val.length;
		int W = 13;

		int[] dp = new int[W + 1];

		// TODO as the default value for int array is 0 so we do not need the initialization for this problem
		// these 2 are for initialization, for both n and w equal to 0, then cell value will be 0
		// though we do not need to do it as the default value in an int array is already 0

		// similar to the knapsack problem, we can use a single dp array.
		// but here is a catch, in knapsack we were dependent on the previous row that's why
		// we were starting from the n..0 otherwise we would get the modified value of dp[j-x].
		// but here we need the j-x th index of the current row, so we can start iteration from 0
		for (int i = 1; i <= n; i++) {
			for (int w = 1; w <= W; w++)
				// we have 2 choices, either to take it or not,
				// but we can take it only if the current item weight is lesser than w
				if (wt[i - 1] <= w)
					dp[w] = Math.max(
							val[i - 1] + dp[w - wt[i - 1]],
							dp[w]
					);
		}

		int profit = dp[W];
		System.out.println(profit);
	}

	// using two 1D arrays for storing current and previous row
	private static void type4() {
		int[] wt = {2, 3, 4, 5, 9, 12};
		int[] val = {3, 5, 7, 8, 15, 20};
		int n = val.length;
		int W = 13;

		int[] prev = new int[W + 1];

		// TODO as the default value for int array is 0 so we do not need the initialization for this problem
		// these 2 are for initialization, for both n and w equal to 0, then cell value will be 0
		// though we do not need to do it as the default value in an int array is already 0

		for (int i = 1; i <= n; i++) {
			int[] curr = new int[W + 1];
			for (int w = 1; w <= W; w++)
				// we have 2 choices, either to take it or not,
				// but we can take it only if the current item weight is lesser than w
				if (wt[i - 1] <= w)
					curr[w] = Math.max(
							val[i - 1] + curr[w - wt[i - 1]],
							prev[w]
					);
				else
					curr[w] = prev[w];
			// assigning the curr to prev
			prev = curr;
		}

		int profit = prev[W];
		System.out.println(profit);
	}

	// we could optimize the space by using a 1D array
	private static void type3() {
		int[] wt = {2, 3, 4, 5, 9, 12};
		int[] val = {3, 5, 7, 8, 15, 20};
		int n = val.length;
		int W = 13;
		int[][] dp = new int[n + 1][W + 1];

		// TODO as the default value for int array is 0 so we do not need the initialization for this problem
		// these 2 are for initialization, for both n and w equal to 0, then cell value will be 0
		// though we do not need to do it as the default value in an int array is already 0
		for (int i = 0; i <= n; i++) dp[i][0] = 0;
		for (int i = 0; i <= W; i++) dp[0][W] = 0;

		for (int i = 1; i <= n; i++)
			for (int w = 1; w <= W; w++)
				// we have 2 choices, either to take it or not,
				// but we can take it only if the current item weight is lesser than w
				if (wt[i - 1] <= w)
					dp[i][w] = Math.max(
							val[i - 1] + dp[i][w - wt[i - 1]],
							dp[i - 1][w]
					);
				else
					dp[i][w] = dp[i - 1][w];


		int profit = dp[n][W];
		System.out.println(profit);
	}

	private static void type2() {
		int[] wt = {2, 3, 4, 5, 9, 12};
		int[] val = {3, 5, 7, 8, 15, 20};
		int n = val.length;
		int w = 13;
		int[][] memo = new int[n + 1][w + 1];
		// TODO as the default value for int array is 0 so we do not need the initialization for this problem
		// these 2 are for initialization, for both n and w equal to 0, then cell value will be 0
		// though we do not need to do it as the default value in an int array is already 0
		for (int i = 0; i <= n; i++) memo[i][0] = 0;
		for (int i = 0; i <= w; i++) memo[0][w] = 0;

		int profit = knapsack(wt, val, n, w, memo);
		System.out.println(profit);
	}

	private static int knapsack(int[] wt, int[] val, int n, int w, int[][] memo) {
		// similar to 0/1 knapsack if n or w is 0, then the answer is 0
		if (n == 0 || w == 0) return 0;
		// we are checking if the current recursion call is already made then we will not call it again
		if (memo[n][w] != 0) return memo[n][w];
		// if we can incorporate the current weight, then we have 2 choices, either to take it or not.
		// but here is a catch, unlike 0/1 knapsack, we can take a single item as much as possible.
		// that's why we are just decreasing the remaining weight, not the item count (n)
		if (wt[n - 1] <= w)
			memo[n][w] = Math.max(
					val[n - 1] + knapsack(wt, val, n, w - wt[n - 1]),
					knapsack(wt, val, n - 1, w)
			);
		else
			memo[n][w] = knapsack(wt, val, n - 1, w);

		return memo[n][w];
	}

	private static void type1() {
		int[] wt = {2, 3, 4, 5, 9, 12};
		int[] val = {3, 5, 7, 8, 15, 20};
		int n = val.length;
		int w = 13;
		int profit = knapsack(wt, val, n, w);
		System.out.println(profit);
	}

	private static int knapsack(int[] wt, int[] val, int n, int w) {
		// similar to 0/1 knapsack if n or w is 0, then the answer is 0
		if (n == 0 || w == 0) return 0;
		// if we can incorporate the current weight, then we have 2 choices, either to take it or not.
		// but here is a catch, unlike 0/1 knapsack, we can take a single item as much as possible.
		// that's why we are just decreasing the remaining weight, not the item count (n)
		if (wt[n - 1] <= w)
			return Math.max(
					val[n - 1] + knapsack(wt, val, n, w - wt[n - 1]),
					knapsack(wt, val, n - 1, w)
			);
		else
			return knapsack(wt, val, n - 1, w);

	}
}
