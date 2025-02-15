package com.problems.dp;

import java.util.Arrays;

/*
 * Problem link :
 * https://leetcode.com/problems/coin-change/description/
 * https://neetcode.io/problems/coin-change
 * https://www.naukri.com/code360/problems/minimum-elements_3843091
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=I-l6PBeERuc&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=16
 * https://www.youtube.com/watch?v=rMfOgY07TFs&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=17
 *
 * https://www.youtube.com/watch?v=myPeWb3Y68A&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=21
 *
 * https://www.youtube.com/watch?v=H9bfqozjoqs
 *
 * https://takeuforward.org/data-structure/minimum-coins-dp-20/
 * https://neetcode.io/solutions/coin-change
 */
public class MinimumNoOfCoinsForCoinChange {

	static int INF = (int) 1e9;

	// todo check this problem to have a very good and progressive intuition
	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
		type5();
		type6();
	}

	// todo best possible solution
	// check coin change 2 for better understanding
	// similar to a previous type with some little optimization
	// similar to the problem of [no of way possible for creating amount]
	private static void type6() {
		int[] coins = {1, 2, 3, 4, 5, 9, 11};
		int amount = 12;
		int[] dp = new int[amount + 1];
		// assigning all values to max possibility, (theoretically it is not possible)
		// the lowest coin possible is 1 and to create amount k we will k coin only
		// TODO in place of INF we are using amount+1 as with the minimum amount coin(1) also we need [amount]
		// 	number of coins to make amount, also it will it shows more efficiency in leetcode
		Arrays.fill(dp, amount + 1);
		// for amount 0 minimum numbers of coins needed are 0
		dp[0] = 0;
		// we will loop through for all the coins and for all the amount
		for (int coin : coins) {
			for (int amt = coin; amt <= amount; ++amt)
				// we have 2 options either to include the current coin or not
				// if we are using the current coin, then we have to add +1,
				// and we have to take the minimum out of 2 choices
				dp[amt] = Math.min(
						dp[amt - coin] + 1,
						dp[amt]
				);
		}
		// ideally dp[amount] should be less than amount, if it greater than then we cannot create amount
		int coinsNeeded = (dp[amount] > amount) ? -1 : dp[amount];
		System.out.println(coinsNeeded);
	}

	// tabulation or top-down approach
	private static void type5() {
		int[] coins = {1, 2, 3, 4, 5, 9, 11};
		int amount = 12;
		int minCount = coinChange5(coins, amount);
		System.out.println(minCount);
	}

	public static int coinChange5(int[] coins, int amount) {
		int n = coins.length;
		int[] dp = new int[amount + 1];

		// with 0 coins, we need infinite coins to make any amount (theoretically it is impossible though)
		for (int j = 1; j <= amount; j++) dp[j] = INF;

		// but dp[0] will always be 0, as we can make amount 0 with 0 coins always

		// now we will fill the remaining cells, starting from the 2nd coin and for all the amount
		for (int i = 1; i <= n; i++)
			for (int amt = 1; amt <= amount; amt++)
				// we have 2 options either to include the current coin or not
				// if we are using the current coin, then we have to add +1,
				// and we have to take the minimum out of 2 choices
				if (coins[i - 1] <= amt)
					dp[amt] = Math.min(
							1 + dp[amt - coins[i - 1]],
							dp[amt]
					);

		return dp[amount] <= amount ? dp[amount] : -1;
	}

	// tabulation with space optimization
	// we will use curr and prev array to store the row values
	private static void type4() {
		int[] coins = {1, 2, 3, 4, 5, 9, 11};
		int amount = 12;
		int minCount = coinChange4(coins, amount);
		System.out.println(minCount);
	}

	public static int coinChange4(int[] coins, int amount) {
		int n = coins.length;
		int[] prev = new int[amount + 1];

		// create amount 0 we will need 0 coins
		// with 0 coins, we need infinite coins to make any amount (theoretically it is impossible though).
		for (int j = 1; j <= amount; j++) prev[j] = INF;

		// now we will fill the remaining cells, starting from the 2nd coin and for all the amount
		for (int i = 1; i <= n; i++) {
			int[] curr = new int[amount + 1];
			for (int j = 1; j <= amount; j++)
				// we have 2 options either to include the current coin or not
				// if we are using the current coin, then we have to add +1,
				// and we have to take the minimum out of 2 choices
				if (coins[i - 1] <= j)
					curr[j] = Math.min(
							1 + curr[j - coins[i - 1]],
							prev[j]
					);
				else
					curr[j] = prev[j];
			// assigning curr to the prev
			prev = curr;
		}
		return prev[amount] <= amount ? prev[amount] : -1;
	}

	// tabulation or top-down approach
	private static void type3() {
		int[] coins = {1, 2, 3, 4, 5, 9, 11};
		int amount = 12;
		int minCount = coinChange3(coins, amount);
		System.out.println(minCount);
	}

	public static int coinChange3(int[] coins, int amount) {
		int n = coins.length;
		int[][] dp = new int[n + 1][amount + 1];
		// create amount 0 we will need 0 coins
		for (int i = 0; i <= n; i++) dp[i][0] = 0;

		// with 0 coins, we need infinite coins to make any amount (theoretically it is impossible though)
		for (int j = 1; j <= amount; j++) dp[0][j] = INF;

		// now we will fill the remaining cells, starting from the 2nd coin and for all the amount
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= amount; j++)
				// we have 2 options either to include the current coin or not
				// if we are using the current coin, then we have to add +1,
				// and we have to take the minimum out of 2 choices
				if (coins[i - 1] <= j)
					dp[i][j] = Math.min(
							1 + dp[i][j - coins[i - 1]],
							dp[i - 1][j]
					);
				else
					dp[i][j] = dp[i - 1][j];

		return dp[n][amount] <= amount ? dp[n][amount] : -1;
	}

	// starting point of the problem
	// using recursion with the dynamic programming
	private static void type2() {
		int[] coins = {1, 2, 3, 4, 5, 9, 11};
		int amount = 12;
		int minCount = coinChange2(coins, amount);
		System.out.println(minCount);
	}

	private static int coinChange2(int[] coins, int amount) {
		int n = coins.length;
		int[][] dp = new int[n + 1][amount + 1];
		// initialization
		for (int[] row : dp) Arrays.fill(row, -1);
		int coinsNeeded = coinChange2(n, coins, amount, dp);
		// coins needed can never exceed amount even if only coin with value 1 present
		// then also we would need only (amount) number of coins
		if (coinsNeeded > amount) return -1;
		return coinsNeeded;
	}

	private static int coinChange2(int n, int[] coins, int amount, int[][] dp) {
		// if the amount is 0 at any point, then we will return 0;
		// that means we will need 0 coins only
		if (amount == 0) return 0;
		// if coin length is 0 then we will return infinity, which means we cannot make any amount
		if (n == 0) return INF;
		// if the recursion call is already done, then we will directly return
		if (dp[n][amount] != -1) return dp[n][amount];
		// we have two choices, either to take the current coin (if possible) or not take it
		// if we take it, then we have to increase the coin count
		if (coins[n - 1] <= amount)
			return dp[n][amount] = Math.min(
					1 + coinChange2(n, coins, amount - coins[n - 1], dp),
					coinChange2(n - 1, coins, amount, dp)
			);
		else
			return dp[n][amount] = coinChange2(n - 1, coins, amount, dp);
	}

	// using recursion
	private static void type1() {
	}

}
