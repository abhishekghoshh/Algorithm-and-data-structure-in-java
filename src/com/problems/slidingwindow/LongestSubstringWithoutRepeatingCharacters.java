package com.problems.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Problem link:
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 * https://neetcode.io/problems/longest-substring-without-duplicates
 * https://www.naukri.com/code360/problems/630418
 *
 *
 * Solution link:
 * new Striver : https://www.youtube.com/watch?v=-zSxTJkcdAo
 * Striver : https://www.youtube.com/watch?v=qtVh-XEpsJo&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=27
 * Aditya verma : https://www.youtube.com/watch?v=L6cffskouPQ&list=PL_z_8CaSLPWeM8BDJmIYDaoQ5zuwyxnfj&index=11
 * Neetcode : https://www.youtube.com/watch?v=wiGpQwVHdE0
 *
 * Blogs:
 * https://takeuforward.org/data-structure/length-of-longest-substring-without-any-repeating-character/
 * https://neetcode.io/solutions/longest-substring-without-repeating-characters
 *
 * */

// Tags: String, Variable length Sliding Window
public class LongestSubstringWithoutRepeatingCharacters {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
	}

	// previous approach uses a boolean array for checking if the character exists or not
	// but we can also use an integer as a set/ boolean array,
	// it has 32 bits, and we can use the bits and the bit position as a set
	// if the bit is 1 then the index is set if 0 then it is empty
	// todo complete it but not necessary for the interview
	private static void type5() {

	}

	// todo best possible solution for leetcode but not for the interview
	//  discuss 1->2->3 in the interview
	private static void type4() {
		String s = "abcabcbb";
		int max = lengthOfLongestSubstring4(s);
		System.out.println("max length is " + max);
	}

	private static int lengthOfLongestSubstring4(String s) {
		int n = s.length();
		boolean[] set = new boolean[128];
		char[] arr = s.toCharArray();
		int left = 0;
		int max = 0;
		for (int right = 0; right < n; right++) {
			while (set[arr[right]]) set[arr[left++]] = false;
			set[arr[right]] = true;
			max = Math.max(max, right - left + 1);
		}
		return max;
	}


	// todo this is more optimized approach
	//  here we do not need to remove the character, so we will store character as well as their indices
	//  so now if we find any duplicate character we will check if the index is lesser than the starting of the window or not
	//  if yes then start is a valid character for the current window, else we will initialize the window from the
	//  character's last index + 1
	// two pointer approach
	// time complexity O(n)
	// space complexity O(n)
	// here instead of set we are taking map
	// and storing character and its latest position
	// we can also use an int array for storing indices
	// but, then we have to initialize that array with -1
	private static void type3() {
		String s = "abcabcbb";
		int max = lengthOfLongestSubstring3(s);
		System.out.println("max length is " + max);
	}

	private static int lengthOfLongestSubstring3(String s) {
		int n = s.length();
		Map<Character, Integer> map = new HashMap<>();
		int max = 0;
		int left = 0, right = 0;
		while (right < n) {
			char ch = s.charAt(right);
			// if it's a new character then it's we are just adding it to map or
			// if the character is present, but its index is less than the start of the current series
			if (!map.containsKey(ch) || map.get(ch) < left) {
				map.put(ch, right);
				max = Math.max(max, right - left + 1);
				right++;
			} else {
				// when we encounter any duplicate character then
				// we again start the series from that character index + 1
				// suppose series is "cabdac", index of 'a' is 1 and 4
				// when we again found 'a' it 4 then we start the series at 2
				// then on next iteration in map we will found c = 0, but it's less than left,
				// so it will treat c as a new character and update its index
				left = map.get(ch) + 1;
			}
		}
		return max;
	}

	// todo explain this in the interview
	// optimized approach using two pointer approach
	// time complexity O(2*n)
	// space complexity O(n)
	private static void type2() {
		String s = "abcabcbb";
		int max = getMax2(s);
		System.out.println("max length is " + max);
	}

	private static int getMax2(String s) {
		int n = s.length();
		char[] arr = s.toCharArray();
		Set<Character> set = new HashSet<>();
		int max = 0;
		int left = 0, right = 0;
		while (right < n) {
			char ch = arr[right];
			// we will add a new character till we find any duplicate
			if (!set.contains(ch)) {
				set.add(ch);
				max = Math.max(max, right - left + 1);
				right++;
			} else {
				// once we find any duplicate character we will
				// shrink the window from the left side and remove characters from the set also
				while (set.contains(ch))
					set.remove(arr[left++]);
			}
		}
		return max;
	}

	// brute force approach
	// time complexity O(n^2)
	// space complexity O(n)
	// we will check for every substring possible
	private static void type1() {
		String s = "abcabcbb";
		int max = lengthOfLongestSubstring1(s);
		System.out.println(max);
	}

	private static int lengthOfLongestSubstring1(String s) {
		int n = s.length();
		char[] arr = s.toCharArray();
		// we will use a set to store the unique characters
		Set<Character> set = new HashSet<>();
		int max = 0;
		char ch;
		for (int i = 0; i < n; i++) {
			set.clear();
			for (int j = i; j < n; j++) {
				ch = arr[j];
				// if the character is already exists then we will break
				if (set.contains(ch)) break;
				max = Math.max(max, j - i + 1);
				set.add(ch);
			}
		}
		return max;
	}

}
