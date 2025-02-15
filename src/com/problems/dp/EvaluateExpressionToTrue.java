package com.problems.dp;

import java.util.Arrays;

/*
 * Problem link :
 * https://practice.geeksforgeeks.org/problems/boolean-parenthesization5610/1
 * https://www.interviewbit.com/problems/evaluate-expression-to-true/
 * https://www.naukri.com/code360/problems/problem-name-boolean-evaluation_1214650
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=pGVguAcWX4g&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=38
 * https://www.youtube.com/watch?v=bzXM1Zond9U&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=39
 *
 * Striver:
 * https://www.youtube.com/watch?v=MM7fXopgyjw&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=53
 * https://takeuforward.org/data-structure/evaluate-boolean-expression-to-true-partition-dp-dp-52/
 *
 * Blog :
 * https://www.codingninjas.com/codestudio/library/evaluate-expression-to-true
 */
public class EvaluateExpressionToTrue {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
	}

	// TODO top-down approach
	// O(N ^ 3), where N is the length of the given string.
	// In the worst-case scenario, after performing N ^ 3 calls, all the states
	// will be investigated, and we will be able to use the dp result to find
	// our final answer.
	// O(N^2), where N is the length of the given string.
	// As a 3-D array of size N * N * 2 is being used.
	private static void type4() {
		String expr = "T|T&F^T";
		int n = expr.length();
		char[] arr = expr.toCharArray();
		// last dimension for storing both true and false to result
		// 1 for true and 0 for false
		int[][][] dp = new int[n][n][2];
		// Initialization
		// initializing for the single length characters
		for (int i = 0; i < n; i++) {
			// if the char is T, then we will keep the 1st cell as 1, or we will keep oth cell as 1
			if (arr[i] == 'T') dp[i][i][1] = 1;
			else if (arr[i] == 'F') dp[i][i][0] = 1;

		}
		// Filling the dp array for all length strings
		for (int d = 2; d < n; d += 2) {
			for (int start = 0; start + d < n; start += 2) {
				int end = start + d;
				for (int k = start; k < end; k += 2) {
					if (arr[k + 1] == '|') {
						// T | T = T, T | F = T, F | T = T, F | F = F.
						// this is for evaluating the true
						dp[start][end][1] += dp[start][k][0] * dp[k + 2][end][1]
								+ dp[start][k][1] * dp[k + 2][end][0]
								+ dp[start][k][1] * dp[k + 2][end][1];
						// this is for evaluating the false
						dp[start][end][0] += dp[start][k][0] * dp[k + 2][end][0];
					} else if (arr[k + 1] == '&') {
						// T & T = T, T & F = F, F & T = F , F | F = F.
						dp[start][end][1] += dp[start][k][1] * dp[k + 2][end][1];
						dp[start][end][0] += dp[start][k][0] * dp[k + 2][end][1]
								+ dp[start][k][1] * dp[k + 2][end][0]
								+ dp[start][k][0] * dp[k + 2][end][0];
					} else if (arr[k + 1] == '^') {
						// T ^ T = F, T ^ F = T, F ^ T = T, F ^ F = F
						dp[start][end][1] += dp[start][k][1] * dp[k + 2][end][0]
								+ dp[start][k][0] * dp[k + 2][end][1];
						dp[start][end][0] += dp[start][k][0] * dp[k + 2][end][0]
								+ dp[start][k][1] * dp[k + 2][end][1];
					}
				}
			}
		}
		int countWays = dp[0][n - 1][1];
		System.out.println(countWays);
	}

	// we can translate the previous recursion into iteration
	private static void type3() {
		String expr = "T|T&F^T";
		int n = expr.length();
		char[] arr = expr.toCharArray();
		// last dimension for storing both true and false to result
		// 1 for true and 0 for false
		long[][][] dp = new long[n][n][2];
		// Initialization
		// initializing for the single length characters
		for (int i = 0; i < n; i++) {
			// if the char is T, then we will keep the 1st cell as 1, or we will keep oth cell as 1
			if (arr[i] == 'T') dp[i][i][1] = 1;
			else if (arr[i] == 'F') dp[i][i][0] = 1;

		}

		for (int start = n - 1; start >= 0; start--) {
			for (int end = start + 1; end <= n - 1; end++) {
				for (int k = start + 1; k <= end - 1; k += 2) {
					long leftTrue = dp[start][k - 1][1];
					long leftFalse = dp[start][k - 1][0];
					long rightTrue = dp[k + 1][end][1];
					long rightFalse = dp[k + 1][end][0];
					if (arr[k] == '|') {
						// T | T = T, T | F = T, F | T = T , F | F = F.
						dp[start][end][1] += leftTrue * rightTrue +
								leftTrue * rightFalse +
								leftFalse * rightTrue;
						dp[start][end][0] += leftFalse * rightFalse;

					} else if (arr[k] == '&') {
						// if the operator between left and right side is 'AND'
						// T & T = T, T & F = F, F & T = F , F | F = F.
						dp[start][end][1] += leftTrue * rightTrue;
						dp[start][end][0] += leftTrue * rightFalse +
								leftFalse * rightTrue +
								leftFalse * rightFalse;
					} else {
						// if the operator between left and right side is 'XOR'
						// T ^ T = F, T ^ F = T, F ^ T = T , F ^ F = F.
						dp[start][end][1] += leftTrue * rightFalse +
								leftFalse * rightTrue;
						dp[start][end][0] += leftTrue * rightTrue +
								leftFalse * rightFalse;
					}
				}
			}
		}
		long countWays = dp[0][n - 1][1];
		System.out.println(countWays);
	}

	// memoization
	private static void type2() {
		String s = "T|T&F^T";
		int n = s.length();
		char[] arr = s.toCharArray();
		// we will require a 3-dimensional array now because other than i and j
		// we will also require to hold answer for true and false
		int[][][] dp = new int[n][n][2];
		// Initialization
		for (int[][] matrix : dp)
			for (int[] row : matrix) Arrays.fill(row, -1);


		int countWays = countWays(arr, 0, n - 1, 1, dp);
		System.out.println(countWays);
	}

	private static int countWays(char[] arr, int start, int end, int trueNeeded, int[][][] dp) {
		// if start is greater than the end, which means it is out of bounds
		if (start > end) return 0;
		// start equal to end means it is a single character
		// now we will think what we need, a true or a false
		if (start == end) {
			if (trueNeeded == 1) return (arr[start] == 'T') ? 1 : 0;
			else return (arr[start] == 'F') ? 1 : 0;
		}
		// checking the dp table if it already has the value
		if (dp[start][end][trueNeeded] != -1) return dp[start][end][trueNeeded];
		int ans = 0;
		// we will divide the array into 2 parts
		// and will check how many ways both parts make true and false
		// as to generate true or false we might require both true and false on both sides
		for (int k = start + 1; k <= end - 1; k += 2) {
			// The number of ways expression left to 'K' will be true.
			int leftTrue = countWays(arr, start, k - 1, 1, dp);
			// The number of ways expression left to 'K' will be false.
			int leftFalse = countWays(arr, start, k - 1, 0, dp);
			// The number of ways expression right to 'K' will be true.
			int rightTrue = countWays(arr, k + 1, end, 1, dp);
			// The number of ways expression right to 'K' will be false.
			int rightFalse = countWays(arr, k + 1, end, 0, dp);
			// if the operator between left and right side is 'OR'
			if (arr[k] == '|') {
				// T | T = T, T | F = T, F | T = T , F | F = F.
				if (trueNeeded == 1)
					ans += leftTrue * rightTrue +
							leftTrue * rightFalse +
							leftFalse * rightTrue;
				else
					ans += leftFalse * rightFalse;

			} else if (arr[k] == '&') {
				// if the operator between left and right side is 'AND'
				// T & T = T, T & F = F, F & T = F , F | F = F.
				if (trueNeeded == 1)
					ans += leftTrue * rightTrue;
				else
					ans += leftTrue * rightFalse +
							leftFalse * rightTrue +
							leftFalse * rightFalse;
			} else {
				// if the operator between left and right side is 'XOR'
				// T ^ T = F, T ^ F = T, F ^ T = T , F ^ F = F.
				if (trueNeeded == 1)
					ans += leftTrue * rightFalse +
							leftFalse * rightTrue;
				else
					ans += leftTrue * rightTrue +
							leftFalse * rightFalse;
			}
		}
		// also setting up dp cell value
		return dp[start][end][trueNeeded] = ans;
	}

	// O(4 ^ N), Where N is the length of the string.
	// At each step, we are making 4 calls and at that particular call, we are
	// making 4 calls again thus, bringing the time complexity to O(4^N).
	// O(4^ N), Where N is the length of the string.
	// As this is the amount of space that the recursion stack will use to store 4 ^ N calls.
	private static void type1() {
		String s = "T|T&F^T";
		int n = s.length();
		char[] arr = s.toCharArray();

		int countWays = countWays(arr, 0, n - 1, 1);
		System.out.println(countWays);
	}

	private static int countWays(char[] arr, int start, int end, int trueNeeded) {
		// if start is greater than the end, which means it is out of bounds
		if (start > end) return 0;
		// start equal to end means it is a single character
		// now we will think what we need, a true or a false
		if (start == end) {
			if (trueNeeded == 1) return (arr[start] == 'T') ? 1 : 0;
			else return (arr[start] == 'F') ? 1 : 0;
		}
		int ans = 0;
		// we will divide the array into 2 parts
		// and will check how many ways both parts make true and false
		// as to generate true or false we might require both true and false on both sides
		for (int k = start + 1; k <= end - 1; k += 2) {
			// The number of ways expression left to 'K' will be true.
			int leftTrue = countWays(arr, start, k - 1, 1);
			// The number of ways expression left to 'K' will be false.
			int leftFalse = countWays(arr, start, k - 1, 0);
			// The number of ways expression right to 'K' will be true.
			int rightTrue = countWays(arr, k + 1, end, 1);
			// The number of ways expression right to 'K' will be false.
			int rightFalse = countWays(arr, k + 1, end, 0);
			// if the operator between left and right side is 'OR'
			if (arr[k] == '|') {
				// T | T = T, T | F = T, F | T = T , F | F = F.
				if (trueNeeded == 1)
					ans += leftTrue * rightTrue +
							leftTrue * rightFalse +
							leftFalse * rightTrue;
				else
					ans += leftFalse * rightFalse;

			} else if (arr[k] == '&') {
				// if the operator between left and right side is 'AND'
				// T & T = T, T & F = F, F & T = F , F | F = F.
				if (trueNeeded == 1)
					ans += leftTrue * rightTrue;
				else
					ans += leftTrue * rightFalse +
							leftFalse * rightTrue +
							leftFalse * rightFalse;
			} else {
				// if the operator between left and right side is 'XOR'
				// T ^ T = F, T ^ F = T, F ^ T = T , F ^ F = F.
				if (trueNeeded == 1)
					ans += leftTrue * rightFalse +
							leftFalse * rightTrue;
				else
					ans += leftTrue * rightTrue +
							leftFalse * rightFalse;
			}
		}
		return ans;
	}

}
