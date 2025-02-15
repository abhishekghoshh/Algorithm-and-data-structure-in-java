package com.problems.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/*
 * Problem link:
 * https://leetcode.com/problems/minimum-window-substring/description/
 * https://neetcode.io/problems/minimum-window-with-characters
 * https://www.naukri.com/code360/problems/minimum-window-substring_1215004
 * 
 * Solution:
 * https://www.youtube.com/watch?v=WJaij9ffOIY
 * Aditya Verma : https://www.youtube.com/watch?v=iwv1llyN6mo&list=PL_z_8CaSLPWeM8BDJmIYDaoQ5zuwyxnfj&index=13
 * Neetcode : https://www.youtube.com/watch?v=jSto0O4AJbM
 *
 * https://neetcode.io/solutions/minimum-window-substring
 * */

// tags: Arrays, String, sliding window
public class MinimumWindowSubstring {
	// Given two strings s and t of lengths m and n respectively, return the minimum
	// window substring of s such that every character in t (including duplicates)
	// is included in the window. If there is no such substring, return the empty
	// string "".
	// The testcases will be generated such that the answer is unique.
	// A substring is a contiguous sequence of characters within the string.
	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// todo exactly like the previous type but here we are using array and freq mao
	// same as previous type but here we will use array instead of map
	private static void type3() {
		String s = "ADOBECODEBANCABN";
		String t = "ABC";
		String minWindow = minWindow3(s, t);
		System.out.println(minWindow);
	}

	private static String minWindow3(String s, String t) {
		int n1 = s.length(), n2 = t.length();
		char[] arr = s.toCharArray();
		int start = 0;
		int minLen = Integer.MAX_VALUE;
		// adding to the frequency map from the pattern array
		int[] freq = new int[128];
		for (char c : t.toCharArray()) freq[c]++;
		// now we will loop through the actual array,
		// and at this point the freq map has items from the pattern array, we will take advantage of that
		for (int left = 0, right = 0; right < n1; right++) {
			char ch = arr[right];
			// we will decrease the characters from the actual array
			freq[ch]--;
			// if the frequency is greater than equal to 0 then the current character also belongs to the pattern array
			// otherwise freq would be negative, so we will decrement n2 as we found one character from pattern array
			if (freq[ch] >= 0) n2--;
			while (n2 == 0) {
				// checking if the len is lesser than the min length, then we will update the min and the start of the window
				int len = right - left + 1;
				if (len < minLen) {
					minLen = len;
					start = left;
				}
				// as we are shifting the left side, so we will increment the frequency
				char leftCh = arr[left++];
				freq[leftCh]++;
				// if the freq of left character is greater than 0 that means it is from the pattern array,
				// so we have again added one character from pattern array, hence we will increment n2
				if (freq[leftCh] > 0) n2++;
			}
		}
		return minLen != Integer.MAX_VALUE ?
				s.substring(start, start + minLen) : "";
	}


	// todo sliding two pointer approach using sliding window discuss this in the interview
	//  this is an optimized approach
	//  we will use a frequency map to store the frequency of the pattern array
	//  then we will apply the map into actual array and decrement character from the freq map
	//  if all the freq is less than equal to 0 then the prev string is consumed
	//  but for that we need to check the all the freq every time, we could do a little trick
	//  we could use one variable n and decrement it when then freq of the char is greater than equal to 0
	//  if negative, then that is not present on the pattern array
	//  if n is 0 then the pattern array is present in that range
	//  and we will save that range length and now we will shrink the range from the left side till n == 0
	private static void type2() {
		String s = "ADOBECODEBANCABN";
		String t = "ABC";
		String min = minWindow2(s, t);
		System.out.println(min);
	}

	private static String minWindow2(String s, String t) {
		char[] sArr = s.toCharArray();
		char[] tArr = t.toCharArray();
		int n1 = sArr.length, n2 = tArr.length;
		int minLen = Integer.MAX_VALUE;
		int start = -1;
		// adding to the frequency map from the pattern array
		Map<Character, Integer> freq = new HashMap<>();
		for (char c : tArr) {
			freq.put(c, freq.getOrDefault(c, 0) + 1);
		}
		// now we will loop through the actual array,
		// and at this point the freq map has items from the pattern array, we will take advantage of that
		for (int left = 0, right = 0; right < n1; right++) {
			char ch = sArr[right];
			// we will decrease the characters from the actual array
			freq.put(ch, freq.getOrDefault(ch, 0) - 1);
			// if the frequency is greater than equal to 0 then the current character also belongs to the pattern array
			// otherwise freq would be negative, so we will decrement n2 as we found one character from pattern array
			if (freq.get(ch) >= 0) n2--;
			// if n2 is 0 then we will shrink the left side
			while (n2 == 0) {
				// checking if the len is lesser than the min length, then we will update the min and the start of the window
				int len = right - left + 1;
				if (len < minLen) {
					minLen = len;
					start = left;
				}
				// as we are shifting the left side, so we will increment the frequency
				char leftCh = sArr[left++];
				freq.put(leftCh, freq.get(leftCh) + 1);
				// if the freq of left character is greater than 0 that means it is from the pattern array,
				// so we have again added one character from pattern array, hence we will increment n2
				if (freq.get(leftCh) > 0) n2++;
			}
		}
		return (minLen != Integer.MAX_VALUE) ?
				s.substring(start, start + minLen) : "";
	}

	// brute force approach
	private static void type1() {
		String s = "ADOBECODEBANC";
		String t = "ABC";

	}

}
