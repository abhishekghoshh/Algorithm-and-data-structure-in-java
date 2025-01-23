package com.algo.sort;

import com.util.PrintUtl;

import static com.util.ArrayUtil.*;

/*
 * Problem link :
 * https://practice.geeksforgeeks.org/problems/counting-sort/1
 * 
 * Solution link :
 * https://www.geeksforgeeks.org/in-place-algorithm/
 * https://www.geeksforgeeks.org/counting-sort/
 * 
 */
public class CountSort {

	public static void main(String[] args) {
		type1();
		type2();
	}

	private static void type2() {
		int[] arr = { 2, 5, 7, 5, 9, 15, 13, 2, 7, 8 };
		int n = arr.length;
		int max = max(arr);
		int min = min(arr);
		int[] copy = copy(arr);
		int range = max - min + 1;
		int[] freq = new int[range];
		// we are subtracting everything with minus because we want a range from 0
		for (int j : arr)
			freq[j - min]++;
		// Modify the count array such that each element at each index stores the sum of previous counts.
		// after this iteration all item freq array will store the corresponding indexes of every item of original array
		// if the array is 1 4 4 6 6 6 8
		// then freq will be 0 1 0 0 2 0 3 0 1
		// after this iteration freq will be 0 1 1 1 3 3 6 6 7
		for (int i = 1; i < range; i++)
			freq[i] += freq[i - 1];
		// if we look closely 1 will be from [0-1)
		// 4 will be from [1-3)
		// 6 will be from [3-6)
		// 8 will be from [6-7)
		// every item from original array stores the last index in the frequency array
		// 1 stores 1
		// 4 stores 3
		// so if we start from freq[item]-1
		// and every time we do freq[item]-- then it will store the current starting
		// index of the number
		for (int i = n - 1; i >= 0; i--) {
			int item = copy[i] - min;
			arr[freq[item] - 1] = copy[i];
			freq[item]--;
		}
		PrintUtl.print(arr);
	}

	private static void type1() {
		String str = "geeksforgeeks";
		char[] arr = str.toCharArray();
		int n = arr.length;
		int[] freq = new int[26];
		for (char c : arr)
			freq[c - 'a']++;
		int index = 0;
		for (int i = 0; i < 26; i++)
			while (freq[i] > 0) {
				arr[index++] = (char) ('a' + i);
				freq[i]--;
			}
		PrintUtl.print(arr);
	}
}
