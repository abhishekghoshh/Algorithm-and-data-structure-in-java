package com.problems.dp;

import java.util.Arrays;

/*
 * Problem link :
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/description/
 * https://www.naukri.com/code360/problems/rahul-and-his-chocolates_3118974
 *
 * Solution link :
 * https://www.youtube.com/watch?v=k4eK-vEmnKg&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=41
 *
 * https://takeuforward.org/data-structure/buy-and-sell-stocks-with-transaction-fees-dp-40/
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {

    // This is exactly like the best time for buy and sell problem 2.
    // However, we have add a fee either while buying or selling
    // that is the only change that we need to do for this problem
    // TODO if you are unable to understand the intuition then check buy and sell problem 2
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }

    static int fee = 0;

    // tabulation or top-down approach
    // with space optimization, using 4 variables,
    // and here we will also remove the inner canBuy loop.
    // we have 2 optimizations here, if you have any issues to follow the intuition then check
    // the best time for buy and sell 2
    private static void type4() {
        int[] prices = {1, 3, 2, 8, 4, 9};
        fee = 2;
        int n = prices.length;
        // we will add another day as it was a boundary case in recursion
        int nextBuy = 0, nextSell = 0, currBuy, currSell;
        // we do not need to initiate for the nth day, as the default value of the int array is
        for (int day = n - 1; day >= 0; day--) {
            // we can either buy or skip for that day
            currBuy = Math.max(-prices[day] + nextSell, nextBuy);
            // else means we can sell on that day
            // we can either sell or we can also check for the next day
            // but we are adding a fee here
            currSell = Math.max(-fee + prices[day] + nextBuy, nextSell);
            // update the variables
            nextBuy = currBuy;
            nextSell = currSell;
        }
        // the answer is on dp[0][0] for the first day, and the time is to buy
        int ans = nextBuy;
        System.out.println(ans);
    }

    // tabulation or top-down approach
    private static void type3() {
        int[] prices = {1, 3, 2, 8, 4, 9};
        fee = 2;
        int n = prices.length;
        // we will add another day as it was a boundary case in recursion
        int[][] dp = new int[n + 1][2];
        dp[n][0] = dp[n][1] = 0;
        for (int day = n - 1; day >= 0; day--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                if (canBuy == 0) {
                    // we can either buy or skip for that day
                    dp[day][canBuy] = Math.max(
                            -prices[day] + dp[day + 1][1],
                            dp[day + 1][0]
                    );
                } else {
                    // else means we can sell on that day
                    // we can either sell or we can also check for the next day
                    // but we are adding a fee here
                    dp[day][canBuy] = Math.max(
                            -fee + prices[day] + dp[day + 1][0],
                            dp[day + 1][1]
                    );
                }
            }
        }
        // the answer is on dp[0][0] for the first day, and the time is to buy
        int ans = dp[0][0];
        System.out.println(ans);
    }

    // recursion with memoization
    private static void type2() {
        int[] prices = {1, 3, 2, 8, 4, 9};
        fee = 2;
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int[] row : dp) Arrays.fill(row, -1);
        int ans = maxProfit2(0, 0, prices, dp);
        System.out.println(ans);
    }

    public static int maxProfit2(int day, int canBuy, int[] prices, int[][] dp) {
        // checking if it is out of bounds or not
        if (day == prices.length) return 0;
        if (dp[day][canBuy] != -1) return dp[day][canBuy];
        // true means we can buy on that day
        if (canBuy == 0) {
            // we can either buy or skip for that day
            return dp[day][canBuy] = Math.max(
                    -prices[day] + maxProfit2(day + 1, 1, prices, dp),
                    maxProfit2(day + 1, 0, prices, dp)
            );
        } else {
            // else means we can sell on that day
            // we can either sell or we can also check for the next day
            // but we are adding a fee here
            return dp[day][canBuy] = Math.max(
                    -fee + prices[day] + maxProfit2(day + 1, 0, prices, dp),
                    maxProfit2(day + 1, 1, prices, dp)
            );
        }
    }

    // recursion with brute force
    private static void type1() {
        int[] prices = {1, 3, 2, 8, 4, 9};
        fee = 2;
        int ans = maxProfit1(0, true, prices);
        System.out.println(ans);
    }

    public static int maxProfit1(int day, boolean canBuy, int[] prices) {
        // checking if it is out of bounds or not
        if (day == prices.length) return 0;
        // true means we can buy on that day
        if (canBuy) {
            // we can either buy or skip for that day
            return Math.max(
                    -prices[day] + maxProfit1(day + 1, false, prices),
                    maxProfit1(day + 1, true, prices)
            );
        } else {
            // else means we can sell on that day
            // we can either sell or we can also check for the next day,
            // but we are adding a fee here
            return Math.max(
                    -fee + prices[day] + maxProfit1(day + 1, true, prices),
                    maxProfit1(day + 1, false, prices)
            );
        }
    }
}
