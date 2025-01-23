package com.problems.array;

/*
 * problem links:
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
 * https://neetcode.io/problems/buy-and-sell-crypto
 * https://www.naukri.com/code360/problems/893405
 * https://www.naukri.com/code360/problems/best-time-to-buy-and-sell-stock_6194560
 * https://www.naukri.com/code360/problems/stocks-are-profitable_893405
 *
 * Solution link:
 * https://www.youtube.com/watch?v=eMSfBgbiEjk
 * https://www.youtube.com/watch?v=excAOvwF_Wk&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=36
 * https://www.youtube.com/watch?v=1pkOgXD63yU
 *
 * Blogs:
 * https://takeuforward.org/data-structure/stock-buy-and-sell/
 * https://takeuforward.org/data-structure/stock-buy-and-sell-dp-35/
 * https://neetcode.io/solutions/best-time-to-buy-and-sell-stock
 *
 * */

// Tags: Two-Pointers,Sliding-Window
public class BestTimeToBuyAndSellStock1 {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// todo using dynamic programming, don't say it in the interview
	//  here we are storing all profits for all days
	//  not necessarily required here, but it is a good practice to do a solution in a different way
	private static void type3() {
		int[] prices = {7, 11, 4, 1, 2};
		int ans = maxProfit3(prices);
		System.out.println(ans);
	}

	private static int maxProfit3(int[] prices) {
		int n = prices.length;
		int maxProfit = 0;
		// finding for all (0..i) profits
		int[] profits = new int[n];
		// for the first day the profit will be 0
		profits[0] = 0;
		// we will treat 0th day price as the lowest price
		int prevMin = prices[0];
		for (int i = 1; i < n; i++) {
			int price = prices[i];
			// if the current price is less than lowest
			if (price < prevMin) {
				prevMin = price;
			} else {
				profits[i] = price - prevMin;
				maxProfit = Math.max(maxProfit, profits[i]);
			}
		}
		return maxProfit;
	}

	// todo discuss in the interview
	// optimized approach
	// traverse from the last
	// keeps track of the previous highest selling day
	private static void type2() {
		int[] prices = { 7, 1, 5, 3, 6, 4 };
		int max = maxProfit2(prices);
		System.out.println(max);

	}

	private static int maxProfit2(int[] prices) {
		int n = prices.length;
		int maxProfit = 0;
		// storing the previous highest selling price
		int prevMax = 0;
		for (int i = n - 1; i >= 0; i--) {
			int price = prices[i];
			// the current price is greater than the previous price then
			if (price > prevMax) {
				prevMax = price;
			} else {
				// the current price is lower than the previous price then
				// we can set the current index as the buying day as previous highest as the selling day
				// and calculate the profit
				int profit = prevMax - price;
				if (profit > maxProfit) maxProfit = profit;
			}
		}
		return maxProfit;
	}

	// brute force approach
	// for everyday we are checking its next highest day and calculate temporary profits
	// try to check for all i,j where (i < j) and (arr[i] < arr[j])
	// o(n^2) time complexity
	private static void type1() {
		int[] prices = { 7, 1, 5, 3, 6, 4 };
		int max = maxProfit1(prices);
		System.out.println(max);
	}

	private static int maxProfit1(int[] prices) {
		int max = 0;
		int n = prices.length;
		// we will go from the last index and check for each day starting from 0th day to i-1 th day
		// and if jth price < ith price then we can buy at jth and sell at ith day
		for (int i = n - 1; i > 0; i--) {
			// now for ith day we will check for all day starting from 0th to i-1th day
			for (int j = 0; j < i; j++) {
				int profit = prices[i] - prices[j]; // calculating the profit
				// the lowest value of max it 0
				// so the profit has to be greater than 0, to update tha max
				if (profit > max) max = profit;
			}
		}
		return max;
	}
}
