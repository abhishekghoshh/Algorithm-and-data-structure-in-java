package com.problems.hashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * Problem link:
 * https://leetcode.com/problems/first-missing-positive/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=8g78yfzMlao
 *
 * https://neetcode.io/solutions/first-missing-positive
 */


// Tags: Array, hashset, swap sort
public class FirstMissingPositive {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // using the swap sort approach
    // time complexity O(n)
    private static void type3() {
        int[] nums = {1, 7, 8, 5, 11, 17, 12, 9, 0};
        int ans = firstMissingPositive3(nums);
        System.out.println(ans);
    }

    public static int firstMissingPositive3(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int correctIndex = nums[i] - 1;
            if ((nums[i] > 0 && nums[i] < nums.length) && nums[i] != nums[correctIndex]) {
                swap(nums, i, correctIndex);
            } else {
                i++;
            }
        }
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != j + 1) {
                return j + 1;
            }
        }
        return nums.length + 1;
    }

    public static void swap(int[] nums, int first, int second) {
        int tmp = nums[first];
        nums[first] = nums[second];
        nums[second] = tmp;
    }

    // using sorting, and then binary search
    private static void type2() {
        int[] nums = {1, 7, 8, 5, 11, 17, 12, 9, 0};
        int ans = firstMissingPositive2(nums);
        System.out.println(ans);
    }

    private static int firstMissingPositive2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        if (1 < nums[0] || nums[n - 1] < 1) return 1;
        // finding the first positive number
        int low = 0, high = n - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] <= 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        int num = 1, prev = 0;
        for (int i = low; i < n; i++) {
            if(prev == nums[i]) continue;
            if (num != nums[i]) return num;
            prev = num;
            num++;
        }
        return nums[n-1] + 1;
    }

    // using hash-set
    private static void type1() {
        int[] nums = {1, 7, 8, 5, 11, 17, 12, 9, 0};
        int ans = firstMissingPositive1(nums);
        System.out.println(ans);
    }

    public static int firstMissingPositive1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        // adding all the positive values in the set
        for (int num : nums) {
            if (num > 0) set.add(num);
        }
        // checking from 1
        int n = nums.length;
        for (int i = 1; i <= n; i++) {
            if (!set.contains(i)) return i;
        }
        return n + 1;
    }
}
