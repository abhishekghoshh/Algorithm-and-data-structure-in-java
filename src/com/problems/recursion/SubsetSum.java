package com.problems.recursion;


import java.util.*;
/*
 * Problem links:
 * https://www.naukri.com/code360/problems/subset-sum_3843086
 * https://www.naukri.com/code360/problems/subset-sum_630213
 * https://www.geeksforgeeks.org/problems/subset-sums2234/1
 *
 * Solution link:
 * https://www.youtube.com/watch?v=rYkfBRtMJr8&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=53
 *
 * https://takeuforward.org/data-structure/subset-sum-sum-of-all-subsets/
 * */
public class SubsetSum {

	public static void main(String[] args) {
		type1();
		type2();
	}


	// pick and non-pick method
	// same as previous just that we are taking set
	// so there will be no duplicate sum
	private static void type2() {
		int[] arr = { 1, 2, 1, 3, 2, 4, 5 };
		Set<Integer> answer = new HashSet<>();
		traverse2(arr, 0, 0, answer);
		List<Integer> allSubsetSum = new ArrayList<>(answer);
		Collections.sort(allSubsetSum);
		System.out.println(allSubsetSum);
	}

	private static void traverse2(int[] arr, int i, int sum, Set<Integer> answer) {
		if (i == arr.length) {
			answer.add(sum);
			return;
		}
		// we have 2 choices either to take it or not to tke it
		// here we are not taking it
		traverse2(arr, i + 1, sum, answer);
		// here we are taking the arr[i]
		traverse2(arr, i + 1, sum + arr[i], answer);
	}

	// pick non pick method
	// Given array has non-unique characters
	// brute force approach will be to find the power set and then find all the sum
	// of the sets in power set, but we will not do it
	// we compute the sum while traversing and in that we will wither choose the
	// number, or we don't and save the sum in list
	// at last we will sort the answer list
	// time complexity will be O(2^n) + sort of 2^n elements
	// list may contain some duplicate as 1+2 is 3 and the if we take only 3 then
	// the sum will be 3
	private static void type1() {
		int[] arr = { 5, 1, 2, 3 };
		List<Integer> answer = new ArrayList<>();
		traverse1(arr, 0, 0, answer);
		Collections.sort(answer);
		System.out.println(answer);

	}

	private static void traverse1(int[] arr, int i, int sum, List<Integer> answer) {
		if (i == arr.length) {
			answer.add(sum);
			return;
		}
		// we have 2 choices either to take it or not to tke it
		// here we are not taking it
		traverse1(arr, i + 1, sum, answer);
		// here we are taking the arr[i]
		traverse1(arr, i + 1, sum + arr[i], answer);
	}

}
