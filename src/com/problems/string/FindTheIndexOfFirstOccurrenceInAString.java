package com.problems.string;
/*
 * Problem link :
 * https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=JoF0Z7nVSrA
 *
 * https://neetcode.io/solutions/find-the-index-of-the-first-occurrence-in-a-string
 */

public class FindTheIndexOfFirstOccurrenceInAString {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// todo with kmp algorithm
	private static void type2() {
		String haystack = "mississippi", needle = "issip";
		int index = strStr2(haystack, needle);
		System.out.println(index);
	}

	public static int strStr2(String haystack, String needle) {
		String res = needle + "#" + haystack;
		int n1 = needle.length();
		char[] arr = res.toCharArray();
		int i = 1, j = 0, n = arr.length;
		int[] pattern = new int[n];
		while (i < n) {
			if (arr[i] == arr[j]) {
				pattern[i] = j + 1;
				if (j == n1 - 1) return (i - 2 * n1);
				i++;
				j++;
			} else if (j > 0) {
				j = pattern[j - 1];
			} else {
				i++;
			}
		}
		return -1;
	}


	// brute force
	private static void type1() {
		String haystack = "mississippi", needle = "issip";
		int index = strStr1(haystack, needle);
		System.out.println(index);
	}

	public static int strStr1(String haystack, String needle) {
		char[] str = haystack.toCharArray(), ptrn = needle.toCharArray();
		int i = 0, j = 0;
		int n1 = str.length, n2 = ptrn.length;
		if (n2 > n1) return -1;
		while (i < n1) {
			if (str[i] == ptrn[j]) {
				j++;
			} else {
				i -= j;
				j = 0;
			}
			if (j == n2) return i - j + 1;
			i++;
		}
		return -1;
	}

}
