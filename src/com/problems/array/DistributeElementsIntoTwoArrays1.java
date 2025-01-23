package com.problems.array;

import com.util.PrintUtl;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem link :
 * https://leetcode.com/problems/distribute-elements-into-two-arrays-i/
 *
 * Solution link :
 *
 */
public class DistributeElementsIntoTwoArrays1 {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // in place
    private static void type2() {
        int[] nums = {5, 4, 3, 8};
        int[] ans = resultArray2(nums);
        PrintUtl.print(ans);
    }

    // todo study it one more time
    private static int[] resultArray2(int[] nums) {
        int idx = 0;
        for (int i = 2; i < nums.length; i++) {
            if (nums[idx] > nums[i - 1]) {
                int num = nums[i];
                idx++;
                for (int j = i; j > idx; j--) {
                    nums[j] = nums[j - 1];
                }
                nums[idx] = num;
            }
        }
        return nums;
    }

    // using extra space
    private static void type1() {
        int[] nums = {5, 4, 3, 8};
        int[] ans = resultArray1(nums);
        PrintUtl.print(ans);
    }

    public static int[] resultArray1(int[] nums) {
        int n = nums.length;
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        int[] ans = new int[n];
        list1.add(nums[0]);
        list2.add(nums[1]);
        for (int i = 2; i < n; i++) {
            int last1 = list1.get(list1.size() - 1);
            int last2 = list2.get(list2.size() - 1);
            if (last1 > last2) list1.add(nums[i]);
            else list2.add(nums[i]);
        }
        int i = 0;
        for (int num : list1) ans[i++] = num;
        for (int num : list2) ans[i++] = num;

        return ans;
    }
}
