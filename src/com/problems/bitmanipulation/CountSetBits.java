package com.problems.bitmanipulation;

/*
 * Problem link :
 * https://leetcode.com/problems/number-of-1-bits/
 * https://www.codingninjas.com/studio/problems/count-total-set-bits_784
 * 
 * Solution link :
 * https://www.codingninjas.com/codestudio/library/count-number-of-set-bits-in-an-integer
 * https://www.geeksforgeeks.org/count-set-bits-in-an-integer/
 * 
 */
public class CountSetBits {
	// Signed Right shift operator (>>)
	// Unsigned Right shift operator (>>>)
	// Left shift operator(<<)
	// Unsigned Left shift operator (<<<)
	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// time complexity O(number of set bit)
	private static void type3() {
//		int n = 2147483647;
		int n = -2147483648;
		int ones = 0;
		while (n != 0) {
			// on each iteration we will remove its right most set bit
			// let say we want to remove right most set bit from 100110
			// then n-1 will be 100101
			// if we do n & n-1
			// we will get 100100
			n = n & (n - 1);
			ones++;
		}
		System.out.println(ones);
	}

	// time complexity O(log(n))
	// same as previous one type2
	private static void type2() {
//		int n = 2147483647;
		int n = -2147483648;
		int ones = 0;
		while (n != 0) {
			ones = ones + (n & 1);
			n = n >>> 1;
		}
		System.out.println(ones);
	}

	// time complexity O(log(n))
	// in case of negative it will fail as >> is a signed shift operator
	// in MSB of negative number there is one and by using >> it will not shift to
	// lsb, it will stay as it is, so in place of >> we can use >>>
	private static void type1() {
		int n = 2147483647;
		// int n = -2147483648;
		int ones = 0;
		while (n != 0) {
			ones += (n & 1);
			n = n >> 1;
		}
		System.out.println(ones);
	}

}
