package com.problems.binarysearch;
/*
 * Problem link :
 * 
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=8x6dmO6XW8k&list=PL_z_8CaSLPWeYfhtuKHj-9MpYb6XQJ_f2&index=14
 * 
 * */
public class FirstOcurrenceOfOneInInfiniteBinaryArray {

	public static void main(String[] args) {
		type1();
		type2();
	}

	private static void type2() {
		int[] arr = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, };

		int low = 0;
		int high = 1;
		// we will find the low and high boundary first
		// once we find the boundary,
		// then we will do binary search in that range
		while (arr[high] != 1) {
			low = high;
			high = high * 2;
		}
		int index = searchOne(arr, low, high, 1);
		System.out.println(index);
	}

	private static int searchOne(int[] arr, int low, int high, int target) {
		int index = -1, mid;
		while (low <= high) {
			mid = low + ((high - low) >> 1);
			if (arr[mid] == target) {
				index = mid;
				high = mid - 1;
			} else low = mid + 1;
		}
		return index;
	}

	// brute force approach
	private static void type1() {

	}
}
