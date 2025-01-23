package com.problems.prime;
/*
 * Problem link :
 * https://practice.geeksforgeeks.org/problems/prime-number2314/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=1xNbjMdbjug
 *
 * https://takeuforward.org/data-structure/check-if-a-number-is-prime-or-not/
 */

public class isPrime {

	public static void main(String[] args) {
		// facts about primes
		// 2 is minimum prime number
		// 2 is even prime number
		// every prime number can be written as 6b+1 or 6n-1, except 2 and 3, where n,b
		// is a natural number
		// Goldbach Conjecture
		// Every even number greater than two can be expressed as the sum of two prime
		// Wilson theorem
		// (p-1)! mod p = (p-1) mod p
		// if p is prime then
		// (p-1) factorial mod p will be equal to (p-1) mod p
		// let take p as 7
		// (p-1)!= 720 mod 7 => 6 which is equal to (p-1) mod p
		// 2 and 3 are only consecutive prime number
		type1();
		type1_();
		type2();
		type3();
		type3_();
	}

	private static void type3_() {
		int n = 27;
		// if we perform Math.sqrt(n) instead of i*i
		// then it will take extra log(n) because sqrt function take log(n) time
		int count = 0;
		for (int i = 1; i * i <= n; i++) {
			// for i=1
			// n/i = n
			// count will track the multiples of n
			if (n % i == 0) {
				count++;
				// for i*i ==n
				// n/i will also i
				// so that will introduce a duplicate multiple
				// so this check n / i != i is needed
				if (n / i != i) {
					count++;
				}
			}
		}
		if (count == 2) {
			System.out.println(n + " is prime : " + false);
		} else {
			System.out.println(n + " is prime : " + true);
		}
	}

	// intuition
	// lets take a number 36 and see i'ts factors
	// 1 x 36
	// 2 x 18
	// 3 X 12
	// 4 x 9
	// 6 x 6
	// 9 x 4
	// 12 x 3
	// 18 x 2
	// 36 x 1
	// if we see closely then we will find
	// that after 6 x 6
	// all the multiplications is repeated
	// so in order to check a number is prime or not
	// then we can check for the first half
	// for any number n if n = a x b and a<=b
	// then a<= sqrt(n)
	// time complexity O(sqrt(n))
	private static void type3() {
		int n = 27;
		boolean isPrime = true;
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				isPrime = false;
				break;
			}
		}
		System.out.println(n + " is prime : " + isPrime);
	}

	private static void type2() {
		int n = 27;
		boolean isPrime = true;
		for (int i = 2; i < n / 2 + 1; i++) {
			if (n % i == 0) {
				isPrime = false;
				break;
			}
		}
		System.out.println(n + " is prime : " + isPrime);
	}

	// brute force approach
	private static void type1_() {
		int n = 27;
		int count = 0;
		for (int i = 1; i <= n; i++) {
			if (n % i == 0) {
				count++;
			}
		}
		System.out.println(n + " is prime : " + (count == 2));
	}

	// brute force approach
	private static void type1() {
		int n = 27;
		boolean isPrime = true;
		for (int i = 2; i < n; i++) {
			if (n % i == 0) {
				isPrime = false;
				break;
			}
		}
		System.out.println(n + " is prime : " + isPrime);
	}

}
