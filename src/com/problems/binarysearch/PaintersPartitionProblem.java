package com.problems.binarysearch;

import java.util.List;

/*
 * Problem link :
 * https://www.codingninjas.com/studio/problems/painter-s-partition-problem_1089557
 *
 * Solution is :
 * https://www.youtube.com/watch?v=thUd_WJn6wk
 *
 * https://takeuforward.org/arrays/painters-partition-problem/
 * */
public class PaintersPartitionProblem {
    public static void main(String[] args) {
        type1();
        type2();
    }


    // binary search on method approach
    private static void type2() {
        List<Integer> arr = List.of(2, 1, 5, 6, 2, 3);
        int k = 2;
        int max = 0, sum = 0;
        for (int num : arr) {
            if (num > max) max = num;
            sum += num;
        }
        int low = max, high = sum, answer = -1, mid, count;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            count = countForMid(arr, mid, k);
            if (count <= k) {
                answer = mid;
                high = mid - 1;
            } else low = mid + 1;
        }
        System.out.println(answer);
    }

    private static int countForMid(List<Integer> nums, int mid, int k) {
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
