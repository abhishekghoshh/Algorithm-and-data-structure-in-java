package com.problems.array;
/*
 *
 * problem links :
 * https://leetcode.com/problems/sort-array-by-parity/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=QC4c9fyr8As
 *
 * https://neetcode.io/solutions/sort-array-by-parity
 * */

import static com.util.PrintUtl.print;

// Tags: Two pointer
public class SortArrayByParity {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // optimal approach using two pointer
    // will skipp even nums in the start and odd nums from the end
    // we will swap if there is an
    private static void type2() {
        int[] nums = {3, 1, 2, 4};
        int[] ans = sortArrayByParity2(nums);
        print(ans);
    }

    public static int[] sortArrayByParity2(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums;
        int i = 0, j = n - 1;
        while (i < j) {
            // skipping even digits if there are any at start
            while (i < j && isEven(nums[i])) i++;
            // skipping odd digits if there are any at last
            while (i < j && !isEven(nums[j])) j--;
            // if i < j that means there are at least one num in start which is odd
            // and one num at last which is odd, so we will swap them, increment the pointers
            if (i < j) {
                swap(nums, i++, j--);
            }
        }
        return nums;
    }

    private static boolean isEven(int num) {
        return num % 2 == 0;
    }

    private static void swap(int[] nums, int i, int j) {
        int num = nums[i];
        nums[i] = nums[j];
        nums[j] = num;
    }

    // brute force
    private static void type1() {
    }
}
