package com.problems.graph;

import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://www.geeksforgeeks.org/problems/implementing-floyd-warshall2042/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=YbY8cVwWAvw&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=42
 * 
 * https://takeuforward.org/data-structure/floyd-warshall-algorithm-g-42/
 */
public class FloydWarshall {

	// todo floyd warshall takes O(n^3), we could also use Dijkstra for all the nodes
	//  which will take v*2e*log(v), which will still lesser than this

	// The problem is to find the shortest distances between every pair of vertices
	// in a given edge-weighted directed graph. The graph is represented as an
	// adjacency matrix of size n*n. Matrix[i][j] denotes the weight of the edge
	// from i to j. If Matrix[i][j]=-1, it means there is no edge from i to j.
	// Do it in-place.

	// we have added those checks in i==j and j==k and others.
	// if there is no such check and if we can just use 3 loops,
	// we could establish that if the graph contains negative cycles or not.
	// after all, the iteration for any [i,i] if the matrix value is less than 0 then
	// the graph is having some negative cycle
	public static void main(String[] args) {
		type1();
		type2();
	}

	private static final int INF = Integer.MAX_VALUE;

	// without transforming the array
	// just like the previous type
	// but here, we will not change -1 to INF
	private static void type2() {
		int[][] matrix = {
				{0, 1, 43},
				{1, 0, 6},
				{-1, -1, 0}
		};
		floydWarshall2(matrix);
		print(matrix);
	}

	public static void floydWarshall2(int[][] matrix) {
		int n = matrix.length;
		// we will try to relax all the edges,
		// we will try to relax the edge from i to j,
		// and we will check i to k and k to j
		// where k will go from 0 to n-1
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				// if i == k then the equation will be [i][j] => [i][k] + [k][j]
				// [i][j] => [i][i] + [i][j], [i][i] is 0, so we can skip
				if (i == k) continue;
				for (int j = 0; j < n; j++) {
					// similarly we can skip for j==k and also i==j
					if (j == k || i == j) continue;
					// if one of the edge is -1, then we can skip, as there is no connection established as of now
					if (matrix[i][k] == -1 || matrix[k][j] == -1) continue;
					// at this point, both [i][k] and [k][j] has proper values
					// we can check if [i][j] is -1 or less than the [i][k] + [k][j]
					if (matrix[i][j] == -1 || matrix[i][j] > matrix[i][k] + matrix[k][j])
						matrix[i][j] = matrix[i][k] + matrix[k][j];
				}
			}
		}
	}
	// using transformation the array
	// first, we will replace the -1 to INF, then again at last we will replace INF with -1
	// simple intuition is for every index we will check this
	// [i][j] => [i][k] + [k][j]
	// we will check that if we can go i to j using k
	private static void type1() {
		int[][] matrix = {
				{0, 1, 43},
				{1, 0, 6},
				{-1, -1, 0}
		};
		floydWarshall1(matrix);
		print(matrix);
	}

	public static void floydWarshall1(int[][] matrix) {
		int n = matrix.length;

		// we will transform the matrix for our simplicity
		// if matrix[i][j] is -1 then there is no connectivity
		// we will replace it with infinity
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == -1) matrix[i][j] = INF;
				if (i == j) matrix[i][j] = 0;
			}
		}

		// we will try to relax all the edges,
		// we will try to relax the edge from i to j,
		// and we will check i to k and k to j
		// where k will go from 0 to n-1
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				// if i == k then the equation will be [i][j] => [i][k] + [k][j]
				// [i][j] => [i][i] + [i][j], [i][i] is 0, so we can skip
				if (i == k) continue;
				for (int j = 0; j < n; j++) {
					// similarly we can skip for j==k and also i==j
					if (j == k || i == j) continue;
					// if one of the edge is the infinity, then we can skip
					if (matrix[i][k] == INF || matrix[k][j] == INF) continue;
					// we will relax the edge
					matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
				}
			}
		}

		// replace the infinity with -1
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == INF) matrix[i][j] = -1;
			}
		}
	}

}
