package com.problems.string;

import static java.lang.Character.isLetterOrDigit;
import static java.lang.Character.toLowerCase;
/*
 * 
 * problem links :
 * https://leetcode.com/problems/valid-palindrome/description/
 * https://neetcode.io/problems/is-palindrome
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=jJXJ16kPFWg
 *
 * https://neetcode.io/solutions/valid-palindrome
 * */


// Tags: String, Array, Two pointer
public class ValidPalindrome {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// we will directly check it from the array
	// two pointer approach
	private static void type2() {
		String s = "A man, a plan, a canal: Panama";
		boolean isPalindrome = isPalindrome2(s);
		System.out.println(isPalindrome);
	}

	private static boolean isPalindrome2(String s) {
		char[] arr = s.toCharArray();
		int n = arr.length;
		int left = 0, right = n - 1;
		boolean isPalindrome = true;
		while (left < right) {
			// skipping left and right until we find any digit or letter
			while (left < n && !isLetterOrDigit(arr[left])) left++;
			while (right >= 0 && !isLetterOrDigit(arr[right])) right--;
			// checking both the character on left and right sides
			if (left < right && toLowerCase(arr[left]) != toLowerCase(arr[right]))
				return false;
			left++;
			right--;
		}
		return isPalindrome;
	}

	private static void type1() {
		String s = "A man, a plan, a canal: Panama";
		boolean isPalindrome = isPalindrome(s);
		System.out.println(isPalindrome);
	}

	public static boolean isPalindrome(String s) {
		StringBuilder sb = new StringBuilder();
		for (char ch : s.toCharArray()) {
			// if it is letter then add it
			if (Character.isLetter(ch))
				sb.append(toLowerCase(ch));
			// if it is digit then add it
			if (Character.isDigit(ch))
				sb.append(ch);
		}
		StringBuilder reversed = new StringBuilder(sb).reverse();
		return sb.compareTo(reversed) == 0;
	}

}
