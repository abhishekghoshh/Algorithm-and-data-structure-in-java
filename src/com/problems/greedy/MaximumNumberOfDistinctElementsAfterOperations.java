package com.problems.greedy;

import java.util.Arrays;

/*
 * Problem link :
 * https://leetcode.com/problems/maximum-number-of-distinct-elements-after-operations/
 *
 * Solution link :
 *
 */
public class MaximumNumberOfDistinctElementsAfterOperations {
    public static void main(String[] args) {
        type1();
    }

    // we will use a greedy method here
    // rather than changing duplicate elements
    // if we try to change all the elements
    // this sounds more work, but it makes changing safe for other elements.
    // a num can be changed into [num-k, num+k]
    // we will take a side we will try to make a num as low as possible
    // or as big as possible. let's take an example. if the X is the max of nums,
    // if we make X to X+k, then it would not interfere with other numbers.
    // if there are other X then we can change them to [ X+k-1, X+k-2, ... X-k ],
    // so we will keep track of the last converted number
    // and check if we can convert the current num to (lastConverted - 1)
    // if (lastConverted - 1) falls in between (num-k) to (num+k) then we can change the curr num to (lastConverted - 1)
    // but if the num+k <<<< (lastConverted - 1)
    // then we can change the current num to num+k again
    // todo but before everything we need to sort the nums array,
    // so that all the numbers will come one by one in decreasing order and then last converted numbers will also
    // come in order and no number will intersect
    private static void type1() {
        int[] nums = {1, 2, 2, 3, 3, 4};
        int k = 2;
        int ans = maxDistinctElements1(nums, k);
        System.out.println(ans);

    }

    public static int maxDistinctElements1(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        // we will convert the last item as a seed value
        int lastConverted = nums[n - 1] + k;
        int count = 1;
        // we will start from n-2 and check if we can convert (lastConverted - 1) or (num+k)
        for (int i = n - 2; i >= 0; i--) {
            int num = nums[i];
            int upperBound = num + k, lowerBound = num - k;
            // if curr num is convertible to (lastConverted - 1), then we will convert it
            if (lowerBound <= (lastConverted - 1) && (lastConverted - 1) <= upperBound) {
                lastConverted--;
                count++;
            } else if (upperBound < (lastConverted - 1)) {
                // num+k <<<< (lastConverted - 1), so we will reset the lastConverted with num+k
                lastConverted = upperBound;
                count++;
            }
        }
        return count;
    }
}
