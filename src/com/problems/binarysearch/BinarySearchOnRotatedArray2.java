package com.problems.binarysearch;

/*
 * Problem link :
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 * https://www.codingninjas.com/studio/problems/search-in-a-rotated-sorted-array-ii_7449547
 *
 * Solution is :
 * https://www.youtube.com/watch?v=w2G2W8l__pc&t=1s
 *
 *
 * Blogs :
 * https://takeuforward.org/arrays/search-element-in-rotated-sorted-array-ii/
 *
 *
 * Tags :
 * Binary-Search
 * */
public class BinarySearchOnRotatedArray2 {
    public static void main(String[] args) {
        type1();
        type2();
    }

    private static void type2() {
        int[] nums = {1, 0, 1, 1, 1};
        int target = 0;
        boolean found = rotatedSearch(nums, target);
        System.out.println(found);
    }

    private static boolean rotatedSearch(int[] nums, int target) {
        int n = nums.length; // size of the array.
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            //if mid-points the target
            if (nums[mid] == target) return true;
            //Edge case:
            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                low = low + 1;
                high = high - 1;
                continue;
            }
            //if left part is sorted:
            if (nums[low] <= nums[mid]) {
                if (nums[low] <= target && target <= nums[mid]) {
                    //element exists:
                    high = mid - 1;
                } else {
                    //element does not exist:
                    low = mid + 1;
                }
            } else { //if right part is sorted:
                if (nums[mid] <= target && target <= nums[high]) {
                    //element exists:
                    low = mid + 1;
                } else {
                    //element does not exist:
                    high = mid - 1;
                }
            }
        }
        return false;
    }

    // brute force approach
    private static void type1() {
    }
}