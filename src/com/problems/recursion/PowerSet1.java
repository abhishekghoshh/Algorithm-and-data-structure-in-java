package com.problems.recursion;

import com.util.PrintUtl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * Problem links:
 * https://leetcode.com/problems/subsets/description/
 * https://neetcode.io/problems/subsets
 * https://www.naukri.com/code360/problems/print-subsequences_8416366
 * https://www.naukri.com/code360/problems/unique-subsets_3625236
 * https://www.naukri.com/code360/problems/subset-sum_3843086
 * https://www.geeksforgeeks.org/problems/power-set4302/1
 *
 * Solution link
 * https://www.youtube.com/watch?v=b7AYbpM5YrE
 *
 * https://www.youtube.com/watch?v=Yg5a2FxU4Fo&list=PL_z_8CaSLPWeT1ffjiImo0sYTcnLzo-wY&index=12
 * https://www.youtube.com/watch?v=lfFqW1DTsqM&list=PL_z_8CaSLPWeT1ffjiImo0sYTcnLzo-wY&index=13
 *
 * https://takeuforward.org/data-structure/power-set-print-all-the-possible-subsequences-of-the-string/
 * https://takeuforward.org/data-structure/subset-sum-sum-of-all-subsets/
 *
 *
 * https://www.youtube.com/watch?v=REOH22Xwdkk
 * https://neetcode.io/solutions/subsets
 * */

public class PowerSet1 {
	// Similar problem AllCombination problem
	// All words made by the word in lexicographic order
	// ba -> "", "a","ab","b"
	// Note,Substring is where all the elements are in order and continuous
	// subsequence is where all the elements are in order but not continuous
	// subset is where all the elements are not in order and not continuous
	// TODO this is only applicable when there is not repetitive element present in the array
	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
	}

	// TODO this will generate all the sum using bit manipulation technique
	//  check out this solution later
	private static void type4() {
		int[] nums = {7, -9, 15, -2};
		int n = nums.length;
		int[] allSum = powerSet(nums, 0, n - 1);
		PrintUtl.print(allSum);
	}

	// check this solution later
	private static int[] powerSet(int[] nums, int start, int end) {
		int[] array = new int[1 << (end - start + 1)];
		for (int i = start; i <= end; i++) {
			int offset = 1 << (i - start);
			for (int j = 0; j < offset; j++) {
				array[j + offset] = array[j] + nums[i];
			}
		}
		return array;
	}
	// let's say our array is 1,2,3, n=3,
	// so we will run a loop from 0 to 2^n -1
	// find the binary of the number i
	// suppose i => 101,
	// so we will add the arr[index] such that binary[index]=1
	// 101 means we will add 1 and 3
	// time complexity O(n*2^n)
	// space complexity O(n)
	private static void type3() {
		int[] nums = {1, 2, 3};
		int n = nums.length;
		// the size of power is 2^n, so we will run a loop 0 to 2^n-1
		int bound = 1 << n;
		List<List<Integer>> answer = new ArrayList<>();
		for (int num = 0; num < bound; num++) {
			List<Integer> bucket = new LinkedList<>();
			for (int place = 0; place < n; place++) {
				int bit = (num >> place) & 1;
				if (bit == 1) bucket.add(nums[place]);
			}
			answer.add(bucket);
		}
		System.out.println(answer);
	}

	// Using backtracking
	private static void type2() {
		int[] nums = {1, 2, 3};
		List<List<Integer>> answer = new ArrayList<>();
		LinkedList<Integer> list = new LinkedList<>();
		powerSet2(0, list, answer, nums);
		System.out.println(answer);
	}

	public static void powerSet2(int n, LinkedList<Integer> list, List<List<Integer>> answer, int[] nums) {
		if (n == nums.length) {
			answer.add(new LinkedList<>(list));
			return;
		}
		// here we are not adding it
		powerSet2(n + 1, list, answer, nums);

		// here we will add it and we will remove it at the last
		list.add(nums[n]);
		powerSet2(n + 1, list, answer, nums);
		list.removeLast();
	}

	// only unique characters
	private static void type1() {
		String str = "abc";
		List<String> answer = new ArrayList<>();
		powerSet1(new StringBuilder(), 0, str, answer);
		System.out.println(answer);
	}

	// we have two options either to choose it or not
	private static void powerSet1(StringBuilder sb, int n, String str, List<String> answer) {
		if (n == str.length()) {
			answer.add(sb.toString());
			return;
		}
		// here we are not choosing it to be a part of the answer
		powerSet1(sb, n + 1, str, answer);
		// here we are choosing the element to a part of the answer
		sb.append(str.charAt(n));
		powerSet1(sb, n + 1, str, answer);
		// as previous is a StringBuilder, so we are changing the actual object, so we
		// need to delete the last character which we have added previously
		sb.deleteCharAt(sb.length() - 1);
	}
}
