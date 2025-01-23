package com.problems.bitmanipulation;

/*
 *
 * problem links :
 * https://www.codingninjas.com/studio/problems/swap-two-numbers_1380853
 *
 * Solution link :
 *
 *
 *
 * */
public class SwapTwoNumbers {

	public static void main(String[] args) {
		type1();
		type2();
	}
	private static void type2() {
		int x = 5;
		int y = 8;
		x = x ^ y;
		y = x ^ y; // this line is now same ase x ^ y ^ y which is x => now y has the value of x
		x = x ^ y; // this line is now same as x ^ y ^ x which is y => now x has the value of y
		System.out.println(x);
		System.out.println(y);
	}

	// let's day a =5 b=7
	// a = 5^7
	// b= 5^7^7 => 5
	// a = 5^7^5 => 7
	// swap two variables without using third variable
	private static void type1() {
		int a = 5, b = 7;
		System.out.println("a = " + a + " b = " + b);
		a = a ^ b;
		b = b ^ a;
		a = a ^ b;
		System.out.println("a = " + a + " b = " + b);
	}

}
