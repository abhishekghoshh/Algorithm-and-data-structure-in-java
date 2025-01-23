package com.problems.bitmanipulation;

/*
 * Problem link :
 * https://www.codingninjas.com/studio/problems/check-whether-k-th-bit-is-set-or-not_5026446
 * https://practice.geeksforgeeks.org/problems/check-whether-k-th-bit-is-set-or-not-1587115620/1
 * 
 * Solution link :
 * 
 * 
 */
public class KthBitSetOrNot {
	// Given a number N and a bit number K, check if Kth bit of N is set or not. A
	// bit is called set if it is 1. Position of set bit '1' should be indexed
	// starting with 0 from LSB side in binary representation of the number.
	// Input: N = 4, K = 2
	// Output: Yes
	// Explanation: Binary representation of 4 is 100,
	// in which 2nd bit from LSB is set.
	// So, return true.
	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
	}

	private static void type4() {
		int n = 5, k = 1;
		int sol = n >> (k - 1);
		int kthBit = (sol & 1) == 1 ? 1 : 0;
		System.out.println(kthBit);
	}

	private static void type3() {
		int n = 5, k = 1;
		int mask = 1 << (k - 1);
		int kthBit = (n & mask) > 0 ? 1 : 0;
		System.out.println(kthBit);
	}

	// rather than changing the input
	// we can change the 1 and create one mask
	// mask = 1<<k => 000100
	// n = 011000
	// n & mask => either 000000 or the 000100
	// n & mask => 0 or mask
	// if it is 0 that means the bit is not set
	private static void type2() {
		int n = 23;
		int k = 3;
		int mask = 1 << k;
		int setBit = (n & mask) == 0 ? 0 : 1;
		System.out.println(setBit);
	}

	// here we will right shift by k
	// so when n = 11011100 and k = 3
	// so n>>k = 00011011
	// 00011011 & 1 => 1
	// 1 means 00000001
	// 00011011 & 00000001 => 1
	// time complexity O(1)
	// space complexity O(1)
	private static void type1() {
		int n = 23;
		int k = 3;
		int setBit = (n >> k) & 1;
		System.out.println(setBit);
	}

}
