package com.problems.bitmanipulation;

import java.util.Arrays;

/*
 * Problem link :
 * 
 * Solution link :
 * 
 * 
 */
public class MinimumXorInArray {

	public static void main(String[] args) {
		type1();
		type2();
	}

	private static void type2() {
		int[] nums = { 3, 10, 5, 25, 2, 8 };
		Arrays.sort(nums);
		int xor = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length - 1; i++) {
			xor = Math.min(xor, nums[i] ^ nums[i + 1]);
		}
		System.out.println(xor);
	}

	private static void type1() {
		int[] nums = { 3, 10, 5, 25, 2, 8 };
		int xor = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums.length; j++) {
				if (i != j) {
					xor = Math.min(xor, nums[i] ^ nums[j]);
				}
			}
		}
		System.out.println(xor);
	}

}
