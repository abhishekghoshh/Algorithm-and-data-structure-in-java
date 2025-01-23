package com.problems.binarysearch;

/*
 * Problem link:
 * https://www.interviewbit.com/problems/search-in-bitonic-array/
 *
 * Solution link:
 * https://www.youtube.com/watch?v=IjaP8qt1IYI&list=PL_z_8CaSLPWeYfhtuKHj-9MpYb6XQJ_f2&index=19
 * 
 * */
// A Bitonic Sequence is a sequence of numbers which is first strictly increasing then after a point strictly decreasing.
public class FindElementInBitonicArray {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// we will split the array
	// and, we will check if the low to mid or mid to high
	// is sorted.We will check if the target present in the sorted
	// part or not else we will go to the other side
	private static void type2() {
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11};
		int target = 12;
		int low = 0, high = arr.length - 1, mid;


	}

	// first we will find the peak,
	// then we will find the in two partitions
	// 3 <= N <= 105
	private static void type1() {
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11};
		int target = 12;
		int n = arr.length;
		int max = findMaximum(arr, n);
		if (arr[max] == target) {
			System.out.printf("key %d found in index %d%n", target, max);
		} else {
			int leftIndex = increasing(arr, 0, max - 1, target);
			int rightIndex = decreasing(arr, max + 1, n - 1, target);
			System.out.printf("key %d found in leftIndex %d%n", target, leftIndex);
			System.out.printf("key %d found in rightIndex %d%n", target, rightIndex);
		}
	}

	private static int findMaximum(int[] arr, int n) {
		int low = 1, high = n - 2, mid;
		while (low <= high) {
			mid = low + (high - low) / 2;
			if (arr[mid - 1] < arr[mid] && arr[mid] > arr[mid + 1]) return mid;
			else if (arr[mid - 1] > arr[mid]) high = mid - 1;
			else low = mid + 1;
		}
		return low;
	}

	private static int increasing(int[] arr, int low, int high, int target) {
		int mid;
		while (low <= high) {
			mid = low + (high - low) / 2;
			if (arr[mid] == target) return mid;
			else if (arr[mid] < target) low = mid + 1;
			else high = mid - 1;
		}
		return -1;
	}

	private static int decreasing(int[] arr, int low, int high, int target) {
		int mid;
		while (low <= high) {
			mid = low + (high - low) / 2;
			if (arr[mid] == target) return mid;
			else if (arr[mid] > target) low = mid + 1;
			else high = mid - 1;
		}
		return -1;
	}

}
