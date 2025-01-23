package com.problems.dp;

import java.util.Arrays;

/*
 * Problem link :
 * https://leetcode.com/problems/longest-palindromic-subsequence/description/
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=wuOOOATz_IA&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=26
 *
 * https://www.youtube.com/watch?v=bUr8cNWI09Q
 * https://neetcode.io/solutions/longest-palindromic-subsequence
 *
 * https://www.youtube.com/watch?v=6i_T5kkfv4A&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=29
 * https://takeuforward.org/data-structure/longest-palindromic-subsequence-dp-28/
 */
public class LongestPalindromicSubsequence {
	// todo longest palindromic subsequence is nothing but
	//  longest subsequence of a string with its reverse
	public static void main(String[] args) {
		type2();
		type3();
		type4();
		type5();

		type6();

		type7();
		type8();
	}

	// TODO if we could change the for loop in previous type in such a way that
	//  it works in a previous and current dp layer instead of touching all layer in one loop
	//  we could directly space optimize the solution
	//  we have added 2 solution for that
	private static void type8() {
		String s = "ynabcdbxmn";
		char[] arr = s.toCharArray();
		int n = s.length();
		// similar to the previous type, but here we are doing layer wise
		{
			int[][] dp = new int[n][n];
			for (int i = 1; i < n; i++) {
				dp[i][i] = 1;
				for (int j = i - 1; j >= 0; j--) {
					if (arr[i] == arr[j]) dp[j][i] = 2 + dp[j + 1][i - 1];
					else dp[j][i] = Math.max(dp[j + 1][i], dp[j][i - 1]);
				}
			}
			System.out.println(dp[0][n - 1]);
		}
		// todo don't discuss unless you know it clearly
		// space optimized version of the previous block
		{
			int[] dp = new int[n];
			dp[0] = 1;
			for (int i = 1; i < n; i++) {
				dp[i] = 1;
				int max = 0;
				for (int j = i - 1; j >= 0; j--) {
					int next = dp[j];
					if (arr[i] == arr[j]) dp[j] = 2 + max;
					max = Math.max(max, next);
				}
			}
			int max = 0;
			for (int i : dp) max = Math.max(i, max);
			System.out.println(max);
		}
	}


	// TODO this a separate approach
	// lets say the string is aXb then to find the longest palindrome
	// there would be two case if a==b then it would be func(aXb) => 2 + func(x)
	// else func(aXb) = max ( func(aX), func(Xb) )
	// we will do it for all length of strings
	// let's directly implement in top down approach
	private static void type7() {
		String s = "ynabcdbxmn";
		int count = longestPalindromeSubseq7(s);
		System.out.println(count);
	}

	private static int longestPalindromeSubseq7(String s) {
		char[] arr = s.toCharArray();
		int n = arr.length;
		int[][] dp = new int[n][n];

		// this is to handle all single digit characters; they are by default palindrome
		for (int i = 0; i < n; i++) dp[i][i] = 1;
		// like the palindromic substring we do not need to compute 2 letter digit here I guess

		// now we will do generalization,
		// we will start from j and j+1, and we will check for all j and j+i
		for (int d = 2; d <= n; d++) {
			for (int i = 0; i + d <= n; i++) {
				int end = i + d - 1;
				if (arr[i] == arr[end])
					dp[i][end] = 2 + dp[i + 1][end - 1];
				else
					dp[i][end] = Math.max(dp[i + 1][end], dp[i][end - 1]);
			}
		}

		return dp[0][n - 1];
	}

	// TODO there is another approach of solving this problem, though it might not be that much efficient
	//  but we should also that one as well
	// we try to expand from all indices, two times,
	// 1. keeping that index as the center, making the odd length string
	// 2. keeping that index as the first left cell and index+1 as the first right cell, making the even length string
	private static void type6() {
		String s = "ynabcdbxmn";
		int max = longestPalindromeSubseq6(s);
		System.out.println(max);
	}

	private static int longestPalindromeSubseq6(String s) {
		char[] arr = s.toCharArray();
		int n = arr.length;
		int[][] dp = new int[n][n];
		for (int[] row : dp) Arrays.fill(row, -1);
		int max = 0;
		for (int i = 0; i < n; i++) {
			int currentMax = Math.max(
					longestPalindromicSubsequence(i, i, arr, dp),
					longestPalindromicSubsequence(i, i + 1, arr, dp)
			);
			max = Math.max(max, currentMax);
		}
		return max;
	}

	private static int longestPalindromicSubsequence(int l, int r, char[] arr, int[][] dp) {
		int n = arr.length;
		// this is the base case or when the left and right are out of boundary
		if (l < 0 || r >= n) return 0;
		// we are directly returning from the stored values
		if (dp[l][r] != -1) return dp[l][r];
		// if left and right values are the same, then we will check for left-1 and right+1
		// if not then we have two options, left,right+1 and left-1,right
		if (arr[l] == arr[r])
			// for then first time when are checking for (i,i) at that time left and right are same
			// for that time the string length will be 1
			return dp[l][r] = ((l == r) ? 1 : 2) + longestPalindromicSubsequence(l - 1, r + 1, arr, dp);
		else
			return dp[l][r] = Math.max(
					longestPalindromicSubsequence(l - 1, r, arr, dp),
					longestPalindromicSubsequence(l, r + 1, arr, dp)
			);
	}


