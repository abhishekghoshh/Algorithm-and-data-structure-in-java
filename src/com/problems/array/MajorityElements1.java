package com.problems.array;

import java.util.HashMap;
import java.util.Map;

/*
 * Problem link:
 * https://leetcode.com/problems/majority-element/
 * https://www.codingninjas.com/codestudio/problems/842495
 * https://www.codingninjas.com/studio/problems/majority-element_6783241
 *
 * Solution link:
 * https://www.youtube.com/watch?v=nP_ns3uSh80
 * https://www.youtube.com/watch?v=AoX3BPWNnoE&list=PLgUwDviBIf0rPG3Ictpu74YWBQ1CaBkm2&index=17
 * https://www.youtube.com/watch?v=OtCsBK7e4rk
 *
 * Https://takeuforward.org/data-structure/find-the-majority-element-that-occurs-more-than-n-2-times/
 *
 */

// Tags : Arrays, Boyer Moore's voting algorithm
public class MajorityElements1 {

	/*
	 * The majority element is the element that appears more than n / 2 times.
	 * You may assume that the majority element always exists in the array.
	 */
	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// moore's voting algorithm
	// let x is majority element means x is least n/2 + 1
	// so after all the calculation of count++ and count--
	// there will least be one element for which count will not be 0 at last
	// time complexity O(n)
	// space complexity O(1)
	private static void type3() {
		int[] nums = { 2, 2, 1, 1, 1, 2, 2 };
		int answer = majorityElement3(nums);
		System.out.println("Item is " + answer);
	}

	private static int majorityElement3(int[] nums) {
		int ans = 0;
		int count = 0;
		for (int num : nums) {
			if (count == 0) {
				count = 1;
				ans = num;
			} else {
				if (ans == num) {
					count++;
				} else {
					count--;
				}
			}
		}
		return ans;
	}

	// frequency map approach
	// time complexity O(n)
	// space complexity O(n)
	private static void type2() {
		int[] nums = { 2, 2, 1, 1, 1, 2, 2 };
		int num = majorityElement2(nums);
		System.out.println("Item is " + num);
	}

	private static int majorityElement2(int[] nums) {
		int n = nums.length;
		Map<Integer, Integer> freq = new HashMap<>();
		// calculating the freq of the elements
		for (int num : nums)
			freq.put(num, 1 + freq.getOrDefault(num, 0));

		for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
			int num = entry.getKey(), f = entry.getValue();
			if (f > n / 2) return num;
		}
		return -1;
	}

	// brute force approach
	// time complexity O(n^2)
	// space complexity O(1)
	// checking all the numbers and check their frequencies
	private static void type1() {
		int[] nums = { 2, 2, 1, 1, 1, 2, 2 };
		int ans = majorityElement1(nums);
		System.out.println(ans);
	}

	private static int majorityElement1(int[] nums) {
		int n = nums.length / 2;
		for (int num1 : nums) {
			int count = 0;
			for (int num2 : nums)
				if (num1 == num2) count++;
			if (count > n) return num1;
		}
		return -1;
	}

}
