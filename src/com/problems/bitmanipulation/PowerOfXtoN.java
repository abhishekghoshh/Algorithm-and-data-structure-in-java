package com.problems.bitmanipulation;
/*
 * Problem link :
 * 
 * 
 * Solution link :
 * https://www.geeksforgeeks.org/fast-exponention-using-bit-manipulation/
 * 
 */

public class PowerOfXtoN {

	public static void main(String[] args) {
		type1();
	}

	// ans = n^b
	// TODO check later
	private static void type1() {
		int n = 2, b = 8;
		// Stores final answer
		int answer = 1;
		while (b > 0) {
			answer *= ((b & 1) == 1) ? n : 1;
			n = n * n;
			b = b >> 1;
		}
		System.out.println("answer is " + answer);
	}

}
