package com.problems.array;

import java.util.HashMap;
import java.util.Map;

/*
 * Problem link:
 * https://leetcode.com/problems/subarray-sum-equals-k/description/
 * https://www.naukri.com/code360/problems/subarray-sums-i_1467103
 * 
 * Solution link:
 * https://www.youtube.com/watch?v=xvNwoz-ufXA
 * https://www.youtube.com/watch?v=fFVZt-6sgyo
 *
 * https://takeuforward.org/arrays/count-subarray-sum-equals-k/
 * https://neetcode.io/solutions/subarray-sum-equals-k
 * */

// Tags : Arrays, hashing, prefix sum
public class CountOfSubarraySumEqualsK {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// same as the previous
	// but here we will make the code little crisp
	private static void type3() {
		int[] nums = {1, 2, 3};
		int k = 3;
		int count = subarraySum3(nums, k);
		System.out.println(count);
	}

	private static int subarraySum3(int[] nums, int k) {
		int count = 0;
		int sum = 0;
		Map<Integer, Integer> prefixSum = new HashMap<>();
		prefixSum.put(0, 1);// zero prefix sum for empty sub array
		for (int num : nums) {
			sum += num;
			int rem = sum - k;
			// if a reminder exists that mean k also exists
			// the number of prefix sum of a reminder is equals the number prefix sum of k
			count += prefixSum.getOrDefault(rem, 0);
			// on every index we are updating the prefix sum count
			prefixSum.put(sum, 1 + prefixSum.getOrDefault(sum, 0));
		}
		return count;
	}

	// prefix sum approach
	// time complexity O(n)
	// space complexity O(n)
	// suppose in a range of 0..x1..x2 the sum is x2
	// and in the same range 0..x1 the xor is x1
	// the xor of in between elements(x1+1..x2) is k
	// we can say that x1 + k = x2 => x1 = x2 - k
	// now we have everything just we have to find x1
	// we will compute sum in every element and store it in map with its count
	// Please Note
	// prefixSumMap.put(0, 1); and if(sum==k) count++ has the same purpose
	// if we include prefixSumMap.put(0, 1) then, at sum==k and reminder will be 0
	// then count = count + prefixSumMap.get(reminder); it will be automatically
	// added if we add if(sum==k) count++ then we will manually check for k
	// equality, at that time prefixSum.containsKey(0) will return false
	// count = count + prefixSumMap.get(0);; will not be executed
	private static void type2() {
		int[] nums = { 1, 2, 3 };
		int k = 3;
		int count = subarraySum2(nums, k);
		System.out.println(count);
	}

	private static int subarraySum2(int[] nums, int k) {
		int sum = 0, count = 0;
		Map<Integer, Integer> prefixSum = new HashMap<>();
		prefixSum.put(0, 1);// zero prefix sum for empty sub array
		for (int num : nums) {
			sum += num;
			int rem = sum - k;
			// if a reminder exists that mean k also exists
			// the number of prefix sum of a reminder is equals the number prefix sum of k
			if (prefixSum.containsKey(rem))
				count += prefixSum.get(rem);
			// on every index we are updating the prefix sum count
			if (!prefixSum.containsKey(sum))
				prefixSum.put(sum, 1);
			else
				prefixSum.put(sum, 1 + prefixSum.get(sum));
		}
		return count;
	}

	// brute force approach using two loops
	// time complexity O(n^2)
	// space complexity O(1)
	private static void type1() {
		int[] nums = { 1, 2, 3 };
		int k = 3;
		int count = subarraySum1(nums, k);
		System.out.println(count);
	}

	private static int subarraySum1(int[] nums, int k) {
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			int sum = 0;
			for (int j = i; j < nums.length; j++) {
				sum = sum + nums[j];
				if (sum == k)
					count++;
			}
		}
		return count;
	}
}
