package com.problems.array;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem link
 * https://www.codingninjas.com/studio/problems/superior-elements_6783446
 *
 *
 * solution link
 * https://www.youtube.com/watch?v=cHrH9CQ8pmY
 *
 * https://takeuforward.org/data-structure/leaders-in-an-array/
 * */
public class LeadersInAnArray {
    public static void main(String[] args) {
        type1();
        type2();
    }

    private static void type2() {
        int[] nums = {1, 2, 3, 2};
        int n = nums.length;
        List<Integer> answer = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] > max) {
                max = nums[i];
                answer.add(max);
            }
        }
        System.out.println(answer);
    }

    // brute force approach
    // time complexity O(n^2)
    // space complexity O(1)
    private static void type1() {
        int[] nums = {1, 2, 3, 2};
        int n = nums.length;
        List<Integer> answer = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            boolean isGreaterThanAllRight = true;
            for (int j = i + 1; j < n; j++) {
                if (nums[i] <= nums[j]) {
                    isGreaterThanAllRight = false;
                    break;
                }
            }
            if (isGreaterThanAllRight) answer.add(nums[i]);
        }
        System.out.println(answer);
    }
}
