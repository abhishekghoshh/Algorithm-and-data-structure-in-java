package com.problems.array;
/*
 *
 * problem links :
 * https://leetcode.com/problems/find-pivot-index/description/
 *
 * Solution link :
 *
 *
 * https://neetcode.io/solutions/find-pivot-index
 * */

// Tags : Prefix Sum, Array
public class FindPivotIndex {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // most optimized approach using just only suffix sum
    private static void type3() {
        int[] nums = {1, 7, 3, 6, 5, 6};
        int ans = pivotIndex3(nums);
        System.out.println(ans);
    }

    public static int pivotIndex3(int[] nums) {
        int n = nums.length;
        int[] suffixSum = new int[n];
        int sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = sum;
            sum += nums[i];
        }
        int prefixSum = 0;
        for (int i = 0; i < n; i++) {
            if (prefixSum == suffixSum[i]) return i;
            prefixSum += nums[i];
        }
        return -1;
    }

    // optimized approach using the prefix sum and suffix sum
    private static void type2() {
        int[] nums = {1, 7, 3, 6, 5, 6};
        int ans = pivotIndex2(nums);
        System.out.println(ans);
    }

    private static int pivotIndex2(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n];
        int[] suffixSum = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            prefixSum[i] = sum;
            sum += nums[i];
        }
        sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = sum;
            sum += nums[i];
        }
        for (int i = 0; i < n; i++)
            if (prefixSum[i] == suffixSum[i])
                return i;
        return -1;
    }

    // brute force approach
    private static void type1() {
        int[] nums = {1, 7, 3, 6, 5, 6};
        int ans = pivotIndex1(nums);
        System.out.println(ans);
    }

    private static int pivotIndex1(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int prefixSum = 0, suffixSum = 0;
            for (int j = i - 1; j >= 0; j--) prefixSum += nums[j];
            for (int j = i + 1; j < n; j++) suffixSum += nums[j];
            if (prefixSum == suffixSum) return i;
        }
        return -1;
    }
}
