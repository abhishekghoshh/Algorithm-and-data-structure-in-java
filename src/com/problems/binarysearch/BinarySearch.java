package com.problems.binarysearch;
/*
 * Problem link :
 * https://leetcode.com/problems/binary-search
 * https://www.codingninjas.com/studio/problems/binary-search_972
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=MHf6awe89xw&t=1s
 * https://www.youtube.com/watch?v=j7NodO9HIbk&list=PL_z_8CaSLPWeYfhtuKHj-9MpYb6XQJ_f2
 *
 *
 * https://takeuforward.org/data-structure/binary-search-explained/
 * 
 * */
public class BinarySearch {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
		int[] nums = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ,10};
		int target = 1;
        int index = search(nums, target);
        System.out.println(index);
	}

    private static int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 1) return nums[0] == target ? 0 : -1;
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (target == nums[mid]) return mid;
            if (target < nums[mid]) high = mid - 1;
            else low = mid + 1;
        }
        return -1;
    }
}
