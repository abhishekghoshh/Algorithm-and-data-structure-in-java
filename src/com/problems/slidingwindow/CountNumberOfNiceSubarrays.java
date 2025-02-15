package com.problems.slidingwindow;

/*
 *
 * problem links :
 * https://leetcode.com/problems/count-number-of-nice-subarrays
 * https://www.codingninjas.com/studio/problems/count-distinct-subarrays-with-at-most-k-odd-elements_1069335
 *
 * Solution link :
 *
 * Aditya Rajiv : https://www.youtube.com/watch?v=atUJS7ArOY0
 * */

// Tags: Arrays, Prefix sum, Sliding window
public class CountNumberOfNiceSubarrays {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }

    // TODO solve it with sliding window problem
    private static void type4() {
    }

    // prefix sum approach
    // same as type2
    private static void type3() {
        int[] nums = {1, 1, 2, 1, 1};
        int k = 3;
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 1;
        int count = 0;
        int sum = 0;
        for (int num : nums) {
            sum += (num & 1);
            if (sum >= k) count += prefixSum[sum - k];
            prefixSum[sum]++;
        }
        System.out.println(count);
    }


    // prefix sum approach
    // so the trick here is to change all the number
    // such that odds are 1 and even are 0
    // the array will be 0 0 1 1 0, something like that
    // now finding k odds number is similar to finding subarrays
    // with sum equal to k
    private static void type2() {
        int[] nums = {1, 1, 2, 1, 1};
        int k = 3;
        int n = nums.length;
        int count = 0;
        int[] bin = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++)
            if (nums[i] % 2 == 1) {
                bin[i] = 1;
                sum++;
            }
        int[] prefixSum = new int[sum + 1];
        prefixSum[0] = 1;
        sum = 0;
        for (int i = 0; i < n; i++) {
            sum += bin[i];
            if (sum >= k && prefixSum[sum - k] != 0) {
                count += prefixSum[sum - k];
            }
            prefixSum[sum]++;
        }
        System.out.println(count);
    }
    // Brute force approach
    private static void type1() {
    }
}
