package com.problems.binarysearch;

import java.util.Arrays;

/*
 * Problem link :
 * https://www.codingninjas.com/studio/problems/median-of-a-row-wise-sorted-matrix_1115473
 * https://www.interviewbit.com/problems/matrix-median/
 * https://www.codingninjas.com/codestudio/problems/873378
 * 
 * Solution is :
 * https://www.youtube.com/watch?v=Q9wXgdxJq48
 * https://www.youtube.com/watch?v=63fPPOdIr2c&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=63
 * https://www.youtube.com/watch?v=_4rxBuhyLXw
 *
 * https://takeuforward.org/data-structure/median-of-row-wise-sorted-matrix/
 * https://www.geeksforgeeks.org/find-median-row-wise-sorted-matrix/
 * */
public class MedianOfRowWiseSortedMatrix {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	private static void type3() {
		int[][] matrix = {{2, 5, 5}, {2, 5, 12}, {3, 5, 15}};
		int answer = findMedian(matrix, matrix.length, matrix[0].length);
		System.out.println(answer);
	}

	public static int findMedian(int[][] matrix, int m, int n) {
		int low = 1;
		int high = Integer.MAX_VALUE;
		while (low <= high) {
			int mid = (low + high) / 2;
			int cnt = 0;
			for (int i = 0; i < m; i++) cnt += findSmallerOrEqualThanMid(matrix[i], mid);
			if (cnt <= (m * n) / 2) low = mid + 1;
			else high = mid - 1;
		}
		return low;
	}

	public static int findSmallerOrEqualThanMid(int[] arr, int mid) {
		int low = 0, high = arr.length - 1;
		while (low <= high) {
			int md = low + ((high - low) >> 1);
			if (arr[md] <= mid) low = md + 1;
			else high = md - 1;
		}
		return low;
	}

	// https://www.geeksforgeeks.org/find-median-row-wise-sorted-matrix/#:~:text=Simple%20Method%3A%20The%20simplest%20method,O(r*c).
	// study one more time
	private static void type2() {
		int[][] matrix = { { 2, 5, 5 }, { 2, 5, 12 }, { 3, 5, 15 } };
		// as per the given question r and c will always be odd
		// so r*c will also be odd and there will be element from 0 to r*c-1 element
		int r = matrix.length, c = matrix[0].length;
		int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;
		for (int[] row : matrix) {
			if (low > row[0]) low = row[0];
			if (high < row[c - 1]) high = row[c - 1];
		}
		// median element index will be (row*column-1)/2
		int medianIndex = (r * c - 1) / 2;
		int mid, position;
		while (low <= high) {
			mid = low + ((high - low) >> 1);
			position = 0;
			// for every mid we will find the position in every row
			// then we will add it
			// to know it's actual position in the combined sorted array
			for (int[] row : matrix) position += upperBoundIndex(row, mid);
			// if position is greater than median
			// then we know that the mid element can not be the median
			// so we initialize low with mid+1
			if (position <= medianIndex) low = mid + 1;
			else high = mid - 1;
		}
		System.out.println(low);
	}

	private static int upperBoundIndex(int[] row, int item) {
		int low = 0, high = row.length - 1, mid;
		while (low <= high) {
			mid = low + (high - low) / 2;
			if (row[mid] <= item) low = mid + 1;
			else high = mid - 1;
		}
		return low;
	}

	// brute force approach
	// if r is row and c is column and n=r*c
	// then time complexity is O(n+n*log(n))
	// O(n) to put it in list
	// O(n*log(n)) to sort the list
	// space complexity O(n) for the list
	private static void type1() {
		int[][] matrix = { { 2, 5, 5 }, { 2, 5, 12 }, { 3, 5, 15 } };
		int n = matrix.length * matrix[0].length;
		int[] list = new int[n];
		int i = 0;
		for (int[] row : matrix) for (int item : row) list[i++] = item;
		Arrays.sort(list);
		int answer = list[(n - 1) / 2];
		System.out.println(answer);
	}
}
