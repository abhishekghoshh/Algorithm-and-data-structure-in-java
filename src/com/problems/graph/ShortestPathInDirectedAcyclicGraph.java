package com.problems.graph;

import com.util.PrintUtl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
 * Problem link :
 * https://practice.geeksforgeeks.org/problems/shortest-path-in-undirected-graph/1
 * 
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=ZUFQfFaU-8U&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=28
 * 
 * 
 * https://takeuforward.org/data-structure/shortest-path-in-directed-acyclic-graph-topological-sort-g-27/
 */

//with path weight
public class ShortestPathInDirectedAcyclicGraph {

	// Given a Directed Acyclic Graph of N vertices from 0 to N-1 and a 2D Integer
	// array(or vector) edges[ ][ ] of length M, where there is a directed edge from
	// edge[i][0] to edge[i][1] with a distance of edge[i][2] for all i, 0<=i

	// Find the shortest path from src(0) vertex to all the vertices and if it is
	// impossible to reach any vertex, then return -1 for that vertex.
	public static void main(String[] args) {
		type1();
	}

	// todo we will use the topo sort to arrange the nodes
	// we are given edges and nodes
	// first we have to transform it to adjacency list
	// we will find the shortest path in a directed acyclic graph,
	// so we will use topo sort using dfs
	// so in stack the ordered vertex will be stored
	// now our task is simple
	// use a distance array and set all the node value to infinity
	// and src node value to 0
	// pop from the stack until we find the source
	// so once we find the source node in stack then we will start popping one by
	// one go to its adjacent nodes update the distance value
	private static void type1() {
		int n = 6;
		int m = 7;
		int[][] edges = {
				{0, 1, 2},
				{0, 4, 1},
				{4, 5, 4},
				{4, 2, 2},
				{1, 2, 3},
				{2, 3, 6},
				{5, 3, 1}
		};
		int src = 0;

		// transforming the edges to adjacent list
		List<List<int[]>> adjList = new ArrayList<>();
		for (int i = 0; i < n; i++)
			adjList.add(new ArrayList<>());
		for (int[] edge : edges)
			adjList.get(edge[0]).add(new int[] { edge[1], edge[2] });

		Stack<Integer> stack = new Stack<>();
		boolean[] visited = new boolean[n];

		// doing the topo sort and after this loop the stack will be prepared
		for (int i = 0; i < n; i++)
			if (!visited[i])
				dfs(i, visited, stack, adjList);

		// while we find the source node, we will pop
		// just in case if node 0 is not the starting node of the graph
		while (stack.peek() != src) stack.pop();

		// initializing the distance array to infinity
		int[] distance = new int[n];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[src] = 0;

		// now we will pop from the stack one by one
		while (!stack.isEmpty()) {
			int start = stack.pop();
			// prevDistance is the minimum distance from src
			int prevDis = distance[start];

			// now we will check all its adjacent node
			for (int[] endNode : adjList.get(start)) {
				int end = endNode[0];
				int dis = endNode[1];
				// it means distance[end] is greater than start distance + edge weight,
				// which means if we go to end node via the start node, that will be shorter in distance,
				// so we will update the distance value and add it to stack again
				if (distance[end] > prevDis + dis) {
					distance[end] = prevDis + dis;
					stack.push(end);
				}
			}
		}
		// replacing the infinity value to -1
		for (int i = 0; i < n; i++)
			if (distance[i] == Integer.MAX_VALUE) distance[i] = -1;

		PrintUtl.print(distance);
	}

	private static void dfs(int start, boolean[] visited, Stack<Integer> stack, List<List<int[]>> adjList) {
		visited[start] = true;
		for (int[] node : adjList.get(start))
			if (!visited[node[0]])
				dfs(node[0], visited, stack, adjList);
		stack.push(start);
	}
}
