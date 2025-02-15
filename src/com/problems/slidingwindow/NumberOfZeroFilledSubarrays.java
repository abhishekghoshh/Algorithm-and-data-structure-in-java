package com.problems.slidingwindow;

/*
 * Problem link:
 * https://leetcode.com/problems/number-of-zero-filled-subarrays/description/
 *
 * Solution link:
 * https://www.youtube.com/watch?v=G-EWVGCcL_w
 *
 * https://neetcode.io/solutions/number-of-zero-filled-subarrays
 */

// Tags: array, sliding window
public class NumberOfZeroFilledSubarrays {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // exactly the same as the previous but here we will see this as an array problem
    private static void type3() {
        int[] nums = {1, 3, 0, 0, 2, 0, 0, 4};
        long ans = zeroFilledSubarray3(nums);
        System.out.println(ans);
    }

    public static long zeroFilledSubarray3(int[] nums) {
        long ans = 0;
        int count = 0;
        for (int num : nums) {
            // once we see any non-zero, we will reset the count, else increase the count
            if (num != 0) count = 0;
            else count++;
            // this new zero will introduce (count) number of subarrays
            ans += count;
        }
        return ans;
    }

    // optimized approach using the sliding window
    // now we will add one 0 at a time.
    // let's say the current string is 0 0 0, and if we add another 0.
    // it will create 4 substring which ends with the last 0.
    // which is nothing but the new length of the string (basic substring pre-knowledge)
    private static void type2() {
        int[] nums = {1, 3, 0, 0, 2, 0, 0, 4};
        long ans = zeroFilledSubarray2(nums);
        System.out.println(ans);
    }

    public static long zeroFilledSubarray2(int[] nums) {
        int n = nums.length;
        long count = 0L;
        int i = 0;
        int start = -1;
        while (i < n) {
            // skipping the non zero elements
            while (i < n && nums[i] != 0) i++;
            start = i;
            // now we will add one 0 at a time.
            // let's say the current string is 0 0 0, and if we add another 0.
            // it will create 4 substring which ends with the last 0.
            // which is nothing but the new length of the string (basic substring pre-knowledge)
            while (i < n && nums[i] == 0) {
                count += (i - start + 1);
                i++;
            }
        }
        return count;
    }

    // brute force approach
    // using 2 loops
    private static void type1() {
    }
}
