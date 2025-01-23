package com.problems.slidingwindow;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem link:
 * https://leetcode.com/problems/max-consecutive-ones-iii/description/
 * https://www.codingninjas.com/studio/problems/maximum-consecutive-ones_892994
 *
 * Solution link:
 *
 * */

// there is no brute force solution for this problem
public class MaxConsecutiveOnes3 {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // TODO study later
    private static void type3() {
        int[] nums = {0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1};
        int k = 2;
        int n = nums.length;
        int max;
        int start = 0, end = 0, zeros = 0;
        while (end < n) {
            if (nums[end++] == 0) zeros++;
            if (zeros > k && nums[start++] == 0) zeros--;
        }
        max = end - start;
        System.out.println(max);
    }

    // we will count the continuous k zeros
    // if we find that then max count will
    private static void type2() {
        int[] nums = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        int k = 2;
        int max = 0;
        int start = 0, zeros = 0, ones = 0;
        for (int num : nums) {
            if (num == 0) zeros++;
            else ones++;
            while (zeros > k)
                if (nums[start++] == 0) zeros--;
                else ones--;
            max = Math.max(max, ones + zeros);
        }
        System.out.println(max);
    }

    // let's say we have a series of 0's and 1's
    // we can change k zeros to make the biggest consecutive 1
    private static void type1() {
        int[] nums = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        int k = 1;
        int max = Integer.MIN_VALUE;
        int n = nums.length;
        List<Integer> zeroIndices = new ArrayList<>();
        zeroIndices.add(-1);
        for (int i = 0; i < n; i++)
            if (nums[i] == 0) zeroIndices.add(i);
        zeroIndices.add(n);
        int size = zeroIndices.size();
//        if (size - 2 <= k) return n;
        int pointer = 1, left, right;
        while (pointer + k < size) {
            left = zeroIndices.get(pointer - 1);
            right = zeroIndices.get(pointer + k);
            max = Math.max(max, (right - left - 1));
            pointer++;
        }
        System.out.println(max);
    }

}
