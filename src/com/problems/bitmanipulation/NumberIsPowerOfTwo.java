package com.problems.bitmanipulation;

/*
 * Problem link :
 * https://leetcode.com/problems/power-of-two/
 * https://www.codingninjas.com/studio/problems/power-of-two_893061
 * https://practice.geeksforgeeks.org/problems/power-of-2-1587115620/1
 *
 * 
 * Solution link :
 * 
 * 
 */
public class NumberIsPowerOfTwo {
	// Given a non-negative integer N. The task is to check if N is a power of 2.
	// More formally, check if N can be expressed as 2x for some x.
	public static void main(String[] args) {
		type1();
		type2();
	}

	private static void type2() {
		int n = 0;
//		if(n<=0) return false;
		// if the number is 2^x then that number will be something like 10000...
		// and the previous number has just the reverse number like 0111111...
		// so their and & operation will be 0
		// so if n & (n - 1) is 0 that means the number is 2^x
		boolean isPowerOfTwo = (n & (n - 1)) == 0;
		System.out.println(isPowerOfTwo);
	}

	// brute force
	// counts the number of ones present
	private static void type1() {
		int n = 0;
//		if (n <= 0) return false;
		int oneCount = 0;
		boolean isPowerofTwo = true;
		while (n != 0) {
			oneCount += (n & 1);
			if (oneCount > 1) {
				isPowerofTwo = false;
				break;
			}
			n = n >> 1;
		}
		System.out.println(isPowerofTwo);
	}

}
