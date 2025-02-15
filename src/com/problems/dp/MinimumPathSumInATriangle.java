package com.problems.dp;

import java.util.Arrays;
import java.util.List;

/*
 *
 * problem links :
 * https://leetcode.com/problems/triangle/description/
 * https://www.naukri.com/code360/problems/triangle_1229398
 *
 * Solution link :
 * https://www.youtube.com/watch?v=SrP-PiLSYC0&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=12
 * https://www.youtube.com/watch?v=OM1MTokvxs4
 *
 * https://takeuforward.org/data-structure/minimum-path-sum-in-triangular-grid-dp-11/
 * https://neetcode.io/solutions/triangle
 * */
public class MinimumPathSumInATriangle {
    public static void main(String[] args) {
        // type1 and type2 are enough for interview
        type1();
        type2();
        // but check type3,type4,type5 for tabulation approach and eventually memory optimization
        type3();
        type4();
        type5();
    }

    // using tabulation or bottom-up approach,
    // but here we will use only 2 arrays for storing current and previous dp values
    private static void type5() {
        List<List<Integer>> triangle = List.of(
                List.of(2),
                List.of(3, 4),
                List.of(6, 5, 7),
                List.of(4, 1, 8, 3)
        );
        int answer = minimumTotal5(triangle);
        System.out.println(answer);
    }

    private static int minimumTotal5(List<List<Integer>> triangle) {
        int n = triangle.size();
        // if n is 1 then we will directly return the answer
        if (n == 1) return triangle.get(0).get(0);
        // Create two arrays to store intermediate results: front and cur
        int[] dp = new int[n]; // Stores the results for the current row
        int[] temp = new int[n];   // Stores the results for the next row

        // Initialize the front array with the values from the bottom row of the triangle
        for (int j = 0; j < n; j++) dp[j] = triangle.get(n - 1).get(j);

        // Starting from the second to last row, calculate the minimum path sum for each element
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                // Calculate the two possible paths: moving down or moving diagonally
                int down = dp[j];
                int diagonal = dp[j + 1];

                // Store the minimum of the two paths in the cur array
                temp[j] = triangle.get(i).get(j) + Math.min(down, diagonal);
            }

            // Update the front array with the values from the cur array for the next row
            dp = temp.clone();
        }

        // The result is stored at the top of the front array
        return dp[0];
    }

    // using tabulation or bottom-up approach
    private static void type4() {
        List<List<Integer>> triangle = List.of(
                List.of(2),
                List.of(3, 4),
                List.of(6, 5, 7),
                List.of(4, 1, 8, 3)
        );
        int answer = minimumTotal4(triangle);
        System.out.println(answer);
    }

    // we will do the same bottom-up approach, but here we will start from the reverse
    private static int minimumTotal4(List<List<Integer>> triangle) {
        int n = triangle.size();
        // if n is 1 then we will directly return the answer
        if (n == 1) return triangle.get(0).get(0);
        int[][] dp = new int[n][n];
        // Initialize the bottom row of dp with the values from the bottom row of the triangle
        for (int j = 0; j < n; j++) dp[n - 1][j] = triangle.get(n - 1).get(j);

        // Starting from the second to last row, calculate the minimum path sum for each element
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                // Calculate the two possible paths: moving down or moving diagonally
                int down = dp[i + 1][j];
                int diagonal = dp[i + 1][j + 1];
                // Store the minimum of the two paths in dp
                dp[i][j] = triangle.get(i).get(j) + Math.min(down, diagonal);
            }
        }
        // The result is stored at the top of dp array
        return dp[0][0];
    }

    // using tabulation or bottom-up approach
    private static void type3() {
        List<List<Integer>> triangle = List.of(
                List.of(2),
                List.of(3, 4),
                List.of(6, 5, 7),
                List.of(4, 1, 8, 3)
        );
        int answer = minimumTotal3(triangle);
        System.out.println(answer);
    }

    private static int minimumTotal3(List<List<Integer>> triangle) {
        int n = triangle.size();
        // if n is 1 then we will directly return the answer
        if (n == 1) return triangle.get(0).get(0);
        int[][] dp = new int[n][n];
        dp[0][0] = triangle.get(0).get(0);
        // unlike recursive way in iteration, we will go in reverse direction
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                int up = (j < i) ? dp[i - 1][j] : Integer.MAX_VALUE; // up
                int dig = (j > 0) ? dp[i - 1][j - 1] : Integer.MAX_VALUE; // diagonally upper left
                dp[i][j] = triangle.get(i).get(j) + Math.min(up, dig);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int cell : dp[n - 1]) if (cell < min) min = cell;
        return min;
    }

    // using recursion and memoization
    private static void type2() {
        List<List<Integer>> triangle = List.of(
                List.of(2),
                List.of(3, 4),
                List.of(6, 5, 7),
                List.of(4, 1, 8, 3)
        );
        int answer = minimumTotal2(triangle);
        System.out.println(answer);
    }

    public static int minimumTotal2(List<List<Integer>> triangle) {
        int n = triangle.size();
        // if n is 1 then we will directly return the answer
        if (n == 1) return triangle.get(0).get(0);
        int[][] dp = new int[n][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        // else we will start from 0,0 to any column in nth row
        return minimumTotal2(0, 0, n, triangle, dp);
    }

    private static int minimumTotal2(int r, int c, int n, List<List<Integer>> triangle, int[][] dp) {
        // if j is greater than the ith row size then it is out of bounds
        List<Integer> row = triangle.get(r);
        if (c >= row.size()) return Integer.MAX_VALUE;
        int curr = row.get(c);
        // i == n-1 means it reaches the last row
        if (r == n - 1) return curr;
        // checking if it is calculated or not
        if (dp[r][c] != -1) return dp[r][c];
        // else we will go to down and down right and take the minimum
        return dp[r][c] = curr + Math.min(
                minimumTotal2(r + 1, c, n, triangle, dp),
                minimumTotal2(r + 1, c + 1, n, triangle, dp));
    }

    // using the recursion
    private static void type1() {
        List<List<Integer>> triangle = List.of(
                List.of(2),
                List.of(3, 4),
                List.of(6, 5, 7),
                List.of(4, 1, 8, 3)
        );
        int answer = minimumTotal1(triangle);
        System.out.println(answer);
    }

    public static int minimumTotal1(List<List<Integer>> triangle) {
        int n = triangle.size();
        // if n is 1 then we will directly return the answer
        if (n == 1) return triangle.get(0).get(0);
        // else we will start from 0,0 to any column in nth row
        return minimumTotal1(0, 0, n, triangle);
    }

    private static int minimumTotal1(int r, int c, int n, List<List<Integer>> triangle) {
        // if j is greater than the ith row size then it is out of bounds
        List<Integer> row = triangle.get(r);
        if (c >= row.size()) return Integer.MAX_VALUE;
        int curr = row.get(c);
        // i == n-1 means it reaches the last row
        if (r == n - 1) return curr;
        // else we will go to down and down right and take the minimum
        return curr + Math.min(
                minimumTotal1(r + 1, c, n, triangle),
                minimumTotal1(r + 1, c + 1, n, triangle)
        );
    }
}
