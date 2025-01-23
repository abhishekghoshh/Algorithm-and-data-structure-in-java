package com.problems.bitmanipulation;

import java.util.*;

import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://leetcode.com/problems/single-number-iii/
 * https://www.codingninjas.com/studio/problems/two-numbers-with-odd-occurrences_8160466
 * https://practice.geeksforgeeks.org/problems/two-numbers-with-odd-occurrences5846/1
 * 
 * Solution link :
 * 
 * 
 */
public class FindTwoIntegerThatComeOnceInArray {

	public static void main(String[] args) {
		type1();
		type1_();
		type2();
		type2_();
	}

	// same as type2
	// check repeat and missing number problem
	private static void type2_() {
		int[] arr = {2, 4, 1, 3, 2, 4};
		int xor = 0;
		for (int num : arr) xor = xor ^ num;
		// if the xor is 0001010100
		// then x ^ y => 0001010100
		// because all other numbers have the even count
		int rightestSetBit = xor & ~(xor - 1);
		int x = 0, y = 0;
		for (int num : arr) {
			if ((num ^ rightestSetBit) == 0) x = x ^ num;
			else y = y ^ num;
		}
		int[] answer = null;
		if (x > y) answer = new int[]{x, y};
		else answer = new int[]{y, x};
		print(answer);
	}



	// optimized approach
	// time complexity O(2n)
	// space complexity O(1)
	// suppose our array is 1, 1, 2, 2, 3, 4, 4, 5
	// in xor it will be 3^5 and remaining num will be cancelled
	// 3^5 => 110
	// if we look closely then we will find there should be some 1 bit
	// in xor result any bit 1 only when one of the bit is 1 and other one is 0
	// suppose in ith bit there is 1
	// so we can create a mask where all the bit is 0 except the ith bit
	// and will create two bucket
	// some element will fall in the first and some will fall in second bucket
	// but if the single elements are x and y then we will surely know that they
	// will not fall in same bucket
	// remaining elements are in twice
	// so it does not matter they fall in which bucket
	// eventually they will beacome 0 as num^num = 0
	// and 0 is identity in xor operation
	private static void type2() {
		int[] nums = { 1, 1, 2, 2, 3, 4, 4, 5 };
		// xor will hold the xor value of all the elements
		int xor = 0;
		for (int num : nums) {
			xor = xor ^ num;
		}
		// we will extract the right most set bit which will also act as the mask
		int mask = xor & (~(xor - 1));
		int bucket1 = 0, bucket2 = 0;
		for (int num : nums)
			if ((num & mask) == 0) bucket1 = bucket1 ^ num;
			else bucket2 = bucket2 ^ num;

		List<Integer> list = List.of(bucket1, bucket2);
		System.out.println(list);
	}

	// brute force approach
	private static void type1_() {
		int[] arr = {2, 4, 1, 3, 2, 4};
		Set<Integer> set = new HashSet<>();
		for (int num : arr) {
			if (set.contains(num)) set.remove(num);
			else set.add(num);
		}
		int[] answer = new int[2];
		int i = 0;
		for (int num : set) answer[i++] = num;
		if (answer[0] < answer[1]) {
			int temp = answer[1];
			answer[1] = answer[0];
			answer[0] = temp;
		}
		print(answer);
	}
	// brute force
	// time complexity O(2n)
	// space complexity O(n)
	private static void type1() {
		int[] nums = { 1, 1, 2, 2, 3, 4, 4, 5 };
		Map<Integer, Integer> freq = new HashMap<>();
		for (int num : nums)
			if (!freq.containsKey(num)) freq.put(num, 1);
			else freq.put(num, freq.get(num) + 1);
		List<Integer> list = new ArrayList<>();
		for (Map.Entry<Integer, Integer> entry : freq.entrySet())
			if (entry.getValue() == 1) list.add(entry.getKey());
		System.out.println(list);
	}

}
