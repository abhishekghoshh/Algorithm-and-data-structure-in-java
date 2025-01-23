package com.problems.bitmanipulation;

/*
 * Problem link :
 * https://leetcode.com/problems/counting-bits/
 * https://www.geeksforgeeks.org/problems/count-total-set-bits-1587115620/1
 *
 * Solution link :
 * https://www.youtube.com/watch?v=ZzZcFXDcbJw
 * https://www.youtube.com/watch?v=kU5G5-6xEF4
 * 
 */
public class CountTotalSetBits {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
		type5();
		type6();
	}

	// suppose the number is 01100
	// we can break the number such as
	// 01000
	// 00100
	// we know how to find total set bit count when it is 2^n
	// so we will find 01000 then 00100
	// but we have also keep track of the bits for extra 1
	// as range is 01000 -> 01001->01010->01011->>>01100
	// so there is one extra 1 bit for for 000->100 bit
	private static void type6() {
		int n = 100;
		int count = 0;
		// we will start from 30 then we will count for every set bit
		int index = 30;
		int mask = 1 << index;
		while (index >= 0) {
			if ((n & mask) != 0) {
				// we have found one set bit
				// the number is 2 to power n then total count is n*2^(n-1)+1
				// (n - mask) for the extra 1 bits in the left
				count += index * (1 << (index - 1)) + 1 + (n - mask);
				// removing the left most set bit
				n = n & (~mask);
			}
			mask = mask >> 1;
			index--;
		}
		System.out.println(count);
	}

	// suppose the number is 01100
	// we can break the number such as
	// 01000
	// 00100
	// we know how to find total set bit count when it is 2^n
	// so we will find 01000 then 00100
	// but we have also keep track of the bits for extra 1
	// as range is 01000 -> 01001->01010->01011->>>01100
	// so there is one extra 1 bit for for 000->100 bit
	private static void type5() {
		int n = 100;
		int count = countSetBits(n);
		System.out.println(count);
	}

	// Function to return sum of count of set bits in the integers from 1 to n.
	public static int countSetBits(int n) {
		if (n == 0)
			return 0;
		int index = leftMostSetBitIndex(n);
		// the number is 2 to power n then total count is n*2^(n-1)+1
		// (n - (1 << index)) for the extra 1 bits in the left
		int totalBitForLeftSetBit = index * (1 << (index - 1)) + 1 + (n - (1 << index));
		n = n - (1 << index);
		return totalBitForLeftSetBit + countSetBits(n);
	}

	public static int leftMostSetBitIndex(int n) {
		int x = 0;
		while ((1 << x) <= n) {
			x++;
		}
		return x - 1;
	}

	// same as type3
	// we are just finding the total count here
	private static void type4() {
		int n = 100;
		int count = 0;
		int arr[] = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			arr[i] = arr[i >> 1] + (i & 1);
			count = count + arr[i];
		}
		System.out.println(count);
	}

	// it is same as type2
	// i/2 and i>>1 is same
	// i%2 and i&1 is same
	// be cautious while working with bitwise operator
	private static void type3() {
		int n = 7;
		int arr[] = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			arr[i] = arr[i >> 1] + (i & 1);
		}
		print(arr);
	}

	// Intuition
	// for a number like 6 has the same bit count as 6/2=>3
	// for 3 and 6 has the same bit count
	// now lets think reverse
	// if the number is 3 then 3*2 and 3*2+1 can be derived from 3
	// 3*2 has the same bit count as 3 and 7 has just exactly one bit more than 3
	// so for a number n the count of bit is the same as n/2 if n is positive and
	// count(n/2)+1 if n is negative
	// so we can store the previous results in array
	private static void type2() {
		int n = 7;
		int arr[] = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			arr[i] = arr[i / 2] + i % 2;
		}
		print(arr);
	}

	// for each number we are counting the bits
	// for each number the average complexity is O(log(n/2))
	// there are n numbers
	// so total time complexity is O(n*log(n))
	// but we can do better
	private static void type1() {
		int n = 7;
		int[] arr = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			arr[i] = count(i);
		}
		print(arr);
	}

	private static int count(int i) {
		int ones = 0;
		while (i != 0) {
			ones = ones + (i & 1);
			i = i >>> 1;
		}
		return ones;
	}

	private static void print(int[] ans) {
		for (int num : ans) {
			System.out.print(num + " ");
		}
		System.out.println();
	}
}
