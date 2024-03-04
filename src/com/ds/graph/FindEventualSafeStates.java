package com.ds.graph;

import java.util.*;

/*
 * Problem link :
 * https://leetcode.com/problems/find-eventual-safe-states/
 * https://www.codingninjas.com/studio/problems/safe-nodes-in-the-graph_1376703
 * https://practice.geeksforgeeks.org/problems/eventual-safe-states/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=uRbJ1OF9aYM&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=20
 * https://www.youtube.com/watch?v=2gtg3VsDGyc&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=26
 * 
 * https://takeuforward.org/data-structure/find-eventual-safe-states-dfs-g-20/
 * https://takeuforward.org/data-structure/find-eventual-safe-states-bfs-topological-sort-g-25/
 */
public class FindEventualSafeStates {

	// A directed graph of V vertices and E edges is given in the form of an
	// adjacency list adj. Each node of the graph is labelled with a distinct
	// integer in the range 0 to V - 1.

	// A node is a terminal node if there are no outgoing edges. A node is a safe
	// node if every possible path starting from that node leads to a terminal node.

	// You have to return an array containing all the safe nodes of the graph. The
	// answer should be sorted in ascending order.
	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
	}

	// Using bfs or topo sort,
	// We know the terminal nodes are safe always, so we will backtrack from terminal nodes.
	// Terminal nodes will have no out degree as we will use topo sort, so we have to use indegree,
	// so we will reverse the graph
	private static void type4() {
		List<ArrayList<Integer>> adjacencyList = adjacencyList();
		int v = 7;
		List<List<Integer>> adjacencyListRev = new ArrayList<>();
		int[] inDegree = new int[v];
		Queue<Integer> queue = new LinkedList<>();
		// we will do the reverse and calculate the indegree in the same loop
		for (int i = 0; i < v; i++) adjacencyListRev.add(new ArrayList<>());
		for (int start = 0; start < v; start++) {
			for (int end : adjacencyList.get(start)) {
				adjacencyListRev.get(end).add(start);
				inDegree[start]++;
			}
		}
		for (int i = 0; i < v; i++)
			if (inDegree[i] == 0) queue.offer(i);

		List<Integer> answer = new ArrayList<>();

		while (!queue.isEmpty()) {
			int start = queue.poll();
			answer.add(start);
			for (int end : adjacencyListRev.get(start)) {
				inDegree[end]--;
				if (inDegree[end] == 0) queue.offer(end);
			}
		}
		Collections.sort(answer);
		System.out.println(answer);
	}

	// same as previous, but here we will directly add the nodes to the
	// safe nodes list, we will not use extra loop
	private static void type3() {
		int[][] adjacencyList = {
				{1, 2},
				{2, 3},
				{5},
				{0},
				{5},
				{},
				{}
		};
		int v = adjacencyList.length;
		int nodeCount = adjacencyList.length;
		int[] color = new int[nodeCount];
		List<Integer> safeNodes = new ArrayList<>();
		for (int node = 0; node < v; node++)
			if (isSafeNode(adjacencyList, node, color))
				safeNodes.add(node);
		System.out.println(safeNodes);
	}

	public static boolean isSafeNode(int[][] adjacencyList, int start, int[] color) {
		// if the node is already visited, then we will return true if the color is 1
		// as color 2 is for path visited
		if (color[start] != 0)
			return color[start] == 1;
		// Mark as path visiting
		color[start] = 2;
		for (int end : adjacencyList[start])
			if (!isSafeNode(adjacencyList, end, color))
				return false;
		color[start] = 1;
		return true;
	}

	// using dfs
	// with adjacency matrix
	// an single visited matrix
	private static void type2() {
		int[][] graph = { { 1, 2 }, { 2, 3 }, { 5 }, { 0 }, { 5 }, {}, {} };
		int v = graph.length;
		int[] visited = new int[v];
		for (int i = 0; i < v; i++) {
			//for every unvisited node, we will start the hasCycle dfs traversal
			if (visited[i] == 0) hasCycleInPath(i, visited, graph);
		}
		List<Integer> safeNodes = new ArrayList<>();
		// for those nodes that have path visited to true that is either in cycle or leading to cycle
		for (int i = 0; i < v; i++)
			if (visited[i] != 2) safeNodes.add(i);
		System.out.println(safeNodes);
	}

	private static boolean hasCycleInPath(int start, int[] visited, int[][] adjacencyList) {
		visited[start] = 2;
		// we are setting a visited flag to 2 at first.
		// if there is any cycle in the path, then in subsequent dfs call will return
		// true or if the start itself is in cycle then visited[node]=2 then also we will return true.
		// in both cases, visited[start] will not set to 1
		for (int node : adjacencyList[start]) {
			if (visited[node] == 2) return true;
			if (visited[node] == 0 && hasCycleInPath(node, visited, adjacencyList))
				return true;
		}
		visited[start] = 1;
		return false;
	}

	// using dfs
	// so intuition is, every node has no out degree will be safe nodes,
	// every node that only going to terminal nodes will be safe nodes.
	// every node is part of a cycle or leading to a cycle is not a safe nodes
	private static void type1() {
		List<ArrayList<Integer>> adjacencyList = adjacencyList();
		int v = 7;

		boolean[] visited = new boolean[v];
		boolean[] pathVisited = new boolean[v];
		for (int i = 0; i < v; i++) {
			if (!visited[i])
				hasCycleInPath(i, visited, pathVisited, adjacencyList);
		}
		List<Integer> safeNodes = new ArrayList<>();
		// for those nodes that have path visited to true that is either in cycle or leading to cycle
		for (int i = 0; i < v; i++)
			if (!pathVisited[i]) safeNodes.add(i);
		System.out.println(safeNodes);
	}

	private static boolean hasCycleInPath(int start, boolean[] visited, boolean[] pathVisited, List<ArrayList<Integer>> adjacencyList) {
		visited[start] = true;
		// We are setting pathVisited flag to true at first.
		// If there is any cycle in the path, then in subsequent dfs call will return true
		// or if the start itself is in cycle then pathVisited[node] then also we will return true.
		// In both cases, pathVisited[start] will not set to false
		pathVisited[start] = true;
		for (int node : adjacencyList.get(start)) {
			if (pathVisited[node]) return true;
			if (!visited[node] && hasCycleInPath(node, visited, pathVisited, adjacencyList))
				return true;
		}
		pathVisited[start] = false;
		return false;
	}

	private static ArrayList<ArrayList<Integer>> adjacencyList() {
		ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();
		adjacencyList.add(new ArrayList<>(List.of(1, 2)));
		adjacencyList.add(new ArrayList<>(List.of(2, 3)));
		adjacencyList.add(new ArrayList<>(List.of(5)));
		adjacencyList.add(new ArrayList<>(List.of(0)));
		adjacencyList.add(new ArrayList<>(List.of(5)));
		adjacencyList.add(new ArrayList<>(List.of()));
		adjacencyList.add(new ArrayList<>(List.of()));
		return adjacencyList;
	}
}
