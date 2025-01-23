package com.algo.string;

import java.util.Arrays;

import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/
 * https://www.codingninjas.com/codestudio/problems/1112619
 *
 * Solution link :
 * pepcoding : https://www.youtube.com/watch?v=QlwzsWs0oyc
 * 
 */
public class ZFunction {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// Z array or lps array
	// Longest-Prefix-Suffix Array
	private static void type2() {
		String text = "aaxaabcaaaaxaabcaa";
		char[] st = text.toCharArray();
		int[] lps = new int[st.length];
		int i = 1, j = 0;
		while (i < st.length) {
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
		print(lps);
	}

	// kmp algorithm
	private static void type1() {
		String str = "aaxaabcaaaaxaabcaa";
		String ptrn = "aabcaa";

		char[] st = (ptrn + "&" + str).toCharArray();
		int[] lps = new int[st.length];

		int i = 1, j = 0;
		while (i < st.length) {
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
		int[] zArray = Arrays.copyOfRange(lps, ptrn.length() + 1, lps.length);
		print(zArray);
	}



}
