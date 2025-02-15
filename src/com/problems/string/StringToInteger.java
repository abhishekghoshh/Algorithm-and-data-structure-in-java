package com.problems.string;

/*
 * Problem link:
 * https://leetcode.com/problems/string-to-integer-atoi/
 * https://www.naukri.com/code360/problems/981270
 *
 * Solution link:
 * https://www.youtube.com/watch?v=FyTpsuWAoc8
 * https://www.youtube.com/watch?v=gLW6DD59ZaM
 *
 * Topics:
 * string, recursion
 */
public class StringToInteger {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}


	// using recursion
	private static void type3() {
		String s = "   -91283472332 with words";
		int num = atoi3(s);
		System.out.println(num);
	}

	// exactly the same as previous, here we have converted the inner loop into recursion
	static int integerExceeds = 0;
	private static int atoi3(String s) {
		char[] arr = s.toCharArray();
		int n = arr.length;
		if (n == 0) return 0;
		int i = 0;
		boolean isPositive = true;
		// 1. Read in and ignore any leading whitespace
		while (i < n && arr[i] == ' ') i++;
		// 2. Check the sign
		if (i < n && (arr[i] == '-' || arr[i] == '+')) isPositive = arr[i++] == '+';
		int result = atoi3(i, 0, arr, isPositive);
		// we are checking if it is returning the exceeding integer or not
		if (integerExceeds != 0) return integerExceeds;
		// else we will apply sign and return
		return isPositive ? result : -result;
	}

	private static int atoi3(int i, int result, char[] arr, boolean isPositive) {
		if (i == arr.length) return result;
		int digit = arr[i] - '0';
		// if the char is not digit, then we will break
		if (digit < 0 || digit > 9) return result;
		// checking that if by adding this char we are exceeding INT MAX value or not
		if (Integer.MAX_VALUE / 10 < result || (Integer.MAX_VALUE / 10 == result && Integer.MAX_VALUE % 10 < digit))
			// also setting the integerExceed variable
			return integerExceeds = isPositive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		return atoi3(i + 1, (result * 10 + digit), arr, isPositive);
	}

	// TODO explain this in the interview
	private static void type2() {
		String s = "-91283472332";
		int num = atoi2(s);
		System.out.println(num);
	}

	// optimized approach
	private static int atoi2(String s) {
		int n = s.length();
		// 0. Check invalid string
		if (n == 0) return 0;
		char[] arr = s.toCharArray();
		int i = 0;
		int sign = 1;
		int result = 0;
		// 1. Read in and ignore any leading whitespace
		while (i < n && arr[i] == ' ') i++;
		// 2. Check the sign
		if (i < n && (arr[i] == '-' || arr[i] == '+'))
			sign = arr[i++] == '-' ? -1 : 1;
		// 3. Convert to integer and avoid overflow
		while (i < n) {
			int digit = arr[i++] - '0';
			// if the char is not digit, then we will break
			if (digit < 0 || digit > 9) break;
			// checking that if by adding this char we are exceeding INT MAX value or not
			if (Integer.MAX_VALUE / 10 < result || (Integer.MAX_VALUE / 10 == result && Integer.MAX_VALUE % 10 < digit))
				return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			result = result * 10 + digit;
		}
		return result * sign;
	}

	private static void type1() {
		String s = "42";
		int num = atoi(s);
		System.out.println(num);
	}

	private static int atoi(String s) {
		s = s.trim();
		int n = s.length();
		// 0. Check invalid string
		if (n == 0) return 0;
		char[] arr = s.toCharArray();
		int i = 0, sign = 1;
		long sum = 0;
		if (arr[i] == '+' || arr[i] == '-')
			sign = arr[i++] == '-' ? -1 : 1;
		while (i < n) {
			int digit = arr[i++] - '0';
			if (digit < 0 || digit > 9) break;
			sum = sum * 10 + digit;
			if (sign == 1 && sum > Integer.MAX_VALUE) return Integer.MAX_VALUE;
			if (sign == -1 && (-1) * sum < Integer.MIN_VALUE) return Integer.MIN_VALUE;
		}
		return (int) sum * sign;
	}

}
