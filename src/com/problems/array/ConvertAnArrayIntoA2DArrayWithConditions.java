package com.problems.array;

/*
 * Problem link:
 * https://leetcode.com/problems/convert-an-array-into-a-2d-array-with-conditions/description/
 *
 * Solution link:
 * https://www.youtube.com/watch?v=9pl1QiaGgmI
 *
 * https://neetcode.io/solutions/convert-an-array-into-a-2d-array-with-conditions
 */

import java.util.ArrayList;
import java.util.List;

// Tags: Array,
public class ConvertAnArrayIntoA2DArrayWithConditions {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // little more optimized than before using the hashing
    // just here we will be doing it differently
    private static void type3() {
        int[] nums = {1, 3, 4, 1, 2, 3, 1};
        List<List<Integer>> ans = findMatrix3(nums);
        System.out.println(ans);
    }

    private static List<List<Integer>> findMatrix3(int[] nums) {
        int n = nums.length;
        int[] freq = new int[n + 1];
        List<List<Integer>> list = new ArrayList<>();
        // here we will go through the nums array and increasing the frequency
        // if the current freq is f, then we will add that item to the f-1 index on the answer list
        for (int num : nums) {
            freq[num]++;
            int f = freq[num];
            if (list.size() < f) list.add(new ArrayList<>());
            list.get(f - 1).add(num);
        }
        return list;
    }

    // optimized approach using hashing
    private static void type2() {
        int[] nums = {1, 3, 4, 1, 2, 3, 1};
        List<List<Integer>> ans = findMatrix2(nums);
        System.out.println(ans);
    }

    private static List<List<Integer>> findMatrix2(int[] nums) {
        int n = nums.length;
        // using an array as a frequency amp
        int[] freq = new int[n + 1];
        // we will take max freq variable,
        // because the maximum size of answer will be maximum freq of the most frequent number
        int maxF = 0;
        for (int num : nums) {
            freq[num]++;
            maxF = Math.max(maxF, freq[num]);
        }
        // adding lists to our answer list beforehand
        List<List<Integer>> list = new ArrayList<>(maxF);
        for (int i = 0; i < maxF; i++) list.add(new ArrayList<>());
        // now we will iterate over (1 to n) and add the elements
        for (int num = 1; num <= n; num++) {
            int f = freq[num];
            for (int j = 0; j < f; j++) list.get(j).add(num);
        }
        return list;
    }

    // brute force approach
    private static void type1() {
    }
}
