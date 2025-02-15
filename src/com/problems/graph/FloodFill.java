package com.problems.graph;

import java.util.LinkedList;
import java.util.Queue;

import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://leetcode.com/problems/flood-fill/
 * https://www.naukri.com/code360/problems/flood-fill-_1082141
 * https://www.geeksforgeeks.org/problems/flood-fill-algorithm1856/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=C-2_uSRli8o&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=9
 * 
 * https://takeuforward.org/graph/flood-fill-algorithm-graphs/
 */
public class FloodFill {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// using dfs
	// recursively traverse all the cell and change all the cell
	// which has the same color as the source cell previous color
	private static void type2() {
		int[][] image = {
				{1, 1, 1},
				{1, 1, 0},
				{1, 0, 1}
		};
		int sr = 1, sc = 1, color = 2;
		image = floodFill2(image, sr, sc, color);
		print(image);
	}

	private static int[][] floodFill2(int[][] image, int sr, int sc, int color) {
		// if the given color is same as the source node color then we do not need to change
		if (color == image[sr][sc]) return image;
		// we will start the dfs for this node
		traverse(image, sr, sc, image[sr][sc], color);
		return image;
	}

	public static void traverse(int[][] image, int x, int y, int srcColor, int color) {
		// checking that the new coordinates are in bounds and that coordinate has the source color
		// then we will add that in the queue
		if (isInBounds(x, y, image) && image[x][y] == srcColor) {
			// changing the cell color
			image[x][y] = color;
			traverse(image, x - 1, y, srcColor, color); // top cell
			traverse(image, x, y + 1, srcColor, color); // right cell
			traverse(image, x + 1, y, srcColor, color); // down cell
			traverse(image, x, y - 1, srcColor, color); // left cell
		}
	}

	// using bfs,
	// but here we do not need to a level wise traversal,
	// we store the indices to one queue and poll one by one
	// we could simply use any data structure
	private static void type1() {
		int[][] image = {
				{1, 1, 1},
				{1, 1, 0},
				{1, 0, 1}
		};
		int sr = 1, sc = 1, color = 2;
		image = floodFill1(image, sr, sc, color);
		print(image);
	}


	// We will use BFS
	public static int[][] floodFill1(int[][] image, int sr, int sc, int color) {
		if (color == image[sr][sc]) return image;
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		// color of the source node
		int srcColor = image[sr][sc];
		// we will use the queue for bfs traversal
		Queue<int[]> queue = new LinkedList<>();
		// we will add the source position into the queue
		queue.offer(new int[]{sr, sc});
		image[sr][sc] = color;

		// we will do this until the queue is not empty
		while (!queue.isEmpty()) {
			int[] point = queue.poll();
			for (int i = 0; i < 4; i++) {
				int x = point[0] + dx[i];
				int y = point[1] + dy[i];
				// checking that the new coordinates are in bounds and that coordinate has the source color
				// then we will add that in the queue
				if (isInBounds(x, y, image) && image[x][y] == srcColor) {
					queue.offer(new int[]{x, y});
					image[x][y] = color;
				}
			}
		}
		return image;
	}

	private static boolean isInBounds(int x, int y, int[][] image) {
		return x >= 0 && y >= 0
				&& x < image.length
				&& y < image[0].length;
	}


}
