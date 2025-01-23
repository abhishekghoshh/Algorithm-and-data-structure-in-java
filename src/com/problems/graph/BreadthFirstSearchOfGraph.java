package com.problems.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Problem link :
 * https://practice.geeksforgeeks.org/problems/bfs-traversal-of-graph/1
 * https://www.codingninjas.com/studio/problems/bfs-in-graph_973002
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=-tgVpUgsQ5k&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=5
 * 
 * https://takeuforward.org/graph/breadth-first-search-bfs-level-order-traversal/
 */
public class BreadthFirstSearchOfGraph {
	public static void main(String[] args) {
		type1();
		type2();
	}


	// in an adjacency list
	private static void type2() {
		ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
		adjList.add(new ArrayList<>(List.of(2, 3, 1)));
		adjList.add(new ArrayList<>(List.of(0)));
		adjList.add(new ArrayList<>(List.of(0, 4)));
		adjList.add(new ArrayList<>(List.of(0)));
		adjList.add(new ArrayList<>(List.of(2)));
		int v = 5;

		ArrayList<Integer> bfsOfGraph = new ArrayList<>();
		// using a visited array to mark the current node is visited or not
		boolean[] visited = new boolean[v];
		Queue<Integer> queue = new LinkedList<>();

		// setting the 0th node as the first node
		visited[0] = true;
		queue.offer(0);
		bfsOfGraph.add(0);

		// we will go to all connected nodes and store it to the queue.
		// and the queue will make sure that the current level is exhausted first
		while (!queue.isEmpty()) {
			int start = queue.poll();
			for (int end : adjList.get(start)) {
				// if the start is end or the end node is already visited, then we will not add that into queue
				if (start == end || visited[end]) continue;
				visited[end] = true;
				queue.offer(end);
				bfsOfGraph.add(end);
			}
		}
		System.out.println(bfsOfGraph);
	}

	// in an adjacency matrix
	private static void type1() {
		int v = 5;
		int[][] graph = {
				{0, 1, 1, 1, 0},
				{1, 0, 0, 0, 0},
				{1, 0, 0, 0, 1},
				{1, 0, 0, 0, 0},
				{0, 0, 1, 0, 0}
		};
		ArrayList<Integer> bfsOfGraph = new ArrayList<>();
		// using a visited array to mark the current node is visited or not
		boolean[] visited = new boolean[v];
		Queue<Integer> queue = new LinkedList<>();

		// setting the 0th node as the first node
		visited[0] = true;
		queue.offer(0);
		bfsOfGraph.add(0);

		// we will go to all connected nodes and store it to the queue.
		// and the queue will make sure that the current level is exhausted first
		while (!queue.isEmpty()) {
			int start = queue.poll();
			for (int end = 0; end < v; end++) {
				// if the start is end or there is no edge or the end node is already visited,
				// then we will not add that into queue
				if (start == end || graph[start][end] == 0 || visited[end]) continue;
				visited[end] = true;
				queue.offer(end);
				bfsOfGraph.add(end);
			}
		}
		System.out.println(bfsOfGraph);
	}

}
