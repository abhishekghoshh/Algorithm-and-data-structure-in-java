package com.problems.graph;
/*
 * Problem link :
 * https://leetcode.com/problems/number-of-enclaves/
 * https://practice.geeksforgeeks.org/problems/number-of-enclaves/1
 * https://www.codingninjas.com/studio/problems/matrix-traps_8365440
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=rxKcepXQgU4&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=15
 * 
 * https://takeuforward.org/graph/number-of-enclaves/
 */

public class NumberOfEnclave {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// same as the previous type just here we will change the input data,
	// changing the input is not a good practice though
	private static void type2() {
		int[][] grid = {
				{0, 0, 0, 0},
				{1, 0, 1, 0},
				{0, 1, 1, 0},
				{0, 0, 0, 0}
		};
		int m = grid.length, n = grid[0].length;

		// visit the boundaries and change the cell value with 0

		// first column and last column
		for (int i = 0; i < m; i++) {
			// we will start for all the cells but in the dfs call it will only go deep if the cell is 1
			dfs(grid, i, 0);
			dfs(grid, i, n - 1);
		}
		// first row and last row
		for (int j = 0; j < n; j++) {
			// we will start for all the cells but in the dfs call it will only go deep if the cell is 1
			dfs(grid, 0, j);
			dfs(grid, m - 1, j);
		}

		// essentially, the inner regions have the cell value with 1,
		// so we just have to count how many cells have the 1 value
		int count = 0;
		for (int[] row : grid)
			for (int cell : row)
				count += cell;

		System.out.println(count);
	}

	// once any cell is visited, we are changing the cell to 1
	private static void dfs(int[][] grid, int i, int j) {
		// if the cell is not visited and the cell is having 1, then we can start dfs again
		if (isInBounds(grid, i, j) && grid[i][j] == 1) {
			// mark that is visited by making it 0 from 1
			grid[i][j] = 0;
			dfs(grid, i + 1, j);
			dfs(grid, i - 1, j);
			dfs(grid, i, j + 1);
			dfs(grid, i, j - 1);
		}
	}

	private static boolean isNotInBoundary(int i, int j, int[][] grid) {
		return i < 0 || i >= grid.length || j < 0 || j >= grid[0].length;
	}

	// one intuition we have found that if in boundary, then we can escape,
	// so boundary can never be our answer.
	// any cell touched with boundary cell with value 1
	// cannot be the answer as well without changing the input data
	// TODO essentially we have to find all the inner regions which is not touched by any
	//  outer cell with 1 value, so first we will mark all the cells of the outer region with value 1
	//  and any cell touched by that cell
	private static void type1() {
		int[][] grid = {
				{0, 0, 0, 0},
				{1, 0, 1, 0},
				{0, 1, 1, 0},
				{0, 0, 0, 0}
		};
		int m = grid.length;
		int n = grid[0].length;

		boolean[][] visited = new boolean[m][n];

		// visit the boundaries and mark the cells

		// first column and last column
		for (int i = 0; i < m; i++) {
			// we will start for all the cells but in the dfs call it will only go deep if the cell is 1
			dfs(i, 0, visited, grid);
			dfs(i, n - 1, visited, grid);
		}
		// first row and last row
		for (int j = 0; j < n; j++) {
			// we will start for all the cells but in the dfs call it will only go deep if the cell is 1
			dfs(0, j, visited, grid);
			dfs(m - 1, j, visited, grid);
		}

		// if the cell is not visited and having one, then we cannot go outside from that cell
		int count = 0;
		// we will only check for the cell which are not in boundary
		for (int i = 1; i < m - 1; i++)
			for (int j = 1; j < n - 1; j++)
				// not visited and having cell
				if (!visited[i][j] && grid[i][j] == 1)
					count++;

		System.out.println(count);
	}

	private static void dfs(int i, int j, boolean[][] visited, int[][] grid) {
		// if the cell is not visited and the cell is having 1, then we can start bfs again
		if (isInBounds(grid, i, j) && grid[i][j] == 1 && !visited[i][j]) {
			visited[i][j] = true;
			dfs(i + 1, j, visited, grid);
			dfs(i - 1, j, visited, grid);
			dfs(i, j + 1, visited, grid);
			dfs(i, j - 1, visited, grid);
		}
	}

	private static boolean isInBounds(int[][] grid, int i, int j) {
		return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
	}
}
