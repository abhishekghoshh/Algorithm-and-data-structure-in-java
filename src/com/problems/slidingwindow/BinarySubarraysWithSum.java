package com.problems.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/*
 *
 * problem links :
 * https://leetcode.com/problems/binary-subarrays-with-sum/description/
 * https://www.naukri.com/code360/problems/count-substrings-with-k-ones_3128698
 *
 * Solution link :
 * Leet Corner :
 * https://www.youtube.com/watch?v=-DNN2Xk7Xb8
 * Neetcode:
 * https://www.youtube.com/watch?v=j4JDr4-jvo4
 *
 * https://neetcode.io/solutions/binary-subarrays-with-sum
 * */

// Tags: Arrays, Prefix sum, Sliding window
public class BinarySubarraysWithSum {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }

    // todo Heavily inspired for SubarrayWithExactlyKDifferentIntegers
    //  we know how to find count of the subarray whose sum is less than equal to target
    //  but we need exactly how many subarrays whose sum is equal to target
    //  if we do a trick,
    //  if we find the count for target and subtract to the count of target-1
    //  then we will get subarrays count whose sum is equal to target
    private static void type4() {
        int[] nums = {1, 0, 1, 0, 1};
        int goal = 2;
        int count = numSubarraysWithSum4(nums, goal);
        System.out.println(count);
    }

    private static int numSubarraysWithSum4(int[] nums, int goal) {
        return getCount(nums, goal) - getCount(nums, goal - 1);
    }

    private static int getCount(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        int count = 0;
        for (int left = 0, right = 0; right < n; right++) {
            sum += nums[right];
            // if sum is greater than target then we need to shrink form the left side
            while (left <= right && sum > target) {
                sum -= nums[left++];
            }
            // subarray count
            if (sum <= target)
                count += (right - left + 1);
        }
        return count;
    }

    // todo optimized approach prefix sum approach but here we will use array as map
    private static void type3() {
        int[] nums = {1, 0, 1, 0, 1};
        int goal = 2;
        int count = numSubarraysWithSum3(nums, goal);
        System.out.println(count);

    }

    private static int numSubarraysWithSum3(int[] nums, int goal) {
        int N = 0;
        for (int num : nums) N += num;
        int[] prefixSum = new int[N + 1];
        // adding 0 sum to the prefix sum as empty array has prefix sum as 0
        prefixSum[0] = 1;
        int sum = 0, count = 0;
        // now we will traverse the nums array
        for (int num : nums) {
            sum += num;
            if (sum >= goal && prefixSum[sum - goal] != 0)
                count += prefixSum[sum - goal];
            prefixSum[sum]++;
        }
        return count;
    }

    // todo optimized approach prefix sum using a hashmap
    //  we will use a count variable to return the answer and sum to calculate the prefix sum
    //  and we will store the prefix sum and count of prefix sums
    //  if current prefix is the sum and prefix sum - goal is in the map then then goal is there in between
    //  so we will take the count of the sum-goal and add that in the answer
    private static void type2() {
        int[] nums = {1, 0, 1, 0, 1};
        int goal = 2;
        int count = numSubarraysWithSum2(nums, goal);
        System.out.println(count);
    }

    private static int numSubarraysWithSum2(int[] nums, int goal) {
        Map<Integer, Integer> prefixSum = new HashMap<>();
        int count = 0;
        // adding 0 sum to the prefix sum as empty array has prefix sum as 0
        prefixSum.put(0, 1);
        int sum = 0;
        // now we will traverse the nums array
        for (int num : nums) {
            sum += num;
            if (prefixSum.containsKey(sum - goal))
                count += prefixSum.get(sum - goal);
            prefixSum.put(sum, prefixSum.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    // brute force approach
    private static void type1() {
    }
}
