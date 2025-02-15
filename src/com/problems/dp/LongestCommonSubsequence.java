package com.problems.dp;

import java.util.Arrays;

/*
 * Problem link :
 * https://leetcode.com/problems/longest-common-subsequence/description/
 * https://neetcode.io/problems/longest-common-subsequence
 * https://www.naukri.com/code360/problems/longest-common-subsequence_624879
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=4dMlCZTONj8&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=18
 * https://www.youtube.com/watch?v=4Urd0a0BNng&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=19
 * https://www.youtube.com/watch?v=g_hIx4yn9zg&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=20
 * https://www.youtube.com/watch?v=hR3s9rGlMTU&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=21
 *
 * https://www.youtube.com/watch?v=Ua0GhsJSlWM
 *
 * https://www.youtube.com/watch?v=NPZn9jBrX8U&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=26
 * https://takeuforward.org/data-structure/longest-common-subsequence-dp-25/
 *
 * https://www.youtube.com/watch?v=Ua0GhsJSlWM
 * https://neetcode.io/solutions/longest-common-subsequence
 */
public class LongestCommonSubsequence {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
	}

	// todo this is not required in the interview
	// Tabulation with a memory optimization
	// two 1D array for storing the current row and previous row
	private static void type4() {
		String text1 = "abacab";
		String text2 = "cab";
		int count = longestCommonSubsequence4(text1, text2);
		System.out.println(count);
	}

	public static int longestCommonSubsequence4(String text1, String text2) {
		// we are creating 1D array based on s2 length,
		// so if s1 length is lesser by any chance, then we will swap the variables
		if (text1.length() < text2.length()) return longestCommonSubsequence4(text2, text1);
		char[] s1 = text1.toCharArray();
		char[] s2 = text2.toCharArray();

		int n1 = s1.length, n2 = s2.length;
		// prev is dp[i-1] and curr is dp[i]
		int[] prev = new int[n2 + 1];

		// it is exactly similar to the last type just here we are creating an array everytime
		// for processing the current row
		for (int i = 1; i <= n1; i++) {
			int[] curr = new int[n2];
			for (int j = 1; j <= n2; j++) {
				if (s1[i - 1] == s2[j - 1])
					curr[j] = 1 + prev[j - 1];
				else
					curr[j] = Math.max(
							prev[j],
							curr[j - 1]
					);
			}
			// assigning the dp array
			prev = curr;
		}

		return prev[prev.length - 1];
	}

	// same as previous type but here we will use bottom up approach
	// recurrence relation is same as the recursion
	private static void type3() {
		String text1 = "abac";
		String text2 = "cab";
		int count = longestCommonSubsequence3(text1, text2);
		System.out.println(count);
	}

	private static int longestCommonSubsequence3(String text1, String text2) {
		char[] arr1 = text1.toCharArray();
		char[] arr2 = text2.toCharArray();
		int n1 = arr1.length;
		int n2 = arr2.length;
		int[][] dp = new int[n1 + 1][n2 + 1];
		// we could fill up all the cell with 0 for n1, and n2 is 0,
		// but it is not needed as all the cells are already 0
		for (int i = 1; i <= n1; i++) {
			for (int j = 1; j <= n2; j++)
				// if the char is same then we will decrease the length for both of the strings
				if (arr1[i - 1] == arr2[j - 1])
					dp[i][j] = 1 + dp[i - 1][j - 1];
				else
					// else we take the max from the 2 choices
					dp[i][j] = Math.max(
							dp[i - 1][j],
							dp[i][j - 1]
					);
		}
		return dp[n1][n2];
	}

	// recursion with memoization
	// same as previous but here we will introduce a dp array with dimension of (n1+1)*(n2+1)
	private static void type2() {
		String s1 = "xabcmagg";
		String s2 = "abcdamg";
		int count = longestCommonSubsequence2(s1, s2);
		System.out.println(count);
	}

	public static int longestCommonSubsequence2(String text1, String text2) {
		int n1 = text1.length();
		int n2 = text2.length();
		int[][] memo = new int[n1 + 1][n2 + 1];
		for (int[] row : memo) Arrays.fill(row, -1);
		return lcs2(text1, text2, n1, n2, memo);
	}

	private static int lcs2(String s1, String s2, int n1, int n2, int[][] dp) {
		// if one of the string len is 0, then we will return 0
		if (n1 == 0 || n2 == 0) return 0;
		// if the cell has the value, then we will return the value
		if (dp[n1][n2] != -1) return dp[n1][n2];

		// if the last character is the same, then we will call the recursion with n1-1 and n2-1
		if (s1.charAt(n1 - 1) == s2.charAt(n2 - 1))
			return dp[n1][n2] = 1 + lcs2(s1, s2, n1 - 1, n2 - 1, dp);
		// else we have two choices, first consider the last char of the first string and then consider
		// the second string and take max among them
		return dp[n1][n2] = Math.max(
				lcs2(s1, s2, n1, n2 - 1, dp),
				lcs2(s1, s2, n1 - 1, n2, dp)
			);
	}


	// We will use simple recursion
	// with an intuition that, if the last char is same, then we will add 1 and check for 1 less character string
	// for both of the strings.
	// If the last character does not match, then we will have 2 choices.
	// 1. s1-1 and s2 and 2. s1 and s2-1 string.
	private static void type1() {
		String s1 = "xabcma";
		String s2 = "abcda";
		int count = longestCommonSubsequence1(s1, s2);
		System.out.println(count);
	}

	public static int longestCommonSubsequence1(String text1, String text2) {
		return lcs1(text1, text2, text1.length(), text2.length());
	}

	private static int lcs1(String s1, String s2, int n1, int n2) {
		// if one of the string len is 0, then we will return 0
		if (n1 == 0 || n2 == 0) return 0;
		// if the last character is the same, then we will call the recursion with n1-1 and n2-1
		if (s1.charAt(n1 - 1) == s2.charAt(n2 - 1))
			return 1 + lcs1(s1, s2, n1 - 1, n2 - 1);
		// else we have two choices, first consider the last char of the first string and then consider
		// the second string and take max among them
		return Math.max(
				lcs1(s1, s2, n1, n2 - 1),
				lcs1(s1, s2, n1 - 1, n2)
			);

	}
}
