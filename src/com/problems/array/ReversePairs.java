package com.problems.array;

/*
 * 
 * problem links :
 * https://leetcode.com/problems/reverse-pairs/
 * https://www.codingninjas.com/codestudio/problems/1112652
 * https://www.codingninjas.com/studio/problems/team-contest_6840309
 *
 *
 * Solution link :
 * https://www.youtube.com/watch?v=0e4bZaP3MDI
 * https://www.youtube.com/watch?v=S6rsAlj_iB4&list=PLgUwDviBIf0rPG3Ictpu74YWBQ1CaBkm2&index=20
 *
 * https://takeuforward.org/data-structure/count-reverse-pairs/
 */

// Tags: merge-technique-from-merge-sort
public class ReversePairs {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// TODO optimal approach
	//  A reverse pair is a pair (i, j) where 0 <= i < j < nums.length and nums[i] > 2 * nums[j].
	// in the merge function of the merge sort we will compute the reverse pairs
	// we will have two sorted array left and right
	// we will use two pointer, we will move the right pointer
	// till the point we get nums1[left] > 2 * nums2[right]
	// suppose our two array is 5,7,9 and 3,4
	// for 3 it will start from 7,
	// as we now both array are sorted, so anything after 7 will also satisfy the upper condition
	// once we find for 3 then we search for 4
	// we will not start again from the start of the left array for the 4
	// because for 3 it start from 7, so for 4 it can not start previous of 7
	// thus we will find the answer for all sub arrays in total
	// TODO we can use Data class or we can use a static variable or even we can just return the count from
	//  the merge function as it is not returning anything
	private static void type2() {
		int[] nums = { 1, 3, 2, 3, 1 };
		int n = nums.length;
		int[] copy = new int[n];
		Data counter = new Data();
		partition(nums, 0, n - 1, copy, counter);
		System.out.println("count is " + counter.data);
	}

	private static void partition(int[] nums, int i, int j, int[] copy, Data counter) {
		if (i >= j) return;
		int mid = (i + j) / 2;
		partition(nums, i, mid, copy, counter);
		partition(nums, mid + 1, j, copy, counter);
		merge(nums, i, mid, j, copy, counter);
	}

	private static void merge(int[] nums, int start, int mid, int end, int[] copy, Data counter) {
		// we will be doing the computing of the reverse pair here
		int i1 = start, i2 = mid + 1;
		// the first pointer on a right side array
		// total complexity of this loops will go O(leftSize + rightSize) => O(n)
		while (i2 <= end) {
			// holding to long in case it overflows while multiplying by 2
			// incrementing left until we find nums[left] > 2 * nums[right].
			while (i1 <= mid && nums[i1] <= 2 * (long) nums[i2]) i1++;
			// if i1 exceeds mid, that means there is no element in the first array
			// for which nums[i1] > 2 * (long) nums[i2] holds
			// so, we will simply break the loop
			if (i1 > mid) break;
			// else we will update the counter,
			// the reverse pair count for the element will be (n-i1+1)
			counter.data += (mid - i1 + 1);
			i2++;
		}
		// now we will merge the first sorted array and second sorted array
		// and save it to an intermediate copy array
		i1 = start;
		i2 = mid + 1;
		int n = end - start + 1;
		int i = 0;
		while (i1 <= mid && i2 <= end) {
			if (nums[i1] <= nums[i2]) copy[i++] = nums[i1++];
			else copy[i++] = nums[i2++];
		}
		while (i1 <= mid) copy[i++] = nums[i1++];
		while (i2 <= end) copy[i++] = nums[i2++];
		// now we are again copying back to the original array
		for (i = 0; i < n; i++) nums[i + start] = copy[i];
	}

	private static class Data {
		int data = 0;
	}

	// brute force approach
	// time complexity O(n^2)
	// space complexity O(1)
	private static void type1() {
		int[] nums = { 1, 3, 2, 3, 1 };
		int n = nums.length;
		int count = 0;
		for (int i = 0; i < n - 1; i++)
			for (int j = i + 1; j < n; j++)
				if (nums[i] > 2 * (long) nums[j]) count++;

		System.out.println("count is " + count);
	}

}
