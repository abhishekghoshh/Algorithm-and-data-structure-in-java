package com.problems.dp;

import java.util.Arrays;

/*
 * Problem link :
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 * https://www.naukri.com/code360/problems/best-time-to-buy-and-sell-stock_1080698
 *
 * Solution link :
 * https://www.youtube.com/watch?v=IV1dHbk5CDc&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=39
 *
 * https://takeuforward.org/data-structure/buy-and-sell-stock-iv-dp-38/
 */
public class BestTimeToBuyAndSellStock4 {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
        // optimization from the striver's solution
        type5();
        type6();
    }

    // similar to the previous type
    // space optimized version
    // this is very optimized solution
    private static void type6() {
        int k = 2;
        int[] prices = {3, 2, 6, 5, 0, 3};
        int n = prices.length;
        int[] next = new int[2 * k + 1];
        int[] curr = new int[2 * k + 1];
        // initialization


        // filling out all the dp cells
        for (int day = n - 1; day >= 0; day--) {

            for (int transaction = 2 * k - 1; transaction >= 0; transaction--) {
                // odd indices for buying and even for selling
                if (transaction % 2 == 0) {
                    curr[transaction] = Math.max(
                            -prices[day] + next[transaction + 1],
                            next[transaction]
                    );
                } else {
                    curr[transaction] = Math.max(
                            prices[day] + next[transaction + 1],
                            next[transaction]
                    );
                }
            }
//            next = curr;
            // assigning the current to the next for future use
            System.arraycopy(curr, 0, next, 0, 2 * k);
        }
        int ans = next[0];
        System.out.println(ans);

    }

    // check the solutions from Best time for buy and sell 3
    // one rows of the dp array will specify
    // buy1, sell1, buy2, sell2,.....buy-k,sell-k
    // if you have done ith transaction then we will find the dp value of next days i+1th transaction.
    // transactions will spread out from 0 to 2k-1
    // even means you have to buy and odd means to sell
    private static void type5() {
        int k = 2;
        int[] prices = {3, 2, 6, 5, 0, 3};
        int n = prices.length;
        int[][] dp = new int[n + 1][2 * k + 1];
        // initialization
        for (int i = 0; i <= 4; i++) dp[n][i] = 0;
        for (int i = 0; i <= n; i++) dp[i][4] = 0;

        for (int day = n - 1; day >= 0; day--) {
            for (int transaction = 2 * k - 1; transaction >= 0; transaction--) {
                // odd indices for buying and even for selling
                if (transaction % 2 == 0) {
                    dp[day][transaction] = Math.max(
                            -prices[day] + dp[day + 1][transaction + 1],
                            dp[day + 1][transaction]
                    );
                } else {
                    dp[day][transaction] = Math.max(
                            prices[day] + dp[day + 1][transaction + 1],
                            dp[day + 1][transaction]
                    );
                }
            }
        }
        int ans = dp[0][0];
        System.out.println(ans);
    }

    // top-down approach with tabulation
    // with space optimization
    private static void type4() {
        int k = 2;
        int[] prices = {3, 2, 6, 5, 0, 3};
        int n = prices.length;
        // transactions could have 2 values, 0 and 1, -1 means no transactions left
        // we will use 3 loops,
        int[][] next = new int[2][k + 1];

        for (int day = n - 1; day >= 0; day--) {
            int[][] curr = new int[2][k + 1];
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                for (int transaction = 1; transaction <= k; transaction++) {
                    if (canBuy == 0) {
                        // we can either buy or skip for that day
                        curr[canBuy][transaction] = Math.max(
                                -prices[day] + next[1][transaction],
                                next[0][transaction]
                        );
                    } else {
                        // else means we can sell on that day
                        // we can either sell or we can also check for the next day
                        curr[canBuy][transaction] = Math.max(
                                prices[day] + next[0][transaction - 1],
                                next[1][transaction]
                        );
                    }
                }
            }
            next = curr;
        }
        System.out.println(next[0][k]);
    }

    // top-down approach with tabulation
    private static void type3() {
        int k = 2;
        int[] prices = {3, 2, 6, 5, 0, 3};
        int n = prices.length;
        // transactions could have 2 values, 0 and 1, -1 means no transactions left
        // we will use 3 loops,
        int[][][] dp = new int[n + 1][2][k + 1];
        for (int day = n - 1; day >= 0; day--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++)
                for (int transaction = 1; transaction <= k; transaction++)
                    if (canBuy == 0)
                        // we can either buy or skip for that day
                        dp[day][canBuy][transaction] = Math.max(
                                -prices[day] + dp[day + 1][1][transaction],
                                dp[day + 1][0][transaction]
                        );
                    else
                        // else means we can sell on that day
                        // we can either sell or we can also check for the next day
                        dp[day][canBuy][transaction] = Math.max(
                                prices[day] + dp[day + 1][0][transaction - 1],
                                dp[day + 1][1][transaction]
                        );
        }
        System.out.println(dp[0][0][k]);
    }

    // recursion with memoization
    private static void type2() {
        int k = 2;
        int[] prices = {3, 2, 6, 5, 0, 3};
        int ans = maxProfit2(k, prices);
        System.out.println(ans);
    }

    public static int maxProfit2(int k, int[] prices) {
        int n = prices.length;
        // transactions could have 2 values, 0 and 1, -1 means no transactions left
        int[][][] dp = new int[n][2][k];
        // initialization
        for (int[][] grid : dp)
            for (int[] row : grid) Arrays.fill(row, -1);
        return maxProfit2(0, 0, k - 1, prices, dp);
    }

    static int maxProfit2(int day, int canBuy, int transactions, int[] prices, int[][][] dp) {
        // checking if it is out of bounds or not
        if (day == prices.length || transactions == -1) return 0;
        if (dp[day][canBuy][transactions] != -1) return dp[day][canBuy][transactions];
        // 0 means we can buy on that day
        if (canBuy == 0) {
            // we can either buy or skip for that day
            return dp[day][canBuy][transactions] = Math.max(
                    -prices[day] + maxProfit2(day + 1, 1, transactions, prices, dp),
                    maxProfit2(day + 1, 0, transactions, prices, dp)
            );
        } else {
            // else means we can sell on that day
            // we can either sell or we can also check for the next day
            return dp[day][canBuy][transactions] = Math.max(
                    prices[day] + maxProfit2(day + 1, 0, transactions - 1, prices, dp),
                    maxProfit2(day + 1, 1, transactions, prices, dp)
            );
        }
    }

    // brute force recursive solution
    private static void type1() {
        int k = 2;
        int[] prices = {3, 2, 6, 5, 0, 3};
    }
}
