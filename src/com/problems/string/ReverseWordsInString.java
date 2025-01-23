package com.problems.string;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem link : 
 * https://leetcode.com/problems/reverse-words-in-a-string
 * https://www.naukri.com/code360/problems/696444
 * 
 * Solution link:
 * 
 * 
 * https://takeuforward.org/data-structure/reverse-words-in-a-string/
 * */
public class ReverseWordsInString {

	/*
	 * 1 <= s.length <= 10^4
	 * s contains English letters (upper-case and lower-case), digits, and spaces ' '
	 * There is at least one word in s
	 * */
	public static void main(String[] args) {
		type1();
		type2();
	}

	// optimized approach using two pointers
	// we will go from the last to first
	// 1. we will skip all the spaces till we will find a letter
	// that will be our end index, 2. then we will go till we find a space
	// that index will be our start of the word.
	// 3. we will use a loop to store the word
	// now we will save that into our string builder and also add a space
	// lastly we will remove one extra space
	private static void type2() {
		String s = "  hello world I am home ";
		String ans = reverseWords2(s);
		System.out.println(ans);
	}

	public static String reverseWords2(String s) {
		char[] arr = s.toCharArray();
		int n = arr.length;
		StringBuilder sb = new StringBuilder();
		int i = n - 1;
		while (i >= 0) {
			// skipping the empty spaces
			while (i >= 0 && arr[i] == ' ') i--;
			if (i == -1) break;
			// going till we find an empty space to find the start of the word
			int end = i;
			while (i >= 0 && arr[i] != ' ') i--;
			int start = i + 1;
			// now we will copy the word into our string builder
			for (int j = start; j <= end; j++) sb.append(arr[j]);
			sb.append(' ');
		}
		// remove the extra space
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	// brute force approach
	// stores a list of [start,end] of every word
	// now reverse the list and loop through the words and add them into our ans
	private static void type1() {
		String s = "  hello world I am home ";
		String ans = reverseWords1(s);
		System.out.println(ans);
	}

	private static String reverseWords1(String s) {
		char[] arr = s.toCharArray();
		int n = arr.length;
		List<int[]> indices = new ArrayList<>();
		// storing the [wordStart, wordEnd] indices in a list,
		// we need one flag to check if the word is already started or not
		boolean wordStarted = false;
		for (int i = 0; i < n; i++) {
			// there is a space
			if (arr[i] == ' ') {
				wordStarted = false;
				continue;
			}
			// there is a character, and it is the start of a word
			if (!wordStarted) {
				indices.add(new int[]{i, i});
				wordStarted = true;
			}
			int[] lastPair = indices.get(indices.size() - 1);
			lastPair[1] = i;
		}
		StringBuilder sb = new StringBuilder();
		// going from last to first in the indices list
		int sz = indices.size();
		for (int i = sz - 1; i >= 0; i--) {
			int[] pair = indices.get(i);
			int start = pair[0], end = pair[1];
			// add the word into the string builder and also adding a space
			for (int j = start; j <= end; j++) sb.append(arr[j]);
			sb.append(" ");
		}
		// remove the extra space
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}


}
