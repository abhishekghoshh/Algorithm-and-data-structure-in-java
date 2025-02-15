package com.problems.dp;

/*
 * Problem link :
 * https://leetcode.com/problems/super-egg-drop/
 * https://www.geeksforgeeks.org/problems/egg-dropping-puzzle-1587115620/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=S49zeUjeUL0&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=42
 * https://www.youtube.com/watch?v=gr2NtY-2QUY&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=43
 * https://www.youtube.com/watch?v=jkygQmOiCCI&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=44
 *
 * https://www.youtube.com/watch?v=UvksR0hR9nA -> best explanation
 *
 * https://www.youtube.com/watch?v=ypz_aflDqBo -> for binary search approach
 * 
 */

// TODO a tricky hard problem
public class EggDropping {
	// You are given k identical eggs, and you have access to a building with n
	// floors labeled from 1 to n.
	// You know that there exists a floor f where 0 <= f <= n such that any egg
	// dropped at a floor higher than f will break, and any egg dropped at or below
	// floor f will not break.
	// Each move, you may take an unbroken egg and drop it from any floor x
	// (where 1 <= x <= n). If the egg breaks, you can no longer use it. However, if
	// the egg does not break, you may reuse it in future moves.
	// Return the minimum number of moves that you need to determine with certainty
	// what the value of f is.
	// TODO check this problem again
	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
		type5();
		type6();
	}

	// TODO check the logic, similar to the previous type just here we are using the 1D array
	private static void type6() {
		int floors = 10;
		int eggs = 2;
		int minAttempts = eggDropping6(eggs, floors);
		System.out.println(minAttempts);
	}

	private static int eggDropping6(int k, int n) {
		int[] dp = new int[k + 1];
		int m;
		for (m = 0; dp[k] < n; m++) {
			for (int x = k; x > 0; x--) {
				dp[x] += 1 + dp[x - 1];
			}
		}
		return m;
	}

	// TODO check the logic
	private static void type5() {
		int floors = 10;
		int eggs = 2;
		int minAttempts = eggDropping5(eggs, floors);
		System.out.println(minAttempts);
	}

	private static int eggDropping5(int k, int n) {
		int[][] dp = new int[k + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= k; j++) {
				dp[j][i] = dp[j - 1][i - 1] + dp[j][i - 1] + 1;
				if (dp[j][i] >= n) return i;
			}
		}
		return -1;
	}

	// similar to previous approach
	// but here we will be using the binary search along with the dp
	private static void type4() {
		int floors = 10;
		int eggs = 2;
		int[][] dp = new int[eggs + 1][floors + 1];
		int minAttempts = eggDropping4(eggs, floors, dp);
		System.out.println(minAttempts);
	}

	public static int eggDropping4(int k, int n, int[][] dp) {
		if (k == 1 || n <= 1) return n;

		if (dp[k][n] != 0) return dp[k][n];

		int low = 1;
		int high = n;
		int min = n;
		// we will loop until both the attempts are minimized
		// we will check between 1 to n floors
		while (low < high) {
			int mid = (low + high) / 2;
			// here egg break
			int attempt1 = eggDropping4(k - 1, mid - 1, dp);
			// here egg didn't break
			int attempt2 = eggDropping4(k, n - mid, dp);
			min = Math.min(min, 1 + Math.max(attempt1, attempt2));
			if (attempt1 < attempt2) {
				low = mid + 1;
			} else if (attempt1 > attempt2) {
				high = mid;
			} else {
				break;
			}
		}
		return dp[k][n] = min;
	}

	// same as previous
	// here we will top-down approach instead of bottom-up approach
	// in simple we will be using the simple iterative approach here
	private static void type3() {
		int floors = 10;
		int eggs = 2;
		int minAttempts = eggDropping3(eggs, floors);
		System.out.println(minAttempts);
	}

	private static int eggDropping3(int k, int n) {
		int[][] dp = new int[k + 1][n + 1];
		for (int i = 1; i <= k; i++) {
			dp[i][1] = 1;
			dp[i][0] = 0;
		}
		for (int j = 1; j <= n; j++) {
			dp[1][j] = j;
		}
		for (int i = 2; i <= k; i++) {
			for (int j = 2; j <= n; j++) {
				dp[i][j] = Integer.MAX_VALUE;
				for (int x = 1; x <= j; x++) {
					int res = 1 + Math.max(dp[i - 1][x - 1], dp[i][j - x]);
					if (res < dp[i][j]) {
						dp[i][j] = res;
					}
				}
			}
		}
		return dp[k][n];
	}


	private static void type2() {
		int floors = 10;
		int eggs = 2;
		int[][] dp = new int[eggs + 1][floors + 1];
		// initialize all the cell with -1
		int minAttempts = eggDropping(eggs, floors, dp);
		System.out.println(minAttempts);
	}

	private static int eggDropping(int k, int n, int[][] dp) {
		if (dp[k][n] != 0) return dp[k][n];

		if (k == 1 || n <= 1) return n;

		int min = Integer.MAX_VALUE;
		for (int i = 1; i <= n; i++) {
			int worstAttempts = Math.max(
					eggDropping(k - 1, i - 1, dp),
					eggDropping(k, n - i, dp)
			);
			if (worstAttempts < min) min = worstAttempts;

		}
		return dp[k][n] = min + 1;
	}

	private static void type1() {
		int floors = 10;
		int eggs = 2;
		int minAttempts = eggDropping(eggs, floors);
		System.out.println(minAttempts);
	}

	private static int eggDropping(int k, int n) {
		// if the egg amount is 1, then we have to check for all the floors to find the critical floor
		// if the floor size is less than 1 then we will have to choose 1 floor to check
		if (k == 1 || n <= 1) return n;

		int min = Integer.MAX_VALUE;
		for (int f = 1; f <= n; f++) {
			int worstAttempts = Math.max(
					eggDropping(k - 1, f - 1),// egg is wasted
					eggDropping(k, n - f) // egg is saved, we can use the same eggs for floors-f
			);
			if (worstAttempts < min) min = worstAttempts;
		}
		// +1 for the current attempt
		return min + 1;
	}
}
