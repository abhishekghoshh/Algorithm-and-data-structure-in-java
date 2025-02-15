package com.problems.greedy;

/*
 * Problem link :
 * https://leetcode.com/problems/non-decreasing-array/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=RegQckCegDk
 *
 * https://neetcode.io/solutions/non-decreasing-array
 */

// Tags: Array, Greedy
public class NonDecreasingArray {
    public static void main(String[] args) {
        type1();
    }


    // most optimized approach
    // greedy approach
    // lets say the array is [ 0, 4, 2, 3]
    // here we can just change 4 to 1 or 2 both works
    // the array is [ 4 5 2 7]
    // if we can just change 2 to 5 or 6 or 7 it will work
    // for the first array we changed the first index 4
    // for the second array we change the 2nd index 2
    // so in the first case the pair was 4,2 and the prev element was 0
    // the obvious choice would be to change the 4 into [0,2] anything as prev < nums[i+1]
    // only nums[i] was misplaced, so we decreased the nums[i] to atleast nums[i+1]
    // for the 2nd case the pair is 5,2 and the prev element is 4
    // the obvious choice would be to change 2 into 5 so that the series would be 4,5,5
    // here we changed nums[i+1] as prev > nums[i+1], the misplaced number is nums[i+1]
    // so we increased it to nums[i]
    private static void type1() {
        int[] nums = {4, 2, 3};
        boolean ans = checkPossibility1(nums);
        System.out.println(ans);
    }

    public static boolean checkPossibility1(int[] nums) {
        int n = nums.length;
        if (n == 1) return true;
        boolean isChanged = false;
        for (int i = 0; i + 1 < n; i++) {
            // not need to do anything
            if (nums[i] <= nums[i + 1]) continue;
            // if already changed a position previously, then we will return false directly
            if (isChanged) return false;
            // the prev is smaller than the nums[i+1] so we decrease the nums[i]
            if (i == 0 || nums[i - 1] <= nums[i + 1]) {
                nums[i] = nums[i + 1];
            } else {
                // else increase nums[i+1]
                nums[i + 1] = nums[i];
            }
            isChanged = true;
        }
        return true;
    }
}
