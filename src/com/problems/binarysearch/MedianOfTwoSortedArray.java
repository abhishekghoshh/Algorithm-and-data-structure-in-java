package com.problems.binarysearch;

/*
 * Problem link :
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * https://www.codingninjas.com/studio/problems/median-of-two-sorted-arrays_985294
 * https://www.codingninjas.com/codestudio/problems/985294
 *
 * Solution is :
 * https://www.youtube.com/watch?v=NTop3VTjmxk&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=66
 *
 *
 * https://takeuforward.org/data-structure/median-of-two-sorted-arrays-of-different-sizes/
 * */
public class MedianOfTwoSortedArray {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
	}

	// same as previous just it looks clean
	private static void type4() {
		int[] nums1 = {2, 4, 6, 8};
		int[] nums2 = {1, 3, 5, 7};
		double answer = findMedian(nums1, nums2);
		System.out.println(answer);
	}

	public static double findMedian(int[] a, int[] b) {
		int n1 = a.length, n2 = b.length;
		//if n1 is bigger swap the arrays:
		if (n1 > n2) return findMedian(b, a);
		int n = n1 + n2; //total length
		int left = (n1 + n2 + 1) / 2; //length of left half
		//apply binary search:
		int low = 0, high = n1;
		while (low <= high) {
			int mid1 = (low + high) / 2;
			int mid2 = left - mid1;
			//calculate l1, l2, r1 and r2;
			int l1 = (mid1 > 0) ? a[mid1 - 1] : Integer.MIN_VALUE;
			int l2 = (mid2 > 0) ? b[mid2 - 1] : Integer.MIN_VALUE;
			int r1 = (mid1 < n1) ? a[mid1] : Integer.MAX_VALUE;
			int r2 = (mid2 < n2) ? b[mid2] : Integer.MAX_VALUE;

			if (l1 <= r2 && l2 <= r1) {
				if (n % 2 == 1) return Math.max(l1, l2);
				else return ((double) (Math.max(l1, l2) + Math.min(r1, r2))) / 2.0;
			} else if (l1 > r2) high = mid1 - 1;
			else low = mid1 + 1;
		}
		return 0; //dummy statement
	}

	// TODO check this solution one more time
	// binary search approach
	// we will try to find the partition from both of the array
	// such that the numbers in the left portion and the number in the right portion
	// follows this rule count(nums1[0..left1])+count(nums2[0..left2]) will be equal
	// to count(nums1[left1..])+count(nums2[left2..])
	// also nums1[left1]<=nums2[left2+1] and nums2[left2]<=nums1[left1+1]
	private static void type3() {
		int[] nums1 = { 2, 4, 6, 8 };
		int[] nums2 = { 1, 3, 5, 7 };
		double answer = findMedian(nums1, nums2, nums1.length, nums2.length);
		System.out.println(answer);
	}

	// time complexity is O(log(min(n1,n2)))
	// space complexity O(1)
	private static double findMedian(int[] nums1, int[] nums2, int n1, int n2) {
		// we are assuming that the first array is always less in size
		if (n1 > n2) return findMedian(nums2, nums1, n2, n1);
		// low = 0 means we are not taking any elements from 1st array as the element of
		// the 1st array are higher than the second array so element in the first array
		// will fall after the median
		// high=n1 means we are not taking any element from the first array as the
		// element of the first array are less than the 2nd array so all the element of
		// the first array will fall before the median position
		// here we are not finding the index rather we are trying to find the cut that's
		// why we took high = n1 rather than n1-1
		int low = 0, high = n1;
		// we are taking median = (n1 + n2 + 1) / 2 because it will work for both it
		// will work for both, when (n1+n2) is even or odd
		// lets say n1=5 and n2=6 then (n1+n2+1)/2 => 6 and we know that 6th element
		// will be median and by our code we know that by our cutting logic 6th element
		// will be in the left portion that's why we will return Math.max(l1,l2)
		// when n1=6 and n2=6 then also (n1+n2+1)/2 =>6 and we know here median will be
		// (6th element + 7th element)/2, and we know by our cut logic in the left side
		// there will be 6 elements and in the right side also there will be 6 elements
		// so we will return max from the left and min from the right
		int l1, l2, r1, r2, median = (n1 + n2 + 1) / 2, cut1, cut2;
		while (low <= high) {
			// cut1 means we are picking cut1 elements from the firsts array
			cut1 = low + (high - low) / 2;
			cut2 = median - cut1;
			// 0 means no element from first array
			l1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1 - 1];
			l2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2 - 1];
			// we are assuming that n1 is less than or equal to n2
			// cut1 can be n1 means all the element of of 1st array is less than all the
			// elements of 2nd array and if n1<n2 then essentially we have consumed the
			// first array and the median can be found in the second array
			// else n1==n2 then the median will be (nums1[last] + nums2[first])/2
			r1 = cut1 == n1 ? Integer.MAX_VALUE : nums1[cut1];
			// cut2 can only be n2 when n1 equal n2 and all the elements of 2nd array is
			// less than all the element of 1st array
			r2 = cut2 == n2 ? Integer.MAX_VALUE : nums2[cut2];

			// we know one thing that as the array is sorted then l1 will always be less
			// than r1 and l2 will always be less than r2, but in the median l1 will also be
			// less than the r2 and l2 will also be less than r1, that will make a perfect
			// partition
			if (l1 <= r2 && l2 <= r1)
				if ((n1 + n2) % 2 == 0) return ((double) Math.max(l1, l2) + Math.min(r1, r2)) / 2;
				else return Math.max(l1, l2);
			else if (l1 > r2) high = cut1 - 1;
			else low = cut1 + 1;
		}
		return 0;
	}

	// same as previous just here we are not creating any other array
	// we are using extra variable
	// merge approach but without using the array
	// space complexity O(1)
	private static void type2() {
		int[] nums1 = { 2, 4, 6, 8 };
		int[] nums2 = { 1, 3, 5, 7 };

		int n1 = nums1.length, n2 = nums2.length, left = 0, right = 0;
		int n = n1 + n2;
		double answer = -1, prev = -1;
		int medianIndex = (n1 + n2) / 2;
		while (left < n1 && right < n2 && (left + right) <= medianIndex) {
			prev = answer;
			if (nums1[left] <= nums2[right]) answer = nums1[left++];
			else answer = nums2[right++];
		}
		while (left < n1 && (left + right) <= medianIndex) {
			prev = answer;
			answer = nums1[left++];
		}
		while (right < n2 && (left + right) <= medianIndex) {
			prev = answer;
			answer = nums2[right++];
		}
		if (n % 2 == 0) answer = (answer + prev) / 2;

		System.out.println(answer);
	}

	// merge algorithm in merge sort approach in merge sort
	// time complexity O(n1+n2)
	// space complexity O(n1+n2)
	private static void type1() {
		int[] nums1 = { 2, 4, 6, 8 };
		int[] nums2 = { 1, 3, 5, 7 };

		int n1 = nums1.length, n2 = nums2.length, left = 0, right = 0, i = 0;
		int n = n1 + n2;
		double answer;
		int[] nums = new int[n1 + n2];
		while (left < n1 && right < n2)
			if (nums1[left] <= nums2[right]) nums[i++] = nums1[left++];
			else nums[i++] = nums2[right++];
		while (left < n1) nums[i++] = nums1[left++];
		while (right < n2) nums[i++] = nums2[right++];

		if (n % 2 == 0) answer = ((double) nums[n / 2 - 1] + nums[n / 2]) / 2;
		else answer = nums[n / 2];
		print(nums);
		System.out.println(answer);
	}

	private static void print(int[] nums) {
		for (int num : nums) {
			System.out.print(num + " ");
		}
		System.out.println();
	}

}
