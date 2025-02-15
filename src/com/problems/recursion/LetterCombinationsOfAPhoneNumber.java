package com.problems.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
 * Problem link :
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
 * https://neetcode.io/problems/combinations-of-a-phone-number
 * https://www.naukri.com/code360/problems/letter-phone_626178
 *
 * Solution is :
 * https://www.youtube.com/watch?v=0snEunUacZY
 * https://neetcode.io/solutions/letter-combinations-of-a-phone-number
 * */
public class LetterCombinationsOfAPhoneNumber {

	public static void main(String[] args) {
		type1();
		type2();
	}


	// todo little optimized from the last solution
	//  but the approach is same
	//  we will use a 2d array instead of a hashmap
	private static void type2() {
		String digits = "23";
		List<String> list = letterCombinations2(digits);
		System.out.println(list);
	}

	private static final char[][] keypadValues = {
			{},
			{},
			{'a', 'b', 'c'},
			{'d', 'e', 'f'},
			{'g', 'h', 'i'},
			{'j', 'k', 'l'},
			{'m', 'n', 'o'},
			{'p', 'q', 'r', 's'},
			{'t', 'u', 'v'},
			{'w', 'x', 'y', 'z'}
	};


	private static List<String> letterCombinations2(String digits) {
		List<String> list = new ArrayList<>();
		int n = digits.length();
		char[] bucket = new char[n];
		letterCombinations(0, digits.toCharArray(), list, bucket);
		return list;
	}

	private static void letterCombinations(int i, char[] digits, List<String> list, char[] bucket) {
		// if we have reached the end of the digits, then add the string to the answer
		if (i == digits.length) {
			list.add(new String(bucket));
			return;
		}
		int digit = digits[i] - '0';
		char[] values = keypadValues[digit];
		for (char ch : values) {
			bucket[i] = ch;
			letterCombinations(i + 1, digits, list, bucket);
		}
	}

	// todo using the backtracking
	// the key pad values are stored in a hashmap
	// we are just creating a String builder, and each time we are adding one character
	// and after using that we are just removing that
	private static void type1() {
		String digits = "23";
		List<String> answer = letterCombinations1(digits);
		System.out.println(answer);
	}

	private static List<String> letterCombinations1(String digits) {
		char[] arr = digits.toCharArray();
		List<String> answer = new ArrayList<>();
		letterCombinations1(0, arr, answer, new StringBuilder());
		return answer;
	}

	private static final Map<Character, List<Character>> keys = new HashMap<>();
	static {
		keys.put('2', List.of('a', 'b', 'c'));
		keys.put('3', List.of('d', 'e', 'f'));
		keys.put('4', List.of('g', 'h', 'i'));
		keys.put('5', List.of('j', 'k', 'l'));
		keys.put('6', List.of('m', 'n', 'o'));
		keys.put('7', List.of('p', 'q', 'r', 's'));
		keys.put('8', List.of('t', 'u', 'v'));
		keys.put('9', List.of('w', 'x', 'y', 'z'));
	}

	public static void letterCombinations1(int i, char[] digits, List<String> answer, StringBuilder sb) {
		int n = digits.length;
		// if we have reached the end of the digits, then add the string to the answer
		if (i == n) {
			answer.add(sb.toString());
			return;
		}
		List<Character> letters = keys.get(digits[i]);
		for (char ch : letters) {
			sb.append(ch);
			letterCombinations1(i + 1, digits, answer, sb);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

}
