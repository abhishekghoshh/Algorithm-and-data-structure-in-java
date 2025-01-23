package com.problems.dp;

/*
 * Problem link :
 * https://leetcode.com/problems/maximize-total-cost-of-alternating-subarrays/description/
 *
 * Solution link :
 *
 *
 */
public class MaximizeTotalCostOfAlternatingSubarrays {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
        type5();
    }

    // bottom-up approach with space optimization
    // as it is only using the previous row,
    // we could easily create 2 arrays curr and prev.
    // but we can do better, we could only use 4 variables to track
    private static void type5() {
        int[] nums = {1, -2, 3, 4};
        int n = nums.length;
        // as we are starting from n-1, and it uses dp[i+1] we will use dp[n+1]
        long nextPos = 0, nextNeg = 0;
        for (int i = n - 1; i >= 0; i--) {
            // we could use an inner loop, but there are only 2 indices on the second dimension, that is 0 and 1
            long posCh1 = nums[i] + nextPos;// for the start of a new subarray
            long posCh2 = nums[i] + nextNeg;// for flipping the sign

            nextNeg = -nums[i] + nextPos;// 0 for both new start and flipping

            // we assigned this in this line because nextNeg is still using the nextPos value
            nextPos = Math.max(posCh1, posCh2);
        }
        long ans = nextPos;
        System.out.println(ans);
    }

    // bottom-up approach
    // we can derive the iterative approach from the previous type
    private static void type4() {
        int[] nums = {1, -2, 3, 4};
        int n = nums.length;
        // as we are starting from n-1, and it uses dp[i+1] we will use dp[n+1]
        long[][] dp = new long[n + 1][2];
        for (int i = n - 1; i >= 0; i--) {
            // we could use an inner loop, but there are only 2 indices on the second dimension, that is 0 and 1
            long posCh1 = nums[i] + dp[i + 1][0];// for the start of a new subarray
            long posCh2 = nums[i] + dp[i + 1][1];// for flipping the sign
            dp[i][0] = Math.max(posCh1, posCh2);

            dp[i][1] = -nums[i] + dp[i + 1][0];// 0 for both new start and flipping
        }
        long ans = dp[0][0];
        System.out.println(ans);
    }

    // recursion with memoization
    private static void type3() {
        int[] nums = {1, -2, 3, 4};
        long ans = maximumTotalCost3(nums);
        System.out.println(ans);
    }

    public static long maximumTotalCost3(int[] nums) {
        int n = nums.length;
        long[][] dp = new long[n][2];
        // initializing the array
        for (int i = 0; i < n; i++) dp[i][0] = dp[i][1] = Long.MIN_VALUE;
        return maximumTotalCost3(nums, 0, 0, dp);
    }

    // we will use 3 flags 0,1,2
    // 0 means it is starting of the new subarray
    // 1 means use positive value for the current item
    // 2 means use negative for the current subarray
    public static long maximumTotalCost3(int[] nums, int i, int flag, long[][] dp) {
        if (i == nums.length) return 0;
        long ch1, ch2;
        // if the cell is already calculated, then we will return the answer
        if (dp[i][flag] != Long.MIN_VALUE) return dp[i][flag];
        // for every number, we have two options
        // either to flip the sign or start a new subarray
        // we will take max out of them
        if (flag == 0) {
            // current sign is positive
            ch1 = nums[i] + maximumTotalCost3(nums, i + 1, 0, dp);
            ch2 = nums[i] + maximumTotalCost3(nums, i + 1, 1, dp);
            return dp[i][flag] = Math.max(ch1, ch2);
        } else {
            // current sign is negative
            return dp[i][flag] = -nums[i] + maximumTotalCost3(nums, i + 1, 0, dp);
        }
    }


    // brute force with recursion, little optimized from the previous type
    // if we think closely, then we will know there are only 2 things we are doing.
    // if the current sign is positive, then for the next we are using positive and negative both
    // one for start of a new subarray another one is for flipping the sign.
    // but for negative we are only using positive for the next.
    // either for the start of the new subarray or for flipping the sign
    // we will use 2 flags 0,1
    // 0 means use positive value for the current item
    // 1 means use negative for the current subarray
    private static void type2() {
        int[] nums = {1, -2, 3, 4};
        long ans = maximumTotalCost2(nums);
        System.out.println(ans);
    }

    public static long maximumTotalCost2(int[] nums) {
        return maximumTotalCost2(nums, 0, 0);
    }

    public static long maximumTotalCost2(int[] nums, int i, int flag) {
        if (i == nums.length) return 0;
        long ch1, ch2;
        // for every number, we have two options
        // either to flip the sign or start a new subarray
        // we will take max out of them
        if (flag == 0) {
            // current sign is positive
            ch1 = nums[i] + maximumTotalCost2(nums, i + 1, 0);// for the start of a new subarray
            ch2 = nums[i] + maximumTotalCost2(nums, i + 1, 1);// for flipping the sign
            return Math.max(ch1, ch2);
        } else {
            // current sign is negative
            return -nums[i] + maximumTotalCost2(nums, i + 1, 0);// 0 for both new start and flipping
        }
    }


    // brute force with recursion
    // from any point, we have 2 options
    // 1. we could either start the subarray from that
    // 2. if the current sign is positive, then we could use negative for the next,
    // if negative then positive for the next.
    // we will use 2 flags 0,1,2
    // 0 means it is starting of the new subarray
    // 1 means use positive value for the current item
    // 2 means use negative for the current subarray
    private static void type1() {
        int[] nums = {1, -2, 3, 4};
        long ans = maximumTotalCost1(nums);
        System.out.println(ans);
    }

    public static long maximumTotalCost1(int[] nums) {
        return maximumTotalCost1(nums, 0, 1);
    }


    public static long maximumTotalCost1(int[] nums, int i, int flag) {
        if (i == nums.length) return 0;
        long ch1, ch2;
        // for every number, we have two options
        // either to flip the sign or start a new subarray
        // we will take max out of them
        if (flag == 0 || flag == 1) {
            ch1 = nums[i] + maximumTotalCost1(nums, i + 1, 2);
            ch2 = nums[i] + maximumTotalCost1(nums, i + 1, 0);
        } else {
            // current sign is negative
            ch1 = -nums[i] + maximumTotalCost1(nums, i + 1, 1);
            ch2 = -nums[i] + maximumTotalCost1(nums, i + 1, 0);
        }
        return Math.max(ch1, ch2);
    }
}
