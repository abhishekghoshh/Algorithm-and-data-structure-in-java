package com.problems.bitmanipulation;

public class SetKthBit {

	public static void main(String[] args) {
		type1();
	}

	// suppose n = 10010
	// and k = 3
	// answer is 10110
	// we will create a mask such that kth bit will set to 1, it does not matter
	// what was the earlier value and it will not change the other bit
	// we know one thing that if we or(|) with 1 then the answer will be always 1
	// and if we or(|) with 0 then the output is same as the input
	// so we have to create one mask such that the all the bit is 0 except the kth
	// so our mask is 1<<k
	// it will left shift the 1 by k bits
	private static void type1() {
		int n = 123;
		int k = 4;
		int mask = 1 << k;
		int answer = n | mask;
		System.out.println(answer);
	}

}
