package com.problems.recursion;

/*
 * Problem link :
 * https://leetcode.com/problems/sudoku-solver/
 * https://codingninjas.com/codestudio/problems/758961
 * https://www.codingninjas.com/studio/problems/sudoku-solver_8416969
 *
 * Solution link:
 * https://www.youtube.com/watch?v=FWAIf_EVUKE
 *
 * https://takeuforward.org/data-structure/sudoku-solver/
 * */
public class SudokuSolver {

	private static final int GRIDS = 9;

	// rematch the solution
	public static void main(String[] args) {
		type1();
		type2();
	}

	private static final boolean[][] rowSet = new boolean[GRIDS][GRIDS + 1];
	private static final boolean[][] colSet = new boolean[GRIDS][GRIDS + 1];
	private static final boolean[][] boxSet = new boolean[GRIDS][GRIDS + 1];

	// this approach is efficient, but it is taking some constant memory
	// to find if the number existed in that specific row or column or that box
	private static void type2() {
		char[][] board = {
				{'9', '5', '7', '.', '1', '3', '.', '8', '4'},
				{'4', '8', '3', '.', '5', '7', '1', '.', '6'},
				{'.', '1', '2', '.', '4', '9', '5', '3', '7'},
				{'1', '7', '.', '3', '.', '4', '9', '.', '2'},
				{'5', '.', '4', '9', '7', '.', '3', '6', '.'},
				{'3', '.', '9', '5', '.', '8', '7', '.', '1'},
				{'8', '4', '5', '7', '9', '.', '6', '1', '3'},
				{'.', '9', '1', '.', '3', '6', '.', '7', '5'},
				{'7', '.', '6', '1', '8', '5', '4', '.', '9'}
		};
		printBoard(board);
		initialize(board);
		boolean isPossible = solveSudoku2(board);
		System.out.println(isPossible);
		printBoard(board);
	}

	private static boolean solveSudoku2(char[][] board) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				// if it already placed then we will skip it
				if (board[row][col] != '.') continue;
				for (char num = '1'; num <= '9'; num++) {
					// if the placement is not valid, then we will skip and go to the next num
					if (!isValid2(row, col, num)) continue;
					board[row][col] = num;
					insert(num, row, col);
					if (solveSudoku2(board)) return true;
					remove(num, row, col);
					board[row][col] = '.';
				}
				return false;
			}
		}
		return true;
	}

	private static void initialize(char[][] board) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (board[row][col] == '.') continue;
				int num = board[row][col] - '0';
				rowSet[row][num] = true;
				colSet[col][num] = true;
				int boxNum = boxNumber(row, col);
				boxSet[boxNum][num] = true;
			}
		}
	}

	private static int boxNumber(int row, int col) {
		return 3 * (row / 3) + col / 3;
	}

	public static boolean isValid2(int row, int col, char ch) {
		int num = ch - '0';
		return !rowSet[row][num]
				&& !colSet[col][num]
				&& !boxSet[boxNumber(row, col)][num];
	}

	public static void insert(char ch, int row, int col) {
		int num = ch - '0';
		rowSet[row][num] = true;
		colSet[col][num] = true;
		boxSet[boxNumber(row, col)][num] = true;
	}

	public static void remove(char ch, int row, int col) {
		int num = ch - '0';
		rowSet[row][num] = false;
		colSet[col][num] = false;
		boxSet[boxNumber(row, col)][num] = false;
	}

	// this is a very simple naive approach using recursion and backtracking
	private static void type1() {
		char[][] board = {
				{'9', '5', '7', '.', '1', '3', '.', '8', '4'},
				{'4', '8', '3', '.', '5', '7', '1', '.', '6'},
				{'.', '1', '2', '.', '4', '9', '5', '3', '7'},
				{'1', '7', '.', '3', '.', '4', '9', '.', '2'},
				{'5', '.', '4', '9', '7', '.', '3', '6', '.'},
				{'3', '.', '9', '5', '.', '8', '7', '.', '1'},
				{'8', '4', '5', '7', '9', '.', '6', '1', '3'},
				{'.', '9', '1', '.', '3', '6', '.', '7', '5'},
				{'7', '.', '6', '1', '8', '5', '4', '.', '9'}
		};
		printBoard(board);
		boolean isPossible = solveSudoku1(board);
		System.out.println(isPossible);
		printBoard(board);
	}

	private static boolean solveSudoku1(char[][] board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				// if it already placed then we will skip it
				if (board[i][j] != '.') continue;
				for (char c = '1'; c <= '9'; c++) {
					// if the placement is not valid then we will skip and go to the next num
					if (!isValid(board, i, j, c)) continue;
					board[i][j] = c;
					if (solveSudoku1(board)) return true;
					board[i][j] = '.';
				}
				return false;
			}
		}
		return true;
	}

	private static boolean isValid(char[][] board, int row, int col, char num) {
		for (int i = 0; i < 9; i++) {
			// check the row wise
			if (board[i][col] == num) return false;
			// check the column wise
			if (board[row][i] == num) return false;
			// check in the box
			if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == num)
				return false;
		}
		return true;
	}

	private static void printBoard(char[][] board) {
		System.out.println("Board starting -------------------");
		for (int row = 0; row < GRIDS; row++) {
			if (row % 3 == 0 && row != 0) System.out.println("-------------------");
			for (int col = 0; col < GRIDS; col++) {
				if (col % 3 == 0 && col != 0) System.out.print("|");
				System.out.print(board[row][col] + " ");
			}
			System.out.println();
		}
		System.out.println("Board ending -------------------");
	}
}
