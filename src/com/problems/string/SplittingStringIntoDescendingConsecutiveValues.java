package com.problems.string;

import java.util.Stack;

/*
 * Problem link:
 * https://leetcode.com/problems/splitting-a-string-into-descending-consecutive-values/
 *
 *
 *
 * */
// Problem is : Check if we can split s into two or more non-empty
// substrings such that the numerical values of the substrings are in
// descending order and the difference between numerical values of every
// two adjacent substrings is equal to 1
public class SplittingStringIntoDescendingConsecutiveValues {
	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// using the backtracking
	// same as type1
	// we will not stack here,
	// we will the recursion stack here
	private static void type3() {
		String s = "21474836482147483647";
		char[] arr = s.toCharArray();
		int n = arr.length;
		long sum = 0;
		boolean isPossible = false;
		for (int i = 0; i < n; i++) {
			sum = sum * 10 + (arr[i] - '0');
			if (sum != 0 && traverse(sum, i + 1, n, arr)) {
				isPossible = true;
				break;
			}
		}
		System.out.println("is possible " + isPossible);
	}

	private static boolean traverse(long previousSum, int current, int n, char[] arr) {
		long sum = 0;
		for (int i = current; i < n; i++) {
			sum = sum * 10 + (arr[i] - '0');
			if (sum + 1 > previousSum) return false;
			// either we have reached to the ending of the string or we will start the next traverse call from here
			if (sum + 1 == previousSum && (i == n - 1 || traverse(sum, i + 1, n, arr))) return true;
		}
		return false;
	}

	// time complexity O(n^2)
	// iteratively
	// space complexity O(1)
	private static void type2() {
		// String s = "21474836482147483647";
		// String s ="0000000001098700600050000000400003000020001";
		String s = "919089088";
		int n = s.length(), left;
		long previousSum, currentSum, sum = 0L;
		boolean isPossible = false;
		Stack<Long> stack = new Stack<>();
		for (int i = 0; i < n; i++) {
			// first we will need a seed sum for starting
			// so from left to right we are adding number one number are calculating the
			// value as of now , like for 0098 -> value is 98
			sum = sum * 10 + (s.charAt(i) - '0');
			stack.push(sum);
			if (sum != 0) {
				// once we find any non zero number we will check that if value-1 item is
				// present on the right side, if present then we are initializing the sum again
				// then we are checking value value-2 present or not, we are checking until
				// left!=n
				// if the currentSum + 1 > previousSum then there will be no value afterwards
				// as we are adding current sum for each element
				left = i + 1;
				previousSum = sum;
				currentSum = 0;
				while (left < n) {
					currentSum = currentSum * 10 + (s.charAt(left) - '0');
					left++;
					if (currentSum + 1 > previousSum) break;
					if (currentSum + 1 == previousSum) {
						if (currentSum == 0 && left != n) continue;
						stack.push(currentSum);
						if (left == n) isPossible = true;
						previousSum = currentSum;
						currentSum = 0;
					}
				}
			}
			if (isPossible) break;
			stack.removeAllElements();
		}
		System.out.println("is possible " + isPossible);
		System.out.println("numbers are " + stack);
	}

	// using backtracking
	// time complexity O(n^2)
	private static void type1() {
		String s = "21474836482147483647";
		char[] arr = s.toCharArray();
		int n = arr.length;
		long sum = 0;
		Stack<Long> stack = new Stack<>();
		boolean isPossible = false;
		for (int i = 0; i < n; i++) {
			sum = sum * 10 + (arr[i] - '0');
			if (sum != 0) {
				stack.push(sum);
				isPossible = traverse(sum, i + 1, n, arr, stack);
				if (isPossible) break;
				else stack.pop();
			}
		}
		System.out.println("is possible " + isPossible);
		System.out.println("numbers are " + stack);
	}

	private static boolean traverse(long previousSum, int current, int n, char[] arr, Stack<Long> stack) {
		long sum = 0;
		for (int i = current; i < n; i++) {
			sum = sum * 10 + (arr[i] - '0');
			if (sum + 1 > previousSum) return false;
			if (sum + 1 == previousSum) {
				stack.push(sum);
				if (i == n - 1 || traverse(sum, i + 1, n, arr, stack)) return true;
				stack.pop();
			}
		}
		return false;
	}

}
