package com.problems.bitmanipulation;

public class NegativeOfNumber {

	// let say n = 10
	// let's consider only 8 bit or 1 byte
	// 10 => 00001010
	// we know n + (-n) = 0
	// ~n => 11110101
	// n+(~n) => 11111111
	// n+(~n)+1 => 100000000 => 9 bit where
	// 9th bit is only 1 and remaining 8 is 0
	// but in computer 9th bit will be discarded
	// so n+(~n)+1 => 00000000 => 8 bit
	// n+(~n)+1 = n+ (-n)
	// so -n = (~n)+1

	// let's say we have 8bits or one byte
	// so we can represent 256 numbers with this
	// but the first bit is reserve for sign
	// so 128 for negative and 128 for positive
	// but in positive we have 0 as well
	// so in positive the range is 0-128 and in negative the range is -1 to -128
	// so in general if the total we have n bit
	// then the range is from -2^(n-1) to 2^(n-1) -1
	public static void main(String[] args) {
		type1();
	}

	private static void type1() {
		int n = 278;

		System.out.println("(n) : " + toBinaryString(n));
		System.out.println("(~n): " + toBinaryString(~n));
		System.out.println("(~n) + 1 : " + toBinaryString((~n) + 1));

		int nOn = (~n) + 1;
		System.out.println(n + " : " + nOn);
	}

	private static String toBinaryString(int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 32; i++) {
			sb.append(n & 1);
			n = n >>> 1;
		}
		return sb.reverse().toString();
	}

}
