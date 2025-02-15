package com.problems.dp;

/*
 * Problem link :
 * https://leetcode.com/contest/weekly-contest-406/problems/minimum-cost-for-cutting-cake-i/description/
 * Course Schedule:
 *
 *
 * Solution link :
 */
public class MinimumCostForCuttingCakeI {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // todo this is incomplete
    private static void type2() {
        int m = 3, n = 2;
        int[] horizontalCut = {1, 3}, verticalCut = {5};
        int[][][][] dp = new int[m + 1][m + 1][n + 1][n + 1];
        for (int i1 = m - 1; i1 >= 0; i1--) {
            for (int i2 = i1 + 2; i2 <= m; i2++) {
                int min = Integer.MAX_VALUE;
                for (int d1 = i1 + 1; d1 < i2; d1++) {
                    for (int j1 = n - 1; j1 >= 0; j1--) {
                        for (int j2 = j1 + 2; j2 <= n; j2++) {

                            for (int d2 = j1 + 1; d2 < j2; d2++) {
                                int cost = horizontalCut[d1 - 1] + verticalCut[d2 - 1] + dp[i1][i2][j1][d2] + dp[i1][i2][d2][j2];
                                min = Math.min(min, cost);
                            }
                        }
                    }
                }
                dp[i1][i2][0][n] = min;
            }
        }
        System.out.println(dp[0][m][0][n]);
    }

    // recursion with memoization
    // check the question first then the intuition
    private static void type1() {
        int m = 3, n = 2;
        int[] horizontalCut = {1, 3}, verticalCut = {5};
        int[][][][] dp = new int[m + 1][m + 1][n + 1][n + 1];
        int ans = minimumCost(0, m, 0, n, horizontalCut, verticalCut, dp);
        System.out.println(ans);
    }

    public static int minimumCost(int i, int m, int j, int n, int[] horizontalCut, int[] verticalCut, int[][][][] dp) {
        if (i == m - 1 && j == n - 1) return 0;
        if (dp[i][m][j][n] != 0) return dp[i][m][j][n];
        int min = Integer.MAX_VALUE;

        if (i != m - 1) {
            for (int d = i + 1; d < m; d++) {
                int cost = horizontalCut[d - 1]
                        + minimumCost(i, d, j, n, horizontalCut, verticalCut, dp)
                        + minimumCost(d, m, j, n, horizontalCut, verticalCut, dp);
                min = Math.min(min, cost);
            }
        }

        if (j != n - 1) {
            for (int d = j + 1; d < n; d++) {
                int cost = verticalCut[d - 1]
                        + minimumCost(i, m, j, d, horizontalCut, verticalCut, dp)
                        + minimumCost(i, m, d, n, horizontalCut, verticalCut, dp);
                min = Math.min(min, cost);
            }
        }

        return dp[i][m][j][n] = min;
    }
}
