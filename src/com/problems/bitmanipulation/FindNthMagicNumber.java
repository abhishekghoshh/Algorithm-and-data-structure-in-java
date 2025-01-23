package com.problems.bitmanipulation;
/*
 * Problem link :
 * 
 * 
 * Solution link :
 * https://www.geeksforgeeks.org/find-nth-magic-number/
 * 
 */

public class FindNthMagicNumber {

	// A magic number is defined as a number which can be expressed as a power of 5
	// or sum of unique powers of 5. First few magic numbers are 5, 25, 30(5 + 25),
	// 125, 130(125 + 5), �.
	public static void main(String[] args) {
		type1();
	}

	private static void type1() {
		int n = 8;
		int pow = 1, answer = 0;
		// Go through every bit of n
		while (n != 0) {
			pow = pow * 5;
			// If last bit of n is set
			answer += ((n & 1) == 1) ? pow : 0;
			// proceed to next bit
			// or n = n/2
			n >>= 1;
		}
		System.out.println(answer);
	}

}
