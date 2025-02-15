package com.problems.dp;

import java.util.Arrays;

/*
 *
 * problem links :
 * https://leetcode.com/problems/minimum-falling-path-sum/description/
 * https://www.naukri.com/code360/problems/maximum-path-sum-in-the-matrix_797998
 *
 * Solution link :
 * https://www.youtube.com/watch?v=N_aJ5qQbYA0&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=13
 * https://www.youtube.com/watch?v=b_F3mz9l-uQ
 *
 * https://takeuforward.org/data-structure/minimum-maximum-falling-path-sum-dp-12/
 * https://neetcode.io/solutions/minimum-falling-path-sum
 * */
public class MinimumFallingPathSum {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // bottom up or iterative approach
    // we will use the same recurrence relation
    private static void type3() {
        int[][] matrix = {{2, 1, 3}, {6, 5, 4}, {7, 8, 9}};
        int answer = minFallingPathSum3(matrix);
        System.out.println(answer);
    }

    // from the question, it is clear that we could either go from the start or go from the n-1
    // and the choices will be the same.
    public static int minFallingPathSum3(int[][] matrix) {
        int n = matrix.length;
        if (n == 1) return matrix[0][0];
        int[][] dp = new int[n][n];
        // copying the first row as the initial value of the dp array
        System.arraycopy(matrix[0], 0, dp[0], 0, n);
        // we could go from n-2 to 0, but here for simplicity we chose the 1 to n-1
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int dig1 = (j > 0) ? dp[i - 1][j - 1] : Integer.MAX_VALUE;// up left
                int dig2 = (j < n - 1) ? dp[i - 1][j + 1] : Integer.MAX_VALUE;// up right
                int up = dp[i - 1][j];// up
                dp[i][j] = matrix[i][j] + Math.min(up, Math.min(dig1, dig2));
            }
        }
        int min = Integer.MAX_VALUE;
        for (int val : dp[n - 1])
            min = Math.min(min, val);
        return min;
    }

    // same as previous but here we will use memoization
    private static void type2() {
        int[][] matrix = {{2, 1, 3}, {6, 5, 4}, {7, 8, 9}};
        int answer = minFallingPathSum2(matrix);
        System.out.println(answer);
    }

    private static int minFallingPathSum2(int[][] matrix) {
        int n = matrix.length;
        if (n == 1) return matrix[0][0];
        // instead of int[] we will use Integer[]
        int[][] dp = new int[n][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        int min = Integer.MAX_VALUE;
        // we will start from all the cells in the first row
        for (int j = 0; j < n; j++)
            min = Math.min(min, minFallingPathSum2(0, j, n, matrix, dp));
        return min;
    }

    private static int minFallingPathSum2(int i, int j, int n, int[][] matrix, int[][] dp) {
        // if we are out of bounds then we will max value
        if (j < 0 || j >= n) return Integer.MAX_VALUE;
        // it means it has reached to the last row; column can be anything
        if (i == n - 1) return matrix[i][j];
        // it means cell is already calculated
        if (dp[i][j] != -1) return dp[i][j];
        // we have 3 options to choose from
        int dig1 = minFallingPathSum2(i + 1, j - 1, n, matrix, dp); // down left
        int dig2 = minFallingPathSum2(i + 1, j + 1, n, matrix, dp); // down right
        int down = minFallingPathSum2(i + 1, j, n, matrix, dp); // down
        // we will take the min out of these
        return dp[i][j] = matrix[i][j] + Math.min(down, Math.min(dig1, dig2));
    }

    // it is very time-consuming to do it in recursion
    // from the first row we will go to three direction (left-down, down, right-down)
    // as answer could lie on n * n options
    // n for the first any cell on the first row, and n for any cell on the last row.
    // todo the memoization will come automatically in our mind.
    // let alone the answer has n * n options, we have also the recursion tree time and space
    private static void type1() {
        int[][] matrix = {{2, 1, 3}, {6, 5, 4}, {7, 8, 9}};
        int answer = minFallingPathSum1(matrix);
        System.out.println(answer);
    }

    private static int minFallingPathSum1(int[][] matrix) {
        int n = matrix.length;
        if (n == 1) return matrix[0][0];
        int min = Integer.MAX_VALUE;
        // we will start from all the cells in the first row
        for (int j = 0; j < n; j++)
            min = Math.min(min, minFallingPathSum1(0, j, n, matrix));
        return min;
    }

    private static int minFallingPathSum1(int i, int j, int n, int[][] matrix) {
        // if we are out of bounds then we will max value
        if (j < 0 || j >= n) return Integer.MAX_VALUE;
        // it means it has reached to the last row; column can be anything
        if (i == n - 1) return matrix[i][j];
        // we have 3 options to choose from
        int dig1 = minFallingPathSum1(i + 1, j - 1, n, matrix); // down left
        int dig2 = minFallingPathSum1(i + 1, j + 1, n, matrix); // down right
        int down = minFallingPathSum1(i + 1, j, n, matrix); // down
        // we will take the min out of these
        return matrix[i][j] + Math.min(down, Math.min(dig1, dig2));
    }
}
