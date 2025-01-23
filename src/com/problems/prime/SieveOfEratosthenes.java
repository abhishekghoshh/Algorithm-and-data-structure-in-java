package com.problems.prime;

/*
 * Problem link :
 * https://leetcode.com/problems/count-primes/description/
 * https://www.codingninjas.com/studio/problems/prime-factorisation_1760849
 * 
 * Solution link :
 * 
 * 
 */
public class SieveOfEratosthenes {

	// overall time complexity can be O(n*log(log(n)))
	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}


	// 2nd level optimization j = i * i
	// let's say we have already marked for till 4
	// now we have to marked for 5
	// so we will mark for 10 15 20 25
	// but if we look closely then we can see that 10,20 is marked by 2
	// 15 marked by 3
	// again if we start for 7
	// then 14 is marked by 2
	// 21 is marked by 3
	// 28 is marked by 2
	// 35 is marked by 5
	// we will have start from 49 => 7*7
	private static void type3() {
		int n = 50;
		boolean[] primes = new boolean[n + 1];
		for (int i = 2; i <= n; i++)
			primes[i] = true;
		for (int i = 2; i * i <= n; i++) {
			if (primes[i]) {
				// 2nd level optimization
				for (int j = i * i; j <= n; j += i) {
					primes[j] = false;
				}
			}
		}
		for (int j = 0; j <= n; j++) {
			System.out.println(j + " : " + primes[j]);
		}
	}

	// first level optimization i * i <= n
	// as the factors of n should be present before sqrt(n)
	private static void type2() {
		int n = 50;
		boolean[] primes = new boolean[n + 1];
		for (int i = 2; i <= n; i++)
			primes[i] = true;
		// first level of optimization
		for (int i = 2; i * i <= n; i++) {
			if (primes[i]) {
				for (int j = 2 * i; j <= n; j += i) {
					primes[j] = false;
				}
			}
		}
		for (int j = 0; j <= n; j++) {
			System.out.println(j + " : " + primes[j]);
		}
	}

	// brute force approach
	private static void type1() {
		int n = 50;
		boolean[] primes = new boolean[n + 1];
		for (int i = 2; i <= n; i++)
			primes[i] = true;
		for (int i = 2; i <= n; i++) {
			if (primes[i]) {
				for (int j = 2 * i; j <= n; j += i) {
					primes[j] = false;
				}
			}
		}
		for (int j = 0; j <= n; j++) {
			System.out.println(j + " : " + primes[j]);
		}
	}

}
