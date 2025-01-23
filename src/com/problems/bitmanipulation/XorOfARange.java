package com.problems.bitmanipulation;

/*
 * Problem link :
 * https://www.codingninjas.com/studio/problems/l-to-r-xor_8160412
 * 
 * Solution link :
 * 
 * 
 */
public class XorOfARange {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// we need to find xor(L..R)
	// we know how to find the xor of xor(n) in O(1)
	// and we know xor(L-1)^xor(L..R)=xor(R)
	// xor(L-1)^xor(L..R)^xor(L-1) = xor(R)^xor(L-1)
	// xor(L..R) = xor(R)^xor(L-1)
	// time complexity O(1)
	// space complexity O(1)
	private static void type2() {
		int left = 5, right = 11;
		int xor = xor(right) ^ xor(left - 1);
		System.out.println(xor);
	}

	// check for multiple examples
	private static int xor(int n) {
		int rem = n % 4;
		if (rem == 0) return n;
		else if (rem == 1) return 1;
		else if (rem == 2) return n + 1;
		else return 0;
	}

	// brute force approach
	// time complexity O(n)
	// space complexity O(1)
	private static void type1() {
		int left = 5, right = 11;
		int xor = 0;
		for (int i = left; i <= right; i++) xor = xor ^ i;
		System.out.println(xor);
	}
}
