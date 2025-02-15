package com.problems.binarysearch;

import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://www.naukri.com/code360/problems/k-th-element-of-2-sorted-array_1164159
 * https://www.naukri.com/code360/problems/1112629
 * https://www.geeksforgeeks.org/problems/k-th-element-of-two-sorted-array1317/1
 * 
 * Solution is :
 * https://www.youtube.com/watch?v=D1oDwWCq50g
 * https://www.youtube.com/watch?v=nv7F4PiLUzo&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=67
 *
 * https://takeuforward.org/data-structure/k-th-element-of-two-sorted-arrays/
 * https://www.geeksforgeeks.org/k-th-element-two-sorted-arrays/
 * */
public class KthElementOfTwoSortedArrays {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
	}

	// same as previous just this code is much cleaner
	// TODO best solution using binary search approach
	private static void type4() {
		int[] arr1 = {2, 4, 6, 8};
		int[] arr2 = {1, 3, 5, 7};
		int k = 7;

		int answer = kthElement(arr1, arr2, arr1.length, arr2.length, k);
		System.out.println(answer);
	}

	static int kthElement(int[] arr1, int[] arr2, int n1, int n2, int k) {
		if (n1 > n2) return kthElement(arr2, arr1, n2, n1, k);
		int low = Math.max(0, k - n1), high = Math.min(k, n2);
		while (low <= high) {
			int cut1 = (low + high) >> 1;
			int cut2 = k - cut1;
			int l1 = cut1 == 0 ? Integer.MIN_VALUE : arr1[cut1 - 1];
			int l2 = cut2 == 0 ? Integer.MIN_VALUE : arr2[cut2 - 1];
			int r1 = cut1 == n2 ? Integer.MAX_VALUE : arr1[cut1];
			int r2 = cut2 == n1 ? Integer.MAX_VALUE : arr2[cut2];

			if (l1 <= r2 && l2 <= r1) return Math.max(l1, l2);
			else if (l1 > r2) high = cut1 - 1;
			else low = cut1 + 1;
		}
		return -1;
	}

	// binary search approach
	// we can also use the heap approach
	// but as the arrays are in sorted order so we can leverage this thing
	// time complexity O(log(min(n1,n2)))
	// space complexity O(1)
	private static void type3() {
		int[] arr1 = { 2, 4, 6, 8 };
		int[] arr2 = { 1, 3, 5, 7 };
		int k = 7;

		int answer = findKthElement(arr1, arr2, arr1.length, arr2.length, k);
		System.out.println(answer);
	}

	private static int findKthElement(int[] arr1, int[] arr2, int n1, int n2, int k) {
		if (n1 > n2) {
			return findKthElement(arr2, arr1, arr2.length, arr1.length, k);
		}
		// we always assume that n1 will be less than equal to n2
		// here also we will try to implement the cut logic just like the find median

		// at min we may not take any element from 1st array and
		// at max we can take all the elements from 1st array

		// if k is greater than n2 then we have to take some elements from the 1st array
		// so that time low will not be start from 0, rather we have to pick atleast
		// k-n2 element the 1st array
		int low = k > n2 ? k - n2 : 0;

		// if k less than first array then we dont required n1 elements rather k element
		// will be needed at worst situation, so the high will be k not n1 at that time
		int high = k < n1 ? k : n1;
		while (low <= high) {
			int cut1 = low + (high - low) / 2;
			int cut2 = k - cut1;
			int l1 = cut1 == 0 ? Integer.MIN_VALUE : arr1[cut1 - 1];
			int l2 = cut2 == 0 ? Integer.MIN_VALUE : arr2[cut2 - 1];
			int r1 = cut1 == n1 ? Integer.MAX_VALUE : arr1[cut1];
			int r2 = cut2 == n2 ? Integer.MAX_VALUE : arr2[cut2];

			if (l1 <= r2 && l2 <= r1) {
				return Math.max(l1, l2);
			} else if (l1 > r2) {
				high = cut1 - 1;
			} else {
				low = cut1 + 1;
			}
		}
		return -1;
	}

	// merge approach but without using the array
	// time complexity O(k)
	// space complexity O(1)
	private static void type2() {
		int[] arr1 = { 2, 4, 6, 8 };
		int[] arr2 = { 1, 3, 5, 7 };
		int k = 3;
		int n = arr1.length, m = arr2.length;
		int left = 0, right = 0;
		int answer = -1;
		while (left < n && right < m && (left + right) <= (k - 1)) {
			if (arr1[left] <= arr2[right]) {
				answer = arr1[left++];
			} else {
				answer = arr2[right++];
			}
		}
		while (left < n && (left + right) <= (k - 1)) {
			answer = arr1[left++];
		}
		while (right < m && (left + right) <= (k - 1)) {
			answer = arr2[right++];
		}
		System.out.println(answer);
	}

	// merge approach in merge sort
	// time complexity O(n1+n2)
	// space complexity O(n1+n2)
	private static void type1() {
		int[] arr1 = { 2, 4, 6, 8 };
		int[] arr2 = { 1, 3, 5, 7 };
		int k = 5;
		int n1 = arr1.length, n2 = arr2.length, left = 0, right = 0, index = 0;
		int[] nums = new int[n1 + n2];
		while (left < n1 && right < n2) {
			if (arr1[left] <= arr2[right]) {
				nums[index++] = arr1[left++];
			} else {
				nums[index++] = arr2[right++];
			}
		}
		while (left < n1) {
			nums[index++] = arr1[left++];
		}
		while (right < n2) {
			nums[index++] = arr2[right++];
		}
		print(nums);
		System.out.println(nums[k - 1]);
	}

}
