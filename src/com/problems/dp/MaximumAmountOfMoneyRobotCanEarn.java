package com.problems.dp;

import java.util.Arrays;

public class MaximumAmountOfMoneyRobotCanEarn {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // try to optimize it
    private static void type3() {
    }

    // todo same as brute force but using the dynamic programming approach
    private static void type2() {
        int[][] coins = {
                {-7, 12, 12, 13},
                {-6, 19, 19, -6},
                {9, -2, -10, 16},
                {-4, 14, -10, -9}
        };
        int ans = maximumAmount1(coins);
        System.out.println(ans);
    }

    public static int maximumAmount1(int[][] coins) {
        int m = coins.length, n = coins[0].length;
        // The robot has a special ability to neutralize robbers in at most 2 cells on its path,
        // so we will create 3 cells for m * n each
        int[][][] dp = new int[m][n][3];
        // initializing it with the -INF
        for (int[][] g : dp) {
            for (int[] row : g) Arrays.fill(row, Integer.MIN_VALUE);
        }
        // we will start from (0,0)
        return traverse1(0, 0, 2, coins, dp);
    }

    static int traverse1(int i, int j, int chanceLeft, int[][] coins, int[][][] dp) {
        int m = coins.length, n = coins[0].length;
        // if (i,j) is out of bound then we wil return -INF
        if (i == m || j == n) return Integer.MIN_VALUE;
        // if cell is already calculated then we will return that
        if (dp[i][j][chanceLeft] != Integer.MIN_VALUE) return dp[i][j][chanceLeft];
        // current coin on the cell
        int coin = coins[i][j];
        // if we have reached the destination then we will return the coin
        if (i == (m - 1) && j == (n - 1)) {
            // if coin is negative but there are some chances left then will return 0
            if (coin < 0 && chanceLeft > 0) return 0;
            // else we will return whatever coin is there on the cell
            return coin;
        }
        // we will go down and left and find the best answer
        int profit1 = coin + Math.max(
                traverse1(i + 1, j, chanceLeft, coins, dp),
                traverse1(i, j + 1, chanceLeft, coins, dp)
        );
        int profit2 = Integer.MIN_VALUE;
        // if the current coin is negative, but we have some chances left then we can skip the current coin
        if (coin < 0 && chanceLeft > 0) {
            profit2 = Math.max(
                    traverse1(i + 1, j, chanceLeft - 1, coins, dp),
                    traverse1(i, j + 1, chanceLeft - 1, coins, dp)
            );
        }
        // ultimately we will return the max of profit1 and profit2
        return dp[i][j][chanceLeft] = Math.max(profit1, profit2);
    }

    // brute force using the recursion
    private static void type1() {
    }
}
