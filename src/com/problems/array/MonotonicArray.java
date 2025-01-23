package com.problems.array;

/*
 *
 * problem links :
 * https://leetcode.com/problems/monotonic-array/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=sqWOFIZ9Z0U
 *
 * */
public class MonotonicArray {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        int[] nums = {6, 5, 4, 4};
        boolean ans = isMonotonic(nums);
        System.out.println(ans);
    }

    public static boolean isMonotonic(int[] nums) {
        int n = nums.length;
        // if it has only one item then it is already monotonic
        if (n == 1) return true;
        // first we will check if the first 2 unique follows increasing or decreasing
        // then we will follow the same for the remaining numbers
        int prev = nums[0];
        int start = 0;
        // finding the next unique element
        while (start < n && prev == nums[start]) start++;
        // if start == n means all the numbers are same, and we have reach to the end
        if (start == n) return true;
        // now we will check for the remaining items
        boolean increasing = (prev < nums[start]);
        prev = nums[start];
        for (int i = start; i < n; i++) {
            if (increasing && prev > nums[i]) return false;
            if (!increasing && prev < nums[i]) return false;
            prev = nums[i];
        }
        return true;
    }
}
