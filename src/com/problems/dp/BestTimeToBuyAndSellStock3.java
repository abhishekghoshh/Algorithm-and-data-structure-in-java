package com.problems.dp;

/*
 * Problem link :
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 *
 * Solution link :
 *
 *
 */
public class BestTimeToBuyAndSellStock3 {

    public static void main(String[] args) {
        type1();
        type2();
    }

    // for the previous type, the only thing we are computing again and again is o..i-1 profit
    // which is making the time complexity O(n^2)
    // if we can calculate and store the value of the profits, then we can query the profit in O(1) time
    // we will store the max profits from left side(0..i) and right side(i+1,n-1) both
    // then we will simply run a loop and query the profits in that range and return the total profit
    private static void type2() {
        int[] prices = {7, 11, 4, 1, 2};
        int answer = maxProfit3(prices);
        System.out.println(answer);
    }

    private static int maxProfit3(int[] prices) {
        int n = prices.length;
        if (n == 1) return 0;
        int[] leftProfit = new int[n];
        leftProfit[0] = 0;
        int leftMaxProfit = 0, lowest = prices[0];
        for (int i = 1; i < n; i++) {
            if (prices[i] < lowest) {
                lowest = prices[i];
            } else {
                leftMaxProfit = Math.max(leftMaxProfit, prices[i] - lowest);
            }
            leftProfit[i] = leftMaxProfit;
        }

        int[] rightProfit = new int[n];
        rightProfit[n - 1] = 0;
        int rightMaxProfit = 0, highest = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (highest < prices[i]) {
                highest = prices[i];
            } else {
                rightMaxProfit = Math.max(rightMaxProfit, highest - prices[i]);
            }
            rightProfit[i] = rightMaxProfit;
        }

        int max = 0;
        for (int i = 1; i < n; i++) {
            int profit1 = leftProfit[i];
            int profit2 = (i <= n - 3) ? rightProfit[i + 1] : 0;
            max = Math.max(max, profit1 + profit2);
        }
        return max;
    }


    // we will split the array and try to find the profit separately
    private static void type1() {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int answer = maxProfit1(prices);
        System.out.println(answer);
    }

    public static int maxProfit1(int[] prices) {
        int n = prices.length;
        if (n == 1) return 0;
        int max = 0;
        for (int i = 1; i < n; i++) {
            int profit1 = maxProfit1(prices, 0, i);
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
