package com.problems.array;

import static com.util.PrintUtl.print2D;

/*
 * 
 * problem links :
 * https://leetcode.com/problems/set-matrix-zeroes/
 * https://www.codingninjas.com/codestudio/problems/set-matrix-zeros_3846774
 *
 * Solution link :
 * https://www.youtube.com/watch?v=N0MgLvceX7M&t=1s
 * https://www.youtube.com/watch?v=M65xBewcqcI&list=PLgUwDviBIf0rPG3Ictpu74YWBQ1CaBkm2&index=8
 *
 * https://takeuforward.org/data-structure/set-matrix-zero/
 * */
public class SetMatrixToZero {
	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	//optimized approach without extra space
	// instead of using two extra matrices row and col,
	// we will use the 1st row and 1st column of the given matrix
	// to keep a track of the cells that need to be marked with 0.
	// as we are marking 0th cell 0, so we can skip traversing that
	private static void type3() {
		int[][] matrix = { 
				{ 1, 1, 2, 2 }, 
				{ 3, 4, 5, 2 }, 
				{ 1, 0, 0, 5 },
				{ 0, 3, 1, 5 }
				};
		// [0,0] can decide both 0th column and row
		boolean isFirstColumnZero = false;
		int n = matrix.length;
		int m = matrix[0].length;
		// calculation of the first column is done by isFirstColumnZero boolean value
		//so j is from 1
		for (int i = 0; i < n; i++) {
			if (matrix[i][0] == 0) isFirstColumnZero = true;
			for (int j = 1; j < m; j++)
				if (matrix[i][j] == 0)
					matrix[i][0] = matrix[0][j] = 0;
		}
		// going from the last
		// checking with 1st element of row and column to find if the cell is 0 or not
		for (int i = n - 1; i >= 0; i--) {
			for (int j = m - 1; j >= 1; j--)
				if (matrix[i][0] == 0 || matrix[0][j] == 0)
					matrix[i][j] = 0;
			if (isFirstColumnZero) matrix[i][0] = 0;
		}
		print2D(matrix);
	}
	
	//optimized approach with extra space of O(row)+O(column)
	// using a row matrix set and colum matrix set to mark which of the row and
	// columns will be zero
	private static void type2() {
		int[][] matrix = { 
				{ 1, 1, 2, 2 }, 
				{ 3, 4, 5, 2 }, 
				{ 1, 0, 0, 5 },
				{ 0, 3, 1, 5 }
				};
		int n = matrix.length, m = matrix[0].length;
		boolean[] rowSet = new boolean[n];
		boolean[] columnSet = new boolean[m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (matrix[i][j] == 0) {
					rowSet[i] = true;
					columnSet[j] = true;
				}
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (rowSet[i] || columnSet[j])
					matrix[i][j] = 0;
		print2D(matrix);
	}
	
	//brute force approach
	private static void type1() {
		int[][] matrix = { 
				{ 1, 1, 2, 2 }, 
				{ 3, 4, 5, 2 }, 
				{ 1, 0, 0, 5 },
				{ 0, 3, 1, 5 }
				};
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == 0) {
					setRowAndColumnToNegativeValue(i, j, matrix);
				}
			}
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == -1) {
					matrix[i][j] = 0;
				}
			}
		}
		print2D(matrix);
	}

	private static void setRowAndColumnToNegativeValue(int row, int column, int[][] matrix) {
		for (int i = 0; i < matrix[row].length; i++) {
			if (matrix[row][i] != 0) {
				matrix[row][i] = -1;
			}
		}
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][column] != 0) {
				matrix[i][column] = -1;
			}
		}
	}

}
