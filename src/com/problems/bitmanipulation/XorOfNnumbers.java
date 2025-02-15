package com.problems.bitmanipulation;

public class XorOfNnumbers {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// optimized approach
	// time complexity O(1)
	// space complexity O(1)
	// lets see xor of 1 to 8 numbers
	// 1 to 1 ->1
	// 1 to 2 ->3
	// 1 to 3 ->0
	// 1 to 4 ->4
	// 1 to 5 ->1
	// 1 to 6 ->7
	// 1 to 7 ->0
	// 1 to 8 ->8
	// we can see one pattern
	// when n % 4 is 0 then xor is n
	// when n % 4 is 1 then xor is 1
	// when n % 4 is 2 then xor is n+1
	// when n % 4 is 3 then xor is 0
	private static void type2() {
		int n = 10;
		int xor = xor(n);
		System.out.println(xor);
	}

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
		int n = 10;
		int xor = 0;
		for (int i = 1; i <= n; i++) {
			xor = xor ^ i;
		}
		System.out.println(xor);
	}

}
