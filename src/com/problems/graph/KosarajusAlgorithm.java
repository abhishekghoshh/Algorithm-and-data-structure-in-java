package com.problems.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
 * Problem link :
 * https://www.geeksforgeeks.org/problems/strongly-connected-components-kosarajus-algo/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=R6uoSjZ2imo&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=54
 *
 * https://leetcode.com/problems/maximum-number-of-non-overlapping-substrings/solutions/766485/kosaraju-algorithm-on/
 * https://takeuforward.org/graph/strongly-connected-components-kosarajus-algorithm-g-54/
 */
public class KosarajusAlgorithm {
	// todo A directed graph is strongly connected if there is a path between any two pair of vertices.
	// Given a Directed Graph with V vertices (Numbered from 0 to V-1) and E edges,
	// Find the number of strongly connected components in the graph.
	// todo check the video again
	public static void main(String[] args) {
		type1();
		type2();
	}

	// todo exactly like the previous but here we will also store the strongly connected component
	private static void type2() {
		int v = 5;
		List<List<Integer>> adj = List.of(
				List.of(2, 3),
				List.of(0),
				List.of(1),
				List.of(4),
				List.of()
		);

		Stack<Integer> stack = new Stack<>();
		boolean[] visited = new boolean[v];

		for (int i = 0; i < v; i++) {
			if (!visited[i])
				dfs(i, adj, visited, stack);
		}

		// now we will reverse the entire graph
		List<List<Integer>> revAdj = new ArrayList<>();
		for (int i = 0; i < v; i++) revAdj.add(new ArrayList<>());
		for (int start = 0; start < v; start++)
			for (int end : adj.get(start))
				revAdj.get(end).add(start);

		// unsetting the visited array, otherwise we have to create another array
		Arrays.fill(visited, false);

		List<List<Integer>> ans = new ArrayList<>();
		// if we also want the components, then we can just pass one arraylist to store all the components.
		// now from the stack we will pop elements and start the dfs as the graph is reversed,
		// so it will only roam around in its strongly connected component, it cannot go outside
		while (!stack.isEmpty()) {
			int node = stack.pop();
			if (!visited[node]) {
				ans.add(new ArrayList<>());
				dfs(node, visited, revAdj, ans);
			}
		}
		System.out.println(ans);
	}

	private static void dfs(int start, boolean[] visited, List<List<Integer>> adj, List<List<Integer>> ans) {
		visited[start] = true;
		ans.get(ans.size() - 1).add(start);
		for (int end : adj.get(start))
			if (!visited[end])
				dfs(end, visited, adj, ans);
	}

	// in a strongly connected component from one node all other nodes can be reached,
	// suppose in a graph there is 4 strongly connected component i.e. sc1 -> sc2 -> sc3 -> sc4
	// if we reverse all the edges then in one sc it will not change anything
	// sc1 <- sc2 <- sc3 <- sc4
	// so by somehow if we store the dfs traversal for the first graph it will store
	// the node time when it was visited
	// again on the second time we will apply the same dfs but with some simple tweak
	private static void type1() {
		int v = 5;
		List<List<Integer>> adj = List.of(
				List.of(2, 3),
				List.of(0),
				List.of(1),
				List.of(4),
				List.of()
		);

		Stack<Integer> stack = new Stack<>();
		boolean[] visited = new boolean[v];

		for (int i = 0; i < v; i++) {
			if (!visited[i])
				dfs(i, adj, visited, stack);
		}

		// now we will reverse the entire graph
		List<List<Integer>> revAdj = new ArrayList<>();
		for (int i = 0; i < v; i++) revAdj.add(new ArrayList<>());
		for (int start = 0; start < v; start++)
			for (int end : adj.get(start))
				revAdj.get(end).add(start);

		// unsetting the visited array, otherwise we have to create another array
		Arrays.fill(visited, false);

		int count = 0;
		// if we also want the components, then we can just pass one arraylist to store all the components.
		// now from the stack we will pop elements and start the dfs as the graph is reversed,
		// so it will only roam around in its strongly connected component, it cannot go outside
		while (!stack.isEmpty()) {
			int node = stack.pop();
			if (!visited[node]) {
				count++;
				dfs(node, visited, revAdj);
			}
		}
		System.out.println(count);
	}

	// this is exactly the same dfs as other just here we are storing the nodes in the stack
	// starting nodes comes at the top and deepest nodes come at the bottom
	private static void dfs(int i, List<List<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
		visited[i] = true;
		for (int node : adj.get(i))
			if (!visited[node])
				dfs(node, adj, visited, stack);
		stack.add(i);
	}

	// a normal dfs function
	private static void dfs(int i, boolean[] visited, List<List<Integer>> adj) {
		visited[i] = true;
		for (int node : adj.get(i))
			if (!visited[node])
				dfs(node, visited, adj);
	}


}
