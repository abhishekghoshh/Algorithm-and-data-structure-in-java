package com.problems.dp;

/*
 * Problem link :
 * https://leetcode.com/problems/palindrome-partitioning-ii/
 * https://www.naukri.com/code360/problems/palindrome-partitioning_873266
 * https://www.geeksforgeeks.org/problems/palindromic-patitioning4845/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=szKVpQtBHh8&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=35
 * https://www.youtube.com/watch?v=fOUlNlawdAU&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=36
 * https://www.youtube.com/watch?v=9h10fqkI7Nk&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=37
 *
 * Striver:
 * https://www.youtube.com/watch?v=_H8V5hJUGd0&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=54
 * https://takeuforward.org/data-structure/palindrome-partitioning-ii-front-partition-dp-53/
 */
public class PalindromePartitioning {
	public static void main(String[] args) {
		type1();
		type2();
	}

	// similar to the previous type, but here we will precompute
	// all the palindrome possible beforehand.
	// we will use simple trick to calculate that
	// string(i,j) will be palindrome when arr[i] == arr[j] and
	// string(i+1,j-1) will be palindrome
	private static void type5() {
		String str = "xnitinjk";
		int minCost = minCut5(str);
		System.out.println(minCost);
	}

	public static int minCut5(String s) {
		char[] arr = s.toCharArray();
		int n = arr.length;
		// we will pre-calculate all the palindrome before the actual computation
		boolean[][] pal = new boolean[n][n];

		// Fill the palindrome table
		// first all single characters
		for (int i = 0; i < n; i++) pal[i][i] = true;

		// then two length characters
		for (int i = 0; i < n - 1; i++)
			pal[i][i + 1] = arr[i] == arr[i + 1];

		// then we will check for all length characters strings from 3
		for (int d = 3; d <= n; d++) {
			for (int start = 0; start <= n - d; start++) {
				// finding the last character index
				int end = start + d - 1;
				//  string(i,j) will be palindrome when arr[i] == arr[j] and string(i+1,j-1) will be palindrome
				pal[start][end] = (arr[start] == arr[end])
						&& pal[start + 1][end - 1];
			}
		}
		// now this portion is exactly same as the previous
		// Calculate minimum cuts
		// now with O(n^2) loop we will try to find all the cuts possible
		int[] dp = new int[n + 1];
		dp[n] = 0;
		for (int i = n - 1; i >= 0; i--) {
			// checking if i to n-1 is palindrome or not
			if (pal[i][n - 1]) {
				dp[i] = 0;
				continue;
			}
			// if not, then we will break it
			int minCost = Integer.MAX_VALUE;
			for (int j = i; j < n; j++) {
				if (pal[i][j]) {
					int cost = 1 + dp[j + 1];
					minCost = Math.min(minCost, cost);
				}
			}
			dp[i] = minCost;
		}
		return dp[0];
	}


	// optimizing from the previous type
	// same as the recursive code
	private static void type4() {
		String str = "xnitinjk";
		int minCost = minCut4(str);
		System.out.println(minCost);
	}

	// converting the recursive code into iterative
	private static int minCut4(String str) {
		char[] arr = str.toCharArray();
		int n = arr.length;
		int[] dp = new int[n + 1];
		dp[n] = 0;
		for (int i = n - 1; i >= 0; i--) {
			// checking if i to n-1 is palindrome or not
			if (isPalindrome(arr, i, n - 1)) {
				dp[i] = 0;
				continue;
			}
			// if not then we will break it
			int minCost = Integer.MAX_VALUE;
			for (int j = i; j < n; j++) {
				if (isPalindrome(arr, i, j)) {
					int cost = 1 + dp[j + 1];
					minCost = Math.min(minCost, cost);
				}
			}
			dp[i] = minCost;
		}
		return dp[0];
	}


	// recursion with memoization
	private static void type3() {
		String str = "xnitinjk";
		int minCost = minCut3(str);
		System.out.println(minCost);
	}

	public static int minCut3(String str) {
		char[] arr = str.toCharArray();
		int n = arr.length;
		int[] dp = new int[n];
		return minCost3(arr, 0, n, dp);
	}

	private static int minCost3(char[] arr, int i, int n, int[] dp) {
		if (i == n) return 0;
		// if it is already a palindrome, then we do not need to partition it
		// we will also save if it is a palindrome in the memo
		if (isPalindrome(arr, i, n - 1)) return dp[i] = 0;

		if (dp[i] != 0) return dp[i];
		// otherwise we will split the string
		int min = Integer.MAX_VALUE;
		for (int j = i; j < n; j++) {
			// from the start we will check if it is a palindrome or not
			// if yes, then only we will check for the remaining string
			// which is valid because why should we waste recursion calls
			if (isPalindrome(arr, i, j)) {
				int cost = 1 + minCost3(arr, j + 1, n, dp);
				min = Math.min(min, cost);
			}
		}
		return dp[i] = min;
	}


	// it is called front partition
	// We know that in the worst case, total cuts=(n-1) i.e., size -1.
	// checking only if the 1st part is Palindrome (then it gives zero cuts, else gives k-1 cuts at that
	// particular point), then check for the other part by calling solve function
	private static void type2() {
		String str = "xnitinjk";
		int minCost = minCut2(str);
		System.out.println(minCost);
	}

	public static int minCut2(String str) {
		char[] arr = str.toCharArray();
		int n = arr.length;
		return minCost2(arr, 0, n);
	}

	private static int minCost2(char[] arr, int i, int n) {
		if (i == n) return 0;
		// if it is already a palindrome, then we do not need to partition it
		// we will also save if it is a palindrome in the memo
		if (isPalindrome(arr, i, n - 1)) return 0;
		// otherwise we will split the string
		int min = Integer.MAX_VALUE;
		for (int j = i; j < n; j++) {
			// from the start we will check if it is a palindrome or not
			// if yes, then only we will check for the remaining string
			// which is valid because why should we waste recursion calls
			if (isPalindrome(arr, i, j)) {
				int cost = 1 + minCost2(arr, j + 1, n);
				min = Math.min(min, cost);
			}
		}
		return min;
	}


	// using recursion
	// brute force approach
	private static void type1() {
		String str = "xnitinjk";
		char[] arr = str.toCharArray();
		int n = arr.length;
		int minCost = minCost1(arr, 0, n - 1);
		System.out.println(minCost);
	}

	// we will try to split the string by every index
	// but before that we will check if the current
	private static int minCost1(char[] str, int i, int j) {
		// if it is a single character, then it is always a palindrome
		if (i >= j) return 0;
		// if it is already a palindrome, then we do not need to partition it
		if (isPalindrome(str, i, j)) return 0;

		int min = Integer.MAX_VALUE;
		for (int k = i; k < j; k++) {
			// on every character we will try to break the string and find the cost
			int cost = 1 + minCost1(str, i, k) + minCost1(str, k + 1, j);
			// we will update the min
			min = Math.min(min, cost);
		}
		return min;
	}

	// checking that the current string is palindrome or not
	private static boolean isPalindrome(char[] str, int i, int j) {
		while (i < j)
			if (str[i++] != str[j--])
				return false;
		return true;
	}
}
