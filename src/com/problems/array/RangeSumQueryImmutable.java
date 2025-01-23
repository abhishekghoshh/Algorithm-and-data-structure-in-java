package com.problems.array;
/*
 *
 * problem links :
 * https://leetcode.com/problems/range-sum-query-immutable/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=2pndAmo_sMA
 *
 * https://neetcode.io/solutions/range-sum-query-immutable
 * */

// Tags : Prefix Sum, Array
public class RangeSumQueryImmutable {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // using the prefix sum
    // here we will first calculate the prefix sums at the start
    // then for the sum range method we will return from prefix sum array directly
    private static void type2() {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray2 numArray = new NumArray2(nums);
        int ans = numArray.sumRange(2, 5);
        System.out.println(ans);
    }

    static class NumArray2 {
        int[] prefixSum;

        public NumArray2(int[] nums) {
            int n = nums.length;
            prefixSum = new int[n];
            prefixSum[0] = nums[0];
            // calculating the prefix sum
            for (int i = 1; i < n; i++)
                prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        public int sumRange(int left, int right) {
            return (left != 0) ? (prefixSum[right] - prefixSum[left - 1])
                    : prefixSum[right];
        }
    }

    // brute force approach
    private static void type1() {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray1 numArray = new NumArray1(nums);
        int ans = numArray.sumRange(2, 5);
        System.out.println(ans);
    }

    static class NumArray1 {
        int[] nums;

        public NumArray1(int[] nums) {
            this.nums = nums.clone();
        }

        public int sumRange(int left, int right) {
            // here we will calculate the range everytime the method is called
            int sum = 0;
            for (int i = left; i <= right; i++)
                sum += nums[i];
            return sum;
        }
    }


}
