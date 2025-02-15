package com.problems.string;

/*
 * Problem link:
 * https://leetcode.com/problems/splitting-a-string-into-descending-consecutive-values/description/
 *
 * Solution link:
 * https://www.youtube.com/watch?v=eDtMmysldaw
 * https://neetcode.io/solutions/splitting-a-string-into-descending-consecutive-values
 * */

public class SplittingStringIntoDescendingConsecutiveValues {
	/*
	 * The Problem is: Check if we can split s into two or more non-empty
	 * substrings such that the numerical values of the substrings are in
	 * descending order and the difference between numerical values of every
	 * two adjacent substrings is equal to 1
	 * */
	public static void main(String[] args) {
		type1(); // todo explain this in the interview
		type2();
	}


	// todo we are using iteration here
	// time complexity O(n^2)
	// iteratively
	// space complexity O(1)
	// first, we will need a seed sum for starting
	// so from left to right we are adding number one number is calculating the
	// value as of now, like for 0098 -> value is 98
	// once we find any non-zero number we will check that if value-1 item is
	// present on the right side, if present then we are initializing the sum again
	// then we are checking value value-2 present or not, we are checking until left!=n
	// if the currentSum + 1 > previousSum then there will be no value afterward
	// as we are adding current sum for each element
	private static void type2() {
		String s = "919089088";
		boolean ans = splitString2(s);
		System.out.println(ans);
	}

	private static boolean splitString2(String s) {
		char[] arr = s.toCharArray();
		int n = arr.length;
		long sum = 0L;
		for (int i = 0; i < n; i++) {
			sum = sum * 10 + (arr[i] - '0');
			if (sum == 0) continue; // if the sum is 0, then we will skip
			int right = i + 1;
			long prev = sum;
			long curr = 0;
			// now we will traverse the remaining string
			while (right < n) {
				curr = curr * 10 + (arr[right++] - '0');
				// if prev is lesser than (curr+1), then we will return false
				if (prev < curr + 1) break;
				if (curr + 1 == prev) {
					// if curr is 0 but this is not the last element, then we will skip we will wait for more 0s at the end
					if (curr == 0 && right != n) continue;
					// if we are at the end, then we will return true
					if (right == n) return true;
					// else we will reinitialize the prev and curr
					prev = curr;
					curr = 0;
				}
			}
		}
		return false;
	}

	// todo most optimized approach
	// using the backtracking
	// same as type1
	// we will not stack here,
	// we will the recursion stack here
	// if we want to store the results then we can just store it in a list
	private static void type1() {
		String s = "21474836482147483647";
		boolean ans = splitString1(s);
		System.out.println(ans);
	}

	private static boolean splitString1(String s) {
		char[] arr = s.toCharArray();
		int n = arr.length;
		long sum = 0;
		// we are starting from the first element we will start once there is any non-zero sum
		for (int i = 0; i < n; i++) {
			sum = sum * 10 + (arr[i] - '0');
			if (sum == 0) continue; // if the sum is 0, then we will skip
			if (splitString1(sum, i + 1, n, arr))
				return true;
		}
		return false;
	}

	private static boolean splitString1(long prev, int start, int n, char[] arr) {
		long curr = 0;
		for (int i = start; i < n; i++) {
			curr = curr * 10 + (arr[i] - '0');
			// if prev is lesser than (curr+1), then we will return false
			if (prev < curr + 1) return false;
			// either we have reached to the ending of the string or we will start the next traverse call from here
			if (prev == curr + 1
					&& (i == n - 1 || splitString1(curr, i + 1, n, arr)))
				return true;
		}
		return false;
	}

}
