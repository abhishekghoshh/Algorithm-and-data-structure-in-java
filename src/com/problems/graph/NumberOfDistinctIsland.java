package com.problems.graph;

import java.util.HashSet;
import java.util.Set;

/*
 * Problem link :
 * https://practice.geeksforgeeks.org/problems/number-of-distinct-islands/1
 * https://leetcode.com/problems/number-of-distinct-islands-ii/
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=7zmgQSJghpo&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=16
 * 
 */

public class NumberOfDistinctIsland {

	public static void main(String[] args) {
		type1();
	}

	// we don't have anything to store the shape,
	// so we will store the points only,
	// but we have to store the distinct shapes,
	// so we will subtract every point to its base point.
	// And thus we will be starting the shape by their absolute point address
	private static void type1() {
		int[][] grid = {
				{1, 1, 0, 0, 0},
				{1, 1, 0, 0, 0},
				{0, 0, 0, 1, 1},
				{0, 0, 0, 1, 1}
		};

		int row = grid.length;
		int column = grid[0].length;
		Set<String> set = new HashSet<>();
		boolean[][] visited = new boolean[row][column];

		for (int r = 0; r < row; r++) {
			for (int c = 0; c < column; c++) {
				// we will start the dfs for every unvisited cell having value of 1
				// and along the way we will store the points of the shape in a string builder
				if (!visited[r][c] && grid[r][c] == 1) {
					StringBuilder points = new StringBuilder();
					dfs(r, c, r, c, visited, grid, points);
					set.add(points.toString());
				}
			}
		}
		System.out.println(set);
		System.out.println(set.size());
	}

	private static void dfs(int r, int c, int rStart, int cStart,
							boolean[][] visited, int[][] grid, StringBuilder points) {
		if (isInBounds(r, c, grid) && !visited[r][c] && grid[r][c] == 1) {
			visited[r][c] = true;
			// absolute point address
			int dr = r - rStart;
			int dc = c - cStart;
			points.append("[").append(dr).append(",").append(dc).append("]");

			// spreading the shape
			dfs(r + 1, c, rStart, cStart, visited, grid, points);
			dfs(r - 1, c, rStart, cStart, visited, grid, points);
			dfs(r, c + 1, rStart, cStart, visited, grid, points);
			dfs(r, c - 1, rStart, cStart, visited, grid, points);
		}
	}

	private static boolean isInBounds(int r, int c, int[][] grid) {
		return r >= 0 && c >= 0 && r < grid.length && c < grid[0].length;
	}

}
