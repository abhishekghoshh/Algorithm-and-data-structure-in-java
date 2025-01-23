package com.problems.graph;
/*
 * Problem link :
 * https://leetcode.com/problems/surrounded-regions/
 * https://practice.geeksforgeeks.org/problems/replace-os-with-xs0052/1
 * https://www.naukri.com/code360/problems/replace-%E2%80%98o%E2%80%99-with-%E2%80%98x%E2%80%99_630517
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=BtdgAys4yMk&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=14
 * 
 * https://takeuforward.org/graph/surrounded-regions-replace-os-with-xs/
 * 
 */

import static com.util.PrintUtl.print;

public class SurroundedRegions {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// for the previous approach, we have used the visited array
	// but, we can easily mark with the board that it is visited
	private static void type2() {
		char[][] board = {
				{'X', 'X', 'X', 'X'},
				{'X', 'O', 'O', 'X'},
				{'X', 'X', 'O', 'X'},
				{'X', 'O', 'X', 'X'}
		};
		int m = board.length;
		int n = board[0].length;
		// first, we will mark all the regions which has a boundary
		// it will visit all the region from boundary
		for (int i = 0; i < m; i++) {
			dfs(i, 0, board);
			dfs(i, n - 1, board);
		}
		for (int j = 0; j < n; j++) {
			dfs(0, j, board);
			dfs(m - 1, j, board);
		}
		// visit from all the cells, if it still 'O' then replace that with 'X'
		// that means it is in the inner region and not touched by any outer region 'O'.
		// if the cell is 'V,' that means it is in the outer region and the 'O' is changed with 'V'
		// so we have to change it back to its original 'O'
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				// if the cell is O means it is not touched by boundary,
				// V means it is touched but we need to revert it back to its original value
				if (board[i][j] == 'O') board[i][j] = 'X';
				else if (board[i][j] == 'V') board[i][j] = 'O';
			}
		}
		print(board);
	}

	private static void dfs(int i, int j, char[][] board) {
		if (isNotInBoundary(i, j, board) || board[i][j] != 'O') return;
		// we will make the cell value as V to make the cell visited
		board[i][j] = 'V';
		// we will visit all 4 sides
		dfs(i + 1, j, board);
		dfs(i - 1, j, board);
		dfs(i, j + 1, board);
		dfs(i, j - 1, board);
	}

	private static boolean isNotInBoundary(int i, int j, char[][] board) {
		return i < 0 || i >= board.length || j < 0 || j >= board[0].length;
	}

	// Using DFS
	// the intuition is
	// if the 'O' is in boundary then it can never be surrounded,
	// so first we will mark all the regions which has any boundary cell
	private static void type1() {
		char[][] board = {
				{'X', 'X', 'X', 'X'},
				{'X', 'O', 'O', 'X'},
				{'X', 'X', 'O', 'X'},
				{'X', 'O', 'X', 'X'}
		};
		int m = board.length;
		int n = board[0].length;
		boolean[][] visited = new boolean[m][n];
		// first, we will mark all the regions which has a boundary
		// it will visit all the region from boundary
		for (int i = 0; i < m; i++) {
			dfs(i, 0, visited, board);
			dfs(i, n - 1, visited, board);
		}
		for (int j = 0; j < n; j++) {
			dfs(0, j, visited, board);
			dfs(m - 1, j, visited, board);
		}

		// visit from all the cells which is not in boundary and cell is not yet visited
		// we will replace that with X
		for (int r = 1; r < m - 1; r++) {
			for (int c = 1; c < n - 1; c++) {
				if (board[r][c] == 'O' && !visited[r][c])
					board[r][c] = 'X';
			}
		}
		print(board);
	}

	private static void dfs(int i, int j, boolean[][] visited, char[][] board) {
		if (isInBoundary(i, j, board) && board[i][j] == 'O' && !visited[i][j]) {
			visited[i][j] = true;
			// we will visit 4 sides
			dfs(i + 1, j, visited, board);
			dfs(i - 1, j, visited, board);
			dfs(i, j + 1, visited, board);
			dfs(i, j - 1, visited, board);
		}
	}

	private static boolean isInBoundary(int i, int j, char[][] board) {
		return i >= 0 && i < board.length && j >= 0 && j < board[0].length;
	}
}
