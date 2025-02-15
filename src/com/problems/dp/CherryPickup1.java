package com.problems.dp;

import java.util.Arrays;

/*
 *
 * problem links :
 * TODO solve cherry pickup 2 first
 * https://leetcode.com/problems/cherry-pickup/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=juJ0j-Otgko -> Backtracking solution
 * https://www.youtube.com/watch?v=ZV0sUzfA7Eg -> DP solution
 *
 * */
public class CherryPickup1 {

    // TODO
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }

    private static void type4() {
        int[][] grid = {
                {0, 1, -1},
                {1, 0, -1},
                {1, 1, 1}
        };
        int count = cherryPickup4(grid);
        System.out.println(count);
    }

    private static int cherryPickup4(int[][] grid) {
        int n = grid.length;
        if (n == 1) return grid[0][0];
        // we could also use Integer[] in place of int[]
        // the default value of the Integer array will be null unlike 0
        // so in the function we could use dp[i][j] != null then return dp[i][j]
        // this will save us some computation of initialization.
        // we could essentially skip that
        int[][][] dp = new int[n][n][n];
        for (int[][] dp1 : dp)
            for (int[] dp2 : dp1) Arrays.fill(dp2, -1);
        return Math.max(cherryPickup4(0, 0, 0, n, grid, dp), 0);
    }

    // how this method works
    // we have started from (0,0) and (0,0),
    // and we have 4 choices, (i+1,j)(i+1,j)
    // (i+1,j)(i,j+1) and (i,j+1)(i+1,j) and at last (i,j+1)(i,j+1)
    // we could notice one thing, on each side we are adding +1.
    // if we could sum up both sides, it will be i1+j1+1 and i2+j2+1,
    // and both are starting from (0,0) and (0,0), and the sum is increasing 1 every time
    // so both sides will be equal, i1+j1+1 == i2+j2+1
    // or j2 = i1+j2 -j2
    // so we could essentially skip one parameter,
    private static int cherryPickup4(int i1, int j1, int i2, int n, int[][] grid, int[][][] dp) {
        // calculating j2
        int j2 = i1 + j1 - i2;
        // checking if it is out of bounds or if any coordinate is on -1
        if (i1 >= n || j1 >= n || i2 >= n || j2 >= n
                || grid[i1][j1] == -1 || grid[i2][j2] == -1)
            return Integer.MIN_VALUE;
        // if the value is already calculated, then we will directly return
        if (dp[i1][j1][i2] != -1) return dp[i1][j1][i2];
        // if they are in the same cell, then we will calculate the cheery once, or we will sum up
        int cherry = (i1 == i2) && (j1 == j2) ?
                grid[i1][j1] :
                (grid[i1][j1] + grid[i2][j2]);
        // if both are on the (n-1,n-1) cell, then we need to return from it
        if (i1 == n - 1 && j1 == n - 1 && j2 == n - 1) return cherry;
        // we have four choices dd dr rd rr
        // we have four choices
        int c1 = cherryPickup4(i1 + 1, j1, i2 + 1, n, grid, dp); // down-down
        int c2 = cherryPickup4(i1 + 1, j1, i2, n, grid, dp); // down-right
        int c3 = cherryPickup4(i1, j1 + 1, i2 + 1, n, grid, dp); // right-down
        int c4 = cherryPickup4(i1, j1 + 1, i2, n, grid, dp); // right-right
        // taking the max out of all choices
        return dp[i1][j1][i2] = cherry + Math.max(Math.max(c1, c2), Math.max(c3, c4));
    }


    private static void type3() {
        int[][] grid = {
                {0, 1, -1},
                {1, 0, -1},
                {1, 1, 1}
        };
        int count = cherryPickup3(grid);
        System.out.println(count);
    }

    private static int cherryPickup3(int[][] grid) {
        int n = grid.length;
        if (n == 1) return grid[0][0];
        int[][][][] dp = new int[n][n][n][n];
        // initializing with -1
        for (int[][][] dp1 : dp)
            for (int[][] dp2 : dp1)
                for (int[] dp3 : dp2) Arrays.fill(dp3, -1);
        return Math.max(cherryPickup3(0, 0, 0, 0, n, grid, dp), 0);
    }

    private static int cherryPickup3(int i1, int j1, int i2, int j2, int n, int[][] grid, int[][][][] dp) {
        // checking if any coordinate is out of bounds or any cell has -1 or not
        if (i1 >= n || j1 >= n || i2 >= n || j2 >= n
                || grid[i1][j1] == -1 || grid[i2][j2] == -1)
            return Integer.MIN_VALUE;
        // if the cell is computed then we will return the answer
        if (dp[i1][j1][i2][j2] != -1) return dp[i1][j1][i2][j2];
        // taking the cherry only once if they both are on the same cell
        int cherry = (i1 == i2) && (j1 == j2) ?
                grid[i1][j1] :
                (grid[i1][j1] + grid[i2][j2]);
        // if they reach the last cell then we will return the cherry
        if (i1 == n - 1 && j1 == n - 1 && j2 == n - 1) return cherry;
        // we have four choices
        int c1 = cherryPickup3(i1 + 1, j1, i2 + 1, j2, n, grid, dp); // down-down
        int c2 = cherryPickup3(i1 + 1, j1, i2, j2 + 1, n, grid, dp); // down-right
        int c3 = cherryPickup3(i1, j1 + 1, i2 + 1, j2, n, grid, dp); // right-down
        int c4 = cherryPickup3(i1, j1 + 1, i2, j2 + 1, n, grid, dp); // right-right
        // taking the max out of all choices
        return dp[i1][j1][i2][j2] = cherry + Math.max(Math.max(c1, c2), Math.max(c3, c4));
    }

    // TODO The intuition is
    //  so we need to go from (0,0) to (n-1,n-1) and then again come to (0,0)
    //  but if we think closely, we could see it is same as 2 guys are going from (0,0) to (n-1,n-1)
    //  at the same time, and collecting the cherries at the same time
    //  so we will take 2 set of variables (i1,j1) and (i2,j2) and we will traverse till both go to the (n-1,n-1)
    private static void type2() {
        int[][] grid = {
                {0, 1, -1},
                {1, 0, -1},
                {1, 1, 1}
        };
        int count = cherryPickup2(grid);
        System.out.println(count);
    }

    private static int cherryPickup2(int[][] grid) {
        int n = grid.length;
        if (n == 1) return grid[0][0];
        // the answer could be negative, so we are using the math max function
        // we are starting from (0,0) and (0,0)
        return Math.max(cherryPickup2(0, 0, 0, 0, n, grid), 0);
    }

    private static int cherryPickup2(int i1, int j1, int i2, int j2, int n, int[][] grid) {
        // checking if any coordinate is out of bounds or any cell has -1 or not
        if (i1 >= n || j1 >= n || i2 >= n || j2 >= n
                || grid[i1][j1] == -1 || grid[i2][j2] == -1)
            return Integer.MIN_VALUE;
        // taking the cherry only once if they both are on the same cell
        int cherry = (i1 == i2) && (j1 == j2) ?
                grid[i1][j1] :
                (grid[i1][j1] + grid[i2][j2]);
        // if they reach the last cell then we will return the cherry
        if (i1 == n - 1 && j1 == n - 1 && j2 == n - 1) return cherry;
        // we have four choices
        int c1 = cherryPickup2(i1 + 1, j1, i2 + 1, j2, n, grid);
        int c2 = cherryPickup2(i1 + 1, j1, i2, j2 + 1, n, grid);
        int c3 = cherryPickup2(i1, j1 + 1, i2 + 1, j2, n, grid);
        int c4 = cherryPickup2(i1, j1 + 1, i2, j2 + 1, n, grid);
        // taking the max out of all choices
        return cherry + cherry + Math.max(Math.max(c1, c2), Math.max(c3, c4));
    }


    // TODO using backtracking, but this solution was failing at the leetcode
    //  First we will go to n-1,n-1 cell if we reach there we will start another function to 0,0
    //  classic backtracking problem
    private static void type1() {
        int[][] grid = {
                {1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 1},
                {1, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1}
        };
        int count = cherryPickup1(grid);
        System.out.println(count);
    }

    static int max = 0;

    private static int cherryPickup1(int[][] grid) {
        int n = grid.length;
        if (n == 1) return grid[0][0];
        cherryPickup1(0, 0, n, grid, 0);
        return max;
    }

    private static void cherryPickup1(int i, int j, int n, int[][] grid, int totalCherry) {
        if (i >= n || j >= n || grid[i][j] == -1) return;
        int cherry = grid[i][j];
        grid[i][j] = 0;
        // once we reach the cell, we will go to 0,0 again
        if (i == n - 1 && j == n - 1) {
            reverseCherryPickup1(i, j, grid, totalCherry + cherry);
            return;
        }
        cherryPickup1(i + 1, j, n, grid, totalCherry + cherry);
        cherryPickup1(i, j + 1, n, grid, totalCherry + cherry);
        grid[i][j] = cherry;
    }

    private static void reverseCherryPickup1(int i, int j, int[][] grid, int totalCherry) {
        if (i < 0 || j < 0 || grid[i][j] == -1) return;
        int cherry = grid[i][j];
        grid[i][j] = 0;
        if (i == 0 && j == 0) {
            max = Math.max(max, totalCherry + cherry);
            return;
        }
        reverseCherryPickup1(i - 1, j, grid, totalCherry + cherry);
        reverseCherryPickup1(i, j - 1, grid, totalCherry + cherry);
        grid[i][j] = cherry;
    }
}
