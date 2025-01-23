package com.problems.graph;

/*
 * Problem link :
 * https://leetcode.com/problems/number-of-operations-to-make-network-connected/
 * https://practice.geeksforgeeks.org/problems/connecting-the-graph/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=FYrl7iz9_ZU&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=49
 * 
 * https://takeuforward.org/data-structure/number-of-operations-to-make-network-connected-dsu-g-49/
 */
public class NumberOfOperationsToMakeANetworkConnected {

	// There are n computers numbered from 0 to n - 1 connected by ethernet cables
	// connections forming a network where connections{i} = {ai, bi} represents a
	// connection between computers ai and bi. Any computer can reach any other
	// computer directly or indirectly through the network.

	// You are given an initial computer network connections. You can extract
	// certain cables between two directly connected computers, and place them
	// between any pair of disconnected computers to make them directly connected.

	// Return the minimum number of times you need to do this to make all
	// the computers connected. If it is not possible, return -1.
	public static void main(String[] args) {
		type1();
	}

	// We can directly use union find data structure here
	// if there are x components then we need x-1 wires to connect
	// if we have exactly x-1 or more unused ropes, then we can use that ropes to connect others
	private static void type1() {
		int n = 6;
		int[][] connections = {
				{0, 1},
				{0, 2},
				{0, 3},
				{1, 2},
				{1, 3}
		};
		int ans = makeConnected(n, connections);
		System.out.println(ans);

	}

	public static int makeConnected(int n, int[][] connections) {
		int[] rank = new int[n];
		int[] parent = new int[n];
		for (int i = 0; i < n; i++) {
			rank[i] = 0;
			parent[i] = i;
		}
		int unusedConnections = 0;
		for (int[] connection : connections) {
			int src = connection[0];
			int dest = connection[1];
			// if the parent of both the src and dest node are the same, then by adding this edge it will create a loop
			if (find(parent, src) != find(parent, dest)) {
				union(parent, rank, src, dest);
			} else {
				// node a and b are already in the same component
				// it means there is already a connection made to connect these 2 nodes
				// we do not need this connection in the group
				unusedConnections++;
			}
		}
		int numberOfProvinces = 0;
		// number of unique parents equals to number of provinces
		// let's say we have n unique components then we will need n-1 new connection to connect n nodes
		for (int i = 0; i < n; i++)
			if (i == parent[i]) numberOfProvinces++;

		// if unused connection is greater than n-1, then we can use those connections to connect n nodes
		return (unusedConnections >= numberOfProvinces - 1) ? (numberOfProvinces - 1) : -1;
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

}
