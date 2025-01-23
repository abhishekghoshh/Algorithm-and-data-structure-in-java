package com.problems.dp;

import java.util.Arrays;

/*
 * problem links :
 * https://leetcode.com/problems/unique-paths-ii/description/
 * https://www.naukri.com/code360/problems/maze-obstacles_977241
 *
 * Solution link :
 * https://www.youtube.com/watch?v=TmhpgXScLyY&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=10
 * https://www.youtube.com/watch?v=d3UOz7zdE4I
 *
 * https://takeuforward.org/data-structure/grid-unique-paths-2-dp-9/
 * https://neetcode.io/solutions/unique-paths-ii
 * */
public class GridUniquePathsWithObstacles {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }

    // same bottom-up approach with some initialization
    // here we will first initialize the first row and column separately then we will calculate for the remaining cells
    private static void type4() {
        int[][] obstacleGrid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        int ans = uniquePathsWithObstacles4(obstacleGrid);
        System.out.println(ans);
    }

    private static int uniquePathsWithObstacles4(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1) return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        // there is always a way for the first cell as we are standing right there
        dp[0][0] = 1;
        // for the first column if there is any obstacle in the cell we will break the loop, so remaining values will remain as 0
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 1) break;
            dp[i][0] = 1;
        }
        // for the first row if there is any obstacle in the cell we will break the loop, so remaining values will remain as 0
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[0][i] == 1) break;
            dp[0][i] = 1;
        }
        // let's fill up the remaining places
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // if there is any obstacle then we will not set the value of the dp
                if (obstacleGrid[i][j] == 1) continue;
                // else we will have 2 choices to take
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    // tabulation or the bottom-up approach
    // here we are converting the recursion into iterative approach
    // keeping the same recurrence relation
    private static void type3() {
        int[][] obstacleGrid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        int ans = uniquePathsWithObstacles3(obstacleGrid);
        System.out.println(ans);
    }

    private static int uniquePathsWithObstacles3(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1) return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        // as there is no initialization,
        // so we will start from (0,0), and go till (n-1,m-1)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // for the (0,0) cell it is always 1 as it is the base case
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else if (obstacleGrid[i][j] == 1) {
                    // if there is an obstacle, then we will make dp value as 0
                    dp[i][j] = 0;
                } else {
                    // else we have 2 options either up or left
                    dp[i][j] += i > 0 ? dp[i - 1][j] : 0;
                    dp[i][j] += j > 0 ? dp[i][j - 1] : 0;
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    private static void type2() {
        int[][] obstacleGrid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int ans = uniquePathsWithObstacles2(obstacleGrid);
        System.out.println(ans);
    }

    private static int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1) return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        return uniquePathsWithObstacles(obstacleGrid, m - 1, n - 1, dp);
    }

    private static int uniquePathsWithObstacles(int[][] obstacleGrid, int m, int n, int[][] dp) {
        // if it is out of bounds or there is an obstacle, then we will return 0
        if (m < 0 || n < 0 || obstacleGrid[m][n] == 1) return 0;
        // if we reached the (0,0) then we will return 1 as this is our base case
        if (m == 0 && n == 0) return 1;
        // checking if the cell is already calculated or not
        if (dp[m][n] != -1) return dp[m][n];
        // we have 2 choices, either to go left or go up
        return dp[m][n] = uniquePathsWithObstacles(obstacleGrid, m - 1, n, dp)
                + uniquePathsWithObstacles(obstacleGrid, m, n - 1, dp);
    }

    private static void type1() {
        int[][] obstacleGrid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        int ans = uniquePathsWithObstacles1(obstacleGrid);
        System.out.println(ans);
    }

    private static int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1) return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        return uniquePathsWithObstacles1(obstacleGrid, m - 1, n - 1);
    }

    private static int uniquePathsWithObstacles1(int[][] obstacleGrid, int m, int n) {
        // if it is out of bounds or there is an obstacle, then we will return 0
        if (m < 0 || n < 0 || obstacleGrid[m][n] == 1) return 0;
        // if we reached the (0,0) then we will return 1 as this is our base case
        if (m == 0 && n == 0) return 1;
        // we have 2 choices, either to go left or go up
        return uniquePathsWithObstacles1(obstacleGrid, m - 1, n)
                + uniquePathsWithObstacles1(obstacleGrid, m, n - 1);
    }
}
