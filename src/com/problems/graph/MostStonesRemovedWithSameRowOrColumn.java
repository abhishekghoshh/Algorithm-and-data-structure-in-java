package com.problems.graph;

/*
 * Problem link :
 * https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
 * https://practice.geeksforgeeks.org/problems/maximum-stone-removal-1662179442/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=OwMNX8SPavM&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=53
 * 
 * https://takeuforward.org/data-structure/most-stones-removed-with-same-row-or-column-dsu-g-53/
 */
public class MostStonesRemovedWithSameRowOrColumn {

	// On a 2D plane, we place n stones at some integer coordinate points. Each
	// coordinate point may have at most one stone.
	// A stone can be removed if it shares either the same row or the same column as
	// another stone that has not been removed.
	// Given an array of stones of length n where stones[i] = [xi, yi] represents the
	// location of the i'th stone, return the largest possible number of stones that
	// can be removed.
	public static void main(String[] args) {
		type1();
	}

	// our work it a group all the stones that are in the same row and column,
	// ideally they are connected by this.
	// so if we remove all the stones from that group except 1, then our work is done
	// then there will be no stone which will be in the same group
	// the answer is = (componentA -1) + (componentB -1) + (componentC -1) + (componentD -1) ....
	// ans = (componentA + componentB + componentC + componentD...) - (1 + 1 + 1 + 1 ...)
	// ans = total summation of component size - number of components
	// ans = number of all stones - number of components
	private static void type1() {
		int[][] stones = {
				{0, 0},
				{0, 2},
				{1, 1},
				{2, 0},
				{2, 2}
		};
		int n = stones.length;

		// first we have find out the row size and column size
		int maxRow = -1, maxCol = -1;
		for (int[] stone : stones) {
			maxRow = Math.max(maxRow, stone[0]);
			maxCol = Math.max(maxCol, stone[1]);
		}
		// as the row and column both can be 0 ... n,
		// so we will be shifting the columns by (maxRow + 1).
		// 0th column will be mapped to (maxRow + 1).
		// so we are projecting the 2d coordinates to 1D
		// 0 to maxRow and then remaining will be (maxRow + 1) to (maxRow + 1 + maxCol).
		int N = maxRow + maxCol + 1;
		// we will initialize the parent and the size array
		int[] parent = new int[N + 1];
		int[] size = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parent[i] = i;
			size[i] = 1;
		}

		boolean[] stoneNodes = new boolean[N + 1];
		for (int[] stone : stones) {
			int nodeRow = stone[0];
			// shifting the column
			int nodeCol = stone[1] + (maxRow + 1);
			// we are doing the union for column and row if there is a stone
			// so the row and column will be connected
			union(parent, size, nodeRow, nodeCol);

			// setting stoneNodes array to true to mark that there is a stone
			stoneNodes[nodeRow] = stoneNodes[nodeCol] = true;
		}

		int componentCount = 0;
		// out of all rows and column we are checking how many individual separate components are present
		for (int i = 0; i <= N; i++) {
			if (stoneNodes[i] && (find(parent, i) == i))
				componentCount++;
		}
		// so for all components, there should be the least one stone present remaining can be removed,
		// so if there is x number of components and total n no of stones, then n-x can be removed
		int ans = n - componentCount;
		System.out.println(ans);
	}

	private static void union(int[] parent, int[] size, int u, int v) {
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

	private static int find(int[] parent, int node) {
		if (node == parent[node]) return node;
		int baseParent = find(parent, parent[node]);
		parent[node] = baseParent;
		return baseParent;
	}

}
