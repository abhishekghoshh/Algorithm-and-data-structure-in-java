package com.problems.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static util.OnlineJudgeInit.scanner;
import static util.OnlineJudgeInit.set;

/*
 * Problem link :
 * https://www.geeksforgeeks.org/problems/minimum-spanning-tree/1
 * 
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=DMnDM_sxVig&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=47
 * 
 * https://takeuforward.org/data-structure/kruskals-algorithm-minimum-spanning-tree-g-47/
 */
public class KruskalAlgorithm {

	public static void main(String[] args) {
//		set();
		type1();
		type2();
	}

	// todo this is same as previous but here we will store the mst
	private static void type2() {
		int v = 3;
		int e = 3;
		int[][] edges = {
				{0, 1, 5},
				{1, 2, 3},
				{0, 2, 1}
		};

		// sorting the edges by weight
		Arrays.sort(edges, Comparator.comparingInt(edge -> edge[2]));

		int[] rank = new int[v];
		int[] parent = new int[v];
		for (int i = 0; i < v; i++) {
			rank[i] = 0;
			parent[i] = i;
		}
		List<int[]> mst = new ArrayList<>();
		// now we will iterate through edges from least to highest weight
		for (int[] edge : edges) {
			int src = edge[0];
			int dest = edge[1];
			// if the parent of both the src and dest node are the same then by adding this edge, it will create a loop
			if (find(parent, src) != find(parent, dest)) {
				union(parent, rank, src, dest);
				mst.add(edge);
			}
		}
		System.out.println("mst ->>");
		print(mst);

	}

	// we will use Disjoint set and Union-find method to join the components
	// first we will sort the edges lowest to highest.
	// we will consider one edge at a time, but we have to check if the adjacent nodes are already
	// in the same component or not if no then only we will add that edge.
	// because if they are in the same component, then addition of this new edge will be unnecessary
	// because we can already go from a to b and this edge also going from a to b
	// todo we are using Disjoint set by rank, but we can also use the Disjoint set by size
	//  this will calculate the minimum path sum but we can also calculate the mst and edges
	private static void type1() {
		int v = 3;
		int e = 3;
		int[][] edges = {
				{0, 1, 5},
				{1, 2, 3},
				{0, 2, 1}
		};

		// sorting the edges by weight
		Arrays.sort(edges, Comparator.comparingInt(edge -> edge[2]));
		// we will initialize the rank and parent
		int[] rank = new int[v];
		int[] parent = new int[v];
		for (int i = 0; i < v; i++) {
			rank[i] = 0;
			parent[i] = i;
		}
		int minimumPathSum = 0;
		// now we will iterate through edges from least to highest weight
		for (int[] edge : edges) {
			int src = edge[0];
			int dest = edge[1];
			int wt = edge[2];
			// if the parent of both the src and dest node are the same then by adding this edge, it will create a loop
			if (find(parent, src) != find(parent, dest)) {
				union(parent, rank, src, dest);
				minimumPathSum += wt;
			}
		}
		System.out.println(minimumPathSum);
	}


	private static void union(int[] parent, int[] rank, int u, int v) {
		int baseParentU = find(parent, u);
		int baseParentV = find(parent, v);
		if (rank[baseParentU] < rank[baseParentV]) {
			parent[baseParentU] = baseParentV;
			rank[baseParentV]++;
		} else {
			parent[baseParentV] = baseParentU;
			rank[baseParentU]++;
		}
	}

	private static int find(int[] parent, int node) {
		// src == parent[src] means parent of the node
		if (node == parent[node]) return node;
		int baseParent = find(parent, parent[node]);
		parent[node] = baseParent;
		return baseParent;
	}

	private static void print(List<int[]> mst) {
		for (int[] edge : mst) {
			System.out.println(edge[0] + ", " + edge[1] + ", " + edge[2]);
		}
		System.out.println();
	}
}
