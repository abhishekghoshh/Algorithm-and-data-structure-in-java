package com.problems.binarysearch;

/*
 * Problem link :
 * https://leetcode.com/problems/split-array-largest-sum/description/
 * https://www.codingninjas.com/studio/problems/largest-subarray-sum-minimized_7461751
 *
 * Solution is :
 * https://www.youtube.com/watch?v=thUd_WJn6wk
 *
 * https://takeuforward.org/arrays/split-array-largest-sum/
 * */
public class SplitArrayLargestSum {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // binary search on answer approach
    private static void type2() {
        int[] nums = {7, 2, 5, 10, 8};
        int k = 2;
        int max = 0, sum = 0;
        for (int num : nums) {
            if (max < num) max = num;
            sum += num;
        }
        int low = max, high = sum, answer = -1, mid, count;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            count = countForMid(nums, mid, k);
            if (count <= k) {
                answer = mid;
                high = mid - 1;
            } else low = mid + 1;
        }
        System.out.println(answer);
    }

    private static int countForMid(int[] nums, int mid, int k) {
        int count = 1, sum = 0;
        for (int num : nums) {
            sum += num;
            if (sum > mid) {
                count++;
                sum = num;
            }
        }
        return count;
    }

    // brute force approach
    private static void type1() {

    }

}
