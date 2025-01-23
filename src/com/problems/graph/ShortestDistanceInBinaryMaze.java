package com.problems.graph;

import java.util.*;

/*
 * Problem link :
 * https://leetcode.com/problems/shortest-path-in-binary-matrix/
 * https://practice.geeksforgeeks.org/problems/shortest-path-in-a-binary-maze-1655453161/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=U5Mw4eyUmw4&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=36
 * 
 * https://takeuforward.org/data-structure/g-36-shortest-distance-in-a-binary-maze/
 */
public class ShortestDistanceInBinaryMaze {


	// Given an n x n binary matrix grid, return the length of the shortest clear
	// path in the matrix. If there is no clear path, return -1.
	// A clear path in a binary matrix is a path from
	// the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1))
	// such that: All the visited cells of the path are 0.
	// All the adjacent cells of the path are 8-directionally connected
	// (i.e., they are different, and they share an edge or a corner).
	// The length of a clear path is the number of the visited cells in this path.
	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	static int[][] dirs = {
			{-1, -1},
			{-1, 0},
			{-1, 1},
			{0, -1},
			{0, 1},
			{1, -1},
			{1, 0},
			{1, 1}
	};
	// here we are not using the direction matrix also
	private static void type3() {
		int[][] grid = {
				{0, 0, 0},
				{1, 1, 0},
				{1, 1, 0}
		};
		int ans = shortestPathBinaryMatrix3(grid);
		System.out.println(ans);
	}

	public static int shortestPathBinaryMatrix3(int[][] grid) {
		int n = grid.length;
		if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;
		// we will do a bfs and traverse level by level
		Deque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[]{0, 0});
		grid[0][0] = 1;
		int dis = 0;
		while (!queue.isEmpty()) {
			dis++;
			int size = queue.size();
			for (int m = 0; m < size; m++) {
				int[] pair = queue.poll();
				int r = pair[0], c = pair[1];
				// once we reach the destination we will stop our destination,
				// we surely know it is the closest path
				if (r == n - 1 && c == n - 1) return dis;
				// we will go to all 8 direction using a loop
				for (int x = r - 1; x <= r + 1; ++x) {
					for (int y = c - 1; y <= c + 1; ++y) {
						// as we are checking level wise, so we don't have to check dist + 1 < grid[x][y]
						// the first index to reach here will be the shortest
						if (isInBounds(x, y, n) && grid[x][y] == 0) {
							queue.offer(new int[]{x, y});
							grid[x][y] = 1;
						}
					}
				}
			}
		}
		return -1;
	}

	// without distance array, same as previous but here we will store the distances in the grid directly
	private static void type2() {
		int[][] grid = {
				{0, 0, 0},
				{1, 1, 0},
				{1, 1, 0}
		};
		int ans = shortestPathBinaryMatrix2(grid);
		System.out.println(ans);

	}

	public static int shortestPathBinaryMatrix2(int[][] grid) {
		int n = grid.length;
		// if the source and the destination are 1 then we can not go
		if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;
		Deque<int[]> queue = new LinkedList<>();
		// we will start from 0,0, we will add it to the queue
		queue.offer(new int[]{0, 0});
		grid[0][0] = 1;
		// we will poll the nodes from queue and take the distance
		while (!queue.isEmpty()) {
			int[] pos = queue.poll();
			int r = pos[0];
			int c = pos[1];
			int dist = grid[r][c];
			// if we go to the n-1,n-1 then we will directly return the answer
			if (r == n - 1 && c == n - 1) return dist;
			// now we will go to all 8 directions
			for (int i = 0; i < 8; i++) {
				int x = r + dirs[i][0];
				int y = c + dirs[i][1];
				// grid[x][y] == 0 means there is a path but not discovered yet
				// grid[x][y] != 1 means there is a path which is discovered
				// dist + 1 < grid[x][y] means there is a better path
				if (isInBounds(x, y, n) && grid[x][y] != 1 && (grid[x][y] == 0 || dist + 1 < grid[x][y])) {
					grid[x][y] = dist + 1;
					queue.offer(new int[]{x, y});
				}
			}
		}
		return -1;
	}

	// with distance array
	// the intuition is, we will start from 0,0 and distance 1
	// we will use Dijkstra algorithm, but here we are using Queue not priorityQueue
	// because here we can go level wise, we are starting from 0,0, and we are trying to visit all its neighbours
	// the distance is increasing eventually with every layer, so we do not need the priority queue
	private static void type1() {
		int[][] grid = {
				{0, 0, 0},
				{1, 1, 0},
				{1, 1, 0}
		};
		int ans = shortestPathBinaryMatrix1(grid);
		System.out.println(ans);
	}


	public static int shortestPathBinaryMatrix1(int[][] grid) {
		int n = grid.length;
		// if the source and the destination are 1 then we can not go
		if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;

		// we will take e n*n distance array and will initialize all the nodes with INF
		int[][] dis = new int[n][n];
		for (int[] row : dis) Arrays.fill(row, Integer.MAX_VALUE);
		// we will start from 0,0, we will add it to the queue
		dis[0][0] = 1;
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[]{0, 0, 1});
		// we will poll the nodes from queue and take the distance
		while (!queue.isEmpty()) {
			int[] pair = queue.poll();
			int prevDistance = pair[2];
			// we will go all 8 directions of a starting node
			for (int i = 0; i < 8; i++) {
				int x = pair[0] + dirs[i][0];
				int y = pair[1] + dirs[i][1];
				// then we will check if the distance can be minimized
				if (isInBounds(x, y, n) && grid[x][y] == 0 && prevDistance + 1 < dis[x][y]) {
					dis[x][y] = prevDistance + 1;
					queue.offer(new int[]{x, y, prevDistance + 1});
				}
			}
		}
		// if the distance is still MAX_VALUE then the node can not be reached
		return (dis[n - 1][n - 1] != Integer.MAX_VALUE) ? dis[n - 1][n - 1] : -1;
	}

	private static boolean isInBounds(int x, int y, int n) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}

}
