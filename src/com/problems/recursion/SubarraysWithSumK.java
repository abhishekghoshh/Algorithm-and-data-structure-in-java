package com.problems.recursion;

import java.util.LinkedList;
import java.util.List;

/*
 * Problem links:
 * https://www.codingninjas.com/studio/problems/subarrays-with-sum-%E2%80%98k'_6922076
 * Solution link
 *
 * */
public class SubarraysWithSumK {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // we can use a sliding window here
    private static void type3() {
    }

    // iterative way
    private static void type2() {
        int[] nums = {1, 2, 3, 1, 1, 1};
        long k = 3;

        int n = nums.length;
        List<List<Integer>> answer = new LinkedList<>();
        List<Integer> bucket = new LinkedList<>();
        long remaining;
        for (int i = 0; i < n; i++) {
            remaining = k;
            bucket.clear();
            for (int j = i; j < n; j++) {
                remaining -= nums[j];
                bucket.add(nums[j]);
                if (remaining == 0) answer.add(new LinkedList<>(bucket));
                else if (remaining < 0) break;
            }
        }
        System.out.println(answer);
    }

    // we can also do it recursive way
    private static void type1() {
    }


}
