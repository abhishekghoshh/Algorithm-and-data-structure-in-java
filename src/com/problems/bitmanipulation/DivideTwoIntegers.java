package com.problems.bitmanipulation;
/*
 * Problem link :
 * https://leetcode.com/problems/divide-two-integers/
 * https://www.codingninjas.com/studio/problems/-divide-two-integers_1112617
 * https://practice.geeksforgeeks.org/problems/division-without-using-multiplication-division-and-mod-operator/0
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=zhJt9xIoXCI
 * 
 */

public class DivideTwoIntegers {
	// Given two integers dividend and divisor, divide two integers without using
	// multiplication, division, and mod operator.
	// Note: Assume we are dealing with an environment that could only store
	// integers within the 32-bit signed integer range: [−231, 231 − 1]. For this
	// problem, if the quotient is strictly greater than 231 - 1, then return 231 -
	// 1, and if the quotient is strictly less than -231, then return -231.

	// TODO check all the possible solution
	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// TODO check it later
	private static void type3() {
		int dividend = -2147483648;
		int divisor = -1;
		int quotient = divide(dividend, divisor);
		System.out.println(quotient);
	}

	// TODO best approach
	public static int divide(int dividend, int divisor) {
		if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
		long dividendL = dividend;
		long divisorL = divisor;
		// if both are negative or both are positive
		boolean sign = (dividendL > 0 && divisorL > 0) || (dividendL < 0 && divisorL < 0);
		// as we have already extracted the final sign, so we don't require the dividend
		// and divisor with sign as it will change the bits
		dividendL = dividendL > 0 ? dividendL : -dividendL;
		divisorL = divisorL > 0 ? divisorL : -divisorL;

		long quotient = 0, mask;
		for (int i = 32; i >= 0 & (dividendL > 0 && dividendL >= divisorL); i--) {
			// this means mask * 2 to power i
			mask = divisorL << i;
			// mask will be negative only there is an overflow
			if (mask > 0 && mask <= dividendL) {
				quotient += (1L << i);
				dividendL = dividendL - mask;
			}
		}
		// add the sign
		quotient = sign ? quotient : -quotient;
		// in case the answer is more than max int value
		int quotientI = quotient > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) quotient;
		System.out.println("quotient is " + quotientI + " reminder is " + dividendL);
		return quotientI;
	}


	private static void type2() {
		long dividend = 100;
		long divisor = 7;
		if (dividend == Integer.MIN_VALUE && divisor == -1) {
			System.out.println(Integer.MAX_VALUE);
			return;
		}
		boolean negative = (dividend ^ divisor) < 0;
		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);
		// int quotient = 0;
		// int subQuot = 0;
		int quotient = 0;
		int subq = 0;

		while (dividend - divisor >= 0) {
			for (subq = 0; dividend - (divisor << subq << 1) >= 0; subq++)
				;
			quotient += 1 << subq;
			dividend -= divisor << subq;

		}
		quotient = negative ? -quotient : quotient;
		System.out.println(quotient);
	}

	private static void type1() {
		long dividend = 100;
		long divisor = 7;

		// if both are negative or both are positive
		int sign = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0) ? 1 : -1;

		// as we have already extracted the final sign so we don't require the dividend
		// and divisor with sign as it will change the bits
		dividend = dividend > 0 ? dividend : -dividend;
		divisor = divisor > 0 ? divisor : -divisor;

		long quotient = 0, previousSum = 0;
		for (int i = 31; i >= 0; i--) {
			if (previousSum + (divisor << i) <= dividend) {
				quotient += (1 << i);
				previousSum += (divisor << i);
			}
		}
		quotient = sign == 1 ? quotient : -quotient;
		System.out.println("quotient is " + quotient);
	}

}
