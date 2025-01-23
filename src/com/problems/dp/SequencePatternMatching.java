package com.problems.dp;
/*
 * Problem link :
 * 
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=QVntmksK2es&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=30
 * 
 */

public class SequencePatternMatching {
	public static void main(String[] args) {
		type2();
		type3();
	}

	// this is a very optimized approach
	// we can directly use two pointers for pattern matching
	// we will take two variables i and j for each string.
	// and every time characters match we increment the j
	// at the end if the j is the length of the pattern, then we can say the pattern is matched
	private static void type3() {
		String string = "abcdefgha";
		String pattern = "bcfgh";
		int n1 = string.length();
		int n2 = pattern.length();
		int j = 0;
		// going till anyone of them is consumed
		for (int i = 0; i < n1 && j < n2; i++)
			if (string.charAt(i) == pattern.charAt(j)) j++;
		// checking j is same as length
		boolean isPossible = j == n2;
		System.out.println(isPossible);
	}

	// What is a pattern matching
	// if a sequence is present in a string, then we will say it is a match.
	// We do not need consecutive characters; However, the order is needed
	// what is the longest common subsequence, it is the maximum length common subsequence present in
	// two strings, what if the max length is a length of the smaller string.
	// In this scenario, we can say the smaller string is present in the larger string.
	// Now our problem became easier.
	// So if we can find the length of the longest common subsequence for the string and the pattern and the length
	//  is the same as the length of the pattern, then we can say the pattern is matched
	private static void type2() {
		String string = "abcdefgh";
		String pattern = "bcfgh";
		int n1 = string.length();
		int n2 = pattern.length();
		int[][] memo = new int[n1 + 1][n2 + 1];
		for (int i = 1; i <= n1; i++) {
			for (int j = 1; j <= n2; j++)
				if (string.charAt(i - 1) == pattern.charAt(j - 1))
					memo[i][j] = 1 + memo[i - 1][j - 1];
				else
					memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
		}
		// if the length of the pattern is same of lcs
		boolean isPossible = memo[n1][n2] == n2;
		System.out.println(isPossible);
	}
}
