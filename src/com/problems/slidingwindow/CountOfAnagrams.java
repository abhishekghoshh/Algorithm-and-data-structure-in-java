package com.problems.slidingwindow;

import java.util.*;

/*
 * Problem link :
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/description/
 * https://www.geeksforgeeks.org/problems/count-occurences-of-anagrams5839/1
 *
 * Solution link :
 * https://www.youtube.com/watch?v=MW4lJ8Y0xXk&list=PL_z_8CaSLPWeM8BDJmIYDaoQ5zuwyxnfj&index=5
 * https://www.youtube.com/watch?v=G8xtZy0fDKg
 *
 * https://neetcode.io/solutions/find-all-anagrams-in-a-string
 * */


public class CountOfAnagrams {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
		type5();
		type6();
	}

	// time complexity O(2n+k)
	// space complexity O(26)
	// constant space complexity for freq array
	private static void type6() {
		String s = "mfrxforxxorfxdofr";
		String p = "for";
		List<Integer> ret = findAnagrams6(s, p);
		System.out.println(ret);
	}

	private static List<Integer> findAnagrams6(String s, String p) {
		int n1 = s.length();
		int n2 = p.length();
		if (n1 < n2) return new ArrayList<>();
		List<Integer> ret = new ArrayList<>();
		// first, we are storing all the frequency of the pattern
		int[] freq = new int[26];
		for (var c : p.toCharArray()) freq[c - 'a']++;
		int j = 0;
		int required = n2;
		char[] arr = s.toCharArray();
		for (int i = 0; i < n1; i++) {
			char right = arr[i];
			// on each iteration we are decrementing the frequency array
			// and checking if it is still greater than zero or not
			// if it is still greater than 0 then we need same element more
			// 0 means we have enough of it
			// for both of these cases we will decrement the required char count
			if (--freq[right - 'a'] >= 0)
				required--;
			// if the required char count is zero that mean all the character in pattern is found,
			// then we will check what is the size of the window
			// if it is the same as the pattern length then it's an anagram
			// if it is greater than from the left side we will increment the left pointer
			// and also increment the frequency as we are not considering it in our window
			// anymore.We will do this until again hit required> 0 or the window length is
			// pattern length
			while (required == 0) {
				if (i - j + 1 == n2) ret.add(j);
				char left = arr[j];
				if (++freq[left - 'a'] > 0) required++;
				j++;
			}
		}
		return ret;
	}

	// same as previous one
	// we are using array rather than using map
	// TODO complete later
	private static void type5() {
		String text = "mfrxforxxorfxdofr";
		String pattern = "for";
		char[] textArray = text.toCharArray();
		char[] patternArray = pattern.toCharArray();
		char[] freq = new char[26];
		int left = 0, right = 0, n = textArray.length, k = patternArray.length, charCount = 0, count = 0, c;
		for (char ch : pattern.toCharArray()) {
			freq[index(ch)]++;
		}
		for (int m = 0; m < 26; m++) {
			if (freq[m] != 0) {
				charCount++;
			}
		}
		while (right < n) {
			if (--freq[index(textArray[right])] == 0) {
				charCount--;
			}
			if (right >= k - 1) {
				if (charCount == 0) {
					count++;
				}

			}
			right++;
		}
	}

	// sliding window
	// if the text size is n then time complexity is O(n)
	// Space complexity is O(sizeof(pattern))
	// so in previous approach we were checking the frequency array on each window
	// we can do better
	// we can use a counter to check
	// TODO complete and optimize it later check it later aditya verma
	private static void type4() {
		String text = "mfrxforxxorfxdofr";
		String pattern = "for";
		Map<Character, Integer> frequency = new HashMap<>();
		char[] textArray = text.toCharArray();
		char[] patternArray = pattern.toCharArray();

		char ch;
		int left = 0, right = 0, n = textArray.length, k = patternArray.length, charCount, count;
		for (int i = 0; i < k; i++) {
			ch = pattern.charAt(i);
			if (!frequency.containsKey(ch)) {
				frequency.put(ch, 0);
			}
			frequency.put(ch, frequency.get(ch) + 1);
		}

		charCount = frequency.keySet().size();
		// sliding until it hits the window
		char leftChar, rightChar;
		while (right < k) {
			rightChar = text.charAt(right);
			if (frequency.containsKey(rightChar)) {
				frequency.put(rightChar, frequency.get(rightChar) - 1);
				if (frequency.get(rightChar) == 0) {
					charCount--;
				}
			}
			right++;
		}
		count = charCount == 0 ? 1 : 0;
		while (right < n) {
			leftChar = text.charAt(left);
			rightChar = text.charAt(right);

			if (frequency.containsKey(leftChar)) {
				frequency.put(leftChar, frequency.get(leftChar) + 1);
				if (frequency.get(leftChar) > 0) {
					charCount++;
				}
			}
			if (frequency.containsKey(rightChar)) {
				frequency.put(rightChar, frequency.get(rightChar) - 1);
				if (frequency.get(rightChar) == 0) {
					charCount--;
				}
			}
			count = count + (charCount == 0 ? 1 : 0);
			left++;
			right++;
		}
		System.out.println(count);
	}

	// type 2 and type 3 are same
	// just that we are not taking any set
	// Asymptotically it should take more time but in reality it's taking less
	// as it's easy it works on array rather than customized data structure
	private static void type3() {
		String text = "mforxxorfxdofr";
		String pattern = "for";
		List<Integer> list = findAnagrams3(text, pattern);
		System.out.println(list);
	}

	private static List<Integer> findAnagrams3(String text, String pattern) {
		if (text.length() < pattern.length()) new ArrayList<>();
		List<Integer> list = new ArrayList<>();
		int[] patternArray = new int[26];
		int[] textArray = new int[26];
		int textSize = text.length();
		int patternSize = pattern.length();
		int i = 0;
		while (i <= textSize) {
			if (i < patternSize) {
				patternArray[pattern.charAt(i) - 'a']++;
				textArray[text.charAt(i) - 'a']++;
			} else {
				if (equals(patternArray, textArray)) {
					list.add(i - patternSize);
				}
				if (i == textSize) break;
				textArray[text.charAt(i) - 'a']++;
				textArray[text.charAt(i - patternSize) - 'a']--;
			}
			i++;
		}
		return list;
	}

	private static boolean equals(int[] patternArray, int[] textArray) {
		for (int i = 0; i < 26; i++) {
			if (patternArray[i] != textArray[i]) {
				return false;
			}
		}
		return true;
	}

	// Sliding window
	// if the text size is n, and we are looping through 26 letters in every window
	// so time complexity is O(n*k)
	// space complexity is O(2*26+k) for 2 array
	// We can optimize this by using Map
	public static void type2() {
		String text = "mforxxorfxdofr";
		String pattern = "for";

		int count = countAnagrams2(text, pattern);
		System.out.println(count);
	}

	private static int countAnagrams2(String text, String pattern) {
		int n1 = text.length();
		int n2 = pattern.length();
		Set<Integer> allUniqueCharacters = new HashSet<>(26);
		int[] freq1 = new int[26]; // for pattern frequency
		int[] freq2 = new int[26]; // for window frequency
		int left = 0, right = 0, count;
		while (right < n2) {
			allUniqueCharacters.add(index(pattern.charAt(right)));
			freq1[index(pattern.charAt(right))]++;
			freq2[index(text.charAt(right))]++;
			right++;
		}
		// calculating for the first window
		count = equals(freq1, freq2, allUniqueCharacters) ? 1 : 0;
		// at this point left=0 and right=k
		while (right < n1) {
			// updating frequency for the current window
			// where left=1 and right=k
			freq2[index(text.charAt(left))]--;
			freq2[index(text.charAt(right))]++;
			// calculating for the current window
			if (equals(freq1, freq2, allUniqueCharacters)) {
				count++;
			}
			left++;
			right++;
		}
		return count;
	}

	private static boolean equals(int[] patternArray, int[] textArray, Set<Integer> allUniqueCharacters) {
		for (int index : allUniqueCharacters) {
			if (patternArray[index] != textArray[index]) {
				return false;
			}
		}
		return true;
	}

	public static int index(char ch) {
		return ch - 'a';
	}

	// Brute force
	private static void type1() {

	}

}
