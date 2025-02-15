package com.problems.bitmanipulation;
/*
 * Problem link :
 * https://www.codingninjas.com/studio/problems/set-the-rightmost-unset-bit_8160456
 * https://practice.geeksforgeeks.org/problems/set-the-rightmost-unset-bit4436/1
 * 
 * Solution link :
 * 
 * 
 */

public class SetTheRightMostUnsetBit {

	public static void main(String[] args) {
		type1();
		type2();
	}

	private static void type2() {
		int n = 10;
		System.out.println(Integer.toBinaryString(n));
		int answer = setRightMostUnsetBit(n);
		System.out.println(answer);
		System.out.println(Integer.toBinaryString(answer));
	}

	public static int setRightMostUnsetBit(int n) {
		// Write your code here.
		int copy = n;
		int mask = 1;
		while (copy != 0 && (copy & 1) == 1) {
			mask = (mask << 1);
			copy = copy >> 1;
		}
		return copy == 0 ? n : (n | mask);
	}

	private static void type1() {
		int n = 1279;
		int N = setBit(n);
		System.out.println(Integer.toBinaryString(n));
		System.out.println(Integer.toBinaryString(N));
	}

	// TODO check later
	static int setBit(int n) {
		for (int i = 0; i < 32; i++) {
			int num = (1 << i) | n;
			if ((1 << i) > n) return n;
			if (num != n) return num;
		}
		return n;
	}
}
