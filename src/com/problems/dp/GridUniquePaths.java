package com.problems.dp;

/*
 * Problem link :
 * https://leetcode.com/problems/unique-paths/description/
 * https://neetcode.io/problems/count-paths
 * https://www.naukri.com/code360/problems/1081470
 *
 * Solution link
 * https://www.youtube.com/watch?v=t_f0nwwdg5o
 * https://www.youtube.com/watch?v=sdE0A2Oxofw&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=9
 *
 * https://takeuforward.org/data-structure/grid-unique-paths-dp-on-grids-dp8/
 * https://takeuforward.org/data-structure/grid-unique-paths-count-paths-from-left-top-to-the-right-bottom-of-a-matrix/
 * https://neetcode.io/solutions/unique-paths
 *
 * */

// Tags: Arrays, Dynamic Programming, Combination Approach
public class GridUniquePaths {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
	}

	// todo maybe optimal but explain at the last
	// Optimal approach
	// combination approach
	// if m=3 n=2 then sequence can be RRD,RDR,DRR
	// distance traveled is 3
	// if our starting point is 1,1 and ending point is m,n
	// so distance traveled is (m-1 + n-1) => m+n-2 // one step at a time
	// so out of m+n-2 distance it has to go m-1 distance right and n-1 down
	// it has become a problem of combination out of m+n-2 position how many ways
	// (m-1) times R can be placed or how many ways (n-1) times D can be placed
	// which will be (m+n-2) C (m-1)
	// time complexity is O(min(m,n))
	// space complexity is O(1)
	// take either float or double or long
	private static void type4() {
		int m = 19, n = 13;
		int count = uniquePaths5(m, n);
		System.out.println("count is " + count);
	}

	public static int uniquePaths5(int m, int n) {
		long N = m + n - 2;
		long R = m < n ? m - 1 : n - 1;
		long count = 1;
		for (int i = 1; i <= R; i++) {
			count = count * (N - i + 1) / i;
		}
		return (int) count;
	}

	// todo explain this or the memoization in the interview
	// dynamic programming approach iterative way
	// time complexity O(m*n)
	// space complexity O(m*n) + O(max(m,n))
	private static void type3() {
		int m = 3, n = 2;
		int ans = uniquePaths3(m, n);
		System.out.println("count is " + ans);
	}

	private static int uniquePaths3(int m, int n) {
		int[][] dp = new int[m + 1][n + 1];
		// if we are on first row or first column then there is only one way to go (1,1),
		// so initializing first row and the first column with 1
		for (int i = 1; i <= m; i++) dp[i][1] = 1;
		for (int j = 1; j <= n; j++) dp[1][j] = 1;
		// filling up the remaining cell
		for (int i = 2; i <= m; i++) {
			for (int j = 2; j <= n; j++)
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
		}
		return dp[m][n];
	}

	// todo explain this
	// dynamic programming approach
	// recursive way
	// time complexity
	// space complexity O(m*n) + O(max(m,n))
	private static void type2() {
		int m = 3, n = 2;
		int count = uniquePaths2(m, n);
		System.out.println("count is " + count);
	}

	private static int uniquePaths2(int m, int n) {
		int[][] dp = new int[m + 1][n + 1];
		return uniquePaths2(m, n, dp);
	}

	private static int uniquePaths2(int m, int n, int[][] dp) {
		// out of bounds
		if (n == 0 || m == 0) return 0;
		// we have reached the destination
		if (n == 1 && m == 1) return 1;
		// if it is already calculated, then we will directly return the answer
		if (dp[m][n] != 0) return dp[m][n];
		// we will traverse and also store the result
		return dp[m][n] = uniquePaths2(m, n - 1, dp) + uniquePaths2(m - 1, n, dp);
	}

	// brute force approach using recursion
	// time complexity
	// space complexity
	private static void type1() {
		int m = 3, n = 2;
		int count = uniquePaths1(m, n);
		System.out.println("count is " + count);
	}

	private static int uniquePaths1(int m, int n) {
		// out of bounds
		if (n == 0 || m == 0) return 0;
		// we have reached the destination
		if (n == 1 && m == 1) return 1;
		// else we have 2 choices
		return uniquePaths1(m, n - 1) + uniquePaths1(m - 1, n);
	}

}
