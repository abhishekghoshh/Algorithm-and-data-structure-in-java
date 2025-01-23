package com.algo.string;

import java.util.Arrays;

/*
 * Problem link :
 * https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/
 * https://www.codingninjas.com/codestudio/problems/1112621
 * 
 * Solution link :
 * pepcoding : https://www.youtube.com/watch?v=__Cu92rei1s
 *
 * neetcode : https://www.youtube.com/watch?v=JoF0Z7nVSrA
 * 
 */
public class KMPAlgorithm {

	// 28. Find the Index of the First Occurrence in a String
	public static void main(String[] args) {
		type1();
		type2();
	}

	// thought process is little different
	// here we are thinking in terms of longest prefix suffix array
	// check pepcoding for explanation
	private static void type2() {
		String string = "aaxaabcaaaaxaabcaa";
		String pattern = "aabcaa";

		char[] st = (pattern + "&" + string).toCharArray();
		int n1 = st.length;
		int n2 = pattern.length();
		int[] lps = new int[n1];

		int i = 1, j = 0;
		while (i < n1) {
			if (st[i] == st[j]) {
				lps[i] = j + 1;
				i++;
				j++;
			} else if (j > 0) {
				j = lps[j - 1];
			} else {
				i++;
			}
		}
		int count = 0;
		for (int num : lps) count += num == n2 ? 1 : 0;

		System.out.println(count);
	}

	// TODO fix this solution
	// it is not working
	// check algoexpert for explanation
	private static void type1() {
		String str = "aaxaabcaaaax";
		String ptrn = "aabcaa";
		char[] s1 = str.toCharArray();
		char[] s2 = ptrn.toCharArray();

		int answer = -1;

		int n2 = s2.length;
		int n1 = s1.length;

		int[] pattern = new int[n2];
		Arrays.fill(pattern, -1);

		// finding pattern array
		int i = 1, j = 0;
		while (i < n2) {
			if (s2[i] == s2[j]) {
				pattern[i] = j;
				i++;
				j++;
			} else if (j > 0) {
				j = pattern[j - 1] + 1;
			} else {
				i++;
			}
		}

		// operating on actual string
		i = j = 0;
		while (i < n1) {
			if (s1[i] == s2[j]) {
				if (j == n2 - 1) {
					answer = i - n2 + 1;
					break;
				}
				i++;
				j++;
			} else if (j > 0) {
				j = pattern[j - 1] + 1;
			} else {
				i++;
			}
		}
		System.out.println(answer);
	}

}
