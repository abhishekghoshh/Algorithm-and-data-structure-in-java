package com.problems.bitmanipulation;

public class ToggleKthBit {

	public static void main(String[] args) {
		type1();
	}

	// suppose n = 10110 and k = 3 answer is 10010
	// suppose n = 10010 and k = 3 answer is 10110
	// we will create a mask such that kth bit will toggle and it will not change
	// the other bit we know one thing that if we xor(^) with 1 then the answer will
	// toggle as 0^1 => 1 and 1^1 => 0, and if we xor(^) with 0 then the output is
	// same as the input as 0^0 => 0 and 1^0 => 1
	// so we have to create one mask such that the all the bit is 0 except the kth
	// so our mask is 1<<k
	// it will left shift the 1 by k bits
	private static void type1() {
		int n = 123; // ans is 107
//		int n = 107; // ans is 123
		int k = 4;
		int mask = 1 << k;
		int answer = n ^ mask;
		System.out.println(answer);
	}
}
