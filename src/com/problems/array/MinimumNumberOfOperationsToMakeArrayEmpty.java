package com.problems.array;
/*
 * Problem link:
 * https://leetcode.com/problems/minimum-number-of-operations-to-make-array-empty/description/
 *
 * Solution link:
 * https://www.youtube.com/watch?v=_AcO35R0fss
 *
 * https://neetcode.io/solutions/minimum-number-of-operations-to-make-array-empty
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Tags: Array, sorting, hashing, two pointer
public class MinimumNumberOfOperationsToMakeArrayEmpty {
    // There are two types of operations that you can apply on the array any number of times:
    // Choose two elements with equal values and delete them from the array.
    // Choose three elements with equal values and delete them from the array.
    // todo it is not possible if the frequency is 1 we will return -1 in that case
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // we will use sort
    // then use a 2 pointer to count the frequency of the same elements
    // then the remaining operations are same
    private static void type3() {
        int[] nums = {2, 3, 3, 2, 2, 4, 2, 3, 4};
        int ans = minOperations3(nums);
        System.out.println(ans);
    }

    private static int minOperations3(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;
        int i = 0;
        while (i < n) {
            int num = nums[i], f = 0;
            while (i < n && nums[i] == num) {
                i++;
                f++;
            }
            // if it is 1 then we will directly return -1
            if (f == 1) return -1;
            // if it is divisible by 3 then we will divide it by 3
            // else for reminder 1 or 2 the number of operation will be (f/3 + 1).
            if (f % 3 == 0)
                count += (f / 3);
            else
                count += (f / 3 + 1);
        }
        return count;
    }

    // optimized approach using hashing
    // first we will use a frequency map to store counts
    // then we will check if it is 1 or not if 1 then return -1.
    // then first, we will check if it is divided by 3 or not if yes we divide it by 3
    // else we will check what is the reminder either 1 or 2.
    // if 2 then we can directly remove that in one operation ⇒ (f/3 + 1).
    // if 1 then we will borrow 3 and make the remaining count as 1+3 as 4, and we can remove 4 by operation 2 of remove two
    // and total operations will be ((f/3)-1)+2 => (f/3 + 1).
    private static void type2() {
        int[] nums = {2, 3, 3, 2, 2, 4, 2, 3, 4};
        int ans = minOperations2(nums);
        System.out.println(ans);
    }

    public static int minOperations2(int[] nums) {
        int count = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        // counting the frequencies
        for (int num : nums)
            freq.put(num, 1 + freq.getOrDefault(num, 0));
        // iterating over the frequencies
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int f = entry.getValue();
            // if it is 1 then we will directly return -1
            if (f == 1) return -1;
            // if it is divisible by 3 then we will divide it by 3
            // else for reminder 1 or 2 the number of operation will be (f/3 + 1).
            if (f % 3 == 0)
                count += (f / 3);
            else
                count += (f / 3 + 1);
        }
        return count;
    }

    // brute force approach
    private static void type1() {
    }
}
