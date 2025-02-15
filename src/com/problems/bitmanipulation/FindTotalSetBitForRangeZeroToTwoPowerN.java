package com.problems.bitmanipulation;

public class FindTotalSetBitForRangeZeroToTwoPowerN {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// 4 3 2 1 0 index
	// 0 0 0 0 0
	// 0 0 0 0 1
	// 0 0 0 1 0
	// 0 0 0 1 1 ->> till this count of 1 is 4
	// 0 0 1 0 0 ->> till this count of 1 is 4+1
	// 0 0 1 0 1
	// 0 0 1 1 0
	// 0 0 1 1 1 ->> till this count of 1 is 12
	// 0 1 0 0 0 ->> till this count of 1 is 12+1
	// 0 1 0 0 1
	// 0 1 0 1 0
	// 0 1 0 1 1
	// 0 1 1 0 0
	// 0 1 1 0 1
	// 0 1 1 1 0
	// 0 1 1 1 1 ->> till this count of 1 is 32
	// 1 0 0 0 0 ->> till this count of 1 is 32+1
	// if we look closely then if
	// the number is 2 to power n then total count is n*2^(n-1)+1
	private static void type2() {
		int n = 128;
		// find the index of left most set bit
		int mask = 1 << 30, index = 30;
		while (index >= 0) {
			if ((mask & n) != 0) {
				break;
			}
			index--;
			mask = mask >> 1;
		}
		// at this point we have find the index where bin[index]=1
//		int count = index * (int) Math.pow(2, index - 1) + 1;
		int count = index * (1 << (index - 1)) + 1;
		System.out.println(count);
	}

	private static void type1() {
		int n = 128;
		int count = 0;
		for (int i = 1; i <= n; i++) {
			count += countBit(i);
		}
		System.out.println(count);
	}

	private static int countBit(int n) {
		int count = 0;
		while (n != 0) {
			n = n & (n - 1);
			count++;
		}
		return count;
	}

}
