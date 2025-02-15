package com.problems.logicbuilding;
/*
 * Problem link :
 * https://leetcode.com/problems/concatenation-of-array/description/
 *
 * Solution link :
 *
 */

import static com.util.PrintUtl.print;

// Tags : Array, Logic building
public class ConcatenationOfArray {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // using library function
    private static void type2() {
        int[] nums = {1, 2, 1};
        int[] ans = getConcatenation2(nums);
        print(ans);
    }

    public static int[] getConcatenation2(int[] nums) {
        int n = nums.length;
        int[] ans = new int[2 * n];
        System.arraycopy(nums, 0, ans, 0, n);
        System.arraycopy(nums, 0, ans, n, n);
        return ans;
    }

    // brute force
    private static void type1() {
        int[] nums = {1, 2, 1};
        int[] ans = getConcatenation1(nums);
        print(ans);
    }

    public static int[] getConcatenation1(int[] nums) {
        int n = nums.length;
        int[] ans = new int[2 * n];
        for (int i = 0; i < n; i++) {
            ans[i] = ans[i + n] = nums[i];
        }
        return ans;
    }
}
