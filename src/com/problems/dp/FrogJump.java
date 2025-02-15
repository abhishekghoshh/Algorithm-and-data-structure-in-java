package com.problems.dp;

import java.util.Arrays;

import static java.lang.Math.abs;
import static java.lang.Math.min;
import static java.lang.System.out;

/*
 *
 * problem links :
 * https://www.codingninjas.com/studio/problems/frog-jump_3621012
 * https://atcoder.jp/contests/dp/tasks/dp_a
 *
 * Solution link :
 * https://www.youtube.com/watch?v=EgG3jsGoPvQ&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=4
 *
 * https://takeuforward.org/data-structure/dynamic-programming-frog-jump-dp-3/
 * */
public class FrogJump {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
        type5();
    }

    // using tabulation with memory optimization
    // Time complexity O(n)
    // space complexity O(1)
    // we will compute from 0th to n-1 th height
    // it is the same as previous
    // we just need last and 2nd last value to compute the current
    // we can just hold those two values in 2 variable prev and prev2
    private static void type5() {
        int[] heights = {7, 4, 4, 2, 6, 6, 3, 4};
        int n = heights.length;
        int prev2 = 0, prev = abs(heights[1] - heights[0]), curr;
        // we will be starting from 2 to n-1
        for (int i = 2; i < n; i++) {
            // first, we will calculate energy from the previous,
            // then we will calculate energy from the previous of previous
            // we will take the minimum
            curr = min(
                    abs(heights[i] - heights[i - 1]) + prev,
                    abs(heights[i] - heights[i - 2]) + prev2
            );
            // we will update those 2 variables
            prev2 = prev;
            prev = curr;
        }
        out.println(prev);
    }

    // using tabulation
    // Time complexity O(n)
    // space complexity O(n) for and array
    // we will compute from 0th to n-1 th height
    // memo[0] is 0 because if we are in 0th cell, we don't have to move anywhere
    private static void type4() {
        int[] heights = {7, 4, 4, 2, 6, 6, 3, 4};
        int n = heights.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        dp[1] = abs(heights[1] - heights[0]);
        for (int i = 2; i < n; i++) {
            // first, we are jumping from the previous
            // then we are jumping from the previous of previous
            // then we are taking the minimum from these two values
            dp[i] = min(
                    abs(heights[i] - heights[i - 1]) + dp[i - 1],
                    abs(heights[i] - heights[i - 2]) + dp[i - 2]
            );
        }
        out.println(dp[n - 1]);
    }

    // using Memoization
    // Time complexity O(n)
    // space complexity O(2n) for stack space and array
    private static void type3() {
        int[] heights = {7, 4, 4, 2, 6, 6, 3, 4};
        int n = heights.length;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        int answer = frogJump(n - 1, heights, memo);
        out.println(answer);
    }

    // same as previous just we are saving 0-ith value in a 1D array
    private static int frogJump(int n, int[] heights, int[] memo) {
        // if it is in 0 then it does not need to jump
        if (n == 0) return 0;
        // if it is in 1st position, then there is only 1 way to go to 0
        if (n == 1) return abs(heights[1] - heights[0]);
        // if the recursion is already made, then we will directly return from the dp array
        if (memo[n] != -1) return memo[n];
        // otherwise it can co either n-1 or n-2, and we will save it to dp
        return memo[n] = min(
                abs(heights[n] - heights[n - 1]) + frogJump(n - 1, heights, memo),
                abs(heights[n] - heights[n - 2]) + frogJump(n - 2, heights, memo)
        );
    }

    // using the Recursion
    // same as previous, but here we will do it in a different way
    private static void type2() {
        int[] heights = {7, 4, 4, 2, 6, 6, 3, 4};
        int n = heights.length;
        int answer = frogJump2(n - 1, heights);
        out.println(answer);
    }

    private static int frogJump2(int n, int[] heights) {
        // if it is in 0 then it does not need to jump
        if (n == 0) return 0;
        // if it is in 1st position, then there is only 1 way to go to 0
        if (n == 1) return abs(heights[1] - heights[0]);
        // otherwise it can co either n-1 or n-2
        return min(
                abs(heights[n] - heights[n - 1]) + frogJump2(n - 1, heights),
                abs(heights[n] - heights[n - 2]) + frogJump2(n - 2, heights)
        );
    }

    // using Recursion
    // Time complexity O(2^n)
    // space complexity O(n) for stack space
    private static void type1() {
        int[] heights = {7, 4, 4, 2, 6, 6, 3, 4};
        int n = heights.length;
        int answer = frogJump(n - 1, heights);
        out.println(answer);
    }

    // let's say we are going from n to 0th step
    // the answer will be the same
    // so for any n we can either go n-1 th step or n-2 step
    // and recursion will give me answer of the remaining part
    // now I have to check which is smaller and need to return
    // for n-2 we just need to check if  n>=2
    private static int frogJump(int n, int[] heights) {
        if (n == 0) return 0;
        int prev = abs(heights[n] - heights[n - 1]) + frogJump(n - 1, heights);
        int prev2 = Integer.MAX_VALUE;
        if (n >= 2)
            prev2 = abs(heights[n] - heights[n - 2]) + frogJump(n - 2, heights);
        return min(prev, prev2);
    }
}
