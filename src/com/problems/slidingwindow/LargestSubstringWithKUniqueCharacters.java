package com.problems.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/*
 * Problem link:
 * https://www.codingninjas.com/studio/problems/longest-substring-with-at-most-k-distinct-characters_2221410
 * https://practice.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 * 
 * Solution:
 * Aditya verma :
 * https://www.youtube.com/watch?v=Lav6St0W_pQ&list=PL_z_8CaSLPWeM8BDJmIYDaoQ5zuwyxnfj&index=10
 * */
public class LargestSubstringWithKUniqueCharacters {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// optimize approach
	// but, same as using a hashmap
	private static void type3() {
		String s = "aabacbebebe";
		int k = 2;
		int max = -1;
		char[] arr = s.toCharArray();
		int n = arr.length;
		int[] freq = new int[26];
		int left = 0, types = 0;
		for (int right = 0; right < n; right++) {
			int chIndex = arr[right] - 'a';
			freq[chIndex]++;
			if (freq[chIndex] == 1) types++;
			while (left < n && types > k) {
				int itemToRemove = arr[left++] - 'a';
				freq[itemToRemove]--;
				if (freq[itemToRemove] == 0) types--;
			}
			if (types == k) max = Math.max(max, right - left + 1);
		}
		System.out.println(max);
	}

	// sliding window
	private static void type2() {
		String s = "aabacbebebe";
		int k = 2;
		int max = -1;
		char[] arr = s.toCharArray();
		int n = arr.length;
		Map<Character, Integer> freq = new HashMap<>();
		int left = 0, types = 0;
		for (int right = 0; right < n; right++) {
			char ch = arr[right];
			freq.put(ch, freq.getOrDefault(ch, 0) + 1);
			if (freq.get(ch) == 1) types++;
			while (left < n && types > k) {
				char charToRemove = arr[left++];
				freq.put(charToRemove, freq.get(charToRemove) - 1);
				if (freq.get(charToRemove) == 0) types--;
			}
			if (types == k) max = Math.max(max, right - left + 1);
		}
		System.out.println(max);
	}

	// brute force
	private static void type1() {

	}

}
