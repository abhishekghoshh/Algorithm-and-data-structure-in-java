package com.problems.binarysearch;
/*
 * Problem link :
 * 
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=gY2b-Q6wDEM&list=PL_z_8CaSLPWeYfhtuKHj-9MpYb6XQJ_f2&index=4
 * 
 * */
public class OrderNotKnownBinarySearch {

	public static void main(String[] args) {
		type2();
	}

	private static void type2() {
		int[] arr = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int key = 8;
		int low = 0, high = arr.length - 1;
		int index = arr[low] <= arr[high] ?
				increasingOrderSearch(arr, low, high, key) :
				decreasingOrderSearch(arr, low, high, key);
		System.out.println(index);
	}

	private static int increasingOrderSearch(int[] nums, int low, int high, int target) {
		int mid;
		while (low <= high) {
			mid = low + (high - low) / 2;
			if (nums[mid] == target) return mid;
			else if (target < nums[mid]) high = mid - 1;
			else low = mid + 1;
		}
		return -1;
	}

	private static int decreasingOrderSearch(int[] nums, int low, int high, int target) {
		int mid;
		while (low <= high) {
			mid = low + (high - low) / 2;
			if (nums[mid] == target) return mid;
			else if (target < nums[mid]) low = mid + 1;
			else high = mid - 1;
		}
		return -1;
	}
}
