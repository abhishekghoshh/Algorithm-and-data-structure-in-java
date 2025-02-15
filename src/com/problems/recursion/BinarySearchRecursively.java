package com.problems.recursion;

/*
 * Problem link :
 *
 *
 * Solution link :
 *
 *
 */
public class BinarySearchRecursively {

	public static void main(String[] args) {
		type1();
	}

	private static void type1() {
		int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
		int key = 5;
		int index = find(arr, key, 0, arr.length - 1);
		System.out.println(index);
	}

	private static int find(int[] arr, int target, int start, int end) {
		// if start > end, then we will return -1
		if (start > end) return -1;
		// now we will partition the array
		// start to (mid-1) and mid and (mid+1) to end
		int mid = start + ((end - start) >> 1);
		// if target is in mid then we will return mid
		if (arr[mid] == target) return mid;
			// if it is lesser, then we will go to the left else go to the right section
		else if (arr[mid] > target)
			return find(arr, target, start, mid - 1);
		else
			return find(arr, target, mid + 1, end);
	}

}
