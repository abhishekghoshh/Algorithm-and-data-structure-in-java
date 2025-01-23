package com.problems.bitmanipulation;

/*
 * Problem link :
 * https://leetcode.com/problems/minimum-bit-flips-to-convert-number
 * https://www.codingninjas.com/studio/problems/flip-bits_8160405
 * https://practice.geeksforgeeks.org/problems/bit-difference-1587115620/1
 * 
 * Solution link :
 * 
 * 
 */
public class CountNumberOfBitsToFlipToConvertAtoB {

	public static void main(String[] args) {
		type1();
		type2();
	}

	private static void type2() {
		int start = 10;
		int goal = 20;
		// we know one thing that if we do the xor it will produce 1 only if both are
		// different bits, 0 and 1 or 1 and 0
		// and our goal is the same, i.e. to check which bit is different,
		// so if we do xor then the answer will have the set bits for different bits of
		// start and goal, so now our work is just to count the bits of this xor output
		int xor = start ^ goal;
		int count = 0;
		while (xor != 0) {
			count++;
			xor = xor & (xor - 1);
		}
		System.out.println(count);
	}

	// Brute force solution
	private static void type1() {
		int start = 10;
		int goal = 20;
		// we will create a mask
		// and we will left shift it
		// and every time we will do and operation with both the number
		// if the result is same that means bit is same
		int mask = 1;
		int bound = 1 << 31;
		int count = 0;
		boolean diffBit;
		while (mask != bound && (mask <= start || mask <= goal)) {
			// checking ith bit is same for both start or goal or not
			diffBit = (start & mask) != (goal & mask);
			count = count + (diffBit ? 1 : 0);
			// after checking we are left shifting it
			// to check the next bit
			mask = mask << 1;
		}
		System.out.println(count);
	}

}
