package com.problems.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static com.util.PrintUtl.print;

/*
 * 
 * problem links :
 * https://leetcode.com/problems/01-matrix/
 * https://www.naukri.com/code360/problems/distance-of-nearest-cell-having-1-in-a-binary-matrix_1169913
 * https://www.geeksforgeeks.org/problems/distance-of-nearest-cell-having-1-1587115620/1
 * 
 * Solution link : 
 * https://www.youtube.com/watch?v=edXdVwkYHF8&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=14
 * 
 * https://takeuforward.org/graph/distance-of-nearest-cell-having-1/
 * */

public class NearestCellHavingZero {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// this is not exactly BFS
	// rather it is using a smart linear traversal
	// while taking account of the previous 0 cells
	// TODO we could also directly operate on the given matrix but it is not a good practice
	private static void type2() {
		int[][] mat = {
				{0, 1, 0, 0},
				{0, 0, 1, 1},
				{0, 0, 1, 1}
		};
		int m = mat.length;
		int n = mat[0].length;
		int[][] distance = new int[m][n];

		// first, we will set all the cell to MAX-1 not MAX
		for (int[] row : distance) Arrays.fill(row, Integer.MAX_VALUE - 1);

		// first, we will traverse from the start
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				// if the cell is 0, then we put the distance as 0
				if (mat[i][j] == 0) {
					distance[i][j] = 0;
					continue;
				}
				// if the cell is not 0, then we are trying to minimize the cell value will its top and left cells.
				// we will compare with (i-1,j) and (i,j-1)
				// as we are doing +1 with the previous cell value, that is why we have initialized with INT_MAX - 1
				// otherwise it would cause an integer overflow and make the distance negative
				if (i > 0) distance[i][j] = Math.min(distance[i][j], distance[i - 1][j] + 1);
				if (j > 0) distance[i][j] = Math.min(distance[i][j], distance[i][j - 1] + 1);
			}
		}
		// so we have already traversed from the start,
		// but if some of the point comes at the end then for that points the
		// neighbors are not minimized, so we will now traverse from the last
		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				// now again we will check with the previous two diagonal cells
				// as we are traversing from the back, so we will do it with (i+1,j) and (i,j+1)
				if (i != m - 1) distance[i][j] = Math.min(distance[i][j], distance[i + 1][j] + 1);
				if (j != n - 1) distance[i][j] = Math.min(distance[i][j], distance[i][j + 1] + 1);
			}
		}
		print(distance);
	}

	// TODO explain this in the interview
	// using BFS,
	// rather searching nearest 0 from all cells have 1, we will start from all the 0 cells
	// we will store all the nodes having 0, and then we will do a level wise traversal
	private static void type1() {
		int[][] mat = {
				{0, 1, 0, 0},
				{0, 0, 1, 1},
				{0, 0, 1, 1}
		};
		int m = mat.length;
		int n = mat[0].length;

		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[m][n];
		int[][] distance = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				// if cell is 0, we will add it to the queue as the starting point
				if (mat[i][j] == 0) {
					queue.offer(new int[] { i, j });
					visited[i][j] = true;
				}
			}
		}
		// for the 4 directions we have defined 4 delta coordinates
		int[] dx = { 1, -1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		// we will initiate the distance value with 0 and one each level we will increase it
		int d = 0;
		// currently queue only holds the points with cell value equal to 1.
		// for that point, the nearest cell having 1 distance is 0
		// so initialize a variable d with 0
		// are we are applying BFS, so we are evenly spreading out,
		// so after each iteration we will increment d value
		while (!queue.isEmpty()) {
			// first, it will check the size of the queue
			// we will apply BFS from those many points and add to the queue
			int size = queue.size();
			// we will poll all the current level elements from the queue and add for next level
			for (int s = 0; s < size; s++) {
				int[] point = queue.poll();
				distance[point[0]][point[1]] = d; // assigning the distance level wise
				for (int i = 0; i < 4; i++) {
					int x = point[0] + dx[i];
					int y = point[1] + dy[i];
					if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
						visited[x][y] = true;
						queue.offer(new int[] { x, y });
					}
				}
			}
			d++;
		}
		print(distance);
	}
}
