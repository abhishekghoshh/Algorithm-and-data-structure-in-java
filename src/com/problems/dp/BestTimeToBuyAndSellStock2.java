package com.problems.dp;

import java.util.Arrays;

/*
 * Problem link :
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
 * https://www.naukri.com/code360/problems/selling-stock_630282
 *
 * Solution link :
 * https://www.youtube.com/watch?v=nGJmxkUJQGs&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=37
 * https://www.youtube.com/watch?v=3SJ3pUkPQMc
 *
 * https://takeuforward.org/data-structure/buy-and-sell-stock-ii-dp-36/
 * https://neetcode.io/solutions/best-time-to-buy-and-sell-stock-ii
 */

// Tags : Arrays, Dynamic programming
public class BestTimeToBuyAndSellStock2 {

    // todo check all the iterative approach
    public static void main(String[] args) {
        // recursive approach (in the interview go with this)
        type1();
        type2();
        // iterative approach (if possible try to derive it
        type3();
        type4();
        type5();
        type6();
        // this is the iterative way, but it is easy to explain
        type7();
        // todo convert other iterative approaches from (n to 0) to (0 to n), so that it become easy to explain
    }

    // here we will calculate from the start
    // todo try to derive this is the interview
    private static void type7() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int ans = maxProfit7(prices);
        System.out.println(ans);
    }

    public static int maxProfit7(int[] prices) {
        int currSell = 0; // current profit after selling the item
        int currBuy = Integer.MIN_VALUE; // current profit after buying the item
        for (int price : prices) {
            // assigning curr to the prev
            int prevSell = currSell;
            int prevBuy = currBuy;
            // now we are either buying or selling on today's price
            // else we can skip for the day then currSell will be prevSell and currBuy will be prevBuy
            currSell = Math.max(prevSell, prevBuy + price); // selling on the current price
            currBuy = Math.max(prevBuy, prevSell - price); // buying stock on current price
        }
        return currSell;
    }

    // same as previous, we will use 4 variables instead of arrays
    private static void type6() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int ans = maxProfit6(prices);
        System.out.println(ans);
    }

    private static int maxProfit6(int[] prices) {
        int n = prices.length;
        // we will add another day as it was a boundary case in recursion
        int nextBuy = 0, nextSell = 0, currBuy, currSell;
        // we do not need to initiate for the nth day, as the default value of the int array is
        for (int day = n - 1; day >= 0; day--) {
            // we can either buy or skip for that day
            currBuy = Math.max(-prices[day] + nextSell, nextBuy);
            // else means we can sell on that day
            // we can either sell or we can also check for the next day
            currSell = Math.max(prices[day] + nextBuy, nextSell);
            // update the variables
            nextBuy = currBuy;
            nextSell = currSell;
        }
        // the answer is on dp[0][0] for the first day, and the time is to buy
        return nextBuy;
    }

    // same as previous just here we will not use the inner loop of canBuy
    // we will directly use curr[0] and curr[1]
    // we can also use two variables directly to store current and previous buy and sell
    private static void type5() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int n = prices.length;
        // we will add another day as it was a boundary case in recursion
        int[] next = new int[2];
        // we do not need to initiate for the nth day, as the default value of the int array is
        for (int day = n - 1; day >= 0; day--) {
            int[] curr = new int[2];
            // we can either buy or skip for that day
            curr[0] = Math.max(-prices[day] + next[1], next[0]);
            // else means we can sell on that day
            // we can either sell or we can also check for the next day
            curr[1] = Math.max(prices[day] + next[0], next[1]);
            next = curr;
        }
        // the answer is on dp[0][0] for the first day, and the time is to buy
        int ans = next[0];
        System.out.println(ans);
    }

    // top-down approach
    // or iterative approach with tabulation
    // with space optimization
    // time complexity is O(2n)
    // everytime we are creating the array with 2 cells.
    // we could also use either System.arrayCopy or use 4 variables instead
    // TODO we can skip the internal canBuy loop and just add that two lines one after another
    //  curr[0] = ...... and curr[1] = .....
    private static void type4() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int n = prices.length;
        // we will add another day as it was a boundary case in recursion
        int[] next = new int[2];
        // we do not need to initiate for the nth day, as the default value of the int array is
        for (int day = n - 1; day >= 0; day--) {
            int[] curr = new int[2];
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                int profit1;
                // true means we can buy on that day and false we can sell on that day
                if (canBuy == 0) {
                    profit1 = -prices[day] + next[1];
                } else {
                    profit1 = prices[day] + next[0];
                }
                int profit2 = next[canBuy]; // skipping the day
                curr[canBuy] = Math.max(profit1, profit2);
            }
            next = curr;
        }
        // the answer is on dp[0][0] for the first day, and the time is to buy
        int ans = next[0];
        System.out.println(ans);
    }

    // top-down approach
    // or iterative approach with tabulation
    // we are copying the same recurrence relation from the recursion
    // TODO we can skip the internal canBuy loop and just add that two lines one after another
    //  dp[day][0] = ...... and dp[day][1] = .....
    private static void type3() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int ans = maxProfit3(prices);
        System.out.println(ans);
    }

    private static int maxProfit3(int[] prices) {
        int n = prices.length;
        // we will add another day as it was a boundary case in recursion
        int[][] dp = new int[n + 1][2];
        dp[n][0] = dp[n][1] = 0;
        for (int day = n - 1; day >= 0; day--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                int profit1;
                // true means we can buy on that day and false we can sell on that day
                if (canBuy == 0) {
                    profit1 = -prices[day] + dp[day + 1][1]; // buying on the day
                } else {
                    profit1 = prices[day] + dp[day + 1][0]; // selling on the day
                }
                int profit2 = dp[day + 1][canBuy]; // skipping the day
                dp[day][canBuy] = Math.max(profit1, profit2);
            }
        }
        // the answer is on dp[0][0] for the first day, and the time is to buy
        return dp[0][0];
    }


    // todo recursion with memoization
    // lets not use a boolean value for canBuy
    // let's use 0,1 values for canBuy
    // 0 for can buy and 1 for can sell
    private static void type1() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int ans = maxProfit2(prices);
        System.out.println(ans);
    }

    private static int maxProfit2(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        // we are setting can buy as true as the person can buy the first day even
        return maxProfit2(0, 0, prices, dp);
    }

    public static int maxProfit2(int i, int canBuy, int[] prices, int[][] dp) {
        // checking if it is out of bounds or not
        if (i == prices.length) return 0;
        if (dp[i][canBuy] != -1) return dp[i][canBuy];
        int profit1;
        // true means we can buy on that day and false we can sell on that day
        if (canBuy == 0) {
            profit1 = -prices[i] + maxProfit2(i + 1, 1, prices, dp); // buying on the day
        } else {
            profit1 = prices[i] + maxProfit2(i + 1, 0, prices, dp); // selling on the day
        }
        int profit2 = maxProfit2(i + 1, canBuy, prices, dp);
        return dp[i][canBuy] = Math.max(profit1, profit2);
    }

    // todo recursion brute force
    // we have 2 choices for every index,
    // 1. either to buy or sell on that day
    // 2. skip the current day
    // we will use a boolean variable to check if stock can be bought on that day.
    // if we can buy, then we will subtract the price and if we could sell then we will add the price
    private static void type2() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int ans = maxProfit1(prices);
        System.out.println(ans);
    }

    private static int maxProfit1(int[] prices) {
        // we are setting can buy as true as the person can buy the first day even
        return maxProfit1(0, true, prices);
    }

    public static int maxProfit1(int i, boolean canBuy, int[] prices) {
        // checking if it is out of bounds or not
        if (i == prices.length) return 0;
        int profit1;
        // true means we can buy on that day and false we can sell on that day
        if (canBuy) {
            profit1 = -prices[i] + maxProfit1(i + 1, false, prices);// buying on the day
        } else {
            profit1 = prices[i] + maxProfit1(i + 1, true, prices);// selling on the day
        }
        int profit2 = maxProfit1(i + 1, canBuy, prices); // skipping the day
        // we will return the max
        return Math.max(profit1, profit2);
    }
}
