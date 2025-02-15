package com.problems.string;

/*
 * Problem link :
 * https://leetcode.com/problems/shortest-palindrome
 * https://www.interviewbit.com/problems/minimum-characters-required-to-make-a-string-palindromic/
 * https://www.naukri.com/code360/problems/893000
 * 
 * Solution link :
 * Consistent Code : https://www.youtube.com/watch?v=rLq2vMILp-c
 * GeeksforGeeks Practice : https://www.youtube.com/watch?v=j-1NLHybCSg
 * Let's Practice Together : https://www.youtube.com/watch?v=pE4D55Yti7o
 * Sagar Malhotra : https://www.youtube.com/watch?v=y1o7ygP-VpQ
 */
public class MinimumCharactersForPalindrome {

	// TODO study it later
	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
		type5();
		type6();
	}

	private static void type6() {
		String text = "AACECAAAA";
		String reverse = new StringBuilder(text).reverse().toString();
		//appending the reversed string with space to avoid mixing the original and reversed string. eg, aaaa and aaaa
		String s = text + "#" + reverse;
		int[] lps = new int[s.length() + 1];
		lps[0] = 0;
		int i = 1, prefixLen = 0;
		while (i < s.length()) {
			if (s.charAt(i) == s.charAt(prefixLen)) {
				lps[i++] = ++prefixLen;
			} else if (prefixLen > 0) {
				prefixLen = lps[prefixLen - 1];
			} else {
				lps[i++] = 0;
			}
		}
		//used the start subsequence of reversed string using the LPS array.
		String output = reverse.substring(0, reverse.length() - lps[s.length() - 1]) + s;
		System.out.println(output);
	}

	private static void type5() {
		String s = "AACECAAAA";
		int pow = 1, p = 31;
		int hash1 = 0, hash2 = 0;
		int pos = -1;
		int n = s.length();
		for (int i = 0; i < n; ++i) {
			hash1 = (hash1 * p) + s.charAt(i) - 'a' + 1;
			hash2 = hash2 + ((s.charAt(i) - 'a' + 1) * pow);
			pow *= p;
			if (hash1 == hash2)
				pos = i;
		}
		String output = new StringBuilder().append(s.substring(pos + 1)).reverse().append(s).toString();
		System.out.println(output);
	}


	// TODO best solution in leetcode
	private static void type4() {
		String s = "AACECAAAA";
		int n = s.length();
		int prefix = 0, postfix = 0, base = 31, pow = 1;
		int maxLen = 0;
		for (int i = 0; i < n; i++) {
			int c = s.charAt(i) - 'a';
			prefix = prefix * base + c;
			postfix += c * pow;
			pow *= base;
			if (prefix == postfix)
				maxLen = i + 1;
		}
		StringBuilder builder = new StringBuilder(maxLen + 2 * (n - maxLen));
		for (int i = n - 1; i >= maxLen; i--)
			builder.append(s.charAt(i));
		builder.append(s);
		String output = builder.toString();
		System.out.println(output);
	}

	// TODO study it later
	// using manacher's algorithm
	// time complexity O(2n)
	private static void type3() {
		String s = "AACECAAAA";
		int max = 0;
		int n = 2 * s.length() + 3;
		// initializing the transformed array
		char[] arr = new char[n];
		arr[0] = '@';
		arr[n - 1] = '&';
		arr[n - 2] = '#';
		for (int i = 0; i < s.length(); i++) {
			arr[2 * i + 1] = '#';
			arr[2 * i + 2] = s.charAt(i);
		}
		int[] lps = new int[n];
		int center = 0, right = 0;
		for (int i = 2; i < n - 2; i++) {
			int reflectionIndex = center - (i - center);
			if (i < right)
				lps[i] = Math.min(lps[reflectionIndex], right - i);
			while (arr[i + 1 + lps[i]] == arr[i - 1 - lps[i]]) {
				lps[i]++;
			}
			if (i + lps[i] > right) {
				center = i;
				right = i + lps[i];
			}
			// modification on manacher's algorithm
			// when the left window is from start
			if (arr[i - 1 - lps[i]] == '@') {
				max = Math.max(max, lps[i]);
			}
		}
		int answer = s.length() - max;
		System.out.println(answer);
	}

	// two pointer approach
	// slightly better than previous approach
	private static void type2() {
		String s = "abcd";
		char[] arr = s.toCharArray();
		int n = arr.length;
		int start = 0, end = arr.length - 1;
		int last = end;
		int count = 0;
		while (start < end) {
			if (arr[start] == arr[end]) {
				start++;
				end--;
			} else {
				count++;
				end = --last;
				start = 0;
			}
		}
		System.out.println(count);
		int n1 = count + n;
		char[] answer = new char[n1];
		for (int i = 0, j = 0; i < n1; i++) {
			if (count > 0) {
				answer[i] = arr[n - 1 - i];
				count--;
			} else {
				answer[i] = arr[j++];
			}
		}
		String output = new String(answer);
		System.out.println(output);
	}

	// brute force approach
	// we will check 0 to n if it is a palindrome or not
	// if it is not, then we will decrease from the end
	// if a string is aac then the minimum character addition to make
	// it a palindrome is c at the start the string will be caac
	// if the string is abc then we have to add cb at the start to make
	// it a palindrome.the string will become cbabc
	private static void type1() {
		String s = "abcd";
		char[] arr = s.toCharArray();
		int n = arr.length, end = arr.length;
		while (!isPalindrome(arr, end - 1)) end--;
		int count = arr.length - end;
		System.out.println(count);
		int n1 = count + n;
		char[] answer = new char[n1];
		for (int i = 0, j = 0; i < n1; i++) {
			if (count > 0) {
				answer[i] = arr[n - 1 - i];
				count--;
			} else {
				answer[i] = arr[j++];
			}
		}
		String output = new String(answer);
		System.out.println(output);
	}

	private static boolean isPalindrome(char[] arr, int right) {
		int left = 0;
		while (left < right) {
			if (arr[left] != arr[right]) return false;
			left++;
			right--;
		}
		return true;
	}

}
