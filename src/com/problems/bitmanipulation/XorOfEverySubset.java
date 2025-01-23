package com.problems.bitmanipulation;

public class XorOfEverySubset {

	public static void main(String[] args) {
		type1();
	}

	// optimized approach O(1)
	// lets find the subset of a set
	// {} => {}
	// {1} => {},{1}
	// {1,2} => {},{1},{2},{1,2}
	// {1,2,3} => {},{1},{2},{3},{1,2},{1,3},{2,3},{1,2,3}
	// if we look closely then in total all numbers comes 2^(n-1) times
	// and we know a property that num^num =>0
	// so the final xor will be 0
	// only n=1 then xor will be arr[0]
	// as 2^(1-1) => 1 only odd number
	private static void type1() {
		int[] arr = { 1, 2 };
		int xor = 0;
		if (arr.length == 1) {
			xor = arr[0];
		}
		System.out.println(xor);
	}

}
