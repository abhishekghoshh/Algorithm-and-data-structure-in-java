package com.problems.dp;

import java.util.Arrays;

/*
 *
 * problem links :
 * https://leetcode.com/problems/minimum-path-sum/description/
 * https://www.naukri.com/code360/problems/minimum-path-sum_985349
 *
 * Solution link :
 * https://www.youtube.com/watch?v=_rgTlyky1uQ&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=11
 * https://www.youtube.com/watch?v=pGMsrvt0fpk
 *
 * https://takeuforward.org/data-structure/minimum-path-sum-in-a-grid-dp-10/
 * https://neetcode.io/solutions/minimum-path-sum
 * */
public class MinimumPathSum {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }

    // todo we can space optimize by using a single dimensional dp array, but I feel that is unnecessary
    // exactly like the previous type just here we are initializing the 0th row and column
    // so that we do not need to check fot the out of bounds
    // we will start from (0,0) and go till (m-1,n-1)
    private static void type4() {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int ans = minPathSum4(grid);
        System.out.println(ans);
    }

    private static int minPathSum4(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        // we will initialize dp[0][0] with grid[0][0]
        dp[0][0] = grid[0][0];
        // prefix sum for first column
        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }
        // prefix sum for first row
        for (int i = 1; i < n; i++) {
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }
        // we will go from the 1 to n-1,
        // so we do not have to check for the out of bounds
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++)
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
        }
        return dp[m - 1][n - 1];
    }

    // bottom-up approach
    private static void type3() {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int ans = minPathSum3(grid);
        System.out.println(ans);
    }

    private static int minPathSum3(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        // we will go from the 0 to n-1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                } else {
                    // same as the recursion also checking if i-1 or j-1 is not out of bounds
                    int up = (i > 0) ? dp[i - 1][j] : Integer.MAX_VALUE;
                    int left = (j > 0) ? dp[i][j - 1] : Integer.MAX_VALUE;
                    dp[i][j] = grid[i][j] + Math.min(up, left);
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    // recursion with memoization
    private static void type2() {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int ans = minPathSum2(grid);
        System.out.println(ans);
    }

    private static int minPathSum2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        return minPathSum(grid, m - 1, n - 1, dp);
    }

    private static int minPathSum(int[][] grid, int m, int n, int[][] dp) {
        // n or m is out of bounds
        if (m < 0 || n < 0) return Integer.MAX_VALUE;
        // if we reach to the 0,0 then we will return the answer
        if (m == 0 && n == 0) return grid[m][n];
        // if the cell value is already calculated, then we will return
        if (dp[m][n] != -1) return dp[m][n];
        // else we have 2 choices, to go up or left, and take the minimum among them
        // also store the result
        return dp[m][n] = grid[m][n] + Math.min(
                minPathSum(grid, m - 1, n, dp),
                minPathSum(grid, m, n - 1, dp)
        );
    }

    // brute force with recursion
    // intuition is very simple we will start from m-1,n-1 and go till 0,0
    // for every cell we have 2 options either to go left or up and we will take the min
    private static void type1() {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        int ans = minPathSum1(grid);
        System.out.println(ans);
    }

    private static int minPathSum1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        return minPathSum(grid, m - 1, n - 1);
    }

    private static int minPathSum(int[][] grid, int m, int n) {
        // n or m is out of bounds
        if (m < 0 || n < 0) return Integer.MAX_VALUE;
        // if we reach to the 0,0 then we will return the answer
        if (m == 0 && n == 0) return grid[m][n];
        // else we have 2 choices, to go up or left, and take the minimum among them
        return grid[m][n] + Math.min(
                minPathSum(grid, m - 1, n),
                minPathSum(grid, m, n - 1)
        );
    }
}
