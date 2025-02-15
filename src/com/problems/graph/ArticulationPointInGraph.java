package com.problems.graph;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem link :
 * https://practice.geeksforgeeks.org/problems/articulation-point-1/1
 * 
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=j1QDfU21iZk&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=56
 * 
 * https://takeuforward.org/data-structure/articulation-point-in-graph-g-56/
 */
public class ArticulationPointInGraph {

	// Given an undirected connected graph with V vertices and adjacency list adj.
	// You are required to find all the vertices removing which (and edges through it)
	// disconnects the graph into 2 or more components.
	// Note: Indexing is zero-based i.e., nodes numbering from (0 to V-1).
	// There might be loops present in the graph.
	public static void main(String[] args) {
		type1();
	}

	// todo check the video once again
	// todo this draws intuition from the bridges in graph problem
	// we will 3 arrays here
	private static void type1() {
		List<List<Integer>> adj = List.of(
				List.of(1),
				List.of(0, 4),
				List.of(3, 4),
				List.of(2, 4),
				List.of(1, 2, 3)
		);
		int n = 5;
		List<Integer> ans = articulationPoints(n, adj);
		System.out.println(ans);
	}

	public static List<Integer> articulationPoints(int n, List<List<Integer>> adj) {
		boolean[] visited = new boolean[n];
		// we will use time of insertion, low and mark array
		// timeOfInsertion array is the first time when then we come to any node
		// low is the lowest time of insertion apart from the parent
		int[] timeOfInsertion = new int[n], low = new int[n];
		int[] mark = new int[n];
		// we will start the dfs from all the unvisited nodes
		for (int i = 0; i < n; i++)
			if (!visited[i])
				dfs(i, -1, visited, timeOfInsertion, low, mark, adj);

		List<Integer> ans = new ArrayList<>();
		// if mark is 1 then
		for (int i = 0; i < n; i++)
			if (mark[i] == 1) ans.add(i);
		return (!ans.isEmpty()) ? ans : List.of(-1);
	}

	static int timer = 1;

	// todo read the comments again and change it accordingly
	private static void dfs(int start, int parent, boolean[] visited, int[] timeOfInsertion,
							int[] low, int[] mark, List<List<Integer>> adj) {
		visited[start] = true;
		timeOfInsertion[start] = low[start] = timer;
		timer++;
		int child = 0;
		for (int end : adj.get(start)) {
			if (end == parent) continue;
			if (!visited[end]) {
				dfs(end, start, visited, timeOfInsertion, low, mark, adj);
				low[start] = Math.min(low[start], low[end]);
				// we are marking the start as an articulation point here
				// we are not adding it to the answer here directly, as start can be articulation point,
				// for multiple components, so we are just marking it here
				if (timeOfInsertion[start] <= low[end] && parent != -1) mark[start] = 1;
				child++;
			} else {
				// this part is different from the bridges of graph
				// if the end is already visited, then we will be taking from timeOfInsertion[end].
				// because we are removing the start node hypothetically, if we remove the start node,
				// then we cannot take its low time, so we will take timeOfInsertion
				low[start] = Math.min(low[start], timeOfInsertion[end]);
			}
		}
		// if it is a starting point, and it has multiple children,
		// then also starting point can be articulation point
		if (child > 1 && parent == -1) mark[start] = 1;
	}
}
