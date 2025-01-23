package com.problems.binarysearch;

/*
 * Problem link :
 * https://leetcode.com/problems/search-insert-position/description/
 * https://www.codingninjas.com/codestudio/problems/algorithm-to-find-best-insert-position-in-sorted-array_839813
 *
 * Solution link :
 * https://www.youtube.com/watch?v=6zhGS79oQ4k&t=1187s
 *
 * https://takeuforward.org/arrays/search-insert-position/
 * */
public class SearchInsertPosition {
    public static void main(String[] args) {
        type1();
        type2();
    }

    private static void type2() {
        int[] nums = {1, 3, 5, 6};
        int target = 5;
        int answer = searchInsert(nums, target);
        System.out.println(answer);
    }

    public static int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int answer = n;
        int low = 0, high = n - 1, mid;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (target <= nums[mid]) {
                if (target == nums[mid]) return mid;
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return answer;
    }

    // brute force approach
    private static void type1() {
        int[] nums = {1, 3, 5, 6};
        int target = 5;
        int n = nums.length;
        int answer = n;
        for (int i = 0; i < n; i++) {
            if (target <= nums[i]) {
                answer = i;
                break;
            }
        }
        System.out.println(answer);
    }

}
