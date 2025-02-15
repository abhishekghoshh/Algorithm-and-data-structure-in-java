package com.problems.bitmanipulation;

import com.util.PrintUtl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://leetcode.com/problems/single-number-iii/description/
 * https://www.geeksforgeeks.org/problems/two-numbers-with-odd-occurrences5846/1
 * https://www.naukri.com/code360/problems/two-numbers-with-odd-occurrences_8160466
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=faoVORjd-T8
 * https://neetcode.io/solutions/single-number-iii
 */
public class FindTwoIntegerThatComeOnceInArray {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}


	// todo optimized approach
	//  time complexity O(2n)
	//  space complexity O(1)
	//  suppose our array is 1, 1, 2, 2, 3, 4, 4, 5
	//  in xor it will be 3^5 => 011 ^ 101 and remaining num will be cancelled
	//  011 ^ 101 => 110
	//  if we look closely then we will find there should be some 1 bit
	//  in xor result any bit will be 1 only when one of the bit is 1 and other one is 0
	//  suppose in ith bit there is 1
	//  so we can create a mask where all the bit is 0 except the ith bit
	//  and will create two bucket some element will fall in the first and some will fall in the second bucket
	//  but if the single elements are x and y then we will surely know that they will not fall in the same bucket
	//  remaining elements are in twice, so it does not matter they fall in which bucket
	//  eventually they will become 0 as num^num = 0 and 0 is identity in xor operation
	private static void type3() {
		int[] nums = { 1, 1, 2, 2, 3, 4, 4, 5 };
		int[] ans = singleNumber3(nums);
		PrintUtl.print(ans);
	}

	private static int[] singleNumber3(int[] nums) {
		// xor will hold the xor value of all the elements
		int xor = 0;
		for (int num : nums) {
			xor = xor ^ num;
		}
		// we will extract the right most set bit which will also act as the mask
		// ~(xor - 1) can be written as -xor
		int mask = xor & (-xor); // getting the first 1 bit from the right
		int bucket1 = 0, bucket2 = 0;
		for (int num : nums)
			if ((num & mask) == 0)
				bucket1 = bucket1 ^ num;
			else
				bucket2 = bucket2 ^ num;

		return new int[]{bucket1, bucket2};
	}

	// todo brute force approach using set
	private static void type2() {
		int[] arr = {2, 4, 1, 3, 2, 4};
		int[] ans = singleNumber2(arr);
		print(ans);
	}

	private static int[] singleNumber2(int[] nums) {
		Set<Integer> set = new HashSet<>();
		for (int num : nums) {
			if (set.contains(num))
				set.remove(num);
			else
				set.add(num);
		}
		int[] ans = new int[2];
		int i = 0;
		for (int num : set) ans[i++] = num;
		return ans;
	}

	// todo brute force
	// time complexity O(2n)
	// space complexity O(n)
	private static void type1() {
		int[] nums = { 1, 1, 2, 2, 3, 4, 4, 5 };
		int[] list = singleNumber1(nums);
		PrintUtl.print(list);
	}

	private static int[] singleNumber1(int[] nums) {
		Map<Integer, Integer> freq = new HashMap<>();
		for (int num : nums) {
			freq.put(num, freq.getOrDefault(num, 0) + 1);
		}
		int[] ans = new int[2];
		int i = 0;
		for (Map.Entry<Integer, Integer> entry : freq.entrySet())
			if (entry.getValue() == 1)
				ans[i++] = entry.getKey();

		return ans;
	}

}
