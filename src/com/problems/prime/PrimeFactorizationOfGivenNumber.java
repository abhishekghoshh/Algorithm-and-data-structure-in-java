package com.problems.prime;

import java.util.ArrayList;
import java.util.List;


/*
 * Problem link :
 * https://www.codingninjas.com/studio/problems/prime-factorisation_1760849
 *
 * Solution link :
 *
 *
 */
public class PrimeFactorizationOfGivenNumber {

	// for 12 it is 2*2*3
	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
	}

	private static void type4() {
		int n = 124;
		primeFactor = null;
		initiateSieveArray();
		List<Integer> list = new ArrayList<>();
		while (n != 1) {
			if (list.isEmpty() || list.get(list.size() - 1) != primeFactor[n])
				list.add(primeFactor[n]);
			n = n / primeFactor[n];
		}
		System.out.println(list);
	}

	// the most optimized approach
	// for any number maximum prime factor is log(n) base 2
	// because the lowest prime factor is 2
	// time complexity O(log(n))
	// first we will have to create the modified sieve array
	private static int[] primeFactor = null;
	private static final int N = 1000000;// 10^6

	private static void type3() {
		if (null == primeFactor) initiateSieveArray();
		int[] queries = {12, 25, 8, 456, 122587, 128, 367};
		for (int query : queries) {
			int q = query;
			List<Integer> list = new ArrayList<>();
			while (query != 1) {
				list.add(primeFactor[query]);
				query = query / primeFactor[query];
			}
			System.out.println(q + " : " + list);
		}
	}

	private static void initiateSieveArray() {
		primeFactor = new int[N + 1];
		for (int i = 0; i <= N; i++) primeFactor[i] = i;
		for (int i = 2; i * i <= N; i++)
			for (int j = i * i; j <= N; j += i)
				// that means j is not marked with any prime factor
				if (primeFactor[j] == j) primeFactor[j] = i;
	}

	// optimized modified approach
	// but it will also give TLE if query size is 10^6
	// time complexity O(sqrt(n))
	private static void type2() {
		int n = 629;
		List<Integer> answer = new ArrayList<>();
		// we know that for any number its factors can be found before sqrt(n)
		// so we can run till sqrt(n)
		for (int i = 2; i * i <= n; i++) {
			while (n % i == 0) {
				answer.add(i);
				n = n / i;
			}
		}
		// at the end if n is not 1
		// then there current n is prime that can not be divided
		// further in range of sqrt(n)
		if (n != 1) answer.add(n);
		System.out.println(answer);
	}

	// brute force
	// time complexity O(n)
	private static void type1() {
		int n = 48;
		List<Integer> answer = new ArrayList<>();
		for (int i = 2; i <= n; i++) {
			while (n % i == 0) {
				answer.add(i);
				n = n / i;
			}
		}
		System.out.println(answer);
	}
}
