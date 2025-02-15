package com.problems.dp;
/*
 * Problem link:
 * https://leetcode.com/problems/min-cost-climbing-stairs/description/
 * https://neetcode.io/problems/min-cost-climbing-stairs
 *
 * Solution link:
 * https://www.youtube.com/watch?v=ktmzAZWkEZ0
 *
 * https://neetcode.io/solutions/min-cost-climbing-stairs
 */


import java.util.Arrays;

// Tags: Array, Dynamic Programming
public class MinCostClimbingStairs {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }

    // space optimized
    // as we were only dependent on the i-1 and i-2,
    // so we could use 2 variables prev2 and prev and a curr variable
    // and then update prev1 and prev2 on each iteration
    private static void type4() {
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        int ans = minCostClimbingStairs4(cost);
        System.out.println(ans);
    }

    public static int minCostClimbingStairs4(int[] cost) {
        int prev1 = 0, prev2 = 0;
        int n = cost.length;
        for (int i = 2; i <= n; i++) {
            int p1 = prev1 + cost[i - 1];
            int p2 = prev2 + cost[i - 2];
            int curr = Math.min(p1, p2);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    // bottom up approach using same dp array
    // we know one thing that
    // from jumping any index, total cost would be cost[i] + prevCost
    // if we want to jump into any index, we can jump from either from i-1 or i-2,
    // so we will take minimum cost of from prev1 and prev2 indices for the current i
    // we will do this till n as someone could land on n skipping n-1 index
    // so we will start from 2 and end at n
    private static void type3() {
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        int ans = minCostClimbingStairs3(cost);
        System.out.println(ans);
    }

    private static int minCostClimbingStairs3(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            int prev1 = dp[i - 1] + cost[i - 1];
            int prev2 = dp[i - 2] + cost[i - 2];
            dp[i] = Math.min(prev1, prev2);
        }
        return dp[n];
    }

    // same as previous
    // but here we are using a dp array and initialize with -1
    // here also we will start from 0 and 1 and take the minimum
    // in each recursive call we will go either 1 step or 2 step further and take the minimum
    // but before calling any further recursive call we will check the dp array if it already has any value or not
    private static void type2() {
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        int ans = minCostClimbingStairs2(cost);
        System.out.println(ans);
    }

    private static int minCostClimbingStairs2(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return Math.min(
                minCostClimbingStairs2(0, cost, dp),
                minCostClimbingStairs2(1, cost, dp)
        );
    }

    private static int minCostClimbingStairs2(int i, int[] cost, int[] dp) {
        int n = cost.length;
        if (i >= n) return 0;
        if (dp[i] != -1) return dp[i];
        return dp[i] = cost[i] + Math.min(
                minCostClimbingStairs2(i + 1, cost, dp),
                minCostClimbingStairs2(i + 2, cost, dp)
        );
    }

    // brute force approach using recursion
    // we will start from 0 and 1 and take the minimum
    // in each recursive call we will go either 1 step or 2 step further and take the minimum
    private static void type1() {
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        int ans = minCostClimbingStairs1(cost);
        System.out.println(ans);
    }

    private static int minCostClimbingStairs1(int[] cost) {
        return Math.min(
                minCostClimbingStairs1(0, cost),
                minCostClimbingStairs1(1, cost)
        );
    }

    private static int minCostClimbingStairs1(int i, int[] cost) {
        int n = cost.length;
        if (i >= n) return 0;
        return cost[i] + Math.min(
                minCostClimbingStairs1(i + 1, cost),
                minCostClimbingStairs1(i + 2, cost)
        );
    }
}
