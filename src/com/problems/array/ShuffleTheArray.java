package com.problems.array;
/*
 * Problem link :
 * https://leetcode.com/problems/shuffle-the-array/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=IvIKD_EU8BY
 * https://neetcode.io/solutions/shuffle-the-array
 * */

import com.util.PrintUtl;

// Tags: Arrays
public class ShuffleTheArray {
    public static void main(String[] args) {
        type1();
    }


    private static void type1() {
        int[] nums = {2, 5, 1, 3, 4, 7};
        int n = 3;
        int[] ans = shuffle(nums, n);
        PrintUtl.print(ans);
    }

    public static int[] shuffle(int[] nums, int n) {
        int[] ans = new int[2 * n];
        for (int i = 0; i < n; i++) {
            ans[2 * i] = nums[i];
            ans[2 * i + 1] = nums[i + n];
        }
        return ans;
    }
}
