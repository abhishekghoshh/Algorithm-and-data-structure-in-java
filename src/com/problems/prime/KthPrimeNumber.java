package com.problems.prime;
/*
 * Problem link :
 * 
 * 
 * Solution link :
 * 
 * 
 */

public class KthPrimeNumber {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// Optimized approach
	// Amortized time complexity O(1)
	// first we will compute sieve array
	// then we will create the array of primes

	private static int[] allPrimes = null;
	private static int N = 100000000; // 10^8 array

	// one important thing to notice is that
	// we can limit our sieve size by N such that
	// our nth prime will be the last or 2nd last number in sieve
	// so at first take the longest sieve
	// then according to the range of primeCount
	// we will know on which index the largest prime is stored
	// then we will change the range of the sieve accordingly
	// this is only helpful in competitive coding challenges
	private static void type2() {
		if (null == allPrimes) {
			boolean[] nonPrimes = new boolean[N + 1];
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
			int limit = 100000; // 10^5
			allPrimes = new int[limit + 1];
			for (int i = 2; i <= N && count <= limit; i++) {
				if (!nonPrimes[i]) {
					allPrimes[count++] = i;
				}
			}
		}
		int[] queries = { 1, 100, 15, 42, 85, 10000, 12, 54 };
		for (int query : queries) {
			System.out.println(query + " th prime is " + allPrimes[query - 1]);
		}
	}

	private static void type1() {

	}

}
