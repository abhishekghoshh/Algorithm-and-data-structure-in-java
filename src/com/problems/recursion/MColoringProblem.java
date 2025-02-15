package com.problems.recursion;

/*
 * Problem link :
 * https://www.codingninjas.com/codestudio/problems/981273
 * https://www.codingninjas.com/studio/problems/m-coloring-problem_981273
 * https://practice.geeksforgeeks.org/problems/m-coloring-problem-1587115620/1#
 * 
 * Solution link:
 * https://www.youtube.com/watch?v=wuVwUK25Rfc
 *
 * https://takeuforward.org/data-structure/m-coloring-problem/
 * */
public class MColoringProblem {

	public static void main(String[] args) {
		type1();
	}

	// using backtracking
	// for every point, we are checking if all colors are possible, or not
	// if one color is possible, then we are going to the next point
	private static void type1() {
		int m = 3;
		int n = 4;
		boolean[][] graph = {
				{false, true, true, true},
				{true, false, true, false},
				{true, true, false, true},
				{true, false, true, false}
		};
		boolean isPossible = graphColoring1(graph, m, n);
		System.out.println(isPossible);
	}

	public static boolean graphColoring1(boolean[][] graph, int m, int n) {
		int[] points = new int[n];
		return isColoringPossible(graph, 0, points, m, n);
	}

	private static boolean isColoringPossible(boolean[][] graph, int i, int[] points, int m, int n) {
		// it n means we have already colored 0 to n-1 nodes
		if (i == n) return true;
		// we are trying for m color one by one
		for (int color = 1; color <= m; color++) {
			// color available means we can allot ith color to point
			if (colorAvailable(graph, i, color, n, points)) {
				points[i] = color;
				// now we are checking possibilities for the next node
				if (isColoringPossible(graph, i + 1, points, m, n)) return true;
				// it comes to this section mean with ith coloring, the graph cannot be colored,
				// so we are removing the color
				points[i] = 0;
			}
		}
		return false;
	}

	private static boolean colorAvailable(boolean[][] graph, int point, int color, int n, int[] points) {
		for (int i = 0; i < n; i++)
			if (graph[i][point] && points[i] == color) return false;
		return true;
	}
}
