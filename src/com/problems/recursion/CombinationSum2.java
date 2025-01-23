package com.problems.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Problem links:
 * https://leetcode.com/problems/combination-sum-ii/
 * https://www.naukri.com/code360/problems/1112622
 *
 * Solution link
 * https://www.youtube.com/watch?v=G1fRTGRxXU8
 *
 * https://takeuforward.org/data-structure/combination-sum-ii-find-all-unique-combinations/
 * */
public class CombinationSum2 {
	/*
	 * Given a collection of candidate numbers (candidates) and a target number
	 * (target), find all unique combinations in candidates where the candidate
	 * numbers sum to target. Each number in candidates may only be used once in the
	 * combination. Note: The solution set must not contain duplicate combinations.
	 * 
	 * The output will be in lexicographical order
	 */
	public static void main(String[] args) {
		type1();
		type2();
	}

	// As there are some repeat elements in given an array we have to follow this subsequence approach,
	// we will only pick unique elements in a loop
	private static void type2() {
		int[] nums = { 10, 1, 2, 7, 6, 1, 5 };
		int target = 8;
		// we will sort the array then all the duplicate elements will come one after another
		Arrays.sort(nums);
		List<List<Integer>> answer = new ArrayList<>();
		List<Integer> bucket = new ArrayList<>();
		combinationSum2(nums, 0, target, bucket, answer);
		System.out.println(answer);
	}

	private static void combinationSum2(int[] nums, int n, int remaining,
										List<Integer> bucket, List<List<Integer>> answer) {
		// if the remaining is 0, then we do not have to check it further, we can directly return
		if (remaining == 0) {
			answer.add(new ArrayList<>(bucket));
			return;
		}
		for (int i = n; i < nums.length; i++) {
			// we will check if the current element is the same as the previous element or not
			if (i != n && nums[i] == nums[i - 1]) continue;
			// if the current element is greater than the target, then there is no point to check
			// index + 1 elements as the array is sorted
			if (nums[i] > remaining) return;
			bucket.add(nums[i]);
			combinationSum2(nums, i + 1, remaining - nums[i], bucket, answer);
			bucket.remove(bucket.size() - 1);
		}
	}

	// If there is no duplicate value in a given array
	private static void type1() {
		int[] candidates = { 10, 1, 2, 7, 6, 5 };
		int target = 8;
		// for early computation, we will sort the array
		Arrays.sort(candidates);
		List<List<Integer>> answer = new ArrayList<>();
		List<Integer> bucket = new ArrayList<>();
		combinationSum1(candidates, 0, target, bucket, answer);
		System.out.println(answer);
	}

	private static void combinationSum1(int[] candidates, int index, int target, List<Integer> bucket,
										List<List<Integer>> answer) {
		if (target == 0) answer.add(new ArrayList<>(bucket));
		// if index is length and target is not zero, so we are not capable to make target
		if (index == candidates.length) return;
		// we will add the element if it is less than the current target,
		// and we know that if the item is not capable, then index+1 element will also unable to make it
		if (candidates[index] <= target) {
			// not choosing the element
			combinationSum1(candidates, index + 1, target, bucket, answer);
			// choosing the element
			bucket.add(candidates[index]);
			combinationSum1(candidates, index + 1, target - candidates[index], bucket, answer);
			bucket.remove(bucket.size() - 1);
		}
	}
}
