package com.problems.dp;

import java.util.Arrays;

/*
 *
 * problem links :
 * https://leetcode.com/problems/climbing-stairs/description/
 * https://neetcode.io/problems/climbing-stairs
 * https://www.naukri.com/code360/problems/count-ways-to-reach-the-n-th-stairs_798650
 *
 * Solution link :
 * https://www.youtube.com/watch?v=mLfjzJsN8us&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=3
 * https://www.youtube.com/watch?v=Y0lT9Fck7qI
 *
 * https://takeuforward.org/data-structure/dynamic-programming-climbing-stairs/
 * https://neetcode.io/solutions/climbing-stairs
 * */
public class ClimbingStairs {
    // This problem is the same as fibonacci series
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }

    // using tabulation with memory optimization
    // Time complexity O(n)
    // space complexity O(1)
    private static void type4() {
        int n = 10;
        int prev2 = 0, prev = 1, curr;
        for (int i = 2; i <= n; i++) {
            // current is sum of previous and previous to previous
            curr = prev + prev2;
            // after the sum, we will update prev2 and then prev
            prev2 = prev;
            prev = curr;
        }
        System.out.println(prev);
    }

    // using tabulation
    // Time complexity O(n)
    // space complexity O(n) for array
    private static void type3() {
        int n = 10;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = dp[1] = 1;
        // same as previous f(i) = f(i-1) + f(i-2)
        // but as it is bottom up approach,
        // we will start from the lowest input possible
        // we will go till n
        for (int i = 2; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];

        System.out.println(dp[n]);
    }

    // using memoization
    // Time complexity O(n)
    // space complexity O(n) for stack space
    private static void type2() {
        int n = 10;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        int answer = climbStairs(n, dp);
        System.out.println(answer);
    }

    private static int climbStairs(int n, int[] dp) {
        if (n <= 1) return 1;
        // checking if the recursion call is already happened or not
        if (dp[n] != -1) return dp[n];
        // before returning, we will also save the answer
        return dp[n] =
                climbStairs(n - 1) + climbStairs(n - 2);
    }

    // using Recursion
    // Time complexity O(2^n)
    // space complexity O(n) for stack space
    private static void type1() {
        int n = 10;
        int answer = climbStairs(n);
        System.out.println(answer);
    }


    private static int climbStairs(int n) {
        // the base case is when n is 0 and n is 1, because we are doing n-1 and n-2
        // so base case 1 is to tackle n-2 function call,
        // otherwise the call will become f(1-2) => f(-1) which is invalid
        if (n <= 1) return 1;
        return climbStairs(n - 1)
                + climbStairs(n - 2);
    }
}
