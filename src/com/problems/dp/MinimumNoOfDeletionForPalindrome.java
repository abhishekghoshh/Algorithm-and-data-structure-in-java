package com.problems.dp;

/*
 * Problem link :
 *
 * Solution link :
 * https://www.youtube.com/watch?v=CFwCCNbRuLY&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=27
 *
 */
public class MinimumNoOfDeletionForPalindrome {

	public static void main(String[] args) {
		type3();
	}

	// this is the intuition
	// if we think closely, then we will find this is exactly the opposite of
	// the longest palindromic subsequence problem
	// if we remove the longest palindromic subsequence from a string.
	// then we will be having the lowest number of uncommon characters.
	// which will be our answer
	private static void type3() {
		String s = "abcbcxcbe";
		char[] arr = s.toCharArray();
		int n = arr.length;
		char[] reversed = new char[n];
		for (int i = 0; i < n; i++) reversed[i] = arr[n - 1 - i];

		int[][] memo = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (arr[i - 1] == reversed[j - 1]) {
					memo[i][j] = 1 + memo[i - 1][j - 1];
				} else {
					memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
				}
			}
		}
		int count = n - memo[n][n];
		System.out.println(count);
	}
}
