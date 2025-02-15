package com.problems.array;

import java.util.Arrays;

import static com.util.ArrayUtil.swap;
import static com.util.PrintUtl.print;

/*
 * problem link:
 * https://leetcode.com/problems/merge-sorted-array/description/
 * https://www.naukri.com/code360/problems/1214628
 *
 * 
 * Solution link:
 * https://www.youtube.com/watch?v=n7uwj04E0I4
 * https://www.youtube.com/watch?v=hVl2b3bLzBw&list=PLgUwDviBIf0rPG3Ictpu74YWBQ1CaBkm2&index=5
 *
 * https://takeuforward.org/data-structure/merge-two-sorted-arrays-without-extra-space/
 *
 *
 * https://www.youtube.com/watch?v=P1Ic85RarKY
 *
 * https://neetcode.io/solutions/merge-sorted-array
 * */

//1st -> brute force approach
//2nd -> two pointer
//3rd -> Insertion sort approach
//4th -> Gap Method
public class MergeTwoSortedArraysWithoutExtraSpace {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
	}

	// Gap Method
	// without extra space swap sort approach
	// time complexity O(size*log(size))
	private static void type4() {
		int[] nums1 = { 1, 2, 4, 5, 6, 7, 8, 0, 0 };
		int[] nums2 = { 3, 9 };
		int m = nums1.length - nums2.length;
		int n = nums2.length;
		merge4(n, nums1, m, nums2);
		print(nums1);
	}

	private static void merge4(int n, int[] nums1, int m, int[] nums2) {
		if (n >= 0)
			System.arraycopy(nums2, 0, nums1, m, n);
		int length = (m + n);
		int gap = length;
		int left, right;
		while (gap != 0) {
			gap = (int) Math.ceil((double) gap / 2);
			left = 0;
			right = left + gap;
			while (right < length) {
				if (nums1[left] > nums1[right]) {
					swap(nums1, nums1, left, right);
				}
				left++;
				right++;
			}
		}
	}

	// todo explain this in the interview
	// two pointer approach
	// using a 3rd array for holding the answer
	// size of the array will be m+n
	// time complexity will be o(m+n)
	private static void type2() {
		int[] nums1 = {1, 3, 4, 0, 0, 0};
		int[] nums2 = { 2, 5, 6 };
		int m = 3;
		int n = 3;
		merge3(m, n, nums1, nums2);
		print(nums1);
	}

	private static void merge3(int m, int n, int[] nums1, int[] nums2) {
		int N = m + n;
		int[] nums = new int[N];
		// 2 pointer on 2 arrays and taking the lowest element and incrementing the pointers
		int i = 0, j = 0, k = 0;
		while (i < m && j < n) {
			if (nums1[i] < nums2[j]) {
				nums[k++] = nums1[i++];
			} else {
				nums[k++] = nums2[j++];
			}
		}
		// if there are any elements remaining the first array
		while (i < m) nums[k++] = nums1[i++];
		// if there are any elements remaining the second array
		while (j < n) nums[k++] = nums2[j++];
		// copying back to num1 again
		for (k = 0; k < N; k++) nums1[k] = nums[k];
	}

	// without extra space
	// insertion sort approach
	// time complexity O(m*n)
	private static void type3() {
		int[] nums1 = { 1, 3, 4 };
		int m = 3;
		int[] nums2 = { 2, 5, 6 };
		int n = 3;
		merge2(m, nums1, nums2, n);
		print(nums1);
	}

	private static void merge2(int m, int[] nums1, int[] nums2, int n) {
		int right, temp = 0;
		for (int i = 0; i < m; i++) {
			if (nums1[i] > nums2[0]) {
				swap(nums1, nums2, i, 0);
				right = 0;
				temp = nums2[0];
				while (right < n - 1 && temp > nums2[right + 1]) {
					nums2[right] = nums2[right + 1];
					right++;
				}
				nums2[right] = temp;
			}
		}
	}


	// without extra space
	// brute force approach
	// time complexity O(log(m+n))
	private static void type1() {
		int[] nums1 = { 1, 3, 4, 0, 0, 0 };
		int m = 3;
		int[] nums2 = { 2, 5, 6 };
		int n = 3;

		merge1(n, nums1, m, nums2);
		print(nums1);
	}

	private static void merge1(int n, int[] nums1, int m, int[] nums2) {
		if (n >= 0)
			System.arraycopy(nums2, 0, nums1, m, n);
		Arrays.sort(nums1);
	}


}
