package com.problems.dp;

import java.util.Arrays;

/*
 * Problem link :
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 * https://www.naukri.com/code360/problems/buy-and-sell-stock_1071012
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=-uQGzhYj8BQ&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=38
 *
 * https://takeuforward.org/data-structure/buy-and-sell-stock-iii-dp-37/
 */
public class BestTimeToBuyAndSellStock3 {

    public static void main(String[] args) {
        // these 2 are my own solutions2
        type1();
        type2();
        // striver solutions are starting
        type3();
        type4();
        type5();
        type6();
        // optimized from striver's solution
        type7();
        type8();
        type9();
    }

    // top solution from the leetcode
    private static void type9() {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int answer = maxProfit9(prices);
        System.out.println(answer);
    }

    public static int maxProfit9(int[] prices) {
        int best_time_to_buy1 = Integer.MAX_VALUE, reInvestMentCost = Integer.MAX_VALUE;
        int best_profit_1 = 0, finalProfit = 0;

        for (int price : prices) {
            best_time_to_buy1 = Math.min(best_time_to_buy1, price);
            best_profit_1 = Math.max(best_profit_1, price - best_time_to_buy1);
            reInvestMentCost = Math.min(reInvestMentCost, price - best_profit_1);
            finalProfit = Math.max(finalProfit, price - reInvestMentCost);
        }

        return finalProfit;
    }

