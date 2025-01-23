package com.problems.dp;

/*
 * Problem link :
 * https://www.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1
 *
 * Solution link :
 *
 */
public class MaxSumIncreasingSubsequence {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // we will directly use the optimized approach
    // using iterative approach
    // using a sum array
    private static void type2() {
        int[] arr = {1, 101, 2, 3, 100};
        int n = arr.length;
        int ans = maxSumIS2(arr, n);
        System.out.println(ans);
    }

    // we will save the max sum till the current index in the sum array
    public static int maxSumIS2(int[] arr, int n) {
        int[] sum = new int[n];
        sum[0] = arr[0];
        int max = sum[0];
        // we will start from index 1
        for (int i = 1; i < n; i++) {
            int tempMax = 0;
            for (int j = i - 1; j >= 0; j--) {
                // we will take the max sum from all left side indices
                // if the value of array is lesser than the current index value
                if (arr[j] < arr[i])
                    tempMax = Math.max(tempMax, sum[j]);
            }
            sum[i] = arr[i] + tempMax;
            max = Math.max(max, sum[i]);
        }
        return max;
    }

    // brute force approach using recursion/iteration
    private static void type1() {
        int[] arr = {1, 101, 2, 3, 100};
        int n = arr.length;
    }
}
