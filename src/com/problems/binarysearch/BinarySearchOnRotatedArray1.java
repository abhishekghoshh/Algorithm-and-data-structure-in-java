package com.problems.binarysearch;

/*
 * Problem link : 
 * https://leetcode.com/problems/search-in-rotated-sorted-array/description/
 * https://neetcode.io/problems/find-target-in-rotated-sorted-array
 * https://www.naukri.com/code360/problems/630450
 * https://www.naukri.com/code360/problems/search-in-rotated-sorted-array_1082554
 * 
 * Solution is :
 * https://www.youtube.com/watch?v=5qGrJbHhqFs
 * https://www.youtube.com/watch?v=r3pMQ8-Ad5s&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=65
 * https://www.youtube.com/watch?v=Id-DdcWb5AU&list=PL_z_8CaSLPWeYfhtuKHj-9MpYb6XQJ_f2&index=8
 *
 * Blogs :
 * https://takeuforward.org/data-structure/search-element-in-a-rotated-sorted-array/
 *
 * Tags :
 * Binary-Search
 * */
public class BinarySearchOnRotatedArray1 {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// find in the single search
	// it will find the mid
	// then it will check which portion is sorted
	// it and if the target is in that portion, then it will search
	// else it will go to another way
	private static void type3() {
		int[] nums = { 7, 8, 9, 10, 11, 12, 0, 1, 2, 3, 4, 5, 6 };
		int target = 1;
		int index = rotatedSearch(nums, target);
		System.out.println(index);
	}

	private static int rotatedSearch(int[] nums, int target) {
		int n = nums.length;
		int low = 0, high = n - 1;
		int index = -1, mid;
		while (low <= high) {
			mid = low + ((high - low) >> 1);
			// arr[low] < arr[mid] means we are on the left side and
			// low to mid all the elements are sorted
			if (nums[low] <= nums[mid])
				// now if the key is in low to mid-range, then we can apply binary search on that
				if (nums[low] <= target && target <= nums[mid])
					return search(nums, low, mid, target);
				else
					// key is not in the range of low to mid,
					// so our new range will be started from mid+1
					low = mid + 1;
			else
				// arr[low]>arr[mid] means we are on the right side and
				// mid to high all the elements are sorted
				// now if the key is in mid to high range then we can apply binary search
				if (nums[mid] <= target && target <= nums[high])
					return search(nums, mid, high, target);
				else
					// key is not in the range of mid to high,
					// so our new range will be started from mid-1
					high = mid - 1;
		}
		return index;
	}

	// first it will split the array, then it will search on the
	// partitions individually
	// binary search
	// first search the rotation time
	// so we will get the first and 2nd half
	// then we can find in the appropriate side
	private static void type2() {
		int[] nums = {3, 1};
		int key = 3;
		int answer = search(nums, key);
		System.out.println(answer);
	}

	private static int search(int[] nums, int target) {
		int n = nums.length;
		if (n == 1) return nums[0] == target ? 0 : -1;
		int high = n - 1;
		// 0th element is less than the n-1th element so the array is not rotated
		if (nums[0] < nums[n - 1]) return search(nums, 0, high, target);
		else {
			// the array is rotated, so we will find the rotation index of the starting element
			int rotationIndex = rotationIndex(nums, 0, high, n);
			// System.out.println("rotation number is " + rotationNumber);
			// if the item is in rotationIndex, then we will return it
			if (nums[rotationIndex] == target) return rotationIndex;
				// if it is in left half then we will apply binary search on the left
			else if (nums[0] <= target) return search(nums, 0, rotationIndex - 1, target);
				// it is in the right half then we will apply binary search on the right
			else return search(nums, rotationIndex + 1, high, target);
		}
	}

	private static int rotationIndex(int[] nums, int low, int high, int n) {
		while (low <= high) {
			int mid = low + ((high - low) >> 1);
			int prev = (mid - 1 + n) % n;
			int next = (mid + 1) % n;
			if (nums[prev] > nums[mid] && nums[mid] < nums[next]) return mid;
			if (nums[mid] < nums[high]) high = mid - 1;
			else low = mid + 1;
		}
		return low;
	}

	private static int search(int[] nums, int low, int high, int target) {
		while (low <= high) {
			int mid = low + ((high - low) >> 1);
			if (nums[mid] == target) return mid;
			if (target < nums[mid]) high = mid - 1;
			else low = mid + 1;
		}
		return -1;
	}

	// brute force approach
	// linearly finds the target element
	private static void type1() {

	}
}
