package com.problems.recursion;

import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://practice.geeksforgeeks.org/problems/sort-the-array0055/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=AZ4jEY_JAVc&list=PL_z_8CaSLPWeT1ffjiImo0sYTcnLzo-wY&index=6
 * 
 * */
public class SortArray {
	public static void main(String[] args) {
		type1();
	}

	private static void type1() {
		int[] arr = { 5, 2, 7, 1, 6, 9, 8, 3, 4 };
		int n = arr.length;
		print(arr);
		sort(arr, n);
		print(arr);
	}

	private static void sort(int[] arr, int n) {
		// When the size of an array is 1, then we will return
		// as a single element array is already sorted
		if (n == 1) return;
		// sort n-1 elements
		sort(arr, n - 1);
		// now pick the last element that was remaining and place if it's an appropriate place
		int last = arr[n - 1];
		int i = n - 2;
		while (i >= 0 && last < arr[i]) {
			// we will shift the item to the right, till we find the perfect place
			arr[i + 1] = arr[i];
			i--;
		}
		// as last < arr[i] will hold no more, so i+1 is the place for that element
		arr[i + 1] = last;
	}

}
