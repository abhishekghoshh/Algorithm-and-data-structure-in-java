package com.problems.bitmanipulation;

/*
 * Problem link :
 * https://leetcode.com/problems/power-of-two/description/
 * https://www.naukri.com/code360/problems/power-of-two_893061
 * https://www.geeksforgeeks.org/problems/power-of-2-1587115620/1
 *
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=H2bjttEV4Vc
 * https://neetcode.io/solutions/power-of-two
 */
public class NumberIsPowerOfTwo {
	// Given a non-negative integer N. The task is to check if N is a power of 2.
	// More formally, check if N can be expressed as 2x for some x.
	public static void main(String[] args) {
		type1();
		type2();
	}

	// todo most optimized approach
	//  time complexity O(1)
	// if the number is 2^x then that number will be something like 10000...
	// and the previous number has just the reverse number like 0111111...
	// so theirs and & operation will be 0,
	// so if n & (n - 1) is 0 that means the number is 2^x
	private static void type2() {
		int n = 0;
		boolean ans = isPowerOfTwo2(n);
		System.out.println(ans);
	}

	private static boolean isPowerOfTwo2(int n) {
		if (n <= 0) return false;
		return (n & (n - 1)) == 0;
	}

	// todo not brute force approach
	// counts the number of ones present
	// if the number is 2^x then that number will be something like 10000...
	private static void type1() {
		int n = 0;
		boolean ans = isPowerOfTwo1(n);
		System.out.println(ans);
	}

	private static boolean isPowerOfTwo1(int n) {
		if (n <= 0) return false;
		int ones = 0;
		// counting numbers of set bits that number
		while (n != 0) {
			ones += (n & 1);
			if (ones > 1) return false;
			n = n >> 1;
		}
		return true;
	}

}
