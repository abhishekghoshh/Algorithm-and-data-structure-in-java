package com.problems.bitmanipulation;

public class CountTotalSetBitsForRange {

	public static void main(String[] args) {
		type1();
	}

	// most optimized approach
	// for reference see count total set bits
	private static void type1() {
		int start = 0;
		int end = 100;
		int answer = countTotalSetBits(end) - countTotalSetBits(start - 1);
		System.out.println(answer);
	}

	public static int countTotalSetBits(int n) {
		if (n <= 0)
			return 0;
		int count = 0;
		int index = 30;
		int mask = 1 << index;
		while (index >= 0) {
			if ((n & mask) != 0) {
				count += index * (1 << (index - 1)) + 1 + (n - mask);
				n = n & (~mask);
			}
			mask = mask >> 1;
			index--;
		}
		return count;
	}

}
