package com.problems.array;

/*
 *
 * problem links :
 * https://leetcode.com/problems/remove-element/description/
 *
 * Solution link :
 *
 *
 * https://neetcode.io/solutions/remove-element
 * */
public class RemoveElement {
    public static void main(String[] args) {
        type1();
    }


    private static void type1() {
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int val = 2;
        int count = removeElement1(nums, val);
        System.out.println(count);
    }

    private static int removeElement1(int[] nums, int val) {
        int n = nums.length;
        if (n == 0) return 0;
        int start = 0, end = n - 1;
        while (start < end) {
            if (nums[start] == val) {
                // shrinking the end till the end is on val
                while (start < end && nums[end] == val) end--;
                // if start < end that means we can not swap the elements now
                if (start == end) break;
                // now we will swap the elements
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
            }
            start++;
        }
        // finding the first element where the val exists
        int count = 0;
        for (int num : nums) {
            if (num == val) break;
            count++;
        }
        return count;
    }

}
