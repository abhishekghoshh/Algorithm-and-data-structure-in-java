package com.problems.binarysearch;
/*
 * Problem link :
 * 
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=3RhGdmoF_ac&list=PL_z_8CaSLPWeYfhtuKHj-9MpYb6XQJ_f2&index=15
 * 
 * */

public class ClosestElementInASortedArray {

	public static void main(String[] args) {
		type2();
	}

	// This is a trick question
	// in the last iteration if the value is not in the array then low and high
	// index represents the ceiling and floor of the key
	// at the last iteration
	// at this point low > high
	// that's why the loop got terminated
	// where high element is the floor
	// and low element is the ceiling
	private static void type2() {
		int[] arr = { 10, 20, 30, 50, 60, 80, 110, 130, 140, 170 };
		int target = 60;
		int[] triplets = search(arr, target);
		int index = triplets[0], low = triplets[1], high = triplets[2];
		int minDifference = 0;
		if (index != -1) minDifference = Math.min(arr[low] - target, target - arr[high]);
		System.out.println("The value of minimum difference is " + minDifference);
	}

	private static int[] search(int[] arr, int target) {
		int low = 0, high = arr.length - 1, mid;
		while (low <= high) {
			mid = low + ((high - low) >> 1);
			if (arr[mid] == target) return new int[]{mid, low, high};
			else if (arr[mid] < target) low = mid + 1;
			else high = mid - 1;
		}
		return new int[]{-1, low, high};
	}

}
