package com.problems.dp;

import com.util.PrintUtl;

/*
 * Problem link :
 * https://www.geeksforgeeks.org/problems/printing-longest-increasing-subsequence/1
 *
 * Solution link :
 * https://www.youtube.com/watch?v=IFfYfonAFGc&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=43
 * https://takeuforward.org/data-structure/printing-longest-increasing-subsequence-dp-42/
 */
public class PrintingLongestIncreasingSubsequence {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // we can use a little optimization
    private static void type2() {
        int[] nums = {10, 9, 2, 5, 3, 7, 11, 18};
        int n = nums.length;

        int[] dp = new int[n];
        int[] prevI = new int[n];

        // for 0 it is LIS is 1 and prev is -1
        dp[0] = 1;
        prevI[0] = -1;
        int maxI = 0;
        // we will start from 1
        for (int i = 1; i < n; i++) {
            // rather, checking all the indices if we can directly
            // check the current index is greater than the maxI, then we can directly increment from maxI
            if (nums[i] > nums[maxI]) {
                dp[i] = dp[maxI] + 1;
                prevI[i] = maxI;
                maxI = i;
            } else {
                int prevMaxI = -1;
                // from i-1 to 0 we will check if the num is greater than prev or not
                // and max len from all the lesser prev value will be our ans
                for (int prev = i - 1; prev >= 0; prev--) {
                    // the current num is greater than previous, and either prevMaxI is still the initial value
                    // or it is less than the current dp value of prev; then we will update the prevMaxI
                    // we can either add <= or < for dp[prevMax] <= dp[prev].
                    // but if they mention Index-wise lexicographically smaller, then we have to add <=
                    if (nums[i] > nums[prev] &&
                            (prevMaxI == -1 || dp[prevMaxI] <= dp[prev]))
                        prevMaxI = prev;
                }
                // len+1 will be the current value of i
                // if there is no lesser previous value then the default len value will be 0
                dp[i] = 1 + (prevMaxI != -1 ? dp[prevMaxI] : 0);
                // we will calculate the maximum LIS index in the same time
                if (dp[maxI] < dp[i]) maxI = i;
                prevI[i] = prevMaxI;
            }
        }
        // we have the index of the longest increasing subsequence
        // and all the previous index
        int len = dp[maxI], i = maxI;
        int[] seq = new int[len];
        while (i != -1) {
            seq[len - 1] = nums[i];
            len--;
            i = prevI[i];
        }
        PrintUtl.print(seq);
    }


    // check the intuition of the Longest increasing subsequence type4
    private static void type1() {
        int[] nums = {10, 9, 2, 5, 3, 7, 11, 18};
        int n = nums.length;

        int[] dp = new int[n];
        int[] prevI = new int[n];

        // for 0 it is LIS is 1 and prev is -1
        dp[0] = 1;
        prevI[0] = -1;
        int maxI = 0;
        // we will start from 1
        for (int i = 1; i < n; i++) {
            int prevMaxI = -1;
            // from i-1 to 0 we will check if the num is greater than prev or not
            // and max len from all the lesser prev value will be our ans
            for (int prev = i - 1; prev >= 0; prev--) {
                // the current num is greater than previous, and either prevMaxI is still the initial value
                // or it is less than the current dp value of prev; then we will update the prevMaxI
                // we can either add <= or < for dp[prevMax] <= dp[prev].
                // but if they mention Index-wise lexicographically smaller, then we have to add <=
                if (nums[i] > nums[prev] &&
                        (prevMaxI == -1 || dp[prevMaxI] <= dp[prev]))
                    prevMaxI = prev;
            }
            // len+1 will be the current value of i
            // if there is no lesser previous value then the default len value will be 0
            dp[i] = 1 + (prevMaxI != -1 ? dp[prevMaxI] : 0);
            prevI[i] = prevMaxI;
            // we will calculate the maximum LIS index in the same time
            if (dp[maxI] < dp[i]) maxI = i;
        }
        // we have the index of the longest increasing subsequence
        // and all the previous index
        int len = dp[maxI], i = maxI;
        int[] seq = new int[len];
        while (i != -1) {
            seq[len - 1] = nums[i];
            len--;
            i = prevI[i];
        }
        PrintUtl.print(seq);
    }
}
