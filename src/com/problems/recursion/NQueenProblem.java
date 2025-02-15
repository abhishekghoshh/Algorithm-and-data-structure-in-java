package com.problems.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Problem link :
 * https://leetcode.com/problems/n-queens/description/
 * https://neetcode.io/problems/n-queens
 * https://www.naukri.com/code360/problems/759332
 * https://www.naukri.com/code360/problems/n-queens_696453
 *
 * Solution link:
 * https://www.youtube.com/watch?v=i05Ju7AftcM
 * https://takeuforward.org/data-structure/n-queen-problem-return-all-distinct-solutions-to-the-n-queens-puzzle/
 *
 * https://www.youtube.com/watch?v=Ph95IHmRp5M
 * https://neetcode.io/solutions/n-queens
 * */
public class NQueenProblem {


	// todo this is a nice problem
	public static void main(String[] args) {
		type1();
		type2();
	}


	private static final char QUEEN = 'Q';
	private static final char SEPARATOR = '.';

	// todo It is the same as the previous type.
	//  However, if we see closely the time to check if the queen placement is safe or not is taking O(3n),
	//  so if we can check in O(1) time that the left row and both left upper and  lower diagonal has any queen or not,
	//  then we the code will be optimized
	//  we can check row very easily by using one array of size n
	//  to check left upper diagonal
	//  let's see some cells in a diagonal (0,1)(2,3)(3,4) or (0,2)(1,3)(2,4) or (1,0)(2,1)(3,2)
	//  if we see the difference of the cell, it will go from -n to +n
	//  so if we take a map or an array of 2n+1 and store r-c values then we can check in O(1) time
	//  arr[r-c] will be the array but the r-c can be negative so will add it to (n-1)
	//  for left lower diagonal, if we check the cell (5,0)(4,1)(3,2) or (5,2)(4,3)(3,4)
	//  if the see closely the for one diagonal r+c is unique which can vary from 0 to 2n,
	//  so we will take an array of 2n+1
	private static void type2() {
		int n = 4;
		List<List<String>> boards = solveNQueens2(n);
		print(boards);
	}

	public static List<List<String>> solveNQueens2(int n) {
		List<List<String>> boards = new ArrayList<>();
		char[][] board = new char[n][n];
		for (char[] row : board) Arrays.fill(row, SEPARATOR);
		// these arrays will be used to validate if there is any queen present in row
		// to check the intuition behind it check the striver solution
		// this will act as cache
		int[] leftRow = new int[n];
		int[] lowerDiagonal = new int[2 * n - 1];
		int[] upperDiagonal = new int[2 * n - 1];
		// we will start with the 1st column
		placeQueen2(0, board, boards, leftRow, lowerDiagonal, upperDiagonal);
		return boards;
	}

	public static void placeQueen2(int c, char[][] board, List<List<String>> boards,
								   int[] leftRow, int[] lowerDiagonal, int[] upperDiagonal) {
		int n = board.length;
		if (c == n) {
			boards.add(construct(board));
			return;
		}
		// like the previous type, we will try to place it in a row
		for (int r = 0; r < n; r++) {
			if (isSafeToPlace2(r, c, n, leftRow, lowerDiagonal, upperDiagonal)) {
				board[r][c] = QUEEN;
				// we will mark that this row, left lower diagonal and left upper diagonal is visited
				leftRow[r] = 1;
				upperDiagonal[(n - 1) + c - r] = 1;
				lowerDiagonal[r + c] = 1;
				// we will go to the next column
				placeQueen2(c + 1, board, boards, leftRow, lowerDiagonal, upperDiagonal);
				board[r][c] = SEPARATOR;
				// now we will unset the visited cells
				leftRow[r] = 0;
				upperDiagonal[(n - 1) + c - r] = 0;
				lowerDiagonal[r + c] = 0;
			}
		}
	}

	// O(1) time to check if there is any queen in the same row or left upper diagonal and left lower diagonal
	private static boolean isSafeToPlace2(int r, int c, int n,
										  int[] leftRow, int[] lowerDiagonal, int[] upperDiagonal) {
		return (leftRow[r] == 0)
				&& (lowerDiagonal[r + c] == 0)
				&& (upperDiagonal[n - 1 + c - r] == 0);
	}

	// todo normal solution
	//  using recursion and backtracking.
	//  we will go column wise, and try to place the queen in every row
	//  for a particular column we will try to place a queen in every row, we can also do the otherwise,
	//  for a particular row we will try to place the queen in column.
	//  once a queen is placed into a column we will go to the next column, also we will check is it safe to place or not
	//  if we go column wise we don't need to check right side of the current column
	private static void type1() {
		int n = 4;
		List<List<String>> boards = solveNQueens1(n);
		print(boards);
	}

	public static List<List<String>> solveNQueens1(int n) {
		List<List<String>> boards = new ArrayList<>();
		char[][] board = new char[n][n];
		// we will fill the entire board with separator character
		for (char[] row : board) Arrays.fill(row, SEPARATOR);
		// we will start from 0th column
		placeQueen1(0, board, boards);
		return boards;
	}

	private static void placeQueen1(int c, char[][] board, List<List<String>> boards) {
		int n = board.length;
		// if c == n that means all queens have been placed, so we can now construct the board and return
		if (c == n) {
			boards.add(construct(board));
			return;
		}
		// in each column we will try to place it in every row
		for (int r = 0; r < n; r++) {
			// before placing any queen, we are checking that on that row and diagonally
			// backwards if there is any other queen placed or not
			if (isSafeToPlace(board, r, c)) {
				board[r][c] = QUEEN;
				placeQueen1(c + 1, board, boards);
				board[r][c] = SEPARATOR;
			}
		}
	}

	private static boolean isSafeToPlace(char[][] board, int row, int column) {
		int n = board.length;
		// checking if there is any queen placed in this row previously or not
		for (int i = 0; i < column; i++)
			if (board[row][i] == QUEEN) return false;
		// checking left diagonally upwards
		for (int r = row, c = column; r > -1 && c > -1; r--, c--) {
			if (board[r][c] == QUEEN) return false;
		}
		// checking left diagonally downwards
		for (int r = row, c = column; r < n && c > -1; r++, c--) {
			if (board[r][c] == QUEEN) return false;
		}
		// todo we do not need to check the right because there are no cells with queen currently
		return true;
	}

	private static List<String> construct(char[][] board) {
		List<String> list = new ArrayList<>();
		for (char[] row : board) list.add(new String(row));
		return list;
	}

	private static void print(List<List<String>> answer) {
		for (List<String> board : answer) {
			for (String row : board) System.out.println(row);
			System.out.println();
		}
		System.out.println("-------------------------");
	}

}
