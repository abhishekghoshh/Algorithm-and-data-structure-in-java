package com.algo.sort;

import static com.util.PrintUtl.print;

public class SwapSort {

	public static void main(String[] args) {
		type1();
	}

	private static void type1() {
		int[] nums = {7, 3, 9, 4, 8, 5, 1, 6, 2};
		int n = nums.length;
		int i = 0;
		while (i < n) {
			// it means 'i' is 'i-1'th index,
			// and we don't need to change anything
			if (nums[i] == i + 1) {
				i++;
			} else {
				// we will place the arr[i] to its actual position
				// item should be in the item-1 position
				int item = nums[i];
				swap(nums, i, item - 1);
			}
		}
		print(nums);
	}

	static void swap(int[] arr, int i, int j) {
		int temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;
	}
}
