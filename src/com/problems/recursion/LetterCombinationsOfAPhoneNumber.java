package com.problems.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
 * Problem link : 
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number
 * https://www.codingninjas.com/studio/problems/letter-phone_626178
 *
 * Solution is :
 * https://www.youtube.com/watch?v=0snEunUacZY
 * 
 * */
public class LetterCombinationsOfAPhoneNumber {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// same as previous,
	// just here we will use a char array as bucket rather than a string builder
	// so we don't have to remove the character from the string builder everytime
	private static void type3() {
		String digits = "23";
		List<String> list = new ArrayList<>();
		char[] bucket = new char[digits.length()];
		letterCombinations3(0, digits.toCharArray(), list, bucket);
		System.out.println(list);
	}

	private static void letterCombinations3(int i, char[] digits, List<String> list, char[] bucket) {
		if (i == digits.length) {
			list.add(new String(bucket));
			return;
		}
		int digit = digits[i] - '0';
		char[] values = keypadValues[digit];
		for (char ch : values) {
			bucket[i] = ch;
			letterCombinations3(i + 1, digits, list, bucket);
		}
	}


	// we will use a 2d array instead of a hashmap
	private static void type2() {
		String digits = "23";
		List<String> list = new ArrayList<>();
		letterCombinations2(0, digits.toCharArray(), list, new StringBuilder());
		System.out.println(list);
	}

	private static final char[][] keypadValues = {
			{}, {},
			{'a', 'b', 'c'},
			{'d', 'e', 'f'},
			{'g', 'h', 'i'},
			{'j', 'k', 'l'},
			{'m', 'n', 'o'},
			{'p', 'q', 'r', 's'},
			{'t', 'u', 'v'},
			{'w', 'x', 'y', 'z'}
	};

	public static void letterCombinations2(int i, char[] digits, List<String> list, StringBuilder sb) {
		if (i == digits.length) {
			list.add(sb.toString());
			return;
		}
		int digit = digits[i] - '0';
		char[] values = keypadValues[digit];
		for (char c : values) {
			sb.append(c);
			letterCombinations2(i + 1, digits, list, sb);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	// This is using proper backtracking,
	// the key pad values are stored in a hashmap
	// we are just creating a String builder, and each time we are adding one character
	// and after using that we are just removing that
	private static void type1() {
		String digits = "23";
		List<String> answer = new ArrayList<>();
		letterCombinations1(0, digits.toCharArray(), answer, new StringBuilder());
		System.out.println(answer);
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
		if (i == digits.length) {
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