	// TODO from type2() to type5() all have the same approach, just everytime we have done some optimization
	// it is even more optimized than the previous as we are using the same char array for comparing
	// and the same arrays for current and previous dp value
	private static void type5() {
		String s = "ynabcdbxmn";

		int ans = longestPalindromeSubseq5(s);
		System.out.println(ans);
	}

	private static int longestPalindromeSubseq5(String s) {
		char[] arr = s.toCharArray();
		int n = arr.length;
		// Create two arrays to store the LCS lengths
		int[] prev = new int[n + 1];
		int[] cur = new int[n + 1];

		// Base Case: Initialized to 0, as no characters matched yet.
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++)
				// i-1 and n-j is same as (n-1)-(j-1)
				if (arr[i - 1] == arr[n - j])
					cur[j] = 1 + prev[j - 1];
				else
					cur[j] = Math.max(prev[j], cur[j - 1]);
			// Update the prev array to store the current values
			System.arraycopy(cur, 0, prev, 0, n + 1);
		}
		return prev[n];
	}

	// todo it is more optimized than the previous
	//  here we have used the same array,
	private static void type4() {
		String s = "ynabcdbxmn";
		String ans = longestPalindromeSubseq4(s);
		System.out.println(ans);
	}

	private static String longestPalindromeSubseq4(String s) {
		char[] arr = s.toCharArray();
		int n = arr.length;
		int[][] dp = new int[n + 1][n + 1];
		// we should be initializing the 0th cells with 0, but the array is already initialized with 0,
		// so we don't need to do anything
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++)
				if (arr[i - 1] == arr[n - j])
					dp[i][j] = 1 + dp[i - 1][j - 1];
				else
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
		}
		int len = dp[n][n];
		// extension of the problem
		char[] answer = new char[len];
		int n1 = n, n2 = n;
		while (n1 != 0 && n2 != 0) {
			if (arr[n1 - 1] == arr[n - n2]) {
				answer[--len] = arr[n1 - 1];
				n1--;
				n2--;
			} else {
				if (dp[n1 - 1][n2] > dp[n1][n2 - 1]) n1--;
				else n2--;
			}
		}
		return new String(answer);
	}

	// todo very optimal approach, and we can discuss it in the interview
	// same as previous, but here we are using an array instead of string builder.
	// it will become little optimized from the previous
	// to make it more optimized we can make use of the same array instead of creating the reverse array
	private static void type3() {
		String s = "ynabcdbxmn";
		String ans = longestPalindromeSubseq3(s);
		System.out.println(ans);
	}

	private static String longestPalindromeSubseq3(String s) {
		char[] arr = s.toCharArray();
		int n = arr.length;
		char[] reversed = new char[n];
		for (int i = 0; i < n; i++) reversed[i] = arr[n - 1 - i];

		int[][] dp = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (arr[i - 1] == reversed[j - 1]) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
				}
			}
		}
		int len = dp[n][n];
		// extension of the problem
		char[] answer = new char[len];
		int n1 = n, n2 = n;
		while (n1 != 0 && n2 != 0) {
			if (arr[n1 - 1] == reversed[n2 - 1]) {
				answer[--len] = arr[n1 - 1];
				n1--;
				n2--;
			} else {
				if (dp[n1 - 1][n2] > dp[n1][n2 - 1]) n1--;
				else n2--;
			}
		}

		return new String(answer);
	}

	// if we think closely, then we will see that the common subsequence of
	// the string and its reverse will be largest palindromic subsequence
	private static void type2() {
		String str1 = "ynabcdbxmn";
		String sb = longestPalindromeSubseq2(str1);
		System.out.println(sb);
	}

	private static String longestPalindromeSubseq2(String str1) {
		String str2 = new StringBuilder().append(str1).reverse().toString();
		int n = str1.length();
		int[][] dp = new int[n + 1][n + 1];
		// now we will fill up the entire dp array
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++)
				if (str1.charAt(i - 1) == str2.charAt(j - 1))
					dp[i][j] = 1 + dp[i - 1][j - 1];
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
		}
		int count = dp[n][n];
		System.out.println(count);

		// this is extension of the problem
		int n1 = n, n2 = n;
		StringBuilder sb = new StringBuilder();
		// we will do the same what we have done for printing the longest common subsequence
		// we will start from n1 and n2 and go backtracking till one of the strings is consumed entirely.
		// as it is a palindromic subsequence, so we do not need to reverse the string
		while (n1 != 0 && n2 != 0) {
			// string is matched so we decrement both n1 and n2
			if (str1.charAt(n1 - 1) == str2.charAt(n2 - 1)) {
				sb.append(str1.charAt(n1 - 1));
				n1--;
				n2--;
			} else {
				// string is not matched, so we will go to n1 or n2
				// based on Math.max(dp[i - 1][j], dp[i][j - 1]);
				if (dp[n1 - 1][n2] > dp[n1][n2 - 1])
					n1--;
				else n2--;
			}
		}
		return sb.toString();
	}
}
