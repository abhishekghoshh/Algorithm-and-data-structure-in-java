package com.algo.sort;

import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://practice.geeksforgeeks.org/problems/insertion-sort/1
 * https://www.geeksforgeeks.org/problems/insertion-sort/0
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=HGk_ypEuS24&t=1900s
 *
 * https://takeuforward.org/data-structure/insertion-sort-algorithm/
 */
public class InsertionSort {

	public static void main(String[] args) {
		type1();
	}

	private static void type1() {
		int[] arr = { 24, 18, 38, 43, 14, 40, 1, 54 };
		int n = arr.length;
		for (int i = 1; i < n; i++) {
			int pivot = arr[i];
			int j = i;
			while (j > 0 && pivot < arr[j - 1]) {
				arr[j] = arr[j - 1];
				j--;
			}
			arr[j] = pivot;
		}
		print(arr);
	}
}
