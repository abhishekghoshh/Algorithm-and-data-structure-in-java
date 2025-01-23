package com.problems.prime;

import java.util.ArrayList;
import java.util.List;
/*
 * Problem link :
 * 
 * 
 * Solution link :
 * https://www.geeksforgeeks.org/segmented-sieve/
 * https://www.codingninjas.com/codestudio/library/segmented-sieve
 * 
 */

public class SegmentedSieve {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// we will be given range L and R
	// and the range is in between 10^6
	// like 10^8 to 10^8+10^6
	// we can not make an array of 10^8+10^6
	// it will give an error while creating array
	// so here segmented sieve will be applied
	// so when the range end is too large rather than
	// creating the sieve array for (0-R)
	// we will compute the prime factors till sqrt(R)
	// as the factors of R will be before sqrt(R)
	// then we will create an array of the range size
	private static void type2() {
		int l = 100000000;// range start
		int r = 101000000;// range end
		int bound = (int) Math.sqrt(r);
		boolean[] nonPrimes = new boolean[bound + 1];
		nonPrimes[0] = nonPrimes[1] = true;
		// this we make a sieve array for 0-sqrt(r)
		for (int i = 2; i * i <= bound; i++) {
			for (int j = i * i; j <= bound; j += i) {
				nonPrimes[j] = true;
			}
		}
		// with this loop we will get all the prime elements till 0-sqrt(r)
		List<Integer> primes = new ArrayList<>(bound);
		for (int i = 0; i <= bound; i++) {
			if (!nonPrimes[i])
				primes.add(i);
		}
		// resultant array
		boolean[] resultantPrime = new boolean[r - l + 1];
		for (int prime : primes) {
			int firstMultiple = (int) Math.ceil(l / prime) * prime;
			for (int j = Math.max(firstMultiple, prime * prime); j * j <= r; j += prime) {
				resultantPrime[j] = true;
			}
		}

	}

	private static void type1() {

	}

}
