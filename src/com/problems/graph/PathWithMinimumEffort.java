package com.problems.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * Problem link :
 * https://leetcode.com/problems/path-with-minimum-effort/
 * https://practice.geeksforgeeks.org/problems/path-with-minimum-effort/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=0ytpZyiZFhA&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=39
 * 
 * https://takeuforward.org/data-structure/g-37-path-with-minimum-effort/
 * 
 */
public class PathWithMinimumEffort {

	// You are a hiker preparing for an upcoming hike. You are given heights, a 2D
	// array of size rows x columns, where heights[row][col] represents the height of cell (row, col).
	// You are situated in the top-left cell, (0, 0),
	// and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e.,0-indexed).
	// You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

	// A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

	// Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
	public static void main(String[] args) {
		type1();
	}


	// We will use a priority queue and apply Dijkstra algorithm here
	// on every iteration we will store the new Min Effort into the queue for a particular node
	// once we reach the final node we will return the  answer as we know it will have the minimum effort value
	private static void type1() {
		int[][] heights = {
				{1, 2, 2},
				{3, 8, 2},
				{5, 3, 5}
		};
		int ans = minimumEffortPath1(heights);
		System.out.println(ans);

	}

	public static int minimumEffortPath1(int[][] heights) {
		int row = heights.length;
		int column = heights[0].length;

		// Create a distance matrix with initially all the cells marked as
		// unvisited and the dist for source cell (0,0) as 0.
		int[][] dist = new int[row][column];
		for (int[] rows : dist) Arrays.fill(rows, Integer.MAX_VALUE);
		dist[0][0] = 0;

		// The following delta rows and delta columns array are created such that
		// each index represents each adjacent node that a cell may have
		// in a direction.
		int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		// p[0] is for distance
		PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(p -> p[0]));
		minHeap.offer(new int[]{0, 0, 0});

		// Iterate through the matrix by popping the elements out of the queue
		// and pushing whenever a shorter distance to a cell is found.
		while (!minHeap.isEmpty()) {
			int[] pair = minHeap.poll();
			int diff = pair[0];
			int x = pair[1];
			int y = pair[2];
			// if we reach the destination then we will return the answer
			if (x == row - 1 && y == column - 1) return diff;
			// we will traverse all its 4 neighbours
			for (int i = 0; i < 4; i++) {
				int r = x + dir[i][0];
				int c = y + dir[i][1];
				// we will check if it is in bounds
				if (isInBounds(r, c, row, column)) {
					// Effort can be calculated as the max value of differences
					// between the heights of the node and its adjacent nodes.
					int absDiff = Math.abs(heights[x][y] - heights[r][c]);
					// we will take the max difference on that path
					int newDiff = Math.max(absDiff, diff);
					// If the new diff is less than the prev diff we update as we need the min effort
					if (newDiff < dist[r][c]) {
						dist[r][c] = newDiff;
						minHeap.add(new int[]{newDiff, r, c});
					}
				}
			}
		}
		return 0;
	}

	private static boolean isInBounds(int r, int c, int row, int column) {
		return r >= 0 && r < row && c >= 0 && c < column;
	}

}
