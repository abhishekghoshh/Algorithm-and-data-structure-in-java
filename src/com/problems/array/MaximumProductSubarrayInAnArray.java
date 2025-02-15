package com.problems.array;

/*
 *
 * problem links :
 * https://leetcode.com/problems/maximum-product-subarray/description/
 * https://neetcode.io/problems/maximum-product-subarray
 * https://www.naukri.com/code360/problems/subarray-with-maximum-product_6890008
 *
 *
 * Solution link :
 * https://www.youtube.com/watch?v=hnswaLJvr6g
 * https://www.youtube.com/watch?v=lXVy6YWFcRM
 *
 * https://takeuforward.org/data-structure/maximum-product-subarray-in-an-array/
 * https://neetcode.io/solutions/maximum-product-subarray
 */
public class MaximumProductSubarrayInAnArray {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }


    // same as the previous, but here we will use the same loop to calculate the prefix and suffix mul
    // TODO explain this approach in any interview
    // We have couple observations here
    // 1. No negative no zeros
    // 2. even number of negatives without zero
    // 3. odd number of negatives without zero
    // 4. with zero

    // for the first two cases we have the whole array as answer
    // lets take with odd number of negatives
    //  [1, 2, 4, -2, 3, -5, 3, -4, 2]
    // the maximum could be [1, 2, 4, -2, 3, -5, 3] or [3, -5, 3, -4, 2]
    // so it is either prefix or suffix
    // we can think of other examples, but it is always the same
    // the reason is every number is an integer,
    // and we are starting from the start or end and make series with even number of negative
    // but what if there is a zero
    // [1, 2, 4, -2, 3, -5, 3, -4, 2 ,0, 1, 2, 4, 9]
    // so if we look close it is actually 2 arrays
    // so, we can do one thing once we encounter a zero we can set the
    // resultant multiplication to 1 to make it a new start
    // so, we can traverse from the start or end in different loop or in the same loop
    // and on every iteration we will pick the max out of it
    private static void type3() {
        int[] nums = {2, 3, -2, 4};
        int max = maxProduct3(nums);
        System.out.println(max);
    }

    private static int maxProduct3(int[] nums) {
        int n = nums.length;

        int pre = 1, suff = 1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (pre == 0) pre = 1;
            if (suff == 0) suff = 1;
            pre *= nums[i]; // multiplying the ith value
            suff *= nums[n - i - 1]; // multiplying the n-i th value
            max = Math.max(max, Math.max(pre, suff));
        }
        return max;
    }

    // if we use a pointer and a variable from the start and store it in the prefix mul variable
    // and every time we will check with the current max and if there is any 0 we will reset the mul with 1
    // but there is a problem with negative numbers
    // lets say we have 3 negatives at start then 2 positive then 4 negatives again and answer lies within 4 negatives
    // but as we are starting for the left this will include the negatives from the left side, so we will use 2 loops
    // one from the left and another from the right
    private static void type2() {
        int[] nums = {2, 3, -2, 4};
        int res = maxProduct2(nums);
        System.out.println(res);
    }

    private static int maxProduct2(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        // calculating from the left side
        for (int i = 0, mul = 1; i < n; ++i) {
            mul *= nums[i];
            max = Math.max(max, mul);
            // if the current multiplication value is 0 by any chance then we will set it 1 again
            // which means which will start a new series from the next number
            if (mul == 0) {
                mul = 1;
            }
        }
        // calculating from the right side
        for (int i = n - 1, mul = 1; i >= 0; --i) {
            mul *= nums[i];
            max = Math.max(max, mul);
            // if the current multiplication value is 0 by any chance then we will set it 1 again
            // which means which will start a new series from the next number
            if (mul == 0) {
                mul = 1;
            }
        }
        return max;
    }

    // brute force approach
    // time complexity O(n^2)
    // space complexity O(1)
    private static void type1() {
        int[] nums = {2, 3, -2, 4};
        int max = maxProduct1(nums);
        System.out.println(max);
    }

    private static int maxProduct1(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int mul = 1;
            for (int j = i; j < n; j++) {
                mul *= nums[j];
                max = Math.max(max, mul);
                if (mul == 0) break;
            }
        }
        return max;
    }
}
