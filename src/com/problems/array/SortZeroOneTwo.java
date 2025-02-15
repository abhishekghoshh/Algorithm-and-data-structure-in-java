package com.problems.array;

import java.util.Arrays;

import static com.util.ArrayUtil.swap;
import static com.util.PrintUtl.print;

/*
 * 
 * problem links :
 * https://leetcode.com/problems/sort-colors/description/
 * https://www.naukri.com/code360/problems/sort-an-array-of-0s-1s-and-2s_892977
 * https://www.naukri.com/code360/problems/631055
 *
 * Solution link :
 * https://www.youtube.com/watch?v=tp8JIuCXBaU
 * https://www.youtube.com/watch?v=oaVa-9wmpns&list=PLgUwDviBIf0rPG3Ictpu74YWBQ1CaBkm2&index=2
 * https://www.youtube.com/watch?v=4xbWSRZHqac
 *
 * https://takeuforward.org/data-structure/sort-an-array-of-0s-1s-and-2s/
 * https://neetcode.io/solutions/sort-colors
 * */

// Tags: Arrays, sorting, hashing, Dutch national flag algorithm 3 pointer
public class SortZeroOneTwo {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// in place ordering using three pointer
	// todo Dutch national flag algorithm 3 pointer
	// TODO this solution is failing for some test cases in leetcode
	private static void type3() {
		int[] arr = { 2, 0, 2, 1, 1, 0, 0, 2, 1, 0, 2, 1, 0 };
		sortColors3(arr);
		print(arr);
	}

	private static void sortColors3(int[] arr) {
		int n = arr.length;
		int low = 0, mid = 0, high = n - 1;
		while (mid <= high) {
			if (arr[mid] == 0) {
				swap(arr, low, mid);
				low++;
				mid++;
			} else if (arr[mid] == 1) {
				mid++;
			} else {
				while (mid <= high && arr[high] == 2) {
					high--;
				}
				swap(arr, mid, high);
				high--;
			}
		}
	}

	// counting sort
	// count the occurrence of 0,1,2
	// it will solve in o(n) but the loop will run twice
	// explain this approach in the interview
	private static void type2() {
		int[] arr = { 2, 0, 2, 1, 1, 0, 0, 2, 1, 0, 2, 1, 0 };
		sortColors2(arr);
		print(arr);
	}

	private static void sortColors2(int[] arr) {
		int n = arr.length;
		int[] freq = {0, 0, 0};
		for (int item : arr) freq[item]++;
		int i = 0;
		int num = 0;
		while (i < n) {
			if (freq[num] == 0) {
				num++;
			} else {
				arr[i++] = num;
				freq[num]--;
			}
		}
	}

	// brute force approach
	// sort the array with o(nlogn) complexity
	private static void type1() {
		int[] arr = { 2, 0, 2, 1, 1, 0, 0, 2, 1, 0, 2, 1, 0 };
		sortColors1(arr);
		print(arr);
	}

	private static void sortColors1(int[] arr) {
		Arrays.sort(arr);
	}

}
