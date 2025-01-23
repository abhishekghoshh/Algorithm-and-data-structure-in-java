package com.problems.dp;

import java.util.HashMap;
import java.util.Map;
/*
 * Problem link :
 * https://leetcode.com/problems/scramble-string
 * https://www.interviewbit.com/problems/scramble-string/
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=SqA0o-DGmEw&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=40
 * https://www.youtube.com/watch?v=VyHEglhbm-A&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=41
 * 
 */

/*
 * Given a string A, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of A = �great�:


    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
 
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node �gr� and swap its two children, it produces a scrambled string �rgeat�.

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that �rgeat� is a scrambled string of �great�.

Similarly, if we continue to swap the children of nodes �eat� and �at�, it produces a scrambled string �rgtae�.

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that rgtae is a scrambled string of �great�.



Given two strings A and B of the same length, determine if B is a scrambled string of S.
 * 
 * 
 * */
public class ScrambledString {

	// TODO check the leetcode for better solution
	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}


	private static void type3() {
		String s1 = "abcdem";
		String s2 = "baecdm";
		boolean isTrue = isScramble3(s1, s2, new HashMap<>());
		System.out.println(isTrue);
	}

	public static boolean isScramble3(String s1, String s2, Map<String, Boolean> memo) {
		String key = s1 + s2;
		if (memo.containsKey(key)) return memo.get(key);

		if (s1.equals(s2)) {
			memo.put(key, true);
			return true;
		}
		int n = s1.length();
		// we are checking that the both strings have the same letters or not
		// if it has not, then there is no way that they are scrambled
		int[] letters = new int[26];
		for (int i = 0; i < n; i++) {
			letters[s1.charAt(i) - 'a']++;
			letters[s2.charAt(i) - 'a']--;
		}
		for (int i = 0; i < 26; i++) {
			if (letters[i] != 0) {
				// this means some character is either has in only one string
				memo.put(key, false);
				return false;
			}
		}
		for (int i = 1; i < n; i++) {
			String s1First = s1.substring(0, i);
			String s1Last = s1.substring(i);
			// strings are not swapped
			if (isScramble3(s1First, s2.substring(0, i), memo)
					&& isScramble3(s1Last, s2.substring(i), memo)) {
				memo.put(key, true);
				return true;
			}
			// strings are swapped
			if (isScramble3(s1First, s2.substring(n - i), memo)
					&& isScramble3(s1Last, s2.substring(0, n - i), memo)) {
				memo.put(key, true);
				return true;
			}
		}
		memo.put(key, false);
		return false;
	}

	private static void type2() {
		String s1 = "abcdem";
		String s2 = "baecdm";
		Map<String, Boolean> memo = new HashMap<>();
		boolean isScramble = isScramble2(s1, s2, memo);
		System.out.println(isScramble);
	}

	private static boolean isScramble2(String s1, String s2, Map<String, Boolean> memo) {
		String key = s1 + s2;
		if (memo.containsKey(key)) return memo.get(key);

		// if both are equals, then we will return true
		if (s1.equals(s2)) return true;
		// if s1 is a single character but s1 no equals to s2, then the program will come here,
		// it means s1 and s2 and single character but not equal, so we will return true
		if (s1.length() <= 1) return false;

		int n = s1.length();
		for (int i = 1; i < n; i++) {
			// strings are not swapped
			// comparing 0..i with 0..i of both strings means left cut on both strings
			// also check the right cut oth both string
			if (isScramble2(s1.substring(0, i), s2.substring(0, i), memo)
					&& isScramble2(s1.substring(i), s2.substring(i), memo)) {
				memo.put(key, true);
				return true;
			}
			// here strings are swapped,
			// we will check o..i and n-i..n of the corresponding string,
			// which means first part of the first string and first part of the second string.
			// and the second part of the first string and first part of the second string
			if (isScramble2(s1.substring(0, i), s2.substring(n - i, n), memo)
					&& isScramble2(s1.substring(i), s2.substring(0, n - i), memo)) {
				memo.put(key, true);
				return true;
			}
		}
		memo.put(key, false);
		return false;
	}

	// brute force approach
	private static void type1() {
		String s1 = "abcdem";
		String s2 = "baecdm";
		boolean isScramble = isScramble1(s1, s2);
		System.out.println(isScramble);
	}

	private static boolean isScramble1(String s1, String s2) {
		// if both are equals, then we will return true
		if (s1.equals(s2)) return true;
		// if s1 is a single character but s1 no equals to s2, then the program will come here,
		// it means s1 and s2 and single character but not equal, so we will return true
		if (s1.length() <= 1) return false;

		int n = s1.length();
		for (int i = 1; i < n; i++) {
			// strings are not swapped
			// comparing 0..i with 0..i of both strings means left cut on both strings
			// also check the right cut oth both string
			if (isScramble1(s1.substring(0, i), s2.substring(0, i)) &&
					isScramble1(s1.substring(i), s2.substring(i)))
				return true;
			// here strings are swapped,
			// we will check o..i and n-i..n of the corresponding string,
			// which means first part of the first string and first part of the second string.
			// and the second part of the first string and first part of the second string
			if (isScramble1(s1.substring(0, i), s2.substring(n - i, n)) &&
					isScramble1(s1.substring(i), s2.substring(0, n - i)))
				return true;
		}
		return false;
	}
}
