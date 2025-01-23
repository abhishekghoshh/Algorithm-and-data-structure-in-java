package com.problems.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * Problem link :
 * https://leetcode.com/problems/largest-divisible-subset/description/
 * https://www.naukri.com/code360/problems/divisible-set_3754960
 *
 * Solution link :
 * https://www.youtube.com/watch?v=gDuZwBW9VvM&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=45
 * https://www.youtube.com/watch?v=LeRU6irRoW0
 *
 * https://takeuforward.org/data-structure/longest-divisible-subset-dp-44/
 * https://neetcode.io/solutions/largest-divisible-subset
 */
public class LargestDivisibleSubset {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // copying the logic from the longest increasing subsequence
    // where we are using a single dp array to store the longest increasing subsequence.
    // we will sort the array, so the array will be arranged in an increasing manner
    // let's say nums are 2, 6, 12.
    // if 2 divisible by 6 and 6 divisible by 12, then we do not need to check for 2 divisible by 12 or not
    // and also we do not need the check divisibility of the numbers in another way.
    // like we are checking only curr % prev, we do not need to check prev % curr
    // we will use two dp arrays, one for storing dp values and another for storing
    // the previous indices of the previous longest increasing subsequence
    // initially we will put -1 for the previous index as we will consider all the num as a single length LIS
    // for the same reason we will put 1 to dp array
    private static void type2() {
        int[] nums = {1, 2, 4, 8};
        List<Integer> list = largestDivisibleSubset(nums);
        System.out.println(list);
    }

    public static List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        // we will initialize previousIndex with -1 as this can be a start of new subsequence
        int[] previousIndex = new int[n];
        Arrays.fill(previousIndex, -1);
        // also we will set the default value of dp to 1 for the same reason
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        // we will sort the array, so the array will be arranged in an increasing manner
        Arrays.sort(nums);
        int maxI = 0;
        for (int i = 0; i < n; i++) {
            int maxPrev = -1;
            for (int prev = 0; prev < i; prev++) {
                // we will check the divisibility here and
                // if maxPrev is unassigned or current prev dp value is greater than max previous dp value, then we will set the prev to the max prev
                if ((nums[i] % nums[prev] == 0)
                        && (maxPrev == -1 || dp[maxPrev] < dp[prev])) {
                    maxPrev = prev;
                }
            }
            // if max prev has a proper value then we will update the dp[i] and set prev index of current as max prev
            if (maxPrev != -1) {
                dp[i] = dp[maxPrev] + 1;
                previousIndex[i] = maxPrev;
            }
            // we will also check the maximum index for the dp value
            if (dp[maxI] < dp[i]) maxI = i;
        }
        // we have the prev index array, we will loop until -1
        List<Integer> list = new ArrayList<>(dp[maxI]);
        while (maxI != -1) {
            list.add(nums[maxI]);
            maxI = previousIndex[maxI];
        }
        Collections.reverse(list);
        return list;
    }

    // using brute force approach
    private static void type1() {
        
    }
}
