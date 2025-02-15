package com.problems.dp;
/*
 * Problem link :
 * https://practice.geeksforgeeks.org/problems/longest-repeating-subsequence2004/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=hbTaCmQGqLg&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=29
 * 
 */

public class LongestRepeatingSubsequence {
	public static void main(String[] args) {
		type3();
		type4();
	}

	// same as type3 but little optimized
	// rather creating a different array altogether, we can directly copy the array with itself
	private static void type4() {
		String str = "abbccabd";
		char[] arr = str.toCharArray();
		int n = arr.length;
		int[][] memo = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++)
				// that extra condition for different position same characters
				if (arr[i - 1] == arr[j - 1] && i != j)
					memo[i][j] = 1 + memo[i - 1][j - 1];
				 else
					memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
		}
		int count = memo[n][n];
		System.out.println(count);
	}

	// The intuition is not straight forward here
	// what is the longest repeating subsequence, a subsequence which repeats most in a string.
	// We could think that if we could find the longest subsequence with string with the same string itself,
	// then we would find the longest subsequence, but there is a catch.
	// The entire string would be the matching subsequence then.
	// However, we are going the right way.
	// If we can remind that when we exactly match the characters, then at that position
	// we can add some extra conditions
	// if s1[i] == s2[j] and i != j then essentially matching would occur for different positions
	private static void type3() {
		String str = "abbccabd";
		char[] arr1 = str.toCharArray();
		int n = arr1.length;
		char[] arr2 = new char[n];
		System.arraycopy(arr1, 0, arr2, 0, n);
		int[][] dp = new int[n + 1][n + 1];

		// now we will fill uop the entire dp array
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++)
				// that extra condition for different position same characters
				if (arr1[i - 1] == arr2[j - 1] && i != j)
					dp[i][j] = 1 + dp[i - 1][j - 1];
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
		}
		int count = dp[n][n];
		System.out.println(count);
	}
}
