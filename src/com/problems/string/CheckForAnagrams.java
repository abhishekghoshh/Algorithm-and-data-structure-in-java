package com.problems.string;

import java.util.HashMap;
import java.util.Map;

/*
 * Problem link :
 * https://leetcode.com/problems/valid-anagram/description/
 * https://neetcode.io/problems/is-anagram
 * https://www.naukri.com/code360/problems/1172164
 * https://www.naukri.com/code360/problems/anagram-pairs_626517
 *
 * Solution link :
 *
 *
 * https://takeuforward.org/data-structure/check-if-two-strings-are-anagrams-of-each-other/
 */

// Tags : Array, String, Hashing
public class CheckForAnagrams {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// same as previous
	// here we are using the freq array as map
	private static void type2() {
		String s = "anagram";
		String t = "nagaram";
		boolean isAnagram = isAnagram2(s, t);
		System.out.println(isAnagram);
	}

	private static boolean isAnagram2(String s, String t) {
		if (s.length() != t.length()) return false;
		char[] arr1 = s.toCharArray();
		char[] arr2 = t.toCharArray();
		int[] freq = new int[26];
		for (int i = 0; i < arr1.length; i++) {
			// adding freq to the map
			freq[arr1[i] - 'a']++;
			// decreasing frequency to the map
			freq[arr2[i] - 'a']--;
		}
		for (int f : freq) {
			if (f != 0) return false;
		}
		return true;
	}


	private static void type1() {
		String s = "anagram";
		String t = "nagaram";
		boolean isAnagram = isAnagram1(s, t);
		System.out.println(isAnagram);
	}

	private static boolean isAnagram1(String s, String t) {
		if (s.length() != t.length()) return false;
		Map<Character, Integer> freq = new HashMap<>();
		// adding freq to the map
		for (char ch : s.toCharArray())
			freq.put(ch, freq.getOrDefault(ch, 0) + 1);
		// decreasing frequency to the map
		for (char ch : t.toCharArray())
			freq.put(ch, freq.getOrDefault(ch, 0) - 1);
		// checking if any character has any value other than 0
		for (Map.Entry<Character, Integer> entry : freq.entrySet())
			if (entry.getValue() != 0) return false;
		return true;
	}
}
