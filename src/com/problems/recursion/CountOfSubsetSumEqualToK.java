package com.problems.recursion;

import java.util.Arrays;

public class CountOfSubsetSumEqualToK {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // count the number of subsets when we can use one number in just one time
    private static void type2() {
        int[] nums = {3, 1, 4, 2};
        int target = 7;
        Arrays.sort(nums);
        int count = count2(nums, 0, target);
        System.out.println(count);
    }

    private static int count2(int[] nums, int n, int remaining) {
        if (remaining == 0) return 1;
        if (n == nums.length) return 0;
        // if the current num is less than equal to target, then we have 2 condition
        // either to add it or ignore it,
        // so if we add it then we will increase the index
        // as we will also check for the same index
        // if num then is not capable then there is no point that next element will also
        // able to make it, as we have sorted the array
        if (nums[n] <= remaining)
            return count2(nums, n + 1, remaining - nums[n]) + count2(nums, n + 1, remaining);
        return 0;
    }

    // count the number of subsets when we can use one number infinite times
    private static void type1() {
        int[] nums = {3, 1, 4, 2};
        int target = 7;
        Arrays.sort(nums);
        int count = count1(nums, 0, target);
        System.out.println(count);
    }

    private static int count1(int[] nums, int n, int remaining) {
        if (remaining == 0) return 1;
        if (n == nums.length) return 0;
        // if the current num is less than equal to target, then we have 2 condition
        // either to add it or ignore it,
        // so if we add it then we will not increase the index
        // as we will also check for the same index
        // if num then is not capable then there is no point that next element will also
        // able to make it, as we have sorted the array
        if (nums[n] <= remaining)
            return count1(nums, n, remaining - nums[n])
                    + count1(nums, n + 1, remaining);
        return 0;
    }
}
