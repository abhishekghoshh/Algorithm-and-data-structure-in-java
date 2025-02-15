package com.problems.recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
 * Problem link :
 * https://leetcode.com/problems/permutation-sequence/
 * https://www.naukri.com/code360/problems/1112626
 * Solution link :
 * https://www.youtube.com/watch?v=wT7gcXLYoao&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=56
 *
 * https://takeuforward.org/data-structure/find-k-th-permutation-sequence/
 */
public class KthPermutationSequence {


	// TODO check the solution again
	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// iterative way
	private static void type3() {
		int n = 4;
		int k = 19;

		// 0 to n - 1 factorial

		ArrayList<Integer> nums = new ArrayList<>();
		nums.add(1);

		int[] fact = new int[n];
		fact[0] = 1;

		for (int i = 1; i < n; i++) {
			nums.add(i + 1);
			fact[i] = fact[i - 1] * i;
		}

		StringBuilder result = new StringBuilder();
		int pos;
		for (int i = n - 1; i >= 0; i--) {
			// getting the position
			pos = (k - 1) / fact[i];
			// appending the num to the result
			result.append(nums.get(pos));
			// now updating the k again
			k = k - (pos * fact[i]);
			// we will remove the num from the num array
			nums.remove(pos);
		}
		System.out.println(result);
	}

	// suppose we have a number 1234, this number will have 24 permutations
	// out of which 1st 6 numbers will be starting with 1, second 6 numbers will be starting from 2 and so on
	// so if we take 10 th number, we know one thing that it will start from 2
	// so first item 2 is fixed remaining number is 134
	// now we know we have 6 numbers and out of which 1st 2 numbers starts with 1,
	// second 2 numbers starts with, this we can find the remaining
	private static void type2() {
		int n = 4;
		int k = 19;
		List<Integer> numbers = new ArrayList<>();
		int fact = 1;
		for (int i = 1; i <= n; i++) {
			numbers.add(i);
			fact = fact * i;
		}
		StringBuilder answer = new StringBuilder();
		// as the group will be formed in 0 indexing formats
		// like 0 to n-1 so subtracting k to 1
		computeKthPermutation(numbers, fact, answer, k - 1);
		System.out.println(answer);
	}

	private static void computeKthPermutation(List<Integer> num, int fact, StringBuilder sb, int k) {
		if (num.isEmpty()) return;
		// currently factorial is n!
		// where n is the size of numbers, so there will be n groups of (n-1)!
		// numbers, if we divide it by numbers.size() then we will find (n-1)!
		fact = fact / num.size();
		// now we will find in which group it will fall
		int pos = k / fact;
		sb.append(num.get(pos));
		// once we get that number, we will remove that from numbers
		// let our number is 1234 and the position is 2, so we will take 3 and remove it,
		// so we will have 124
		num.remove(pos);
		// now we will find our next group
		k = k % fact;
		computeKthPermutation(num, fact, sb, k);
	}

	// TODO first explain this approach
	// brute force will be to find all the permutation and then store it in list then, then return n-1
	private static void type1() {
		int n = 4;
		int k = 19;

		// find all the permutations
		char[] arr = new char[n];
		for (int i = 1; i <= n; i++)
			arr[i - 1] = (char) ('0' + i);
		List<String> answer = new ArrayList<>();
		computePermutation1(arr, 0, answer);
		Collections.sort(answer);

		// get the kth permutation
		System.out.println(answer.get(k - 1));
	}

	private static void computePermutation1(char[] array, int start, List<String> answer) {
		int n = array.length;
		if (start == n) {
			answer.add(new String(array));
			return;
		}
		for (int i = start; i < n; i++) {
			swap(array, i, start);
			computePermutation1(array, start + 1, answer);
			swap(array, i, start);
		}
	}

	private static void swap(char[] array, int left, int right) {
		char ch = array[left];
		array[left] = array[right];
		array[right] = ch;
	}
}
