package com.problems.array;

import java.util.HashMap;
import java.util.Map;

/*
 * Problem link :
 * https://leetcode.com/problems/single-number/description/
 * https://neetcode.io/problems/single-number
 * https://www.geeksforgeeks.org/problems/find-the-odd-occurence4820/1
 * https://www.naukri.com/code360/problems/find-the-single-element_6680465
 * https://www.naukri.com/code360/problems/one-odd-occurring_4606074
 *
 * Solution link :
 * https://www.youtube.com/watch?v=bYWLJb3vCWY&t=1369s
 * https://takeuforward.org/arrays/find-the-number-that-appears-once-and-the-other-numbers-twice/
 *
 * https://www.youtube.com/watch?v=qMPX1AOa83k
 * https://neetcode.io/solutions/single-number
 */

// Tags: Hashing, Bit Manipulation
public class FindTheNumberAppearsOnlyOnce {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// todo optimize approach
	// as all the numbers appear twice,
	// and we know that a^a =0
	// we can use this property
	private static void type2() {
		int[] nums = { 4, 1, 2, 1, 2 };
		int xor = singleNumber2(nums);
		System.out.println(xor);
	}

	private static int singleNumber2(int[] nums) {
		int xor = 0;
		for (int num : nums)
			xor = xor ^ num;
		return xor;
	}

	// todo brute force approach
	// time complexity O(n)
	// space complexity O(n)
	private static void type1() {
		int[] nums = { 4, 1, 2, 1, 2 };
		int ans = singleNumber1(nums);
		System.out.println(ans);
	}

	private static int singleNumber1(int[] nums) {
		Map<Integer, Integer> freq = new HashMap<>();
		for (int num : nums) {
			freq.put(num, freq.getOrDefault(num, 0) + 1);
		}
		int ans = -1;
		for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
			if (entry.getValue() == 1) {
				return entry.getKey();
			}
		}
		return -1;
	}
}
