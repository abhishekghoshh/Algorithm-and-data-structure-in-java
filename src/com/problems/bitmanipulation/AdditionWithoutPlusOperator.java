package com.problems.bitmanipulation;

/*
 * Problem link :
 * https://leetcode.com/problems/sum-of-two-integers/
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=qq64FrA2UXQ
 * https://www.youtube.com/watch?v=N3dtzMKJMn8
 * 
 */
public class AdditionWithoutPlusOperator {

	public static void main(String[] args) {
		type1();
	}

	// TODO check later
	//  optimized approach
	//  -1000 <= a, b <= 1000
	private static void type1() {
		int a = 5;
		int b = -6;
		int sum = getSum(a, b);
		System.out.println(a);
	}

	public static int getSum(int a, int b) {
		while (b != 0) {
			int carry = (a & b) << 1;
			a = a ^ b;
			b = carry;
		}
		return a;
	}
}
