package com.problems.bitmanipulation;

import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://leetcode.com/problems/counting-bits/description/
 * https://neetcode.io/problems/counting-bits
 * https://www.geeksforgeeks.org/problems/count-total-set-bits-1587115620/1
 *
 * Solution link :
 * https://www.youtube.com/watch?v=ZzZcFXDcbJw
 * https://www.youtube.com/watch?v=kU5G5-6xEF4
 *
 * https://www.youtube.com/watch?v=RyBM56RIWrM
 * https://neetcode.io/solutions/counting-bits
 */
public class CountTotalSetBits {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// todo optimized approach
	//  for a number like 6 has the same bit count as 6/2=>3
	//  for 3 and 6 has the same bit count
	//  now lets think reverse
	//  if the number is 3 then 3*2 and 3*2+1 can be derived from 3
	//  3*2 has the same bit count as 3 and 7 has just exactly one bit more than 3
	//  so for a number n the count of bit is the same as n/2 if n is positive and
	//  count(n/2)+1 if n is negative,
	//  so we can store the previous results in array
	//  (i / 2) and (i >> 1) is same and (i & 1 == 1) is same as (i % 2 == 1)
	private static void type2() {
		int n = 7;
		int[] arr = countBits2(n);
		print(arr);
	}

	private static int[] countBits2(int n) {
		int[] arr = new int[n + 1];
		for (int num = 1; num <= n; num++) {
			// 6 and 7 both derived from 3
			arr[num] = arr[num / 2];
			// but if the number is odd, then there will be another bit at the end
			if (num % 2 == 1) {
				arr[num]++;
			}
		}
		return arr;
	}


	// todo brute force approach
	//  for each number we are counting the bits
	//  for each number the average complexity is O(log(n/2))
	//  there are n numbers
	//  so total time complexity is O(n*log(n))
	//  but we can do better
	private static void type1() {
		int n = 7;
		int[] arr = countBits1(n);
		print(arr);
	}

	private static int[] countBits1(int n) {
		int[] arr = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			arr[i] = count(i);
		}
		return arr;
	}

	private static int count(int num) {
		int ones = 0;
		while (num != 0) {
			if ((num & 1) == 1) {
				ones++;
			}
			num = num >>> 1;
		}
		return ones;
	}

}
