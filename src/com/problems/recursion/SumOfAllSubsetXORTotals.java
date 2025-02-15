package com.problems.recursion;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem link :
 * https://leetcode.com/problems/sum-of-all-subset-xor-totals/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=LI7YR-bwNYY
 *
 * https://neetcode.io/solutions/sum-of-all-subset-xor-totals
 * */
public class SumOfAllSubsetXORTotals {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // exactly the same as type2 but with slight difference
    private static void type3() {
        int[] nums = {5, 1, 6};
        int ans = subsetXORSum3(nums);
        System.out.println(ans);
    }

    private static int subsetXORSum3(int[] nums) {
        return subset3(0, nums, 0);
    }

    private static int subset3(int i, int[] nums, int xor) {
        int n = nums.length;
        if (i == n) return xor;
        return subset3(i + 1, nums, nums[i] ^ xor)
                + subset3(i + 1, nums, xor);
    }

    // efficient approach
    // here we will not generate all the subsets rather we will carry a variable called xor
    // and add the xor of the element to the xor variable
    private static void type2() {
        int[] nums = {5, 1, 6};
        int ans = subsetXORSum2(nums);
        System.out.println(ans);
    }

    static int sum2 = 0;

    private static int subsetXORSum2(int[] nums) {
        subset2(0, nums, 0);
        return sum2;
    }

    private static void subset2(int i, int[] nums, int xor) {
        int n = nums.length;
        if (i == n) {
            sum2 += xor;
            return;
        }
        // not choosing the element
        subset2(i + 1, nums, xor ^ nums[i]);
        // not choosing the element
        subset2(i + 1, nums, xor);
    }


    // brute force approach
    private static void type1() {
        int[] nums = {5, 1, 6};
        int ans = subsetXORSum1(nums);
        System.out.println(ans);
    }

    public static int subsetXORSum1(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // generate all subsets
        subset(0, nums, list, new ArrayList<>());
        int xorSum = 0;
        for (List<Integer> l : list) {
            int s = 0;
            // calculating total xor of each subset
            for (int num : l)
                s = (s ^ num);
            xorSum += s;
        }
        return xorSum;
    }

    static void subset(int i, int[] nums, List<List<Integer>> list, List<Integer> bucket) {
        int n = nums.length;
        if (i == n) {
            list.add(new ArrayList<>(bucket));
            return;
        }
        // choosing the element
        bucket.add(nums[i]);
        subset(i + 1, nums, list, bucket);
        bucket.remove(bucket.size() - 1);
        // not choosing the element
        subset(i + 1, nums, list, bucket);
    }
}
