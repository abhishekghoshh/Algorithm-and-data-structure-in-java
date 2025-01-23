package com.problems.binarysearch;

/*
 * Problem link :
 * https://www.codingninjas.com/studio/problems/rotation_7449070
 *
 * Solution link :
 * https://www.youtube.com/watch?v=jtSiWTPLwd0
 *
 * https://takeuforward.org/arrays/find-out-how-many-times-the-array-has-been-rotated/
 * */
public class NoOfTimesSortedArrayRotated {
	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// same as previous
	private static void type3() {
		int[] arr = {4, 5, 6, 7, 0, 1, 2, 3};
		int low = 0, high = arr.length - 1;
		int ans = Integer.MAX_VALUE;
		int index = -1;
		while (low <= high) {
			//search space is already sorted
			//then arr[low] will always be
			//the minimum in that search space:
			if (arr[low] <= arr[high]) {
				if (arr[low] < ans) {
					index = low;
					ans = arr[low];
				}
				break;
			}
			int mid = (low + high) / 2;
			//if left part is sorted:
			if (arr[low] <= arr[mid]) {
				// keep the minimum:
				if (arr[low] < ans) {
					index = low;
					ans = arr[low];
				}
				// Eliminate left half:
				low = mid + 1;
			} else {
				//if the right part is sorted:
				// keep the minimum:
				if (arr[mid] < ans) {
					index = mid;
					ans = arr[mid];
				}
				// Eliminate right half:
				high = mid - 1;
			}
		}
		System.out.println(index);
	}

	// binary search method
	private static void type2() {
		int[] nums = {4, 5, 6, 7, 0, 1, 2, 3};
		int low = findKRotation(nums);
		System.out.println(low);
	}

	private static int findKRotation(int[] nums) {
		int n = nums.length;
		int low = 0, high = n - 1;
		int next, prev, mid;
		while (low <= high) {
			mid = low + ((high - low) >> 1);
			next = next(mid, n);
			prev = prev(mid, n);
			// if the mid is less than its next and prev, that means it is the starting element
			if (nums[prev] >= nums[mid] && nums[mid] <= nums[next]) {
				low = mid;
				break;
			} else if (nums[low] <= nums[mid] && nums[mid] <= nums[high]) {
				// low to high portion is sorted, so the answer will be low we can break here
				break;
			} else if (nums[low] <= nums[mid]) {
				// it is on the left side low to mid-elements are sorted
				low = mid + 1;
			} else {
				// it is on the right side mid to high elements are sorted
				high = mid - 1;
			}
		}
		return low;
	}

	// brute force
	private static void type1() {
		int[] nums = { 7, 8, 9, 10, 11, 0, 1, 2, 3, 4, 5, 6 };
		int n = nums.length, next, prev, index = -1;
		for (int i = 0; i < n; i++) {
			next = next(i, n);
			prev = prev(i, n);
			if (nums[i] < nums[next] && nums[i] < nums[prev]) {
				index = i;
			}
		}
		System.out.println(index);
	}

	private static int prev(int i, int n) {
		return (i - 1 + n) % n;
	}

	private static int next(int i, int n) {
		return (i + 1) % n;
	}
}
