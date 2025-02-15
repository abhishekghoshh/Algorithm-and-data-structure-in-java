package com.problems.dp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/*
 * Problem link :
 * https://www.geeksforgeeks.org/problems/print-all-lcs-sequences3413/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=x5hQvnUcjiM&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=23
 *
 * https://www.youtube.com/watch?v=-zI4mrF2Pb4&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=27
 *
 * https://takeuforward.org/dynamic-programming/striver-dp-series-dynamic-programming-problems
 */
public class PrintLongestCommonSubsequence {
	public static void main(String[] args) {
		type1();
		type2();
	}

	// TODO this solution is quite right but with TreeSet we are getting TLE in gfg, solve it later
	// the previous solution only saves one string,
	// but if there is more than one, then we have to use recursion with backtracking
	private static void type2() {
		String str1 = "abaaa";
		String str2 = "baabaca";
		char[] arr1 = str1.toCharArray();
		char[] arr2 = str2.toCharArray();
		int n1 = arr1.length;
		int n2 = arr2.length;
		int[][] dp = new int[n1 + 1][n2 + 1];

		// now we will fill all the cells of the dp array
		for (int i = 1; i <= n1; i++) {
			for (int j = 1; j <= n2; j++)
				if (arr1[i - 1] == arr2[j - 1])
					dp[i][j] = 1 + dp[i - 1][j - 1];
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
		}
		// same like previous one but here we will use recursion
		Set<String> answer = new TreeSet<>();
		int n = dp[n1][n2];
		char[] arr = new char[n];
		// backtrack to store all the strings
		backtrack(n1, n2, arr1, arr2, dp, arr, n, answer);
		List<String> ans = new ArrayList<>(answer);
		System.out.println(ans);
	}

	private static void backtrack(int n1, int n2, char[] arr1, char[] arr2,
								  int[][] dp, char[] arr, int n, Set<String> answer) {
		if (n1 == 0 || n2 == 0) {
			answer.add(new String(arr));
			return;
		}
		if (arr1[n1 - 1] == arr2[n2 - 1]) {
			arr[n - 1] = arr1[n1 - 1];
			backtrack(n1 - 1, n2 - 1, arr1, arr2, dp, arr, n - 1, answer);
		} else {
			int top = dp[n1 - 1][n2];
			int left = dp[n1][n2 - 1];

			if (top >= left) backtrack(n1 - 1, n2, arr1, arr2, dp, arr, n, answer);

			if (top <= left) backtrack(n1, n2 - 1, arr1, arr2, dp, arr, n, answer);
		}
	}

	private static void type1() {
		String str1 = "abcfe";
		String str2 = "abcdef";
		char[] arr1 = str1.toCharArray();
		char[] arr2 = str2.toCharArray();
		int n1 = arr1.length;
		int n2 = arr2.length;
		int[][] dp = new int[n1 + 1][n2 + 1];

		// now we will fill all the cells of the dp array
		for (int i = 1; i <= n1; i++)
			for (int j = 1; j <= n2; j++)
				if (arr1[i - 1] == arr2[j - 1])
					dp[i][j] = 1 + dp[i - 1][j - 1];
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);

		int n = dp[n1][n2];
		char[] str = new char[n];
		// now we will backtrack from n1,n2 to 0,0 and along the way we will add the letters
		while (n1 != 0 && n2 != 0) {
			// if letters match then we will add that to the answer
			if (arr1[n1 - 1] == arr2[n2 - 1]) {
				str[--n] = arr1[n1 - 1];
				n1--;
				n2--;
			} else {
				// if characters do not match, then we have to go either to n1-1 direction or n2-1
				// because dp[i][j] came from Math.max(dp[i - 1][j], dp[i][j - 1]);
				if (dp[n1 - 1][n2] > dp[n1][n2 - 1]) n1--;
				else n2--;
			}
		}
		String lcs = new String(str);
		System.out.println(lcs);
	}
}
