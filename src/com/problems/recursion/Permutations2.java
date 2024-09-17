package com.problems.recursion;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem link
 * https://leetcode.com/problems/permutations-ii/description/
 *
 * solution link
 * */
public class Permutations2 {
    public static void main(String[] args) {
        type1();
    }

    // it is from the permutation1 problem
    // check all the solutions of permutation1
    private static void type1() {
        int[] nums = {1, 1, 2};
        List<List<Integer>> ans = permuteUnique(nums);
        System.out.println(ans);
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        permuteUnique(0, nums, ans);
        return ans;
    }

    // as there is limit -10 <= nums[i] <= 10
    // we will set the size of the set to 21, and we will use offset as 10
    private static void permuteUnique(int start, int[] nums, List<List<Integer>> ans) {
        int n = nums.length;
        if (start == n) {
            List<Integer> list = new ArrayList<>();
            for (int item : nums) list.add(item);
            ans.add(list);
            return;
        }
        int N = 21, offset = 10;
        boolean[] set = new boolean[N];
        for (int i = start; i < n; i++) {
            if (!set[nums[i] + offset]) {
                set[nums[i] + offset] = true;
                // swapping the element
                swap(nums, start, i);
                permuteUnique(start + 1, nums, ans);
                // swapping again to retain the nums array
                swap(nums, start, i);
            }
        }
    }

    private static void swap(int[] nums, int left, int right) {
        int num = nums[left];
        nums[left] = nums[right];
        nums[right] = num;
    }
}
