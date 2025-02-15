package com.problems.slidingwindow;

import java.util.HashSet;
import java.util.Set;

/*
 * Problem link:
 * https://leetcode.com/problems/subarrays-with-k-different-integers/
 * 
 * Solution:
 * https://www.youtube.com/watch?v=akwRFY2eyXs
 * https://www.youtube.com/watch?v=CBSeilNvZHs
 *
 * https://www.youtube.com/watch?v=etI6HqWVa8U
 * https://neetcode.io/solutions/subarrays-with-k-different-integers
 * */

// Please check the problem SubarrayWithAtMostKDifferentIntegers first
public class SubarrayWithExactlyKDifferentIntegers {

	public static void main(String[] args) {
		type1();
		type2(); // todo discuss it in the interview
		type3();
	}

	// TODO best solution in the leetcode
	/*
    Now, let's walk through the code with the example:

    Initialize the variables:

    result: Initially set to 0, it will store the count of subarrays with k distinct elements.
    size: Set to the length of the input array nums, which is 5 in this example.
    count: An array to count the occurrences of elements. It is initialized to all zeros.
    Enter a loop to iterate through the elements of nums. The loop uses three pointers: left, mid, and right.

    As we iterate through the array:

    We increment the count of the current element in the count array.
    If a new distinct element is encountered (its count becomes 1), we reduce k.
    If k becomes negative, it means we have more than k distinct elements, so we move the left and mid pointers and reset the counts until we have exactly k distinct elements.
    After ensuring that we have k distinct elements, we count subarrays by calculating mid - left + 1 and add this count to the result.

    Continue this process until we've gone through all the elements in the array.

    In the example with [1, 2, 1, 2, 3] and k = 2, the code will find and count subarrays with exactly 2 distinct elements. The result will be the count of such subarrays, which is 6.
    */
	private static void type3() {
		int[] nums = {1, 2, 1, 2, 3};
		int k = 2;
		int result = 0; // Initialize the result variable.
		int size = nums.length; // Get the size of the input array.
		int[] count = new int[size + 1]; // Initialize an array to count the occurrences of elements.

		for (int left = 0, mid = 0, right = 0; right < size; ++right) {
			if (++count[nums[right]] == 1) {
				// If a new distinct element is encountered, reduce k.
				if (--k < 0) {
					count[nums[mid++]] = 0; // Move the left pointer and reset the count.
					left = mid;
				}
			}
			if (k <= 0) {
				while (count[nums[mid]] > 1) {
					// Decrease the count for elements until only one occurrence is left.
					--count[nums[mid++]];
				}
				// Add the count of subarrays with k distinct elements to the result.
				result += mid - left + 1;
			}
		}
		System.out.println(result);
	}


	// TODO explain this in the interview
	// time complexity O(2n)
	// sliding window
	// if we know how to calculate Subarrays with at most K Different Integers
	// then our work is pretty easy
	// we will calculate Subarrays with at most K Different Integers for k and k-1
	// and if we subtract SubarrayWithAtMostKDifferentIntegers of k and k-1
	// then we will find Subarrays with exactly K Different Integers

	// todo we know how to find count of the subarray whose sum is less than equal to target
	//  but we need exactly how many subarrays whose sum is equal to target
	//  if we find the count for target and subtract to the count of target-1
	//  then we will get subarrays count whose sum is equal to target
	private static void type2() {
		int[] nums = {1, 2, 1, 2, 3};
		int k = 2;
		int count = subarraysWithKDistinct2(nums, k);
		System.out.println(count);
	}

	private static int subarraysWithKDistinct2(int[] nums, int k) {
		return getCount(nums, k) - getCount(nums, k - 1);
	}

	private static int getCount(int[] nums, int k) {
		int n = nums.length;
		int[] freq = new int[n + 1];
		int left = 0, num, leftItem, distinct = 0;
		int count = 0;
		for (int right = 0; right < n; right++) {
			num = nums[right];
			// if f is 0 then it is a new number in the range
			if (freq[num] == 0) distinct++;
			freq[num]++; // updating the freq
			// if distinct count is greater thatz
			while (left < n && distinct > k) {
				leftItem = nums[left++];
				freq[leftItem]--;
				if (freq[leftItem] == 0) distinct--;
			}
			count += right - left + 1;
		}
		return count;
	}

	// brute force
	// time complexity O(n^2)
	private static void type1() {
		int[] nums = {1, 2, 1, 2, 3};
		int k = 2;
		int count = subarraysWithKDistinct1(nums, k);
		System.out.println(count);
	}

	private static int subarraysWithKDistinct1(int[] nums, int k) {
		int count = 0, n = nums.length;
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (set.size() == k && !set.contains(nums[j])) break;
				set.add(nums[j]);
				if (set.size() == k) count++;
			}
			set.clear();
		}
		return count;
	}

}
