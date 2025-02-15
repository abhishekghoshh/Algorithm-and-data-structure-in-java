package com.problems.array;

import static com.util.PrintUtl.print;

/*
 *
 * problem links :
 * https://leetcode.com/problems/move-zeroes/description/
 * https://www.naukri.com/code360/problems/ninja-and-the-zero-s_6581958
 *
 * Solution link :
 * https://www.youtube.com/watch?v=wvcQg43_V8U&t=1633s
 *
 * https://takeuforward.org/data-structure/move-all-zeros-to-the-end-of-the-array/
 *
 *
 * https://www.youtube.com/watch?v=aayNRwUN3Do
 *
 * https://neetcode.io/solutions/move-zeroes
 * */
public class MovesZeroToEnd {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // 2 pointer approach
    // time complexity O(n)
    // space complexity O(1)
    private static void type3() {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes3(nums);
        print(nums);
    }

    private static void moveZeroes3(int[] nums) {
        int n = nums.length;
        int slow, fast;
        for (slow = 0, fast = 0; fast < n; fast++) {
            if (nums[fast] != 0)
                nums[slow++] = nums[fast];
        }
        for (int i = slow; i < n; i++) nums[i] = 0;
    }

    // In place swapping
    // time complexity O(n)
    // space complexity O(1)
    private static void type2() {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes2(nums);
        print(nums);
    }

    private static void moveZeroes2(int[] nums) {
        int n = nums.length;
        int zerothIndex = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0)
                swap(nums, zerothIndex++, i);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    // This is not in place
    // it is using an extra array
    // time complexity O(2n)
    // space complexity O(n)
    private static void type1() {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes1(nums);
        print(nums);
    }

    private static void moveZeroes1(int[] nums) {
        int n = nums.length;
        int[] copy = new int[n];
        int j = 0;
        for (int num : nums) {
            if (num != 0) copy[j++] = num;
        }
        for (int i = 0; i < n; i++) {
            nums[i] = i < j ? copy[i] : 0;
        }
    }
}
