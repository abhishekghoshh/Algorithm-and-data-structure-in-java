package com.problems.bitmanipulation;

import java.util.HashMap;
import java.util.Map;

public class NqueriesForNumberToThePowerK {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// queries must be less than 20
	private static void type3() {
		int n = 7;
		int[] queries = { 7, 8, 19, 12, 3 };
		long[] answer = new long[queries.length];

		// pre computation of values
		Map<Integer, Long> powers = new HashMap<>();
		// store n^1, n^2 , n^4, n^8 and so on
		powers.put(1, (long) n);
		for (int i = 2; i <= 16; i = i * 2) {
			powers.put(i, powers.get(i / 2) * powers.get(i / 2));
		}
		int i = 0, lastSetBit;
		long val;
		for (int query : queries) {
			val = 1L;
			while (query != 0) {
				// extracting the last set bit
				lastSetBit = query & (~(query - 1));
				val *= powers.get(lastSetBit);
				// removing the last set bit from the number
				query = query & (query - 1);
			}
			answer[i++] = val;
		}
		print(answer);
	}

	private static void print(long[] answer) {
		for (double num : answer) {
			System.out.print(num + " ");
		}
		System.out.println();

	}

	// queries must be less than 20
	private static void type2() {
		int n = 7;
		int[] queries = { 7, 8, 19, 12, 3 };
		long[] answer = new long[queries.length];
		long powers[] = new long[21];
		powers[0] = 1;
		for (int i = 1; i < 21; i++) {
			powers[i] = powers[i - 1] * (long) n;
		}
		for (int i = 0; i < queries.length; i++) {
			answer[i] = powers[queries[i]];
		}
		print(answer);
	}

	private static void type1() {
		int n = 7;
		int[] queries = { 7, 8, 19, 12, 3 };
		double[] answer = new double[queries.length];
		for (int i = 0; i < queries.length; i++) {
			answer[i] = Math.pow(n, queries[i]);
		}
		print(answer);
	}

	private static void print(double[] answer) {
		for (double num : answer) {
			System.out.print(num + " ");
		}
		System.out.println();
	}

}
