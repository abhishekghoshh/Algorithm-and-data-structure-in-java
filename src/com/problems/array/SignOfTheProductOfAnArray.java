package com.problems.array;
/*
 * Problem link:
 * https://leetcode.com/problems/sign-of-the-product-of-an-array/description/
 *
 * Solution link:
 * https://www.youtube.com/watch?v=ILDLM86jNow
 *
 * https://neetcode.io/solutions/sign-of-the-product-of-an-array
 */


// Tags: Arrays
public class SignOfTheProductOfAnArray {
    public static void main(String[] args) {
        type1();
    }

    // if num is 0, then the multiple will always be 0
    // otherwise we will count the number of negatives
    // if the count is odd then the final product will be negative else positive
    private static void type1() {
        int[] nums = {-1, -2, -3, -4, 3, 2, 1};
        int ans = arraySign(nums);
        System.out.println(ans);
    }

    public static int arraySign(int[] nums) {
        int ne = 0;
        for (int num : nums) {
            if (num == 0) return 0;
            if (num < 0) ne++;
        }
        return (ne % 2 == 1) ? -1 : 1;
    }
}
