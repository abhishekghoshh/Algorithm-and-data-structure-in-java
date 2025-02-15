package com.problems.prime;

import java.util.Arrays;

/*
 * Problem link :
 * https://leetcode.com/problems/count-primes/description/
 * 
 * Solution link :
 * 
 * 
 */
public class PrimeCount {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	private static void type3() {
		
	}

	// better approach
	// make a global array to store all prime count
	// first compute all the primes index then from that compute primeCount array
	// return in O(1) every time
	private static int[] primeCount = null;
	private static int N = 5 * 1000000;

	private static void type2() {
		if (null == primeCount) {
			boolean[] nonPrimes = new boolean[N + 1];
			primeCount = new int[N + 1];
			nonPrimes[0] = true;
			nonPrimes[1] = true;
			for (int i = 2; i * i <= N; i++) {
				if (!nonPrimes[i]) {
					for (int j = i * i; j <= N; j += i) {
						nonPrimes[j] = true;
					}
				}
			}
			int count = 0;
			for (int i = 2; i <= N; i++) {
				count += !nonPrimes[i] ? 1 : 0;
				primeCount[i] = count;
			}
		}
		int n = 12;
		System.out.println(primeCount[n - 1]);
	}

	// brute force approach
	private static void type1() {
		int n = 12;
		if (n == 0 || n == 1)
			return;
		n--;
		boolean[] primes = new boolean[n + 1];
		Arrays.fill(primes, true);
		primes[0] = false;
		primes[1] = false;
		int i = 2;
		while (i * i <= n) {
			if (primes[i]) {
				for (int j = 2; j <= (n / i); j++) {
					primes[i * j] = false;
				}
			}
			i++;
		}
		int count = 0;
		for (int j = 2; j <= n; j++) {
			count += primes[j] ? 1 : 0;
		}
		System.out.println(count);
	}

}
