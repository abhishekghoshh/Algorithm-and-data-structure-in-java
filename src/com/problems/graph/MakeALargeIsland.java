package com.problems.graph;

import java.util.HashSet;
import java.util.Set;

/*
 * Problem link :
 * https://leetcode.com/problems/making-a-large-island/
 * https://practice.geeksforgeeks.org/problems/maximum-connected-group/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=lgiz0Oup6gM&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=52
 * 
 * https://takeuforward.org/data-structure/making-a-large-island-dsu-g-52/
 */
public class MakeALargeIsland {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// todo exactly like the previous one but here we have used an array as set and stack for clearing the set array
	// we will use union by size this time in previous type we have used a set,
	// but we know the size, so we can use a visited array and one stack to remove the visited position.
	// we will not use a set here
	private static void type2() {
		int[][] grid = {
				{1, 1, 0, 1, 1, 0},
				{1, 1, 0, 1, 1, 0},
				{1, 1, 0, 1, 1, 0},
				{0, 0, 1, 0, 0, 0},
				{0, 0, 1, 1, 1, 0},
				{0, 0, 1, 1, 1, 0},
		};
		int ans = largestIsland2(grid);
		System.out.println(ans);
	}

	public static int largestIsland2(int[][] grid) {
		int n = grid.length;
		int N = n * n;
		int[] parent = new int[N];
		int[] size = new int[N];
		for (int i = 0; i < N; i++) {
			size[i] = 1;
			parent[i] = i;
		}
		int[][] dirs = {
				{0, 1},
				{0, -1},
				{1, 0},
				{-1, 0}
		};
		// by this union find we have gathered the size of the individual components
		int noOfOnes = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 0) continue;
				noOfOnes++;
				int nodeId = i * n + j;
				for (int[] dir : dirs) {
					int x = i + dir[0];
					int y = j + dir[1];
					if (isInBounds(x, y, n) && grid[x][y] == 1) {
						int adjacentNodeId = x * n + y;
						if (find(parent, nodeId) != find(parent, adjacentNodeId)) {
							union(parent, size, nodeId, adjacentNodeId);
						}
					}
				}
			}
		}
		// if noOfOnes == n*n that means there is no zero,
		// we cannot place any 1 in place of 0
		if (noOfOnes == N) return noOfOnes;

		int[] stack = new int[N];
		int top = -1;
		boolean[] set = new boolean[N];

		int largestIsland = 0;
		// now we will loop through the grid and find 0
		// and check if we can find unique surrounding component or not
		// then the size will be 1+all surrounding unique component
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) continue;
				int localIsland = 1;
				for (int[] dir : dirs) {
					int x = i + dir[0];
					int y = j + dir[1];
					if (isInBounds(x, y, n) && grid[x][y] == 1) {
						int adjacentNodeId = x * n + y;
						int adjacentNodeParent = find(parent, adjacentNodeId);
						if (!set[adjacentNodeParent]) {
							// adding the node to the set
							set[adjacentNodeParent] = true;
							// adding node to stack so that we don't have to
							// create a visited array all the time which will save some memory
							stack[++top] = adjacentNodeParent;
							localIsland += size[adjacentNodeParent];
						}
					}
				}
				// clearing the set array using the stack
				while (top != -1) set[stack[top--]] = false;
				largestIsland = Math.max(largestIsland, localIsland);
			}
		}
		return largestIsland;
	}

	// We will use a Disjoint set here
	// we will use union by size this time
	private static void type1() {
		int[][] grid = {
				{1, 1, 0, 1, 1, 0},
				{1, 1, 0, 1, 1, 0},
				{1, 1, 0, 1, 1, 0},
				{0, 0, 1, 0, 0, 0},
				{0, 0, 1, 1, 1, 0},
				{0, 0, 1, 1, 1, 0},
		};
		int ans = largestIsland1(grid);
		System.out.println(ans);
	}

	public static int largestIsland1(int[][] grid) {
		int n = grid.length;
		int N = n * n;
		// first, we will create an initialize parent and size arrays
		int[] parent = new int[N];
		int[] size = new int[N];
		for (int i = 0; i < N; i++) {
			size[i] = 1;
			parent[i] = i;
		}
		// this is the direction array
		int[][] dirs = {
				{0, 1},
				{0, -1},
				{1, 0},
				{-1, 0}
		};
		// by this union find we have gathered the size of the individual components
		int noOfOnes = 0;
		// we will traverse all the nodes and create components
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// if this is 0, then we can skip
				if (grid[i][j] == 0) continue;
				noOfOnes++;
				// we will create a unique node id for all the cells
				int nodeId = (i * n) + j;
				for (int[] dir : dirs) {
					int x = i + dir[0];
					int y = j + dir[1];
					// if there are adjacent cells with value 1, then we will unify the components
					if (isInBounds(x, y, n) && grid[x][y] == 1) {
						int adjacentNodeId = (x * n) + y;
						if (find(parent, nodeId) != find(parent, adjacentNodeId))
							union(parent, size, nodeId, adjacentNodeId);
					}
				}
			}
		}
		// if noOfOnes == n*n that means there is no zero,
		// we cannot place any 1 in place of 0
		if (noOfOnes == N) return noOfOnes;
		int largestIsland = 0;
		// now we will loop through the grid and find 0, and check if we can find unique surrounding component
		// or not, then the size will be 1+all surrounding unique part.
		// we have used the set to check all the unique components which are neighbors to the current cell
		// we will store the parent nodes of the components in the set
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) continue;
				set.clear();
				int localIsland = 1;
				for (int[] dir : dirs) {
					int x = i + dir[0];
					int y = j + dir[1];
					if (isInBounds(x, y, n) && grid[x][y] == 1) {
						int adjacentNodeId = (x * n) + y;
						int adjacentNodeParent = find(parent, adjacentNodeId);
						// checking if the component is in the set or not if then, we will add that into the set,
						// and also we will add size and at last we will check the total size
						if (!set.contains(adjacentNodeParent)) {
							set.add(adjacentNodeParent);
							localIsland += size[adjacentNodeParent];
						}
					}
				}
				largestIsland = Math.max(largestIsland, localIsland);
			}
		}
		return largestIsland;
	}

	private static boolean isInBounds(int x, int y, int n) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}

	public static void union(int[] parent, int[] size, int u, int v) {
		int baseParentU = find(parent, u);
		int baseParentV = find(parent, v);
		if (size[baseParentU] < size[baseParentV]) {
			parent[baseParentU] = baseParentV;
			size[baseParentV] += size[baseParentU];
		} else {
			parent[baseParentV] = baseParentU;
			size[baseParentU] += size[baseParentV];
		}
	}

	public static int find(int[] parent, int node) {
		// src == parent[src] means parent of the node
		if (node == parent[node]) return node;
		int baseParent = find(parent, parent[node]);
		parent[node] = baseParent;
		return baseParent;
	}
}