    // similar to the previous type
    // just a optimization from previous
    private static void type8() {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int n = prices.length;
        int[] next = new int[5];
        int[] curr = new int[5];
        // initialization


        for (int day = n - 1; day >= 0; day--) {
            for (int transaction = 3; transaction >= 0; transaction--) {
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
            // assigning the current to the next for future use
            System.arraycopy(curr, 0, next, 0, 4);
        }
        int ans = next[0];
        System.out.println(ans);
    }

    // we are using a fix size 2D array, and out of that we need 4 cells only
    // if we can use 4 variables instead of 2d array
    // let's introduce 4 variables, though it is not intuitive at all, so do not tell this in the interview
    // TODO check striver solution and leetcode top solutions one more time
    // another approach is use a 1D array with 4 elements
    // trans1-buy trans1-sell trans2-buy trans2-sell
    // This solution is not also very intuitive
    // check it later one more time
    //
    // one rows of the dp array will specify
    // buy1, sell1, buy2, sell2,.....buy-k,sell-k
    // if you have done ith transaction then we will find the dp value of next days i+1th transaction.
    // transactions will spread out from 0 to 2k-1
    // even means you have to buy and odd means to sell
    private static void type7() {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int n = prices.length;
        int[][] dp = new int[n + 1][5];
        // initialization
        for (int i = 0; i <= 4; i++) dp[n][i] = 0;
        for (int i = 0; i <= n; i++) dp[i][4] = 0;

        for (int day = n - 1; day >= 0; day--) {
            for (int transaction = 3; transaction >= 0; transaction--) {
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

    // top-down approach
    // or iterative approach with tabulation
    // with space optimization
    // time complexity is O(4n)
    // we could also use either System.arrayCopy instead of creating a new array
    private static void type6() {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int n = prices.length;
        // transactions could have 2 values, 0 and 1, -1 means no transactions left
        // we will use 3 loops,
        int[][] next = new int[2][3];
        for (int day = n - 1; day >= 0; day--) {
            int[][] curr = new int[2][3];
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                for (int transaction = 1; transaction <= 2; transaction++) {
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
            // assigning curr to the next
            next = curr;
            // copying current to the next
            // for (int i = 0; i < 2; i++) System.arraycopy(curr[i], 0, next[i], 0, 3);
        }
        System.out.println(next[0][2]);
    }

    // tabulation form of the previous type
    private static void type5() {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int n = prices.length;
        // transactions could have 2 values, 0 and 1, -1 means no transactions left
        // we will use 3 loops,
        int[][][] dp = new int[n + 1][2][3];
        for (int day = n - 1; day >= 0; day--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                for (int transaction = 1; transaction <= 2; transaction++) {
                    if (canBuy == 0) {
                        // we can either buy or skip for that day
                        dp[day][canBuy][transaction] = Math.max(
                                -prices[day] + dp[day + 1][1][transaction],
                                dp[day + 1][0][transaction]
                        );
                    } else {
                        // else means we can sell on that day
                        // we can either sell or we can also check for the next day
                        dp[day][canBuy][transaction] = Math.max(
                                prices[day] + dp[day + 1][0][transaction - 1],
                                dp[day + 1][1][transaction]
                        );
                    }
                }
            }
        }
        System.out.println(dp[0][0][2]);
    }

    // similar to the previous type 
    // recursion with memoization
    private static void type4() {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int n = prices.length;
        // transactions could have 2 values, 0 and 1, -1 means no transactions left
        int[][][] dp = new int[n][2][2];
        // initialization
        for (int[][] grid : dp)
            for (int[] row : grid) Arrays.fill(row, -1);
        int ans = maxProfit4(0, 0, 1, prices, dp);
        System.out.println(ans);
    }

    public static int maxProfit4(int day, int canBuy, int transactions, int[] prices, int[][][] dp) {
        // checking if it is out of bounds or not
        if (day == prices.length || transactions == -1) return 0;
        if (dp[day][canBuy][transactions] != -1) return dp[day][canBuy][transactions];
        // 0 means we can buy on that day
        if (canBuy == 0) {
            // we can either buy or skip for that day
            return dp[day][canBuy][transactions] = Math.max(
                    -prices[day] + maxProfit4(day + 1, 1, transactions, prices, dp),
                    maxProfit4(day + 1, 0, transactions, prices, dp)
            );
        } else {
            // else means we can sell on that day
            // we can either sell or we can also check for the next day
            return dp[day][canBuy][transactions] = Math.max(
                    prices[day] + maxProfit4(day + 1, 0, transactions - 1, prices, dp),
                    maxProfit4(day + 1, 1, transactions, prices, dp)
            );
        }
    }

    // recursion with brute force
    // this is very similar to the buy and sell stock 2 problem
    // there we did not have any limit on how many transactions we can do
    // but here, we have a strict boundary
    private static void type3() {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        // transactions could have 2 values, 0 and 1, -1 means no transactions left
        int ans = maxProfit3(0, true, 1, prices);
        System.out.println(ans);

    }

    public static int maxProfit3(int day, boolean canBuy, int transactions, int[] prices) {
        // checking if it is out of bounds or not
        if (day == prices.length || transactions == -1) return 0;
        // true means we can buy on that day
        // we will decrement the transaction only if there is a buy 
        if (canBuy) {
            // we can either buy or skip for that day
            return Math.max(
                    -prices[day] + maxProfit3(day + 1, false, transactions, prices),
                    maxProfit3(day + 1, true, transactions, prices)
            );
        } else {
            // else means we can sell on that day
            // we can either sell or we can also check for the next day
            return Math.max(
                    prices[day] + maxProfit3(day + 1, true, transactions - 1, prices),
                    maxProfit3(day + 1, false, transactions, prices)
            );
        }
    }

    // TODO below 3 solutions are my own solution
    //  for the previous type, the only thing we are computing again and again is o..i-1 profit
    //  which is making the time complexity as O(n^2)
    //  if we can calculate and store the value of the profits, then we can query the profit in O(1) time
    //  we will store the max profits from left side(0..i) and right side(i+1,n-1) both
    //  then we will simply run a loop and query the profits in that range and return the total profit
    private static void type2() {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int answer = maxProfit2(prices);
        System.out.println(answer);
    }

    private static int maxProfit2(int[] prices) {
        int n = prices.length;
        if (n == 1) return 0;
        // finding for all 0..i profits
        // we are not storing the profit on that day, we are storing the max profit till the day.
        // as we are going from left to right, our action will be choosing lowest for buying and then sell.
        int[] left = new int[n];
        left[0] = 0;
        int max = 0;
        int lowest = prices[0];
        // starting from 1 as we can only buy in that day
        for (int i = 1; i < n; i++) {
            if (prices[i] < lowest) {
                lowest = prices[i];
            } else {
                int profit = prices[i] - lowest;
                max = Math.max(max, profit);
            }
            // storing the max profit
            left[i] = max;
        }
        // finding for all i+1 to n-1 profits.
        // we are not storing the profit on that day, we are storing the max profit till the day.
        // as we are going from right to left, our action will be choosing the highest day for selling
        // and then find the day for buying
        int[] right = new int[n];
        right[n - 1] = 0;
        max = 0;
        int highest = prices[n - 1];
        // starting from n-2 as n-1 th day, we can only sell
        for (int i = n - 2; i >= 0; i--) {
            if (highest < prices[i]) {
                highest = prices[i];
            } else {
                int profit = highest - prices[i];
                max = Math.max(max, profit);
            }
            right[i] = max;
        }
        // now finding left max profit and right max profit simultaneously
        max = 0;
        for (int i = 1; i < n; i++) {
            // profit in the range of 0..i
            int profit1 = left[i];
            // we are considering i+1 as the starting point of the next array
            // we will need at least 2 days in the next array
            // last two indices are n-2 and n-1, we want i+1 <= n-2 or i<=n-3
            int profit2 = (i <= n - 3) ? right[i + 1] : 0;
            max = Math.max(max, profit1 + profit2);
        }
        return max;
    }


    // TODO let's do it in a simple way
    //  we will loop through O to n-1 and take one pointer i
    //  and will calculate the max profit of the range 0..i and i+1 to n-1
    //  we will split the array and try to find the profit separately
    private static void type1() {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int answer = maxProfit1(prices);
        System.out.println(answer);
    }

    public static int maxProfit1(int[] prices) {
        int n = prices.length;
        if (n == 1) return 0;
        int max = 0;
        // looping through the range
        for (int i = 1; i < n; i++) {
            int profit1 = maxProfit1(prices, 0, i);
            // we are considering i+1 as the starting point of the next array
            // we will need at least 2 days in the next array
            // last two indices are n-2 and n-1, we want i+1 <= n-2 or i<=n-3
            int profit2 = (i <= n - 3) ? maxProfit1(prices, i + 1, n - 1) : 0;
            max = Math.max(max, profit1 + profit2);
        }
        return max;
    }

    public static int maxProfit1(int[] prices, int start, int end) {
        int max = 0;
        int highest = 0;
        for (int i = end; i >= start; i--) {
            if (prices[i] > highest) {
                highest = prices[i];
            } else {
                max = Math.max(max, highest - prices[i]);
            }
        }
        return max;
    }

}
