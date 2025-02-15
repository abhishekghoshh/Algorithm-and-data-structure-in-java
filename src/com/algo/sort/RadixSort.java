package com.algo.sort;

import com.util.PrintUtl;

import static com.util.ArrayUtil.*;

/*
 * Problem link :
 * 
 * 
 * Solution link :
 * https://www.geeksforgeeks.org/radix-sort/
 * 
 */
public class RadixSort {

	public static void main(String[] args) {
		type1();
	}

	private static void type1() {
		int[] arr = {121, 432, 564, 23, 1, 45, 788};
		int max = max(arr);
		// Apply counting sort to sort elements based on place value.
		for (int place = 1; max / place > 0; place *= 10)
			countingSort(arr, arr.length, place, max);
		PrintUtl.print(arr);
	}

	private static void countingSort(int[] arr, int n, int place, int max) {
		int[] output = new int[n + 1];
		int[] count = new int[max + 1];
		// Calculate count of elements
		for (int i = 0; i < n; i++) {
			int digit = (arr[i] / place) % 10;
			count[digit]++;
		}
		// Calculate cumulative count
		for (int i = 1; i < 10; i++)
			count[i] += count[i - 1];
		// Place the elements in sorted order
		for (int i = n - 1; i >= 0; i--) {
			int digit = (arr[i] / place) % 10;
			int item = count[digit];
			output[item - 1] = arr[i];
			count[item]--;
		}
		copy(arr, output);
		PrintUtl.print(arr);
	}



}
