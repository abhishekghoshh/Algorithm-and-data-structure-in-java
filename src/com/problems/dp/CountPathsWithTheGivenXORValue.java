package com.problems.dp;

import java.util.HashMap;
import java.util.Map;

/*
 * Problem link:
 * https://leetcode.com/problems/count-paths-with-the-given-xor-value/description/
 *
 * Solution link :
 *
 */
public class CountPathsWithTheGivenXORValue {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    static int mod = (int) 1e9 + 7;

    // same as before top-down approach with the same dp table
    // for every cell, we will go to its right and down and calculate new xor.
    // and check if the new xor is already calculated or not if yes then get the count else take 0
    // and add the count for the previous
    private static void type3() {
        int[][] grid = {};
        int k = 11;
        int ans = countPathsWithXorValue3(grid, k);
        System.out.println(ans);
    }


    static int countPathsWithXorValue3(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        // Create a DP table where each cell contains a HashMap for XOR values
        Map<Integer, Integer>[][] dp = new Map[m][n];

        // Initialize the DP table
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = new HashMap<>();
            }
        }
        // Starting point
        dp[0][0].put(grid[0][0], 1);
        // Fill the DP table
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int xor : dp[i][j].keySet()) {
                    int count = dp[i][j].get(xor);
                    // Move right
                    if (j + 1 < n) {
                        int newXor = xor ^ grid[i][j + 1];
                        int countForNewXor = dp[i][j + 1].getOrDefault(newXor, 0);
                        dp[i][j + 1].put(newXor, (countForNewXor + count) % mod);
                    }
                    // Move down
                    if (i + 1 < m) {
                        int newXor = xor ^ grid[i + 1][j];
                        int countForNewXor = dp[i + 1][j].getOrDefault(newXor, 0);
                        dp[i + 1][j].put(newXor, (countForNewXor + count) % mod);
                    }
                }
            }
        }

        // The result is the number of paths that reach the bottom-right corner with XOR equal to k
        return dp[m - 1][n - 1].getOrDefault(k, 0);
    }

    // same as before, but here we will use dynamic programming
    // before creating and 2D array or map let's see what we need, actually.
    // so there are (m*n) cells, and we can visit any cell in multiple possible ways,
    // and all those ways will generate different xor values for that cell.
    // so even for a single cell, there will be different xor and their total path counts.
    // so in each cell we will create a map
    private static void type2() {
        int[][] grid = {{1, 3, 3, 3}, {0, 3, 3, 2}, {3, 0, 1, 1}};
        int k = 2;
        int ans = countPathsWithXorValue2(grid, k);
        System.out.println(ans);
    }

    public static int countPathsWithXorValue2(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        // m*n cells have their individual maps
        Map<Integer, Integer>[][] dp = new Map[m][n];
        return countPathsWithXorValue(m - 1, n - 1, grid, k, 0, dp);
    }

    static int countPathsWithXorValue(int i, int j, int[][] grid, int k, int xor, Map<Integer, Integer>[][] dp) {
        if (i == -1 || j == -1) return 0;
        // calculating the xor value
        xor = xor ^ grid[i][j];
        // if there is a map for the current cell and xor value is present in that map,
        // then we will return from that
        if (dp[i][j] != null && dp[i][j].containsKey(xor)) return dp[i][j].get(xor);
        // if we have reached to (0,0) and the xor value is k, then we will return 1 else 0
        if (i == 0 && j == 0) {
            return (k == xor) ? 1 : 0;
        }
        // else we will go to the left or go up
        int up = countPathsWithXorValue(i - 1, j, grid, k, xor, dp) % mod;
        int left = countPathsWithXorValue(i, j - 1, grid, k, xor, dp) % mod;
        int val = (up + left) % mod;
        // if the cell is not initialized, then we will initialize and then put the current xor and count value
        if (dp[i][j] == null) dp[i][j] = new HashMap<>();
        dp[i][j].put(xor, val);
        return val;
    }

    // brute force approach using normal recursion
    // rather starting from the (0,0) we will start from
    // we will start from the (m-1,n-1) and go till (0,0)
    private static void type1() {
        int[][] grid = {{1, 3, 3, 3}, {0, 3, 3, 2}, {3, 0, 1, 1}};
        int k = 2;
        int ans = countPathsWithXorValue1(grid, k);
        System.out.println(ans);
    }


    public static int countPathsWithXorValue1(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        Map<Integer, Integer>[][] dp = new Map[m][n];
        return countPathsWithXorValue(m - 1, n - 1, grid, k, 0);
    }

    static int countPathsWithXorValue(int i, int j, int[][] grid, int k, int xor) {
        if (i == -1 || j == -1) return 0;
        xor = xor ^ grid[i][j];
        // if we have reached to (0,0) and the xor value is k, then we will return 1 else 0
        if (i == 0 && j == 0) {
            return (k == xor) ? 1 : 0;
        }
        // else we will go to the left or go up
        int up = countPathsWithXorValue(i - 1, j, grid, k, xor) % mod;
        int left = countPathsWithXorValue(i, j - 1, grid, k, xor) % mod;
        // and return the total possibilities
        return (up + left) % mod;
    }
}
