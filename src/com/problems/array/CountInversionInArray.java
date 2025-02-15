package com.problems.array;

/*
 * 
 * problem links :
 * https://www.naukri.com/code360/problems/number-of-inversions_6840276?leftPanelTabValue=PROBLEM
 * https://www.naukri.com/code360/problems/615
 * https://www.naukri.com/code360/problems/count-inversions_615
 * https://www.naukri.com/code360/problems/count-inversions_615
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=AseUmwVNaoY
 * https://www.youtube.com/watch?v=kQ1mJlwW-c0
 *
 * https://takeuforward.org/data-structure/count-inversions-in-an-array/
 * */

import static com.util.PrintUtl.print;

/*
 * Description:
 * A pair ('ARR[i]', 'ARR[j]') is said to be an inversion when:
	1. 'ARR[i] > 'ARR[j]' 
	2. 'i' < 'j'
 * Where 'i' and 'j' denote the indices ranging from [0, 'N').
 * */
public class CountInversionInArray {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	private static void type3() {

	}

	// merge sort approach
	// time complexity O(n*log(n))
	// space complexity o(n)
	// if we don't have to change the original array then
	// we can copy the original and compute on that
	// we will divide the array
	// and on the merge we will do our operation
	// before merging we will have two sorted array like 2,3,5 and 1,4
	// so we can definitely find ARR[i] > 'ARR[j] from two arrays
	// if we are taking anything from right array
	// that means everything on left array is greater than item on right array
	// like when left =0 and right=0 on upper array
	// among 2 and 1 we are taking 1 and it is from right array
	// so all element of left will be greater than right top
	private static void type2() {
		int[] nums = { 2, 5, 1, 3, 4 };
		Value value = new Value();
		applyMergeSort(nums, 0, nums.length - 1, value);
		print(nums);
		System.out.println("Count is " + value.data);

	}

	public static class Value {
		int data = 0;
	}

	private static void applyMergeSort(int[] nums, int i, int j, Value value) {
		if (i < j) {
			int mid = (i + j) / 2;
			applyMergeSort(nums, i, mid, value);
			applyMergeSort(nums, mid + 1, j, value);
			mergeWithComputation(nums, i, mid, j, value);
		}
	}

	private static void mergeWithComputation(int[] nums, int i, int mid, int j, Value value) {
		int[] array = new int[j - i + 1];
		int left = i, right = mid + 1;
		int index = 0;
		while (left <= mid && right <= j) {
			if (nums[left] <= nums[right]) {
				array[index++] = nums[left++];
			} else {
				// here the element on right array is small
				// so we will be doing the calculation
				value.data = value.data + (mid - left + 1);
				array[index++] = nums[right++];
			}
		}
		while (left <= mid) {
			array[index++] = nums[left++];
		}
		while (right <= j) {
			array[index++] = nums[right++];
		}
		for (index = 0; index < j - i + 1; index++) {
			nums[index + i] = array[index];
		}
	}

	// brute force
	// time complexity O(n^2)
	// space complexity O(1)
	private static void type1() {
		int[] nums = { 2, 5, 1, 3, 4 };
		int count = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] > nums[j]) {
					count++;
				}
			}
		}
		System.out.println("Count is " + count);
	}


}
