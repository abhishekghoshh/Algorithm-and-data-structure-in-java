package com.problems.slidingwindow;

/*
 * Problem link :
 * https://leetcode.com/problems/minimum-size-subarray-sum/description/
 *
 * Solution link:
 * https://www.youtube.com/watch?v=aYqYMIqZx5s
 *
 * https://neetcode.io/solutions/minimum-size-subarray-sum
 * */

// Tags :
public class MinimumSizeSubarraySum {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // sliding window approach
    // we will use 2 pointer, one for the starting index and one for the current index
    // we will traverse the array with the current index.
    // once the sum has reached to target, we will slowly shrink the window
    private static void type2() {
        int target = 7;
        int[] nums = {2, 3, 1, 2, 4, 3};
        int ans = minSubArrayLen2(target, nums);
    }

    public static int minSubArrayLen2(int target, int[] nums) {
        int min = Integer.MAX_VALUE;
        int n = nums.length;
        int sum = 0;
        // traversing the array
        for (int i = 0, start = 0; i < n; i++) {
            sum += nums[i];
            // if the sum is less than target, then we will skip
            if (sum < target) continue;
            // now we will shrink the window
            while (sum >= target) {
                int len = i - start + 1;
                min = Math.min(min, len);
                sum -= nums[start++];
            }
        }
        return (min != Integer.MAX_VALUE) ? min : 0;
    }

    private static void type1() {
    }
}
