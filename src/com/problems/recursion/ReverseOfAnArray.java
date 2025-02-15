package com.problems.recursion;
/*
 * Problem link :
 * https://www.geeksforgeeks.org/problems/reverse-an-array/0
 *
 * Solution link :
 * https://www.youtube.com/watch?v=twuC1F6gLI8
 *
 * https://takeuforward.org/data-structure/reverse-a-given-array/
 */

import static com.util.PrintUtl.print;

public class ReverseOfAnArray {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// using recursive way
	private static void type2() {
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		print(arr);
		reverse(arr, 0, arr.length - 1);
		print(arr);
	}

	private static void reverse(int[] arr, int start, int end) {
		if (start >= end) return;
		// swap the start and end then cal reverse again with start+1 and end-1
		swap(arr, start, end);
		// call the reverse function again
		reverse(arr, start + 1, end - 1);
	}

	// using iterative way
	private static void type1() {
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int left = 0, right = arr.length - 1;
		while (left < right) {
			swap(arr, left++, right--);
		}
		print(arr);
	}

	private static void swap(int[] arr, int left, int right) {
		int temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
	}

}
