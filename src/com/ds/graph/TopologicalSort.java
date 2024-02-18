package com.ds.graph;

import com.util.GraphUtil;

import java.util.List;
import java.util.Stack;

import static com.util.GraphUtil.graphBuilder;
import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://practice.geeksforgeeks.org/problems/topological-sort/1
 * https://www.codingninjas.com/studio/problems/topological-sorting_973003
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=5lZ0iJMrUMk&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=21
 * 
 * https://takeuforward.org/data-structure/topological-sort-algorithm-dfs-g-21/
 */
public class TopologicalSort {
	// Given a Directed Acyclic Graph (DAG) with V vertices and E edges,
	// Find any Topological Sorting of that Graph.
	// In a graph if there is any edge from 1 to 2 and from 2 to 3
	/// then in topological sort, the answer will be 1 -> 2 -> 3,
	// and it will always be acyclic graph
	public static void main(String[] args) {
		type1();
	}

	// TODO check one more time if it still confuses you
	// so we will call dfs
	// and dfs will make sure that it will go to the last node,
	// and while backtracking we are storing the nodes in a stack
	// so the last node from which, there is no outgoing edge
	// will be put in the stack first
	// so if the edge is 1 -> 4 -> 3,
	// and we are going from 1, then the stack will be like
	// 1|4|3, now if we just pop and store in the array then
	// we will find our simple Topo sort
	private static void type1() {
		int v = 6;
		List<List<Integer>> adjacencyList = graphBuilder()
				.nodes(v)
				.start(0).end()
				.start(1).end()
				.start(2).end(3)
				.start(3).end(1)
				.start(4).end(0, 1)
				.start(5).end(0, 2)
				.buildList();
		GraphUtil.print(adjacencyList);

		boolean[] visited = new boolean[v];
		Stack<Integer> stack = new Stack<>();
		int[] answer = new int[v];

		for (int i = 0; i < v; i++)
			// if the node is not visited, yet then we will start the dfs from the node
			if (!visited[i]) dfs(i, visited, adjacencyList, stack);
		int i = 0;
		while (!stack.isEmpty()) answer[i++] = stack.pop();
		print(answer);
	}

	private static void dfs(int start, boolean[] visited,
							List<List<Integer>> adjacencyList, Stack<Integer> stack) {
		visited[start] = true;
		for (int node : adjacencyList.get(start))
			// calling the dfs if the adjacent node is not visited
			if (!visited[node]) dfs(node, visited, adjacencyList, stack);
		// for start node either there is not any unvisited adjacent node or simply
		// there is no adjacent node, so we can add it to the stack
		stack.add(start);
	}
}
