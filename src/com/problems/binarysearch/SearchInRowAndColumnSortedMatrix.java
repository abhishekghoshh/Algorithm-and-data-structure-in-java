package com.problems.binarysearch;

/*
 * Problem link :
 * https://leetcode.com/problems/search-a-2d-matrix/
 * https://www.codingninjas.com/codestudio/problems/980531
 * https://www.codingninjas.com/studio/problems/search-in-a-2d-matrix_980531
 *
 * Solution link :
 * https://www.youtube.com/watch?v=JXU4Akft7yk
 * https://www.youtube.com/watch?v=ZYpYur0znng&list=PLgUwDviBIf0rPG3Ictpu74YWBQ1CaBkm2&index=15
 *
 * https://takeuforward.org/data-structure/search-in-a-sorted-2d-matrix/
 *
 * */
public class SearchInRowAndColumnSortedMatrix {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}


	// TODO best approach
	// When integers are sorted row wise and column
	// means a[i][n-1] < a[i+1][0]
	// start element of a row is greater than last element of previous row
	// from there we will go either left side or bottom side
	// we can think of the matrix as a large sorted array stored in a matrix
	// time complexity is o(log(n*m))
	private static void type3() {
		int[][] matrix = { { 10, 20, 30, 40 }, { 15, 25, 35, 45 }, { 27, 29, 37, 48 }, { 32, 33, 39, 50 } };
		int target = 50;
		int row = matrix.length;
		int column = matrix[0].length;
		int low = 0;
		int high = row * column - 1;
		int mid, i = 0, j = 0;
		while (low <= high) {
			mid = low + (high - low) / 2;
			i = mid / column;
			j = mid % column;
			if (matrix[i][j] == target) {
				break;
			} else if (matrix[i][j] < target) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		System.out.printf("arr[%d][%d] = %d%n", i, j, matrix[i][j]);
	}

	// When integers are sorted row wise and column
	// but that does not mean a[i][n-1] < a[i+1][0]
	// start element of a row may or may not be greater than last element of
	// previous row
	// we will start from [0,last]
	// from there we will go either left side or bottom side
	// time complexity is o(n+m)
	private static void type2() {
		int[][] matrix = { { 10, 15, 17, 20 }, { 22, 23, 25, 27 }, { 28, 29, 32, 35 }, { 37, 39, 45, 50 } };
		int target = 50;
		int row = matrix.length;
		int column = matrix[0].length;
		int r = 0;
		int c = column - 1;
		while (r <= row - 1 && c >= 0)
			if (matrix[r][c] > target) c--;
			else if (matrix[r][c] < target) r++;
			else break;
		System.out.printf("arr[%d][%d] = %d%n", r, c, matrix[r][c]);
	}

	// search all items
	// complexity o(n*m)
	private static void type1() {
		int[][] matrix = { { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 60 } };
		int target = 3;
		int row = matrix.length;
		int column = matrix[0].length;
		int x = -1, y = -1;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++)
				if (matrix[i][j] == target) {
					x = i;
					y = j;
					break;
				}
			if (x != -1) break;
		}
		System.out.printf("arr[%d][%d] = %d%n", x, y, matrix[x][y]);
	}

}
