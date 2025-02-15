package com.problems.graph;

import com.util.PrintUtl;

import java.util.*;

//import static util.OnlineJudgeInit.scanner;
//import static util.OnlineJudgeInit.set;

/*
 * Problem link :
 * https://practice.geeksforgeeks.org/problems/shortest-path-in-undirected-graph-having-unit-distance/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=C4gxoTaI71U&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=28
 * 
 * https://takeuforward.org/data-structure/shortest-path-in-undirected-graph-with-unit-distance-g-28/
 */
public class ShortestPathInUndirectedGraphWithUnitWeights {

	// You are given an Undirected Graph having unit weight, Find the shortest path
	// from src(0) to all the vertex and if it is unreachable to reach any vertex,
	// then return -1 for that vertex.
	public static void main(String[] args) {
		type1();
	}

	// we will dimple bfs
	// first we have to transform the edges to corresponding undirected adj list
	// we will start from the src node and visit all it's adjacent nodes
	// use a distance array and set all the node value to infinity
	// and src node value to 0
	// but in queue we will store node value with its closest distance from the
	// source node, we will start by adding the (src,0) to queue
	// from this we will visit all it's adjacent nodes and will update other nodes
	// distance value
	private static void type1() {
		int[][] edges = {
				{0, 1},
				{0, 3},
				{3, 4},
				{4, 5},
				{5, 6},
				{1, 2},
				{2, 6},
				{6, 7},
				{7, 8},
				{6, 8}
		};
		int n = 9;
		int m = 10;
		int src = 0;

		// transform the edges to corresponding undirected adj list
		List<List<Integer>> adjList = new ArrayList<>();
		for (int i = 0; i < n; i++)
			adjList.add(new ArrayList<>());
		for (int[] edge : edges) {
			adjList.get(edge[0]).add(edge[1]);
			adjList.get(edge[1]).add(edge[0]);
		}

		// initializing the distance array to infinity
		int[] distance = new int[n];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[src] = 0;

		// initializing the queue to (src,0)
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { src, 0 });

		while (!queue.isEmpty()) {
			int[] startNode = queue.poll();
			int start = startNode[0];
			// prevDistance is the minimum distance from src
			int prevDis = startNode[1];

			// now we will check all its adjacent node
			for (int end : adjList.get(start)) {
				// it means distance[node] is greater than current point distance + edge weight,
				// so we will update the distance value and add it to queue
				// for an unweighted graph edge weight is 1
				if (distance[end] > prevDis + 1) {
					distance[end] = prevDis + 1;
					// we will only add to the queue when we get a shorter distance path
					queue.offer(new int[]{end, prevDis + 1});
				}
			}
		}
		// replacing the infinity value to -1
		for (int i = 0; i < n; i++)
			if (distance[i] == Integer.MAX_VALUE) distance[i] = -1;
		PrintUtl.print(distance);
	}

}
