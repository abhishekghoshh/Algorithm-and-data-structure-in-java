package com.problems.bitmanipulation;

public class ClearKthBit {

	public static void main(String[] args) {
		type1();
	}

	// suppose n = 10110
	// and k = 3
	// answer is 10010
	// we will create a mask such that kth bit will set to 1, it does not matter
	// what was the earlier value and it will not change the other bit
	// we know one thing that if we and(&) with 0 then the answer will be always 0
	// and if we and(&) with 1 then the output is same as the input
	// so we have to create one mask such that the all the bit is 1 except the kth
	// 1<<k means all the bit is 0 except the kth
	// ~(1<<k) means all the bit is 1 except kth
	private static void type1() {
		int n = 123;
		int k = 4;
		int mask = ~(1 << k);
		int answer = n & mask;
		System.out.println(answer);
	}

}
