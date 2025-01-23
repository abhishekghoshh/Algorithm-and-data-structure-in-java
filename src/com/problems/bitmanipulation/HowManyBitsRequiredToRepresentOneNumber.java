package com.problems.bitmanipulation;

public class HowManyBitsRequiredToRepresentOneNumber {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// when base is 2
	private static void type3() {
		int n = 100;
		int bitsRequired = 0;
		while (n != 0) {
			bitsRequired++;
			n = (n >> 1);
		}
		System.out.println("bits required is " + bitsRequired);
	}

	// optimized approach
	private static void type2() {
		int n = 100;
		int base = 2;
		int bitsRequired = 0, bound = 1;
		while (bound <= n) {
			bitsRequired++;
			bound *= base;
		}
		System.out.println("bits required is " + bitsRequired);
	}

	// for in general
	private static void type1() {
		int n = 100;
		int base = 2;
		int bitsRequired = 1 + (int) (Math.log(n) / Math.log(base));
		System.out.println("bits required is " + bitsRequired);
	}

}
