package com.algo.sort;

/*
 * Problem link :
 * https://leetcode.com/problems/sort-an-array/
 * https://www.geeksforgeeks.org/problems/quick-sort/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=WIrA4YexLRQ&t=1s
 * https://www.youtube.com/watch?v=MsYZSinhuFo
 *
 * https://takeuforward.org/data-structure/quick-sort-algorithm/
 * https://www.geeksforgeeks.org/quick-sort/
 */

import com.util.PrintUtl;

import static com.util.ArrayUtil.swap;

public class QuickSort {

	public static void main(String[] args) {
		type1();
		type2();
	}

	private static void type2() {
		int[] arr = { 10, 7, 8, 9, 1, 5 };
		int n = arr.length;
		quickSort2(arr, 0, n - 1);
	}

	static void quickSort2(int[] arr, int low, int high) {
		if (low < high) {
			// partitionIndex is partitioning index, arr[p] is now at right place
			int partitionIndex = partition2(arr, low, high);
			// Separately sort elements before
			// partition and after partition
			quickSort2(arr, low, partitionIndex - 1);
			quickSort2(arr, partitionIndex + 1, high);
		}
	}

	/*
	 * This function takes last element as pivot, places the pivot element at its
	 * correct position in sorted array, and places all smaller (smaller than pivot)
	 * to left of pivot and all greater elements to right of pivot
	 */
	static int partition2(int[] arr, int low, int high) {
		// we are setting last item as pivot
		int pivot = arr[high];
		// Index of smaller element and indicates the right position of pivot found so far
		int i = low - 1;
		for (int j = low; j <= high - 1; j++) {
			// If current element is smaller than the pivot
			if (arr[j] < pivot) {
				// Increment index of smaller element
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, high);
		return (i + 1);
	}

	private static void type1() {
		int[] arr = {12, 10, 11, 13, 21, 24, 25, 20, 9};
		int n = arr.length;
		quicksort1(arr, 0, n - 1);
		PrintUtl.print(arr);
	}

	private static void quicksort1(int[] arr, int low, int high) {
		if (low < high) {
			// after each partition method one item is placed in its actual position
			// and on its left every item is smaller
			// and on its right every item is greater
			int partitionIndex = partition1(arr, low, high);
			// after partitionIndex element is placed on so we can do the same for
			// low to partitionIndex-1 and partitionIndex+1 to high
			quicksort1(arr, low, partitionIndex - 1);
			quicksort1(arr, partitionIndex + 1, high);
		}
	}

	private static int partition1(int[] arr, int low, int high) {
		// we are setting first item as pivot
		int pivot = arr[low];
		int start = low;
		int end = high;
		while (start < end) {
			// this loop will break once there is item greater than pivot
			while (arr[start] <= pivot && start <= high - 1)
				start++;
			// this loop will break once there is item lesser than pivot
			while (arr[end] > pivot && end >= low)
				end--;
			// once we found two indices we will swap two items
			// i > j means there are two partitions already made
			if (start < end)
				swap(arr, start, end);
		}
		// after the iteration low+1 to j is lesser than pivot
		swap(arr, end, low);
		return end;
	}


}
