package com.problems.array;

import java.util.Arrays;

/*
 *
 * problem links :
 * https://leetcode.com/problems/special-array-with-x-elements-greater-than-or-equal-x/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=Z51jYCeBLVI
 *
 * https://neetcode.io/solutions/special-array-with-x-elements-greater-than-or-equal-x
 * */

// Tags : Arrays, hashing, sorting, binary search
public class SpecialArrayWithXElementsGreaterThanOrEqualX {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // most optimized approach
    // using freq array
    // we know one thing that the answer will lie between 1 and n
    // even if nums[i] > n because if there is n elements then
    // we can have at max n elements which can be greater than the special element,
    // we will count the freq of the array and for the elements > n we will increment freq[n]
    private static void type3() {
        int[] nums = {3, 9, 7, 8, 3, 8, 6, 6};
        int ans = specialArray3(nums);
        System.out.println(ans);
    }

    public static int specialArray3(int[] nums) {
        int n = nums.length;
        // creating the freq array
        int[] freq = new int[n + 1];
        for (int num : nums) {
            // if the array is greater than n then we will take the n
            num = Math.min(num, n);
            freq[num]++;
        }
        int count = 0;
        // now we will go from the last, and count the numbers which are greater than num
        // count is the cumulative freq
        for (int num = n; num >= 0; num--) {
            count += freq[num];
            if (count == num) {
                return num;
            }
        }
        return -1;
    }

    // optimized approach using sort
    // lets sort it and count the elements from right to left
    // lets say numbers are 1 2 4 5 6,
    // and we are now currently 4 so that means on right there is 3 numbers
    // is 3 is in between 2 and 4, which is yes, so we will return 3
    // we will go till to start
    private static void type2() {
        int[] nums = {3, 9, 7, 8, 3, 8, 6, 6};
        int ans = specialArray2(nums);
        System.out.println(ans);
    }

    private static int specialArray2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int i = n - 1;
        // checking from the last
        while (i >= 0) {
            int curr = nums[i];
            // skipping all the duplicates
            while (i > 0 && curr == nums[i - 1]) i--;
            // finding the count on the right side
            int count = (n - i);
            int prev = (i > 0) ? nums[i - 1] : Integer.MIN_VALUE;
            // here we are checking the actual condition
            if (prev < count && count <= curr) return count;
            i--;
        }
        return -1;
    }

    // brute force approach
    private static void type1() {
        int[] nums = {3, 9, 7, 8, 3, 8, 6, 6};
        int ans = specialArray1(nums);
        System.out.println(ans);
    }

    public static int specialArray1(int[] nums) {
        int n = nums.length;
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int num : nums) {
                if (i <= num) count++;
            }
            if (i == count) return i;
        }
        return -1;
    }
}
