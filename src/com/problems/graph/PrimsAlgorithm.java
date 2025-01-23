package com.problems.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*
 * Problem link :
 * https://www.geeksforgeeks.org/problems/minimum-spanning-tree/1
 * 
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=mJcZjjKzeqk&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=45
 * 
 * https://takeuforward.org/data-structure/prims-algorithm-minimum-spanning-tree-c-and-java-g-45/
 */
public class PrimsAlgorithm {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// exactly like the previous type, but here we will not store the mst
	// we will only store the total edge-distance of the mst
	// hence we will also not store the parent of the node in the min heap
	private static void type2() {
		int v = 3;
		int e = 3;
		int[][] edges = {
				{0, 1, 5},
				{1, 2, 3},
				{0, 2, 1}
		};

		// first, we will create an adjacency list
		List<List<int[]>> adj = new ArrayList<>();
		for (int i = 0; i < v; i++) adj.add(new ArrayList<>());
		// it will be an undirected graph
		for (int[] edge : edges) {
			adj.get(edge[0]).add(new int[] { edge[1], edge[2] });
			adj.get(edge[1]).add(new int[] { edge[0], edge[2] });
		}
		// we will use a visited array to store the visited nodes
		boolean[] visited = new boolean[v];

		// we will use a priority queue to store the pair of { edge , current node},
		// and everytime we will take the minimum edge and add the nodes in the visited array
		PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(p -> p[0]));
		// we will start with the 0
		minHeap.offer(new int[]{0, 0});

		int minimumPathWt = 0;

		while (!minHeap.isEmpty()) {
			int[] pair = minHeap.poll();
			int edgeWt = pair[0];
			int src = pair[1];
			// if the node is already visited, then we can skip
			if (visited[src]) continue;
			// set the node to the visited
			visited[src] = true;
			// we will add the edge weight
			minimumPathWt += edgeWt;
			for (int[] edge : adj.get(src)) {
				// if visited[adjacentNode] is true, then, if we add the edge, then it will create a loop
				int adjNode = edge[0], dis = edge[1];
				// if the nodes are visited, then we will skip
				if (visited[adjNode]) continue;
				// we will add the edge distance, node and its parent node
				minHeap.offer(new int[]{dis, adjNode});
			}
		}
		System.out.println(minimumPathWt);
	}

	// we will use a hard min heap to store the edge distance and nodes,
	// and every time we will take the least edge-distance and add that nodes the mst if they are unvisited
	private static void type1() {
		int v = 3;
		int e = 3;
		int[][] edges = {
				{0, 1, 5},
				{1, 2, 3},
				{0, 2, 1}
		};
		// first, we will create an adjacency list
		List<List<int[]>> adj = new ArrayList<>();
		for (int i = 0; i < v; i++) adj.add(new ArrayList<>());
		// it will be an undirected graph
		for (int[] edge : edges) {
			adj.get(edge[0]).add(new int[] { edge[1], edge[2] });
			adj.get(edge[1]).add(new int[] { edge[0], edge[2] });
		}
		// we will use a visited array to store the visited nodes
		boolean[] visited = new boolean[v];

		// we will use a priority queue to store the pair of { edge , current node and its parent},
		// and everytime we will take the minimum edge and add the nodes in the visited array
		PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(p -> p[0]));
		// we will start with the 0
		minHeap.offer(new int[] { 0, 0, -1 });

		List<int[]> mst = new ArrayList<>();

		// we will not add the node to a visited array while traversing
		// we will only add when we are polling from the heap
		while (!minHeap.isEmpty()) {
			int[] pair = minHeap.poll();
			int edgeWt = pair[0];
			int node = pair[1];
			int parent = pair[2];
			// if the node is already visited, then we can skip
			if (visited[node]) continue;
			// set the node to the visited
			visited[node] = true;
			// we will the edge to the mst, parent = -1 means, it is the starting node or 0
			if (parent != -1) mst.add(new int[]{parent, node, edgeWt});
			// now we will traverse all its adjacent nodes
			for (int[] edge : adj.get(node)) {
				// if visited[adjacentNode] is true, then, if we add the edge, then it will create a loop
				int adjNode = edge[0], dis = edge[1];
				// if the nodes are visited, then we will skip
				if (visited[adjNode]) continue;
				// we will add the edge distance, node and its parent node
				minHeap.offer(new int[]{dis, adjNode, node});
			}
		}
		print(mst);
	}

	private static void print(List<int[]> mst) {
		for (int[] edge : mst) {
			System.out.println(edge[0] + ", " + edge[1] + ", " + edge[2]);
		}
		System.out.println();
	}

}
