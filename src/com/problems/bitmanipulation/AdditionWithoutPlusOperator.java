package com.problems.bitmanipulation;

/*
 * Problem link :
 * https://leetcode.com/problems/sum-of-two-integers/description/
 * https://neetcode.io/problems/sum-of-two-integers
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=qq64FrA2UXQ
 * https://www.youtube.com/watch?v=N3dtzMKJMn8
 *
 *
 * https://www.youtube.com/watch?v=gVUrDV4tZfY
 * https://neetcode.io/solutions/sum-of-two-integers
 */
public class AdditionWithoutPlusOperator {

	public static void main(String[] args) {
		type1();
	}

	// todo if we try to use only bitwise operators then it is the only option
	private static void type1() {
		int a = 5;
		int b = -6;
		int sum = getSum(a, b);
		System.out.println(sum);
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
