package com.problems.dp;

/*
 * Problem link :
 * https://leetcode.com/problems/number-of-longest-increasing-subsequence/
 * https://www.naukri.com/code360/problems/number-of-longest-increasing-subsequence_3751627
 *
 * Solution link :
 * https://www.youtube.com/watch?v=cKVl1TFdNXg&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=48
 * https://www.youtube.com/watch?v=Tuc-rjJbsXU
 *
 * https://takeuforward.org/dynamic-programming/striver-dp-series-dynamic-programming-problems/
 * https://neetcode.io/solutions/number-of-longest-increasing-subsequence
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Tags: Array, Dynamic Programming, Binary Indexed Tree, Segment Tree
public class NumberOfLongestIncreasingSubsequence {

    // Given an integer array nums, return the number of longest increasing subsequences.
    // Notice that the sequence has to be strictly increasing.
    // nums = [1,3,5,4,7]
    // Output: 2
    // Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // todo check the leetcode top submissions for segment tree and binary search implementations
    private static void type3() {
    }

    // todo optimized approach, took the intuition from the longest increasing subsequence,
    // but here along with dp array we will also use another array
    // todo check the striver video is you do not understand the solution
    //  we could use n^2 solution as n <= 2000
    private static void type2() {
        int[] nums = {50, 3, 90, 60, 80};
        int ans = findNumberOfLIS2(nums);
        System.out.println(ans);
    }

    private static int findNumberOfLIS2(int[] nums) {
        int n = nums.length;

        int[] dp = new int[n];
        int[] counts = new int[n];

        // we will initialize everything with 1, as this will be the default value
        // if in any case in the inner loop does not execute or (nums[prev] < nums[i]) do not hold
        // every number is itself an increasing sequence
        Arrays.fill(dp, 1);
        Arrays.fill(counts, 1);
        int max = 1;
        // we will iterate over the array
        for (int i = 0; i < n; i++) {
            for (int prev = i - 1; prev >= 0; prev--) {
                // The intuition is the same as LIS. However, here we will use some extra computations
                if (nums[prev] < nums[i]) {
                    // if the current is greater than previous, and dp[prev]+1 is also greater than the dp[i],
                    // so it is the time that we have found a new longer LIS, so we will update the dp[i].
                    if (dp[i] < dp[prev] + 1) {
                        // we will update the dp[i]
                        dp[i] = dp[prev] + 1;
                        // but now we will assign the count value with the count value of longest prev subsequence
                        counts[i] = counts[prev];
                    } else if (dp[prev] + 1 == dp[i]) {
                        // if the dp[prev]+1 is the same as dp[i], then we have found another LIS,
                        // so we will add that count value in the current counts[i]
                        // only when we find any new longer subsequence, we will add assign the counts[i] with counts[prev]
                        counts[i] = counts[i] + counts[prev];
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }

        // max is the longest increasing subsequence length
        // we will loop through the array and take the index for which dp[i]=max,
        // and we will take their count[i] value
        int count = 0;
        for (int i = 0; i <= n - 1; i++)
            if (dp[i] == max) count += counts[i];
        return count;
    }

    private static void type1() {

    }
}
