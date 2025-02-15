package com.problems.array;

/*
 * Problem link :
 * https://leetcode.com/problems/longest-continuous-increasing-subsequence
 *
 * Solution link :
 * */
public class LongestContinuousIncreasingSubsequence {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // same as type2 method
    // just here we will reduce the line number
    private static void type3() {
        int[] nums = {1, 3, 5, 4, 7};
        int max = 0, count = 0;
        int prev = Integer.MIN_VALUE;
        for (int num : nums) {
            if (prev < num) count++;
            else count = 1;
            prev = num;
            if (max < count) max = count;
        }
        System.out.println(max);
    }

    // optimized approach
    private static void type2() {
        int[] nums = {1, 3, 5, 4, 7};
        int max = 0, count = 0;
        int prev = Integer.MIN_VALUE;
        for (int num : nums) {
            if (prev < num) {
                prev = num;
                count++;
            } else {
                prev = num;
                count = 1;
            }
            if (max < count) max = count;
        }
        System.out.println(max);
    }

    // brute force approach
    // using 2 loops
    private static void type1() {
        int[] nums = {1, 3, 5, 4, 7};
    }
}
