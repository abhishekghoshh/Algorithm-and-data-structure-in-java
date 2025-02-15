package com.problems.recursion;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem links:
 *
 * Solution link
 *
 * */
public class PrintSubsetSumDivisibleByK {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// this will make the total count
	private static void type2() {
		int[] nums = { -3, -2, 1, 0, 1, 2, 3 };
		int k = 3;
		int count = traverse(0, 0, nums, k) - 1;// -1 for empty subset
		System.out.println(count);

	}

	private static int traverse(int i, int sum, int[] nums, int k) {
		if (i == nums.length)
			return (sum % k == 0) ? 1 : 0;
		return traverse(i + 1, sum + nums[i], nums, k)
				+ traverse(i + 1, sum, nums, k);
	}

	private static void type1() {
		int[] nums = { -3, -2, 1, 0, 1, 2, 3 };
		int k = 3;
		List<List<Integer>> answer = new ArrayList<>();
		List<Integer> bucket = new ArrayList<>();
		traverse(0, 0, bucket, nums, answer, k);
		System.out.println(answer);
		System.out.println(answer.size());
	}

	private static void traverse(int i, int sum, List<Integer> bucket, int[] nums,
								 List<List<Integer>> answer, int k) {
		if (i == nums.length) {
			// empty subset not considered
			if (sum % k == 0 && !bucket.isEmpty())
				answer.add(new ArrayList<>(bucket));
			return;
		}
		// choosing ith element to be a part of the answer
		bucket.add(nums[i]);
		traverse(i + 1, sum + nums[i], bucket, nums, answer, k);
		bucket.remove(bucket.size() - 1);
		// choosing ith element not to be a part of the answer
		traverse(i + 1, sum, bucket, nums, answer, k);
	}

}
